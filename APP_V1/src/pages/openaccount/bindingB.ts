import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { StatementPage } from './statement';
import { StatementBPage } from './statementB';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';

@Component({
  selector: 'page-bindingB',
  templateUrl: 'bindingB.html'
})


export class BindingBPage {

  public memberId = '';
  public orgType:any;
  public meType:any;

  public binding: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
  };

  public bingDing=false;
  public bingDings=true;


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
        this.openaccountPage();
      });
      this.binding.bizLicenceRegisteredNo = this.params.get("OPEN_NUMBER"); //公司信用代码号
    });
    this.meType  = this.params.get("ME_TYPE"); //我类型
  }

  Toopenaccount(){
    if(this.orgType == 1 ){
      this.navCtrl.push(StatementPage,{
        ORG_TYPE:this.orgType,
        ME_TYPE:1
      });
    }else if(this.orgType == 0){
      this.navCtrl.push(StatementBPage,{
        ORG_TYPE:this.orgType,
        ME_TYPE:0
      });
    }
  }

  closeme(){
    if(this.meType==1){
      this.navCtrl.setRoot(MePage,{
        INDEX:5
      });
    }else if(this.meType==2){
      this.navCtrl.setRoot(MePageB,{
        INDEX:5
      });
    }else {
      this.navCtrl.pop();
    }
  }

  openaccountPage() {
    let data: any = {
      memberId: this.memberId,
      type: this.orgType,
    };
    this.apiSev.api("newpost", "orginfo/rz", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.binding.name = data.data.cib.name;
        this.binding.bizLicenceRegisteredNo = data.data.cib.bizLicenceRegisteredNo;
        if (data.data.info_state == 'PENDING') {
          this.bingDing = false;
          this.bingDings = true;
        }
        if (data.data.info_state == 'NOPASS') {
          this.bingDing = true;
          this.bingDings = false;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}
