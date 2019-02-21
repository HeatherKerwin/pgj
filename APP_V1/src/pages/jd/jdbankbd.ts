import {Component} from '@angular/core';
import {NavController, ViewController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {BranchlistBPage} from "./branchlistB";
import {jdbankbdBPage} from "./jdbankbdB";

@Component({
  selector: 'page-jdbankbd',
  templateUrl: 'jdbankbd.html'
})


export class jdbankbdPage {

  public messagecontent: '';
  public memberId;

  public jd: any = {};
  //省联动
  public province: any = '请选择省';
  //市联动
  public city: any = '请选择市';
  //银行联动
  public banks: any = '请选择开户行';
  //开户行
  public bankListResult: any = [];

  //京东数据
  public jdData:any={
    bankChildName:'请选择支行',

  };

  constructor(public storage: Storage,public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.jdjr();
    });
    this.jd.bankName = this.params.get("bankName");
  }


  ionViewDidEnter() {
    this.queryBankList();
    if (this.apiSev.jdjrcard != null && this.apiSev.jdjrcard != '' && this.apiSev.jdjrcard != 'undefined'&& this.apiSev.jdjrcard!={}) {
      if(this.apiSev.jdjrcard.bankChildName!=null&&this.apiSev.jdjrcard.bankChildName!='undefined'&&this.apiSev.jdjrcard.bankChildName!=''){
        this.jdData.bankChildName = this.apiSev.jdjrcard.bankChildName;
      }
      if(this.apiSev.jdjrcard.bankCnaps!=null&&this.apiSev.jdjrcard.bankCnaps!='undefined'&&this.apiSev.jdjrcard.bankCnaps!='') {
        this.jdData.bankCnaps = this.apiSev.jdjrcard.bankCnaps;
      }
      if(this.apiSev.jdjrcard.provinceId!=null&&this.apiSev.jdjrcard.provinceId!='undefined'&&this.apiSev.jdjrcard.provinceId!='') {
        this.jdData.provinceId = this.apiSev.jdjrcard.provinceId;
      }
      if(this.apiSev.jdjrcard.provinceName!=null&&this.apiSev.jdjrcard.provinceName!='undefined'&&this.apiSev.jdjrcard.provinceName!='') {
        this.jdData.provinceName = this.apiSev.jdjrcard.provinceName;
      }
      this.jdData.cityId = this.apiSev.jdjrcard.cityId;
      this.jdData.cityName = this.apiSev.jdjrcard.cityName;

      this.apiSev.jdjrcard={
        bankChildName:'',
        bankCnaps:'',
        provinceId:'',
        provinceName:'',
        cityId:'',
        cityName:''
      }
    }
  }


  //去往审核中
  sava() {
    if (this.banks == '请选择开户行') {
      this.apiSev.itip("请先选择开户行");
      return;
    }
    if (this.jdData.bankChildName == '请选择支行'||this.jdData.bankChildName ==''||this.jdData.bankChildName ==null||this.jdData.bankChildName =='undefined') {
      this.apiSev.itip("请先请选择支行");
      return;
    }

    if (this.jdData.legalendTime ==''||this.jdData.legalendTime ==null||this.jdData.legalendTime =='undefined') {
      this.apiSev.itip("请输入绑定的银行卡");
      return;
    }

    if (this.jdData.legalendTime.toString().substr(0, 1) != '0' && this.jdData.legalendTime.length > 7 && this.jdData.legalendTime.length <= 27) {

    } else {
      this.apiSev.itip("请输入非0数字开头7到27位的银行卡号");
      return;
    }


    let bankCode:any;
    let bankName:any;

    let list = this.bankListResult;
    if (list.length > 0) {
      for (let i = 0; i < list.length; i++) {
        if (list[i].cnapBankCode == this.banks) {
          bankCode=list[i].bankCode;
          bankName=list[i].bankName;
        }
      }
    }



    let data: any = {
      memberId:this.memberId, //用户主键
      bankCode:bankCode, //银行编码
      bankName:bankName, //开户行名称
      bankCnaps:this.jdData.bankCnaps, //开户行联行号
      bankChildName:this.jdData.bankChildName, //支行银行全称
      bankAccountName:this.jd.blicCompanyName, //账号名称
      bankAccountNo:this.jdData.legalendTime, //银行账号
      provinceId:this.jdData.provinceId,//省ID
      provinceName:this.jdData.provinceName, //省名称
      cityId:this.jdData.cityId, //城市ID
      cityName:this.jdData.cityName, //城市名称
    };
    this.apiSev.api("newpost", "jdjrcard/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(jdbankbdBPage,{
          BANK:data.data,
          SOURCE:1
        });
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //查询支持小额打款银行列表
  queryBankList() {
    let data: any = {};
    this.apiSev.api("newpost", "jdjrcard/queryBankList", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.bankListResult = data.data.bankListResult
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


  //点击城市判断
  xuanz1() {
    if (this.banks == '请选择开户行') {
      this.apiSev.itip("请选择开户行");
      return;
    }

    this.navCtrl.push(BranchlistBPage,{
      bankinfo:this.banks,
    });
  }

  xuanz2() {
    this.jdData.bankChildName = '请选择支行';
    this.jdData.bankCnaps = '';
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
        if (data.data!= null && data.data != '' && data.data != 'undefined') {
          this.jd = data.data;
        }else {
          this.jdjrbind();
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //京东是否绑定
  jdjrbind(){
    let data:any={
      memberId:this.memberId,
    };
    this.apiSev.api("newpost", "jdjrbind/get/jdjr/jdjrbind", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.jd = data.data.jdjr;
        return;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}
