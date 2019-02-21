import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import { ActionSheetController } from 'ionic-angular';
declare var Wechat:any;
@Component({
  selector: 'page-aboutUs',
  templateUrl: 'aboutUs.html'
})

export class AboutUsPage {
  public myInvitationCode;//我的推荐码
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public actionSheetCtrl:ActionSheetController) {
    this.storage.get('userInfo').then((data) => {
      this.myInvitationCode = data.myInvitationCode;
    });
  }

  presentActionSheet(e) {
    var that = this;
    let actionSheet = that.actionSheetCtrl.create({
      title: '分享到',
      buttons: [
        {
          text: '微信好友',
          role: '',
          handler: () => {
            Wechat.isInstalled(function (installed) {
              if(!installed){
                that.apiSev.itip("报告主人,您可能没有这个微信程序！");
              }else{
                Wechat.share({
                  message: {
                    title: "票据管家送现金啦，快来出票",
                    description: "票据管家费心为您提供专业的交易平台，还贴心送你20现金。",
                    thumb: that.apiSev.getImgUrl+'upload/image/20180110/weixin.png',
                    media: {
                      type: Wechat.Type.LINK,
                      webpageUrl: that.apiSev.HONGBAOURL+ "/app/register.htm?code=" + that.myInvitationCode
                    }
                  },
                  scene: Wechat.Scene.SESSION   // share to Timeline
                }, function () {
                  that.apiSev.itip("分享成功");
                }, function (reason) {
                });
              }
            }, function (reason) {

            });
          }
        },{
          text: '朋友圈',
          role: '',
          handler: () => {
            Wechat.isInstalled(function (installed) {
              if(!installed){
                that.apiSev.itip("报告主人,您可能没有这个微信程序！");
              }else{
                Wechat.share({
                  message: {
                    title: "票据管家送现金啦，快来出票",
                    description: "票据管家费心为您提供专业的交易平台，还贴心送你20现金。",
                    thumb:that.apiSev.getImgUrl+'upload/image/20180110/weixin.png',
                    media: {
                      type: Wechat.Type.LINK,
                      webpageUrl: that.apiSev.HONGBAOURL+ "/app/register.htm?code=" + that.myInvitationCode
                    }
                  },
                  scene: Wechat.Scene.TIMELINE   // share to Timeline
                }, function () {
                  this.apiSev.itip("分享成功");
                }, function (reason) {
                });
              }
            }, function (reason) {});

          }
        },{
          text: '取消',
          role: 'cancel',
          handler: () => {
          }
        }
      ]
    });
    actionSheet.present();
    e.preventDefault();
  }


}
