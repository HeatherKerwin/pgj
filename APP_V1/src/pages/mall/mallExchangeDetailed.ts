import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { DomSanitizer } from '@angular/platform-browser';
import { PhotoViewer } from '@ionic-native/photo-viewer';
import { apiService } from "../../api.service";
import { mallExchangeOrderPage } from "../../pages/mall/mallExchangeOrder";

@Component({
  selector: 'page-mallExchangeDetailed',
  templateUrl: 'mallExchangeDetailed.html'
})
export class mallExchangeDetailedPage {
  public mall:any={
    goodsName:'',//商品名
    integral:'',//积分
    stock:'',//库存
    goodsParam:'',//参数
    goodsExplain:'',//介绍
  }
  public quehuo:boolean;

  public slides = [];
  public picShows = [];
  public picShow:any;

  constructor(
    private sanitizer: DomSanitizer,
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public pv:PhotoViewer
  ) {
    this.loadGoodsDetailed();
  }

  /**
   * 加载商品详细信息
   */
  loadGoodsDetailed(){
    let hosgoodsId=this.params.get("HOTGOODSID");
    let data:any={
      id:hosgoodsId
    }
    this.apiSev.api("post", "app/goods/detailed", (res) => {
      if (res.response == 'success') {
        let objs=res.data;
        this.mall=objs;

        if(objs.banner1!=null){
          this.slides[0]=objs.banner1
        }
        if(objs.banner2!=null){
          this.slides[1]=objs.banner2
        }
        if(objs.banner3!=null){
          this.slides[2]=objs.banner3
        }

        if(objs.stock <= 0){
          this.quehuo=true;
        }
        if(objs.stock <= 99){
          this.mall.stock=objs.stock;
        }else{
          this.mall.stock="充足";
        }

        if(objs.explainBanner1!=null){
          this.picShows[0]=objs.explainBanner1
        }
        if(objs.explainBanner2!=null){
          this.picShows[1]=objs.explainBanner2
        }
        if(objs.explainBanner3!=null){
          this.picShows[2]=objs.explainBanner3
        }

      } else {
        this.apiSev.itip(res.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }

  //查看图片大图
  viewPhoto (img) {
    this.pv.show(img);
  }

  //转译HTML为文本
  assembleHTML(strHTML:any) {
    return this.sanitizer.bypassSecurityTrustHtml(strHTML);
  }

  //立即兑换
  exchange(e){
    let flag = this.params.get("FLAG");
    let id=this.params.get("HOTGOODSID");
    this.navCtrl.push(mallExchangeOrderPage,{
      HOTGOODSID : id,
      FLAG:flag
    })
    e.preventDefault();
  }


}
