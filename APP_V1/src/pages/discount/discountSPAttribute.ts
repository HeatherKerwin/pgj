import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { DiscountSPOcrPage } from "./discountSPOcr";
import { DiscountSPPage } from "./discountSP";
import {StatementBPage} from '../openaccount/statementB';

import { OpenaccountFPage } from "../openaccount/openaccountF";
import {OpenaccountEPage} from "../openaccount/openaccountE";
import {BindingBPage} from "../openaccount/bindingB";
import {BindingCPage} from "../openaccount/bindingC";
import {HomePageB} from "../home/homeB";
import {JdinfoPage} from "../jd/jdinfo";
import {jdLicense3Page} from "../jd/jdLicense3";
import {jdlicense4Page} from "../jd/jdLicense4";
import {JdSuccessPage} from "../jd/jdSuccess";
import {jdbindingBPage} from "../jd/jdbindingB";

@Component({
  selector: 'page-discountSPAttribute',
  templateUrl: 'discountSPAttribute.html'
})


export class DiscountSPAttributePage {

  public note={//顶部提示
    alert:'',
    content:''
  };

  public maskDiv=false; //弹出层
  public Authentication=false; //开户
  public active:any={
  };
  public cib:any={};
  public memberId:any;
  public orderinventory:any={}; //票据清单

  public jdData:any={};



  constructor(public storage: Storage,public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.orderinventory = this.params.get("ORDERINVENTORY");
    this.topBar();//初始化提示语
  }


  ionViewDidEnter() {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.Toactive();  //开户判断
      this.surplus();
    });
  }

  // 初始化提醒
  topBar() {
    this.storage.get('userInfo').then((data) => {
      this.apiSev.api("get", "app/topbar/?belong=0&memberid=" + data.id, (res) => {
        this.note=res;
      },(error) => {}, 500,{});
    })
  }

  dianpiao(type1){
    let data:any={
      memberId:this.memberId,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.cib!=null&&data.data.cib!=''&&data.data.cib!='undefined'){
          //是否兴业开户
          if(data.data.cib.status==2&&data.data.cib.cib_check_state=='PASS'){
            this.navCtrl.push(DiscountSPOcrPage,{TEMP_TYPE1:type1});
          }else {
            //兴业绑定开户判断
            this.maskDiv = true;
            this.Authentication = true;
          }
        }else if(data.data.jdjr!=null&&data.data.jdjr!=''&&data.data.jdjr!='undefined'){
          this.jdData=data.data.jdjr;
          //是否开京东户
          if(this.jdData.status==2&&this.jdData.check_state=='PASS'){
            this.navCtrl.push(DiscountSPOcrPage,{TEMP_TYPE1:type1});
          }else {
            //京东绑定开户判断
            this.maskDiv = true;
            this.Authentication = true;
          }
        } else {
          //京东兴业都没开户
          this.maskDiv = true;
          this.Authentication = true;
        }
        //未开户-去往开户页面
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.navCtrl.setRoot(HomePageB);
      return;
    }, 500, data);

  }

  zhipiao(type1){
    let data:any={
      memberId:this.memberId,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.cib!=null&&data.data.cib!=''&&data.data.cib!='undefined'){
          //是否兴业开户
          if(data.data.cib.status==2&&data.data.cib.cib_check_state=='PASS'){
            this.navCtrl.push(DiscountSPPage,{TEMP_TYPE1:type1,ORDERINVENTORY:this.orderinventory});
          }else {
            //兴业绑定开户判断
            this.maskDiv = true;
            this.Authentication = true;
          }
        }else if(data.data.jdjr!=null&&data.data.jdjr!=''&&data.data.jdjr!='undefined'){
          this.jdData=data.data.jdjr;
          //是否开京东户
          if(this.jdData.status==2&&this.jdData.check_state=='PASS'){
            this.navCtrl.push(DiscountSPPage,{TEMP_TYPE1:type1,ORDERINVENTORY:this.orderinventory});
          }else {
            //京东绑定开户判断
            this.maskDiv = true;
            this.Authentication = true;
          }
        } else {
          //京东兴业都没开户
          this.maskDiv = true;
          this.Authentication = true;
        }
        //未开户-去往开户页面
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.navCtrl.setRoot(HomePageB);
      return;
    }, 500, data);


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
    let data:any={
      memberId:this.memberId,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.cib!=null&&data.data.cib!=''&&data.data.cib!='undefined'){
          //是否兴业开户
          if(data.data.cib.status==2&&data.data.cib.cib_check_state=='PASS'){

          }else {
            //兴业绑定开户判断
            this.maskDiv = true;
            this.Authentication = true;
          }
        }else if(data.data.jdjr!=null&&data.data.jdjr!=''&&data.data.jdjr!='undefined'){
          this.jdData=data.data.jdjr;
          //是否开京东户
          if(this.jdData.status==2&&this.jdData.check_state=='PASS'){

          }else {
            //京东绑定开户判断
            this.maskDiv = true;
            this.Authentication = true;
          }
        } else {
          //京东兴业都没开户
          this.maskDiv = true;
          this.Authentication = true;
        }
        //未开户-去往开户页面
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.navCtrl.setRoot(HomePageB);
      return;
    }, 500, data);
  }

//去填写
  ToAuthentication() {
    if(this.jdData!=null&&this.jdData!=''&&this.jdData=='undefined'){

    }else {
      this.navCtrl.push(JdinfoPage);
      return;
    }
    //待审核
    if(this.jdData.status==0||this.jdData.status==1||this.jdData.status==6){
      this.navCtrl.push(jdLicense3Page);
    }
    //待鉴定
    if(this.jdData.status==5){
      this.navCtrl.push(jdlicense4Page);
    }

    if(this.jdData.check_state=='PENDING'||this.jdData.check_state=='NOPASS') {
      this.navCtrl.push(jdbindingBPage);
    }

/*    if (this.cib.name != null && this.cib.name != '') {
      if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 == null) {
        this.navCtrl.push(OpenaccountEPage, {
          ORG_TYPE: 0,
        });
      }
      if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && (this.cib.status == 1 || this.cib.status == 0)) {
        this.navCtrl.push(OpenaccountEPage, {
          ORG_TYPE: 0,
        });
      }
      if (this.active.info_state == 'PASS' && this.cib.bizLicenceRegisteredNo != null && this.cib.status == 2 && this.apiSev.pass == '') {
        this.navCtrl.push(BindingCPage, {
          ORG_TYPE: 0,
          ME_TYPE: 0
        });
      }
      if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && (this.cib.status == 5 || this.cib.status == 6)) {
        this.navCtrl.push(OpenaccountFPage, {
          ORG_TYPE: 0,
        });
      }
      if (this.active.info_state == 'PENDING' || this.active.info_state == 'NOPASS') {
        this.navCtrl.push(BindingBPage, {
          ORG_TYPE: 0,
        });
      }
    } else if (this.active.info_state == 'NONE') {
      this.navCtrl.push(StatementBPage, {
        ORG_TYPE: 0,
        ME_TYPE: 0
      });
    }*/
  }

  //tab红点
  surplus(){
    if(this.memberId!=null && this.memberId!='') {
      let data: any = {
        memberId: this.memberId,
      };
      this.apiSev.api("newpost", "order/get/count", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          if (data.data.count >= 1) {
            this.apiSev.SingularB = data.data.count;
          } else if (data.data.count == 0) {
            this.apiSev.SingularB = '';
          }
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
    }
  }
}
