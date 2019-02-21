import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {TabsPage} from "../../tabs/tabs";
import {MyOfferPage} from "../myOffer";

@Component({
  selector: 'page-bigDian2',
  templateUrl: 'bigDian2.html'
})
export class BigDian2Page {
  public bigDianGuoGu = 0.00.toFixed(2);
  public bigDianDaShang = 0.00.toFixed(2);
  public bigDianXiaoShang = 0.00.toFixed(2);
  public bigDianWaiZi = 0.00.toFixed(2);
  public bigDianNongShang = 0.00.toFixed(2);
  public bigDianNongHe = 0.00.toFixed(2);
  public bigDianNongXin = 0.00.toFixed(2);
  public bigDianCunZhen = 0.00.toFixed(2);

  public guogu;
  public dashang;
  public xiaoshang;
  public waizi;
  public nongshang;
  public nonghe;
  public nongxin;
  public cunzhen;
  public orgId;
  public memberId;

  public maskDiv=false; //弹出层
  public PlatformPrice = false;
  public Confirm = false;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params: NavParams) {
    this.guogu = params.get("GUOGU");
    this.dashang = params.get("DASHANG");
    this.xiaoshang = params.get("XIAOSHANG");
    this.waizi = params.get("WAIZI");
    this.nongshang = params.get("NONGSHANG");
    this.nonghe = params.get("NONGHE");
    this.nongxin = params.get("NONGXIN");
    this.cunzhen = params.get("CUNZHEN");
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
  }

  /**
   * 对比价格
   */
  ContrastPrice(){
    this.apiSev.api(
      "post",
      "/app/discountrecord/getPriceContrast",
      (res) => {
        if(res.response == 'success') {
          var historypriceList = res.historypriceList;
          for (var i in historypriceList) {
            var hp = historypriceList[i];
            var type1 = hp.type1;
            var type6 = hp.type6;
            var type7 = hp.type7;
            var type2 = hp.type2;
            var price = hp.price;
            if(localStorage["priceTypeId"] == 2){
              if(type2 == 1 && type6 == 2 && type7 == 1){
                this.setPrice(type1,price);
              }
            }else if(localStorage["priceTypeId"] == 21){
              if(type2 == 1 && type6 == 2 && type7 == 0){
                this.setPrice(type1,price);
              }
            }
          }
          this.maskDiv=true;
          this.Confirm = false;
          this.PlatformPrice = true;
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1);
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

  back(){
    this.navCtrl.pop();
  }

  /**
   * 确认订单
   */
  save(){
    let priceTypeId = this.params.get("PRICETYPE_ID");
    let guogu = this.guogu;
    let dashang = this.dashang;
    let xiaoshang = this.xiaoshang;
    let waizi = this.waizi;
    let nongshang = this.nongshang;
    let nongxin = this.nongxin;
    let nonghe = this.nonghe;
    let cunzhen = this.cunzhen;
    let data={
      priceTypeId:priceTypeId,
      orgId:this.orgId,
      cityId:1,
      memberId:this.memberId,
      guogu:guogu,
      dashang:dashang,
      chengshang:xiaoshang,
      waizi:waizi,
      nongshang:nongshang,
      nonghe:nonghe,
      nongxin:nongxin,
      cunzhen:cunzhen,
      guogu1:"--",
      dashang1:"--",
      xiaoshang1:"--",
      waizi1:"--",
      nongshang1:"--",
      nonghe1:"--",
      nongxin1:"--",
      cunzhen1:"--",
      guogu2:"--",
      dashang2:"--",
      xiaoshang2:"--",
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
      this.bigDianWaiZi = price;
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
