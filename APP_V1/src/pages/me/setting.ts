import { Component } from '@angular/core';
import {NavController, Platform, AlertController} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Getpassword } from '../login/getpassword';
import { Login } from '../login/login';
import { Infomemo } from '../login/infomemo';
import { AboutUsPage } from '../me/aboutUs/aboutUs';
import { InAppBrowser } from '@ionic-native/in-app-browser';

@Component({
  selector: 'page-setting',
  templateUrl: 'setting.html'
})

export class SettingPage {
  public isAccept;
  public versionInfo;
  public memberId;

  public appVersion = '0.0.0';
  public updateFlag = false;
  public btnText = '重启进入新版本';
  public appDes = '';
  public appUrl = '';
  public backDropShow = false;
  constructor(public storage: Storage,public iab: InAppBrowser,private alertCtrl: AlertController,public navCtrl: NavController,public apiSev: apiService,private platform:Platform) {
    this.getVersionInfo();
    this.getAppVersion();
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.loadData();
    });
  }

  ToResetPwd1(e){
    var mobile;
    this.storage.get('userInfo').then((data)=>{
      mobile=data.mobile;
      this.navCtrl.push(Getpassword,{MOBILE:mobile});
    });
    e.preventDefault();
  }

  exit(e){
    this.storage.set('userInfo', "");
    this.storage.set('ORGID', "");
    this.storage.set('lgtoken', "");
    window.localStorage.setItem('memberId',"");
    this.navCtrl.push(Login);
    e.preventDefault();
    this.apiSev.MyID='';
    this.apiSev.MyPhone='';
    this.apiSev.ALIPAY={};
    this.apiSev.BANKPAY={};
  }

  ToInfoMemo(e){
    this.navCtrl.push(Infomemo);
    e.preventDefault();
  }
  //是否接收推送消息
  notify(event){
    // localStorage.setItem("ISACCEPT",event.checked);
    let receivePush = "T";
    if(event.checked){
      receivePush = "T";
    }else{
      receivePush = 'F';
    }
    let data = {
      memberId:this.memberId,
      receivePush:receivePush
    };
    this.apiSev.api("newpost", "member/update/receivepush", (res) => {

    },(error) => {

    }, 500,data);
  }

  ToAboutUsPage(e){
    this.navCtrl.push(AboutUsPage);
    e.preventDefault();
  }

  getVersionInfo(){
    if(this.platform.is("android")){
      this.versionInfo = this.apiSev.getVersion();
    }
    if(this.platform.is("ios")){
      this.versionInfo = this.apiSev.getVersionIos();
    }
  }

  loadData(){
    let data = {
      id:this.memberId
    };
    this.apiSev.api("newpost", "/member/get", (res) => {
      let data = res.data;
      if(data.response=="success"){
        if(data.data.receivePush == "F"){
          this.isAccept=false;
        }else{
          this.isAccept=true;
        }
      }
    },(error) => {
    }, 500,data);
  }


  getAppVersion () {

    this.apiSev.api("get", "app/version/", (res) => {
      console.log(res[0]);
      if(res[0].response=='success'){
        if(this.platform.is('ios')){

          if (res[0].msg.iosflag=="1") {
            this.updateFlag=true ;
            this.btnText='确定更新';
          }
          this.appUrl = 'https://itunes.apple.com/cn/app/id1001869394';
          this.appVersion = res[0].msg.iosversion;
          this.appDes = res[0].msg.iosdesc;
          if(this.apiSev.getVersionIos() < res[0].msg.iosversion){
            let elements = document.querySelectorAll(".tabbar");
            if(elements != null) {
              Object.keys(elements).map((key) => {
                elements[key].style.display ='none';
              });
            }
            this.backDropShow = true;
          }else{
            this.backDropShow = false;
          }
        }else if(this.platform.is('android')){

          if (res[0].msg.androidflag=="1") {
            this.updateFlag=true ;
            this.btnText='确定更新';
          }
          this.appUrl = 'http://static.utiexian.com/andriod/piaojuguanjia.apk';
          this.appVersion = res[0].msg.androidversion;
          this.appDes = res[0].msg.androiddesc;
          if(this.apiSev.getVersion() < res[0].msg.androidversion){
            let elements = document.querySelectorAll(".tabbar");
            if(elements != null) {
              Object.keys(elements).map((key) => {
                elements[key].style.display ='none';
              });
            }
            this.backDropShow = true;
          }else{
            this.backDropShow = false;
          }
        }
      }
    }, (error) => {
    },3000,{})
  }


  //反馈信息
  versionData(){
    if(this.versionInfo==this.appVersion){
      this.apiSev.itip("目前您已是最新版本");
      return;
    }

    if(this.versionInfo!=this.appVersion){
      let alert = this.alertCtrl.create({
        message: "目前最新版本号为："+this.appVersion,
        buttons: [
          {
            text: '取消',
            role: 'cancel',
            handler: () => {

            }
          },
          {
            text: '更新',
            handler: () => {
              this.iab.create(this.appUrl, '_system');
            }
          }
        ]
      });
      alert.present();
    }
  }

}
