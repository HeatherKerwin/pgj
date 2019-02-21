import {Component} from '@angular/core';
import {NavController, ViewController, NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {BindingPage} from './binding';
import {OpenaccountBPage} from './openaccountB';



@Component({
  selector: 'page-openaccount',
  templateUrl: 'openaccount.html'
})


export class OpenaccountPage {

  public memberId = '';
  public orgType=1;
  public meType:any;
  public Ocropen:any={

  };  //Ocr传值

  public openaccount: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
  };

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
      })
    });
    this.meType = this.params.get("ME_TYPE"); //我类型
    this.Ocropen = this.params.get("OCROPEN"); //ocr传值
    if(this.Ocropen!=null&&this.Ocropen!=''&&this.Ocropen!='undefined'){
      if(this.Ocropen.bizLicenceRegisteredNo!=null&&this.Ocropen.bizLicenceRegisteredNo!=''&&this.Ocropen.bizLicenceRegisteredNo!='undefined'){
        this.openaccount.bizLicenceRegisteredNo=this.Ocropen.bizLicenceRegisteredNo
      }
      if(this.Ocropen.bizLicenceName!=null&&this.Ocropen.bizLicenceName!=''&&this.Ocropen.bizLicenceName!='undefined'){
        this.openaccount.name=this.Ocropen.bizLicenceName
      }
    }
  }


  openaccountPage() {
    if (this.openaccount.bizLicenceRegisteredNo.length != 18) {
      this.apiSev.itip("信用代码输入错误,请输入正确的信用代码");
      return;
    }else {
      let bizLicenceRegisteredNo = this.openaccount.bizLicenceRegisteredNo.match(/^[0-9a-zA-Z]+$/);
      if (bizLicenceRegisteredNo) {
      } else {
        this.apiSev.itip("信用代码输入错误,请输入正确的信用代码");
        return;
      }
    }
    if(this.openaccount.name == null || this.openaccount.name== ''){
      this.apiSev.itip("请输入公司名称");
      return;
    }

    let data:any={
      memberId:this.memberId,
      bizLicenceRegisteredNo:this.openaccount.bizLicenceRegisteredNo,
    };
    this.apiSev.api("newpost", "cib/get/same", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.type == 0 ) {//已经开户的角色是企业
          if(this.orgType == 1 ){//当前选择的角色是机构，去开户
            this.navCtrl.push(OpenaccountBPage, {
              ORG_TYPE: this.orgType,
              ME_TYPE: this.meType,
              OPEN_NUMBER:this.openaccount.bizLicenceRegisteredNo,
              OPRN_NAME:this.openaccount.name,
              OCROPEN:this.Ocropen,
            });
          }else{//去绑定
            this.navCtrl.push(BindingPage, {
              ORG_TYPE: this.orgType,
              ME_TYPE: this.meType,
              OPEN_NUMBER:this.openaccount.bizLicenceRegisteredNo,
            });
          }
        }else if(data.data.type == 1){//已经开户的角色是机构直接去绑定
          this.navCtrl.push(BindingPage, {
            ORG_TYPE: this.orgType,
            ME_TYPE: this.meType,
            OPEN_NUMBER:this.openaccount.bizLicenceRegisteredNo
          });
        }else {//两边都没开户去开户
          this.navCtrl.push(OpenaccountBPage, {
            ORG_TYPE: this.orgType,
            ME_TYPE: this.meType,
            OPEN_NUMBER:this.openaccount.bizLicenceRegisteredNo,
            OPRN_NAME:this.openaccount.name,
            OCROPEN:this.Ocropen,
          });
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
