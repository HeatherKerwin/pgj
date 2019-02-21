import { Component } from '@angular/core';
import {NavController , NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {MyInquiryReplyDetailPage} from "./myInquiryReplyDetail";


@Component({
  selector: 'page-myInquiryReply',
  templateUrl: 'myInquiryReply.html'
})

export class MyInquiryReplyPage {
  public memberId;
  public isOk:boolean; //是否还有数据
  public pageNo = 1;
  public InquiryList = [];
  constructor(public storage: Storage, public navCtrl: NavController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.loadData(this.pageNo);
    })
  }

  ionViewDidEnter(){

  }

  loadData(pagenumber){
    this.isOk = false;
      let data1:any = {
        memberid:this.memberId,
        pageIndex:pagenumber
      };
      this.apiSev.api(
        "post",
        "/app/inquiryReply/inqueryReplyList",
        (res) => {
          console.log(res);
          if(res.response == 'success'){
            let objs = res.data;
            if(objs.length>0){
              this.isOk = false;
              if(objs.length==10){//说明可能还有数据
                this.isOk = true;
              }
              for (let i=0;i<objs.length;i++){
                this.InquiryList.push(objs[i]);
              }
            }
          }
        }, (error) => {},6100,data1);
  }
  doInfinite(infiniteScroll) {
    if(this.isOk){
      setTimeout(() => {
        this.pageNo=this.pageNo+1;
        this.loadData(this.pageNo);
        infiniteScroll.complete();
      }, 6100);
    }else{
      infiniteScroll.enable(false);
      infiniteScroll.complete();
    }
  }

  ToDetailPage(id){
    this.navCtrl.push(MyInquiryReplyDetailPage,{'ID':id});
  }
}
