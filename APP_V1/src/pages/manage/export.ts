import { Component } from '@angular/core';
import {NavController, NavParams, ViewController} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-export',
  templateUrl: 'export.html'
})

export class ExportPage {
  public today = new Date().toISOString();
  public beginTime = new Date().toISOString();
  public endTime = new Date().toISOString();
  public type1;
  public type2;
  public type3;
  public email = "";
  public role;
  public memberId;
  public orgId='';
  constructor(
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController,
    public params: NavParams
  ){
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    });
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
  }
  sendEmail(e){
    if(this.email == ""){
      this.apiSev.itip("请输入邮箱地址");
      return;
    }
    let r = this.email.match(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/);
    if (!r) {
      this.apiSev.itip("输入的邮箱输入格式有误");
      return;
    }

    let data = {
      start:new Date(this.beginTime).getFullYear()+ "-" + ("0"+(new Date(this.beginTime).getMonth() + 1)).slice(-2) + "-" +new Date(this.beginTime).getDate(),
      end:new Date(this.endTime).getFullYear()+ "-" + ("0"+(new Date(this.endTime).getMonth() + 1)).slice(-2) + "-" +new Date(this.endTime).getDate(),
      email:this.email,
      memberId:this.memberId
    };

    this.apiSev.api(
      "newpost",
      "draftrecord/export/email",
      (res) => {
        this.apiSev.itip("导出成功");
        console.log(res);
      }, (error) => {
      }, 1,data);
    e.preventDefault();
  }
}
