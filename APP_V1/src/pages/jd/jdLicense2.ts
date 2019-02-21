import {Component} from '@angular/core';
import {NavController, ViewController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {jdLicense3Page} from "./jdLicense3";
import {BranchlistPage} from "./branchlist";

@Component({
  selector: 'page-jdLicense2',
  templateUrl: 'jdLicense2.html'
})


export class jdLicense2Page {

  public messagecontent: '';
  public memberId;

  public jd:any={
    occBankChildName:'请选择开户支行'
  };

  //开户支行信息
  public banklist:any=[];

  //银行联动
  public bankinfo:any='请选择开户行';
  public bankinfolist:any=[];


  //搜索框
  public banktxt:any;

  //省联动
  public province:any='请选择省';
  public provincelist:any=[];
  //市联动
  public city:any='请选择市';
  public citylist:any=[];

  //弹窗
  public isMask=false;

  public xz:any=1;

  public xz1:any='请选择省';
  public xz2:any='请选择市';
  public xz3:any='请选择';

  //城市编号
  public bankCode:any;
  //银行编号
  public cityCode:any;


  //京东
  public occBankAccount:any;  //对公账户号


  //京东传值
  public jdData:any={};

  constructor(public storage: Storage,public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    this.jdData = this.params.get("jdData");

    //获取银行信息
    this.querybankinfo();
    //获取省份信息
    this.queryprovince();
  }

  ionViewDidEnter(){
    if(this.apiSev.occBankChildName!=null&&this.apiSev.occBankChildName!=''&&this.apiSev.occBankChildName!='undefined'){
      this.jd.occBankChildName=this.apiSev.occBankChildName;
      this.apiSev.occBankChildName='';
    }
    if(this.apiSev.occBankChildCode!=null&&this.apiSev.occBankChildCode!=''&&this.apiSev.occBankChildCode!='undefined'){
      this.jd.occBankChildCode=this.apiSev.occBankChildCode;
      this.apiSev.occBankChildCode='';
    }
  }



  //去往审核中
  jdlicense3() {
    if (this.bankinfo == '请选择开户行' || this.bankinfo == '' || this.bankinfo == 'undefined' || this.bankinfo == null) {
      this.apiSev.itip("请选择开户行");
      return;
    }
    if (this.jd.occBankChildName == '请选择开户支行' || this.jd.occBankChildName == '' || this.jd.occBankChildName == 'undefined' || this.jd.occBankChildName == null) {
      this.apiSev.itip("请选择开户支行");
      return;
    }
    if (this.occBankAccount == '' || this.occBankAccount == 'undefined' || this.occBankAccount == null) {
      this.apiSev.itip("请输入绑定的银行卡");
      return;
    }
    if (this.occBankAccount.toString().substr(0, 1) != '0' && this.occBankAccount.length > 7 && this.occBankAccount.length <= 27) {

    } else {
      this.apiSev.itip("请输入非0数字开头7到27位的银行卡号");
      return;
    }


    let occBankName:any;
    let objs = this.bankinfolist;
    if(objs.length>0){
      for (let i=0;i<objs.length;i++) {
        if(objs[i].bankCode==this.bankinfo){
          occBankName=objs[i].bankName
        }
      }
    }

    this.jdData.occBankAccount=this.occBankAccount;
    this.jdData.occBankCnap=this.bankinfo;
    this.jdData.occBankName=occBankName;
    this.jdData.occBankChildName=this.jd.occBankChildName;
    this.jdData.occBankChildCode=this.jd.occBankChildCode;

    console.log(this.jdData);

    let data: any = this.jdData;
    data.memberId= this.memberId;
    this.apiSev.api("newpost", "jdjr/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(jdLicense3Page,{
          jdData:this.jdData
        });
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //获取银行信息
  querybankinfo() {
    this.bankinfolist=[];
    let data: any = {};
    this.apiSev.api("newpost", "jdjr/querybankinfo", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.storage.get('role').then((res) => {
          if (res == 'chupiao') {
            this.bankinfolist = data.data.list;
          } else if (res == 'baojia') {
            let objs = data.data.list;
            if (objs.length > 0) {
              for (let i = 0; i < objs.length; i++) {
                if (objs[i].bankCode == 307 || objs[i].bankCode == 302) {
                  this.bankinfolist.push(objs[i]);
                }
              }
            }
          }
        });
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'||e.srcElement.className=='maskConB'){
      this.isMask = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
  }
  //弹出下拉框时，取消scroll
  hiddenscroll(){
    //获取当前组件的ID
    let aboutContent = document.querySelector("ion-content");
    //获取当前组件下的scroll-content元素
    let scroll:any = aboutContent.querySelector(".scroll-content");
    if(this.isMask){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }

  closeMask(){
    this.isMask = false;
  }

  //获取省份信息
  queryprovince() {
    let data: any = {};
    this.apiSev.api("newpost", "jdjr/queryprovince", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.provincelist=data.data.list;
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //获取省份内的城市信息
  querycity(id) {
    let data: any = {
      provinceCode:id,
    };
    this.apiSev.api("newpost", "jdjr/querycity", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.citylist=data.data.list;
        this.xz2=this.citylist[0].cityName;
        this.cityCode=this.citylist[0].cityCode;
        this.xz=2;
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //获取省份内的城市信息
  querybankcnapsinfo() {
    let data: any = {
      bankCode:this.bankinfo,
      cityCode:this.cityCode,
    };
    this.apiSev.api("newpost", "jdjr/querybankcnapsinfo", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.banklist=data.data.list;
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  draftcalculatesave(){
    if(this.banktxt==null||this.banktxt==''||this.banktxt=='undefined'){
      this.apiSev.itip("请输入搜索支行的关键字");
      return
    }
    this.banklist=[];
    let data: any = {
      bankCode:this.bankinfo,
      cityCode:this.cityCode,
    };
    this.apiSev.api("newpost", "jdjr/querybankcnapsinfo", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if(objs.length>0){
          for (let i=0;i<objs.length;i++) {
            if(objs[i].bankCnapsName.indexOf(this.banktxt) != -1){
              this.banklist.push(objs[i]);
            }
          }
        }
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //点击城市判断
  xuanz1() {
    if (this.bankinfo == '请选择开户行') {
      this.apiSev.itip("请选择开户行");
      return;
    }

    this.navCtrl.push(BranchlistPage,{
      bankinfo:this.bankinfo,
    });

    //this.isMask=true;
  }
  xuanz2(){
    if(this.xz1=='请选择省'){
      this.apiSev.itip("请先选择省");
      return;
    }

    this.xz=2;
  }
  xuanz3(){
    if(this.xz1=='请选择省'){
      this.apiSev.itip("请先选择省");
      return;
    }
    if(this.xz2=='请选择市'){
      this.apiSev.itip("请选择市");
      return;
    }

    this.xz=3;
    this.querybankcnapsinfo();
  }

}
