import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Camera, CameraOptions } from '@ionic-native/camera';


@Component({
  selector: 'page-jdimgedit',
  templateUrl: 'jdimgedit.html'
})


export class jdimgeditPage {

  public messagecontent: '';
  public memberId;
  public jd: any = {};

  public infosl: boolean = false;


  public isMask = false;//不显示上传图片
  public CameraRoll: boolean = false; //相机
  public Distinguish: boolean = false; //弹窗
  public isUpload: any = true;
  public isPicpath: any = false;
  public isUploadB: any = true;
  public isPicpathB: any = false;
  public isUploadC: any = true;
  public isPicpathC: any = false;
  //正面或反面
  public zfmian: any = 'A';

  public jdData:any={};


    constructor(public storage: Storage,public params: NavParams,public camera: Camera, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
      this.jdData = this.params.get("jdData");
      this.isUpload=false;
      this.isUploadB=false;
      this.isUploadC=false;
      this.isPicpath=true;
      this.isPicpathB=true;
      this.isPicpathC=true;
      this.jd.picpath= this.apiSev.getOrderImgUrl+this.jdData.blicUrlA; //展示的图片
      this.jd.front=this.jdData.blicUrlA;
      this.jd.picpathB= this.apiSev.getOrderImgUrl+this.jdData.lepUrlA; //展示的图片
      this.jd.frontB=this.jdData.lepUrlA;
      this.jd.picpathC= this.apiSev.getOrderImgUrl+this.jdData.lepUrlB; //展示的图片
      this.jd.frontC=this.jdData.lepUrlB;
      console.log(this.jdData);
    }



  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'||e.srcElement.className=='maskConB'){
      this.isMask = false;
      this.CameraRoll =false;
      this.Distinguish =false;
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

  closeMask(){
    this.isMask = false;
    this.CameraRoll =false;
    this.Distinguish =false;
  }


  //查看试例
  uploadBtninfo(){
    this.isMask = true;
    this.CameraRoll= false;
    this.Distinguish =true;
  }

  //上传图弹出层
  uploadBtn(){
    this.isMask = true;
    this.CameraRoll= true;
    this.Distinguish =false;
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
    };
    if(this.zfmian=='A'){
      this.findPic(options);
    }
    if(this.zfmian=='B'){
      this.findPicB(options);
    }
    if(this.zfmian=='C'){
      this.findPicC(options);
    }
    this.closeMask();
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
    };
    if(this.zfmian=='A'){
      this.findPic(options);
    }
    if(this.zfmian=='B'){
      this.findPicB(options);
    }
    if(this.zfmian=='C'){
      this.findPicC(options);
    }
    this.closeMask();
  }

  //上传图片
  findPic (options) {
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url,
        maxKb:2000
      }
      this.apiSev.api("newpost", "upload/image/quality", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.jd.picpath= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.jd.front=data.data.base64Image;
          this.isUpload = false;
          this.isPicpath = true;
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
      }, 6100,data);

    }, (err) => {
    });
  }

  //上传图片
  findPicB (options) {
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url,
        maxKb:2000
      }
      this.apiSev.api("newpost", "upload/image/quality", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.jd.picpathB= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.jd.frontB=data.data.base64Image;
          this.isUploadB = false;
          this.isPicpathB = true;
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
      }, 6100,data);

    }, (err) => {
    });
  }

  //上传图片
  findPicC (options) {
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url,
        maxKb:2000
      }
      this.apiSev.api("newpost", "upload/image/quality", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.jd.picpathC= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.jd.frontC=data.data.base64Image;
          this.isUploadC = false;
          this.isPicpathC = true;
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
      }, 6100,data);

    }, (err) => {
    });
  }


  sava(){
    this.jdData.blicUrlA = this.jd.front;
    this.jdData.lepUrlA = this.jd.frontB;
    this.jdData.lepUrlB = this.jd.frontC;

    this.apiSev.api("newpost", "jdjr/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.apiSev.itip("修改成功");
        this.navCtrl.pop();
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, this.jdData);
  }

}
