import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { OpenaccountEPage } from './openaccountE';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';

@Component({
  selector: 'page-openaccountD',
  templateUrl: 'openaccountD.html'
})


export class openaccountDPage {

  public memberId = '';
  public orgType:any;
  public meType:any;

  public isMask=false;//不显示上传图片
  public isUpload=true;//不显示上传图片
  public isUploadB=true;//委托书不显示上传图片
  public isUploadC=true;//报价承诺函不显示上传图片
  public isUploadD=true;//身份证正面
  public isUploadE=true;//身份证反面
  public isPicpath=false;//不显示上传图片
  public isPicpathB=false;//委托书不显示上传图片
  public isPicpathC=false;//报价承诺函不显示上传图片
  public isPicpathD=false;//身份证正面
  public isPicpathE=false;//身份证反面
  public uploadFlag:boolean=false;//是否上传图片成功
  public selection:any='';//选择点击哪张图

  public judgeA = true; //机构
  public judgeB = false;  //企业
  public judgeC = true;  //机构图

  public cib:any={
    id:'',
  };
  public openaccount:any={
    businesss:'',
    picpath:'',
    attorney:'',
    offer:'',
    positive:'',
    sideitive:'',
    attorneys:'',
    offers:'',
    positives:'',
    sideitives:'',
    businessss:'',

};


  constructor(public storage: Storage, public navCtrl: NavController,public loadingCtrl: LoadingController, public viewCtrl: ViewController, public apiSev: apiService, public camera: Camera,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.apiSev.pass='0';
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
        if(this.orgType==1){
          this.judgeA = true;
          this.judgeB = false;
          this.judgeC = true;
        }else if(this.orgType==0){
          this.judgeA = false;
          this.judgeB = true;
          this.judgeC = false;
        }
        this.openaccounTactive();
      });
    });
    this.meType = this.params.get("ME_TYPE"); //我类型
  }
  business(){
    this.selection=1;
  }
  attorney(){
    this.selection=2;
  }
  offer(){
    this.selection=3;
  }
  positive(){
    this.selection=4;
  }
  sideitive(){
    this.selection=5;
  }

  openaccounTactive(){
    let data:any={
      memberId:this.memberId,
      type:this.orgType,
    };
    this.apiSev.api("newpost", "orginfo/rz", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.cib = data.data.cib;
        this.cib.id = data.data.cib.id;
        this.cibget();
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //上传营业执照图片
  findPic (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.openaccount.businesss = this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.openaccount.businessss = data.data.base64Image;
          this.openaccount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUpload = false;
          this.isPicpath = true;
          loading.dismiss();
        }else {
          loading.dismiss();
          this.apiSev.itip(data.msg);
        }
      },(error) => {
        loading.dismiss();
        this.apiSev.itip('操作失败！');
      }, 6100,data);
    }, (err) => {
      loading.dismiss();
    });
  }

  //上传营业执照图片
  findPicB (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.openaccount.attorney = this.apiSev.getOrderImgUrl+data.data.base64Image;   //委托书展示的图片
          this.openaccount.attorneys = data.data.base64Image;
          this.openaccount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUploadB = false;
          this.isPicpathB = true;
          loading.dismiss();
        }else {
          this.apiSev.itip(data.msg);
          loading.dismiss();
        }
      },(error) => {
        loading.dismiss();
        this.apiSev.itip('操作失败！');
      }, 6100,data);
    }, (err) => {
      loading.dismiss();
    });
  }

  //上传报价承诺函扫描件图片
  findPicC (options) {
    let loading = this.loadingCtrl.create({
      //content: '请等待',
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.openaccount.offer = this.apiSev.getOrderImgUrl+data.data.base64Image;   //委托书展示的图片
          this.openaccount.offers = data.data.base64Image;
          this.openaccount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUploadC = false;
          this.isPicpathC = true;
          loading.dismiss();
        }else {
          this.apiSev.itip(data.msg);
          loading.dismiss();
        }
      },(error) => {
        loading.dismiss();
        this.apiSev.itip('操作失败！');
      }, 6100,data);
    }, (err) => {
      loading.dismiss();
    });
  }

  //上传身份证正面图片
  findPicD (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.openaccount.positive= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.openaccount.positives=data.data.base64Image;
          this.openaccount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUploadD = false;
          this.isPicpathD = true;
          loading.dismiss();
        }else {
          this.apiSev.itip(data.msg);
          loading.dismiss();
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
        loading.dismiss();
      }, 6100,data);
    }, (err) => {
      loading.dismiss();
    });
  }

  //上传身份证反面图片
  findPicE (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.openaccount.sideitive= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.openaccount.sideitives = data.data.base64Image;
          this.openaccount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUploadE = false;
          this.isPicpathE = true;
          loading.dismiss();
        }else {
          this.apiSev.itip(data.msg);
          loading.dismiss();
        }
      },(error) => {
        loading.dismiss();
        this.apiSev.itip('操作失败！');
        loading.dismiss();
      }, 6100,data);
    }, (err) => {
      loading.dismiss();
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
    if(this.selection==1){
      this.findPic(options);
    }
    if(this.selection==2){
      this.findPicB(options);
    }
    if(this.selection==3){
      this.findPicC(options);
    }
    if(this.selection==4){
      this.findPicD(options);
    }
    if(this.selection==5){
      this.findPicE(options);
    }
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
    if(this.selection==1){
      this.findPic(options);
    }
    if(this.selection==2){
      this.findPicB(options);
    }
    if(this.selection==3){
      this.findPicC(options);
    }
    if(this.selection==4){
      this.findPicD(options);
    }
    if(this.selection==5){
      this.findPicE(options);
    }
    this.closeMask();
  }


  //上传图弹出层
  uploadBtn(){
    this.isMask = true;
  }
  closeMask(){
    this.isMask = false;
  }
  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
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


  openaccountPage(){
    if(this.openaccount.businesss == null || this.openaccount.businesss == ''){
      this.apiSev.itip("请上传清晰的营业执照正面照片");
      return;
    }
/*    if(this.openaccount.attorney == null || this.openaccount.attorney == ''){
      this.apiSev.itip("请上传清晰的授权委托书扫描件");
      return;
    }*/
    if(this.openaccount.positive == null || this.openaccount.positive == ''){
      this.apiSev.itip("点击上传身份证正面");
      return;
    }
    if(this.openaccount.sideitive == null || this.openaccount.sideitive == ''){
      this.apiSev.itip("点击上传身份证反面");
      return;
    }

    let data:any={
      memberId:this.memberId,
      id:this.cib.id,
      imgPathA1:this.openaccount.positives,
      imgPathA2:this.openaccount.sideitives,
      imgPath20:this.openaccount.businessss,
      //imgPath40:this.openaccount.attorneys,
    };
/*    if (this.orgType == 1) {
      if (this.openaccount.offer == null || this.openaccount.offer == '') {
        this.apiSev.itip("请上传清晰的报价承诺函扫描件");
        return;
      }
      data.imgPathCnh = this.openaccount.offers;
    }*/
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.apiSev.api("newpost", "/cib/update", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(OpenaccountEPage,{
          ORG_TYPE:this.orgType,
          ME_TYPE:this.meType,
        });
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
    loading.dismiss();
  }

  cibget(){
    let data:any={
      memberId:this.memberId,
      id:this.cib.id,
    };
    this.apiSev.api("newpost", "cib/get/", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let openaccount = data.data;
        this.openaccount.email = openaccount.email;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  closeme(){
    if(this.meType==1){
      this.navCtrl.setRoot(MePage,{
        INDEX:5
      });
    }else if(this.meType==0){
      this.navCtrl.setRoot(MePageB,{
        INDEX:5
      });
    }else {
      this.navCtrl.pop();
    }
  }
}
