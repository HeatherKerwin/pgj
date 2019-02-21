import { Component } from '@angular/core';
import { NavController, ViewController, NavParams, ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountOrcPage } from './openaccountOrc';
import { MePageB } from '../me/meB';
import {DisclaimerPage} from './disclaimer';
import {ServicePage} from '../home/ServicePage';

@Component({
  selector: 'page-statementB',
  templateUrl: 'statementB.html'
})


export class StatementBPage {

  public memberId='';
  public orgType:any;
  public meType:any;
  public enterprises=true;
  public commitment=true;

  constructor(public storage: Storage, public modalCtrl:ModalController, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id
    });
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
    let meType = this.params.get("ME_TYPE"); //我类型
    this.meType = meType;
  }

  //开户
  OpenaccountPage(){
    if(this.enterprises==false){
      this.apiSev.itip("请查看“票管家”平台免责声明,并勾选.");
      return;
    }
    this.navCtrl.push(OpenaccountOrcPage,{
      ORG_TYPE:0,
      ME_TYPE:0
    });
  }

  //返回
  closeme(){
    if(this.meType==0){
      this.navCtrl.setRoot(MePageB,{
        INDEX:5
      });
    }else {
      this.navCtrl.pop();
    }
  }

  //免责声明
  disclaimer(){
    let profileModal = this.modalCtrl.create(DisclaimerPage,{
      CONTRACT:"statement",
    });
    profileModal.present();
  }

  //免责声明图片按钮
  Arrow() {
    this.enterprises = !this.enterprises;
  }

  //三方合同
  commitments(){
    this.commitment = !this.commitment;
  }
  disclaimerB(){
    let profileModal = this.modalCtrl.create(ServicePage);
    profileModal.present();
  }
}
