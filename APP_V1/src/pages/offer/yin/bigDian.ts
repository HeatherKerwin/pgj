import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {BigDian1Page} from "./bigDian1";

@Component({
  selector: 'page-bigDian',
  templateUrl: 'bigDian.html'
})
export class BigDianPage {
  public amount;//报价额度
  public remarkYp;//备注
  public acceptTime:number = 0;
  public orgId;
  public memberId;
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      this.loadPrice();
    })
  }

  ToBigDian1Page(){
    let priceTypeId;
    if(this.acceptTime==1){
      priceTypeId = 2;
    }else if(this.acceptTime == 0){
      priceTypeId = 21;
    }
    this.navCtrl.push(BigDian1Page,{PRICETYPE_ID:priceTypeId});
  }

  //加载金额
  loadPrice(){
    var data = {orgId:this.orgId};
    this.apiSev.api(
      "post",
      "/app/orgLimit/get/priceByOrgId",
      (res) => {
        if(res.response == 'success') {
          if(res.orgLimit != null){
            this.amount=res.orgLimit.price;
            this.remarkYp=res.orgLimit.remarkYp;
          }
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1, data);
  }

  /**
   * 用户点击开始报价
   */
  price(){
    let amount = this.amount;//额度
    let remarks = this.remarkYp;//备注
    if(amount == "" || amount==null){
      this.apiSev.itip("报价额度不能为空");
      return;
    }
    let data = {
      orgId:this.orgId,
      price:amount,
      remarkYp:remarks
    }
    this.apiSev.api(
      "post",
      "/app/orgLimit/update/priceAndTime",
      (res) => {
        if(res.response == "success"){
          this.ToBigDian1Page();
          this.apiSev.itip(res.msg);
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1, data);

  }
}
