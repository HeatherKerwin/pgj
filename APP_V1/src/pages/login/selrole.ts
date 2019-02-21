import { Component } from '@angular/core';
import { NavController, ViewController ,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { TabsPage } from '../tabs/tabs';
import { TabsPageB } from '../tabs/tabsB';

@Component({
  selector: 'page-selrole',
  templateUrl: 'selrole.html'
})
export class Selrole {
  public memberId:any;
  public orgId:any;

  public user:any = {};
  constructor(
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    })
  }

  //选择报价方
  baojia(){
    this.storage.set('role', 'baojia');
    this.viewCtrl.dismiss();
	this.selectOrg(1);
    this.navCtrl.push(TabsPage);
  }

  //选择出票方
  chupiao(){
    this.storage.set('role', 'chupiao');
    this.viewCtrl.dismiss();
	this.selectOrg(0);
    this.navCtrl.push(TabsPageB);
  }

  //构造orgID
  selectOrg(flag){
    let data:any={
      memberId: this.memberId,
      type: flag
    }
    if(this.orgId!=null&&this.orgId!= ""&&this.orgId!=undefined){
      data.orgId= this.orgId;
    }
    this.apiSev.api("post", "app/org/save2", (res) => {
      if (res.response == 'success') {
        let result = res.data;
        if (result.id != null) {
          this.storage.set('ORGID', result.id);//记录机构主键,对应orgId
        }
      }
    },(error) => {}, 500,data);
  }

  close () {
  	this.viewCtrl.dismiss();
  }

}
