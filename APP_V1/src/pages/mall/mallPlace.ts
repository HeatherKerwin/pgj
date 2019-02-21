import { Component } from '@angular/core';
import { NavController, ViewController , NavParams,AlertController} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { mallPlaceAddPage } from "../../pages/mall/mallPlaceAdd";

@Component({
  selector: 'page-mallPlace',
  templateUrl: 'mallPlace.html'
})
export class mallPlacePage {
  public places=[];
  public place:any={};
  public isOk:boolean; //是否还有数据
  public placeList=false;//是否已有地址
  public placeNo=false;//是否已有地址
  public pageIndex:any;//分页
  public orgId:any;
  public memberId:any;

  public pageUrl='';//页面来源
  public isShow:boolean = true;

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
	public alertCtrl: AlertController,
    public params: NavParams
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    })
    this.pageUrl=this.params.get("ADDRESSCALLBACKURL");
    if (this.pageUrl!=null){
      this.isShow = false;
    }
    this.pageIndex=1;
  }

  //每次进入页面加载
  ionViewDidEnter() {
    this.places=[];
    this.loadData(this.pageIndex);
  }

  //加载数据
  loadData(id) {
    let data:any={
      memberId:this.memberId,
      pageIndex:id
    }
    this.apiSev.api("post", "app/goodsaddress/list", (res) => {
      console.log(res);
      if (res.response == 'success') {
        this.placeList=true;
        this.placeNo=false;
        let objs = res.data;
        this.isOk=false;
        if(objs.length>0){
          if(objs.length==1){
            this.setDefault(id=objs[0].id)
          }
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i=0;i<objs.length;i++){
            this.places.push(objs[i]);
          }
        }else {
          this.placeList=false;
          this.placeNo=true;
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

  /*选择地址*/
  myColumn(id,consignee,tel,address,detailAddress){
    this.apiSev.parmData.consignee=consignee;
    this.apiSev.parmData.tel=tel;
    this.apiSev.parmData.address=address+detailAddress;
    this.apiSev.tempId=id;
    this.navCtrl.pop();
  }

  //设置默认
  setDefault(id){
    let data:any={
      memberId:this.memberId,
      id:id
    }
    this.apiSev.api("post", "app/goodsaddress/set/default", (res) => {
      if (res.response == 'success') {
		  this.apiSev.itip('设置默认地址成功');
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }

  /*编辑*/
  placeEdit(id,state){
    this.navCtrl.push(mallPlaceAddPage,{
      UPDATEADDRESSID:id,
      state:state,
      placeType:0
    })
  }

  /**
   * 删除地址
   */
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
			  id:id
			}
			this.apiSev.api("post", "app/goodsaddress/del", (res) => {
			  if (res.response == 'success') {
				this.pageIndex = 1;
				this.places=[];
				this.loadData(this.pageIndex);
			  } else {
				this.apiSev.itip(data.msg);
			  }
			},(error) => {
			  this.apiSev.itip('操作失败！');
			  return;
			}, 500,data);
          }
        }
      ]
    });
    confirm.present();


  }



  //新增按钮
  placeAdd(e){
    this.navCtrl.push(mallPlaceAddPage,{
      placeType:1
    })
    e.preventDefault();
  }


}
