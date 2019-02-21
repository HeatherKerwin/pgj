import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
import {OpenaccountSuccessPage} from './openaccountSuccess';

@Component({
  selector: 'page-iframesigture',
  templateUrl: 'iframesigture.html'
})


export class IframesigtureePage {

  public memberId = '';
  public browser:any = {url:'',secUrl: ''};
  public orgType:any;
  public meType:any;
  public cib:any={
    id:'',
  };
  public openaccount: any = {
    bankAcctAcctNo:'',
    bankAcctAcctName:'',
    allprice:'',  //输入的金额
  };
  public xebutton:boolean=true;


  constructor(public storage: Storage, public navCtrl: NavController,public params: NavParams, public viewCtrl: ViewController, public apiSev: apiService, public sanitizer: DomSanitizer) {
    this.memberId = this.params.get("MEMBERID");
    this.meType = this.params.get("ME_TYPE"); //我类型
    this.orgType = this.params.get("ORG_TYPE");
    this.cib = this.params.get("CIB");
    this.openaccount = this.params.get("OPENACCOUNT");
    let url=this.apiSev.getNewUrl()+"out/authorization/?memberId="+this.memberId+"&type="+this.orgType+"&cibId="+this.cib.id+"";
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  canceldd(){
    this.navCtrl.pop();
  }

  Toopenaccount(){
    this.apiSev.pass='0';
    this.xebutton=false;
    let data:any={
      cibId:this.cib.id,
      amt:this.openaccount.allprice,
      memberId:this.memberId,
    };
    this.apiSev.api("newpost", "cib/corp/authenticate", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.return_msg!=null&&data.data.return_msg!='') {
          this.apiSev.itip(data.data.return_msg);
          this.navCtrl.pop();
          return;
        }
        if(data.data.error_msg!=null&&data.data.error_msg!='') {
          this.apiSev.itip(data.data.error_msg);
          this.navCtrl.pop();
          return;
        }
        if (data.data.corp != null && data.data.corp != '') {
          if (data.data.corp.status == 5) {
            this.apiSev.itip("您输入的小额鉴定金额有误,您还有" + data.data.authenticate_order.left_count + "机会可操作");
            this.navCtrl.pop();
            return;
          }
          if (data.data.corp.status == 6) {
            this.apiSev.itip("鉴定失败,请联系客服");
            this.navCtrl.pop();
            return;
          }
          if (data.data.corp.status == 2) {
            this.apiSev.itip("鉴定成功");
            this.navCtrl.push(OpenaccountSuccessPage, {
              ORG_TYPE: this.orgType,
              ME_TYPE: this.meType
            });
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }
}
