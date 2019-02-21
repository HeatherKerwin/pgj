import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {SmallZhi1Page} from "./smallZhi1";

@Component({
  selector: 'page-smallZhi',
  templateUrl: 'smallZhi.html'
})
export class SmallZhiPage {
  public amount;//报价额度
  public remarkYp;//备注
  public money:number = 2;
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

  ToSmallZhi1Page(){
    let typePrice = this.money;
    let priceTypeId;
    let WAYBJ = "CGBJ";
    if(typePrice == 0){
      WAYBJ = "KSBJ";//(priceTypeId：5 90天一下，priceTypeId：8 90-178天，priceTypeId：11 187天以上)
      priceTypeId = "5"; // 提供的报价 类型（主表主键:小票-电票  90天，不限 (快速)）
    }else if(typePrice == 1){
      priceTypeId = "5"; // 提供的报价 类型（主表主键:小票-电票  90天，50 ）
    }else if(typePrice == 2){
      priceTypeId = "8"; // 提供的报价 类型（主表主键:小票-电票  90天，50-100 ）
    }else if(typePrice == 3){
      priceTypeId = "11"; // 提供的报价 类型（主表主键:小票-电票  90天，100-500）
    }
    this.navCtrl.push(SmallZhi1Page,{WAYBJ:WAYBJ,PRICETYPE_ID:priceTypeId});
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
          this.ToSmallZhi1Page();
          this.apiSev.itip(res.msg);
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1, data);

  }
}
