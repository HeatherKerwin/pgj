import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-vipupgrade',
  templateUrl: 'vipupgrade.html'
})


export class VipupgradePage {

    public messagecontent: '';
    public memberId;


    constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    }
}
