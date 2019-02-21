import {Component} from '@angular/core';
import { apiService } from "../../api.service";
import { MePage } from '../me/me';
import { ToolsPage } from '../tools/tools';
import {Bbs} from "../home/bbs";
import { OrderPage } from '../order/order';
import { HomePage } from '../home/home';
import { Storage } from '@ionic/storage';
import {NavParams,Platform} from "ionic-angular";
import {AndroidBbsPage} from "../home/androidbbs";

@Component({
  selector: 'tabs-page',
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  public tab2Root:any = Bbs;
  tab3Root = OrderPage;
  tab4Root = ToolsPage;
  tab5Root = MePage;

  public index  = 0;
  public role:any = {};
  public orgId:any;
  public memberId='';

  constructor(public storage: Storage,public params: NavParams,public apiSev: apiService,public platform: Platform) {
    let index = this.params.get("INDEX");
    if(index!=null){
      this.index = index;
    }
    this.bbs();
  }



  ionViewDidEnter(){
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.NewMessage();
      this.surplus();
    });
  }

  bbs(){
    if(this.platform.is('ios')) {
      this.storage.get('userInfo').then((data) => {
        this.apiSev.MyID = data.id;
        this.apiSev.MyPhone = data.mobile;
        this.tab2Root = Bbs
      })
    }else if(this.platform.is('android')){
      this.storage.get('userInfo').then((data) => {
        this.apiSev.MyID = data.id;
        this.apiSev.MyPhone = data.mobile;
        this.tab2Root = AndroidBbsPage
      })
    }
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
