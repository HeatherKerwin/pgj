import { Component } from '@angular/core';
import { NavController,ModalController,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Bonus } from "../me/bonus";
import { MeMessagePage } from "../me/meMessage";
import { SettingPage } from "../me/setting";
import { ReferrerPage } from '../me/referrer/referrer';
import { MyGradeBnsPage } from "./myGrade/myGradeBns";
import {TransactionPage} from "../account/transaction";
import { Login } from '../login/login';
import {BindPage} from "./bind";
import {MyInquiryReplyPage} from "./myInquiryReply/myInquiryReply";
import { mallPage } from '../mall/mall';
import {OrderListPageB} from "../order/orderListB";
import {DiscountPlaceListPage} from "../discount/discountPlaceList";
import { Selrole } from '../login/selrole';
import {Camera,CameraOptions} from "@ionic-native/camera";
import { StatementBPage } from "../openaccount/statementB";
import { openaccountDPage } from "../openaccount/openaccountD";
import { OpenaccountFPage } from "../openaccount/openaccountF";
import {OpenaccountEPage} from "../openaccount/openaccountE";
import {OpenaccountSuccessPage} from "../openaccount/openaccountSuccess";
import {BindingBPage} from "../openaccount/bindingB";
import {BindingCPage} from "../openaccount/bindingC";
import { MyredgadPage } from "../account/myredgad";
import { VipaggregatePage } from "../account/vipaggregate";
import { RookieredPage } from "../account/Rookiered";
import {JdinfoPage} from "../jd/jdinfo";
import {jdLicense3Page} from "../jd/jdLicense3";
import {jdlicense4Page} from "../jd/jdLicense4";
import {JdSuccessPage} from "../jd/jdSuccess";
import {jdbindingBPage} from "../jd/jdbindingB";


@Component({
  selector: 'page-meB',
  templateUrl: 'meB.html'
})
export class MePageB {
  public memberId: any;
  public cib:any={
  };
  public active:any={
  };
  public enDat='';

  public IsNewMsg = false;
  public isShowPass = false;
  public myInfo:any = {};
  public myShowInfo:any = {};
  public myLevel = '';
  public newInfo:any = {};

  public mobile;
  public authState = '未开户';//认证状态
  public maskDiv = false;
  public pic = "assets/images/me/touxiang.png";
  public picStr = "";
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public loadingCtrl: LoadingController,public camera: Camera,public modalCtrl:ModalController) {
  }

  order(){
    this.navCtrl.push(OrderListPageB);
  }

  ionViewDidEnter(){
    this.storage.get('lgtoken').then((lgtoken)=>{
      if (lgtoken === null || lgtoken === undefined || lgtoken === ''){
        let profileModal = this.modalCtrl.create(Login);
        profileModal.present();
      }
      else{
        this.storage.get('userInfo').then((data)=>{
          this.memberId=data.id;
        })
      }
    });
    this.storage.get('userInfo').then((data) => {
      if(data.headpic!=null){
        this.pic = this.apiSev.getOrderImgUrl + data.headpic;
      }else{
        this.pic = "assets/images/me/touxiang.png";
      }
      this.mobile = data.mobile;
      this.memberId = data.id;
      this.NewMessage();
      this.check();
      this.surplus();
      this.viploadData();
    });
  }

  juese(){
    let profileModal = this.modalCtrl.create(Selrole);
    profileModal.present();
  }

  //贴现地址
  DiscountPlace(){
    this.navCtrl.push(DiscountPlaceListPage);
  }
  //押金管理
  ToTransactionPage(){
    this.navCtrl.push(TransactionPage,{
      ORG_TYPE:0
    });
  }
  //积分商城
  ToMall(){
    this.navCtrl.push(mallPage,{FLAG:4})
  }
  //开户信息
  StAtementBPage(){
    let data:any={
      memberId:this.memberId,
      type:0,
    };
    this.apiSev.api("newpost", "orginfo/rz/", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.active = data.data;
        if (data.data.cib.name != null && data.data.cib.name != '') {
          this.cib = data.data.cib;
          if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 == null) {
            this.navCtrl.push(OpenaccountEPage, {
              ORG_TYPE: 0,
              ME_TYPE: 0
            });
          }
          if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && (this.cib.status == 1 || this.cib.status == 0)){
            this.navCtrl.push(OpenaccountEPage, {
              ORG_TYPE: 0,
              ME_TYPE: 0
            });
          }
          if ((this.active.info_state == 'PASS' && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && this.cib.status == 2)||(this.active.info_state == 'PASS' && this.cib.bizLicenceRegisteredNo != '' && this.cib.status == 2 && this.apiSev.pass =='0')) {
            this.navCtrl.push(OpenaccountSuccessPage, {
              ORG_TYPE: 0,
              ME_TYPE: 0
            });
          }
          if (this.active.info_state == 'PASS' && this.cib.bizLicenceRegisteredNo != null && this.cib.status == 2 && this.apiSev.pass =='') {
            this.navCtrl.push(BindingCPage, {
              ORG_TYPE: 0,
              ME_TYPE: 0
            });
          }
          if ((this.active.info_state == 'NONE'||this.active.info_state == 'PASS') && this.cib.bizLicenceRegisteredNo != null && this.cib.imgPath20 != null && (this.cib.status == 5 || this.cib.status == 6)) {
            this.navCtrl.push(OpenaccountFPage, {
              ORG_TYPE: 0,
              ME_TYPE: 0
            });
          }
          if(this.active.info_state== 'PENDING'|| this.active.info_state== 'NOPASS'){
            this.navCtrl.push(BindingBPage, {
              ORG_TYPE: 0,
              ME_TYPE: 0
            });
          }
        }else if(this.active.info_state== 'NONE' || this.active.info_state== 'NOPASS'){
          this.navCtrl.push(StatementBPage, {
            ORG_TYPE: 0,
            ME_TYPE: 0
          });
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  openNavPage(item) {
    if (item=='bonus')
    	this.navCtrl.push(Bonus,{});
  }

  ToMeMessagePage(e) {
    this.navCtrl.push(MeMessagePage);
    e.preventDefault();
  }
  ToSettingPage(e){
    this.navCtrl.push(SettingPage);
	  e.preventDefault();
  }
  ToReferrerPage(){
    this.navCtrl.push(ReferrerPage);
  }

  ToBindPage(){
    this.navCtrl.push(BindPage);
  }

  ToMyGradePage(){
    this.navCtrl.push(MyGradeBnsPage,{"ROLE":1});
  }

  ToMyInquiryPage(){
    this.navCtrl.push(MyInquiryReplyPage,{
      ORG_TYPE:0  //企业 出票方
    });
  }

  //调取相机上传
  cameraPic(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 1,
      targetWidth: 1280,
      targetHeight: 1280,
      saveToPhotoAlbum: false,
      correctOrientation:true
    }
    this.findPic(options);
    this.closeMask();
  }
  //点取相册上传
  albumPic(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 0,
      targetWidth: 1280,
      targetHeight: 1280
    }
    this.findPic(options);
    this.closeMask();
  }
  //上传图片
  findPic (options) {
    let loading = this.loadingCtrl.create({
      //content: '请等待',
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url=  imageData; //'data:image/jpeg;base64,' +
      let data= {
        base64Image:url
      }
      //this.pic=url;
      this.apiSev.api("newpost", "upload/image", (res) => {
        if (res.data.response == 'success') {
          this.pic= this.apiSev.getOrderImgUrl+res.data.data.base64Image;//this.apiSev.getImgUrl+data.data; //展示的图片
          this.picStr= res.data.data.base64Image;
          let data1 = {
            headpic:this.picStr,
            memberId:this.memberId,
          }
          this.apiSev.api("newpost", "member/save/headpic", (res) => {
            this.storage.get('userInfo').then((data) => {
              data.headpic = res.data.data.headpic;
              this.storage.set('userInfo',data);
            });
			loading.dismiss();
            this.apiSev.itip("修改成功");
          },(error) => {
			loading.dismiss();
            this.apiSev.itip('操作失败');
          }, 500,data1);
        }
		else{
			loading.dismiss();
		}

      },(error) => {
		loading.dismiss();
        this.apiSev.itip('操作失败！'+JSON.stringify(error));
      }, 500,data);

    }, (err) => {
	  loading.dismiss();
      // Handle error
    });

  }


  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
    let elements = document.querySelectorAll(".tabbar");
    if(elements != null) {
      Object.keys(elements).map((key) => {
        elements[key].style.display ='flex';
      });
    }
  }

  changeTouXiang(e){
    this.maskDiv = true;
    let elements = document.querySelectorAll(".tabbar");
    if(elements != null) {
      Object.keys(elements).map((key) => {
        elements[key].style.display ='none';
      });
    }
    e.preventDefault();
  }

  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
    let elements = document.querySelectorAll(".tabbar");
    if(elements != null) {
      Object.keys(elements).map((key) => {
        elements[key].style.display ='flex';
      });
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

  /**
   * 检查是否已认证
   */
  check() {
    let data:any={
      memberId:this.memberId,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.cib != null && data.data.cib != '' && data.data.cib != 'undefined') {
          //是否兴业开户
          if (data.data.cib.status == 2 && data.data.cib.cib_check_state == 'PASS') {
            this.authState='已开户';
          }
        } else if (data.data.jdjr != null && data.data.jdjr != '' && data.data.jdjr != 'undefined') {
          if (data.data.jdjr.status == 2 && data.data.jdjr.check_state == 'PASS') {
            this.authState='已开户';
          }
        }
      }
    }, (error) => {
    }, 500, data);
  }

  //是否有新消息
  NewMessage(){
    let data ={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "systeminfo/get/unread", (res) => {
      let num = res.data.data.num;
      if(num>0){
        this.IsNewMsg = true;
        this.apiSev.systeminfo=num;
      }else{
        this.IsNewMsg = false;
        this.apiSev.systeminfo='';
      }
    },(error) => {

    }, 500,data);
  }

  //接单红点
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

        return;
      }, 500, data);
    }
  }

  //我的红包
  Myredgad(){
    let data:any={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "coupon/register/init", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(RookieredPage,{
          ORG_TYPE:0  //企业 出票方
        });
      } else {
        this.navCtrl.push(MyredgadPage,{
          ORG_TYPE:0  //企业 出票方
        });
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //会员中心
  vipgate(){
    this.navCtrl.push(VipaggregatePage,{
      ORG_TYPE:0  //企业 出票方
    });
  }
  //获取用户购买的会员
  viploadData(){
    let data:any={
      memberId:this.memberId,
      vipType:'0'
    };
    this.apiSev.api("newpost", "vipmember/get/by/memberid", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.vipMember!=null&&data.data.vipMember!=''){
          this.enDat=this.apiSev.transformdateC(data.data.vipMember.endDate)+ '  到期';
        }else {
          this.enDat='';
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //京东是否开户
  jdjr(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        //未开户-去往开户页面
        if(data.data == null || data.data == '' || data.data == 'undefined'){
          this.jdjrbind();
          return;
        }
        if (data.data!= null && data.data != '' && data.data != 'undefined') {
          //待审核
          if (data.data.status == 0||data.data.status == 1||data.data.status == 6) {
            this.navCtrl.push(jdLicense3Page);
            return;
          }
          //正常
          if (data.data.status == 2) {
            this.navCtrl.push(JdSuccessPage);
            return;
          }
          if (data.data.status == 5) {
            this.navCtrl.push(jdlicense4Page);
            return;
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //京东是否绑定
  jdjrbind(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjrbind/get/jdjr/jdjrbind", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.jdjrBind== null || data.data.jdjrBind == '' || data.data.jdjrBind == 'undefined'){
          this.navCtrl.push(JdinfoPage);
          return;
        }
        if(data.data.jdjrBind.checkState=='PENDING'||data.data.jdjrBind.checkState=='NOPASS'){
          this.navCtrl.push(jdbindingBPage);
          return;
        }
        if(data.data.jdjrBind.checkState=='PASS'){
          this.navCtrl.push(JdSuccessPage);
          return;
        }

      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

}
