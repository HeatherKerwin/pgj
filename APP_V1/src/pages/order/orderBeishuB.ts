import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OrderPageB } from '../order/orderB';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { InAppBrowser } from '@ionic-native/in-app-browser';


@Component({
  selector: 'page-orderBeishuB',
  templateUrl: 'orderBeishuB.html'
})
export class OrderBeishuPageB {
  public user:any = {};
  public orderflag = '';
  public isCodeDisabled = true;

  public item:any = {};
  public item2:any = {};
  public maskDiv=false; //弹出层
  public maskDiv1=false; //弹出层
  public QXcash:boolean=false;

  public ListsTime1 = [];
  public Lists1 = [];
  public isSelect='';
  public ORGID = "";
  public info = "0";
  public isCancel=false;
  public isConfirm=false;
  public memo = "";
  public endtime = "";
  public isMemo=false;
  public pic = "";
  public picStr = "";
  public memberId = "";
  public bankAcctAcctNo:any='';
  public bankAcctAcctName="";
  public bankAcctCnapsCode="";
  public isCash:boolean;
  public outputemail='';
  public bankChildName:any='';  //支行全称


  constructor(public storage: Storage,public iab: InAppBrowser,public camera: Camera,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
      //window.clearInterval(mapPanel.drawTick);
      this.item=this.params.get('item');
      console.log(this.item);
	  if (this.item.endorse_pic_path) this.pic=this.apiSev.getOrderImgUrl+this.item.endorse_pic_path;
	  this.storage.get('userInfo').then((res)=>{
		this.memberId=res.id;
	   })
  }

  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
	  this.maskDiv1 = false;
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

  bigshow(){
	  this.phCtrl.show(this.pic, '背书截图', {share:false});
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
          data.type = 0;
          data.cancel = this.info;
          data.cancelCause = this.memo
        }
      } else {
        data.memberId = this.memberId;
        data.orderId = this.item.id;
        data.orderType = this.item.order_type;
        data.type = 0;
        data.cancel = this.info;
        data.cancelCause = this.memo
      }
		let objs=[];

		this.apiSev.api("newpost", "cibcancel/save", (res) => {

		  if(res.data.response == 'success') {
			this.apiSev.itip("已成功申请取消订单");
			this.navCtrl.pop();
		  }
		}, (error) => {},6100,data);
    this.maskDiv1 = false;
  }


  Save(){

    let data={
		id:this.item.id,
		orderflag:this.orderflag,
		endorsePicPath:this.picStr
	}

	if ((this.item.type1=='1')&&(this.picStr=="")){
		this.apiSev.itip('请上传已经背书过的截图');
	}
	else{
		let url= "" ;
		if (this.item.order_type=="DISCOUNTRECORD"){
			url= "discountrecord/update/endorsetime" ;
		}
		else{
			url= "discountrecordsp/update/endorsetime" ;
		}

		this.apiSev.api("newpost", url , (res) => {
			  if(res.data.response == 'success') {
				this.apiSev.itip("确认背书成功");
				this.navCtrl.setRoot(OrderPageB );
			  }
		}, (error) => {},6100,data);
	}
  }

  Chage(){
	if (this.info=="4")
	  this.isMemo=true;
    else
	  this.isMemo=false;
  }

  showConfirm(){
    this.maskDiv = true;
  }

  showConfirm1(){
    this.maskDiv1 = true;
    this.isCash = false;
    this.QXcash = true;
  }

  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }

  closeMask1(){
	this.maskDiv1 = false;
  }

  okMask(){
  	let data={
		id:this.item.id,
		orderflag:this.orderflag ,
		selectDistId:this.item2.distId
	}
	let objs=[];
    this.apiSev.api("newpost", "discountrecord/update/select", (res) => {

      if(res.data.response == 'success') {
        this.apiSev.itip("已成功选定报价");
      }
    }, (error) => {},6100,data);
    this.maskDiv = false;
  }

  upload(){
     this.maskDiv = true;
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
    this.camera.getPicture(options).then((imageData) => {
      let url=  imageData; //'data:image/jpeg;base64,' +
      let data= {
        base64Image:url
      }
	  //this.pic=url;
      this.apiSev.api("newpost", "upload/image", (res) => {
        if (res.data.response == 'success') {
          this.pic= this.apiSev.getOrderImgUrl+res.data.data.base64Image;//this.apiSev.getOrderImgUrl+data.data; //展示的图片
          this.picStr= res.data.data.base64Image;
        }

      },(error) => {
        this.apiSev.itip('操作失败！'+JSON.stringify(error));
      }, 6100,data);

    }, (err) => {
      // Handle error
    });
  }


  ionViewDidEnter(){

	for (let i=0;i<this.ListsTime1.length;i++){
		clearInterval(this.ListsTime1[i]);
	}
	this.ListsTime1 = [];

	this.storage.get('ORGID').then((res)=>{
		this.ORGID=res;


		let date1 = new Date();//'2017-9-26 15:36:00'
		let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
		date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
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
		this.initData();

	})

  }



  initData() {




	let data={
		discId:this.item.id,
		order_type:this.item.order_type
	}
	let objs=[];

    this.apiSev.api("newpost", "order/isselect/details", (res) => {

      if((res.data.response == 'success')&&(res.data.data.listmap!=null)) {
        this.item=res.data.data;
		this.bankAcctAcctNo=res.data.data.cib.bankAcctAcctNo;
		this.bankAcctAcctName=res.data.data.cib.bankAcctAcctName;
		this.bankAcctCnapsCode=res.data.data.cib.bankAcctCnapsCode;
		this.bankChildName=res.data.data.cib.bankChildName;
        objs=res.data.data.listmap;
		this.endtime=res.data.data.endtime;
		this.orderflag = res.data.data.orderflag;
		this.item.dist_bind_id=res.data.data.dist_bind_id;
        for (let i=0;i<objs.length;i++){
            this.Lists1.push(objs[i]);
        }
      }
    }, (error) => {},2000,data);




	    setTimeout(() => {



			let date1 = new Date();
			let date2 = new Date(this.item.create_time.replace(/-/g, "/"));
			date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
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

  }



  Selected (item) {

    if (document.getElementById("did").innerHTML == '无')
	    return;
    this.item2=item;
	for (let i=0;i<this.Lists1.length;i++){
		document.getElementById(this.Lists1[i].memberId).style.border="0";
		document.getElementById('h'+this.Lists1[i].memberId).setAttribute("src","assets/images/order/weixuanzhong.png");
	}
    document.getElementById(item.memberId).style.border="1px solid #e84c3d";

    document.getElementById('h'+item.memberId).setAttribute("src","assets/images/order/xuanzhong.png");

	this.isSelect=item.memberId;
	this.showConfirm();
  }

  getDurg (test) {
    //artime.push(timePromise);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  outputHT(){
    //合同,如果有京东ID走url，不是京东走兴业的邮箱发送
    if(this.item.dist_bind_id!=null&&this.item.dist_bind_id!=''&&this.item.dist_bind_id!='undifined'){
      let data:any={
        orderId:this.item.id,
        orderType:this.item.order_type,
      };
      //类型企业前2中，资方后两种（DISCOUNTRECORD、DISCOUNTRECORDSP、DISTRIBUTEORDER、DISTRIBUTEORDERSP）
      if(this.item.order_type=='DISCOUNTRECORD'){
        data.orderType='DISCOUNTRECORD';
      }else {
        data.orderType='DISCOUNTRECORDSP';
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
      this.maskDiv1 = true;
      this.isCash = true;
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
        this.maskDiv=false;
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
