import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import {CounterPage} from '../tools/counter/counter';
import {DisclaimerPage} from '../openaccount/disclaimer';
import {noUndefined} from "@angular/compiler/src/util";

@Component({
  selector: 'page-orderEdit',
  templateUrl: 'orderEdit.html'
})
export class OrderEditPage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;

  public isMask:boolean;//弹出层
  public isCostItem:boolean;//费用分项表
  public isCostItem_jd:boolean;//京东费用分项表
  public isOffer:boolean;//确认报价

  public item:any = {};
  public itemPinfen:any = {};

  public maskDiv=false; //弹出层
  public swich= 'swi2'; //tab弹出

  public swi1:boolean=false;  //我要报价tab
  public swi2:boolean=false;   //一口价tab
  public isOfferB:boolean;  //一口价弹窗

  public discountpay:any ='';
  public discountpayB:any ='';
  public discounttxtys:any = '--';
  public Actual:any='--';
  public Annualrate:any='0.00';   //年利率

  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public swjx=0; //十万计息
  public info = "1";
  public isCancel=false;
  public isConfirm=false;
  public chgemoney:any ='' ;
  public exmoney:any = "0";
  public isMemo=false;
  public Guide:boolean;
  public guideDate:any;
  public roleTop:any;
  public roleLeft:any;
  public TradingMarket='';
  public ORGID='';
  public Newmoney:any=''; //页面传值金额

  constructor(public storage: Storage,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public modalCtrl: ModalController,public apiSev: apiService) {
      //window.clearInterval(mapPanel.drawTick);
      this.item=this.params.get('item');
    this.TradingMarket=this.params.get('TradingMarket');  //是否是交易市场

    this.exmoney=this.item.allmoney*10000;
    this.discountpay=this.item.allmoney*10000;
    this.Newmoney=this.item.allmoney*10000;

  }

  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
      this.isMask = false;
      this.isCostItem = false;
      this.isOffer = false;
      this.Guide = false;
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

  Chage(){
    let z= (this.item.allmoney*10000 - this.chgemoney)/10000 ;
	  this.exmoney=z.toFixed(4);
    this.discountpay = this.item.allmoney*10000 - this.chgemoney;
    this.discountpayB=((this.discountpay * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    //十万贴息
    this.swjx=this.chgemoney*10/this.item.allmoney

  }
  bigpic(){
	  if (this.itemPinfen.picpath!=null) {
      if(this.item.type1=='2'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'票号：'+this.item.draft_no+' | 备注：'+this.item.memberother, {share:false});
      }else if(this.item.type1=='1'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'备注：'+this.item.memberother, {share:false});
      }
    } else{
		  this.apiSev.itip('未上传票面');
	  }
  }

  /*确认报价提示*/
  SaveAlert(){
    if (this.chgemoney<=0) {
      this.apiSev.itip("请输入扣息金额");
      return;
    }
    if(Number(this.chgemoney)>=Number(this.exmoney*10000)){
      this.apiSev.itip("您输入的计息金额有误,请重新输入！");
      return;
    }
    this.isMask = true;
    this.isOffer = true;
  }

  Save() {
    if (this.chgemoney > 0) {
      let url= "" ;
      if (this.item.order_type=="DISCOUNTRECORD"){
        url= "distributeorder/update/txlx" ;
      }
      else{
        url= "distributeordersp/update/txlx" ;
      }
      let data:any={
        id:this.item.dist_id,
        txlx:this.chgemoney,
        txje:(this.discountpay/10000)
      };
      this.apiSev.api("newpost", url, (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.navCtrl.pop();
          this.apiSev.itip("修改报价成功")
        } else {
          this.apiSev.itip(data.msg);
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500, data);
    }
    else
      this.apiSev.itip("请输入扣息金额");
  }

  Call(){
    //跳转计算器，并带回值
    this.navCtrl.push(CounterPage, {
      TEMP_TYPE1: this.item.type1,
      TEMP_ALLMONEY: this.item.allmoney,
      TEMP_TYPE2: this.item.type2,
      TEMP_START: this.item.create_time,
      TEMP_DAY:this.item.jxts,
      ORDERDEATAIL: 1,
    });
  }


  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }

  ionViewDidEnter(){
	if (this.apiSev.parmData!=null){
		if (this.apiSev.parmData.Discountinterest != null && this.apiSev.parmData.Discountinterest != '') {
		  let Discountinterest = this.apiSev.parmData.Discountinterest;
		  if (Discountinterest != null && Discountinterest != '') {
			this.chgemoney = Discountinterest;
			this.apiSev.parmData.Discountinterest = '';
		  }
		}
	}

	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];

	this.storage.get('userInfo').then((res)=>{

		this.memberId=res.id;

		let date1 = new Date();//'2017-9-26 15:36:00'
		let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
		date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
		let s1 = date1.getTime(),s2 = date2.getTime();
    var afterHour = Math.floor((s2 - s1)/1000);
    var hour=Math.floor(afterHour/3600);
    var min = Math.floor((afterHour-hour*3600)/60);//计算整数分
    var afterMin = afterHour - min*60;//取得算出分后剩余的秒数
		if (afterHour<1){
			this.item.difDate = '无'
		}else{
			this.item.difDate = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
		}
    this.storage.get('ORGID').then((data)=> {
      this.ORGID = data;
      this.initData();
    })

	});
    //取计算器里的值
    this.Chage();
    //this.cibpay();
  }



  initData() {

	    setTimeout(() => {
			let date1 = new Date();
			let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
			date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();


			var afterHour = Math.floor((s2 - s1)/1000);

      document.getElementById("hdid").innerHTML = afterHour.toString();

			let timePromise = setInterval(()=>{

				if (document.getElementById("hdid")!=null)
				{
				  var minjj=parseInt(document.getElementById("hdid").innerHTML)-1;
				  document.getElementById("hdid").innerHTML= minjj.toString();
				  if(minjj<=0){
					clearInterval(timePromise);
					timePromise = undefined;

					document.getElementById("did").innerHTML = '无' ;
				  }else{

            var hour=Math.floor(minjj/3600);
            var min = Math.floor((minjj-hour*3600)/60);//计算整数分
            var afterMin = minjj - min*60;//取得算出分后剩余的秒数
					document.getElementById("did").innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
				  }
				}
				else{
					clearInterval(timePromise);
					timePromise = undefined;
				}

			},1000,1000);
			this.ListsTime1.push(timePromise);


    }, 100);


    let data:any = {
      id: this.item.id,
      order_type: this.item.order_type,
      memberId: this.item.bns_member_id,
    };

    if (this.item.org_id == null || this.item.org_id == '' || this.item.org_id == 'undefined') {
      data.orgId = this.ORGID
    } else {
      data.orgId = this.item.org_id
    }

    this.apiSev.api("newpost", "dispatch/show/dist/price", (res) => {
      if(res.data.response == 'success') {
        this.itemPinfen=res.data.data;
        let min = Math.floor(this.itemPinfen.endorseTime / 60);//计算整数分
        let afterMin = this.itemPinfen.endorseTime - min * 60;//取得算出分后剩余的秒数
        this.itemPinfen.endorseTime = this.apiSev.PrefixInteger(min, 2) + ':' + this.apiSev.PrefixInteger(afterMin, 2);

      }
    }, (error) => {},2000,data);


  }



  getDurg (test) {
    //artime.push(timePromise);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  Agreement(){
    this.navCtrl.push(DisclaimerPage,{
      CONTRACT:"commitment",
    });
  }

  //兴业数金鉴证服务费弹窗
  payservice(){
    this.isCostItem = true;
    this.isMask = true;
  }

  //兴业数金鉴证服务费弹窗
  payservice_jd(){
    this.isCostItem_jd = true;
    this.isMask = true;
  }

  //实际支付总金额调用
  cibpay(){
    //传值贴现票款
    let data:any={
      money:this.Newmoney,
      txje:this.discountpay,
      jxts:this.item.jxts
    };
    if(this.item.dist_bind_id!=''&&this.item.dist_bind_id!='undefined'&&this.item.dist_bind_id!=null){
      data.bindId=this.itemPinfen.disc_bind_id;
      data.draftNo=this.itemPinfen.draft_no;
    }

    this.apiSev.api("newpost", "dispatch/fee", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let discounttxty:any=0;
        if(this.item.dist_bind_id!=''&&this.item.dist_bind_id!='undefined'&&this.item.dist_bind_id!=null){
          discounttxty = data.data.fee;
        }else {
          discounttxty = data.data.fee;
        }
        if (this.item.type1 == '2') {
          this.discounttxtys = ((discounttxty * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
          this.Actual = ((this.discountpay * 1 + discounttxty * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }else if (this.item.type1 == '1'){
          this.Actual = ((this.discountpay * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
        if(data.data.price!=null&&data.data.price!=''&&data.data.price!='undefined'){
          this.Annualrate=data.data.price;
        }
        if(data.data.price2!=null&&data.data.price2!=''&&data.data.price2!='undefined'){
          this.swjx=data.data.price2;
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
