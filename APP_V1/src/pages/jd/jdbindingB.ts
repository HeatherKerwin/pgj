import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';
import {TabsPage} from "../tabs/tabs";
import {TabsPageB} from "../tabs/tabsB";
import {JdinfoPage} from "./jdinfo";

@Component({
  selector: 'page-jdbindingB',
  templateUrl: 'jdbindingB.html'
})


export class jdbindingBPage {

  public memberId = '';
  public orgType:any;
  public meType:any;

  public binding: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
  };

  public bingDing=false;
  public bingDings=true;

  public jdData:any={
    blicCompanyName:'',
    blicUscc:'',
    blicCardNo:''
  };
  public band=0;

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.band = this.params.get("BAND");
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
      });
      this.jdjr();
    });
  };

  closeme(){
    if(this.band==1){
      if(this.orgType==1){
        this.navCtrl.setRoot(TabsPage,{
          INDEX:4
        });
      }else if(this.orgType==0){
        this.navCtrl.setRoot(TabsPageB,{
          INDEX:4
        });
      }
    }if(this.band==0){
      this.navCtrl.pop();
    }
  }

  //京东是否开户
  jdjr(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjrbind/get/jdjr/jdjrbind", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        //未开户-去往开户页面
        if(data.data.jdjrBind!=null&&data.data.jdjrBind!=''&&data.data.jdjrBind!='undefined') {
          //绑定 //待审核
          this.jdData=data.data.jdjr;
          if(data.data.jdjrBind.checkState=='NOPASS'){
          this.bingDing=true;
          this.bingDings=false;
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //重新开户
  Toopenaccount(){
    this.navCtrl.push(JdinfoPage);
  }

}
