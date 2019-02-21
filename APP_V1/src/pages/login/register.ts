import { Component } from '@angular/core';
import { NavController, ViewController,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Infomemo } from '../login/infomemo';
import { Md5 } from 'ts-md5/dist/md5';
import { TabsPage } from '../tabs/tabs';
import { TabsPageB } from '../tabs/tabsB';
import { Selrole } from '../login/selrole';
import { JPush } from 'ionic3-jpush';

@Component({
  selector: 'page-register',
  templateUrl: 'register.html'
})
export class Register {
  public reqister:any = {
    mobile:'',  //手机号码
    code:'',    //验证码
    pwd:'',     //密码
    confirmpwd:'',  //确认密码
    recommendpeople:''  //推荐人
  };
  public infomemo = true;
  public btnText = '获取验证码';
  public isCodeDisabled = true;
  public memberId:any;
  public registrationId:any;

  public datetime = '';


  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService, public modalCtrl:ModalController,public jPush: JPush) {

    this.apiSev.api("get", "app/timestamp/", (res) => {
      this.datetime=res[0].time;
    }, (error) => {},2000,{});

  }


  ionViewDidEnter(){
  }

  close (e) {
  	this.viewCtrl.dismiss();
    e.preventDefault();
  }

   checkInfo () {
    if(this.infomemo){
      this.infomemo = false;
    }else{
      this.infomemo = true;
    }
  }

  checkPhone () {
    if(this.reqister.mobile.length == 11){
      this.isCodeDisabled = false;
    }else{
      this.isCodeDisabled = true;
    }
  }

  showinfomemo(){
    this.navCtrl.push(Infomemo);
  }

  //输入错误提示
  login(){
    if(this.reqister.mobile==''){
      this.apiSev.itip("手机号码不能为空");
      return;
    }else if(this.reqister.mobile.length!=11){
      this.apiSev.itip("请输入11位有效的手机号码！");
      return;
    }
    if(this.reqister.code =='' || this.reqister.code.length != 4){
      this.apiSev.itip("验证码输入有误");
      return;
    }
    if(this.reqister.pwd=='' || this.reqister.confirmpwd==''){
      this.apiSev.itip("密码不能为空");
      return;
    }
    if(this.reqister.pwd.length < 6 || this.reqister.confirmpwd.length > 11){
      this.apiSev.itip("密码长度不正确");
      return;
    }
    if(this.reqister.pwd!=this.reqister.confirmpwd){
      this.apiSev.itip("两次密码不一致");
      return;
    }

    let timestamp= this.datetime + apiService.getQuDao();
    let data:any={
      mobile:this.reqister.mobile,
      code:this.reqister.code,
      pwd:this.reqister.pwd,
      recommendpeople:this.reqister.recommendpeople,
      timestamp:Md5.hashStr(timestamp)
    };
    this.apiSev.api("post", "app/register/new/", (res) => {
      if (res[0].response == 'success') {
        this.apiSev.itip("恭喜您注册成功");
        this.apiSev.loginpost((res) => {
          if (res.sign=="INDEX"){
            this.memberId = res.data.data.id;
            this.Deviceidentification();
            this.storage.set('userInfo', res.data.data);
            this.storage.set('ORGID', res.data.data.orgId);//
            this.storage.set('lgtoken', res.token);
            this.storage.get('role').then((res2)=>{
              if (res2 == "baojia") {
                this.navCtrl.push(TabsPage);
              }else if(res2 == "chupiao"){
                this.navCtrl.push(TabsPageB);
              }
              else{
                let profileModal = this.modalCtrl.create(Selrole);
                profileModal.present();
              }
            })
          }else {
            this.apiSev.itip('用户名密码错误');
          }

        }, (error) => {
          this.apiSev.itip('登录失败');
        }, this.reqister.mobile , Md5.hashStr(this.reqister.pwd),'','');


        return;
      } else {
        this.apiSev.itip(res[0].msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);

  }

  getCode(e){
    if(this.reqister.mobile==''){
      this.apiSev.itip("手机号码不能为空");
      return;
    }else if(this.reqister.mobile.length!=11){
      this.apiSev.itip("请输入11位有效的手机号码！");
      return;
    }

    this.apiSev.api("get", "app/sendcode/?mobile="+this.reqister.mobile+"&flag=reg", (res) => {
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
          this.apiSev.itip('手机号已经存在，可以尝试重置密码');
        }
      }, (error) => {},1,{}
    );
    e.preventDefault();
  }

  Deviceidentification(){
    this.jPush.getRegistrationID().then(res => {
      this.registrationId=res;
      let data:any={
        memberId:this.memberId,
        registrationId:this.registrationId,
      };
      this.apiSev.api("newpost", "member/registrationid", (res) => {
        let data = res.data;
        if (data.response == 'success') {
        }
      }, (error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 6100, data);
    });
  }

}
