import { Component } from '@angular/core';
import { NavController, ViewController , NavParams,AlertController} from 'ionic-angular';
import { ChangeDetectorRef } from '@angular/core';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import {DiscountPlaceAddPage} from "./discountPlaceAdd";
import {DiscountYPPage} from "./discountYP";
import {DiscountSPPage} from "./discountSP";

@Component({
  selector: 'page-discountPlaceList',
  templateUrl: 'discountPlaceList.html'
})
export class DiscountPlaceListPage {
  public orgId:any;
  public memberId:any;
  public isOk:boolean; //是否还有数据
  public pageIndex:any;//分页
  public placeMaps=[];

  public placeList=false;//是否已有地址
  public placeNo=false;//是否已有地址
  public placeMap:any={
    name: '',
    sex: '',
    mobile:'',
    address: ''
  }

  public YPDATA:any;//银票贴现带过来的数据
  public SPDATA:any;//商票贴现带过来的数据

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public cd: ChangeDetectorRef,
	public alertCtrl: AlertController
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    })
    this.pageIndex=1;

    //银票贴现带过来的数据还要传回去
    this.YPDATA=this.params.get("YPDATA");
    //银票贴现带过来的数据还要传回去
    this.SPDATA=this.params.get("SPDATA");
  }

  //每次进入页面加载
  ionViewDidEnter() {
    this.placeMaps=[];
    this.loadData(this.pageIndex);
  }

  //初始加载数据
  loadData(pageIndex) {
    let data:any={
      memberId:this.memberId,
      pageIndex:pageIndex,
      belong:0
    }
    if (this.orgId!=null&&this.orgId!= ""&&this.orgId!=undefined){
      data.orgId=this.orgId
    }
    this.apiSev.api("post", "app/address/list", (res) => {
      if (res.response == 'success'){
        this.placeList=true;
        this.placeNo=false;
        let objs = res.data;
        this.isOk=false;
        if(objs.length>0){
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i=0;i<objs.length;i++){
            this.placeMaps.push(objs[i]);
          }
        }else {
          this.placeList=false;
          this.placeNo=true;
        }
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
    }, 500,data);
  }
  //下滑加载
  doInfinite(infiniteScroll) {
    setTimeout(() => {

      if(this.isOk){
        this.pageIndex=this.pageIndex+1;
        this.loadData(this.pageIndex);
        this.isOk = false;
      }else{
        infiniteScroll.enable(false);
      }
      infiniteScroll.complete();
    }, 500);
  }

  //性别显示
  sexShow(i){
    if(i==1){//男
      return "先生";
    }else if(i==2){
      return "女士";
    }
  }

  //选中地址
  myColumn(id){
    let url = this.params.get("ADDRESSCALLBACKURL");
    if(url=='DiscountYPPage') {
      let DiscountYPPage={
        ADDRESSID: id,
        YPDATA:this.YPDATA
      }
      this.apiSev.parmData = DiscountYPPage;
      this.navCtrl.pop();
    }else if(url=='DiscountSPPage') {
      let DiscountSPPage={
        ADDRESSID: id,
        SPDATA:this.SPDATA
      }
      this.apiSev.parmData = DiscountSPPage;
      this.navCtrl.pop();
    };
  }

  // 编辑
  placeEdit(id,state){
    this.navCtrl.push(DiscountPlaceAddPage,{
      ADDRESSID:id,
      state:state,
      placeType: 0//用作区分下一页为： 新增地址 或 编辑地址
    });
  }

  //设置默认地址
  setDefault(id){
    let data:any={
      memberId:this.memberId,
      id:id,
      belong:0
    }
    if (this.orgId!=null&&this.orgId!= ""&&this.orgId!=undefined){
      data.orgId=this.orgId
    }
    this.apiSev.api("post", "app/address/set/default", (res) => {
      if (res.response != 'success') {
        this.pageIndex = 1;
        this.myColumn(this.placeMap.id);
        this.loadData(this.pageIndex);
      }
    },(error) => {
      this.apiSev.itip('暂无数据');
    }, 500,data);
  }

  // 删除
  deleteById(id){
	  let confirm = this.alertCtrl.create({
      title: '提示',
      message: '确认要删除吗?',
      buttons: [
        {
          text: '取消',
          handler: () => {
          }
        },
        {
          text: '确定',
          handler: () => {
			let data:any={
			  memberId:this.memberId,
			  id:id,
			  belong:0
			}
			if (this.orgId!=null&&this.orgId!= ""&&this.orgId!=undefined){
			  data.orgId=this.orgId
			}
			this.apiSev.api("post", "app/address/del", (res) => {
			  if (res.response == 'success') {
				this.pageIndex = 1;
				this.placeMaps=[];
				this.loadData(this.pageIndex);
			  }
			},(error) => {}, 500,data);
          }
        }
      ]
    });
    confirm.present();
  }

  // 新增页面
  placeAdd(){
    let url = this.params.get("ADDRESSCALLBACKURL");
    let loaddata;
    if (this.YPDATA!=null||this.YPDATA!=undefined){
      loaddata=this.YPDATA;
    }else if (this.SPDATA!=null||this.SPDATA!=undefined){
      loaddata=this.SPDATA;
    }
    this.navCtrl.push(DiscountPlaceAddPage,{
      placeType: 1,//用作区分下一页为： 新增地址 或 编辑地址
      ADDRESSCALLBACKURL:url,
      LOADDATA:loaddata
    });
  }
}
