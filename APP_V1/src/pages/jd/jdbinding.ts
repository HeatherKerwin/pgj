import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { jdbindingBPage } from './jdbindingB';

@Component({
  selector: 'page-jdbinding',
  templateUrl: 'jdbinding.html'
})


export class jdbindingPage {

  public memberId = '';
  public orgType: any;
  public meType: any;

  public jdData:any={
    blicCompanyName:'',
  };


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams,public alertCtrl: AlertController) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.jdData  = this.params.get("jdData"); //公司信用代码号
    });
  }

  binDing() {
    let data: any = {
      jdjrId:this.jdData.id,
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "jdjrbind/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let alert = this.alertCtrl.create({
          title: '提示',
          subTitle: '确定申请账户绑定',
          buttons: [
            {
              text: '确定',
              handler: data => {
                this.navCtrl.push(jdbindingBPage, {
                  BAND:1
                });
              }
            },
          ]
        });
        alert.present();
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}

