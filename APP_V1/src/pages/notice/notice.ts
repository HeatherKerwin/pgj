import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { NoticeDetailPage } from "../notice/noticeDetail";

@Component({
  selector: 'page-notice',
  templateUrl: 'notice.html'
})
export class NoticePage {
  segmentsArray = ['billNews','bankNews','marketNews','aboutNews'];
  segmentModel: string = this.segmentsArray[0];

  public pageNo=1;
  public page2No=1;
  public page3No=1;
  public page4No=1;
  public newsLists = [];
  public newsLists2 = [];
  public newsLists3 = [];
  public newsLists4 = [];

  constructor(
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService
  ) {
    this.initData1(1);
    this.initData2(1);
    this.initData3(1);
    this.initData4(1);

  }

  //票据新闻
  initData1(pagenumber) {
    this.apiSev.api("get", "app/newlist/?pagenumber="+pagenumber+"&type=1&memberid=1&belong=0", (res) => {
      if(res[0].response == 'success'){
        let objs=[];
        objs=res[0].msg[1];
        for (let i=0;i<objs.length;i++){
          this.newsLists.push(objs[i]);
        }
      }
    }, (error) => {},1,{});
  }
  doInfinite(infiniteScroll) {
    setTimeout(() => {
      this.pageNo=this.pageNo+1;
      this.initData1(this.pageNo);
      infiniteScroll.complete();
    }, 500);
  }

  //银行动态
  initData2(pagenumber) {
    this.apiSev.api("get", "app/newlist/?pagenumber="+pagenumber+"&type=2&memberid=1&belong=0", (res) => {
      if(res[0].response == 'success'){
        let objs=[];
        objs=res[0].msg[1];
        for (let i=0;i<objs.length;i++){
          this.newsLists2.push(objs[i]);
        }
      }
    }, (error) => {},1,{});

  }
  doInfinite2(infiniteScroll) {
    setTimeout(() => {
      this.page2No=this.page2No+1;
      this.initData2(this.page2No);
      infiniteScroll.complete();
    }, 500);
  }

  //市场分析
  initData3(pagenumber) {
    this.apiSev.api("get", "app/newlist/?pagenumber="+pagenumber+"&type=3&memberid=1&belong=0", (res) => {
      if(res[0].response == 'success'){
        let objs=[];
        objs=res[0].msg[1];
        for (let i=0;i<objs.length;i++){
          this.newsLists3.push(objs[i]);
        }
      }
    }, (error) => {},1,{});
  }
  doInfinite3(infiniteScroll) {
    setTimeout(() => {
      this.page3No=this.page3No+1;
      this.initData3(this.page3No);
      infiniteScroll.complete();
    }, 500);
  }

  //相关新闻
  initData4(pagenumber) {
    this.apiSev.api("get", "app/newlist/?pagenumber="+pagenumber+"&type=4&memberid=1&belong=0", (res) => {
      if(res[0].response == 'success'){
        let objs=[];
        objs=res[0].msg[1];
        for (let i=0;i<objs.length;i++){
          this.newsLists4.push(objs[i]);
        }
      }
    }, (error) => {},1,{});
  }
  doInfinite4(infiniteScroll) {
    setTimeout(() => {
      this.page4No=this.page4No+1;
      this.initData4(this.page4No);
      infiniteScroll.complete();
    }, 500);
  }

  //跳转查看详情
  newsSelected(newsid) {
    this.navCtrl.push(NoticeDetailPage,{
    newsid:newsid
    });
  }


  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==2){
      if(this.segmentsArray.indexOf(this.segmentModel)<3){
    this.segmentModel = this.segmentsArray[this.segmentsArray.indexOf(this.segmentModel)+1];
      }
    }
    //向右滑
    if(event.direction==4){
      if(this.segmentsArray.indexOf(this.segmentModel)>0){
    this.segmentModel = this.segmentsArray[this.segmentsArray.indexOf(this.segmentModel)-1];
      }
    }
  }

}
