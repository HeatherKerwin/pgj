import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-myGradeOrg',
  templateUrl: 'myGradeOrg.html'
})

export class MyGradeOrgPage {
  public ContentList = [];
  public price;//价格真实
  public service;//服务态度
  public speed;//确认效率
  public priceDurative;//历史报价持续性
  public singleRate;//订单成交率
  public advanceTime;//打款速度
  public memberId;
  public orgId;

  public pageNo = 1;
  public total;
  constructor(public storage: Storage, public navCtrl: NavController, public apiSev: apiService,public params:NavParams) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.LoadGrade();
      this.LoadContent(this.pageNo);
    })

  }
  //加载评分
  LoadGrade() {
    let role = this.params.get("ROLE");
    let data = {
      memberId:this.memberId,
      role:role
    };
    this.apiSev.api(
      "newpost",
      "comments/get",
      (res) => {
        if(res.data.response == "success"){
          let thisData = res.data.data;
          this.singleRate = (thisData.singleRate*100).toFixed(2)+'%';//订单成交率
          this.speed = thisData.speed !='--'?thisData.speed.toFixed(2):thisData.speed;//确认效率
          let t = thisData.advanceTime;
          this.advanceTime = Math.floor(t/60)+"分"+(t%60/100).toFixed(2).slice(-2)+'秒';//打款速度
          this.priceDurative = ((thisData.priceDurative)*100).toFixed(0)+'%';//历史报价持续性
          this.service = thisData.service !='--'? thisData.service.toFixed(2):thisData.service;//服务态度
          this.price = thisData.price !='--'? thisData.price.toFixed(2):thisData.price;//价格真实
        }
      }, (error) => {
      }, 500, data);
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
