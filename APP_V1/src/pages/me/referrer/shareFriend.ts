import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
//import { Clipboard } from '@ionic-native/clipboard';
declare var Wechat:any;


@Component({
  selector: 'page-shareFriend',
  templateUrl: 'shareFriend.html'
})

export class ShareFriendPage {
  public myInvitationCode;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public params : NavParams) {//,public clipboard: Clipboard
    this.myInvitationCode = this.params.get("MYCODE");
  }
  //复制到剪切板  暂停使用
  // copyLink(){
  //   this.clipboard.copy(this.apiSev.HONGBAOURL+'/app/register.htm?code='+this.myInvitationCode);
  //   this.clipboard.paste().then(
  //     (resolve: string) => {
  //       if(resolve!=''){
  //         this.apiSev.itip('已复制到剪切板');
  //       }
  //     },
  //     (reject: string) => {
  //
  //     }
  //   );
  // }

  /**
   * 分享到朋友圈
   */
  shareWxSession(e){
    var that = this;
    Wechat.isInstalled(function (installed) {
      if(!installed){
        this.apiSev.itip("报告主人,您可能没有这个微信程序！");
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
          scene: Wechat.Scene.TIMELINE   // share to Timeline
        }, function () {
          that.apiSev.itip("分享成功");
        }, function (reason) {});
      }
    }, function (reason) {});
    e.preventDefault();
  }

  /**
   *分享到微信好友
   */
  shareWxTimeLine(e){
    var that = this;
    Wechat.isInstalled(function (installed) {
      if(!installed){
        this.apiSev.itip("报告主人,您可能没有这个微信程序！");
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
        }, function (reason) {});
      }
    }, function (reason) {});
    e.preventDefault();
  }
}
