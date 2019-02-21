import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-infomemo',
  templateUrl: 'infomemo.html'
})
export class Infomemo {
  public user:any = {};

  public browser:any = {url:'',secUrl: ''};

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService,public sanitizer: DomSanitizer) {

    let url=this.apiSev.getWapUrl()+"wap/member/agreementAPP/page";
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }


  ionViewDidEnter(){
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
