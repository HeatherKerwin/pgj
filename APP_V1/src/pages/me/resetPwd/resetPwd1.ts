import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import { AlertController } from 'ionic-angular';
@Component({
  selector: 'page-resetPwd1',
  templateUrl: 'resetPwd1.html'
})

export class resetPwd1Page {
  public mobile;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public alertCtrl: AlertController) {
    this.mobile="13127952341";
  }

  nextStep(e){
    let mobile = this.mobile;
    if(mobile==""){
      this.alert("提示","手机号不能为空","ios");
    }else{
      this.apiSev.api(
        "get",
        "/app/sendcode/?mobile=13127952341&flag=forget",
        (res) => {
          if(res.state == 'success'){

          }
        }, (error) => {},1,{});
    }
    e.preventDefault();
  }

  alert(title,subTitle,mode){
    let alert = this.alertCtrl.create({
      title: title,
      subTitle: subTitle,
      buttons: ['确定']
    });
    alert.setMode(mode);
    alert.present();
  }
}
