import { Component } from '@angular/core';
import {ActionSheetController, NavController} from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
declare var Wechat:any;
@Component({
  selector: 'page-myOffer',
  templateUrl: 'myOffer.html'
})
export class MyOfferPage {
  public GuoGuBig;//大电票国股一年期
  public DaShangBig;//大电票城商一年期
  public ChengShangBig;//大电票城商一年期
  public WaiZiBig;//大电票外资一年期
  public NongShangBig;//大电票农商一年期
  public NongHeBig;//大电票农合一年期
  public NongXinBig;//大电票农信一年期
  public CunZhenBig;//大电票村镇一年期
  public show1:boolean = false;

  public GuoGuBig1;//大电票国股半年期
  public DaShangBig1;//大电票城商半年期
  public ChengShangBig1;//大电票城商半年期
  public WaiZiBig1;//大电票外资半年期
  public NongShangBig1;//大电票农商半年期
  public NongHeBig1;//大电票农合半年期
  public NongXinBig1;//大电票农信半年期
  public CunZhenBig1;//大电票村镇半年期
  public show2:boolean = false;

  public GuoGuZhiSmall;//小纸票国股0-50万
  public ChengShangZhiSmall;//小纸票城商0-50万
  public WaiZiZhiSmall;//小纸票外资0-50万
  public NongShangZhiSmall;//小纸票农商0-50万
  public NongHeZhiSmall;//小纸票农合0-50万
  public NongXinZhiSmall;//小纸票农信0-50万
  public CunZhenZhiSmall;//小纸票村镇0-50万
  public GuoguZhiMatrueprice;//国股足月票价0-50万
  public ChengShangZhiMatrueprice;//城商足月票价0-50万
  public WaiZiZhiMatrueprice;//外资足月票价0-50万
  public NongShangZhiMatrueprice;//农商足月票价0-50万
  public NongHeZhiMatrueprice;//农合足月票价0-50万
  public NongXinZhiMatrueprice;//农信足月票价0-50万
  public CunZhenZhiMatrueprice;//村镇足月票价0-50万
  public show3:boolean = false;

  public GuoGuZhiSmall1;//小纸票国股50-100万
  public ChengShangZhiSmall1;//小纸票城商50-100万
  public WaiZiZhiSmall1;//小纸票外资50-100万
  public NongShangZhiSmall1;//小纸票农商50-100万
  public NongHeZhiSmall1;//小纸票农合50-100万
  public NongXinZhiSmall1;//小纸票农信50-100万
  public CunZhenZhiSmall1;//小纸票村镇50-100万
  public GuoguZhiMatrueprice1;//国股足月票价50-100万
  public ChengShangZhiMatrueprice1;//城商足月票价50-100万
  public WaiZiZhiMatrueprice1;//外资足月票价50-100万
  public NongShangZhiMatrueprice1;//农商足月票价50-100万
  public NongHeZhiMatrueprice1;//农合足月票价50-100万
  public NongXinZhiMatrueprice1;//农信足月票价50-100万
  public CunZhenZhiMatrueprice1;//村镇足月票价50-100万
  public show4:boolean = false;

  public GuoGuZhiSmall2;//小纸票国股100-300万
  public ChengShangZhiSmall2;//小纸票城商100-300万
  public WaiZiZhiSmall2;//小纸票外资100-300万
  public NongShangZhiSmall2;//小纸票农商100-300万
  public NongHeZhiSmall2;//小纸票农合100-300万
  public NongXinZhiSmall2;//小纸票农信100-300万
  public CunZhenZhiSmall2;//小纸票村镇100-300万
  public GuoguZhiMatrueprice2;//国股足月票价100-300万
  public ChengShangZhiMatrueprice2;//城商足月票价100-300万
  public WaiZiZhiMatrueprice2;//外资足月票价100-300万
  public NongShangZhiMatrueprice2;//农商足月票价100-300万
  public NongHeZhiMatrueprice2;//农合足月票价100-300万
  public NongXinZhiMatrueprice2;//农信足月票价100-300万
  public CunZhenZhiMatrueprice2;//村镇足月票价100-300万
  public show5:boolean = false;

  public GuoguDianSmall;//小电票国股50万以下0-90天
  public ChengShangDianSmall;//小电票城商50万以下0-90天
  public WaiZiDianSmall;//小电票外资50万以下0-90天
  public NongShangDianSmall;//小电票农商50万以下0-90天
  public NongHeDianSmall;//小电票农合50万以下0-90天
  public NongXinDianSmall;//小电票农信50万以下0-90天
  public CunZhenDianSmall;//小电票村镇50万以下0-90天
  public show6:boolean = false;

  public GuoguDianSmall1;//小电票国股50万以下90-178天
  public ChengShangDianSmall1;//小电票城商50万以下90-178天
  public WaiZiDianSmall1;//小电票外资50万以下90-178天
  public NongShangDianSmall1;//小电票农商50万以下90-178天
  public NongHeDianSmall1;//小电票农合50万以下90-178天
  public NongXinDianSmall1;//小电票农信50万以下90-178天
  public CunZhenDianSmall1;//小电票村镇50万以下90-178天
  public show7:boolean = false;

  public GuoguDianSmall2;//小电票国股50万以下178天以上
  public ChengShangDianSmall2;//小电票城商50万以下178天以上
  public WaiZiDianSmall2;//小电票外资50万以下178天以上
  public NongShangDianSmall2;//小电票农商50万以下178天以上
  public NongHeDianSmall2;//小电票农合50万以下178天以上
  public NongXinDianSmall2;//小电票农信50万以下178天以上
  public CunZhenDianSmall2;//小电票村镇50万以下178天以上
  public show8:boolean = false;

  public GuoguDianSmall3;//小电票国股50-100万0-90天
  public ChengShangDianSmall3;//小电票城商50-100万0-90天
  public WaiZiDianSmall3;//小电票外资50-100万0-90天
  public NongShangDianSmall3;//小电票农商50万-100万0-90天
  public NongHeDianSmall3;//小电票农合50万-100万0-90天
  public NongXinDianSmall3;//小电票农信50万-100万0-90天
  public CunZhenDianSmall3;//小电票村镇50万-100万0-90天
  public show9:boolean = false;

  public GuoguDianSmall4;//小电票国股50万-100万90-178天
  public ChengShangDianSmall4;//小电票城商50万-100万90-178天
  public WaiZiDianSmall4;//小电票外资50万-100万90-178天
  public NongShangDianSmall4;//小电票农商50万-100万90-178天
  public NongHeDianSmall4;//小电票农合50万-100万90-178天
  public NongXinDianSmall4;//小电票农信50万-100万90-178天
  public CunZhenDianSmall4;//小电票村镇50万-100万90-178天
  public show10:boolean = false;

  public GuoguDianSmall5;//小电票国股50万-100万178天以上
  public ChengShangDianSmall5;//小电票城商50万-100万178天以上
  public WaiZiDianSmall5;//小电票外资50万-100万178天以上
  public NongShangDianSmall5;//小电票农商50万-100万178天以上
  public NongHeDianSmall5;//小电票农合50万-100万178天以上
  public NongXinDianSmall5;//小电票农信50万-100万178天以上
  public CunZhenDianSmall5;//小电票村镇50万-100万178天以上
  public show11:boolean = false;

  public GuoguDianSmall6;//小电票国股100万以上0-90天
  public ChengShangDianSmall6;//小电票城商100万以上0-90天
  public WaiZiDianSmall6;//小电票外资100万以上0-90天
  public NongShangDianSmall6;//小电票农商100万以上0-90天
  public NongHeDianSmall6;//小电票农合100万以上0-90天
  public NongXinDianSmall6;//小电票农信100万以上0-90天
  public CunZhenDianSmall6;//小电票村镇100万以上0-90天
  public show12:boolean = false;

  public GuoguDianSmall7;//小电票国股100万以上90-178天
  public ChengShangDianSmall7;//小电票城商100万以上90-178天
  public WaiZiDianSmall7;//小电票外资100万以上90-178天
  public NongShangDianSmall7;//小电票农商100万以上90-178天
  public NongHeDianSmall7;//小电票农合100万以上90-178天
  public NongXinDianSmall7;//小电票农信100万以上90-178天
  public CunZhenDianSmall7;//小电票村镇100万以上90-178天
  public show13:boolean = false;

  public GuoguDianSmall8;//小电票国股100万以上178天以上
  public ChengShangDianSmall8;//小电票城商100万以上178天以上
  public WaiZiDianSmall8;//小电票外资100万以上178天以上
  public NongShangDianSmall8;//小电票农商100万以上178天以上
  public NongHeDianSmall8;//小电票农合100万以上178天以上
  public NongXinDianSmall8;//小电票农信100万以上178天以上
  public CunZhenDianSmall8;//小电票村镇100万以上178天以上
  public show14:boolean = false;



  public orgId;
  public memberId;

  public price = 0;//报价额度
  public usedPrice = 0;//已用额度
  public company:any="--";//报价机构

  //切换
  segmentsArray = ['yinBigDian','yinSmallZhi','yinSmallDian'];
  pet: string = this.segmentsArray[0];
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService,public actionSheetCtrl:ActionSheetController) {
      this.storage.get('ORGID').then((data)=>{
        this.orgId=data;
        this.loadPrice();
      })
  }
  /**
   *  初始化页面,加载总金额和已用金额 ，加载已经报价的票
   */
  loadPrice(){
    let data = {
      orgId:this.orgId,
      cityId:1
    }
    this.apiSev.api(
      "post",
      "/app/price/get/priceAndType",
      (res) => {
        if(res.response == "success"){
          if(res.orgLimit != null){
            this.price=res.orgLimit.price;
            if(res.orginfo.company!=null && res.orginfo.company!=''){
              this.company = res.orginfo.company;
            }
            if(res.orgLimit.usedPrice == null || res.orgLimit.usedPrice == ""){
            }else{
              this.usedPrice = res.orgLimit.usedPrice;
            }
          }
          if(res.priceList != null){
            this.loadOffer(res.priceList);
          }
        }else{
          alert(res.msg);
        }
      }, (error) => {
      }, 1,data);
  }

  //加载报价
  /**
   *  根据参数判断票据类型的显示
   */
  loadOffer(obj){
    for(var i in obj){

      var gg_price = obj[i].guogu;
      var gg_price1 = obj[i].guogu1;
      var gg_price3 = obj[i].guogu3;

      var ds_price = obj[i].dashang;

      var cs_price = obj[i].chengshang;
      var cs_price1 = obj[i].chengshang1;
      var cs_price3 = obj[i].chengshang3;

      var wz_price = obj[i].waizi;
      var wz_price1 = obj[i].waizi1;
      var wz_price3 = obj[i].waizi3;

      var ns_price = obj[i].nongshang;
      var ns_price1 = obj[i].nongshang1;
      var ns_price3 = obj[i].nongshang3;

      var nh_price = obj[i].nonghe;
      var nh_price1 = obj[i].nonghe1;
      var nh_price3 = obj[i].nonghe3;

      var nx_price = obj[i].nongxin;
      var nx_price1 = obj[i].nongxin1;
      var nx_price3 = obj[i].nongxin3;

      var cz_price = obj[i].cunzhen;
      var cz_price1 = obj[i].cunzhen1;
      var cz_price3 = obj[i].cunzhen3;

      var priceTypeId = obj[i].priceTypeId;

      if(priceTypeId == 2 || priceTypeId == 21){
        if(obj[i].priceTypeId == 2){//半年期
          if(gg_price != null && gg_price != "") this.GuoGuBig1 = gg_price;
          if(ds_price != null && ds_price != "") this.DaShangBig1 = ds_price;
          if(cs_price != null && cs_price != "") this.ChengShangBig1 = cs_price;
          if(wz_price != null && wz_price != "") this.WaiZiBig1 = wz_price;
          if(ns_price != null && ns_price != "") this.NongShangBig1 = ns_price;
          if(nh_price != null && nh_price != "") this.NongHeBig1 = nh_price;
          if(nx_price != null && nx_price != "") this.NongXinBig1 = nx_price;
          if(cz_price != null && cz_price != "") this.CunZhenBig1 = cz_price;
          this.show2 = true;
        }else if(obj[i].priceTypeId == 21){
          if(gg_price != null && gg_price != "") this.GuoGuBig = gg_price;
          if(ds_price != null && ds_price != "") this.DaShangBig = ds_price;
          if(cs_price != null && cs_price != "") this.ChengShangBig = cs_price;
          if(wz_price != null && wz_price != "") this.WaiZiBig = wz_price;
          if(ns_price != null && ns_price != "") this.NongShangBig = ns_price;
          if(nh_price != null && nh_price != "") this.NongHeBig = nh_price;
          if(nx_price != null && nx_price != "") this.NongXinBig = nx_price;
          if(cz_price != null && cz_price != "") this.CunZhenBig = cz_price;
          this.show1 = true;
        }
      }else if(priceTypeId == 5 || priceTypeId == 8 || priceTypeId == 11){
        if(priceTypeId == 11){//50万以下
          if(gg_price != null && gg_price != "") this.GuoGuZhiSmall = gg_price+'‰+'+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangZhiSmall = cs_price+'‰+'+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiZhiSmall = wz_price+'‰+'+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangZhiSmall = ns_price+'‰+'+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeZhiSmall = nh_price+'‰+'+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinZhiSmall = nx_price+'‰+'+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenZhiSmall = cz_price+'‰+'+cz_price1;

          if(gg_price!=null && gg_price != "") this.GuoguZhiMatrueprice = gg_price3;
          if(cs_price!=null && cs_price != "") this.ChengShangZhiMatrueprice = cs_price3;
          if(wz_price!=null && wz_price != "") this.WaiZiZhiMatrueprice = wz_price3;
          if(ns_price!=null && ns_price != "") this.NongShangZhiMatrueprice = ns_price3;
          if(nh_price!=null && nh_price != "") this.NongHeZhiMatrueprice = nh_price3;
          if(nx_price!=null && nx_price != "") this.NongXinZhiMatrueprice = nx_price3;
          if(cz_price!=null && cz_price != "") this.CunZhenZhiMatrueprice = cz_price3;
          this.show3 = true;
        }else if(priceTypeId == 8){//50-100万
          if(gg_price != null && gg_price != "") this.GuoGuZhiSmall1 = gg_price+'‰+'+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangZhiSmall1 = cs_price+'‰+'+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiZhiSmall1 = wz_price+'‰+'+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangZhiSmall1 = ns_price+'‰+'+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeZhiSmall1 = nh_price+'‰+'+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinZhiSmall1 = nx_price+'‰+'+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenZhiSmall1 = cz_price+'‰+'+cz_price1;

          if(gg_price!=null && gg_price != "") this.GuoguZhiMatrueprice1 = gg_price3;
          if(cs_price!=null && cs_price != "") this.ChengShangZhiMatrueprice1 = cs_price3;
          if(wz_price!=null && wz_price != "") this.WaiZiZhiMatrueprice1 = wz_price3;
          if(ns_price!=null && ns_price != "") this.NongShangZhiMatrueprice1 = ns_price3;
          if(nh_price!=null && nh_price != "") this.NongHeZhiMatrueprice1 = nh_price3;
          if(nx_price!=null && nx_price != "") this.NongXinZhiMatrueprice1 = nx_price3;
          if(cz_price!=null && cz_price != "") this.CunZhenZhiMatrueprice1 = cz_price3;
          this.show4 = true;
        }else if(priceTypeId == 5){//100万以上
          if(gg_price != null && gg_price != "") this.GuoGuZhiSmall2 = gg_price+'‰+'+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangZhiSmall2 = cs_price+'‰+'+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiZhiSmall2 = wz_price+'‰+'+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangZhiSmall2 = ns_price+'‰+'+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeZhiSmall2 = nh_price+'‰+'+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinZhiSmall2 = nx_price+'‰+'+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenZhiSmall2 = cz_price+'‰+'+cz_price1;

          if(gg_price!=null && gg_price != "") this.GuoguZhiMatrueprice2 = gg_price3;
          if(cs_price!=null && cs_price != "") this.ChengShangZhiMatrueprice2 = cs_price3;
          if(wz_price!=null && wz_price != "") this.WaiZiZhiMatrueprice2 = wz_price3;
          if(ns_price!=null && ns_price != "") this.NongShangZhiMatrueprice2 = ns_price3;
          if(nh_price!=null && nh_price != "") this.NongHeZhiMatrueprice2 = nh_price3;
          if(nx_price!=null && nx_price != "") this.NongXinZhiMatrueprice2 = nx_price3;
          if(cz_price!=null && cz_price != "") this.CunZhenZhiMatrueprice2 = cz_price3;
          this.show5 = true;
        }
      }else if(priceTypeId == 12 || priceTypeId == 13 || priceTypeId == 14){//100万以上
        if(priceTypeId == 12){//90天以下
          if(gg_price != null && gg_price != "") this.GuoguDianSmall6 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall6 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall6 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall6 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall6 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall6 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall6 = cz_price+"%+"+cz_price1;
          this.show12 = true;
        }else if(priceTypeId == 13){//90天-178天
          if(gg_price != null && gg_price != "") this.GuoguDianSmall7 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall7 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall7 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall7 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall7 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall7 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall7 = cz_price+"%+"+cz_price1;
          this.show13 = true;
        }else if(priceTypeId == 14){//178天以上
          if(gg_price != null && gg_price != "") this.GuoguDianSmall8 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall8 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall8 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall8 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall8 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall8 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall8 = cz_price+"%+"+cz_price1;
          this.show14 = true;
        }
      }else if(priceTypeId == 15 || priceTypeId == 16 || priceTypeId == 17){//50-100万
        if(priceTypeId == 15){//90天以下
          if(gg_price != null && gg_price != "") this.GuoguDianSmall3 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall3 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall3 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall3 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall3 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall3 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall3 = cz_price+"%+"+cz_price1;
          this.show9 = true;
        }else if(priceTypeId == 16){//90天-178天
          if(gg_price != null && gg_price != "") this.GuoguDianSmall4 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall4 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall4 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall4 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall4 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall4 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall4 = cz_price+"%+"+cz_price1;
          this.show10 = true;
        }else if(priceTypeId == 17){//178天以上
          if(gg_price != null && gg_price != "") this.GuoguDianSmall5 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall5 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall5 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall5 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall5 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall5 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall5 = cz_price+"%+"+cz_price1;
          this.show11 = true;
        }
      }else if(priceTypeId == 18 || priceTypeId == 19 || priceTypeId == 20){//50万以下
        if(priceTypeId == 18){//90天以下
          if(gg_price != null && gg_price != "") this.GuoguDianSmall = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall = cz_price+"%+"+cz_price1;
          this.show6 = true;
        }else if(priceTypeId == 19){//90天-178天
          if(gg_price != null && gg_price != "") this.GuoguDianSmall1 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall1 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall1 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall1 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall1 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall1 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall1 = cz_price+"%+"+cz_price1;
          this.show7 = true;
        }else if(priceTypeId == 20){//178天以上
          if(gg_price != null && gg_price != "") this.GuoguDianSmall2 = gg_price+"%+"+gg_price1;
          if(cs_price != null && cs_price != "") this.ChengShangDianSmall2 = cs_price+"%+"+cs_price1;
          if(wz_price != null && wz_price != "") this.WaiZiDianSmall2 = wz_price+"%+"+wz_price1;
          if(ns_price != null && ns_price != "") this.NongShangDianSmall2 = ns_price+"%+"+ns_price1;
          if(nh_price != null && nh_price != "") this.NongHeDianSmall2 = nh_price+"%+"+nh_price1;
          if(nx_price != null && nx_price != "") this.NongXinDianSmall2 = nx_price+"%+"+nx_price1;
          if(cz_price != null && cz_price != "") this.CunZhenDianSmall2 = cz_price+"%+"+cz_price1;
          this.show8 = true;
        }
      }
    }
  }
  presentActionSheet() {
    let that = this;
    let actionSheet = this.actionSheetCtrl.create({
      title: '分享到',
      buttons: [
        {
          text: '微信好友',
          role: '',
          handler: () => {
            Wechat.isInstalled(function (installed) {
              if(!installed){
                that.apiSev.itip("报告主人,您可能没有这个微信程序！");
              }else{
                let day = that.apiSev.transformdate(new Date());
                Wechat.share({
                  message: {
                    title: "我的票据报价",
                    description: "我在票据管家平台报了今天的票据的价格，赶紧戳进来看一下吧！",
                    thumb: that.apiSev.getImgUrl+'upload/image/20180110/weixin.png',
                    media: {
                      type: Wechat.Type.LINK,
                      webpageUrl: that.apiSev.HONGBAOURL + "/app/offer.htm?orgId=" + that.orgId + "&day=" + day
                    }
                  },
                  scene: Wechat.Scene.SESSION   // share to Timeline
                }, function () {
                }, function (reason) {
                });
              }
            }, function (reason) {

            });
          }
        },{
          text: '朋友圈',
          role: '',
          handler: () => {
            Wechat.isInstalled(function (installed) {
              if(!installed){
                that.apiSev.itip("报告主人,您可能没有这个微信程序！");
              }else{
                let day = that.apiSev.transformdate(new Date());
                Wechat.share({
                  message: {
                    title: "我的票据报价",
                    description: "我在票据管家平台报了今天的票据的价格，赶紧戳进来看一下吧！",
                    thumb: that.apiSev.getImgUrl+'upload/image/20180110/weixin.png',
                    media: {
                      type: Wechat.Type.LINK,
                      webpageUrl: that.apiSev.HONGBAOURL + "/app/offer.htm?orgId=" + that.orgId + "&day=" + day
                    }
                  },
                  scene: Wechat.Scene.TIMELINE   // share to Timeline
                }, function () {
                }, function (reason) {
                });
              }
            }, function (reason) {});
          }
        },{
          text: '取消',
          role: 'cancel',
          handler: () => {
          }
        }
      ]
    });
    actionSheet.present();
  }
}
