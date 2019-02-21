import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-zhi',
  templateUrl: 'zhi.html'
})
export class ZhiPage {
  public limit;//商票报价额度
  public remarkSp;//商票备注
  public zhiId = null;
  public zhiprice1;
  public zhiprice2;
  public zhidate1;
  public zhidate2;

  public orgId;
  public memberId;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService) {
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      this.loadPrice();//加载报价额度
      this.loadData();//加载报价数据
    });
  }

  loadPrice(){
    var data = {orgId:this.orgId};
    this.apiSev.api(
      "post",
      "/app/orgLimit/get/priceByOrgId",
      (res) => {
        if(res.response == 'success') {
          if(res.orgLimit != null){
            this.limit = res.orgLimit.priceSp;
            this.remarkSp = res.orgLimit.remarkSp;
          }
        }
      }, (error) => {
      }, 1, data);
  }

  /**
   * 初始化数据
   */
  loadData(){
    var data = {orgId:this.orgId};
    this.apiSev.api(
      "post",
      "/app/requirementsp/getbyorgid",
      (res) => {
        if (res.zhipiao != null) {
          var zhipiao = res.zhipiao;
          this.zhiId = zhipiao.id;
          this.zhiprice1 = zhipiao.minPrice;
          this.zhiprice2 = zhipiao.maxPrice;
          this.zhidate1 = zhipiao.minDay;
          this.zhidate2 = zhipiao.maxDay;
        }
      }, (error) => {
      }, 1, data);
  }

  //判断输入的大小是否符合
  checkprice(num) {
    if (num == 1) {
      if ((parseFloat(this.zhiprice1) >= parseFloat(this.zhiprice2))||(parseFloat(this.zhiprice1)<0)) {
        this.zhiprice1 = "";
        this.apiSev.itip("输入不规范");
      }
    } else if (num == 2) {
      if ((parseFloat(this.zhiprice1) >= parseFloat(this.zhiprice2))||(parseFloat(this.zhiprice2)<0)) {
        this.zhiprice2 = "";
        this.apiSev.itip("输入不规范");
      }
    } else if (num == 3) {
      if (parseFloat(this.zhidate1) >= parseFloat(this.zhidate2)) {
        this.zhidate1 = "";
        this.apiSev.itip("输入不规范");
      }
    } else if (num == 4) {
      if (parseFloat(this.zhidate1) >= parseFloat(this.zhidate2)) {
        this.zhidate2 = "";
        this.apiSev.itip("输入不规范");
      }
    }
  }

  /**
   * 提交报价
   */
  save(){

    let limit = this.limit;
    let remarkSp = this.remarkSp;
    let price1 = this.zhiprice1;
    var price2 = this.zhiprice2;
    let day1 = this.zhidate1;
    let day2 = this.zhidate2;
    let orgId = this.orgId;
    let memberId = this.memberId;
    let zhiId = this.zhiId;
    if(limit == null) {
      this.apiSev.itip("请填写额度");
      return true;
    }
    if(price1 == null || price2 == null) {
      this.apiSev.itip("请填写完整的报价");
      return true;
    }
    let data = {
      priceSp:limit,
      remarkSp:remarkSp,
      minPrice:price1,
      maxPrice:price2,
      minDay:day1,
      maxDay:day2,
      orgId:orgId,
      memberId:memberId,
      zhiId:zhiId,
      type:'1'
    };
    this.apiSev.api(
      "post",
      "/app/requirementsp/save",
      (res) => {
        if (res.response == 'success') {
          this.apiSev.alert('温馨提示','已报价成功，请等待票据管家给你派送商票订单吧','知道了',);
        } else if (res.response == 'error') {
          this.apiSev.itip("电票报价修改失败");
        } else if (res.response == 'fail') {
          this.apiSev.itip("请确认是否修改了");
        } else {}
        this.apiSev.itip(res.msg);
      }, (error) => {
      }, 1, data);
  }

}
