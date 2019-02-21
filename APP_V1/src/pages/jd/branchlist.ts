import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-branchlist',
  templateUrl: 'branchlist.html'
})


export class BranchlistPage {

    public messagecontent: '';
    public memberId;

  //省联动
  public province:any='请选择省';
  public provincelist:any=[];
  //市联动
  public city:any='请选择市';
  public citylist:any=[];

  //银行分页
  public banklists:any=[];

  //城市编号
  public bankCode:any;
  //银行编号
  public cityCode:any;

  public bankinfo:any;

  //关键字
  public keyword:any;


  constructor(public storage: Storage,public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.bankinfo = this.params.get("bankinfo");
    this.queryprovince();
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
  querycity() {
    let data: any = {
      provinceCode:this.province,
    };
    this.apiSev.api("newpost", "jdjr/querycity", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.citylist=data.data.list;
        this.cityCode=this.citylist[0].cityCode;
        this.city=this.citylist[0].cityCode;
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //获取省份内的城市信息
  querybankcnapsinfo() {
    if (this.province == '请选择省' || this.province == null || this.province == '' || this.province == 'undefined') {
      this.apiSev.itip("请先选择省");
      return
    }


    let data: any = {
      bankCode:this.bankinfo,
      cityCode:this.city,
    };
    this.apiSev.api("newpost", "jdjr/querybankcnapsinfo", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.banklists=data.data.list;
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  draftcalculatesave(){
    if (this.province == '请选择省' || this.province == null || this.province == '' || this.province == 'undefined') {
      this.apiSev.itip("请先选择省");
      return
    }

    if(this.city=='请选择市'||this.city==null||this.city==''||this.city=='undefined'){
      this.apiSev.itip("请先选择市");
      return
    }

    if(this.keyword==null||this.keyword==''||this.keyword=='undefined'){
      this.apiSev.itip("请输入搜索支行的关键字");
      return
    }
    this.banklists=[];
    let data: any = {
      bankCode:this.bankinfo,
      cityCode:this.city,
    };
    this.apiSev.api("newpost", "jdjr/querybankcnapsinfo", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if(objs.length>0){
          for (let i=0;i<objs.length;i++) {
            if(objs[i].bankCnapsName.indexOf(this.keyword) != -1||objs[i].bankCnapsCode.indexOf(this.keyword) != -1){
              this.banklists.push(objs[i]);
            }
          }
        }
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }



  myColumn(bankCnapsCode,bankCnapsName){
    this.apiSev.occBankChildName=bankCnapsName;
    this.apiSev.occBankChildCode=bankCnapsCode;
    this.navCtrl.pop()
  }



}
