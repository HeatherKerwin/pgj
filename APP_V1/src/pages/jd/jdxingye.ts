import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { OpenaccountListBPage } from '../openaccount/openaccountListB';

@Component({
  selector: 'page-jdxingye',
  templateUrl: 'jdxingye.html'
})


export class jdxingyePage {

  public messagecontent: '';
  public memberId:any;
  public orgType:any;
  //开户信息
  public openaccount: any = {
    bankAcctBankBranch: '请选择开户银行支行号'
  };

  public jdData:any={};


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.jdjr();
      this.storage.get('role').then((res) => {
        if (res == 'baojia') {
          this.orgType = 1;
        } else if (res == 'chupiao') {
          this.orgType = 0; //角色类型
        }
      });
    });
  }

  ionViewDidEnter(){
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

  //支行号查询
  banklistB(){
    this.navCtrl.push(OpenaccountListBPage,{
      BANK_TYPE:1,
      ORG_TYPE:this.orgType,
    });
  }


  //取京东信息
  //京东是否开户
  jdjr(){
    let data:any={
      memberId:this.memberId,
    };
    //status 公司状态（0待审核、1审核失败、2正常、3锁定、4无效、5审核通过待鉴定、6鉴定失败）
    this.apiSev.api("newpost", "jdjr/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if (data.data.jdjr != null && data.data.jdjr != '' && data.data.jdjr != 'undefined') {
          //是否正常
          if(data.data.jdjr.status==2){
            this.jdData=data.data.jdjr;
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


  //申请开户
  sava() {
    if(this.openaccount.bankNo == null || this.openaccount.bankNo== ''){
      this.apiSev.itip("请点击选择银行");
      return;
    }
    if(this.orgType==1){
      if(this.openaccount.bankNo != "309391000011"){
        this.apiSev.itip("请选择兴业银行兴业支行");
        return;
      }
    }
    if(this.openaccount.bankAcctAcctNo == null || this.openaccount.bankAcctAcctNo== ''){
      this.apiSev.itip("请填写对应卡号");
      return;
    }
    if(this.openaccount.bankAcctAcctName == null || this.openaccount.bankAcctAcctName== ''){
      this.apiSev.itip("请输入银行账户名！");
      return;
    }
    if(this.openaccount.contactName == null || this.openaccount.contactName== ''){
      this.apiSev.itip("请输入经办人姓名");
      return;
    }
    if(this.openaccount.agentCertNo == null || this.openaccount.agentCertNo== ''){
      this.apiSev.itip("请输入经办人身份证号");
      return;
    }
    if(this.openaccount.agentCertNo.length != 18 && this.openaccount.agentCertNo.length != 15 ){
      this.apiSev.itip("请输入正确的经办人身份证号");
      return;
    }
    if(this.openaccount.contactMobile == null || this.openaccount.contactMobile== ''){
      this.apiSev.itip("请输入经办人的手机号码");
      return;
    }
    if(this.openaccount.contactMobile.length != 11){
      this.apiSev.itip("您输入经办人的手机号码有误,请重新输入");
      return;
    }


    let data:any={
      memberId:'',
      type:'',
      name:'',
      address:'',
      bankAcctBankNo: '',
      bankAcctAcctNo:'',
      bankAcctAcctName:'',
      bankAcctCnapsCode: '',
      bankAcctBankBranch:'支行名称',
      bizLicenceRegisteredNo:'公司注册号',
      bizLicenceName:'公司名称',
      bizLicenceType:'营业执照上公司类型',
      bizLicenceAddr:'营业执照上的住所',
      bizLicenceLegalName:'营业执照上的法人姓名',
      bizLicenceFoundedDate:'营业执照上的成立日期',
      legalCertNo:'法人身份证',
      agentCertNo:this.openaccount.agentCertNo,
      treasurerName:this.openaccount.contactName,
      treasurerCertNo:this.openaccount.agentCertNo,
      orgCodeCertCode:'代码',
      orgCodeCertName:'机构名称',
      orgCodeCertType:'机构类型',
      contactName:this.openaccount.contactName,
      contactMobile:this.openaccount.contactMobile,
      email:this.openaccount.email,
      imgPathA1:'',
      imgPathA2:'',
      imgPath20:'',
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
  }
}
