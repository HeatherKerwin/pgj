import { Component } from '@angular/core';
import { NavController, ViewController,LoadingController,AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import {jdLicense1Page} from "./jdLicense1";
import { Camera, CameraOptions } from '@ionic-native/camera';
import {jdbindingPage} from "./jdbinding";

@Component({
  selector: 'page-jdLicense',
  templateUrl: 'jdLicense.html'
})


export class jdLicensePage {

  public messagecontent: '';
  public memberId;

  public jd: any = {};

  //省联动
  public province:any='请选择省';
  public provincelist:any=[];
  //市联动
  public city:any='请选择市';
  public cityslist:any=[];
  public citylist:any=[];

  //长期期限
  public cqterm:boolean=false;

  //固定期限
  public gdterm:boolean=true;

  //是否上传了图片
  public isimg:boolean=false;

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


  //京东
  public blicCompanyName:any='';  //公司名称
  public blicUscc:any='';  //统一社会信用代码
  public blicCardNo:any='';  //注册号
  public blicObaCardNo:any='';  //税字号
  public blicTrcCardNo:any='';  //组织机构代码
  public blicAddress:any='';  //地址
  public blicScope:any='';  //经营范围
  public blicValidityEnd:any; //截止日期
  public blicCardType:any='';      //证件类型
  public blicTrcValidityEndmin:any=new Date().toISOString();  //最小日期
  public blicTrcValidityEndmax:any=new Date(new Date().getTime()+51*360*24*3600*1000).toISOString();  //最大日期
  public blicLongTerm:boolean=false;  //是否长期展示


  public isMask=false;//不显示上传图片
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗
  public isUpload:any=true;
  public isPicpath:any=false;


  constructor(public storage: Storage,public loadingCtrl: LoadingController,public alertCtrl: AlertController,public camera: Camera, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    //获取省份信息
    this.queryprovince();
  }

  //去往上传身份证
  jdLincese1() {
    let jdData:any={};
    jdData.companyType='ENTER';
/*    if (this.jd.picpath == null || this.jd.picpath == '' || this.jd.picpath == 'undefined') {
      this.apiSev.itip("请上传营业执照");
      return;
    }*/
    jdData.blicUrlA = this.jd.front;
    //jdData.blicUrlA = 'temp/image/20180910/20180910160624_247.jpg';

    if (this.blicCompanyName == null || this.blicCompanyName == '' || this.blicCompanyName == 'undefined') {
      this.apiSev.itip("请输入公司名称");
      return;
    }

    jdData.blicCompanyName = this.blicCompanyName;
    jdData.blicCardType = this.blicCardType;


    if (this.blicCardType == 'USC') {
      if (this.blicUscc == null || this.blicUscc == '' || this.blicUscc == 'undefined') {
        this.apiSev.itip("请输入统一社会信用代码");
        return;
      }
      if (this.blicUscc.length != 18) {
        this.apiSev.itip("您必须输入由18位的字母数字组合统一社会信用代码");
        return;
      }
      jdData.blicUscc = this.blicUscc;
    }

    if (this.blicCardType == 'OCI') {
      if (this.blicCardNo == null || this.blicCardNo == '' || this.blicCardNo == 'undefined') {
        this.apiSev.itip("请输入注册号");
        return;
      }
      jdData.blicCardNo = this.blicCardNo;

      if (this.blicObaCardNo == null || this.blicObaCardNo == '' || this.blicObaCardNo == 'undefined') {
        this.apiSev.itip("请输入税字号");
        return;
      }
      jdData.blicObaCardNo = this.blicObaCardNo;

      if (this.blicTrcCardNo == null || this.blicTrcCardNo == '' || this.blicTrcCardNo == 'undefined') {
        this.apiSev.itip("请输入组织机构代码");
        return;
      }
      jdData.blicTrcCardNo = this.blicTrcCardNo;
    }

    if (this.province == '请选择省') {
      this.apiSev.itip("请选择您公司所在省");
      return;
    }
    if (this.city == '请选择市') {
      this.apiSev.itip("请选择您公司所在市");
      return;
    }
    jdData.blicProvince = this.province;
    jdData.blicCity = this.city;

    if (this.blicAddress == null || this.blicAddress == '' || this.blicAddress == 'undefined') {
      this.apiSev.itip("请输入具体门牌号");
      return;
    }
    jdData.blicAddress = this.blicAddress;

    if (this.blicScope == null || this.blicScope == '' || this.blicScope == 'undefined') {
      this.apiSev.itip("请输入经营范围");
      return;
    }
    jdData.blicScope = this.blicScope;

    if (this.gdterm == true) {
      if (this.blicValidityEnd == null || this.blicValidityEnd == '' || this.blicValidityEnd == 'undefined') {
        this.apiSev.itip("请输入营业执照上的截止日期");
        return;
      }
      jdData.blicLongTerm = false;
      jdData.blicValidityEnd = this.blicValidityEnd;
    } else {
      jdData.blicLongTerm = true;
      jdData.blicValidityEnd = '长期';
    }

    console.log(jdData);
    //查询绑定
    this.bindingyesNo(jdData);


/*    this.navCtrl.push(jdLicense1Page,{
      jdData:jdData
    })*/
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
  albumPic() {
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 0,
      targetWidth: 1280,
      targetHeight: 1280
    };
    this.findPic(options);
    this.closeMask();
  }

  //调取相机上传
  cameraPic() {
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 1,
      targetWidth: 1280,
      targetHeight: 1280,
      saveToPhotoAlbum: false,
      correctOrientation: true
    };
    this.findPic(options);
    this.closeMask();
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
        maxKb:2000
      }
      this.apiSev.api("newpost", "upload/image/quality", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.jd.picpath = this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.jd.front=data.data.base64Image;
          this.isUpload = false;
          this.isPicpath = true;
          if(data.data.ocrInfo!=null&&data.data.ocrInfo!=''&&data.data.ocrInfo!='undefined'){
            if(data.data.ocrInfo.bizLicenceRegisteredNoMap!=null&&data.data.ocrInfo.bizLicenceRegisteredNoMap!=''&&data.data.ocrInfo.bizLicenceRegisteredNoMap!='undefined'){if(data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo!=null&&data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo!=''&&data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo!='undefined'){this.blicUscc=data.data.ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo;}if(data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy!='undefined'){this.bizLicenceRegisteredNoAccuracy=data.data.ocrInfo.bizLicenceRegisteredNoMap.Accuracy;}}//统一社会信用代码
            if(data.data.ocrInfo.bizLicenceNameMap!=null&&data.data.ocrInfo.bizLicenceNameMap!=''&&data.data.ocrInfo.bizLicenceNameMap!='undefined'){if(data.data.ocrInfo.bizLicenceNameMap.bizLicenceName!=null&&data.data.ocrInfo.bizLicenceNameMap.bizLicenceName!=''&&data.data.ocrInfo.bizLicenceNameMap.bizLicenceName!='undefined'){this.blicCompanyName=data.data.ocrInfo.bizLicenceNameMap.bizLicenceName;}if(data.data.ocrInfo.bizLicenceNameMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceNameMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceNameMap.Accuracy!='undefined'){this.bizLicenceNameAccuracy=data.data.ocrInfo.bizLicenceNameMap.Accuracy;}}  //名称
            if(data.data.ocrInfo.bizLicenceLegalNameMap!=null&&data.data.ocrInfo.bizLicenceLegalNameMap!=''&&data.data.ocrInfo.bizLicenceLegalNameMap!='undefined'){if(data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName!=null&&data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName!=''&&data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName!='undefined'){this.bizLicenceLegalName=data.data.ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName;}if(data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy!='undefined'){this.bizLicenceLegalNameAccuracy=data.data.ocrInfo.bizLicenceLegalNameMap.Accuracy;}}  //法定代表人
            if(data.data.ocrInfo.bizLicenceFoundedDateMap!=null&&data.data.ocrInfo.bizLicenceFoundedDateMap!=''&&data.data.ocrInfo.bizLicenceFoundedDateMap!='undefined'){if(data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate!=null&&data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate!=''&&data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate!='undefined'){this.bizLicenceFoundedDate=data.data.ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate;}if(data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy!=''&&data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy!='undefined'){this.bizLicenceFoundedDateAccuracy=data.data.ocrInfo.bizLicenceFoundedDateMap.Accuracy;}}       //成立日期
            if(data.data.ocrInfo.bizLicenceAddrMap!=null&&data.data.ocrInfo.bizLicenceAddrMap!=''&&data.data.ocrInfo.bizLicenceAddrMap!='undefined'){if(data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr!=null&&data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr!='undefined'&&data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr!=''){this.blicAddress=data.data.ocrInfo.bizLicenceAddrMap.bizLicenceAddr}if(data.data.ocrInfo.bizLicenceAddrMap.Accuracy!=null&&data.data.ocrInfo.bizLicenceAddrMap.Accuracy!='undefined'&&data.data.ocrInfo.bizLicenceAddrMap.Accuracy!=''){this.bizLicenceAddrAccuracy=data.data.ocrInfo.bizLicenceAddrMap.Accuracy}}       //地址
            if(data.data.ocrInfo.blicScopeMap!=null&&data.data.ocrInfo.blicScopeMap!=''&&data.data.ocrInfo.blicScopeMap!='undefined'){if(data.data.ocrInfo.blicScopeMap.blicScope!=null&&data.data.ocrInfo.blicScopeMap.blicScope!=''&&data.data.ocrInfo.blicScopeMap.blicScope!='undefined'){this.blicScope=data.data.ocrInfo.blicScopeMap.blicScope;}}//统一社会信用代码

            if (this.bizLicenceRegisteredNoAccuracy==1&&this.bizLicenceNameAccuracy==1&&this.bizLicenceAddrAccuracy==1&&this.bizLicenceFoundedDateAccuracy==1&&this.bizLicenceFoundedDateAccuracy==1) {
              /*            this.navCtrl.push(OpenaccountPage, {
                            //TEMP_START: this.discount.beginDate,
                          });*/
              loading.dismiss();
            } else {
              this.isMask = true;
              this.Distinguish = true;
              this.CameraRoll = false;
              loading.dismiss();
            }


            let datas: any = {};
            if(this.blicCardType == 'USC'){
              datas.blicUscc = this.blicUscc;
            } else if(this.blicCardType == 'OCI'){
              datas.blicCardNo = this.blicCardNo;
            }
            this.apiSev.api("newpost", "jdjr/query", (res) => {
              let datas = res.data;
              if (datas.response == 'success') {
                if(datas.data.list!=[]&&datas.data.list!='undefined'&&datas.data.list!=''&&datas.data.list!=null){
                  let jdData = datas.data.list[0];

                  this.navCtrl.push(jdbindingPage, {
                    jdData: jdData
                  })
                }
              }else {
                this.apiSev.itip(datas.msg);
              }
            }, (error) => {
              this.apiSev.itip('操作失败！');
              return;
            }, 500, datas);

            this.isimg=true;
            loading.dismiss();
          }
        }else {
          this.apiSev.itip(data.msg);
          this.isimg=true;
          loading.dismiss();
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
        this.isimg=true;
        loading.dismiss();
      }, 6100,data);
    }, (err) => {
      this.isimg=true;
      loading.dismiss();
    });
  }

  //传图类型
  uploadBtnType(){
    let alert = this.alertCtrl.create({
      title: '证件类型',
      cssClass:'headChoice',
      mode:'ios',
      message: '请选择您上传的证件类型',
      buttons: [
        {
          text: '多证合一',
          handler: () => {
            this.blicCardType='OCI';
            this.uploadBtn();
          }
        },
        {
          text: '统一社会信用代码证',
          handler: () => {
            this.blicCardType='USC';
            this.uploadBtn();
          }
        }
      ]
    });
    alert.present();
  }


//获取省份信息
  queryprovince() {
    let data: any = {};
    this.apiSev.api("post", "app/region", (res) => {
      console.log(res);
      this.provincelist = res.p;
      console.log(this.provincelist);
      this.cityslist = res.c;
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  querycity(){
    let augcode:any;
    let list = this.provincelist;
    if (list.length > 0) {
      for (let i = 0; i < list.length; i++) {
        if (list[i].name == this.province) {
          augcode=list[i].id
        }
      }
    }

    this.citylist = [];
    let objs = this.cityslist;
    if (objs.length > 0) {
      for (let i = 0; i < objs.length; i++) {
        if (objs[i].parentId == augcode) {
          this.citylist.push(objs[i]);
        }
      }
      this.city=this.citylist[0].name
    }
  }


  bindingyesNo(jdData){
    let data: any = {
    };

    if(this.blicCardType == 'USC'){
      data.blicUscc = this.blicUscc;
    } else if(this.blicCardType == 'OCI'){
      data.blicCardNo = this.blicCardNo;
    }

    this.apiSev.api("newpost", "jdjr/query", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.list!=[]&&data.data.list!='undefined'&&data.data.list!=''&&data.data.list!=null){
          let jdDatas = data.data.list[0];
          this.navCtrl.push(jdbindingPage, {
            jdData: jdDatas
          })
        } else {
          this.navCtrl.push(jdLicense1Page, {
            jdData: jdData
          })
        }
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

}
