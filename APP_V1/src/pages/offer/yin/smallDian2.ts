import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {TabsPage} from "../../tabs/tabs";
import {MyOfferPage} from "../myOffer";

@Component({
  selector: 'page-smallDian2',
  templateUrl: 'smallDian2.html'
})
export class SmallDian2Page {
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

  public priceTypeId;
  public memberId;
  public orgId;
  public maskDiv=false; //弹出层
  public Confirm = false;
  public PlatformPrice = false;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params: NavParams) {
    this.guogu = params.get("GUOGU");
    this.chengshang = params.get("CHENGSHANG");
    this.waizi = params.get("WAIZI");
    this.nongshang = params.get("NONGSHANG");
    this.nonghe = params.get("NONGHE");
    this.nongxin = params.get("NONGXIN");
    this.cunzhen = params.get("CUNZHEN");

    this.guogu1 = params.get("GUOGU1");
    this.chengshang1 = params.get("CHENGSHANG1");
    this.waizi1 = params.get("WAIZI1");
    this.nongshang1 = params.get("NONGSHANG1");
    this.nonghe1 = params.get("NONGHE1");
    this.nongxin1 = params.get("NONGXIN1");
    this.cunzhen1 = params.get("CUNZHEN1");

    this.priceTypeId = params.get("PRICETYPE_ID");
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    })
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
   * 确认价格
   */
  save(){
    let WAYBJ = this.params.get("WAYBJ");
    let guogu = this.guogu;
    let chengshang = this.chengshang;
    let waizi = this.waizi;
    let nongshang = this.nongshang;
    let nongxin = this.nongxin;
    let nonghe = this.nonghe;
    let cunzhen = this.cunzhen;

    let guogu1 = this.guogu1;
    let chengshang1 = this.chengshang1;
    let waizi1 = this.waizi1;
    let nongshang1 = this.nongshang1;
    let nongxin1 = this.nongxin1;
    let nonghe1 = this.nonghe1;
    let cunzhen1 = this.cunzhen1;

    let data={
      baojiaWay:WAYBJ,
      way:'0',
      priceTypeId:this.priceTypeId,
      orgId:this.orgId,
      cityId:1,
      memberId:this.memberId,
      guogu:guogu,
      chengshang:chengshang,
      waizi:waizi,
      nongshang:nongshang,
      nonghe:nonghe,
      nongxin:nongxin,
      cunzhen:cunzhen,
      guogu1:guogu1,
      chengshang1:chengshang1,
      waizi1:waizi1,
      nongshang1:nongshang1,
      nonghe1:nonghe1,
      nongxin1:nongxin1,
      cunzhen1:cunzhen1,
      guogu2:"--",
      chengshang2:"--",
      waizi2:"--",
      nongshang2:"--",
      nonghe2:"--",
      nongxin2:"--",
      cunzhen2:"--",
    };
    this.apiSev.api(
      "post",
      "/app/price/save2",
      (res) => {
        if(res.response == 'success') {
          this.maskDiv=true;
          this.Confirm = true;
          this.PlatformPrice = false;
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1,data);
  }

  back(){
    this.navCtrl.pop();
  }

  /**
   * 对比价格
   */
  ContrastPrice(){
    this.apiSev.api(
      "post",
      "/app/discountrecord/getPriceContrast",
      (res) => {
        this.maskDiv=true;
        this.Confirm = false;
        this.PlatformPrice = true;
        if(res.state == 'success') {
          this.PlatformPrice = true;
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

        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1);
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

  closeMask(){
    this.maskDiv = false;
  }

  ContinuePrice(){
    this.navCtrl.push(TabsPage,{INDEX:1});
  }

  /**
   * 查看报价
   */
  queryPrice(){
    this.navCtrl.push(MyOfferPage);
  }
}
