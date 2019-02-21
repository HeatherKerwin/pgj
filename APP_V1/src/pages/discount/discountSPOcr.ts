import { Component } from '@angular/core';
import { NavController, ViewController,LoadingController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { DiscountSPPage } from './discountSP';

@Component({
  selector: 'page-discountSPOcr',
  templateUrl: 'discountSPOcr.html'
})


export class DiscountSPOcrPage {

  public note={//顶部提示
    alert:'',
    content:''
  };

  public discount:any={

  };  //贴现参数

  public isMask=false;//不显示上传图片
  public isUpload=true;//不显示上传图片
  public isPicpath=false;//不显示上传图片
  public uploadFlag:boolean=false;//是否上传图片成功
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗

  public endDateAccuracy:any='';  //结束时间是否清晰
  public beginDateAccuracy:any=''; //开始时间是否清晰
  public moneyAccuracy:any='';  //票面金额是否清晰
  public noAccuracy:any='';   //票号是否清晰
  public bankNoAccuracy:any=''; //承兑行行号是否清晰
  public bankNameAccuracy:any=''; //承兑行是否清晰
  public Ocrgif:boolean=false;  //动图


  constructor(public storage: Storage,public camera: Camera,public params: NavParams,public loadingCtrl: LoadingController, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.topBar();//初始化提示语
  }



  // 初始化提醒
  topBar() {
    this.storage.get('userInfo').then((data) => {
      this.apiSev.api("get", "app/topbar/?belong=0&memberid=" + data.id, (res) => {
        this.note=res;
      },(error) => {}, 500,{});
    })
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
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
    }
    this.findPic(options);
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
    }
    this.findPic(options);
    this.closeMask();
  }

  discountsp(){
    this.isMask = false;
    this.Distinguish =false;
    this.navCtrl.push(DiscountSPPage, {
      TEMP_START: this.discount.beginDate,
      TEMP_END: this.discount.endDate,
      TEMP_ALLMONEY: this.discount.money,
      TEMP_NO: this.discount.no,
      TEMP_BANKNO: this.discount.bankNo,
      TEMP_BANKNAME: this.discount.bankName,
      TEMP_TYPE1: 2,
      TEMP_FRONT:this.discount.front,
      TEMP_PICPATH:this.discount.picpath,
    });
  }

  findPic (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url,
        ocrGenre:'DRAFT'
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.discount.picpath= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.discount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUpload = false;
          this.isPicpath = true;

          if(data.data.ocrInfo.bankNameMap!=null&&data.data.ocrInfo.bankNameMap!=''&&data.data.ocrInfo.bankNameMap!='undefined'){if(data.data.ocrInfo.bankNameMap.bankName!=null&&data.data.ocrInfo.bankNameMap.bankName!=''&&data.data.ocrInfo.bankNameMap.bankName!='undefined'){this.discount.bankName=data.data.ocrInfo.bankNameMap.bankName;}if(data.data.ocrInfo.bankNameMap.Accuracy!=null&&data.data.ocrInfo.bankNameMap.Accuracy!=''&&data.data.ocrInfo.bankNameMap.Accuracy!='undefined'){this.bankNameAccuracy=data.data.ocrInfo.bankNameMap.Accuracy;}}//承兑行全称
          if(data.data.ocrInfo.bankNoMap!=null&&data.data.ocrInfo.bankNoMap!=''&&data.data.ocrInfo.bankNoMap!='undefined'){if(data.data.ocrInfo.bankNoMap.bankNo!=null&&data.data.ocrInfo.bankNoMap.bankNo!=''&&data.data.ocrInfo.bankNoMap.bankNo!='undefined'){this.discount.bankNo=data.data.ocrInfo.bankNoMap.bankNo;}if(data.data.ocrInfo.bankNoMap.Accuracy!=null&&data.data.ocrInfo.bankNoMap.Accuracy!=''&&data.data.ocrInfo.bankNoMap.Accuracy!='undefined'){this.bankNoAccuracy=data.data.ocrInfo.bankNoMap.Accuracy;}}  //承兑行行号
          if(data.data.ocrInfo.noMap!=null&&data.data.ocrInfo.noMap!=''&&data.data.ocrInfo.noMap!='undefined'){if(data.data.ocrInfo.noMap.no!=null&&data.data.ocrInfo.noMap.no!=''&&data.data.ocrInfo.noMap.no!='undefined'){this.discount.no=data.data.ocrInfo.noMap.no;}if(data.data.ocrInfo.noMap.Accuracy!=null&&data.data.ocrInfo.noMap.Accuracy!=''&&data.data.ocrInfo.noMap.Accuracy!='undefined'){this.noAccuracy=data.data.ocrInfo.noMap.Accuracy;}}  //票号
          if(data.data.ocrInfo.moneyMap!=null&&data.data.ocrInfo.moneyMap!=''&&data.data.ocrInfo.moneyMap!='undefined'){if(data.data.ocrInfo.moneyMap.money!=null&&data.data.ocrInfo.moneyMap.money!=''&&data.data.ocrInfo.moneyMap.money!='undefined'){this.discount.money=(data.data.ocrInfo.moneyMap.money*0.0001);}if(data.data.ocrInfo.moneyMap.Accuracy!=null&&data.data.ocrInfo.moneyMap.Accuracy!=''&&data.data.ocrInfo.moneyMap.Accuracy!='undefined'){this.moneyAccuracy=data.data.ocrInfo.moneyMap.Accuracy;}}       //票面金额
          if(data.data.ocrInfo.beginDateMap!=null&&data.data.ocrInfo.beginDateMap!=''&&data.data.ocrInfo.beginDateMap!='undefined'){if(data.data.ocrInfo.beginDateMap.beginDate!=null&&data.data.ocrInfo.beginDateMap.beginDate!=''&&data.data.ocrInfo.beginDateMap.beginDate!='undefined'){this.discount.beginDate=data.data.ocrInfo.beginDateMap.beginDate;}if(data.data.ocrInfo.beginDateMap.Accuracy!=null&&data.data.ocrInfo.beginDateMap.Accuracy!=''&&data.data.ocrInfo.beginDateMap.Accuracy!='undefined'){this.beginDateAccuracy=data.data.ocrInfo.beginDateMap.Accuracy;}}       //创建日期
          if(data.data.ocrInfo.endDateMap!=null&&data.data.ocrInfo.endDateMap!=''&&data.data.ocrInfo.endDateMap!='undefined'){if(data.data.ocrInfo.endDateMap.endDate!=null&&data.data.ocrInfo.endDateMap.endDate!=''&&data.data.ocrInfo.endDateMap.endDate!='undefined'){this.discount.endDate=data.data.ocrInfo.endDateMap.endDate;}if(data.data.ocrInfo.endDateMap.Accuracy!=null&&data.data.ocrInfo.endDateMap.Accuracy!=''&&data.data.ocrInfo.endDateMap.Accuracy!='undefined'){this.endDateAccuracy=data.data.ocrInfo.endDateMap.Accuracy;}}     //结束日期

          if (this.bankNameAccuracy==1&&this.bankNoAccuracy==1&&this.noAccuracy==1&&this.moneyAccuracy==1&&this.endDateAccuracy==1&&this.beginDateAccuracy==1&&this.discount.bankName!='undefined'&&this.discount.bankNo!='undefined'&&this.discount.no!='undefined'&&this.discount.money!='undefined'&&this.discount.beginDate!='undefined'&&this.discount.endDate!='undefined'&&this.discount.bankName != null && this.discount.bankName != '' && this.discount.bankNo != null && this.discount.bankNo != '' && this.discount.no != null && this.discount.no != '' && this.discount.money != null && this.discount.money != '' && this.discount.beginDate != null && this.discount.beginDate != '' && this.discount.endDate != null && this.discount.endDate != '') {
            this.navCtrl.push(DiscountSPPage, {
              TEMP_START: this.discount.beginDate,
              TEMP_END: this.discount.endDate,
              TEMP_ALLMONEY: this.discount.money,
              TEMP_NO: this.discount.no,
              TEMP_BANKNO: this.discount.bankNo,
              TEMP_BANKNAME: this.discount.bankName,
              TEMP_TYPE1: 2,
              TEMP_FRONT:this.discount.front,
              TEMP_PICPATH:this.discount.picpath,
            });
            loading.dismiss();
          } else {
            this.navCtrl.push(DiscountSPPage, {
              TEMP_START: this.discount.beginDate,
              TEMP_END: this.discount.endDate,
              TEMP_ALLMONEY: this.discount.money,
              TEMP_NO: this.discount.no,
              TEMP_BANKNO: this.discount.bankNo,
              TEMP_BANKNAME: this.discount.bankName,
              TEMP_TYPE1: 2,
              TEMP_FRONT:this.discount.front,
              TEMP_PICPATH:this.discount.picpath,
            });
            this.isMask=false;
            this.Distinguish=false;
            this.CameraRoll=false;
            loading.dismiss();
          }
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

}
