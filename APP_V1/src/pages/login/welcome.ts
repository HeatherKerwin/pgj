import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Login } from '../login/login';

@Component({
  selector: 'page-welcome',
  templateUrl: 'welcome.html'
})
export class welcomePage {


  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService) {

  }


  ionViewDidEnter(){
  }

  goToHome(e){
	  this.navCtrl.setRoot(Login);
    e.preventDefault();
  }

}
