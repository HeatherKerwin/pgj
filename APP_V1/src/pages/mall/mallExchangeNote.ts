import { Component } from '@angular/core';
import { NavController, ViewController , NavParams ,AlertController} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { mallExchangeNoteDetailPage } from "../../pages/mall/mallExchangeNoteDetail";

@Component({
  selector: 'page-mallExchangeNote',
  templateUrl: 'mallExchangeNote.html'
})
export class mallExchangeNotePage {
  public memberId;
  public pageIndex=1;
  public isOk:boolean=false;
  public details=[];
  public detail:any={};
  public express:any={};
  public detailList:boolean=false;
  public detailNo:boolean=false;
  public isMask:boolean=false;
  public isDelete:boolean=false;
  public isEedit:boolean=false;
  public detailId='';

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public alertCtrl: AlertController
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.loadData(1);
    })

  }

  /**
   * 获取兑换列表
   */
  loadData(pageIndex){
    let data:any={
      memberId:this.memberId,
      pageIndex:pageIndex,
    }
    this.apiSev.api("post", "app/integralorders/list", (res) => {
      if (res.response == 'success') {

        this.detailList=true;
        this.detailNo=false;
        let objs = res.data;
        this.isOk=false;
        if(objs.length>0){
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i=0;i<objs.length;i++){
            this.details.push(objs[i]);
          }
        }else {
          this.detailList=false;
          this.detailNo=true;
        }

      } else {
        this.apiSev.itip(data.msg);
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
        this.loadData(this.pageIndex);
        this.isOk = false;
      }else{
        infiniteScroll.enable(false);
      }
      infiniteScroll.complete();
    }, 500);
  }

  /**
   *  查看快递
   */
  search(e,id){
    this.isMask = true;
    this.isEedit = true;
    this.isDelete = false;
    let data:any={
      id:id,
    }
    this.apiSev.api("post", "app/integralorders/getorders", (res) => {
      if (res.response == 'success') {
        let objs=res.data;
        this.express=objs;
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);

    e.preventDefault();
  }

  /**
   * 删除订单
   */
  del(e,id){
    this.isMask=true;
    this.isDelete=true;
    this.isEedit=false;
    this.detailId=id;
    e.preventDefault();
  }
  delete(e){
    let data:any={
      id:this.detailId
    }
    this.apiSev.api("post", "app/integralorders/del", (res) => {
      if (res.response == 'success') {
        this.isMask=false;
        this.isDelete=false;
        this.isEedit=false;
        this.details=[];
        this.loadData(1);
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
    e.preventDefault();
  }

  // 查看详情
  Detailed(e,id){
    this.navCtrl.push(mallExchangeNoteDetailPage,{
      REDERSNO : id
    })
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
