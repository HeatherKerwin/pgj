import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,AlertController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { BindingBPage } from './bindingB';

@Component({
  selector: 'page-binding',
  templateUrl: 'binding.html'
})


export class BindingPage {

  public memberId = '';
  public orgType: any;
  public meType: any;

  public binding: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
    bizname:'', //绑定申请人姓名
  };


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams,public alertCtrl: AlertController) {
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
      this.binding.bizLicenceRegisteredNo  = this.params.get("OPEN_NUMBER"); //公司信用代码号
    });
    this.meType = this.params.get("ME_TYPE"); //我类型
  }

  binDing() {
    if(this.binding.bizname==null||this.binding.bizname==''){
      this.apiSev.itip("请输入绑定申请人姓名");
      return;
    }
    let data: any = {
      cibId: this.binding.id,
      memberId: this.memberId,
      type: this.orgType,
      name:this.binding.bizname,
    };
    this.apiSev.api("newpost", "cib/corp/bind", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.apiSev.pass='';
        let alert = this.alertCtrl.create({
          title: '提示',
          subTitle: '确定申请账户绑定',
          buttons: [
            {
              text: '确定',
              handler: data => {
                this.navCtrl.push(BindingBPage, {
                  ORG_TYPE: this.orgType,
                  ME_TYPE: this.meType,
                  OPEN_NUMBER: this.binding.bizLicenceRegisteredNo
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

  openaccountPage() {
    let data: any = {
      memberId: this.memberId,
      bizLicenceRegisteredNo: this.binding.bizLicenceRegisteredNo,
    };
    this.apiSev.api("newpost", "cib/get/same", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.binding.name = data.data.name;
        this.binding.id = data.data.id;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}

