import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-Detailedlist',
  templateUrl: 'Detailedlist.html'
})


export class DetailedlistPage {

  public memberId:any={};
  public List:any=[];

  public StaticState: boolean = true; //静态展示
  public Dynamic: boolean = false; //动态修改
  public large: boolean = true;  //点击放大按钮
  public Small:boolean = false; //点击缩小按钮
  public inventory:any={};    //票据清单

  public discount:any={
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期
    max: new Date(new Date(new Date()).getTime() + 360 * 10 * 24 * 60 * 60 * 1000).toISOString(),//到期日最小值
  };


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.List= this.params.get("LISTDATA");
  }

  //每次进入页面都加载
  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      //this.List=[];
      //this.inventorypage()
    });
  }


  //分页显示展示数据
  inventorypage() {
    let data: any = {
      pageIndex:1,
      pageSize: 20,
      memberId:this.memberId,
      orderBy:'CREATETIME_DESC',
      state:'PASS'
    };
    this.apiSev.api("newpost", "inventory/page", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        let objs = data.data.list;
        if (objs.length > 0) {
          if(objs.length==5){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            objs.EditTxt = false;
            objs[i].printtime=new Date(objs[i].printtime).toISOString();
            objs[i].endtime=new Date(objs[i].endtime).toISOString();
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


  //编辑
  setinventory(item) {
    let data: any = {
      memberId: this.memberId,
      id: item.id,
      allmoney: item.allmoney,
      bank: item.bank,
      printtime: item.printtime,
      endtime: item.endtime,
      remarks: item.remarks,
      price: item.price,
    };
    this.apiSev.api("newpost", "inventory/update", (res) => {
      let data = res.data;
      if (data.response == 'success') {
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }


}
