import { Component } from '@angular/core';
import { NavController, ViewController, NavParams, } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-ServicePage',
  templateUrl: 'ServicePage.html'
})
export class ServicePage {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public sanitizer: DomSanitizer, public params: NavParams) {


    let url = 'https://wap.utiexian.com/wap/login/payment';   //协议
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);


  }


  ionViewDidEnter(){
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
