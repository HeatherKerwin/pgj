import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-addmanage',
  templateUrl: 'addmanage.html'
})


export class AddmanagePage {
  segmentsArray = ['manage1', 'manage2', 'manage3'];
  pet: any = this.segmentsArray[0];


  //票据类型
  public discounttype: any = 'YD';

  public messagecontent: '';
  public memberId;

  public manage: any = {
    tiexianDate: new Date().toISOString(),//贴现日期
    inData: new Date().toISOString(),//入账日期
    expiryDate: new Date(new Date().getTime() + 183 * 24 * 3600 * 1000).toISOString(),//到期日期
    noticeDate: new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//提醒日期
    expirySDate:  new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//质押赎回日期
    min: '1990-01-01',//到期日期最小值
    txjx: 0,   //贴现计息
  };


  //指引
  public zy6: boolean = false;

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });

    this.storage.get('manage6').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy6=true;
        this.storage.set('manage6', 1);
      }
    });
  }


  //到期时间和提醒时间联调
  timechanger(){
    this.manage.noticeDate=new Date(new Date(this.manage.expiryDate).getTime() - 10 * 24 * 3600 * 1000).toISOString();
  }

  //保存
  draftrecordsave() {
    if (this.manage.allmoney == null || this.manage.allmoney == '' || this.manage.allmoney == 'undefined') {
      this.apiSev.itip("请输入票面金额");
      return;
    }

    let data: any = {
      memberId: this.memberId, //用户主键
      //recordType:this.manage.recordType, //类型（HOLD持有、OUT已出票、IN待入账）
      draftType:this.discounttype, //票据类型（YZ银纸、YD银电、SZ商纸、SD商电）
      //bank:this.manage.bank, //承兑行全称
      //type2:'', //承兑行类型（1国股、2城商（小商）、3外资、4农商、5农合、6农信、7村镇、8城商（大商））
      //draftNo:this.manage.draftNo, //票号
      allmoney:(this.manage.allmoney*10000), //票面金额（元）
      //txje:this.manage.txje, //贴现金额（元）
      //inData:this.manage.inData, //入账日期
      //tiexianDate:this.manage.tiexianDate, //贴现日期
      //expiryDate:this.manage.expiryDate, //到期日期
      //noticeDate:this.manage.noticeDate, //提醒日期（默认设置为提醒日期前十天）仅限持有
      //draftFrom:this.manage.draftFrom, //票据来源
      //draftTo:this.manage.draftTo //票据去处
    };
    if(this.pet=='manage1'){
      if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
        this.apiSev.itip("请输入承兑行全称");
        return;
      }
      if (this.manage.txje == null || this.manage.txje == '' || this.manage.txje == 'undefined') {
        this.apiSev.itip("请输入实收票款");
        return;
      }
      data.recordType='OUT';
      data.tiexianDate=new Date(this.manage.tiexianDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.tiexianDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.tiexianDate).getDate(); //贴现日期
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      //data.txje=(this.manage.txje*10000); //贴现金额（元）
      data.txje=this.manage.txje;
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
      this.apiSev.newState=1;
    }else if(this.pet=='manage2'){
      if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
        this.apiSev.itip("请输入承兑行全称");
        return;
      }
      data.recordType='HOLD';
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      data.noticeDate=new Date(this.manage.noticeDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.noticeDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.noticeDate).getDate();
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
      this.apiSev.newState=2;
    }else if(this.pet=='manage3') {
      data.recordType='IN';
      data.inData=new Date(this.manage.inData).getFullYear()+ "-" + ("0"+(new Date(this.manage.inData).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.inData).getDate();
      data.draftFrom=this.manage.draftFrom; //票据来源
      this.apiSev.newState=3;
    } else if(this.pet=='manage4') {
      if(this.manage.pledgemoney>0){

      }else {
        this.apiSev.itip("请输入质押金额");
        return;
      }

      data.recordType='PLEDGE';
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
      data.pledgeMoney=this.manage.pledgemoney;            //质押金额
      data.pledgeOutDate=new Date(this.manage.tiexianDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.tiexianDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.tiexianDate).getDate();         //质押时间
      data.pledgeInDate=new Date(this.manage.expirySDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expirySDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expirySDate).getDate();          //赎回时间
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      this.apiSev.newState=9;
    }


    this.apiSev.api("newpost", "draftrecord/save", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.apiSev.itip("保存成功");
        this.navCtrl.pop();
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }


  //清空
  cleraData(){
    this.manage = {
      tiexianDate: new Date().toISOString(),//贴现日期
      inData: new Date().toISOString(),//入账日期
      expiryDate: new Date(new Date().getTime() + 183 * 24 * 3600 * 1000).toISOString(),//到期日期
      noticeDate: new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//提醒日期
      min: new Date().toISOString(),//到期日期最小值
      expirySDate:new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//赎回日期
      bank:'',
      draftNo:'',
      allmoney:'',
      txje:'',
      draftFrom:'',
      draftTo:''
    };
    this.discounttype='YD';
  }

}
