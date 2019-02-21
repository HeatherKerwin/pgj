import { Component } from '@angular/core';
import { NavController, ViewController ,NavParams} from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-payment',
  templateUrl: 'payment.html'
})
export class PaymentPage {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};
  constructor(
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public sanitizer: DomSanitizer,
    public params: NavParams,
    public apiSev: apiService
  ) {
    let money = this.params.get("money");
    let memberId = this.params.get("memberId");
    let type = this.params.get("type");
    let url = this.apiSev.getWapUrl() + 'account/pay?money=' + money + '&memberId=' + memberId + '&type=' + type;
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  ionViewDidEnter(){
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
