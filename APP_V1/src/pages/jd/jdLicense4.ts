import {Component} from '@angular/core';
import {NavController, ViewController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {OpenaccountSuccessPage} from "../openaccount/openaccountSuccess";
import {JdSuccessPage} from "./jdSuccess";


@Component({
  selector: 'page-jdlicense4',
  templateUrl: 'jdlicense4.html'
})


export class jdlicense4Page {

  public memberId = '';
  public orgType:any;
  public meType:any;

  //按钮判断
  public Tonosuccess = true;
  public Tosuccess = false;

  //京东数组
  public jdData:any={};


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      //京东开户
      this.jdjr();
    })

  }

  //取京东信息
  jdjr(){
    let data:any={
      memberId:this.memberId,
    };
    this.apiSev.api("newpost", "jdjr/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.jdData=data.data;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  switching(){
    if(this.jdData.allprice > 0){
      this.Tonosuccess = false;
      this.Tosuccess = true;
    }else {
      this.Tonosuccess = true;
      this.Tosuccess = false;
    }
  }

  //取京东信息
  realnamepaycheckmoney(){
    let data:any={
      id:this.jdData.id,
      amount:this.jdData.allprice
    };
    this.apiSev.api("newpost", "jdjr/update/realnamepaycheckmoney", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.navCtrl.push(JdSuccessPage);
        this.apiSev.itip("开户成功");
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}
