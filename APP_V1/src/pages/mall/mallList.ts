import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { mallExchangeDetailedPage } from "../../pages/mall/mallExchangeDetailed";
import { mallExchangeOrderPage } from "../../pages/mall/mallExchangeOrder";

@Component({
  selector: 'page-mallList',
  templateUrl: 'mallList.html'
})
export class mallListPage {
  public pageIndex=1;
  public isOk:boolean;
  public commoditys=[];
  public commodity:any={};

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.loadGoods(1);
  }

  /**
   * 加载商品
   */
  loadGoods(pageIndex){
    let data:any={
      pageIndex:pageIndex
    }
    this.apiSev.api("post", "app/goods/getlist", (res) => {
      if (res.response == 'success') {
        let objs=res.data;
        this.isOk=false;
        if(objs.length>0){
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i=0;i<objs.length;i++){
            this.commoditys.push(objs[i]);
          }
        }
      }

    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);

  }
  //下滑加载
  doInfinite(infiniteScroll) {
    setTimeout(() => {

      if(this.isOk){
        this.pageIndex=this.pageIndex+1;
        this.loadGoods(this.pageIndex);
        this.isOk = false;
      }else{
        infiniteScroll.enable(false);
      }
      infiniteScroll.complete();
    }, 500);
  }

  //兑换
  convert(id){
    this.navCtrl.push(mallExchangeOrderPage,{
      HOTGOODSID : id
    })
  }

  // 查看详情
  Detailed(id){
    this.navCtrl.push(mallExchangeDetailedPage,{
      HOTGOODSID : id
    })
  }


}
