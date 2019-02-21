import { Component } from '@angular/core';
import { NavController, ViewController, NavParams, } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-ClickPage',
  templateUrl: 'ClickPage.html'
})
export class ClickPage {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public sanitizer: DomSanitizer, public params: NavParams) {


			 let url=this.params.get("Click_Page",);       //banner图点击页面;
			 this.browser.url = url;
			 this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);


  }


  ionViewDidEnter(){
  }

  close (e) {
  	this.viewCtrl.dismiss();
    e.preventDefault();
  }

}
