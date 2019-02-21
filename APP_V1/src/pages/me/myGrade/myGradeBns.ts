import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-myGradeBns',
  templateUrl: 'myGradeBns.html'
})

export class MyGradeBnsPage {

  public ContentList = [];
  public price;//票据真实
  public service;//服务态度
  public speed;//确认效率
  public singleRate;//订单成交率
  public endorseTime;//背书时间
  public memberId;

  public pageNo = 1;
  public total;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params:NavParams) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.LoadGrade();
      this.LoadContent(10);
    });

  }

  //加载评分
  LoadGrade() {
    let role = this.params.get("ROLE");
    let data = {memberId:this.memberId,role:role};
    this.apiSev.api(
      "newpost",
      "comments/get",
      (res) => {
          if (res.data.response == "success") {
            let thisData = res.data.data;
            if(thisData.singleRate!=null){
              this.singleRate = (thisData.singleRate.toFixed(2)*100) + '%';
            }else {
              this.singleRate ='--';
            }//订单成交率
            this.speed = thisData.speed != '--'?thisData.speed.toFixed(2):thisData.speed;//确认效率
            this.service = thisData.service != '--'?thisData.service.toFixed(2):thisData.service;//服务态度
            this.price = thisData.price !='--'? thisData.price.toFixed(2):thisData.price;//价格真实
            let t = thisData.endorseTime;
            this.endorseTime = Math.floor(t/60)+"分"+(t%60/100).toFixed(2).slice(-2)+'秒';//背书时间
          }
      }, (error) => {
      }, 1, data);
  }

  LoadContent(pageNo){
    let role = this.params.get("ROLE");
    let data = {
      memberId:this.memberId,
      role:role,
      pageIndex:pageNo,
      pageSize:1
    };
    this.apiSev.api(
      "newpost",
      "comments/get/discinfo",
      (res) => {
        let result = res.data;
        if(result.response == 'success') {
          let data = result.data;
          this.total = data.total;
          for (let i=0;i<data.list.length;i++){
            this.ContentList.push(data.list[i]);
          }
        }
      }, (error) => {
      }, 1, data);
  }
  doInfinite(infiniteScroll) {
    if(this.pageNo<this.total){
      setTimeout(() => {
        this.pageNo=this.pageNo+1;
        this.LoadContent(this.pageNo);
        infiniteScroll.complete();
      }, 500);
    }else{
      infiniteScroll.enable(false);
      infiniteScroll.complete();
    }
  }


}
