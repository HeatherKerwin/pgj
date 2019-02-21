import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
import { apiService } from "../../api.service";
import { Buffer } from 'buffer';
@Component({
  selector: 'page-bbs',
  templateUrl: 'bbs.html'
})
export class Bbs {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};
  public mobile = '';
  public memberId='';
  public url:any;
  constructor(public storage: Storage,public apiSev: apiService,public navCtrl: NavController,public params: NavParams, public viewCtrl: ViewController,public sanitizer: DomSanitizer) {
    this.memberId = this.params.get("MEMBERID");
    this.mobile = this.params.get("MOBILE");
    let url = "https://bbs.utiexian.com/?mid=" + this.Basedd(this.apiSev.MyID, this.apiSev.MyPhone);
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
  ionViewDidEnter(){
  }
  close () {
    this.viewCtrl.dismiss();
  }

  //随机数生成
  getItemID(e){
    let len = e;
    let $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890';
    let maxPos = $chars.length;
    let pwd = '';
    for (let i = 0; i < len; i++) {
      pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
  }


  //Base64位加密
  Basedd(mid,mobile){
    let qian = "MID";
    let zhong = "MOBILE";
    let hou = "50965066";
    let temp = qian + mid + zhong + mobile + hou;
    let encodedText = this.getItemID(10)+new Buffer(temp).toString('base64');
    return encodedText
  }

}
