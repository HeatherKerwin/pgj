import { Component } from '@angular/core';
import { NavController, ViewController,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Bankline} from "./banks/banks";
import { CounterPage} from "./counter/counter";
import { ResultPage } from "./gongcui/result";
import { OpinionPage } from "./kefu/opinion";
import { ShiborPage } from "./shibor/shibor";
import {QueryYP} from "./queryYP/queryYP"
import { Login } from '../login/login';
import { GuideimgPage } from '../home/guide/guideimg';
import {NewmanagePage} from "../newmanage/newmanage";

@Component({
  selector: 'page-tools',
  templateUrl: 'tools.html'
})


export class ToolsPage {

  public orgId:any;
  public memberId:any;
  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public modalCtrl:ModalController) {

  }

  ShiBor()  {
    this.navCtrl.push(ShiborPage);
  }

  bankCode() {
    this.navCtrl.push(Bankline);
  }

  counTer() {
    this.navCtrl.push(CounterPage,{
      ORG_TYPE:1////机构1 企业0
    });
  }
  OpiNion() {
    this.navCtrl.push(OpinionPage);
  }

  reSult()  {
    this.navCtrl.push(ResultPage);
  }

  queryYp(){
/*    this.navCtrl.push(QueryYP,{
      ORG_TYPE:1////机构1 企业0
    });*/
    //票据管理
    this.navCtrl.push(NewmanagePage);
  }

  queryZn(){
    let profileModal = this.modalCtrl.create(GuideimgPage);
    profileModal.present();
  }


  ionViewDidEnter() {
    this.storage.get('lgtoken').then((lgtoken)=>{
      if (lgtoken === null || lgtoken === undefined || lgtoken === ''){
        let profileModal = this.modalCtrl.create(Login);
        profileModal.present();
      }
      else {
        this.storage.get('userInfo').then((data) => {
          this.memberId = data.id;
          this.NewMessage();
          this.surplus();
        })
      }
    });
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
  }

  //tab红点
  surplus(){
    if(this.memberId!=null && this.memberId!='') {
      let data: any = {
        memberId: this.memberId,
      };
      this.apiSev.api("newpost", "dispatch/get/count", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data.count >= 1) {
            this.apiSev.Singular = data.data.count;
          } else if (data.data.count == 0) {
            this.apiSev.Singular = '';
          }
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
    }
  }

  //是否有新消息
  NewMessage(){
    let data ={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "systeminfo/get/unread", (res) => {
      let num = res.data.data.num;
      if(num>0){
        this.apiSev.systeminfo = num;
      }else{
        this.apiSev.systeminfo = '';
      }
    },(error) => {

    }, 500,data);
  }
}
