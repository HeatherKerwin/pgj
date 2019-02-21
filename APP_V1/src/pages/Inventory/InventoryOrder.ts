import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-InventoryOrder',
  templateUrl: 'InventoryOrder.html'
})


export class InventoryOrderPage {

  public isMask=false;//不显示上传图片
  public isUpload=true;//不显示上传图片
  public isPicpath=false;//不显示上传图片
  public uploadFlag:boolean=false;//是否上传图片成功
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗
  public footer:boolean=true;   //隐藏底部
  public editP:boolean=false; //编辑弹窗
  public delP:boolean=false;  //删除弹窗
  public XdP:boolean=false; //下单弹窗

  public messagecontent: '';
  public memberId;
  public List: any = [];   //页面展示
  public pageNo = 1;
  public isOk: boolean; //是否还有数据
  public discount:any={};
  public inventory:any={};   //编辑添加数据
  public inven:any={};

    constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    }

  //每次进入页面都加载
  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.inventorypage()
    });
  }

  //分页显示展示数据
  inventorypage() {
    this.isOk = false;
    let data: any = {
      currentMId:this.memberId,
      pageIndex:this.pageNo,
      pageSize: 10,
      orderBy:'CREATETIME_DESC',
      state:'PASS'
    };
    this.apiSev.api("newpost", "inventory/hall", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if (objs.length > 0) {
          this.isOk = false;
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i = 0; i < objs.length; i++) {
            this.List.push(objs[i]);
          }
        }
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  doInfinite(infiniteScroll) {
    if(this.isOk){
      setTimeout(() => {
        this.pageNo=this.pageNo+1;
        this.inventorypage();
        infiniteScroll.complete();
      }, 6100);
    }else{
      infiniteScroll.enable(false);
      infiniteScroll.complete();
    }
  }

  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask' || e.srcElement.className == 'offer'){
      this.isMask = false;
      this.Distinguish =false;
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

  //下单意向
  inventoryintentsave() {
    this.Distinguish =false;
    this.isMask = false;
    this.delP=false;
    this.XdP=false;
    let data: any = {
      inventoryId:this.inventory.id,
      memberId:this.memberId,
    };
    this.apiSev.api("newpost", "inventoryintent/save", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.inven.amount=this.inven.amount+1;
        this.inven.intent='LIKE';
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //取值
  getinventory(member_id,id,allmoney,bank,printtime,endtime,remarks,price,amount,item){
    this.inventory={
      member_id:member_id,
      id:id,
      allmoney:allmoney,
      bank:bank,
      printtime:printtime,
      endtime:endtime,
      remarks:remarks,
      price:price,
      amount:amount,
    };
    this.inven=item;
    if(this.inventory.member_id==this.memberId){
      this.apiSev.itip("您不能拍自己的票！");
      return;
    }else {
      this.isMask=true;
      this.Distinguish=true;
      this.delP=false;
      this.XdP=true;
    }
  }

}
