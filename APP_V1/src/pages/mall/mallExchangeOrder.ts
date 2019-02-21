import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { mallPlacePage } from "../../pages/mall/mallPlace";
import { mallExchangeSuccessPage } from "../../pages/mall/mallExchangeSuccess";

@Component({
  selector: 'page-mallExchangeOrder',
  templateUrl: 'mallExchangeOrder.html'
})
export class mallExchangeOrderPage {
  public place:any={};

  public placeNot:boolean;
  public placeShow:boolean;
  public isSave:boolean;
  public memberId:'';
  public addressId:'';
  public hosgoodsId:'';

  public order:any={
    goodsName:'',//商品名
    stock:0,//库存
    integral:null,//单件商品所需积分
    goodsParam:'',
    goodsExplain:'',
    banner1:'',//商品图片
    count:1,//选择商品数
    sumprice:0,//需要扣除的积分
    integralTotal:0,//个人的积分
    remark:''//备注
  }

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.loadAddress();
      this.loadGoodsDetailed();
    });
    this.addressId=this.params.get("ADDRESSID");
    this.hosgoodsId=this.params.get("HOTGOODSID");
  }

  ionViewDidEnter(){
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.loadAddress();
      this.loadGoodsDetailed();
    });
    //取地址
    let consignee = this.apiSev.parmData.consignee;
    if (consignee != null && consignee != '') {
      this.place.consignee = consignee;
      this.apiSev.parmData.consignee = '';
    }
    let tel = this.apiSev.parmData.tel;
    if (tel != null && tel != '') {
      this.place.tel = tel;
      this.apiSev.parmData.tel = '';
    }
    let address = this.apiSev.parmData.address;
    if (address != null && address != '') {
      this.place.address = address;
      this.apiSev.parmData.address = '';
    }
  }

  //获取默认地址
  loadAddress(){
    let data:any={
      memberId:this.memberId
    }
    if(this.addressId!=null){
      data.addressId=this.addressId
    }
    this.apiSev.api("post", "app/goodsaddress/get/default", (res) => {
      console.log(res);
      if (res.response == 'success') {
        let objs=res.data;
        if(objs == null){
          this.placeShow=false;
          this.placeNot=true;
        }else{
          this.placeShow=true;
          this.placeNot=false;
          this.place.tel=objs.tel;
          this.place.consignee=objs.consignee;
          this.place.address=objs.address+objs.detailAddress;
          this.addressId=objs.id;
        }

      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);

  }

  //加载商品详细信息
  loadGoodsDetailed(){
    let data:any={
      id:this.hosgoodsId,
      memberId:this.memberId,
    }
    this.apiSev.api("post", "app/goods/detailed", (res) => {
      if (res.response == 'success') {
        let objs=res.data;
        if(objs!=null){
          this.order=objs;
          this.order.integral=objs.integral;
          this.order.integralTotal=res.integral.integralTotal;
          this.order.count=1;
          this.jifen();
          if(this.order.integralTotal < objs.integral){
            this.apiSev.itip("积分不足！");
            this.isSave=true;
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }

  //添加地址
  chosePlace(e) {
    this.navCtrl.push(mallPlacePage,{
      ADDRESSCALLBACKURL:'mallExchangeOrderPage',
      HOTGOODSID:this.hosgoodsId
    })
    e.preventDefault();
  }

  //商品数加减
  incr(e){//加
    if(this.order.count<this.order.stock){
      this.order.count += 1;
      this.jifen();
    }else {
      this.apiSev.itip("库存不足！");
    }
    e.preventDefault();
  }
  decr(e){//减
    //只有大于一的时候才减
    if(this.order.count>1){
      this.order.count -= 1;
      this.jifen();
    }else {
      this.apiSev.itip("所选商品数最少为1！");
    }
    e.preventDefault();
  }
  //计算所需积分
  jifen(){
    this.order.sumprice=this.order.count*this.order.integral;
    if(this.order.sumprice > this.order.integralTotal){
      this.isSave=true;
      this.apiSev.itip("您的积分不足！");
    }else{
      this.isSave=false;
    }
  }

  save(e){
    if(this.addressId == null || this.addressId == ""){
      this.apiSev.itip("收货地址不能为空");
      return ;
    }
    if(this.place.address == null || this.place.address == ""){
      this.apiSev.itip("收货地址不能为空");
      return ;
    }
    if(this.order.count>this.order.stock){
      this.apiSev.itip("库存不足，该商品仅剩"+this.order.stock+"件，请重新选择");
      return ;
    }
    let data:any={
      count:this.order.count,
      memberId:this.memberId,
      shipingAddressId:this.addressId,
      goodsId:this.hosgoodsId,
      integral:this.order.integral*this.order.count,
      state:0,
      sumPrice:this.order.sumprice,
      remark:this.order.remark
    }
    this.apiSev.api("post", "app/integralorders/save", (res) => {
      if (res.response == 'success') {
        let flag = this.params.get("FLAG");
        this.navCtrl.push(mallExchangeSuccessPage,{Flag:flag});
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);

    e.preventDefault();
  }

}
