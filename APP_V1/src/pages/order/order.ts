import { Component } from '@angular/core';
import { LoadingController,NavController, ViewController ,NavParams,ModalController} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderDetailPage } from '../order/orderDetail';
import { OrderDakuanPage } from '../order/orderDakuan';
import { OrderBeishuPage } from '../order/orderBeishu';
import { OrderDetailShowPage } from '../order/orderDetailShow';
import { Login } from '../login/login';
import { OrderNewDetailPage } from '../order/orderNewDetail';
import {HomePageB} from "../home/homeB";
import {JdinfoPage} from "../jd/jdinfo";
import {jdLicense3Page} from "../jd/jdLicense3";
import {jdlicense4Page} from "../jd/jdLicense4";
import {jdbindingBPage} from "../jd/jdbindingB";
import {MePage} from "../me/me";

@Component({
  selector: 'page-order',
  templateUrl: 'order.html'
})
export class OrderPage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;
  public swich= 'swi1';
  public ListsTime1 = [];
  public ListsTime2 = [];
  public active:any={};
  public memberId:any;
  public cib:any={};
  public orgId:any;

  public pageNo1=1;
  public pageNo2=1;
  public pageMax1=1;
  public pageMax2=1;
  public Lists1 = [];
  public Lists2 = [];
  public Lists3 = [];
  public ORGID = "";
  public isOk:boolean=false;

  public maskDiv=false; //弹出层
  public Authentication=false; //开户

  public jdData:any={};
  public cibrz:any=0; //是否兴业开户
  public jdrz:any=0;  //是否京东开户

  constructor(public storage: Storage,public modalCtrl:ModalController,public navCtrl: NavController,public loadingCtrl: LoadingController, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      let sel=this.params.get('sel');

	  if (sel==2)
	  {
		this.swich= 'swi2';
	  }
	  else
		this.swich= 'swi1';
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
          this.NewMessage();
        })
      }
    });
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.Toactive();  //开户判断
      this.surplus();
    });
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });


	this.Lists1 = [];
	this.Lists2 = [];
	this.Lists3 =[];
	this.pageNo1=1;
	this.pageNo2=1;
	this.pageMax1=1;
	this.pageMax2=1;

	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];
	for (let i=0;i<this.ListsTime2.length;i++){
		clearInterval(this.ListsTime2[i]);
	}
	this.ListsTime2 = [];

	this.storage.get('ORGID').then((res)=>{
		this.ORGID=res;

			if (this.swich=='swi1')
			{
				this.initData1(1);
				//this.initData3(1);

        let data:any={
          pageIndex:1,
          pageSize:5,
          orgId:this.ORGID,
          orderTypes:['DISCOUNTRECORDSP','DISCOUNTRECORD'],
          orderflags:[1,4]
        };
        this.apiSev.api("newpost", "/dispatch/page/hall", (res) => {
          let data = res.data;
          if (data.response == 'success') {
            if(data.data.page.list!=''&&data.data.page.list!=null){
              let objs = data.data.page.list;
              if(objs.length>0){
                this.isOk = true;
                for (let i=0;i<objs.length;i++){
                  this.Lists3.push(objs[i]);
                }
              }
            }

          } else {
            this.apiSev.itip(data.msg);
          }
        }, (error) => {
          this.apiSev.itip('操作失败！');
          return;
        }, 6100, data);

			}
			else
			{
				this.initData2(1);
			}

	})
  }

  fresh(){
	let loading = this.loadingCtrl.create({
      //content: '请等待',
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
	loading.present();
	this.ionViewDidEnter();
	loading.dismiss();
  }

  Changed(e){
	  this.Lists1 = [];
		this.Lists2 = [];
		this.Lists3 = [];
		this.pageNo1=1;
		this.pageNo2=1;
		this.pageMax1=1;
		this.pageMax2=1;
	  	for (let i=0;i<this.ListsTime1.length;i++){
			clearInterval(this.ListsTime1[i]);
		}
		this.ListsTime1 = [];
		for (let i=0;i<this.ListsTime2.length;i++){
			clearInterval(this.ListsTime2[i]);
		}
		this.ListsTime2 = [];

		setTimeout(() => {
			if (this.swich=='swi1')
			{
				this.initData1(1);
        this.initData3(1);
			}
			else
			{
				this.initData2(1);
			}
		}, 100);
  }



  initData1(pagenumber) {

	let data={
		pageIndex:pagenumber,
		pageSize:999,
		orgId:this.ORGID
	}
	let objs=[];
    this.apiSev.api("newpost", "dispatch/halllist", (res) => {

      if((res.data.response == 'success')&&(res.data.data.list!=null)) {
        objs=res.data.data.list;
		this.pageMax1=res.data.data.pages;
        for (let i=0;i<objs.length;i++){
			let date1 = new Date();//'2017-9-26 15:36:00'
			let date2 = new Date(objs[i].create_time.replace(/-/g, "/"));
			date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();
      var afterHour = Math.floor((s2 - s1)/1000);
      var hour=Math.floor(afterHour/3600);
      var min = Math.floor((afterHour-hour*3600)/60);//计算整数分
      var afterMin = afterHour - min*60;//取得算出分后剩余的秒数
			if (afterHour<1){
				objs[i].difDate = '无'
			}else{
				objs[i].difDate = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
			}
            this.Lists1.push(objs[i]);
        }
      }
    }, (error) => {},2000,data);



    setTimeout(() => {

		for (let i=0;i<this.Lists1.length;i++){

			let date1 = new Date();//'2017-9-26 15:36:00'
			let date2 = new Date(this.Lists1[i].create_time.replace(/-/g, "/"));
			 date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();


			var afterHour = Math.floor((s2 - s1)/1000);

			document.getElementById("h"+this.Lists1[i].did).innerHTML = afterHour.toString();

			let timePromise = setInterval(()=>{

				if (document.getElementById("h"+this.Lists1[i].did)!=null)
				{
				  var minjj=parseInt(document.getElementById("h"+this.Lists1[i].did).innerHTML)-1;
				  document.getElementById("h"+this.Lists1[i].did).innerHTML= minjj.toString();
				  if(minjj<=0){
					clearInterval(timePromise);
					timePromise = undefined;

					document.getElementById(this.Lists1[i].did).innerHTML = '无' ;
				  }else{

            var hour=Math.floor(minjj/3600);
            var min = Math.floor((minjj-hour*3600)/60);//计算整数分
            var afterMin = minjj - min*60;//取得算出分后剩余的秒数

					document.getElementById(this.Lists1[i].did).innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
				  }
				}
				else{
					clearInterval(timePromise);
					timePromise = undefined;
				}

			},1000,1000);
			this.ListsTime1.push(timePromise);
		}

    }, 500);

  }
  doInfinite1(infiniteScroll) {


    setTimeout(() => {

      if(this.isOk=true){
        console.log("进方法");
        this.pageNo1 = this.pageNo1 + 1;
        this.initData3(this.pageNo1);
        infiniteScroll.complete();
      }else{
        infiniteScroll.enable(false);
        infiniteScroll.complete();
      }

	  if (this.pageMax1<this.pageNo1+1)
	  {
		  infiniteScroll.complete();
	  }
	  else{
		  	for (let i=0;i<this.ListsTime1.length;i++){
				clearInterval(this.ListsTime1[i]);
			}
			this.ListsTime1 = [];
		  this.pageNo1=this.pageNo1+1;
		  this.initData1(this.pageNo1);
		  infiniteScroll.complete();
	  }

    }, 500);
  }


  initData2(pagenumber) {



	for (let i=0;i<this.ListsTime2.length;i++){
		clearInterval(this.ListsTime2[i]);
	}
	this.ListsTime2 = [];
	let data={
		pageIndex:pagenumber,
		pageSize:5,
		orgId:this.ORGID
	}
	let objs=[];
    this.apiSev.api("newpost", "dispatch/tradelist", (res) => {

      if((res.data.response == 'success')&&(res.data.data.list!=null)) {
        objs=res.data.data.list;
		this.pageMax2=res.data.data.pages;
        for (let i=0;i<objs.length;i++){
			let date1 = new Date();//'2017-9-26 15:36:00'
			let date2 = new Date(objs[i].create_time.replace(/-/g, "/"));
			date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();
      var afterHour = Math.floor((s2 - s1)/1000);
      var hour=Math.floor(afterHour/3600);
      var min = Math.floor((afterHour-hour*3600)/60);//计算整数分
      var afterMin = afterHour - min*60;//取得算出分后剩余的秒数
			if (afterHour<1){
				objs[i].difDate = '无'
			}else{
				objs[i].difDate = this.apiSev.PrefixInteger(hour,1)+':'+this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
			}
            this.Lists2.push(objs[i]);
        }
      }
    }, (error) => {},2000,data);



    setTimeout(() => {

		for (let i=0;i<this.Lists2.length;i++){
			if (this.Lists2[i].state==1) {
				let date1 = new Date();//'2017-9-26 15:36:00'
				let date2 = new Date(this.Lists2[i].create_time.replace(/-/g, "/"));
				date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
				let s1 = date1.getTime(),s2 = date2.getTime();


				var afterHour = Math.floor((s2 - s1)/1000);
				document.getElementById("ph"+this.Lists2[i].dist_id).innerHTML = afterHour.toString();


				let timePromise = setInterval(()=>{

					if (document.getElementById("ph"+this.Lists2[i].dist_id)!=null)
					{
						  var minjj=parseInt(document.getElementById("ph"+this.Lists2[i].dist_id).innerHTML)-1;
						  document.getElementById("ph"+this.Lists2[i].dist_id).innerHTML= minjj.toString();
						  if(minjj<=0){
							clearInterval(timePromise);
							timePromise = undefined;

							document.getElementById("p"+this.Lists2[i].dist_id).innerHTML = '无' ;
						  }else{

                var hour=Math.floor(minjj/3600);
                var min = Math.floor((minjj-hour*3600)/60);//计算整数分
                var afterMin = minjj - min*60;//取得算出分后剩余的秒数

							document.getElementById("p"+this.Lists2[i].dist_id).innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
						  }
					}
					else{
						clearInterval(timePromise);
						timePromise = undefined;
					}
				},1000,1000);
				this.ListsTime2.push(timePromise);
			}


		}

    }, 1000);



  }
  doInfinite2(infiniteScroll) {

    setTimeout(() => {
	  if (this.pageMax2<this.pageNo2+1)
	  {
		  infiniteScroll.complete();
	  }
	  else{

			for (let i=0;i<this.ListsTime2.length;i++){
				clearInterval(this.ListsTime2[i]);
			}
			this.ListsTime2 = [];
		  this.pageNo2=this.pageNo2+1;
		  this.initData2(this.pageNo2);
		  infiniteScroll.complete();
	  }
    }, 500);
  }


  initData3(pagenumber){
    let data:any={
      pageIndex:pagenumber,
      pageSize:5,
      orgId:this.ORGID,
      orderTypes:['DISCOUNTRECORDSP','DISCOUNTRECORD'],
      orderflags:[1,4]
    };
    this.apiSev.api("newpost", "/dispatch/page/hall", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.page.list!=''&&data.data.page.list!=null){
          let objs = data.data.page.list;
          if(objs.length>0){
              this.isOk = true;
            for (let i=0;i<objs.length;i++){
              this.Lists3.push(objs[i]);
            }
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


  SelectedJD (item) {
    if(item.disc_bind_id!=null&&item.disc_bind_id!=''&&item.disc_bind_id!='undefined'&&item.disc_v_acct_acct_no!=null&&item.disc_v_acct_acct_no!=''&&item.disc_v_acct_acct_no!='undefined'&&(this.cibrz==1||this.jdrz==1)){

    }else {
      if(item.disc_bind_id!=null&&item.disc_bind_id!=''&&item.disc_bind_id!='undefined'&&this.jdrz==0){
        this.maskDiv = true;
        this.Authentication = true;
        return;
      }

      if(item.disc_v_acct_acct_no!=null&&item.disc_v_acct_acct_no!=''&&item.disc_v_acct_acct_no!='undefined'&&this.cibrz==0){
        this.maskDiv = true;
        this.Authentication = true;
        return;
      }
    }

    this.Lists1 = [];
    this.Lists2 = [];
    this.Lists3 = [];
    this.pageNo1=1;
    this.pageNo2=1;
    this.pageMax1=1;
    this.pageMax2=1;
	for (let i=0;i<this.ListsTime1.length;i++){
	clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];
	for (let i=0;i<this.ListsTime2.length;i++){
		clearInterval(this.ListsTime2[i]);
	}
	this.ListsTime2 = [];
    if (item.state==1){
		this.navCtrl.push(OrderDetailShowPage,{item});
	}
	if (item.state==4 ){
		this.navCtrl.push(OrderDakuanPage,{item});
	}
	if (item.state==2||item.state==7  ){
		this.navCtrl.push(OrderBeishuPage,{item});
	}
	if (item.state==5 ){
		this.navCtrl.push(OrderBeishuPage,{item});
	}
	if(item.tag=='WAITTRADE'){
    this.navCtrl.push(OrderDetailPage,{item});
  }
  }

  SelectedJD3 (item) {
    this.pageNo1=1;
    this.pageMax1=1;

    if(item.disc_bind_id!=null&&item.disc_bind_id!=''&&item.disc_bind_id!='undefined'&&item.disc_v_acct_acct_no!=null&&item.disc_v_acct_acct_no!=''&&item.disc_v_acct_acct_no!='undefined'&&(this.cibrz==1||this.jdrz==1)){

    }else {
      if(item.disc_bind_id!=null&&item.disc_bind_id!=''&&item.disc_bind_id!='undefined'&&this.jdrz==0){
        this.maskDiv = true;
        this.Authentication = true;
        return;
      }

      if(item.disc_v_acct_acct_no!=null&&item.disc_v_acct_acct_no!=''&&item.disc_v_acct_acct_no!='undefined'&&this.cibrz==0){
        this.maskDiv = true;
        this.Authentication = true;
        return;
      }
    }

    if(item.bns_member_id==this.memberId){
      this.apiSev.itip("您不能选择自己的订单报价");
      return;
    }else {
      this.Lists3 = [];
      if(item.tag=='TRADE'||(item.dispId!=''&&item.dispId!=null&&item.dispId!='undefined')){
        this.navCtrl.push(OrderNewDetailPage,{item,TradingMarket:'true'});
      }
      if((item.tag=='WAITTRADE_MINE'||item.tag=='WAITTRADE')&&(item.dispId==''||item.dispId==null||item.dispId=='undefined')){
        this.navCtrl.push(OrderDetailPage,{item,TradingMarket:'true'});
      }
    }
  }


  Selected (item) {
    if(item.disc_bind_id!=null&&item.disc_bind_id!=''&&item.disc_bind_id!='undefined'&&item.disc_v_acct_acct_no!=null&&item.disc_v_acct_acct_no!=''&&item.disc_v_acct_acct_no!='undefined'&&(this.cibrz==1||this.jdrz==1)){

    }else {
      if(item.disc_bind_id!=null&&item.disc_bind_id!=''&&item.disc_bind_id!='undefined'&&this.jdrz==0){
        this.maskDiv = true;
        this.Authentication = true;
        return;
      }

      if(item.disc_v_acct_acct_no!=null&&item.disc_v_acct_acct_no!=''&&item.disc_v_acct_acct_no!='undefined'&&this.cibrz==0){
        this.maskDiv = true;
        this.Authentication = true;
        return;
      }
    }

    this.navCtrl.push(OrderDetailPage,{item});

  }

  getDurg (test) {
    //artime.push(timePromise);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      if(this.cibrz==1||this.jdrz==1){
        this.maskDiv = false;
        this.Authentication=false;
      }
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
      type:1,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if((data.data.cib==null||data.data.cib==''||data.data.cib=='undefined')&&(data.data.jdjr==null||data.data.jdjr==''||data.data.jdjr=='undefined')){
          this.maskDiv = true;
          this.Authentication = true;
          return;
        }

        if(data.data.cib!=null&&data.data.cib!=''&&data.data.cib!='undefined'){
          //是否兴业开户
          if(data.data.cib.status==2&&data.data.cib.cib_check_state=='PASS'){
            this.cibrz=1;
            this.apiSev.cibrz=1;
          }else {
            //兴业绑定开户判断
            this.cibrz=0;
            this.apiSev.cibrz=0;
          }
        }else {
          //兴业绑定开户判断
          this.cibrz=0;
          this.apiSev.cibrz=0;
        }

        if(data.data.jdjr!=null&&data.data.jdjr!=''&&data.data.jdjr!='undefined'){
          this.jdData=data.data.jdjr;
          //是否开京东户
          if(this.jdData.status==2&&this.jdData.check_state=='PASS'){
            this.jdrz=1;
            this.apiSev.jdrz=1;
          }else {
            //京东绑定开户判断
            this.jdrz=0;
            this.apiSev.jdrz=0;
          }
        }else {
          //京东绑定开户判断
          this.jdrz=0;
          this.apiSev.jdrz=0;
        }
        //未开户-去往开户页面
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
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
  }

  cibkaihu(){
      this.navCtrl.setRoot(MePage);
  }

//tab红点
  surplus(){
    if(this.memberId!=null && this.memberId!='') {
      let data: any = {
        memberId: this.memberId,
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
