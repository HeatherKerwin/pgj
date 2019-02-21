import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
//兑换商品
import { mallListPage } from '../../pages/mall/mallList';
//商城首页
import { mallPage } from '../../pages/mall/mall';
//兑换商品详情
import { mallExchangeDetailedPage } from '../../pages/mall/mallExchangeDetailed';

@Component({
  selector: 'page-mallExchangeSuccess',
  templateUrl: 'mallExchangeSuccess.html'
})
export class mallExchangeSuccessPage {
  public malls=[];
  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.loadGoods();
  }


  /**
   * 加载热门兑换商品
   */
  loadGoods(){
    let data:any={}
    this.apiSev.api("post", "app/goods/gethotlist", (res) => {
      if (res.response == "success") {
        let goods = res.data;
        this.malls=goods;
      }else {
        this.apiSev.itip(res.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //商品
  mall(e){
    let flag = this.params.get("Flag");
    this.navCtrl.push(mallPage,{FLAG:flag});
    e.preventDefault();
  }
  //全部商品
  mallAll(e){
    this.navCtrl.push(mallListPage);
    e.preventDefault();
  }
  //商品、详情
  details(id){


    this.navCtrl.push(mallExchangeDetailedPage,{
      HOTGOODSID:id
    });
  }
}
