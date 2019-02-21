import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {SmallZhi2Page} from "./smallZhi2_2";

@Component({
  selector: 'page-smallZhi1',
  templateUrl: 'smallZhi1.html'
})
export class SmallZhi1Page {
  public maskDiv=false; //弹出层
  public PlatformPrice = false;
  public orgId;
  public memberId;

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

  public smallDianGuoGu3 = 0.00.toFixed(2);
  public smallDianChengShang3 = 0.00.toFixed(2);
  public smallDianWaiZi3 = 0.00.toFixed(2);
  public smallDianNongShang3 = 0.00.toFixed(2);
  public smallDianNongHe3 = 0.00.toFixed(2);
  public smallDianNongXin3 = 0.00.toFixed(2);
  public smallDianCunZhen3 = 0.00.toFixed(2);

  public guogu;
  public chengshang;
  public waizi;
  public nongshang;
  public nongxin;
  public nonghe;
  public cunzhen;

  public guogu1;
  public chengshang1;
  public waizi1;
  public nongshang1;
  public nongxin1;
  public nonghe1;
  public cunzhen1;

  public guogu3;
  public chengshang3;
  public waizi3;
  public nongshang3;
  public nongxin3;
  public nonghe3;
  public cunzhen3;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params:NavParams) {
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      //this.loadData();
    })
  }

  /**
   * 加载最近的一次报价
   */
  loadData(){
    let priceTypeId = this.params.get("PRICETYPE_ID");
    let data = {
      orgId:this.orgId,
      priceTypeId:priceTypeId,
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

          if(price.guogu3!='--')this.guogu3 = price.guogu3;
          if(price.chengshang3!='--')this.chengshang3 = price.chengshang3;
          if(price.waizi3!='--')this.waizi3 = price.waizi1;
          if(price.nongshang3!='--')this.nongshang3 = price.nongshang3;
          if(price.nonghe3!='--')this.nonghe3 = price.nonghe3;
          if(price.nongxin3!='--')this.nongxin3 = price.nongxin3;
          if(price.cunzhen3!='--')this.cunzhen3 = price.cunzhen3;
        }
      }, (error) => {
      }, 1,data);
  }

  /**
   * 对比价格
   */
  ContrastPrice(){
    this.apiSev.api(
      "post",
      "/app/discountrecord/getPriceContrast",
      (res) => {
        this.maskDiv = true;
        this.PlatformPrice = true;
        if(res.state == "success"){
          var historypriceList = res.historypriceList;
          for (var i in historypriceList) {
            var hp = historypriceList[i];
            var type1 = hp.type1;
            var type6 = hp.type6;
            var type7 = hp.type7;
            var type2 = hp.type2;
            var type5 = hp.type5;
            var price = hp.price;
            var price1 = hp.price1;
            var price3 = hp.matrueprice;
            if(localStorage["priceTypeId"] == 5){
              if(type2 == 2 && type6 == 1 && type1 == 1){
                this.setGuoGu(price,price1,price3);
              }else if(type2 == 2 && type6 == 1 && type1 == 2){
                this.setChengShang(price,price1,price3);
              }else if(type2 == 2 && type6 == 1 && type1 == 3){
                this.setWaiZi(price,price1,price3);
              }else if(type2 == 2 && type6 == 1 && type1 == 4){
                this.setNongShang(price,price1,price3);
              }else if(type2 == 2 && type6 == 1 && type1 == 5){
                this.setNongHe(price,price1,price3);
              }else if(type2 == 2 && type6 == 1 && type1 == 6){
                this.setNongXin(price,price1,price3);
              }else if(type2 == 2 && type6 == 1 && type1 == 7){
                this.setCunZhen(price,price1,price3);
              }
            }else if(localStorage["priceTypeId"] == 8){
              if(type2 == 3 && type6 == 1 && type1 == 1){
                this.setGuoGu(price,price1,price3);
              }else if(type2 == 3 && type6 == 1 && type1 == 2){
                this.setChengShang(price,price1,price3);
              }else if(type2 == 3 && type6 == 1 && type1 == 3){
                this.setWaiZi(price,price1,price3);
              }else if(type2 == 3 && type6 == 1 && type1 == 4){
                this.setNongShang(price,price1,price3);
              }else if(type2 == 3 && type6 == 1 && type1 == 5){
                this.setNongHe(price,price1,price3);
              }else if(type2 == 3 && type6 == 1 && type1 == 6){
                this.setNongXin(price,price1,price3);
              }else if(type2 == 3 && type6 == 1 && type1 == 7){
                this.setCunZhen(price,price1,price3);
              }
            }else if(localStorage["priceTypeId"] ==  11){
              if(type2 == 4 && type6 == 1 && type1 == 1){
                this.setGuoGu(price,price1,price3);
              }else if(type2 == 4 && type6 == 1 && type1 == 2){
                this.setChengShang(price,price1,price3);
              }else if(type2 == 4 && type6 == 1 && type1 == 3){
                this.setWaiZi(price,price1,price3);
              }else if(type2 == 4 && type6 == 1 && type1 == 4){
                this.setNongShang(price,price1,price3);
              }else if(type2 == 4 && type6 == 1 && type1 == 5){
                this.setNongHe(price,price1,price3);
              }else if(type2 == 4 && type6 == 1 && type1 == 6){
                this.setNongXin(price,price1,price3);
              }else if(type2 == 4 && type6 == 1 && type1 == 7){
                this.setCunZhen(price,price1,price3);
              }
            }
          }
          this.maskDiv = true;
          this.PlatformPrice = true;
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1);
  }

  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }

  ToSmallZhi2Page(){
    let priceTypeId = this.params.get("PRICETYPE_ID");

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

    let guogu3 = this.guogu3==null?"--" : (this.guogu3/1).toFixed(2);
    let chengshang3 = this.chengshang3==null?"--" : (this.chengshang3/1).toFixed(2);
    let waizi3 = this.waizi3==null?"--" : (this.waizi3/1).toFixed(2);
    let nongshang3 = this.nongshang3==null?"--" : (this.nongshang3/1).toFixed(2);
    let nonghe3 = this.nonghe3==null?"--" : (this.nonghe3/1).toFixed(2);
    let nongxin3 = this.nongxin3==null?"--" : (this.nongxin3/1).toFixed(2);
    let cunzhen3 = this.cunzhen3==null?"--" : (this.cunzhen3/1).toFixed(2);
    let WAYBJ = this.params.get("WAYBJ");
    this.navCtrl.push(SmallZhi2Page,{GUOGU:guogu,CHENGSHANG:chengshang,WAIZI:waizi,NONGSHANG:nongshang,NONGHE:nonghe,NONGXIN:nongxin,CUNZHEN:cunzhen,GUOGU1:guogu1,CHENGSHANG1:chengshang1,WAIZI1:waizi1,NONGSHANG1:nongshang1,NONGHE1:nonghe1,NONGXIN1:nongxin1,CUNZHEN1:cunzhen1,GUOGU3:guogu3,CHENGSHANG3:chengshang3,WAIZI3:waizi3,NONGSHANG3:nongshang3,NONGHE3:nonghe3,NONGXIN3:nongxin3,CUNZHEN3:cunzhen3,PRICETYPE_ID:priceTypeId,WAYBJ:WAYBJ});
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

  submit(){
    if((this.guogu==0||this.guogu==null)&&(this.chengshang==0||this.chengshang==null)&&(this.waizi==0||this.waizi==null)&&(this.nongshang==0||this.nongshang==null)&&(this.nongxin==0||this.nongxin==null)&&(this.nonghe==0||this.nonghe==null)&&(this.cunzhen==0||this.cunzhen==null)){
      this.apiSev.itip("你还没有报价不能提交");
    }else{
       this.ToSmallZhi2Page();
    }
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

  //给国股赋值
  setGuoGu(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianGuoGu3 = price3;
    }else{
      this.smallDianGuoGu3 = "0";
    }
  }
  //给城商赋值
  setChengShang(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianChengShang3 = price3;
    }else{
      this.smallDianChengShang3 = "0";
    }
  }
  //给外资赋值
  setWaiZi(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianWaiZi3 = price3;
    }else{
      this.smallDianWaiZi3 = "0";
    }
  }
  //给农商赋值
  setNongShang(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianNongShang3 = price3;
    }else{
      this.smallDianNongShang3 = "0";
    }
  }
  //给农合赋值
  setNongHe(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianNongHe3 = price3;
    }else{
      this.smallDianNongHe3 = "0";
    }
  }
  //给农信赋值
  setNongXin(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianNongXin3 = price3;
    }else{
      this.smallDianNongXin3 = "0";
    }
  }
  //给村镇赋值
  setCunZhen(price,price1,price3){
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
    if(price3 != null && price3 !=""){
      this.smallDianCunZhen3 = price3;
    }else{
      this.smallDianCunZhen3 = "0";
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

    this.guogu3=null;
    this.chengshang3=null;
    this.waizi3=null;
    this.nongshang3=null;
    this.nonghe3=null;
    this.nongxin3=null;
    this.cunzhen3=null;
  }
}
