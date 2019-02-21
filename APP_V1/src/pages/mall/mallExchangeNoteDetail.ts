import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { mallExchangeNotePage } from "../../pages/mall/mallExchangeNote";

@Component({
  selector: 'page-mallExchangeNoteDetail',
  templateUrl: 'mallExchangeNoteDetail.html'
})
export class mallExchangeNoteDetailPage {
  public placeNot:boolean;
  public placeShow:boolean;

  public isMask:boolean=false;
  public isDelete:boolean=false;
  public isEedit:boolean=false;

  // public detail:any={};
  public address:any={};
  public integralOrders:any={};
  public goods:any={};

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.loadGoodsDetailed();
  }

  //加载商品详细信息
  loadGoodsDetailed(){
    let ordersNo =  this.params.get("REDERSNO");
    let data:any={
      no:ordersNo
    }
    this.apiSev.api("post", "app/integralorders/details", (res) => {
      if (res.response == 'success') {
        this.address=res.address;
        this.integralOrders=res.integralOrders;
        this.goods=res.data[0];
      } else {
        this.apiSev.itip(res.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }

  /**
   * 删除订单
   */
  delete(e,id){
    let data:any={
      id:id
    }
    this.apiSev.api("post", "app/integralorders/del", (res) => {
      if (res.response == 'success') {
        this.isMask=false;
        this.isDelete=false;
        this.isEedit=false;
        this.navCtrl.push(mallExchangeNotePage);
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
    e.preventDefault();
  }

  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.isDelete = false;
      this.isEedit = false;
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

}
