import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {SmallDian1Page} from "./smallDian1";

@Component({
  selector: 'page-smallDian',
  templateUrl: 'smallDian.html'
})
export class SmallDianPage {
  public amount;//报价额度
  public remarkYp;//备注
  public orgId;
  public memberId;
  public acceptTime:number = 2;
  public typePrice:number = 2;
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      this.loadPrice();
    })
  }

  ToSmallDian1Page(){
    let acceptTime = this.acceptTime;
    let typePrice = this.typePrice;
    let priceTypeId;
    let WAYBJ;
    if(typePrice == 0){
      WAYBJ = 'KSBJ';
    }else{
      WAYBJ = 'CGBJ';
    }
    if(acceptTime == 0 && typePrice == 0){
      priceTypeId = "12"; // 提供的报价 类型（主表主键:小票-电票  90天，不限 (快速)）
    }else if(acceptTime == 0 && typePrice == 3){
      priceTypeId = "18"; // 提供的报价 类型（主表主键:小票-电票  90天，50 ）
    }else if(acceptTime == 0 && typePrice == 2){
      priceTypeId = "15"; // 提供的报价 类型（主表主键:小票-电票  90天，50-100 ）
    }else if(acceptTime == 0 && typePrice == 1){
      priceTypeId = "12"; // 提供的报价 类型（主表主键:小票-电票  90天，100-500）
    }else if(acceptTime == 1 && typePrice == 0){
      priceTypeId = "13"; // 提供的报价 类型（主表主键:小票-电票  90-178天，不限(快速) ）
    }else if(acceptTime == 1 && typePrice == 3){
      priceTypeId = "19"; // 提供的报价 类型（主表主键:小票-电票  90-178天，50）
    }else if(acceptTime == 1 && typePrice == 2){
      priceTypeId = "16"; // 提供的报价 类型（主表主键:小票-电票  90-178天，50-100 ）
    }else if(acceptTime == 1 && typePrice == 1){
      priceTypeId = "13"; // 提供的报价 类型（主表主键:小票-电票  90-178天，100-500 ）
    }else if(acceptTime == 2 && typePrice == 0){
      priceTypeId = "14"; // 提供的报价 类型（主表主键:小票-电票  187天，不限 (快速)）
    }else if(acceptTime == 2 && typePrice == 3){
      priceTypeId = "20"; // 提供的报价 类型（主表主键:小票-电票  187天，50 ）
    }else if(acceptTime == 2 && typePrice == 2){
      priceTypeId = "17"; // 提供的报价 类型（主表主键:小票-电票  187天，50-100 ）
    }else if(acceptTime == 2 && typePrice == 1){
      priceTypeId = "14"; // 提供的报价 类型（主表主键:小票-电票  178天，100-500 ）
    }
    this.navCtrl.push(SmallDian1Page,{WAYBJ:WAYBJ,WAY:'0',PRICETYPE_ID:priceTypeId});
  }

  loadPrice(){
    let data = {orgId:this.orgId};
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
          this.ToSmallDian1Page();
          this.apiSev.itip(res.msg);
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1, data);

  }
}
