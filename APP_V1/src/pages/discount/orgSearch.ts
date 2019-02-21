import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { DiscountOrderPage } from "../../pages/discount/discountOrder";

@Component({
  selector: 'page-orgSearch',
  templateUrl: 'orgSearch.html'
})
export class DiscountOrgSearchPage {
  mechanisms=[];
  mechanism:any={};
  public searchName='';
  public isOk:boolean=false;
  public type1='';
  public pageIndex='1';
  public ORDER:any={};
  public order_type:any;//区分商票银票
  public memberId:any;

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    this.ORDER=this.params.get("ORDER");
    this.type1=this.params.get("type1");
    this.order_type=this.params.get("order_type");//区分商票银票
  }

  //搜索
  search(pageIndex){
    if(this.searchName==''||this.searchName==null){
      this.apiSev.itip("请输入您要搜索的机构关键字！");
      return;
    }
    this.mechanisms=[];
    let data:any={
      removeMemberId:this.memberId,
      pageSize:'5',
      pageIndex:pageIndex,
      company:this.searchName
    };
    this.apiSev.api("newpost", "orginfo/search", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.isOk=false;
        if(objs.data.list.length>0){
          if(objs.data.list.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i=0;i<objs.data.list.length;i++){
            this.mechanisms.push(objs.data.list[i]);
          }
        }else {}

      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
  //下滑加载
  doInfinite(infiniteScroll) {
    setTimeout(() => {
      if(this.isOk){
        this.pageIndex=this.pageIndex+1;
        this.search(this.pageIndex);
        this.isOk = false;
      }else{
        infiniteScroll.enable(false);
      }
      infiniteScroll.complete();
    }, 500);
  }

  //选中
  myColumn(id,org_id,company,name,price,service,speed){
    this.navCtrl.push(DiscountOrderPage,{
      COMPANY:company,
      NAME:name,
      PRICE:price,
      SERVICE:service,
      SPEED:speed,
      ID:id,
      ORGID:org_id,
      DISCOUNTRECORDID:this.ORDER.dcrdId,
      order_type:this.order_type,//区分商票银票
      type1:this.type1,
      ORDER:this.ORDER
    })
  }
}
