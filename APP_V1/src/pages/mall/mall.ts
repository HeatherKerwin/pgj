import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";

//积分收支明细
import { mallDetailedPage } from '../../pages/mall/mallDetailed';
//兑换记录
import { mallExchangeNotePage } from '../../pages/mall/mallExchangeNote';
//兑换商品
import { mallListPage } from '../../pages/mall/mallList';
//兑换商品详情
import { mallExchangeDetailedPage } from '../../pages/mall/mallExchangeDetailed';
//积分攻略
import { mallStrategyPage } from '../../pages/mall/mallStrategy';
//积分攻略
import { mallPlacePage } from '../../pages/mall/mallPlace';
import {TabsPage} from "../tabs/tabs";
import {TabsPageB} from "../tabs/tabsB";

@Component({
  selector: 'page-mall',
  templateUrl: 'mall.html'
})
export class mallPage {
  public malls=[];
  public mall:any={
    integeral:0,
    sign:'签到',
    banner1:'',
    goodsName:'',
    integral:'',

}
  public memberId:any;
  public orgId:any;
  public isMask=false;

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.storage.get('userInfo').then((data)=>{//memberId
      this.memberId=data.id;
      this.laodIntegral();
      this.judgeSign();
      this.loadGoods();
    })

  }

  /**
   *  加载积分
   */
  laodIntegral(){
    let data:any={
      memberId:this.memberId,
    }
    this.apiSev.api("post", "app/integraltradingdetail/getmodel", (res) => {
      if (res.response == 'success') {
        this.mall.integeral=res.integeral.integralTotal;
      } else {
        //this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  /**
   * 判断是否已经签到
   */
  judgeSign(){
    let data:any={
      memberId:this.memberId,
    }
    this.apiSev.api("post", "app/usersign/getmodel", (res) => {
      if (res.response == 'success') {
        this.mall.sign='已签到';
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  /*
  * 签到
  * */
  sign(){
    let data:any={
      memberId:this.memberId,
    }
    this.apiSev.api("post", "app/usersign/save", (res) => {
      if (res.response == 'success') {
        this.mall.sign='已签到';
        this.isMask=true;
        this.laodIntegral();
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  /**
   * 加载热门兑换商品
   */
  loadGoods(){
    let data:any={};
    this.apiSev.api("post", "app/goods/gethotlist", (res) => {
      if (res.response == "success") {
        let goods = res.data;
        this.malls=goods;
      }else {
        this.apiSev.itip(res.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
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
    if(this.isMask){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }
  //积分攻略
  mallIntegral(e){
    this.navCtrl.push(mallStrategyPage);
    e.preventDefault();
  }
  //积分收支
  mallDetailed(e){
    this.navCtrl.push(mallDetailedPage);
    e.preventDefault();
  }
  //兑换记录
  mallexchange(e){
    this.navCtrl.push(mallExchangeNotePage);
    e.preventDefault();
  }
  //收货地址
  mallPlace(e){
    this.navCtrl.push(mallPlacePage);
    e.preventDefault();
  }
  //全部商品
  mallAll(e){
    this.navCtrl.push(mallListPage);
    e.preventDefault();
  }
  //商品、详情
  details(id){
    let flag = this.params.get("FLAG");
    this.navCtrl.push(mallExchangeDetailedPage,{
      HOTGOODSID:id,
      FLAG:flag
    });
  }
  back(e){
    let flag = this.params.get("FLAG");//判断从哪个页面进来的
    if(flag == 1){
      this.navCtrl.push(TabsPage,{INDEX:0});
    }else if(flag == 2){
      this.navCtrl.push(TabsPage,{INDEX:4});
    }else if(flag == 3){//贴现方
      this.navCtrl.push(TabsPageB,{INDEX:0});
    }else if(flag == 4){//贴现方
      this.navCtrl.push(TabsPageB,{INDEX:4});
    }
    e.preventDefault();
  }
}
