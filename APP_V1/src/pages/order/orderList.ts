import { Component } from '@angular/core';
import { NavController, ViewController ,NavParams} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderDakuanPage } from '../order/orderDakuan';
import { OrderBeishuPage } from '../order/orderBeishu';
import { OrderDetailShowPage } from '../order/orderDetailShow';
import { OrderPinjiaPage } from '../order/orderPinjia';
import { MePage } from '../me/me';
import { OrderNewDetailPage } from "./orderNewDetail";

@Component({
  selector: 'page-orderList',
  templateUrl: 'orderList.html'
})
export class OrderListPage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;
  public swich= 'swi1';
  public st= 's1';

  public ListsTime1 = [];
  public ListsTime2 = [];

  public pageNo1=1;
  public pageNo2=1;
  public pageMax1=1;
  public pageMax2=1;
  public Lists1 = [];
  public Lists2 = [];
  public ORGID = "";

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      let sel=this.params.get('sel');

	  if (sel==2)
	  {
		this.swich= 'swi2';
	  }
	  else {
		this.swich= 'swi1';
	  }
  }

  ionViewDidEnter(){

	this.clear();

	this.storage.get('ORGID').then((res)=>{
		this.ORGID=res;

			if (this.swich=='swi1')
			{
				this.initData1(1);
			}
			else
			{
				this.initData2(1);
			}

	})

  }


  Changed(e){
	  	this.clear();

		setTimeout(() => {
			if (this.swich=='swi1')
			{
				this.initData1(1);
			}
			else
			{
				this.initData2(1);
			}
		}, 100);
  }



  initData1(pagenumber) {

	let orderType="DISTRIBUTEORDER";
	if (this.st=="s2")
		orderType="DISTRIBUTEORDERSP";
	let data:any={
		pageIndex:pagenumber,
		pageSize:5,
		orgId:this.ORGID,
		orderType:orderType
	}
	if (this.swich == 'swi2'){//交易中
		data.orderflags=[1,2,4,5,7];
	}
	if (this.swich == 'swi4'){//待评价
		data.orderflags=[3];
		data.comment=1;
	}
	if (this.swich == 'swi5'){//无效
		data.orderflags=[0];
	}
	let objs=[];

    this.apiSev.api("newpost", "order/list/dist", (res) => {

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

			if ((this.Lists1[i].state==1)||(this.Lists1[i].state==4)||(this.Lists1[i].state==2)||(this.Lists1[i].state==7))
			{
				continue;
			}
			let date1 = new Date();//'2017-9-26 15:36:00'
			let date2 = new Date(this.Lists1[i].create_time.replace(/-/g, "/"));
			 date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
			let s1 = date1.getTime(),s2 = date2.getTime();

			var afterHour = Math.floor((s2 - s1)/1000);
			if (document.getElementById("h"+this.Lists1[i].id)!=null)
				document.getElementById("h"+this.Lists1[i].id).innerHTML = afterHour.toString();

			let timePromise = setInterval(()=>{

				if (document.getElementById("h"+this.Lists1[i].id)!=null)
				{
				  var minjj=parseInt(document.getElementById("h"+this.Lists1[i].id).innerHTML)-1;
				  document.getElementById("h"+this.Lists1[i].id).innerHTML= minjj.toString();
				  if(minjj<=0){
					clearInterval(timePromise);
					timePromise = undefined;

					if (document.getElementById(this.Lists1[i].id)!=null)
						document.getElementById(this.Lists1[i].id).innerHTML = '　--:--' ;
				  }else{

            var hour=Math.floor(minjj/3600);
            var min = Math.floor((minjj-hour*3600)/60);//计算整数分
            var afterMin = minjj - min*60;//取得算出分后剩余的秒数
					if (document.getElementById(this.Lists1[i].id)!=null)
						document.getElementById(this.Lists1[i].id).innerHTML = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2) ;//second+"";
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

	let orderType="DISTRIBUTEORDER";
	if (this.st=="s2")
		orderType="DISTRIBUTEORDERSP";
	let data:any={
		pageIndex:pagenumber,
		pageSize:5,
		orgId:this.ORGID,
		orderType:orderType
	}
	if (this.swich == 'swi2'){//交易中
		data.orderflags=[2,4,5,7];
	}
	if (this.swich == 'swi4'){//待评价
		data.orderflags=[3];
		data.comment=1;
	}
	if (this.swich == 'swi5'){//无效
		data.orderflags=[0];
	}
	let objs=[];

    this.apiSev.api("newpost", "order/list/dist", (res) => {

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
				objs[i].difDate = '　--:--'
			}else{
				objs[i].difDate = this.apiSev.PrefixInteger(hour,1)+':'+ this.apiSev.PrefixInteger(min,2)+':'+ this.apiSev.PrefixInteger(afterMin,2)
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
				date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
				let s1 = date1.getTime(),s2 = date2.getTime();

				var afterHour = Math.floor((s2 - s1)/1000);
				if (document.getElementById("ph"+this.Lists2[i].dist_id)!=null)
					document.getElementById("ph"+this.Lists2[i].dist_id).innerHTML = afterHour.toString();


				let timePromise = setInterval(()=>{

					if (document.getElementById("ph"+this.Lists2[i].dist_id)!=null)
					{
						  var minjj=parseInt(document.getElementById("ph"+this.Lists2[i].dist_id).innerHTML)-1;
						  document.getElementById("ph"+this.Lists2[i].dist_id).innerHTML= minjj.toString();
						  if(minjj<=0){
							clearInterval(timePromise);
							timePromise = undefined;

							document.getElementById("p"+this.Lists2[i].dist_id).innerHTML = '　--:--' ;
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



  SelectedJD (item) {

    if (item.state==1){
		this.clear();
		this.navCtrl.push(OrderDetailShowPage,{item});
	}
	if (item.state==4 ){
		this.clear();
		this.navCtrl.push(OrderDakuanPage,{item});
	}
	if (item.state==2||item.state==7||item.state==5  ){
		this.clear();
		this.navCtrl.push(OrderBeishuPage,{item});
	}
	if ((item.state==3)&&(item.c_id==null ) ){
		this.clear();
		this.navCtrl.push(OrderPinjiaPage,{item});
	}
	if((item.state==3)&&(item.c_id!=null)){
      this.navCtrl.push(OrderNewDetailPage,{item});
  }

  }

  clear(){
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
  }



  getDurg (test) {
  }

  close (e) {
  	this.navCtrl.setRoot(MePage);
    e.preventDefault();
  }

}
