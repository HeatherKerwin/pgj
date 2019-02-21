import { Component } from '@angular/core';
import { NavController, ViewController,LoadingController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { DetailedlistPage } from './Detailedlist';
import { DiscountYPAttributePage } from '../discount/discountYPAttribute'
import { DiscountSPAttributePage } from "../discount/discountSPAttribute"

@Component({
  selector: 'page-Inventory',
  templateUrl: 'Inventory.html'
})


export class InventoryPage {

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

  public memberId:any;
  public List:any=[];   //页面展示

  public pageNo = 1;
  public isOk:boolean=false; //是否还有数据
  public inventory:any={};   //编辑添加数据
  public inven:any={};
  public order:any={};  //下单

  public discount:any={
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期
    max: new Date(new Date(new Date()).getTime() + 360 * 10 * 24 * 60 * 60 * 1000).toISOString(),//到期日最小值
  };

    constructor(public storage: Storage,public camera: Camera,public loadingCtrl: LoadingController, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {

    }


  //每次进入页面都加载
  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.List=[];
      setTimeout(() => {
      this.inventorypage()
      },5);
    });
  }


  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'|| e.srcElement.className == 'maskConB'){
      this.isMask = false;
      this.CameraRoll =false;
      this.Distinguish =false;
      this.footer=true;
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

  closeMask(){
    this.isMask = false;
    this.CameraRoll =false;
    this.Distinguish =false;
    this.footer=true;
  }

  //上传图弹出层
  uploadBtn(){
    this.isMask = true;
    this.CameraRoll= true;
    this.Distinguish =false;
    this.footer=false;
  }

  //点取相册上传
  albumPic(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 0,
      targetWidth: 1280,
      targetHeight: 1280
    }
    this.findPic(options);
    this.closeMask();
  }

  //调取相机上传
  cameraPic(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 1,
      targetWidth: 1280,
      targetHeight: 1280,
      saveToPhotoAlbum: false,
      correctOrientation:true
    }
    this.findPic(options);
    this.closeMask();
  }

  findPic (options) {
    let loading = this.loadingCtrl.create({
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
    loading.present();
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url,
        memberId:this.memberId
      };
      this.apiSev.api("newpost", "inventory/save", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.Detailedlist(data.data);
          loading.dismiss();
        }else {
          loading.dismiss();
          this.apiSev.itip(data.msg);
        }
      },(error) => {
        loading.dismiss();
        this.apiSev.itip('操作失败！');
      }, 6100,data);
    }, (err) => {
      loading.dismiss();
    });
  }

  //分页显示展示数据
  inventorypage() {
    this.isOk = false;
    let data: any = {
      pageIndex:this.pageNo,
      pageSize: 10,
      memberId:this.memberId,
      orderBy:'CREATETIME_DESC',
      state:'PASS'
    };
    this.apiSev.api("newpost", "inventory/page", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if (objs.length > 0) {
          this.isOk = false;
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i = 0; i < objs.length; i++) {
            objs[i].commitment=false;
            //objs[i].printtime=new Date(objs[i].printtime).toISOString();
            //objs[i].endtime=new Date(objs[i].endtime).toISOString();
            this.List.push(objs[i]);
          }
        }
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  doInfinite(infiniteScroll) {
    if(this.isOk==true){
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

  //去往库存清单
  Detailedlist(data){
    this.Distinguish =false;
    this.isMask = false;
    this.footer=true;
    this.delP=false;
    this.editP=false;
    this.XdP=false;
    this.apiSev.itip("已提交后台审核，需等待1个工作日进行审核");
    this.navCtrl.push(DetailedlistPage,{
      LISTDATA:data
    });
  }

  //删除
  inventorydel() {
    this.footer=true;
    this.isMask=false;
    this.Distinguish=false;

    let data: any = {
      id: this.discount.id
    }
    this.apiSev.api("newpost", "inventory/del", (res) => {
      let data = res.data;
      if (data.response == 'success') {

        for (let i = 0; i < this.List.length; i++) {
          if(this.List.length>1){
            if (this.List[0].commitment == true) {
              this.List.splice(0,1);
            }
            if(this.List.length>2){
              if (this.List[1].commitment == true) {
                this.List.splice(1,1);
              }
            }
            if(this.List.length>3){
              if (this.List[2].commitment == true) {
                this.List.splice(2,1);
              }
            }
            if(this.List.length>4){
              if (this.List[3].commitment == true) {
                this.List.splice(3,1);
              }
            }
            if(this.List.length>5){
              if (this.List[4].commitment == true) {
                this.List.splice(4,1);
              }
            }
            if(this.List.length>6){
              if (this.List[5].commitment == true) {
                this.List.splice(5,1);
              }
            }
          }
          if (this.List[i].commitment == true) {
            this.List.splice(i,1);
          }
        }

      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //编辑
  inventoryupdate() {

    let data: any = {
      memberId:this.memberId,
      id: this.inventory.id,
      allmoney: this.inventory.allmoney,
      bank: this.inventory.bank,
      printtime: this.inventory.printtime,
      endtime: this.inventory.endtime,
      remarks: this.inventory.remarks,
      price: this.inventory.price,
    };
    this.apiSev.api("newpost", "inventory/update", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.inven.allmoney=this.inventory.allmoney;
        this.inven.bank=this.inventory.bank;
        this.inven.printtime=this.inventory.printtime;
        this.inven.endtime=this.inventory.endtime;
        this.inven.remarks=this.inventory.remarks;
        this.inven.price=this.inventory.price;
        this.Distinguish =false;
        this.isMask = false;
        this.footer=true;
        this.delP=false;
        this.editP=false;
        this.XdP=false

      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //取值
  getinventory(id,allmoney,bank,printtime,endtime,remarks,price,item){
    this.inventory={
      id:id,
      allmoney:allmoney,
      bank:bank,
      printtime:printtime,
      endtime:endtime,
      remarks:remarks,
      price:price,
    };
    this.inven=item;
  }

  //下单
  discountorders(type){
    this.Distinguish =false;
    this.isMask = false;
    this.footer=true;
    this.delP=false;
    this.editP=false;
    this.XdP=false;
    this.discount.discount=0;
    if(type==1){
      this.navCtrl.push(DiscountYPAttributePage,{
        ORDERINVENTORY:this.order
      });
    }else if(type==2){
      this.navCtrl.push(DiscountSPAttributePage,{
        ORDERINVENTORY:this.order
      });
    }
  }
}
