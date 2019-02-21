import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
import { OrderPageB } from '../order/orderB';
import { successPage } from '../../pages/discount/success1';

@Component({
  selector: 'page-signatureifrm',
  templateUrl: 'signatureifrm.html'
})


export class SignatureifrmPage {
  public browser:any = {url:'',secUrl: ''};
  public item:any = {};
  public item2:any = {};
  public orderflag = '';
  public distId = '';
  public data:any={
    bindId:'',
  };//接口传值
  public OnePrice:any=''; //一口价
  public order_type:any=''; //类型
  public discId:any=''; //贴现ID主键


  constructor(public storage: Storage, public navCtrl: NavController,public params: NavParams, public viewCtrl: ViewController, public apiSev: apiService, public sanitizer: DomSanitizer) {
    this.item=this.params.get("item");
    this.discId=this.params.get("DISCOUNTRECORDID"); //贴现ID主键
    this.orderflag=this.params.get("orderflag");
    this.item2.distId=this.params.get("selectDistId");
    this.item2.txlx=this.params.get("txlx");
    if(this.params.get("DATA")!=null&&this.params.get("DATA")!=''&&this.params.get("DATA")!='undefined'){this.data=this.params.get("DATA");}  //接口传值}
    this.OnePrice=this.params.get("OnePrice");  //是否有一口价
    this.order_type=this.params.get("ORDERTYPE");  //类型

    console.log(this.data);
    console.log(this.data.dist_bind_id);
    console.log(this.apiSev.jdrz);

    let order_type:any;
    if(this.order_type=='yp'){
      order_type='DISCOUNTRECORD';
    }else {
      order_type='DISCOUNTRECORDSP';
    }
    let url:any;
    if(this.OnePrice!=null&&this.OnePrice!=''&&this.OnePrice!='undefined'){
      //是否是京东订单
      if(this.data.bindId!=null&&this.data.bindId!=''&&this.data.bindId!='undefined'&&this.apiSev.jdrz==1){
        url=this.apiSev.getNewUrl()+"out/jd/econtract/buyout/?discId="+this.discId+"&orderType="+order_type+"&bindId="+this.data.bindId;
      }else {
        url=this.apiSev.getNewUrl()+"out/econtract/buyout/?discId="+this.discId+"&orderType="+order_type;
      }
    }else {
      //是否是京东订单
      if(this.data.dist_bind_id!=null&&this.data.dist_bind_id!=''&&this.data.dist_bind_id!='undefined'&&this.apiSev.jdrz==1){
        url=this.apiSev.getNewUrl()+"out/jd/econtract/?distId="+this.item2.distId+"&orderType="+this.item.order_type+"&bindId="+this.data.dist_bind_id;
      }else {
        url=this.apiSev.getNewUrl()+"out/econtract/?distId="+this.item2.distId+"&orderType="+this.item.order_type;
      }
    }
    this.browser.url = url;
    this.browser.secUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  canceldd(){
    this.navCtrl.pop();
  }

  // 生成订单
  save() {
    if(this.OnePrice!=null&&this.OnePrice!=''&&this.OnePrice!='undefined'){
      this.Newsave();
    } else {
      let data = {
        id: this.item.id,
        orderflag: this.orderflag,
        selectDistId: this.item2.distId,
        txlx: this.item2.txlx
      };
      let url = "discountrecord/update/select";
      if (this.item.order_type == 'DISCOUNTRECORDSP')
        url = "discountrecordsp/update/select";
      this.apiSev.api("newpost", url, (res) => {

        if (res.data.response == 'success') {
          this.apiSev.itip("已成功选定报价");
          this.navCtrl.setRoot(OrderPageB);
        }
      }, (error) => {
      }, 6100, data);
    }
  }

  // 一口价生成订单
  Newsave() {
    let data:any={
      id:this.data.id,//订单主键、
      vAcctAcctNo:this.data.vAcctAcctNo,
      bindId:this.data.bindId,
    };
    if(this.data.selectOrgId!=null&&this.data.selectOrgId!=''&&this.data.selectOrgId!='undefined'){
      data.selectOrgId= this.data.selectOrgId;//指定机构
    }
    if(this.data.couponId!=null&&this.data.couponId!=''&&this.data.couponId!='undefined'){
      data.couponId=this.data.couponId;
    }
    let order_url;
    if(this.order_type=='yp'){
      order_url='discountrecord/update';
    }else if(this.order_type=='sp'){
      order_url='discountrecordsp/update';
    }
    this.apiSev.api("newpost", order_url, (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(successPage);
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }
}
