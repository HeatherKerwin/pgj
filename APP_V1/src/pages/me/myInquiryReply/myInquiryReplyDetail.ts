import {Component} from '@angular/core';
import {NavController , NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import {MyInquiryReplyEditPage} from "./myInquriyReplyEdit";
import {QueryYP} from "../../tools/queryYP/queryYP";


@Component({
  selector: 'page-myInquiryReplyDetail',
  templateUrl: 'myInquiryReplyDetail.html'
})

export class MyInquiryReplyDetailPage {
  public isMask:boolean = false;
  public isPay:boolean  = false;

  public draftNo;//票号
  public money;//金额
  public drawer;//出票人
  public payee;//收款人
  public bank;//承兑行
  public bankNo;//承兑行号
  public startDate;//出票日期
  public endDate;//到期日期
  public payState;//订单状态
  public payMoney;//付款金额
  public payWay;//付款方式
  public createTime;//下单日期
  public result;//验票结果
  public no;//查询查复
  public isShow:boolean;

  constructor(public storage: Storage, public navCtrl: NavController, public apiSev: apiService,public params: NavParams) {

  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.isPay = false;
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

  loadData(){
    let id = this.params.get("ID");
    let data = {
      id:id
    }
    this.apiSev.api(
      "post",
      "/app/inquiryReply/inqueryReplyDeatil",
      (res) => {
        if (res.response == 'success') {
          var result = res.data;
          var expressNo = res.expressNo;
          if(result.editState == 0){//允许编辑
            this.isShow = true;
          }
          if(result.draftNo!=null){
            this.draftNo = result.draftNo;
          }else{
            this.draftNo = '';
          }
          if(result.money!=null){
            this.money = result.money+"万元";
          }else{
            this.money = "0万元";
          }
          if(result.drawer!=null){
            this.drawer = result.drawer;
          }else{
            this.drawer = '';
          }
          if(result.payee!=null){
            this.payee = result.payee;
          }else{
            this.payee = '';
          }
          this.bank = result.bank;
          if(result.bankNo!=null){
            this.bankNo = result.bankNo;
          }else{
            this.bankNo = '';
          }
          if(result.startDate!=null){
            this.startDate = result.startDate;
          }else{
            this.startDate = "";
          }
          if(result.endDate!=null){
            this.endDate = result.endDate;
          }else{
            this.endDate = "";
          }
          if(result.payState!=null){
            this.payState = this.apiSev.payStateStr(result.payState);
          }
          if(result.payMoney!=null){
            this.payMoney = result.payMoney;
          }else{
            this.payMoney = '0';
          }
          this.payWay = this.apiSev.getPayWay(result.payWay);
          if(result.createTime!=null){
            this.createTime = result.createTime;
          }else{
            this.createTime = '';
          }
          if(result.no!=null){
            this.no = result.no;
          }else{
            this.no = '';
          }
          var res = result.result;
          if(res!=null && res!=""){
            this.result = res;
          }else{
            this.result = "";
          }
        }
      }, (error) => {},6100,data);
  }

  ToMyInquiryReplyEditPage(e){
    this.navCtrl.push(MyInquiryReplyEditPage,{"ID":this.params.get("ID")});
    e.preventDefault();
  }

  /**
   * 再查一笔
   */
  again(e){
    this.navCtrl.push(QueryYP);
    e.preventDefault();
  }

  ionViewDidEnter(){
    this.loadData();
  }


}
