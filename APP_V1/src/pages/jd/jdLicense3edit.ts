import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import {BranchlistPage} from "./branchlist";

@Component({
  selector: 'page-jdLicense3edit',
  templateUrl: 'jdLicense3edit.html'
})


export class jdLicense3editPage {

  public memberId='';
  public orgType:any;
  public meType:any;

  public bingDings = true;

  public xz:any=1;
  public xz1:any='请选择省';
  public xz2:any='请选择市';
  public xz3:any='请选择';

  //城市编号
  public bankCode:any;
  //银行编号
  public cityCode:any;

  //弹窗
  public isMask=false;

  public bankinfoData:any='请选择开户行';

  public banklist:any=[];


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

  //省联动
  public province:any='请选择省';
  public provincelist:any=[];
  //市联动
  public city:any='请选择市';
  public cityslist:any=[];
  public citylist:any=[];

  public bankinfolist:any=[];

  //搜索框
  public banktxt:any;

  //长期期限
  public cqterm:boolean=false;
  public cqtermB:boolean=false;
  //固定期限
  public gdterm:boolean=true;
  public gdtermB:boolean=true;

  public blicTrcValidityEndmin:any=new Date().toISOString();  //最小日期
  public blicTrcValidityEndmax:any=new Date(new Date().getTime()+51*360*24*3600*1000).toISOString();  //最大日期


  public jdData:any={
    blicCompanyName:'', //公司名称
    occBankChildName:'请选择开户支行',
  };


  constructor(public storage: Storage, public navCtrl: NavController,public loadingCtrl: LoadingController, public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams) {
    this.storage.get('userInfo').then((data)=> {
      this.memberId = data.id;
    });
    this.jdData = this.params.get("jdData");
    console.log(this.jdData);
    this.queryprovince();
    this.querybankinfo();

    //营业执照
    if(this.jdData.blicLongTerm==true){
      this.cqterm=false;
      this.gdterm=true;
    }else {
      this.cqterm=true;
      this.gdterm=false;
    }
    //身份证
    if(this.jdData.lepLongTerm==true){
      this.cqtermB=false;
      this.gdtermB=true;
    }else {
      this.cqtermB=true;
      this.gdtermB=false;
    }

    this.province=this.jdData.blicProvince;
    this.bankinfoData=this.jdData.occBankCnap;
    setTimeout(() => {
      this.querycity();
      this.city=this.jdData.blicCity;
    },500);
  }

  ionViewDidEnter(){
    if(this.apiSev.occBankChildName!=null&&this.apiSev.occBankChildName!=''&&this.apiSev.occBankChildName!='undefined'){
      this.jdData.occBankChildName=this.apiSev.occBankChildName;
      this.apiSev.occBankChildName='';
    }
    if(this.apiSev.occBankChildCode!=null&&this.apiSev.occBankChildCode!=''&&this.apiSev.occBankChildCode!='undefined'){
      this.jdData.occBankChildCode=this.apiSev.occBankChildCode;
      this.apiSev.occBankChildCode='';
    }
  }




  //确认修改信息
  Appraisal(){
    if (this.jdData.blicCompanyName == null || this.jdData.blicCompanyName == '' || this.jdData.blicCompanyName == 'undefined') {
      this.apiSev.itip("请输入公司名称");
      return;
    }
    this.jdData.companyType='ENTER';
    this.jdData.blicCompanyName = this.jdData.blicCompanyName;
    this.jdData.blicCardType = this.jdData.blicCardType;


    if (this.jdData.blicCardType == 'USC') {
      if (this.jdData.blicUscc == null || this.jdData.blicUscc == '' || this.jdData.blicUscc == 'undefined') {
        this.apiSev.itip("请输入统一社会信用代码");
        return;
      }
      if (this.jdData.blicUscc.length != 18) {
        this.apiSev.itip("您必须输入由18位的字母数字组合统一社会信用代码");
        return;
      }
    }

    this.jdData.blicUscc = this.jdData.blicUscc;

    if (this.jdData.blicCardType == 'OCI') {
      if (this.jdData.blicCardNo == null || this.jdData.blicCardNo == '' || this.jdData.blicCardNo == 'undefined') {
        this.apiSev.itip("请输入注册号");
        return;
      }
      this.jdData.blicCardNo = this.jdData.blicCardNo;
      if (this.jdData.blicObaCardNo == null || this.jdData.blicObaCardNo == '' || this.jdData.blicObaCardNo == 'undefined') {
        this.apiSev.itip("请输入税字号");
        return;
      }
      this.jdData.blicObaCardNo = this.jdData.blicObaCardNo;
      if (this.jdData.blicTrcCardNo == null || this.jdData.blicTrcCardNo == '' || this.jdData.blicTrcCardNo == 'undefined') {
        this.apiSev.itip("请输入组织机构代码");
        return;
      }
      this.jdData.blicTrcCardNo = this.jdData.blicTrcCardNo;
    }

    if (this.province == '请选择省') {
      this.apiSev.itip("请选择您公司所在省");
      return;
    }
    if (this.city == '请选择市') {
      this.apiSev.itip("请选择您公司所在市");
      return;
    }
    this.jdData.blicProvince = this.province;
    this.jdData.blicCity = this.city;


    if (this.jdData.blicAddress == null || this.jdData.blicAddress == '' || this.jdData.blicAddress == 'undefined') {
      this.apiSev.itip("请输入具体门牌号");
      return;
    }
    this.jdData.blicAddress = this.jdData.blicAddress;
    if (this.jdData.blicScope == null || this.jdData.blicScope == '' || this.jdData.blicScope == 'undefined') {
      this.apiSev.itip("请输入经营范围");
      return;
    }
    this.jdData.blicScope = this.jdData.blicScope;

    if (this.gdterm == true) {
      if (this.jdData.blicValidityEnd == null || this.jdData.blicValidityEnd == '' || this.jdData.blicValidityEnd == 'undefined') {
        this.apiSev.itip("请输入营业执照上的截止日期");
        return;
      }
      this.jdData.blicLongTerm = true;
      this.jdData.blicValidityEnd = this.jdData.blicValidityEnd;
    } else {
      this.jdData.blicLongTerm = false;
      this.jdData.blicValidityEnd = '长期';
    }

    if (this.jdData.lepName == null || this.jdData.lepName == '' || this.jdData.lepName == 'undefined') {
      this.apiSev.itip("请输入法人代表姓名");
      return;
    }
    this.jdData.lepCardType='ID';
    this.jdData.lepName = this.jdData.lepName;

    if (this.jdData.lepCardNo == null || this.jdData.lepCardNo == '' || this.jdData.lepCardNo == 'undefined') {
      this.apiSev.itip("请输入法人代表身份证号");
      return;
    }
    if(this.jdData.lepCardNo.length==15||this.jdData.lepCardNo.length==18){
    }else {
      this.apiSev.itip("请输入15/18位有效身份证号");
      return;
    }
    this.jdData.lepCardNo = this.jdData.lepCardNo;

    if (this.gdtermB == true) {
      if (this.jdData.lepValidityEnd == null || this.jdData.lepValidityEnd == '' || this.jdData.lepValidityEnd == 'undefined') {
        this.apiSev.itip("请输入身份证上的截止日期");
        return;
      }
      this.jdData.lepLongTerm = true;
      this.jdData.lepValidityEnd = this.jdData.lepValidityEnd;
    } else {
      this.jdData.lepLongTerm = false;
      this.jdData.lepValidityEnd = '长期';
    }

    if (this.bankinfoData == '请选择开户行' || this.bankinfoData == '' || this.bankinfoData == 'undefined' || this.bankinfoData == null) {
      this.apiSev.itip("请选择开户行");
      return;
    }
    if (this.jdData.occBankChildName == '请选择开户支行' || this.jdData.occBankChildName == '' || this.jdData.occBankChildName == 'undefined' || this.jdData.occBankChildName == null) {
      this.apiSev.itip("请选择开户支行");
      return;
    }
    if (this.jdData.occBankAccount == '' || this.jdData.occBankAccount == 'undefined' || this.jdData.occBankAccount == null) {
      this.apiSev.itip("请输入绑定的银行卡");
      return;
    }
    if (this.jdData.occBankAccount.toString().substr(0, 1) != '0' && this.jdData.occBankAccount.length > 7 && this.jdData.occBankAccount.length <= 27) {

    } else {
      this.apiSev.itip("请输入非0数字开头7到27位的银行卡号");
      return;
    }

    let occBankName:any;
    let objs = this.bankinfolist;
    if(objs.length>0){
      for (let i=0;i<objs.length;i++) {
        if(objs[i].bankCode==this.bankinfoData){
          occBankName=objs[i].bankName
        }
      }
    }

    this.jdData.occBankAccount=this.jdData.occBankAccount;
    this.jdData.occBankCnap=this.bankinfoData;
    this.jdData.occBankName=occBankName;
    this.jdData.occBankChildName=this.jdData.occBankChildName;
    this.jdData.occBankChildCode=this.jdData.occBankChildCode;


    this.apiSev.api("newpost", "jdjr/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.apiSev.itip("修改成功");
        this.navCtrl.pop();
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, this.jdData);

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


  //获取省份信息
  queryprovince() {
    let data: any = {};
    this.apiSev.api("post", "app/region", (res) => {
      console.log(res);
      this.provincelist = res.p;
      console.log(this.provincelist);
      this.cityslist = res.c;
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  querycity(){
    let augcode:any;
    let list = this.provincelist;
    if (list.length > 0) {
      for (let i = 0; i < list.length; i++) {
        if (list[i].name == this.province) {
          augcode=list[i].id
        }
      }
    }

    this.citylist = [];
    let objs = this.cityslist;
    if (objs.length > 0) {
      for (let i = 0; i < objs.length; i++) {
        if (objs[i].parentId == augcode) {
          this.citylist.push(objs[i]);
        }
      }
      this.city=this.citylist[0].name
    }
  }

  //获取银行信息
  querybankinfo() {
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
                if (objs[i].bankCode == '307' || objs[i].bankCode == '302') {
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


  //点击城市判断
  xuanz1() {
    if (this.bankinfoData == '请选择开户行') {
      this.apiSev.itip("请选择开户行");
      return;
    }

    this.navCtrl.push(BranchlistPage,{
      bankinfo:this.bankinfoData,
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


  //获取省份信息
  queryprovinces() {
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
  querycitys(id) {
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

  //操作开户行
  kaihuhang(){
    this.jdData.occBankChildCode='';
    this.jdData.occBankChildName='请选择开户支行';
  }



}
