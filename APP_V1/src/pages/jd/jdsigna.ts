import { Component } from '@angular/core';
import { NavController, ViewController, NavParams, AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
import { OrderDetailNewShowPage } from '../order/orderDetailNewShow';

@Component({
  selector: 'page-jdsigna',
  templateUrl: 'jdsigna.html'
})


export class JdsignaPage {


  public browser:any = {url:'',secUrl: ''};
  public distId = '';
  public item:any = {};
  public OnePrice:any='';//是否有一口价
  public data:any={};//接口传值
  public todoor_price:any='';
  public memberId:any;


  constructor(public storage: Storage, public navCtrl: NavController, public params: NavParams, public alertCtrl: AlertController, public viewCtrl: ViewController, public apiSev: apiService, public sanitizer: DomSanitizer) {
    this.memberId = this.params.get("memberId");
    let url = this.apiSev.getNewUrl() + "jdjr/login/?memberId=" + this.memberId;
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  canceldd() {
    this.navCtrl.pop();
  }

  Save(e) {
    let data: any = {
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "jdjr/login/check", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.viewCtrl.dismiss();
        e.preventDefault();
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}
