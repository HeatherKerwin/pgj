import { Component } from '@angular/core';
import { NavController, ViewController,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Bankline} from "./banks/banks";
import { CounterPage} from "./counter/counter";
import { ResultPage } from "./gongcui/result";
import { OpinionPage } from "./kefu/opinion";
import { ShiborPage } from "./shibor/shibor";
import {QueryYP} from "./queryYP/queryYP";
import { Login } from '../login/login';
import { GuideimgBPage } from '../home/guide/guideimgB';
import { InquiryPage } from '../home/inquiry/inquiry';
import {NewmanagePage} from "../newmanage/newmanage";

@Component({
  selector: 'page-toolsB',
  templateUrl: 'toolsB.html'
})
export class ToolsPageB {
  public memberId:any;

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService,public modalCtrl:ModalController) {
  }


  ionViewDidEnter(){
    this.storage.get('lgtoken').then((lgtoken)=>{
      if (lgtoken === null || lgtoken === undefined || lgtoken === ''){
        let profileModal = this.modalCtrl.create(Login);
        profileModal.present();
      }
      else {
        this.storage.get('userInfo').then((data) => {
          this.memberId = data.id;
        })
      }
    });
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.surplus();
      this.NewMessage();
    });
  }

  ShiBor()  {
    this.navCtrl.push(ShiborPage);
  }

  bankCode() {
    this.navCtrl.push(Bankline);
  }
  counTer() {
    this.navCtrl.push(CounterPage,{
      ORG_TYPE:0////机构1 企业0
    });
  }

  //银票询价
  inquiry(){
    this.navCtrl.push(InquiryPage);
  }

  queryZn(){
    let profileModal = this.modalCtrl.create(GuideimgBPage);
    profileModal.present();
  }

  reSult()  {
    this.navCtrl.push(ResultPage);
  }

  OpiNion() {
    this.navCtrl.push(OpinionPage);
  }

  queryYp(){
/*    this.navCtrl.push(QueryYP,{
      ORG_TYPE:0////机构1 企业0
    });*/
    //票据管理
    this.navCtrl.push(NewmanagePage);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  //接单红点
  surplus(){
    if(this.memberId!=null && this.memberId!='') {
      let data: any = {
        memberId: this.memberId,
      };
      this.apiSev.api("newpost", "order/get/count", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data.count >= 1) {
            this.apiSev.SingularB = data.data.count;
          } else if (data.data.count == 0) {
            this.apiSev.SingularB = '';
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
