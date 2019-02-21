import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { baojiaPage } from '../order/baojia';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import {CounterPage} from '../tools/counter/counter';
import {DisclaimerPage} from '../openaccount/disclaimer';
import {noUndefined} from "@angular/compiler/src/util";
@Component({
  selector: 'page-orderDetail',
  templateUrl: 'orderDetail.html'
})
export class OrderDetailPage {
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

  public Choice:any;  //1为兴业0为京东

  constructor(public storage: Storage,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public modalCtrl: ModalController,public apiSev: apiService) {
      //window.clearInterval(mapPanel.drawTick);
      this.item=this.params.get('item');
      if(this.item.buyout_price!=null&&this.item.buyout_price!=''&&this.item.buyout_price!='undefined'){
        this.swi1=false;  //我要报价tab
        this.swi2=true;   //一口价tab
      }else {
        this.swi1=true;  //我要报价tab
        this.swi2=false;   //一口价tab
      }
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
      this.isCostItem_jd=false;
      this.isOffer = false;
      this.Guide = false;
      this.isOfferB=false;
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
	  this.exmoney=z.toFixed(6);
    this.discountpay = this.item.allmoney*10000 - this.chgemoney;
    this.discountpayB=((this.discountpay * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    //十万贴息
    //this.swjx=this.chgemoney*10/this.item.allmoney

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
    if(this.item.buyout_price!=null&&this.item.buyout_price!=''&&this.item.buyout_price!='undefined'){
      if(this.swich == 'swi1'){
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
        this.isOfferB = false;
      }else {
        this.item.buyout_priceB=((this.item.buyout_price * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        console.log(this.item.buyout_priceB);
        this.isMask = true;
        this.isOfferB = true;
        this.isOffer = false;
      }
    }else {
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
  }

  Save() {
    if(this.item.buyout_price!=null&&this.item.buyout_price!=''&&this.item.buyout_price!='undefined'&&this.swich=='swi2'){
      this.itemPinfen.type1 = this.item.type1;
      this.itemPinfen.txje = this.exmoney;
      this.itemPinfen.txlx = this.chgemoney;
      this.itemPinfen.place = this.item.place;
      this.itemPinfen.need_todoor = this.item.need_todoor;
      this.itemPinfen.order_type = this.item.order_type;
      this.navCtrl.push(baojiaPage, {
        itemPinfen: this.itemPinfen,
        discountpay: this.item.buyout_price,
        chgemoney: (this.item.allmoney*10000-this.item.buyout_price),
        TradingMarket: this.TradingMarket,
        OnePrice:'true',
        Choice:this.Choice,
      });
    }else {
      if (this.chgemoney > 0) {
      this.itemPinfen.type1 = this.item.type1;
      this.itemPinfen.txje = this.exmoney;
      this.itemPinfen.txlx = this.chgemoney;
      this.itemPinfen.place = this.item.place;
      this.itemPinfen.need_todoor = this.item.need_todoor;
      this.itemPinfen.order_type = this.item.order_type;
      this.navCtrl.push(baojiaPage, {
        itemPinfen: this.itemPinfen,
        discountpay: this.discountpay,
        chgemoney: this.chgemoney,
        TradingMarket: this.TradingMarket,
        Choice:this.Choice,
      });
    }
    else
      this.apiSev.itip("请输入扣息金额");
    }
  }

  Call(){
    //跳转计算器，并带回值
    this.navCtrl.push(CounterPage, {
      TEMP_TYPE1: this.item.type1,
      TEMP_ALLMONEY: this.item.allmoney,
      TEMP_TYPE2: this.item.type2,
      TEMP_START:new Date(this.item.create_time).getFullYear()+ "-" + ("0"+(new Date(this.item.create_time).getMonth() + 1)).slice(-2) + "-" +new Date(this.item.create_time).getDate(),

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
    if(this.chgemoney!=null&&this.chgemoney!=''&&this.chgemoney!='undefined'&&this.chgemoney>0){
      if(this.item.buyout_price!=null&&this.item.buyout_price!=''&&this.item.buyout_price!='undefined'){
        this.Newcibpay();
      }else {
        this.cibpay();
      }
    }else {
      this.imgGuide();
    }

  }



  initData() {

	    setTimeout(() => {
	    if(this.TradingMarket!='true'){
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

        }
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
        if(this.item.type1=='2'&&this.apiSev.jdrz==1&&this.apiSev.cibrz==1&&this.itemPinfen.disc_v_acct_acct_no!=''&&this.itemPinfen.disc_v_acct_acct_no!='undefined'&&this.itemPinfen.disc_v_acct_acct_no!=null&&this.itemPinfen.disc_bind_id!=''&&this.itemPinfen.disc_bind_id!='undefined'&&this.itemPinfen.disc_bind_id!=null){
          this.Choice=0;
        }
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
    if((this.apiSev.cibrz==1&&this.apiSev.jdrz==0&&this.itemPinfen.disc_v_acct_acct_no!=''&&this.itemPinfen.disc_v_acct_acct_no!='undefined'&&this.itemPinfen.disc_v_acct_acct_no!=null)||(this.apiSev.cibrz==1&&this.apiSev.jdrz==1&&this.Choice==1&&this.itemPinfen.disc_v_acct_acct_no!=''&&this.itemPinfen.disc_v_acct_acct_no!='undefined'&&this.itemPinfen.disc_v_acct_acct_no!=null)){

    }else {
      data.bindId=this.itemPinfen.disc_bind_id;
      data.draftNo=this.itemPinfen.draft_no;
    }
    this.apiSev.api("newpost", "dispatch/fee", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let discounttxty:any=0;
        if(this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undefined'&&this.item.disc_bind_id!=null){
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

  //根据DIV指引定位
  imgGuide() {
    this.storage.get('qdGuied').then((qdguied) => {
      this.roleTop = document.getElementById("textCss").offsetTop;
      this.roleLeft = document.getElementById("textCss").offsetLeft;
      this.guideDate = {
        "top": (this.roleTop + 50) + 'px',
        "left": (this.roleLeft + 40) + 'px'
      };
      if ((qdguied < 3 || qdguied == '' || qdguied == 'null' || qdguied == undefined) && this.roleTop > 0 && this.roleLeft) {
        if (qdguied == '' || qdguied == 'null' || qdguied == undefined) {
          this.storage.set('qdGuied', 1);
        } else {
          this.storage.set('qdGuied', qdguied + 1);
        }
        this.Guide=true;
        this.isMask=true;
      }
    });
  }

  //头部右侧图标按钮
  Changed() {
    if (this.swich == 'swi1') {
      this.swi1=true;
      this.swi2=false;
      this.cibpay();
    }
    if (this.swich == 'swi2') {
      this.swi1=false;
      this.swi2=true;
      this.Newcibpay();
    }
  }

  //一口价调用接口
  Newcibpay(){
    //传值贴现票款
    let data:any={
      money:(this.item.allmoney*10000),
      txje:this.item.buyout_price,
      jxts:this.item.jxts
    };
    if((this.apiSev.cibrz==1&&this.apiSev.jdrz==0&&this.itemPinfen.disc_v_acct_acct_no!=''&&this.itemPinfen.disc_v_acct_acct_no!='undefined'&&this.itemPinfen.disc_v_acct_acct_no!=null)||(this.apiSev.cibrz==1&&this.apiSev.jdrz==1&&this.Choice==1&&this.itemPinfen.disc_v_acct_acct_no!=''&&this.itemPinfen.disc_v_acct_acct_no!='undefined'&&this.itemPinfen.disc_v_acct_acct_no!=null)){

    }else {
      data.bindId=this.itemPinfen.disc_bind_id;
      data.draftNo=this.itemPinfen.draft_no;
    }
    this.apiSev.api("newpost", "dispatch/fee", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let discounttxty:any=0;
        if(this.item.disc_bind_id!=''&&this.item.disc_bind_id!='undefined'&&this.item.disc_bind_id!=null){
          discounttxty = data.data.fee;
        }else {
          discounttxty = data.data.fee;
        }
        if (this.item.type1 == '2') {
          this.discounttxtys = ((discounttxty * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
          this.Actual = ((this.item.buyout_price * 1 + discounttxty * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }else if (this.item.type1 == '1'){
          this.Actual = ((this.item.buyout_price * 1).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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

  //取消按钮
  newCancel(){
    this.isMask = false;
    this.isOffer = false;
    this.isOfferB=false;
    this.isCostItem=false;
    this.isCostItem_jd=false;
    this.Guide=false;
  }

}
