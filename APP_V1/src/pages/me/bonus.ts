import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-bonus',
  templateUrl: 'bonus.html'
})
export class Bonus {
  public user:any = {};
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService) {

  }


  ionViewDidEnter(){
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
