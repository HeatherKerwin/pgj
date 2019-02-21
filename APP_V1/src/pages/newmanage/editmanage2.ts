import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Editmanage3Page } from './editmanage3';

@Component({
  selector: 'page-editmanage2',
  templateUrl: 'editmanage2.html'
})


export class Editmanage2Page {

  public messagecontent: '';
  public memberId;
  public titlename: any = '票据编辑';

  public pet:any='manage3';

  public discounttype:any='YD';

  public manage: any = {
    tiexianDate: new Date().toISOString(),//贴现日期
    inData: new Date().toISOString(),//入账日期
    expiryDate: new Date(new Date().getTime() + 183 * 24 * 3600 * 1000).toISOString(),//到期日期
    noticeDate: new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//提醒日期
    min: '1990-01-01',//到期日期最小值
  };

  public item: any = {};
  //指引
  public zy8: boolean = false;

  constructor(public storage: Storage,public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    this.item = this.params.get("ITEM");
    this.draftrecordget();

    this.storage.get('manage8').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy8=true;
        this.storage.set('manage8', 1);
      }
    });
  }


  //查看详情
  draftrecordget() {
    let data: any = {
      id: this.item.id,
    };
    this.apiSev.api("newpost", "draftrecord/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.manage=data.data;
        this.manage.allmoney=this.manage.allmoney/10000;
        this.manage.txje=this.manage.txje;
        this.discounttype=this.manage.draftType;
        if(this.manage.expiryDate!=null&&this.manage.expiryDate!=''&&this.manage.expiryDate!='undefined'){
          this.manage.expiryDate=new Date(new Date(this.manage.expiryDate).getTime() + 24 * 3600 * 1000).toISOString();
        }
        if(this.manage.noticeDate!=null&&this.manage.noticeDate!=''&&this.manage.noticeDate!='undefined'){
          this.manage.noticeDate=new Date(new Date(this.manage.noticeDate).getTime() + 24 * 3600 * 1000).toISOString();
        }
        if(this.manage.expiryDate!=null&&this.manage.expiryDate!=''&&this.manage.expiryDate!='undefined'){
          this.manage.tiexianDate=new Date(new Date(this.manage.tiexianDate).getTime() + 24 * 3600 * 1000).toISOString();
        }
        if(this.manage.inData!=null&&this.manage.inData!=''&&this.manage.inData!='undefined'){
          this.manage.inData=new Date(new Date(this.manage.inData).getTime() + 24 * 3600 * 1000).toISOString();
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }


  //保存
  draftrecordsave() {
    if (this.manage.allmoney == null || this.manage.allmoney == '' || this.manage.allmoney == 'undefined') {
      this.apiSev.itip("请输入票面金额");
      return;
    }

    let data: any = {
      id:this.item.id,
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
      data.txje=this.manage.txje; //贴现金额（元）
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
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
    }else {
      data.recordType='IN';
      data.inData=new Date(this.manage.inData).getFullYear()+ "-" + ("0"+(new Date(this.manage.inData).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.inData).getDate();
      data.draftFrom=this.manage.draftFrom; //票据来源
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




  //去往票据入账
  editmanage3Data() {
    this.navCtrl.push(Editmanage3Page,{
      ITEM:this.item,
    });
  }
}
