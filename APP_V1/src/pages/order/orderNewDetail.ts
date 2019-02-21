import { Component } from '@angular/core';
import { NavController, ViewController,NavParams,ModalController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import {DisclaimerPage} from '../openaccount/disclaimer';


@Component({
  selector: 'page-orderNewDetail',
  templateUrl: 'orderNewDetail.html'
})
export class OrderNewDetailPage {
  public user:any = {};

  public isMask:boolean;//弹出层

  public item:any = {};
  public itemPinfen:any = {};

  public maskDiv=false; //弹出层

  public Actual:any='';

  public ListsTime1 = [];
  public isSelect='';
  public memberId = "";
  public info = "1";
  public isCancel=false;
  public isConfirm=false;
  public isMemo=false;
  public Guide:boolean;
  public TradingMarket='';
  public ORGID='';

  constructor(public storage: Storage,public navCtrl: NavController, public phCtrl: PhotoViewer, public viewCtrl: ViewController,public params: NavParams,public modalCtrl: ModalController,public apiSev: apiService) {
      this.item=this.params.get('item');
    this.TradingMarket=this.params.get('TradingMarket');  //是否是交易市场
  }




  bigpic(){
	  if (this.itemPinfen.picpath!=null) {
      if(this.item.type1=='2'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'票号：'+this.item.draft_no+' | 备注：'+this.item.memberother, {share:false});
      }else if(this.item.type1=='1'){
        this.phCtrl.show(this.apiSev.getOrderImgUrl+this.item.picpath,'备注：'+this.item.memberother, {share:false});
      }
    } else{
		  this.apiSev.itip('未上传票面');
	  }
  }



  initData() {

    let data:any = {
      id: this.item.id,
      order_type: this.item.order_type,
      memberId: this.item.bns_member_id,
    };

    if (this.item.org_id == null || this.item.org_id == '' || this.item.org_id == 'undefined') {
      data.orgId = this.ORGID
    } else {
      data.orgId = this.item.org_id
    }

    this.apiSev.api("newpost", "dispatch/show/dist/price", (res) => {
      if(res.data.response == 'success') {
        this.itemPinfen=res.data.data;
        let min = Math.floor(this.itemPinfen.endorseTime / 60);//计算整数分
        let afterMin = this.itemPinfen.endorseTime - min * 60;//取得算出分后剩余的秒数
        this.itemPinfen.endorseTime = this.apiSev.PrefixInteger(min, 2) + ':' + this.apiSev.PrefixInteger(afterMin, 2);

      }
    }, (error) => {},2000,data);


  }

  close () {
  	this.viewCtrl.dismiss();
  }


}
