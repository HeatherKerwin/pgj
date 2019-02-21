import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import {jdLicense3editPage} from "./jdLicense3edit";
import {jdimgeditPage} from "./jdimgedit";
import {jdlicense4Page} from "./jdLicense4";
import {MePage} from "../me/me";
import {MePageB} from "../me/meB";
import {JdinfoPage} from "./jdinfo";

@Component({
  selector: 'page-jdLicense3',
  templateUrl: 'jdLicense3.html'
})


export class jdLicense3Page {

  public memberId='';
  public orgType:any;
  public meType:any;

  public bingDings = true;
  public bingDing = false;

  //企业角标判断
  public enterprises=false;
  public enterprise = true;
  //银行角标判断
  public bankinfo = false;
  public bankinfos = true;
  //经办人
  public handler = true;
  public handlers = true;
  //财务
  public finance = false;
  public finances = true;
  //机构信用代码
  public mechanism = true;
  public mechanisms = false;

  public jd: any = {};
  public jdData:any={};

  //省联动
  public province:any='请选择省';
  //市联动
  public city:any='请选择市';

  public F5:any=0;


  constructor(public storage: Storage, public navCtrl: NavController,public loadingCtrl: LoadingController, public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams) {
    //this.jdData = this.params.get("jdData");
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      //京东开户
      this.jdjr();
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
      });
    })
  }


  ionViewDidEnter(){
    if(this.F5==1){
      this.jdjr();
    }
  }

  //跳转到我里面
  closeme(){
    if (this.orgType == 1) {
      this.navCtrl.setRoot(MePage,{
        INDEX:5
      });
    } else if (this.orgType == 0) {
      this.navCtrl.setRoot(MePageB,{
        INDEX:5
      });
    }
  }


  //取京东信息
  jdjr(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）

    this.apiSev.api("newpost", "jdjr/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.jdData=data.data;
        if(this.jdData.status==1||this.jdData.status==6){
          this.bingDings=false;
          this.bingDing=true;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

//企业法人营业执照
  Arrow(){
    this.enterprises = !this.enterprises;
    if (this.enterprises == true) {
      this.enterprise = false
    } else if (this.enterprises == false) {
      this.enterprise = true
    }
  }
  //银行信息
  Tobanks(){
    this.bankinfo = !this.bankinfo;
    if (this.bankinfo == true) {
      this.bankinfos = false
    } else if (this.bankinfo == false) {
      this.bankinfos = true
    }
  }
  //身份证
  Tofinance(){
    this.finance = !this.finance;
    if(this.finance==true){
      this.finances = false;
    }else if(this.finance==false){
      this.finances = true;
    }
  }


  //修改资料
  Appraisal(){
    this.F5=1;
    this.navCtrl.push(jdLicense3editPage,{
      jdData:this.jdData
    });
  }

  //修改开户图片
  Appraisalimg(){
    this.F5=1;
    this.navCtrl.push(jdimgeditPage,{
      jdData:this.jdData
    });
  }

  xiaoejd(){
    this.navCtrl.push(jdlicense4Page);
  }


  //重新开户
  Toopenaccount(){
    this.navCtrl.push(JdinfoPage);
  }
}
