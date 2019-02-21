import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
// import { Buffer } from 'buffer';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-iframediv',
  templateUrl: 'iframediv.html'
})
export class Iframediv {
  public user:any = {};
  public browser:any = {url:'',secUrl: ''};
  constructor(
      public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public sanitizer: DomSanitizer,
    public params: NavParams ,
	public apiSev: apiService
  ) {

     let orderId=this.params.get('orderId');
     let orderType=this.params.get('orderType');

	 if (orderType=='DISCOUNTRECORD'){
		 orderType='DISTRIBUTEORDER';
	 }
	 else{
		 orderType='DISTRIBUTEORDERSP';
	 }
		 
    let url = this.apiSev.getWapUrl() + '/order/pay?orderId='+orderId+'&orderType='+orderType ;

	this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.apiSev.getWapUrl() + '/order/loading');

   }


   
   
   
   pub(){ 
		this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.browser.url);
   }


  ionViewDidEnter(){
  
	setTimeout(() => {
		this.pub();
	}, 1000);
	
	/*  console.log('url');
	 let url=this.params.get('url');
	 url = new Buffer(url,'base64').toString();
	 console.log(url);
     document.getElementById("div1").innerHTML=url;*/
  }


  close () {
  	this.viewCtrl.dismiss();
  }

}
