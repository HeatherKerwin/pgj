﻿import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { apiService } from "../../../api.service";

@Component({
  selector: 'page-chart',
  templateUrl: 'chart.html'
})
export class ChartPage {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};

  constructor(public apiSev: apiService,public toastCtrl: ToastController, public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public sanitizer: DomSanitizer) {

    //引用wap项目appchart页面
    // let url='http://192.168.1.26:8080/rywap/app/chartApp';
    // let url='https://test.utiexian.com/rywap/app/chart';
    // let url='https://wap.utiexian.com/app/chart';
	
	let url=this.apiSev.getWapUrl()+'/app/chartApp?ow=app';
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);

  }

}
