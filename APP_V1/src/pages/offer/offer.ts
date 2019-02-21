import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { BigDianPage} from "./yin/bigDian";
import {SmallDianPage} from "./yin/smallDian";
import {SmallZhiPage} from "./yin/smallZhi";
import {DianPage} from "./shang/dian";
import {ZhiPage} from "./shang/zhi";
import {MyOfferPage} from "./myOffer";
import { StatementPage } from "../openaccount/statement";
import { openaccountDPage } from "../openaccount/openaccountD";
import { OpenaccountFPage } from "../openaccount/openaccountF";
import {OpenaccountEPage} from "../openaccount/openaccountE";
import {BindingPage} from "../openaccount/binding";
import {BindingCPage} from "../openaccount/bindingC";
import {HomePage} from "../home/home"


@Component({
  selector: 'page-offer',
  templateUrl: 'offer.html'
})
export class OfferPage {

  //切换
  segmentsArray = ['yin','shang'];
  pet: string = this.segmentsArray[0];
  public maskDiv=false; //弹出层
  public Authentication=false; //开户
  public memberId:any;
  public active:any={};
  public cib:any={};
  public orgId:any;

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService) {
  }

  //每次进入页面都加载
  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.Toactive();  //开户判断
      this.NewMessage();
      this.surplus();
    });
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
  }

  //大电银报价页面
  ToOffer1Page(){
    this.navCtrl.push(BigDianPage)
  }

  ToSmallDianPage(){
    this.navCtrl.push(SmallDianPage);
  }

  ToSmallZhiPage(){
    this.navCtrl.push(SmallZhiPage);
  }

  ToDianPage(){
    this.navCtrl.push(DianPage);
  }

  ToZhiPage(){
    this.navCtrl.push(ZhiPage);
  }

  ToMyOfferPage(){
    this.navCtrl.push(MyOfferPage)
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = true;
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
  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
    this.Authentication = false;
  }

  //开户判断
  Toactive() {

    let data: any = {
      memberId: this.memberId,
      type: 1,
    };
    this.apiSev.api("newpost", "orginfo/rz/", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.active = data.data;
        this.cib = data.data.cib;
        if(data.data.cib.name!= null && data.data.cib.name != '') {
          this.cib.name = data.data.cib.name;
        }
        if (this.active.info_state != 'PASS' && this.cib.status != 2){
          this.maskDiv = true;
          this.Authentication = true;
        }
      } else {
      }
    }, (error) => {
      this.navCtrl.setRoot(HomePage);
      return;
    }, 500, data);
  }

//去填写
  ToAuthentication() {
    if (this.cib.name != null && this.cib.name != '') {
      if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 == null) {
        this.navCtrl.push(OpenaccountEPage, {
          ORG_TYPE: 1,
        });
      }
      if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && (this.cib.status == 1 || this.cib.status == 0)) {
        this.navCtrl.push(OpenaccountEPage, {
          ORG_TYPE: 1,
        });
      }
      if (this.active.info_state == 'PASS' && this.cib.bizLicenceRegisteredNo != null && this.cib.status == 2 && this.apiSev.pass == '') {
        this.navCtrl.push(BindingCPage, {
          ORG_TYPE: 1,
          ME_TYPE: 1
        });
      }
      if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && (this.cib.status == 5 || this.cib.status == 6)) {
        this.navCtrl.push(OpenaccountFPage, {
          ORG_TYPE: 1,
        });
      }
      if (this.active.info_state == 'PENDING' || this.active.info_state == 'NOPASS') {
        this.navCtrl.push(BindingPage, {
          ORG_TYPE: 1,
        });
      }
    } else if (this.active.info_state == 'NONE') {
      this.navCtrl.push(StatementPage, {
        ORG_TYPE: 1,
        ME_TYPE: 1
      });
    }
  }

  //tab红点
  surplus(){
    if(this.memberId!=null && this.memberId!='') {
      let data: any = {
        orgId: this.memberId,
      };
      this.apiSev.api("newpost", "dispatch/get/count", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data.count >= 1) {
            this.apiSev.Singular = data.data.count;
          } else if (data.data.count == 0) {
            this.apiSev.Singular = '';
          }
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
    }
  }
  //是否有新消息
  NewMessage(){
    let data ={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "systeminfo/get/unread", (res) => {
      let num = res.data.data.num;
      if(num>0){
        this.apiSev.systeminfo = num;
      }else{
        this.apiSev.systeminfo = '';
      }
    },(error) => {

    }, 500,data);
  }
}
