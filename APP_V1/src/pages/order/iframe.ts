import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-iframe',
  templateUrl: 'iframe.html'
})
export class Iframe {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};
  constructor(public storage: Storage,public navCtrl: NavController,public params: NavParams, public viewCtrl: ViewController,public sanitizer: DomSanitizer) {
 
	 let url=this.params.get('url'); //'https://bbs.utiexian.com/';
	 this.browser.url = url;
	 this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);


  }


  ionViewDidEnter(){
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
