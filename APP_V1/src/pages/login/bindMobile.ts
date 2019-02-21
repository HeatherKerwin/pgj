import { Component } from '@angular/core';
import {ModalController, NavController, NavParams, ViewController} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Md5 } from 'ts-md5/dist/md5';
import {TabsPage} from "../tabs/tabs";
import {TabsPageB} from "../tabs/tabsB";
import {Selrole} from "./selrole";

@Component({
  selector: 'page-bindMobile',
  templateUrl: 'bindMobile.html'
})
export class BindMobilePage {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = false;

  constructor(public storage: Storage,public navCtrl: NavController,public modalCtrl:ModalController, public viewCtrl: ViewController,public apiSev: apiService,public params:NavParams) {

  }


  ionViewDidEnter(){

  }

  close (e) {
    this.viewCtrl.dismiss();
    e.preventDefault();
  }

  getCode(e){
    if(this.user.username == ""||this.user.username == null){
      this.apiSev.itip("请输入手机号码");
      return;
    }
    if(this.user.username.length != 11){
      this.apiSev.itip("请输入正确的手机号码");
      return;
    }
    let second = 60;
    let timePromise = setInterval(()=>{
      if(second<=0){
        clearInterval(timePromise);
        timePromise = undefined;
        second = 60;
        this.btnText = "重发验证码";
        this.isCodeDisabled = false;
      }else{
        second--;
        this.btnText = second + "秒后可重发";
        this.isCodeDisabled = true;
      }
    },1000,100);

    let data = {
      mobile:this.user.username,
      flag:'forget'
    };
    this.apiSev.api("post", "app/sendcode/", (res) => {
        if(res[0].response == 'success'){

        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {},1,data
    );
    e.preventDefault();
  }

  bind(e) {
    var that = this;
    var mobile = that.user.username;
    var code = that.user.smsnum;
    //验证
    if (code.length != 4) {
      that.apiSev.itip('验证码输入格式不正确');
      return;
    }
    let data = {
      mobile:mobile,
      code:Md5.hashStr(code),
      type:that.params.get("TYPE"),
      openid:that.params.get("OPENID"),
      token:that.params.get("TOKEN"),
      timestamp:Md5.hashStr(that.params.get("TIME")+apiService.getQuDao())
    };
    that.apiSev.api("post", "/app/quicklogin/new", (res) => {

      if(res[0].response == 'success'){
        let data = {
          mobile:res[0].msg.mobile,
          id:res[0].msg.id,
          myInvitationCode:res[0].msg.myInvitationCode,
          username:res[0].msg.username
        };
        that.storage.set('userInfo', data);
        that.apiSev.api("newget", "index", (res) => {
          that.storage.set('lgtoken', res.token);
          that.storage.get('role').then((res2)=>{
            if (res2 == "baojia") {
              that.navCtrl.push(TabsPage);
            }else if(res2 == "chupiao"){
              that.navCtrl.push(TabsPageB);
            }
            else{
              let profileModal = that.modalCtrl.create(Selrole);
              profileModal.present();
            }
          })
        }, (error) => {},2000,{});
      }else{
        this.apiSev.itip(res[0].msg);
      }
    }, (error) => {},1,data);
    e.preventDefault();
  }
}
