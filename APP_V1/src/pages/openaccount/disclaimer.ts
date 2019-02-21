import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-disclaimer',
  templateUrl: 'disclaimer.html'
})
export class DisclaimerPage {
  public disclaimers=false;
  public commitment=false;

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService,public params: NavParams) {
  }


  ionViewDidEnter(){
    let CONTRACT = this.params.get("CONTRACT"); //协议类型
    if(CONTRACT=="statement"){
      this.disclaimers=true;
      return;
    }
    if(CONTRACT=="commitment"){
      this.commitment=true;
      return;
    }
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
