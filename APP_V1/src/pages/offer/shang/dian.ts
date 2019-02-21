import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-dian',
  templateUrl: 'dian.html'
})
export class DianPage {
  public limit;//商票报价额度
  public remarkSp;//商票备注
  public dianId = null;
  public dianprice1;
  public dianprice2;
  public diandate1;
  public diandate2;

  public orgId;
  public memberId;

  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService) {
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      this.loadPrice();
      this.loadData();
    })
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
        if(res.response == 'success') {
          if (res.dianpiao != null) {
            var dianpiao = res.dianpiao;
            this.dianId = dianpiao.id;
            this.dianprice1 = dianpiao.minPrice;
            this.dianprice2 = dianpiao.maxPrice;
            this.diandate1 = dianpiao.minDay;
            this.diandate2 = dianpiao.maxDay;
          }
        }else{
          this.apiSev.itip(res.msg);
        }
      }, (error) => {
      }, 1, data);
  }

  //判断输入的大小是否符合
  checkprice(num) {
    if (num == 1) {
      if ((parseFloat(this.dianprice1) >= parseFloat(this.dianprice2))||(parseFloat(this.dianprice1)<0)) {
        this.dianprice1 = "";
        this.apiSev.itip("输入不规范");
      }
    } else if (num == 2) {
      if ((parseFloat(this.dianprice1) >= parseFloat(this.dianprice2))||(parseFloat(this.dianprice2)<0)) {
        this.dianprice2 = "";
        this.apiSev.itip("输入不规范");
      }
    } else if (num == 3) {
      if (parseFloat(this.diandate1) >= parseFloat(this.diandate2)) {
        this.diandate1 = "";
        this.apiSev.itip("输入不规范");
      }
    } else if (num == 4) {
      if (parseFloat(this.diandate1) >= parseFloat(this.diandate2)) {
        this.diandate2 = "";
        this.apiSev.itip("输入不规范");
      }
    }
  }
  /**
   * 提交报价
   */
  save(){
    if(this.dianprice1==null ||this.dianprice2==null ||this.diandate1==null ||this.diandate2==null){
      this.apiSev.itip("输入不规范");
      return;
    }
    let limit = this.limit;
    let remarkSp = this.remarkSp;
    let price1 = this.dianprice1;
    var price2 = this.dianprice2;
    let day1 = this.diandate1;
    let day2 = this.diandate2;
    let orgId = this.orgId;
    let memberId = this.memberId;
    let dianId = this.dianId;
    if(limit == null) {
      this.apiSev.itip("请填写额度");
      return;
    }else if(limit<=0){
      this.apiSev.itip("请填写正确的额度");
      return;
    }
    if(price1 == null || price2 == null) {
      this.apiSev.itip("请填写完整的报价");
      return;
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
      dianId:dianId,
      type:'2'
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
