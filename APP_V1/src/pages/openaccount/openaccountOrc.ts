import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { OpenaccountPage } from './openaccount';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';

@Component({
  selector: 'page-openaccountOrc',
  templateUrl: 'openaccountOrc.html'
})


export class OpenaccountOrcPage {

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
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗
  public Distinguishyyzz:boolean=false; //营业执照弹窗判断
  public Distinguishsfz:boolean=false;  //身份证弹窗判断
  public Ocrgif:boolean=false;  //动图

  public judgeA = true; //机构
  public judgeB = false;  //企业
  public judgeC = true;  //机构图

  public bizLicenceRegisteredNo:any=''; //统一社会信用代码
  public bizLicenceRegisteredNoAccuracy:any=''; //统一社会信用代码
  public bizLicenceName:any=''; //名称
  public bizLicenceNameAccuracy:any=''; //
  public bizLicenceLegalName:any=''; //法定代表人
  public bizLicenceLegalNameAccuracy:any=''; //
  public bizLicenceFoundedDate:any=''; //成立日期
  public bizLicenceFoundedDateAccuracy:any=''; //成立日期清晰度
  public bizLicenceAddr:any=''; //地址
  public bizLicenceAddrAccuracy:any='';


  public birth:any=''; //生日
  public sex:any=''; // 性别
  public name:any=''; // 名字
  public address:any=''; // 地址
  public id:any=''; // 身份证号码
  public nation:any=''; // 籍贯
  public birthAccuracy:any=''; //准确度 0低 1高
  public sexAccuracy:any=''; //准确度 0低 1高
  public nameAccuracy:any=''; //准确度 0低 1高
  public addressAccuracy:any=''; //准确度 0低 1高
  public idAccuracy:any=''; //准确度 0低 1高
  public nationAccuracy:any=''; //准确度 0低 1高

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
        base64Image:url,
        ocrGenre:'BIZLICENCE',
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
          if(data.data.ocrInfo!=null&&data.data.ocrInfo!=''&&data.data.ocrInfo!='undefined'){
          if(data.data.ocrInfo.bizLicenceRegisteredNoMap!=null&&data.data.ocrInfo.bizLicenceRegisteredNoMap!=''&&data.data.ocrInfo.bizLicenceRegisteredNoMap!='undefined'){if(data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo!=null&&data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo!=''&&data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo!='undefined'){this.bizLicenceRegisteredNo=data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo;}if(data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy!='undefined'){this.bizLicenceRegisteredNoAccuracy=data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy;}}//统一社会信用代码
          if(data.data.ocrInfo.bizLicenceNameMap!=null&&data.data.ocrInfo.bizLicenceNameMap!=''&&data.data.ocrInfo.bizLicenceNameMap!='undefined'){if(data.data.ocrInfo.bizLicenceNameMap.bizLicenceName!=null&&data.data.ocrInfo.bizLicenceNameMap.bizLicenceName!=''&&data.data.ocrInfo.bizLicenceNameMap.bizLicenceName!='undefined'){this.bizLicenceName=data.data.ocrInfo.bizLicenceNameMap.bizLicenceName;}if(data.data.ocrInfo.bizLicenceNameMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceNameMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceNameMap.Accuracy!='undefined'){this.bizLicenceNameAccuracy=data.data.ocrInfo.bizLicenceNameMap.Accuracy;}}  //名称
          if(data.data.ocrInfo.bizLicenceLegalNameMap!=null&&data.data.ocrInfo.bizLicenceLegalNameMap!=''&&data.data.ocrInfo.bizLicenceLegalNameMap!='undefined'){if(data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName!=null&&data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName!=''&&data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName!='undefined'){this.bizLicenceLegalName=data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName;}if(data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy!='undefined'){this.bizLicenceLegalNameAccuracy=data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy;}}  //法定代表人
          if(data.data.ocrInfo.bizLicenceFoundedDateMap!=null&&data.data.ocrInfo.bizLicenceFoundedDateMap!=''&&data.data.ocrInfo.bizLicenceFoundedDateMap!='undefined'){if(data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate!=null&&data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate!=''&&data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate!='undefined'){this.bizLicenceFoundedDate=data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate;}if(data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy!='undefined'){this.bizLicenceFoundedDateAccuracy=data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy;}}       //成立日期
          if(data.data.ocrInfo.bizLicenceAddrMap!=null&&data.data.ocrInfo.bizLicenceAddrMap!=''&&data.data.ocrInfo.bizLicenceAddrMap!='undefined'){if(data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr!=null&&data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr!='undefined'&&data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr!=''){this.bizLicenceAddr=data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr}if(data.data.ocrInfo.bizLicenceAddrMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceAddrMap.Accuracy!='undefined'&&data.data.ocrInfo.bizLicenceAddrMap.Accuracy!=''){this.bizLicenceAddrAccuracy=data.data.ocrInfo.bizLicenceAddrMap.Accuracy}}       //地址

          if (this.bizLicenceRegisteredNoAccuracy==1&&this.bizLicenceNameAccuracy==1&&this.bizLicenceAddrAccuracy==1&&this.bizLicenceFoundedDateAccuracy==1&&this.bizLicenceFoundedDateAccuracy==1&&this.bizLicenceRegisteredNo!='undefined'&&this.bizLicenceName!='undefined'&&this.bizLicenceLegalName!='undefined'&&this.bizLicenceFoundedDate!='undefined'&&this.bizLicenceAddr!='undefined'&&this.bizLicenceRegisteredNo != null && this.bizLicenceRegisteredNo != '' && this.bizLicenceName != null && this.bizLicenceName != '' && this.bizLicenceLegalName != null && this.bizLicenceLegalName != '' && this.bizLicenceFoundedDate != null && this.bizLicenceFoundedDate != ''&&this.bizLicenceAddr!=null&&this.bizLicenceAddr!='') {
/*            this.navCtrl.push(OpenaccountPage, {
              //TEMP_START: this.discount.beginDate,
            });*/
            loading.dismiss();
          } else {
            this.isMask = true;
            this.Distinguish = true;
            this.CameraRoll = false;
            this.Distinguishyyzz = true;
            this.Distinguishsfz = false;
            loading.dismiss();
          }
            loading.dismiss();
          }
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
        base64Image:url,
        ocrGenre:'IDCARD',
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
          if(data.data.ocrInfo!=null&&data.data.ocrInfo!=''&&data.data.ocrInfo!='undefined') {
            if (data.data.ocrInfo.birthMap != null && data.data.ocrInfo.birthMap != '' && data.data.ocrInfo.birthMap != 'undefined') {
              if (data.data.ocrInfo.birthMap.birth != null && data.data.ocrInfo.birthMap.birth != '' && data.data.ocrInfo.birthMap.birth != 'undefined') {
                this.birth = data.data.ocrInfo.birthMap.birth;
              }
              if (data.data.ocrInfo.birthMap.Accuracy != null && data.data.ocrInfo.birthMap.Accuracy != '' && data.data.ocrInfo.birthMap.Accuracy != 'undefined') {
                this.birthAccuracy = data.data.ocrInfo.birthMap.Accuracy;
              }
            }//生日
            if (data.data.ocrInfo.sexMap != null && data.data.ocrInfo.sexMap != '' && data.data.ocrInfo.sexMap != 'undefined') {
              if (data.data.ocrInfo.sexMap.sex != null && data.data.ocrInfo.sexMap.sex != '' && data.data.ocrInfo.sexMap.sex != 'undefined') {
                this.sex = data.data.ocrInfo.sexMap.sex;
              }
              if (data.data.ocrInfo.sexMap.Accuracy != null && data.data.ocrInfo.sexMap.Accuracy != '' && data.data.ocrInfo.sexMap.Accuracy != 'undefined') {
                this.sexAccuracy = data.data.ocrInfo.sexMap.Accuracy;
              }
            }//性别
            if (data.data.ocrInfo.nameMap != null && data.data.ocrInfo.nameMap != '' && data.data.ocrInfo.nameMap != 'undefined') {
              if (data.data.ocrInfo.nameMap.name != null && data.data.ocrInfo.nameMap.name != '' && data.data.ocrInfo.nameMap.name != 'undefined') {
                this.name = data.data.ocrInfo.nameMap.name;
              }
              if (data.data.ocrInfo.nameMap.Accuracy != null && data.data.ocrInfo.nameMap.Accuracy != '' && data.data.ocrInfo.nameMap.Accuracy != 'undefined') {
                this.nameAccuracy = data.data.ocrInfo.nameMap.Accuracy;
              }
            }//名字
            if (data.data.ocrInfo.addressMap != null && data.data.ocrInfo.addressMap != '' && data.data.ocrInfo.addressMap != 'undefined') {
              if (data.data.ocrInfo.addressMap.address != null && data.data.ocrInfo.addressMap.address != '' && data.data.ocrInfo.addressMap.address != 'undefined') {
                this.address = data.data.ocrInfo.addressMap.address;
              }
              if (data.data.ocrInfo.addressMap.Accuracy != null && data.data.ocrInfo.addressMap.Accuracy != '' && data.data.ocrInfo.addressMap.Accuracy != 'undefined') {
                this.addressAccuracy = data.data.ocrInfo.addressMap.Accuracy;
              }
            }//地址
            if (data.data.ocrInfo.idMap != null && data.data.ocrInfo.idMap != '' && data.data.ocrInfo.idMap != 'undefined') {
              if (data.data.ocrInfo.idMap.id != null && data.data.ocrInfo.idMap.id != '' && data.data.ocrInfo.idMap.id != 'undefined') {
                this.id = data.data.ocrInfo.idMap.id;
              }
              if (data.data.ocrInfo.idMap.Accuracy != null && data.data.ocrInfo.idMap.Accuracy != '' && data.data.ocrInfo.idMap.Accuracy != 'undefined') {
                this.idAccuracy = data.data.ocrInfo.idMap.Accuracy;
              }
            }//身份证号
            if (data.data.ocrInfo.nationMap != null && data.data.ocrInfo.nationMap != '' && data.data.ocrInfo.nationMap != 'undefined') {
              if (data.data.ocrInfo.nationMap.nation != null && data.data.ocrInfo.nationMap.nation != '' && data.data.ocrInfo.nationMap.nation != 'undefined') {
                this.nation = data.data.ocrInfo.nationMap.nation;
              }
              if (data.data.ocrInfo.nationMap.Accuracy != null && data.data.ocrInfo.nationMap.Accuracy != '' && data.data.ocrInfo.nationMap.Accuracy != 'undefined') {
                this.nationAccuracy = data.data.ocrInfo.nationMap.Accuracy;
              }
            }//籍贯

            if (this.nameAccuracy == 1 && this.idAccuracy == 1 && this.name != 'undefined' && this.id != 'undefined' && this.name != null && this.name != ''&& this.id != null && this.id != '' ) {
/*              this.navCtrl.push(OpenaccountPage, {
                //TEMP_START: this.discount.beginDate,
              });*/
              loading.dismiss();
            } else {
              this.isMask = true;
              this.Distinguish = true;
              this.CameraRoll = false;
              this.Distinguishyyzz = false;
              this.Distinguishsfz = true;
              loading.dismiss();
            }
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
        base64Image:url,
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
/*    if(this.selection==2){
      this.findPicB(options);
    }*/
/*    if(this.selection==3){
      this.findPicC(options);
    }*/
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
/*    if(this.selection==2){
      this.findPicB(options);
    }*/
/*    if(this.selection==3){
      this.findPicC(options);
    }*/
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
    this.CameraRoll = true;
    this.Distinguish = false;
  }
  closeMask(){
    this.isMask = false;
    this.CameraRoll = false;
    this.Distinguish = false;
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
    if(this.openaccount.positive == null || this.openaccount.positive == ''){
      this.apiSev.itip("点击上传身份证正面");
      return;
    }
    if(this.openaccount.sideitive == null || this.openaccount.sideitive == ''){
      this.apiSev.itip("点击上传身份证反面");
      return;
    }

    this.navCtrl.push(OpenaccountPage,{
      ORG_TYPE:this.orgType,
      ME_TYPE:this.meType,
      OCROPEN:{
        bizLicenceName:this.bizLicenceName,
        bizLicenceRegisteredNo:this.bizLicenceRegisteredNo,
        bizLicenceLegalName:this.bizLicenceLegalName,
        bizLicenceFoundedDate:this.bizLicenceFoundedDate,
        bizLicenceAddr:this.bizLicenceAddr,
        birth:this.birth,
        sex:this.sex,
        name:this.name,
        address:this.address,
        id:this.id,
        nation:this.nation,
        positives:this.openaccount.positives,
        sideitives:this.openaccount.sideitives,
        businessss:this.openaccount.businessss,
      }
    });
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
