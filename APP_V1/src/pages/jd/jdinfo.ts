import {Component} from '@angular/core';
import {NavController, ViewController,ModalController} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {jdLicensePage} from "./jdLicense";
import {jdbanklistPage} from "./jdbanklist";
import {JdsignaPage} from "./jdsigna";

@Component({
  selector: 'page-jdinfo',
  templateUrl: 'jdinfo.html'
})


export class JdinfoPage {

  public messagecontent: '';
  public memberId;

  //勾选未勾选
  public term:boolean=true;


  constructor(public storage: Storage, public modalCtrl:ModalController,public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
  }


  //去往上传营业执照页面
  OpenaccountPage() {
    if(this.term!=true){
      this.apiSev.itip("请先勾选京东钱包服务协议！");
      return;
    }

    let data: any = {
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "jdjr/login/check", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(jdLicensePage);
      }else {
        this.jdsigna();
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //开户协议
  jdsigna(){
    let profileModal = this.modalCtrl.create(JdsignaPage,{
      memberId:this.memberId
    });
    profileModal.present();
  }

}
