import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {BigDian2Page} from "./bigDian2";

@Component({
  selector: 'page-bigDian1',
  templateUrl: 'bigDian1.html'
})
export class BigDian1Page {
  public bigDianGuoGu = 0.00.toFixed(2);
  public bigDianDaShang = 0.00.toFixed(2);
  public bigDianXiaoShang = 0.00.toFixed(2);
  public bigDianWaiZi = 0.00.toFixed(2);
  public bigDianNongShang = 0.00.toFixed(2);
  public bigDianNongHe = 0.00.toFixed(2);
  public bigDianNongXin = 0.00.toFixed(2);
  public bigDianCunZhen = 0.00.toFixed(2);

  public guogu:number;
  public dashang:number;
  public xiaoshang:number;
  public waizi:number;
  public nongshang:number;
  public nonghe:number;
  public nongxin:number;
  public cunzhen:number;
  public orgId;
  public memberId;
  public maskDiv=false; //弹出层
  public PlatformPrice = true;
  public priceTypeId;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params: NavParams) {
    this.priceTypeId = this.params.get("PRICETYPE_ID");
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      //this.loadData();
    })
  }

  ToBigDian2Page(){
    let guogu = this.guogu==null?"--" : (this.guogu/1).toFixed(2);
    let dashang = this.dashang==null?"--" : (this.dashang/1).toFixed(2);
    let xiaoshang = this.xiaoshang==null?"--" : (this.xiaoshang/1).toFixed(2);
    let waizi = this.waizi==null?"--" : (this.waizi/1).toFixed(2);
    let nongshang = this.nongshang==null?"--" : (this.nongshang/1).toFixed(2);
    let nonghe = this.nonghe==null?"--" : (this.nonghe/1).toFixed(2);
    let nongxin = this.nongxin==null?"--" : (this.nongxin/1).toFixed(2);
    let cunzhen = this.cunzhen==null?"--" : (this.cunzhen/1).toFixed(2);
    this.navCtrl.push(BigDian2Page,{GUOGU:guogu,DASHANG:dashang,XIAOSHANG:xiaoshang,WAIZI:waizi,NONGSHANG:nongshang,NONGHE:nonghe,NONGXIN:nongxin,CUNZHEN:cunzhen,PRICETYPE_ID:this.priceTypeId});
  }

  /**
   * 对比价格
   */
  ContrastPrice(){
    this.apiSev.api(
      "post",
      "/app/discountrecord/getPriceContrast",
      (res) => {
        if(res.state == 'success') {
          var historypriceList = res.historypriceList;
          for (var i in historypriceList) {
            var hp = historypriceList[i];
            var type1 = hp.type1;
            var type6 = hp.type6;
            var type7 = hp.type7;
            var type2 = hp.type2;
            var price = hp.price;
            if(this.priceTypeId == 2){
              if(type2 == 1 && type6 == 2 && type7 == 1){
                this.setPrice(type1,price);
              }
            }else if(this.priceTypeId == 21){
              if(type2 == 1 && type6 == 2 && type7 == 0){
                this.setPrice(type1,price);
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

  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
    }
    //隐藏滚动条  m,
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

  submit(){
    if((this.guogu==0||this.guogu==null)&&(this.dashang==0||this.dashang==null)&&(this.xiaoshang==0||this.xiaoshang==null)&&(this.waizi==0||this.waizi==null)&&(this.nongshang==0||this.nongshang==null)&&(this.nongxin==0||this.nongxin==null)&&(this.nonghe==0||this.nonghe==null)&&(this.cunzhen==0||this.cunzhen==null)){
        this.apiSev.itip("你还没有报价不能提交");
    }else{
      this.ToBigDian2Page();
    }
  };

  clear(){
    this.guogu=null;
    this.dashang=null;
    this.xiaoshang=null;
    this.waizi=null;
    this.nongshang=null;
    this.nonghe=null;
    this.nongxin=null;
    this.cunzhen=null;
  }

  /**
   * 加载最近一次的报价
   */
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
          if(price.dashang!="--")this.dashang = price.dashang;
          if(price.chengshang!="--")this.xiaoshang = price.chengshang;
          if(price.waizi!='--')this.waizi = price.waizi;
          if(price.nongshang!='--')this.nongshang = price.nongshang;
          if(price.nonghe!='--')this.nonghe = price.nonghe;
          if(price.nongxin!='--')this.nongxin = price.nongxin;
          if(price.cunzhen!='--')this.cunzhen = price.cunzhen;
        }
      }, (error) => {
      }, 1,data);
  }
//赋值
  setPrice(type1,price){
    if(type1 == 1){
      this.bigDianGuoGu = price;
    }
    if(type1 == 2){
      this.bigDianXiaoShang = price;
    }
    if(type1 == 3){
      this.bigDianWaiZi = price;
    }
    if(type1 == 4){
      this.bigDianNongShang = price;
    }
    if(type1 == 5){
      this.bigDianNongHe = price;
    }
    if(type1 == 6){
      this.bigDianNongXin = price;
    }
    if(type1 == 7){
      this.bigDianCunZhen = price;
    }
    if(type1 == 8){
      this.bigDianDaShang = price;
    }
  }
}
