import {Component} from '@angular/core';
import {NavController, ViewController,LoadingController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {jdLicense2Page} from "./jdLicense2";
import { Camera, CameraOptions } from '@ionic-native/camera';

@Component({
  selector: 'page-jdLicense1',
  templateUrl: 'jdLicense1.html'
})


export class jdLicense1Page {

  public messagecontent: '';
  public memberId;

  public jd: any = {};


  //长期期限
  public cqterm:boolean=false;

  //固定期限
  public gdterm:boolean=true;

  public isMask=false;//不显示上传图片
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗
  public isUpload:any=true;
  public isPicpath:any=false;
  public isUploadB:any=true;
  public isPicpathB:any=false;
  //正面或反面
  public zfmian:any='A';




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


  //京东参数
  public lepName:any='';  //法人姓名
  public lepCardNo:any='';  //法人证件号
  public lepValidityEnd:any; //截止日期

  public blicTrcValidityEndmin:any=new Date().toISOString();  //最小日期
  public blicTrcValidityEndmax:any=new Date(new Date().getTime()+51*360*24*3600*1000).toISOString();  //最大日期


  //京东传值
  public jdData:any={};



  constructor(public storage: Storage,public params: NavParams, public loadingCtrl: LoadingController,public camera: Camera,public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.jdData = this.params.get("jdData");
  }

  //去往上传银行卡
  jdlicense2() {
    this.jdData.lepCardType='ID';

    if (this.jd.picpath == null || this.jd.picpath == '' || this.jd.picpath == 'undefined') {
      this.apiSev.itip("请上传身份证正面");
      return;
    }
    this.jdData.lepUrlA = this.jd.front;
    //this.jdData.lepUrlA = 'temp/image/20180910/20180910160624_247.jpg';

    if (this.jd.picpathB == null || this.jd.picpathB == '' || this.jd.picpathB == 'undefined') {
      this.apiSev.itip("请上传身份证反面");
      return;
    }
    this.jdData.lepUrlB = this.jd.frontB;
    //this.jdData.lepUrlB = 'temp/image/20180910/20180910160624_247.jpg';

    if (this.lepName == null || this.lepName == '' || this.lepName == 'undefined') {
      this.apiSev.itip("请输入法人代表姓名");
      return;
    }
    this.jdData.lepName = this.lepName;

    if (this.lepCardNo == null || this.lepCardNo == '' || this.lepCardNo == 'undefined') {
      this.apiSev.itip("请输入法人代表身份证号");
      return;
    }
    if (this.lepCardNo.length == 15 || this.lepCardNo.length == 18) {

    } else {
      this.apiSev.itip("请输入15/18位有效身份证号");
      return;
    }
    this.jdData.lepCardNo = this.lepCardNo;

    if (this.gdterm == true) {
      if (this.lepValidityEnd == null || this.lepValidityEnd == '' || this.lepValidityEnd == 'undefined') {
        this.apiSev.itip("请输入身份证上的截止日期");
        return;
      }
      this.jdData.lepLongTerm = false;
      this.jdData.lepValidityEnd = this.lepValidityEnd;
    } else {
      this.jdData.lepLongTerm = true;
      this.jdData.lepValidityEnd = '长期';
    }

    console.log(this.jdData);

    this.navCtrl.push(jdLicense2Page,{
      jdData:this.jdData
    });
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
    this.closeMask();
  }

  //上传身份证正面
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
        ocrGenre:'IDCARD',
        maxKb:2000,
      }
      this.apiSev.api("newpost", "upload/image/quality", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.jd.picpath= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.jd.front=data.data.base64Image;
          this.isUpload = false;
          this.isPicpath = true;
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
                this.lepName = data.data.ocrInfo.nameMap.name;
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
                this.lepCardNo = data.data.ocrInfo.idMap.id;
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

  //上传身份证反面
  findPicB (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
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
