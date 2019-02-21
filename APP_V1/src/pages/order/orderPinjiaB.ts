import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderListPageB } from '../order/orderListB';

@Component({
  selector: 'page-orderPinjiaB',
  templateUrl: 'orderPinjiaB.html'
})
export class OrderPinjiaPageB {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = false;

  public item:any = {};
  public itemPinfen:any = {};
  public itemComapny:any = {};

  public maskDiv=false; //弹出层

  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public info = "0";
  public memo = "";
  public isCancel=false;
  public isConfirm=false;
  public chgemoney = 0;
  public exmoney = "0";
  public isMemo=false;
  public code = "";


  constructor(public storage: Storage,public navCtrl: NavController,  public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      this.item=this.params.get('item');
	  this.storage.get('userInfo').then((res)=>{
		this.memberId=res.id;
	  })
  }

  Save(){
	  if (this.score.star==0||this.score1.star==0||this.score2.star==0)
	  {
		  this.apiSev.itip("请选择星级给予评价");
	  }
	  else {
            let type = 1 ;
			if (this.item.order_type=='DISCOUNTRECORD')
				type = 0 ;
	    	let data={
				dcrdId:this.item.id ,
				dtboId:this.item.dist_id ,
				role:0,
				type:type,
				operatorId:this.memberId,
				content:this.memo,
				price:this.score.star,
				service:this.score1.star,
				speed:this.score2.star
			}
			let objs=[];
			this.apiSev.api("newpost", "comments/save", (res) => {

			  if(res.data.response == 'success') {
				this.apiSev.itip("已成功评论");
				this.navCtrl.setRoot(OrderListPageB);
			  }
			}, (error) => {
				this.apiSev.itip("评论不支持表情");
			},6100,data);

	  }
  }


  score: any = {
    star: 0,
    starMap: [
      '不满意',
      '还行',
      '一般',
      '满意',
      '很满意',
    ]
  }
  chooseStar(e){
	if (e.target.dataset.index>=0) {
		let star = parseInt(e.target.dataset.index);
		this.score.star = star;
	}
  }

  score1: any = {
    star: 0,
    starMap: [
      '不满意',
      '还行',
      '一般',
      '满意',
      '很满意',
    ]
  }
  chooseStar1(e){
	  	if (e.target.dataset.index>=0) {
    let star = parseInt(e.target.dataset.index);
    this.score1.star = star;
		}
  }

  score2: any = {
    star: 0,
    starMap: [
      '不满意',
      '还行',
      '一般',
      '满意',
      '很满意',
    ]
  }
  chooseStar2(e){
	  	if (e.target.dataset.index>=0) {
    let star = parseInt(e.target.dataset.index);
    this.score2.star = star;
		}
  }


  ionViewDidEnter(){

	this.storage.get('userInfo').then((res)=>{

		this.memberId=res.id;

		this.initData();

	})

  }


  close(){
  }

  initData() {
  }



  getDurg (test) {
    //artime.push(timePromise);
  }


}
