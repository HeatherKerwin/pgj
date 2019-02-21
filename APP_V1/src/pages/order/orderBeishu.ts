import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderPage } from '../order/order';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { InAppBrowser } from '@ionic-native/in-app-browser';

@Component({
  selector: 'page-orderBeishu',
  templateUrl: 'orderBeishu.html'
})
export class OrderBeishuPage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = false;
  public maskDiv1=false; //弹出层
  public QXcash:boolean= false;

  public item:any = {};
  public itemPinfen:any = {};
  public itemComapny:any = {};

  public maskDiv=false; //弹出层
  public memberophone='';

  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public mobile = "";
  public info = "0";
  public memo = "";
  public isCancel=false;
  public isConfirm=false;
  public chgemoney = 0;
  public exmoney = "0";
  public isMemo=false;
  public code = "";
  public isMask:boolean;
  public isCash:boolean;
  public outputemail='';


  constructor(public storage: Storage,public iab: InAppBrowser,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      this.item=this.params.get('item');
      console.log(this.item);
  }

  bigshow(){
	  this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.endorse_pic_path, '背书截图', {share:false});
  }

  bigpic(){
    if (this.item.picpath!=null) {
      if(this.item.type1=='2'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'票号：'+this.item.draft_no+' | 备注：'+this.item.memberother, {share:false});
      }else if(this.item.type1=='1'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'备注：'+this.item.memberother, {share:false});
      }
    }else{
      this.apiSev.itip('未上传票面');
    }
  }

/*  getCode(){
	let data={
		mobile: this.mobile,
		type : 'BEISHU'
	}
    this.apiSev.api("newpost", "/send/sms", (res) => {


        if(res.response == 'success'){
          let second = 60;
          let timePromise5 = setInterval(()=>{

            if(second<=0){
              clearInterval(timePromise5);
              timePromise5 = undefined;

              second = 60;
              this.btnText = "重发验证码";
              this.isCodeDisabled = false;
            }else{
              second--;
              this.btnText = second + "秒后可重发";
              this.isCodeDisabled = true;
            }
          },1000,100);
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {},1,data
    );
  }*/


  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
      this.maskDiv1 = false;
      this.isMask = false;
      this.isCash = false;
      this.QXcash = false;
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

  closeMask1(){
    this.maskDiv1 = false;
  }

  Save(){
	    let data={
			id:this.item.dist_id,
			state:this.item.state ,
			//code:this.code
		};

		let url= "" ;
		if (this.item.order_type=="DISCOUNTRECORD"){
			url= "distributeorder/update/confirmendorsetime" ;
		}
		else{
			url= "distributeordersp/update/confirmendorsetime" ;
		}

		this.apiSev.api("newpost", url , (res) => {
			  if(res.data.response == 'success') {

			     if (this.item.type1=='1'){
					 this.apiSev.itip('确认签收成功');
					 this.navCtrl.pop();
				 }else{
					 if(res.data.data.return_code == '99'&& res.data.data.trans_status!='F') {
						 if(res.data.data.biz_order.applications[0].endorse_status == 'S') {
							 this.apiSev.itip('确认签收成功');
							 this.navCtrl.pop();
						 } else if (res.data.data.biz_order.applications[0].endorse_status == 'F'){
							 this.apiSev.itip('票据还未在网银上签收，请在网银实际签收后再到这里确认签收。');
						 } else {
							 this.apiSev.itip('已提交等待审核。'+res.data.data.biz_order.applications[0].endorse_desc);
						 }
					 } else{
						 this.apiSev.itip(res.data.data.error_msg+"，请联系客服！");
					 }
				 }

			  }else{
          //this.apiSev.itip('请输入正确的验证码');
          this.apiSev.itip(res.data.msg);
			  }
		}, (error) => {},6100,data);

  }
  Cancel(){
	  this.maskDiv = true;

  }


  Chage(){
    if (this.info=="4")
	  this.isMemo=true;
    else
	  this.isMemo=false;
  }


  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }
  okMask(){
    this.maskDiv = false;
  }
  ionViewDidEnter(){

	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];

	this.storage.get('userInfo').then((res)=>{

		this.memberId=res.id;
        this.mobile=res.mobile;
		this.initData();

	})

  }


  close(){

  }

  initData() {

	let data={
		role:1,
		memberId:this.item.bns_member_id
	}

    this.apiSev.api("newpost", "comments/get", (res) => {
      if(res.data.response == 'success') {
        this.itemPinfen=res.data.data;
        let min = Math.floor(this.itemPinfen.endorseTime / 60);//计算整数分
        let afterMin = this.itemPinfen.endorseTime - min * 60;//取得算出分后剩余的秒数
        this.itemPinfen.endorseTime = this.apiSev.PrefixInteger(min, 2) + ':' + this.apiSev.PrefixInteger(afterMin, 2);
      }
    }, (error) => {},2000,data);

	//获得出票企业信息
/*	let data1={
		memberId:this.item.bns_member_id,
		type:0
	}

    this.apiSev.api("newpost", "orginfo/get", (res) => {
      if(res.data.response == 'success') {
        this.itemComapny=res.data.data.orgInfo;
        this.memberophone=res.data.data.member.mobile;

      }
    }, (error) => {},2000,data1);*/


      let url:any;
    	let data1={
        id:this.item.dist_id,
        };
    	  if(this.item.order_type=="DISCOUNTRECORD"){
    	    url='distributeorder/get/details';
        }else {
          url='distributeordersp/get/details';
        }
        this.apiSev.api("newpost", url, (res) => {
          if(res.data.response == 'success') {
            this.itemComapny=res.data.data;
            this.memberophone=res.data.data.member.mobile;
            if(res.data.data.orgInfo!=null&&res.data.data.orgInfo!=''&&res.data.data.orgInfo!='undefined'){
              this.memberophone=res.data.data.orgInfo.phone;
              this.itemComapny=res.data.data.orgInfo;
            }
          }
        }, (error) => {},500,data1);

  }

  //确认取消按钮
  showConfirm1(){
    this.maskDiv1 = true;
    this.QXcash = true;
    this.isCash = false;
  }

  //确认取消按钮
  CancelMask1(){
      let data: any={

      };
      if (this.item.cib_cancel != null && this.item.cib_cancel != '') {
        if (this.item.cib_cancel.id != null && this.item.cib_cancel.id != '') {
          data.id = this.item.cib_cancel.id;
        }else {
          data.memberId = this.memberId;
          data.orderId = this.item.id;
          data.orderType = this.item.order_type;
          data.type = 1;
          data.cancel = this.info;
          data.cancelCause = this.memo
        }
      } else {
        data.memberId = this.memberId;
        data.orderId = this.item.id;
        data.orderType = this.item.order_type;
        data.type = 1;
        data.cancel = this.info;
        data.cancelCause = this.memo
      }
      let objs=[];

      this.apiSev.api("newpost", "cibcancel/save", (res) => {

        if(res.data.response == 'success') {
          this.apiSev.itip("已成功取消订单");
          this.navCtrl.pop();
        }
      }, (error) => {},6100,data);
    this.maskDiv1 = false;
  }

  getDurg (test) {
    //artime.push(timePromise);
  }

  outputHT(){
    //合同,如果有京东ID走url，不是京东走兴业的邮箱发送
    if(this.item.dist_bind_id!=null&&this.item.dist_bind_id!=''&&this.item.dist_bind_id!='undifined'){
      let data:any={
        orderId:this.item.dist_id,
        orderType:this.item.order_type,
      };
      //类型企业前2中，资方后两种（DISCOUNTRECORD、DISCOUNTRECORDSP、DISTRIBUTEORDER、DISTRIBUTEORDERSP）
      if(this.item.order_type=='DISCOUNTRECORD'){
        data.orderType='DISTRIBUTEORDER';
      }else {
        data.orderType='DISTRIBUTEORDERSP';
      }
      this.apiSev.api("newpost", "order/sceneevi/url", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.iab.create(res.data.data.url, '_system');
        } else {
          this.apiSev.itip(data.msg);
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);

    }else {
      this.isCash = true;
      this.maskDiv1 = true;
      this.QXcash = false;
    }
  }

  //邮箱发送
  cash(){
    if(this.outputemail == null || this.outputemail== ''){
      this.apiSev.itip("请输入您的邮箱");
      return;
    }else {
      let email = this.outputemail.match(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/);
      if (email) {
      } else {
        this.apiSev.itip("输入的邮箱输入格式有误");
        return;
      }
    }
    let data:any={
      no:this.item.ordernumber,
      email:this.outputemail,
    };
    this.apiSev.api("newpost", "/cib/econtract/email", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.maskDiv1 = false;
        this.isCash = false;
        this.apiSev.itip(data.msg);
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

}
