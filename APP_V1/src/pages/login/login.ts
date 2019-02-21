import { Component } from '@angular/core';
import { NavController,ModalController,Platform} from 'ionic-angular';
import { apiService } from "../../api.service";
import { TabsPage } from '../tabs/tabs';
import { TabsPageB } from '../tabs/tabsB';
import { Register } from '../login/register';
import { Infomemo } from '../login/infomemo';
import { Selrole } from '../login/selrole';
import { Getpassword } from '../login/getpassword';
import { Storage } from '@ionic/storage';
import { Md5 } from 'ts-md5/dist/md5';
import { QQSDK,QQShareOptions} from '@ionic-native/qqsdk';
import {BindMobilePage} from "./bindMobile";
import { JPush } from 'ionic3-jpush';
import {WechatChenyu} from "wechat-chenyu";

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
  providers: [ apiService ],
})
export class Login {

  pet: string = "kittens";
  public user:any = {};
  public infomemo = true;
  public btnText = '获取验证码';
  public isCodeDisabled = true;
  public datetime = '';
  public memberId:any;
  public registrationId:any;
  public isQQflag=true;
  public isWeChatflag=true;

  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService, public modalCtrl:ModalController,public qq:QQSDK,public jPush: JPush,public platform: Platform,private wechatChenyu: WechatChenyu) {

    this.qq.checkClientInstalled(this.clientOptions)
      .then(() => {
      })
      .catch((error) => {
        this.isQQflag=false;
      });
    this.wechatChenyu.isInstalled()
      .then((data) => {
        if(data!=1){
          this.isWeChatflag = false;
        }
      });
    this.apiSev.api("get", "app/timestamp/", (res) => {
        this.datetime=res[0].time;
    }, (error) => {},2000,{});
  }

  public clientOptions: QQShareOptions = {
    client: this.qq.ClientType.QQ,
  };

  ionViewDidEnter(){

  }

    getCode (e) {

	 let data ={
		 mobile:this.user.mobile
	 }
     this.apiSev.api("newget", "smscode", (res) => {
       if(res.response == 'success'){
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

      e.preventDefault();
    }

  checkPhone () {
    if(this.user.mobile.length == 11){
      this.isCodeDisabled = false;
    }else{
      this.isCodeDisabled = true;
    }
  }

   checkInfo () {
    if(this.infomemo){
      this.infomemo = false;
    }else{
      this.infomemo = true;
    }
  }

    login (e) {
	    if (!this.infomemo)
			this.apiSev.itip('请先同意票管家用户协议');
        else {

			if (this.pet=='kittens')
			{
				//快速登录
				let timestamp= this.datetime + apiService.getQuDao();

				if (this.user.code==undefined) {
					this.apiSev.itip('请输入验证码');
				}
				else {
					this.apiSev.loginpost((res) => {

					    if (res.sign=="INDEX")
						{
              this.memberId = res.data.data.id;
              this.Deviceidentification();
              window.localStorage.setItem('MEMBER_ID',res.data.data.id);
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
						}
						else
						{
							this.apiSev.itip('验证码错误');
						}

					}, (error) => {
					  this.apiSev.itip('登录失败');
					}, this.user.mobile,'','2',this.user.code
					)


				}








			}
			else
			{

                    if (!this.user.password){
						this.apiSev.itip('请输入密码！');
						return;
		            }


		  			this.apiSev.loginpost((res) => {

					    if (res.sign=="INDEX")
						{
              this.memberId = res.data.data.id;
              this.Deviceidentification();
              window.localStorage.setItem('MEMBER_ID',res.data.data.id);
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
						}
						else
						{
							this.apiSev.itip('用户名密码错误');
						}

					}, (error) => {
					  this.apiSev.itip('登录失败');
					}, this.user.mobile,Md5.hashStr(this.user.password),'1',''
					)



			}

        }

    }

  register(e){
    let profileModal = this.modalCtrl.create(Register);
     profileModal.present();
  }

  showinfomemo(e){
	  this.navCtrl.push(Infomemo);
    e.preventDefault();
  }

  getpass(e){
	  this.navCtrl.push(Getpassword);
    e.preventDefault();
  }

  ngOnInit() {
    console.log('ngOnInit');
  }

  ngOnDestroy(){
    console.log('ngOnDestroy')
  }

  QQLogin(e){
    var that = this;

    that.qq.ssoLogin(that.clientOptions)
      .then(result => {
        let data = {
          openId:result.userid,
          token:result.access_token,
          type:2
        }
        that.apiSev.api("post", "/app/loginthird/", (res) => {
          if(res[0].response == 'success'){
            that.memberId = res[0].msg.id;
            that.Deviceidentification();
            window.localStorage.setItem('MEMBER_ID',res[0].msg.id);
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
            }, (error) => {},6100,{});
          }else{
            this.navCtrl.push(BindMobilePage,
            {
              TYPE:2,
              OPENID:result.userid,
              TOKEN:result.access_token,
              TIME:that.datetime
            });
          }
        }, (error) => {},1,data);

      })
      .catch(error => {
      });
    e.preventDefault();
  }



  WeChatLogin(e){
    e.preventDefault();
    var that = this;
    //微信登录
    var str = null;
    that.wechatChenyu.auth("snsapi_userinfo", "_" + (+new Date()))
      .then((data) => {

        let code = data.code;
        that.apiSev.api(
          "getLink",
          "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+that.apiSev.appId+"&secret="+that.apiSev.appSecret+"&code="+code+"&grant_type=authorization_code",
          (res) => {

            let data = {
              openId:res.openid,
              token:res.access_token,
              type:1
            }
            that.apiSev.api("post", "/app/loginthird/", (res1) => {
              if(res1[0].response == 'success'){
                that.apiSev.itip('微信授权成功');
                that.memberId = res1[0].msg.id;
                that.Deviceidentification();
                //window.localStorage.setItem('MEMBER_ID',res[0].msg.id);
                let data = {
                  mobile:res1[0].msg.mobile,
                  id:res1[0].msg.id,
                  myInvitationCode:res1[0].msg.myInvitationCode,
                  username:res1[0].msg.username
                };
                that.storage.set('userInfo', data);
                // that.storage.set('ORGID', res1[1]);//
                that.apiSev.api("newget", "index", (res) => {
                  that.apiSev.itip('登陆成功');
                  that.storage.set('lgtoken', res.token);
                  that.storage.get('role').then((res2)=>{
                    if (res2 == "baojia") {
                      that.navCtrl.push(TabsPage);
                    }else if(res2 == "chupiao"){
                      that.navCtrl.push(TabsPageB);
                    }else{
                      let profileModal = that.modalCtrl.create(Selrole);
                      profileModal.present();
                    }
                  })
                }, (error) => {},6100,{});
              }else{
                that.navCtrl.push(BindMobilePage,
                  {
                    TYPE:1,
                    OPENID:res.openid,
                    TOKEN:res.access_token,
                    TIME:that.datetime
                  });
            }
            }, (error) => {},1,data);
          }, (error) => {},1000,{});
      }).catch(err => {
    });

  }



  timestamp() {
    this.apiSev.api("post", "/app/timestamp/", (res) => {

    }, (error) => {},2000,{});
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
      }, 500, data);
    });
  }
}


