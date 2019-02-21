import { Component } from '@angular/core';
import { NavController, ViewController,AlertController,NavParams,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountListPage } from "./openaccountList";
import { OpenaccountListBPage } from "./openaccountListB";
import { StatementPage } from './statement';
import { StatementBPage } from './statementB';
import { MePage } from '../me/me';
import { MePageB } from '../me/meB';

@Component({
  selector: 'page-openaccountE',
  templateUrl: 'openaccountE.html'
})


export class OpenaccountEPage {

  public memberId='';
  public orgType:any;
  public meType:any;
  public info_state:any;
  public cib:any={
    id:'',
  };

  //提示语判断
  public bingDing = false;
  public bingDings = true;

  //企业角标判断
  public enterprises=true;
  public enterprise = false;
  //银行角标判断
  public bankinfo = true;
  public bankinfos = false;
  //经办人
  public handler = true;
  public handlers = false;
  //财务
  public finance = true;
  public finances = false;
  //机构信用代码
  public mechanism = true;
  public mechanisms = false;
  //开户许可证
  public licence = true;
  public licences = false;
  //税务登记号
  public tax = true;
  public taxs = false;
  //组织机构代码
  public organization = true;
  public organizations = false;

  public judgeA = true; //行号判断
  public judgeB = false;

  public openaccount: any = {
    name: '',  //企业名称
    bizLicenceRegisteredNo: '',  //统一信用代码
    contactMobile:'',   //联系人手机号
    email:'',           //邮箱
    contactName:'',     //联系人姓名
    address:'',         //联系人公司地址
    agentCertNo:'',   //联系人的身份证号码
    bizLicenceType:'',  //营业执照上公司类型
    bizLicenceAddr:'',  //营业执照上的住所
    bizLicenceFoundedDate:'', //入营业执照上的成立日期
    bizLicenceLegalName:'',  //营业执照上的法人姓名
    legalCertNo:'', //营业执照上的法人身份证
    bankAcctAcctName:'', //卡号名
    bankAcctBankBranch:'', //支行名称
    CnapsCode:'',     //支行行号
    bankAcctAcctNo:'',  //卡号
    bankNo:'', //企业开户行行号
    bankName:'',            //企业开户行名称
    treasurerName:'', //财务姓名
    treasurerCertNo:'', //财务身份证号
    //机构信用代码
    orgCreditCodeCertCode:'', //代码
    orgCreditCodeCertName:'', //名称
    orgCreditCodeCertAddr:'',//地址
    orgCreditCodeCertExpiredDate:'',  //有效期
    //开户许可证
    acctPermitNo:'', //编号
    acctPermitPermitNo:'', //核准号
    //税务登记号
    taxCertGovTaxNo:'',  //国税
    taxCertLocalTaxNo:'', //地税
    taxCertName:'',  //纳税人名称
    //组织机构代码
    orgCodeCertCode:'', //代码
    orgCodeCertName:'', //机构名称
    orgCodeCertType:'', //机构类型
  };

  constructor(public storage: Storage, public navCtrl: NavController, public loadingCtrl: LoadingController,public viewCtrl: ViewController, public apiSev: apiService,public alertCtrl: AlertController,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.apiSev.pass='0';
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
        this.openaccounTactive();
      });
    });
    this.meType = this.params.get("ME_TYPE"); //我类型
  }

  ionViewDidEnter(){
    if (this.apiSev.parmData != null && this.apiSev.parmData != '') {
      //取银行值
      let bankNo = this.apiSev.parmData.bankNo;
      if (bankNo != null && bankNo != '') {
        this.openaccount.bankNo = bankNo;
        this.apiSev.parmData.bankNo = '';
      }
      let bankName = this.apiSev.parmData.bankName;
      if (bankName != null && bankName != '') {
        this.openaccount.bankName = bankName;
        this.openaccount.bankAcctBankBranch = bankName;
        this.apiSev.parmData.bankName = '';
      }
      let CnapsCode = this.apiSev.parmData.CnapsCode;
      if (CnapsCode != null && CnapsCode != '') {
        this.openaccount.CnapsCode = CnapsCode;
        this.apiSev.parmData.CnapsCode = '';
      }
    }
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
  //经办人
  Tohandler(){
    this.handler = !this.handler;
    if (this.handler == true) {
      this.handlers = false
    } else if (this.handler == false) {
      this.handlers = true
    }
  }
  //财务
  Tofinance(){
    this.finance = !this.finance;
    if(this.finance==true){
      this.finances = false;
    }else if(this.finance==false){
      this.finances = true;
    }
  }
  //机构信用代码
  Tomechanism(){
    this.mechanism = !this.mechanism;
    if(this.mechanism==true){
      this.mechanisms = false;
    }else if(this.mechanism==false){
      this.mechanisms = true;
    }
  }
  //开户许可证
  Tolicence(){
    this.licence = !this.licence;
    if(this.licence==true){
      this.licences = false;
    }else if(this.licence==false){
      this.licences = true;
    }
  }
  //税务登记号
  Totax(){
    this.tax = !this.tax;
    if(this.tax==true){
      this.taxs = false;
    }else if(this.tax==false){
      this.taxs = true;
    }
  }

  //修改
  Appraisal() {
    if (this.openaccount.bizLicenceType == null || this.openaccount.bizLicenceType == '') {
      this.apiSev.itip("请输入营业执照上公司类型");
      return;
    }
    if (this.openaccount.bizLicenceAddr == null || this.openaccount.bizLicenceAddr == '') {
      this.apiSev.itip("请输入营业执照上的住所");
      return;
    }
    if (this.openaccount.bizLicenceFoundedDate == null || this.openaccount.bizLicenceFoundedDate == '') {
      this.apiSev.itip("请输入营业执照上的成立日期");
      return;
    }
    if (this.openaccount.bizLicenceLegalName == null || this.openaccount.bizLicenceLegalName == '') {
      this.apiSev.itip("请输入营业执照上的法人姓名");
      return;
    }
    if (this.openaccount.legalCertNo == null || this.openaccount.legalCertNo == '') {
      this.apiSev.itip("请输入营业执照上的法人身份证");
      return;
    }
    if (this.openaccount.legalCertNo.length != 18 && this.openaccount.legalCertNo.length != 16) {
      this.apiSev.itip("请输入正确的法人身份证");
      return;
    }
    if (this.openaccount.bankAcctAcctNo == null || this.openaccount.bankAcctAcctNo == '') {
      this.apiSev.itip("请填写对应卡号");
      return;
    }
    if(this.openaccount.bankNo == null || this.openaccount.bankNo== ''){
      this.apiSev.itip("请点击选择银行");
      return;
    }
    if(this.openaccount.bankAcctAcctName == null || this.openaccount.bankAcctAcctName== ''){
      this.apiSev.itip("请输入银行账户名！");
      return;
    }
    if(this.orgType==1){
      if(this.openaccount.bankNo != "309391000011"){
        this.apiSev.itip("请选择兴业银行兴业支行");
        return;
      }
    }
    if (this.openaccount.contactName == null || this.openaccount.contactName == '') {
      this.apiSev.itip("请输入经办人姓名");
      return;
    }

    if (this.openaccount.agentCertNo == null || this.openaccount.agentCertNo == '') {
      this.apiSev.itip("请输入经办人身份证号");
      return;
    }
    if (this.openaccount.agentCertNo.length != 18 && this.openaccount.agentCertNo.length != 16) {
      this.apiSev.itip("请输入正确的经办人身份证号");
      return;
    }
    if (this.openaccount.contactMobile == null || this.openaccount.contactMobile == '') {
      this.apiSev.itip("请输入经办人的手机号码");
      return;
    }
    if (this.openaccount.contactMobile.length != 11) {
      this.apiSev.itip("您输入经办人的手机号码有误,请重新输入");
      return;
    }
    if (this.openaccount.treasurerName == null || this.openaccount.treasurerName == '') {
      this.apiSev.itip("请输入财务姓名");
      return;
    }
    if (this.openaccount.treasurerCertNo == null || this.openaccount.treasurerCertNo == '') {
      this.apiSev.itip("请输入财务身份证号");
      return;
    }
    if (this.openaccount.treasurerCertNo.length != 18 && this.openaccount.treasurerCertNo.length != 16) {
      this.apiSev.itip("请输入正确的财务身份证号");
      return;
    }

    let data: any = {
      id:this.cib.id,
      memberId: this.memberId,
      type: this.orgType,
      name: this.openaccount.name,
      address: this.openaccount.bizLicenceAddr,
      bankAcctBankNo: this.openaccount.bankNo,
      bankAcctAcctNo:this.openaccount.bankAcctAcctNo,
      bankAcctAcctName: this.openaccount.bankAcctAcctName,
      bankAcctCnapsCode: this.openaccount.CnapsCode,
      bankAcctBankBranch:this.openaccount.bankAcctBankBranch,
      bizLicenceRegisteredNo: this.openaccount.bizLicenceRegisteredNo,
      bizLicenceName: this.openaccount.name,
      bizLicenceType: this.openaccount.bizLicenceType,
      bizLicenceAddr: this.openaccount.bizLicenceAddr,
      bizLicenceLegalName: this.openaccount.bizLicenceLegalName,
      bizLicenceFoundedDate: this.openaccount.bizLicenceFoundedDate,
      legalCertNo: this.openaccount.legalCertNo,
      agentCertNo: this.openaccount.agentCertNo,
      treasurerName: this.openaccount.treasurerName,
      treasurerCertNo: this.openaccount.treasurerCertNo,
      orgCodeCertCode: this.openaccount.bizLicenceRegisteredNo,
      orgCodeCertName: this.openaccount.name,
      orgCodeCertType:this.openaccount.bizLicenceType,
      contactName: this.openaccount.contactName,
      contactMobile: this.openaccount.contactMobile,
      email: this.openaccount.email,
    };
    if(this.openaccount.orgCreditCodeCertCode == null){
      data.orgCreditCodeCertCode = '-';
    }else {
      data.orgCreditCodeCertCode = this.openaccount.orgCreditCodeCertCode;
    }
    if(this.openaccount.orgCreditCodeCertName == null){
      data.orgCreditCodeCertName = '-';
    }else {
      data.orgCreditCodeCertName = this.openaccount.orgCreditCodeCertName;
    }
    if(this.openaccount.orgCreditCodeCertAddr == null){
      data.orgCreditCodeCertAddr = '-';
    }else {
      data.orgCreditCodeCertAddr = this.openaccount.orgCreditCodeCertAddr;
    }
    if(this.openaccount.orgCreditCodeCertExpiredDate == null){
      data.orgCreditCodeCertExpiredDate = '-';
    }else {
      data.orgCreditCodeCertExpiredDate = this.openaccount.orgCreditCodeCertExpiredDate;
    }
    if(this.openaccount.taxCertGovTaxNo == null){
      data.taxCertGovTaxNo = '-';
    }else {
      data.taxCertGovTaxNo = this.openaccount.taxCertGovTaxNo;
    }
    if(this.openaccount.taxCertLocalTaxNo == null){
      data.taxCertLocalTaxNo = '-';
    }else {
      data.taxCertLocalTaxNo = this.openaccount.taxCertLocalTaxNo;
    }
    if(this.openaccount.taxCertName == null){
      data.taxCertName = '-';
    }else {
      data.taxCertName = this.openaccount.taxCertName;
    }
    if(this.openaccount.acctPermitNo == null){
      data.acctPermitNo = '-';
    }else {
      data.acctPermitNo = this.openaccount.acctPermitNo;
    }
    if(this.openaccount.acctPermitPermitNo == null){
      data.acctPermitPermitNo = '-';
    }else {
      data.acctPermitPermitNo = this.openaccount.acctPermitPermitNo;
    }

    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.apiSev.api("newpost", "/cib/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.apiSev.itip("修改成功")
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
    loading.dismiss();
  }

  openaccounTactive(){
    let data:any={
      memberId:this.memberId,
      type:this.orgType,
    };
    this.apiSev.api("newpost", "orginfo/rz/", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.cib = data.data.cib;
        this.cib.id = data.data.cib.id;
        this.info_state = data.data.info_state;
        this.cibget();
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
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
        this.openaccount.name = openaccount.name;
        this.openaccount.address = openaccount.address;
        this.openaccount.bankAcctAcctNo = openaccount.bankAcctAcctNo;
        this.openaccount.bankAcctAcctName = openaccount.bankAcctAcctName;
        this.openaccount.bizLicenceRegisteredNo = openaccount.bizLicenceRegisteredNo;
        this.openaccount.bizLicenceType = openaccount.bizLicenceType;
        this.openaccount.bizLicenceName = openaccount.bizLicenceName;
        this.openaccount.bizLicenceAddr = openaccount.bizLicenceAddr;
        this.openaccount.bizLicenceLegalName = openaccount.bizLicenceLegalName;
        this.openaccount.bizLicenceFoundedDate = openaccount.bizLicenceFoundedDate;
        this.openaccount.legalCertNo = openaccount.legalCertNo;
        this.openaccount.agentCertNo = openaccount.agentCertNo;
        this.openaccount.treasurerName = openaccount.treasurerName;
        this.openaccount.treasurerCertNo = openaccount.treasurerCertNo;
        this.openaccount.orgCodeCertCode = openaccount.orgCodeCertCode;
        this.openaccount.orgCodeCertName = openaccount.orgCodeCertName;
        this.openaccount.orgCodeCertType = openaccount.orgCodeCertType;
        this.openaccount.bizLicenceType = openaccount.bizLicenceType;
        this.openaccount.contactName = openaccount.contactName;
        this.openaccount.contactMobile = openaccount.contactMobile;
        this.openaccount.email = openaccount.email;
        this.openaccount.orgCreditCodeCertCode = openaccount.orgCreditCodeCertCode;
        this.openaccount.orgCreditCodeCertName = openaccount.orgCreditCodeCertName;
        this.openaccount.orgCreditCodeCertAddr = openaccount.orgCreditCodeCertAddr;
        this.openaccount.orgCreditCodeCertExpiredDate = openaccount.orgCreditCodeCertExpiredDate;
        this.openaccount.taxCertGovTaxNo = openaccount.taxCertGovTaxNo;
        this.openaccount.taxCertLocalTaxNo = openaccount.taxCertLocalTaxNo;
        this.openaccount.taxCertName = openaccount.taxCertName;
        this.openaccount.acctPermitNo = openaccount.acctPermitNo;
        this.openaccount.acctPermitPermitNo = openaccount.acctPermitPermitNo;
        this.openaccount.bankNo = openaccount.bankAcctBankNo;
        this.openaccount.bankAcctBankBranch = openaccount.bankAcctBankBranch;
        this.openaccount.CnapsCode = openaccount.bankAcctCnapsCode;
        if(openaccount.status == 1|| this.info_state == 'NOPASS'){
          this.bingDing = true;
          this.bingDings = false;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //行号查询
  banklist(){
    this.navCtrl.push(OpenaccountListPage,{
      BANK_TYPE:2,
      ORG_TYPE:this.orgType,
      ME_TYPE:this.meType,
    });
  }
  //支行号查询
  banklistB(){
    this.navCtrl.push(OpenaccountListBPage,{
      BANK_TYPE:1,
      ORG_TYPE:this.orgType,
      ME_TYPE:this.meType,
    });
  }

  newup(){
    if(this.orgType == 1 ){
      this.navCtrl.push(StatementPage,{
        ORG_TYPE:this.orgType,
        ME_TYPE:1
      });
    }else if(this.orgType == 0){
      this.navCtrl.push(StatementBPage,{
        ORG_TYPE:this.orgType,
        ME_TYPE:0
      });
    }
  }

  //返回
  closeme(){
    if(this.meType==1){
      this.navCtrl.setRoot(MePage,{
        INDEX:5
      });
    }else if(this.meType==0){
      this.navCtrl.setRoot(MePageB,{
        INDEX:5
      });
      }else {
      this.navCtrl.pop();
    }
  }

  //修改图片
  Appraisalimg(){
    this.navCtrl.push(OpenaccountListPage, {
      ORG_TYPE: this.orgType,
    });
  }
}
