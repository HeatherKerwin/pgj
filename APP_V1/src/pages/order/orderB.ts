import { Component } from '@angular/core';
import { LoadingController,NavController, ViewController,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderBeishuPageB } from '../order/orderBeishuB';
import { OrderDetailPageB } from '../order/orderDetailB';
import { OrderDetailPageShowB } from '../order/orderDetailShowB';
import { OrderEndPageB } from '../order/orderEndB';
import { Login } from '../login/login';
import {DiscountOrderPage} from "../discount/discountOrder";
@Component({
  selector: 'page-orderB',
  templateUrl: 'orderB.html'
})
export class OrderPageB {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;

  public ListsTime1 = [];
  public ListsTime2 = [];
  public swich= 'swi1';

  public pageNo1=1;
  public pageNo2=1;
  public pageMax1=1;
  public pageMax2=1;
  public Lists1 = [];
  public Lists2 = [];
  public memberID = "";

  public jdData:any={};

  constructor(public storage: Storage,public modalCtrl:ModalController,public navCtrl: NavController,public loadingCtrl: LoadingController, public viewCtrl: ViewController,public apiSev: apiService) {
      //window.clearInterval(mapPanel.drawTick);
    this.storage.get('userInfo').then((res)=>{
      this.memberID=res.id;
      this.initData1(1);
    })

  }

  ionViewDidEnter(){
    this.storage.get('lgtoken').then((lgtoken)=>{
      if (lgtoken === null || lgtoken === undefined || lgtoken === ''){
        let profileModal = this.modalCtrl.create(Login);
        profileModal.present();
      }
      else{
        this.storage.get('userInfo').then((data)=>{
          this.memberID=data.id;
          this.NewMessage();
          this.Toactive();
        })
      }
    });
	this.Lists1 = [];
    this.Lists2 = [];
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

	this.storage.get('userInfo').then((res)=>{
		this.memberID=res.id;
    this.surplus();
    if (this.swich=='swi1')
    {
      this.initData2(1);
    }
    else
    {
      this.initData1(1);
    }
	})


  }


  Changed(e){
    this.Lists1 = [];
    this.Lists2 = [];
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
        this.initData2(1);
      }
      else
      {
        this.initData1(1);
      }
    }, 100);
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



  initData1(pagenumber) {

	let data={
		pageIndex:pagenumber,
		pageSize:5,
		memberId:this.memberID
	}
	let objs=[];
    this.apiSev.api("newpost", "order/hall", (res) => {

      if((res.data.response == 'success')&&(res.data.data.list!=null)) {
        objs=res.data.data.list;
		this.pageMax1=res.data.data.pages;
        for (let i=0;i<objs.length;i++){
			let date1 = new Date();//'2017-9-26 15:36:00'
			let date2 = new Date(objs[i].create_time.replace(/-/g, "/"));
			date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
      let s1 = date1.getTime(),s2 = date2.getTime();
			var afterHour = Math.floor((s2 - s1)/1000);
			var hour=Math.floor(afterHour/3600);
			var min = Math.floor((afterHour-hour*3600)/60);//计算整数分
			var afterMin = afterHour - min*60;//取得算出分后剩余的秒数
			if (afterHour<1){
				objs[i].difDate = ''
			}else{
				objs[i].difDate = this.apiSev.PrefixInteger(hour,1)+':'+this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
			}
            this.Lists1.push(objs[i]);
        }
      }
    }, (error) => {},2000,data);



    setTimeout(() => {

		for (let i=0;i<this.Lists1.length;i++){
			let date1 = new Date();//'2017-9-26 15:36:00'
			let date2 = new Date(this.Lists1[i].create_time.replace(/-/g, "/"));
			 date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();

			var afterHour = Math.floor((s2 - s1)/1000);

			document.getElementById("h"+this.Lists1[i].id).innerHTML = afterHour.toString();

			let timePromise = setInterval(()=>{

				if (document.getElementById("h"+this.Lists1[i].id)!=null)
				{
				  var minjj=parseInt(document.getElementById("h"+this.Lists1[i].id).innerHTML)-1;
				  document.getElementById("h"+this.Lists1[i].id).innerHTML= minjj.toString();
				  if(minjj<=0){
					clearInterval(timePromise);
					timePromise = undefined;

					document.getElementById(this.Lists1[i].id).innerHTML = '' ;
				  }else{

            var hour=Math.floor(minjj/3600);
          var min = Math.floor((minjj-hour*3600)/60);//计算整数分
          var afterMin = minjj - min*60;//取得算出分后剩余的秒数

          document.getElementById(this.Lists1[i].id).innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
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

  initData2(pagenumber) {



    for (let i=0;i<this.ListsTime2.length;i++){
      clearInterval(this.ListsTime2[i]);
    }
    this.ListsTime2 = [];
/*   let orderType="DISCOUNTRECORD";
   if (this.st=="s2")
      orderType="DISCOUNTRECORDSP";*/
    let data:any={
      pageIndex:pagenumber,
      pageSize:5,
      memberId:this.memberID,
      depositState:0,
      orderflags:1
      //orderType:orderType
    };
    let objs=[];

    this.apiSev.api("newpost", "order/list/disc", (res) => {

      if((res.data.response == 'success')&&(res.data.data.list!=null)) {
        objs=res.data.data.list;
        this.pageMax2=res.data.data.pages;
        for (let i=0;i<objs.length;i++){
          let date1 = new Date();//'2017-9-26 15:36:00'
          let date2 = new Date(objs[i].create_time.replace(/-/g, "/"));
          date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
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
          this.Lists2.push(objs[i]);
        }
      }
    }, (error) => {},2000,data);


    setTimeout(() => {

      for (let i=0;i<this.Lists2.length;i++){

        if (this.Lists2[i].orderflag==1) {
          let date1 = new Date();//'2017-9-26 15:36:00'
          let date2 = new Date(this.Lists2[i].create_time.replace(/-/g, "/"));
          date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
          let s1 = date1.getTime(),s2 = date2.getTime();

          var afterHour = Math.floor((s2 - s1)/1000);

          if (document.getElementById("ph"+this.Lists2[i].id)!=null)
            document.getElementById("ph"+this.Lists2[i].id).innerHTML = afterHour.toString();


          let timePromise = setInterval(()=>{

            if (document.getElementById("ph"+this.Lists2[i].id)!=null)
            {

              var minjj=parseInt(document.getElementById("ph"+this.Lists2[i].id).innerHTML)-1;
              document.getElementById("ph"+this.Lists2[i].id).innerHTML= minjj.toString();

              if(minjj<=0){
                clearInterval(timePromise);
                timePromise = undefined;

                document.getElementById("p"+this.Lists2[i].id).innerHTML = '无' ;
              }else{

                var hour=Math.floor(minjj/3600);
                var min = Math.floor((minjj-hour*3600)/60);//计算整数分
                var afterMin = minjj - min*60;//取得算出分后剩余的秒数
                document.getElementById("p"+this.Lists2[i].id).innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
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

  doInfinite1(infiniteScroll) {


    setTimeout(() => {
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


  SelectedJD(item) {
    let type:any='';
    if(item.order_type=='DISCOUNTRECORDSP'){
      type = 'sp'
    }else if (item.order_type=="DISCOUNTRECORD") {
      type = 'yp'
    }
    this.navCtrl.push(DiscountOrderPage,
      {
        order_type: type,
        type1: item.type1,
        DISCOUNTRECORDID: item.id,
        OnePrice: item.buyout_price
      });
  }



  Selected (item) {
    if (item.orderflag==5)
		this.navCtrl.push(OrderEndPageB,{item});
    if (item.orderflag==4)
		this.navCtrl.push(OrderDetailPageShowB,{item});
	if (item.orderflag==1)
		this.navCtrl.push(OrderDetailPageB,{item});
	if (item.orderflag==2||item.orderflag==7)
		this.navCtrl.push(OrderBeishuPageB,{item});
  }

  getDurg (test) {
    //artime.push(timePromise);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  //接单红点
  surplus(){
    if(this.memberID!=null && this.memberID!='') {
      let data: any = {
        memberId: this.memberID,
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

  //是否有新消息
  NewMessage(){
    let data ={
      memberId:this.memberID
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


  //开户判断
  Toactive() {
    let data:any={
      memberId:this.memberID,
      type:0,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/cib/account", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.cib!=null&&data.data.cib!=''&&data.data.cib!='undefined'){
          //是否兴业开户
          if(data.data.cib.status==2&&data.data.cib.cib_check_state=='PASS'){
            this.apiSev.cibrz=1;
          }else {
            //兴业绑定开户判断
            this.apiSev.cibrz=0;
          }
        }else {
          this.apiSev.cibrz=0;
        }
        if(data.data.jdjr!=null&&data.data.jdjr!=''&&data.data.jdjr!='undefined'){
          this.jdData=data.data.jdjr;
          //是否开京东户
          if(this.jdData.status==2&&this.jdData.check_state=='PASS'){
            this.apiSev.jdrz=1;
          }else {
            //京东绑定开户判断
            this.apiSev.jdrz=0;
          }
        } else {
          this.apiSev.jdrz=0;
        }
        //未开户-去往开户页面
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }

}
