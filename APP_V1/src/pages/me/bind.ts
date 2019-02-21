import { Component } from '@angular/core';
import {NavController,AlertController} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { QQSDK,QQShareOptions} from '@ionic-native/qqsdk';
import {WechatChenyu} from "wechat-chenyu";

@Component({
  selector: 'page-bind',
  templateUrl: 'bind.html'
})

export class BindPage {
  public qqFun = "ToBindQQ()";
  public wxFun = "ToBindWX()";
  public qqStr="绑定";
  public wxStr="绑定";
  public memberId;

  public appId = "wxfcf5788bc89da8db";
  public appSecret = "d0347590863908e5382c06254a7b21e5";
  public isQQflag=true;
  public isWeChatflag=true;



  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public alertCtrl: AlertController,public qq:QQSDK,private wechatChenyu: WechatChenyu) {
    this.loadData();
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
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
  }

  public clientOptions: QQShareOptions = {
    client: this.qq.ClientType.QQ,
  };
  ToBindQQ(){
    var that = this;
    if(that.qqStr == "绑定"){
      that.qq.checkClientInstalled(that.clientOptions)
        .then(() => {
        })
        .catch((error) => {
          that.apiSev.itip("手机上未安装QQ，不能绑定");
        });
      that.qq.ssoLogin(this.clientOptions)
        .then(result => {
          // Success
          let data = {
            type:2,
            openId:result.userid,
            token:result.access_token,
            belong:1,
            memberId:this.memberId,
          }
          that.apiSev.api(
            "post",
            "/app/bind/third/",
            (res1) => {
              that.apiSev.itip("绑定成功");
              that.loadData();
            }, (error) => {that.apiSev.itip('出错');},1000,data);
        })
        .catch(error => {
          that.apiSev.itip('出错'); // Failed
        });
    }else{
      that.unbind(2);
    }
  }

  ToBindWX(){
    var that = this;
    if(that.wxStr == "绑定"){
      this.wechatChenyu.isInstalled()
        .then((data) => {
          if(data!=1){
            that.apiSev.itip("手机上未安装微信，不能绑定");
          }else{
            //安装了微信获取code
            var scope = "snsapi_userinfo",
              state = "_" + (+new Date());
            that.wechatChenyu.auth("snsapi_userinfo", "_" + (+new Date()))
              .then((data) => {
                let code = data.code;
                that.apiSev.api(
                  "getLink",
                  "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+that.appId+"&secret="+that.appSecret+"&code="+code+"&grant_type=authorization_code",
                  (res) => {
                    if(res.openid!=null){
                      let data = {
                        type:1,//微信登录
                        openId:res.openid,
                        token:res.access_token,
                        belong:1,
                        memberId:that.memberId,
                      };
                      that.apiSev.api(
                        "post",
                        "/app/bind/third/",
                        (res1) => {
                          that.apiSev.itip("绑定成功");
                          that.loadData();
                        }, (error) => {that.apiSev.itip('出错');},1000,data);
                    }
                  }, (error) => {},1000,{});
              }, function (reason) {
            });
          }
        });
    }else{
      that.unbind(1);
    }
  }

  unbind(type){
    var title;
    if(type=='2'){
      title="腾讯QQ";
    }else if(type=='1'){
      title="微信";
    }

    this.showConfirm("提示","你确定要解绑"+title+"吗？",type);

  }

  loadData(){
    this.storage.get("userInfo").then((data)=>{
      let data1 = {memberId:data.id};
      this.apiSev.api(
        "post",
        "/app/getbind/",
        (res) => {
          var data = res;
          if (data.response == 'success') {
            if(data.wechat == 1){
              this.wxStr="解绑";
            }else{
              this.wxStr="绑定";
            }
            if(data.qq == 1){
              this.qqStr="解绑";
            }else{
              this.qqStr="绑定";
            }
          }
        }, (error) => {},1,data1);
    });
  }

  showConfirm(title,msg,type) {
    let alert = this.alertCtrl.create({
      title: title,
      message: msg,
      buttons: [
        {
          text: '取消',
          role: 'cancel',
          handler: () => {
          }
        },
        {
          text: '确定',
          handler: () => {
            this.storage.get("userInfo").then((data)=>{
              let data1={
                memberId:data.id,
                belong:'1',
                type:type.toString(),
              }
              this.apiSev.api(
                "post",
                "/app/unbind/third/",
                (res) => {
                  if(res.response == "success"){
                    this.loadData();
                  }
                }, (error) => {},1,data1);
            })
          }
        }
      ]
    });
    alert.present();
  }
}
