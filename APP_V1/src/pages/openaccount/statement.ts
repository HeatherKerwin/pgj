import { Component } from '@angular/core';
import { NavController, ViewController, NavParams, ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountOrcPage } from './openaccountOrc';
import { MePage } from '../me/me';
import {DisclaimerPage} from './disclaimer';
import {ServicePage} from '../home/ServicePage';

@Component({
  selector: 'page-statement',
  templateUrl: 'statement.html'
})


export class StatementPage {

  public memberId='';
  public orgType:any;
  public meType:any;
  public enterprises=true;
  public commitment=true;


  constructor(public storage: Storage, public modalCtrl:ModalController, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id
    });
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
    let meType = this.params.get("ME_TYPE"); //我类型
    this.meType = meType;
  }

  //开户
  OpenaccountPage(e) {
    if(this.enterprises==false){
      this.apiSev.itip("请查看“票管家”平台免责声明,并勾选.");
      return;
    }
    this.navCtrl.push(OpenaccountOrcPage,{
      ORG_TYPE:1,
      ME_TYPE:1
    });
    e.preventDefault();
  }

  //返回
  closeme(e){
    if(this.meType==1){
      this.navCtrl.setRoot(MePage,{
        INDEX:5
      });
    }else {
      this.navCtrl.pop();
    }
    e.preventDefault();
  }

  //免责声明
  disclaimer(e){
    let profileModal = this.modalCtrl.create(DisclaimerPage,{
      CONTRACT:"statement",
    });
    profileModal.present();
    e.preventDefault();
  }

  //免责声明图片按钮
  Arrow(e) {
    this.enterprises = !this.enterprises;
    e.preventDefault();
  }

  //三方合同
  commitments(e){
    this.commitment = !this.commitment;
    e.preventDefault();
  }
  disclaimerB(e){
    let profileModal = this.modalCtrl.create(ServicePage);
    profileModal.present();
    e.preventDefault();
  }
}
