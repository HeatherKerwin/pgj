import {Component} from '@angular/core';
import {NavController, ViewController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {OpenaccountSuccessPage} from './openaccountSuccess';
import {IframesigtureePage} from './iframesigture';


@Component({
  selector: 'page-openaccountF',
  templateUrl: 'openaccountF.html'
})


export class OpenaccountFPage {

  public memberId = '';
  public orgType:any;
  public meType:any;
  public cib:any={
    id:'',
  };

  //按钮判断
  public Tonosuccess = true;
  public Tosuccess = false;

  public openaccount: any = {
    bankAcctAcctNo:'',
    bankAcctAcctName:'',
    allprice:'',  //输入的金额
  };

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;

      this.storage.get('role').then((res) => {
        if (res=='baojia') {
          this.orgType = 1;//角色类型
        }else if (res=='chupiao'){
          this.orgType = 0; //角色类型
        }
        this.meType = this.params.get("ME_TYPE"); //我类型
        this.openaccounTactive();
      });
    });

  }

  switching(){
    if(this.openaccount.allprice > 0){
    this.Tonosuccess = false;
    this.Tosuccess = true;
    }else {
      this.Tonosuccess = true;
      this.Tosuccess = false;
    }
  }

  cibget(){
    let data:any={
      memberId:this.memberId,
      id:this.cib.id,
    };
    this.apiSev.api("newpost", "cib/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let openaccount = data.data;
        this.openaccount.bankAcctAcctNo = openaccount.bankAcctAcctNo;
        this.openaccount.bankAcctAcctName = openaccount.bankAcctAcctName;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  openaccounTactive(){
    let data:any={
      memberId:this.memberId,
      type:this.orgType,
    };
    this.apiSev.api("newpost", "orginfo/rz", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.cib = data.data.cib;
        this.cib.id = data.data.cib.id;
        this.cibget();
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  iframesigture(){
    this.navCtrl.push(IframesigtureePage, {
      ORG_TYPE: this.orgType,
      ME_TYPE: this.meType,
      CIB:this.cib,
      OPENACCOUNT:this.openaccount,
      MEMBERID:this.memberId,
    });
  }

  Toopenaccount(){
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
          return;
        }
        if(data.data.error_msg!=null&&data.data.error_msg!='') {
          this.apiSev.itip(data.data.error_msg);
          return;
        }
        if (data.data.corp != null && data.data.corp != '') {
          if (data.data.corp.status == 5) {
            this.apiSev.itip("您输入的小额鉴定金额有误,您还有" + data.data.authenticate_order.left_count + "机会可操作");
            return;
          }
          if (data.data.corp.status == 6) {
            this.apiSev.itip("鉴定失败,请联系客服");
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
