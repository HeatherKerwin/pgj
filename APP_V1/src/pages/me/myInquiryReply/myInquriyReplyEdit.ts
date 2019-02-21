import { Component } from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-myInquiryReplyEdit',
  templateUrl: 'myInquiryReplyEdit.html'
})

export class MyInquiryReplyEditPage {
  public id;
  public draftNo;
  public money;
  public drawer;
  public payee;
  public bank;
  public bankNo;
  public beginDate = new Date().toISOString();
  public endDate = new Date(new Date().getTime() + 180 * 24 * 3600 * 1000).toISOString();

  public memberId;
  constructor(public storage: Storage, public navCtrl: NavController, public apiSev: apiService, public params: NavParams) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.id = this.params.get("ID");
    this.initData();
    if(this.isOk()){
      return;
    }
  }

  isOk(){
    return true;
  }

  /**
   * 获取基本信息
   */
  initData() {
    let data = {
      id:this.id
    }
    this.apiSev.api(
      "post",
      "/app/inquiryReply/inqueryReplyDeatil",
      (res) => {
        var res = res.data;
        this.draftNo = res.draftNo;
        this.money = res.money;
        this.drawer = res.drawer;
        this.payee = res.payee;
        this.bank = res.bank;
        this.bankNo = res.bankNo;
        var start = res.startDate;
        if(start!=null){
          this.beginDate = start.substring(0,10);
        }
        var end = res.endDate;
        if(end!=null){
          this.endDate = end.substring(0,10);
        }
      }, (error) => {
        this.apiSev.itip('暂无数据');
      }, 500, data);
  }

  /**
   * 保存
   */
  save(){
    let id = this.params.get("ID");
    let draftNo = this.draftNo;
    let money = this.money;
    let drawer = this.drawer;
    let payee = this.payee;
    let bank = this.bank;
    let bankNo = this.bankNo;
    let beginDate = this.beginDate;
    let endDate = this.endDate;
    let memberId = this.memberId;

    if (draftNo == null || draftNo == "") {
      this.apiSev.itip("请输入票号");
      return;
    }else if(draftNo.length!=16 && draftNo.length!=30){
      this.apiSev.itip('你输入的票号位数不对，请仔细核对');
    }
    if(isNaN(money)||Number(money)<1){
      this.apiSev.itip("总金额不能小于1");
      return;
    }
    if(drawer == null || drawer == ""){
      this.apiSev.itip("请输入出票人");
      return;
    }
    if(payee == null || payee == ""){
      this.apiSev.itip("请输入收款人");
      return;
    }
    if(bankNo == null || bankNo == ""){
      this.apiSev.itip("请输入承兑行号");
      return;
    }
    let data = {
      id:id,
      memberId:memberId,
      draftNo:draftNo,
      money:money,
      drawer:drawer,
      payee:payee,
      bank:bank,
      bankNo:bankNo,
      start:beginDate,
      end:endDate
    }
    this.apiSev.api(
      "post",
      "/app/inquiryReply/update",
      (res) => {
        if (res.response == 'success') {
          this.navCtrl.pop();
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
        this.apiSev.itip('暂无数据');
      }, 500, data);
  }

  /**
   * 检查票号
   */
  checkDraftNo(){
    this.draftNo=this.draftNo.replace(/[^\d]/g,'');
  }

}


