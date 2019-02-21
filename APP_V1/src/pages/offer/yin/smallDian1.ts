import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {SmallDian2Page} from "./smallDian2";

@Component({
  selector: 'page-smallDian1',
  templateUrl: 'smallDian1.html'
})
export class SmallDian1Page {
  public maskDiv=false; //弹出层
  public PlatformPrice = false;

  public smallDianGuoGu = 0.00.toFixed(2);
  public smallDianChengShang = 0.00.toFixed(2);
  public smallDianWaiZi = 0.00.toFixed(2);
  public smallDianNongShang = 0.00.toFixed(2);
  public smallDianNongHe = 0.00.toFixed(2);
  public smallDianNongXin = 0.00.toFixed(2);
  public smallDianCunZhen = 0.00.toFixed(2);

  public smallDianGuoGu1 = 0.00.toFixed(2);
  public smallDianChengShang1 = 0.00.toFixed(2);
  public smallDianWaiZi1 = 0.00.toFixed(2);
  public smallDianNongShang1 = 0.00.toFixed(2);
  public smallDianNongHe1 = 0.00.toFixed(2);
  public smallDianNongXin1 = 0.00.toFixed(2);
  public smallDianCunZhen1 = 0.00.toFixed(2);

  public guogu:number;
  public chengshang:number;
  public waizi:number;
  public nongshang:number;
  public nonghe:number;
  public nongxin:number;
  public cunzhen:number;

  public guogu1:number;
  public chengshang1:number;
  public waizi1:number;
  public nongshang1:number;
  public nonghe1:number;
  public nongxin1:number;
  public cunzhen1:number;

  public WAYBJ;
  public priceTypeId;
  public memberId;
  public orgId;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params:NavParams) {
    this.priceTypeId = this.params.get("PRICETYPE_ID");
    this.WAYBJ = this.params.get("WAYBJ");
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    })
  }

  ToSmallDian2Page(){
    let guogu = this.guogu==null?"--" : (this.guogu/1).toFixed(2);
    let chengshang = this.chengshang==null?"--" : (this.chengshang/1).toFixed(2);
    let waizi = this.waizi==null?"--" : (this.waizi/1).toFixed(2);
    let nongshang = this.nongshang==null?"--" : (this.nongshang/1).toFixed(2);
    let nonghe = this.nonghe==null?"--" : (this.nonghe/1).toFixed(2);
    let nongxin = this.nongxin==null?"--" : (this.nongxin/1).toFixed(2);
    let cunzhen = this.cunzhen==null?"--" : (this.cunzhen/1).toFixed(2);

    let guogu1 = this.guogu1==null?"--" : (this.guogu1/1).toFixed(2);
    let chengshang1 = this.chengshang1==null?"--" : (this.chengshang1/1).toFixed(2);
    let waizi1 = this.waizi1==null?"--" : (this.waizi1/1).toFixed(2);
    let nongshang1 = this.nongshang1==null?"--" : (this.nongshang1/1).toFixed(2);
    let nonghe1 = this.nonghe1==null?"--" : (this.nonghe1/1).toFixed(2);
    let nongxin1 = this.nongxin1==null?"--" : (this.nongxin1/1).toFixed(2);
    let cunzhen1 = this.cunzhen1==null?"--" : (this.cunzhen1/1).toFixed(2);
    let WAYBJ = this.params.get("WAYBJ");
    this.navCtrl.push(SmallDian2Page ,{GUOGU:guogu,CHENGSHANG:chengshang,WAIZI:waizi,NONGSHANG:nongshang,NONGHE:nonghe,NONGXIN:nongxin,CUNZHEN:cunzhen,GUOGU1:guogu1,CHENGSHANG1:chengshang1,WAIZI1:waizi1,NONGSHANG1:nongshang1,NONGHE1:nonghe1,NONGXIN1:nongxin1,CUNZHEN1:cunzhen1,PRICETYPE_ID:this.priceTypeId,WAYBJ:WAYBJ});
  }
  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
  }

  //弹出下拉框时，取消scroll
  hiddenscroll(){
    //获取当前组件的ID
    let aboutContent = document.querySelector("ion-content");
    //获取当前组件下的scroll-content元素
    let scroll:any = aboutContent.querySelector(".scroll-content");
    if(this.maskDiv){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }
  /**
   * 对比价格
   */
  ContrastPrice(){
    this.PlatformPrice = true;
    this.apiSev.api(
      "post",
      "/app/discountrecord/getPriceContrast",
      (res) => {
        if(res.response == 'success') {
          let historypriceList = res.historypriceList;
          for (let i in historypriceList) {
            let hp = historypriceList[i];
            let type1 = hp.type1;
            let type6 = hp.type6;
            let type7 = hp.type7;
            let type2 = hp.type2;
            let type5 = hp.type5;
            let price = hp.price;
            let price1 = hp.price1;
            if(this.priceTypeId == 12){
              if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 1 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 13){
              if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 4){
                this.setChengShang(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 2 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 14){
              if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 4){
                this.setChengShang(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 2 && type6 == 2 && type5 == 3 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 15){
              if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 1 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 16){
              if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 2 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 17){
              if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 3 && type6 == 2 && type5 == 3 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 18){
              if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 1 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 19){
              if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 2 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }else if(this.priceTypeId == 20){
              if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 1){
                this.setGuoGu(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 2){
                this.setChengShang(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 3){
                this.setWaiZi(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 4){
                this.setNongShang(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 5){
                this.setNongHe(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 6){
                this.setNongXin(price,price1);
              }else if(type2 == 4 && type6 == 2 && type5 == 3 && type1 == 7){
                this.setCunZhen(price,price1);
              }
            }
          }
          this.maskDiv=true;

        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1);
  }

  loadData(){
    let data = {
      orgId:this.orgId,
      priceTypeId:this.priceTypeId,
      cityId:1
    };
    this.apiSev.api(
      "post",
      "/app/price/get/infoById",
      (res) => {
        let price = res.price;
        if(price != null){
          if(price.guogu!="--")this.guogu = price.guogu;
          if(price.chengshang!="--")this.chengshang = price.chengshang;
          if(price.waizi!='--')this.waizi = price.waizi;
          if(price.nongshang!='--')this.nongshang = price.nongshang;
          if(price.nonghe!='--')this.nonghe = price.nonghe;
          if(price.nongxin!='--')this.nongxin = price.nongxin;
          if(price.cunzhen!='--')this.cunzhen = price.cunzhen;

          if(price.guogu1!='--')this.guogu1 = price.guogu1;
          if(price.chengshang1!='--')this.chengshang1 = price.chengshang1;
          if(price.waizi1!='--')this.waizi1 = price.waizi1;
          if(price.nongshang1!='--')this.nongshang1 = price.nongshang1;
          if(price.nonghe1!='--')this.nonghe1 = price.nonghe1;
          if(price.nongxin1!='--')this.nongxin1 = price.nongxin1;
          if(price.cunzhen1!='--')this.cunzhen1 = price.cunzhen1;
        }
      }, (error) => {
      }, 1,data);
  }

  submit(){
    if((this.guogu==0||this.guogu==null)&&(this.chengshang==0||this.chengshang==null)&&(this.waizi==0||this.waizi==null)&&(this.nongshang==0||this.nongshang==null)&&(this.nongxin==0||this.nongxin==null)&&(this.nonghe==0||this.nonghe==null)&&(this.cunzhen==0||this.cunzhen==null)){
      this.apiSev.itip("你还没有报价不能提交");
    }else{
      this.ToSmallDian2Page();
    }
  }
  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }
  //给国股赋值
  setGuoGu(price,price1){
    if(price != null && price !=""){
      this.smallDianGuoGu = price;
    }else{
      this.smallDianGuoGu = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianGuoGu1 = price1;
    }else{
      this.smallDianGuoGu1 = "0";
    }
  }
  //给城商赋值
  setChengShang(price,price1){
    if(price != null && price !=""){
      this.smallDianChengShang = price;
    }else{
      this.smallDianChengShang = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianChengShang1 = price1;
    }else{
      this.smallDianChengShang1 = "0";
    }
  }
  //给外资赋值
  setWaiZi(price,price1){
    if(price != null && price !=""){
      this.smallDianWaiZi = price;
    }else{
      this.smallDianWaiZi = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianWaiZi1 = price1;
    }else{
      this.smallDianWaiZi1 = "0";
    }
  }
  //给农商赋值
  setNongShang(price,price1){
    if(price != null && price !=""){
      this.smallDianNongShang = price;
    }else{
      this.smallDianNongShang = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianNongShang1 = price1;
    }else{
      this.smallDianNongShang1 = "0";
    }
  }
  //给农合赋值
  setNongHe(price,price1){
    if(price != null && price !=""){
      this.smallDianNongHe = price;
    }else{
      this.smallDianNongHe = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianNongHe1 = price1;
    }else{
      this.smallDianNongHe1 = "0";
    }
  }
  //给农信赋值
  setNongXin(price,price1){
    if(price != null && price !=""){
      this.smallDianNongXin = price;
    }else{
      this.smallDianNongXin = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianNongXin1 = price1;
    }else{
      this.smallDianNongXin1 = "0";
    }
  }
  //给村镇赋值
  setCunZhen(price,price1){
    if(price != null && price !=""){
      this.smallDianCunZhen = price;
    }else{
      this.smallDianCunZhen = "0";
    }
    if(price1 != null && price1 !=""){
      this.smallDianCunZhen1 = price1;
    }else{
      this.smallDianCunZhen1 = "0";
    }
  }

  clear(){
    this.guogu=null;
    this.chengshang=null;
    this.waizi=null;
    this.nongshang=null;
    this.nonghe=null;
    this.nongxin=null;
    this.cunzhen=null;

    this.guogu1=null;
    this.chengshang1=null;
    this.waizi1=null;
    this.nongshang1=null;
    this.nonghe1=null;
    this.nongxin1=null;
    this.cunzhen1=null;
  }
}
