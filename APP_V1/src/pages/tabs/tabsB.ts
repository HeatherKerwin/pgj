import { Component } from '@angular/core';
import { apiService } from "../../api.service";
import { Platform } from 'ionic-angular';
import { MePageB } from '../me/meB';
import { NoticePage } from '../notice/notice';
import { ToolsPageB } from '../tools/toolsB';
import { OrderPageB } from '../order/orderB';
import { HomePageB } from '../home/homeB';
import { Storage } from '@ionic/storage';
import {NavParams} from "ionic-angular";

@Component({
  selector: 'tabs-pageB',
  templateUrl: 'tabsB.html'
})
export class TabsPageB {

  tab1Root = HomePageB;
  tab2Root = NoticePage;
  tab3Root = ToolsPageB;
  tab4Root = MePageB;
  tab5Root = OrderPageB;

  public index  = 0;
  public role:any = {};
  public memberId:any;

  constructor(public storage: Storage,public params: NavParams,public apiSev: apiService,public platform: Platform) {
    let index = this.params.get("INDEX");
    if(index!=null){
      this.index = index;
    }
  }

  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.surplus();
      this.NewMessage();
    });
  }

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
