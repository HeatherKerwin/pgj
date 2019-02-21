import { Component } from '@angular/core';
import {NavController, NavParams, ViewController} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Login } from '../login/login';

@Component({
  selector: 'page-getpassword',
  templateUrl: 'getpassword.html'
})
export class Getpassword {
  public user:any = {};
  public btnText = '获取验证码';
  public isCodeDisabled = true;
  public memberId;

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService,public params:NavParams) {
    this.user.username = this.params.get("MOBILE");
    if(this.user.username!=null){
      this.checkPhone ();
    }
  }


  ionViewDidEnter(){
  }

  checkPhone () {
    if(this.user.username.length == 11){
      this.isCodeDisabled = false;
    }else{
      this.isCodeDisabled = true;
    }
  }

  close () {
  	this.viewCtrl.dismiss();
  }

  getCode(){
    let username = this.user.username.match(/^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
    if (username) {
    } else {
      this.apiSev.itip("请输入正确的手机号码");
      return;
    }
    let data = {
      mobile:this.user.username,
      flag:"forget"
    };
    this.apiSev.api("post", "app/sendcode/", (res) => {
        if(res[0].response == 'success'){
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
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {},1,data
    );
  }
  reset(){
    var code = this.user.smsnum;
    var pwd = this.user.password;
    var rePwd = this.user.password1;
    if(code=="" || typeof(code)=="undefined"){
      this.apiSev.itip("验证码不能为空");
      return;
    }
    if(pwd=="" || typeof(pwd)=="undefined"){
      this.apiSev.itip("密码不能为空");
      return;
    }
    if(!pwd.match(/^[a-zA-Z0-9]{6,16}$/g)){
      this.apiSev.itip("请输入正确的密码格式");
      return;
    }
    if(pwd != rePwd){
      this.apiSev.itip("两次密码不一致");
      return;
    }
    let data = {
      mobile:this.user.username,
      memberid:this.memberId,
      code:code,
      pwd:pwd
    };
    this.apiSev.api(
      "post",
      "app/updatepwd/",
      (res) => {
        if(res[0].response=="success"){
          this.apiSev.itip(res[0].msg+"，请使用新密码登录");
          this.navCtrl.push(Login);
        }else{
          this.apiSev.itip(res[0].msg);
        }
      }, (error) => {},1,data
    );
  }
}
