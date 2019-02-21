import { Component } from '@angular/core';
import { NavController, ViewController, NavParams, AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
import { OrderDetailNewShowPage } from '../order/orderDetailNewShow';

@Component({
  selector: 'page-signature',
  templateUrl: 'signature.html'
})


export class SignaturePage {


  public browser:any = {url:'',secUrl: ''};
  public distId = '';
  public item:any = {};
  public OnePrice:any='';//是否有一口价
  public data:any={};//接口传值
  public todoor_price:any='';


  constructor(public storage: Storage, public navCtrl: NavController, public params: NavParams, public alertCtrl: AlertController, public viewCtrl: ViewController, public apiSev: apiService, public sanitizer: DomSanitizer) {

    this.item = this.params.get("item");

    let url :any;
    //是否是京东订单
    if(this.item.dist_bind_id!=null&&this.item.dist_bind_id!=''&&this.item.dist_bind_id!='undefined'&&this.apiSev.jdrz==1){
      url = this.apiSev.getNewUrl() + "out/jd/econtract/?distId=" + this.item.dist_id + "&orderType=" + this.item.order_type + "&bindId=" + this.item.dist_bind_id;
    }else {
      url = this.apiSev.getNewUrl() + "out/econtract/?distId=" + this.item.dist_id + "&orderType=" + this.item.order_type;
    }

    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  canceldd() {
    this.navCtrl.pop();
  }

  Save(e) {
    this.apiSev.parmData = {
      signature: 'running'
    };
    this.apiSev.itip("合同已经签署成功,请继续支付票款！");
    this.viewCtrl.dismiss();
    e.preventDefault();
  }
}
