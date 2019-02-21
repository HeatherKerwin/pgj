import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountSuccessPage } from './openaccountSuccess';

@Component({
  selector: 'page-bindingC',
  templateUrl: 'bindingC.html'
})


export class BindingCPage {

  public memberId = '';
  public orgType:any;
  public meType:any;

  public binding: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
  };


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams) {
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
    this.meType = this.params.get("ME_TYPE"); //我类型
  }

  //前往开户信息
  binDing() {
    this.apiSev.pass='0';    //将缓存清空
    this.navCtrl.push(OpenaccountSuccessPage,{
      ORG_TYPE:this.orgType,
      ME_TYPE:this.meType
    });
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
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}
