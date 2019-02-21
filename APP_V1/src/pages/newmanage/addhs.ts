import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-addhs',
  templateUrl: 'addhs.html'
})


export class AddhsPage {

    public messagecontent: '';
    public memberId;


    constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    console.log(this.apiSev.hsLists);
    }

  close () {
    this.navCtrl.pop();
  }
}
