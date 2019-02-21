import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import {TabsPageB} from "../tabs/tabsB";

@Component({
  selector: 'page-success1',
  templateUrl: 'success1.html'
})
export class successPage {

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {

  }

  success(){
    this.navCtrl.push(TabsPageB,{INDEX:2});
  }
}
