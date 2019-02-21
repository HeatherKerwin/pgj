import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import { ShareFriendPage} from "./shareFriend";
import { QQSDK,QQShareOptions} from '@ionic-native/qqsdk';
import {WechatChenyu} from "wechat-chenyu";

@Component({
  selector: 'page-referrer',
  templateUrl: 'referrer.html'
})
//推荐人
export class ReferrerPage {
  public show = false;
  public code;
  public myCode;//我的邀请码
  public myCount;//我的邀请人数
  public isReadonly = false;//是否只读
  public memberId;
  public isQQflag=true;
  public isWeChatflag=true;

  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public qq:QQSDK,private wechatChenyu: WechatChenyu) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.getCode();
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

  //保存邀请码
  saveInfo(){
    let code = this.code;
    var reg = /^\d{3,4}$/;
    var data = {
      memberid:this.memberId,
      recommendpeople:code,
      belong:0
    };
    if (reg.test(code)) {
      this.apiSev.api("post", "/app/update", (res) => {
        if(res[0].response == 'success'){

        }else{
          this.apiSev.itip(res[0].msg);
        }
        }, (error) => {},1,{}
      );
    }else{
      this.apiSev.itip("请输入3位或4位数的邀请码");
    }
  }

  //加载推荐码
  getCode() {
    var data = {
      id:this.memberId
    };
    this.apiSev.api("post", "/app/member/get", (res) => {
        let data = res.data;
        if(res.state == "success"){
          this.myCode = data.myInvitationCode;
          this.myCount = data.count;
          if(data.recommendpeople != null && data.recommendpeople != ""){//如果邀请码不为空,只读
            this.code = data.recommendpeople;
            this.isReadonly = true;
          }
        }
        }, (error) => {
      }, 1, data
    );
  }
  //推荐好友得福利
  ToShareFriendPage(){
    this.navCtrl.push(ShareFriendPage,{MYCODE:this.myCode});
  }
}
