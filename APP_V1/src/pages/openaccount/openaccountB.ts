import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountCPage } from './openaccountC';

@Component({
  selector: 'page-openaccountB',
  templateUrl: 'openaccountB.html'
})


export class OpenaccountBPage {

  public memberId='';
  public orgType:any;
  public meType:any;

  public openaccount: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
    contactMobile:'',   //联系人手机号
    email:'',           //邮箱
    contactName:'',     //联系人姓名
    agentCertNo:'',   //联系人的身份证号码
  };
  public Ocropen:any={

  };

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.openaccount.contactMobile = data.mobile;
    });
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
    let meType = this.params.get("ME_TYPE"); //我类型
    this.meType = meType;

    let bizLicenceRegisteredNo = this.params.get("OPEN_NUMBER"); //统一信用代码
    this.openaccount.bizLicenceRegisteredNo = bizLicenceRegisteredNo;
    let name = this.params.get("OPRN_NAME"); //企业名称
    this.openaccount.name = name;

    this.Ocropen = this.params.get("OCROPEN"); //ocr传值
/*    if(this.Ocropen!=null&&this.Ocropen!=''&&this.Ocropen!='undefined'){
      if(this.Ocropen.name!=null&&this.Ocropen.name!=''&&this.Ocropen.name!='undefined'){
        this.openaccount.contactName=this.Ocropen.name
      }
      if(this.Ocropen.id!=null&&this.Ocropen.id!=''&&this.Ocropen.id!='undefined'){
        this.openaccount.agentCertNo=this.Ocropen.id
      }
    }*/
  }


  Toopenaccount() {
    if(this.openaccount.contactName == null || this.openaccount.contactName== ''){
      this.apiSev.itip("请输入联系人姓名");
      return;
    }
    if(this.openaccount.contactMobile == null || this.openaccount.contactMobile== ''){
      this.apiSev.itip("请输入联系人的手机号码");
      return;
    }else{
      let phone = this.openaccount.contactMobile.match(/^(1)[0-9]{10}$/);
      if (phone) {
      } else {
        this.apiSev.itip("不是完整的11位手机号或者正确的手机号前七位");
        return;
      }
    }
    if(this.openaccount.contactMobile.length != 11){
      this.apiSev.itip("您输入联系人的手机号码有误,请重新输入");
      return;
    }
    if(this.openaccount.agentCertNo == null || this.openaccount.agentCertNo== ''){
      this.apiSev.itip("请输入联系人的身份证号码");
      return;
    }
    if(this.openaccount.agentCertNo.length != 18 && this.openaccount.agentCertNo.length != 16){
      this.apiSev.itip("请输入正确的联系人身份证号码");
      return;
    }else {
      let agentCertNo = this.openaccount.agentCertNo.match(/^[0-9a-zA-Z]+$/);
      if (agentCertNo) {
      } else {
        this.apiSev.itip("请输入正确的联系人身份证号码");
        return;
      }
    }
    if(this.openaccount.email == null || this.openaccount.email== ''){
      this.apiSev.itip("请输入联系人的邮箱");
      return;
    }else {
      let email = this.openaccount.email.match(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/);
      if (email) {
      } else {
        this.apiSev.itip("输入的邮箱输入格式有误");
        return;
      }
    }
    this.apiSev.pass = '0';
    this.navCtrl.push(OpenaccountCPage, {
      ORG_TYPE: this.orgType,
      ME_TYPE: this.meType,
      OPEN_NUMBER:this.openaccount.bizLicenceRegisteredNo,
      OPRN_NAME:this.openaccount.name,
      OPEN_EMAIL:this.openaccount.email,
      OPEN_CONTACTNAME:this.openaccount.contactName,
      OPEN_AGENTCERTNO:this.openaccount.agentCertNo,
      OPEN_PHONE:this.openaccount.contactMobile,
      OCROPEN:this.Ocropen,
    });
  }
}
