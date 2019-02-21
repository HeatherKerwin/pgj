import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-openaccountListB',
  templateUrl: 'openaccountListB.html'
})
export class OpenaccountListBPage {
  banklists=[];
  banklist:any={};
  public keyword='';
  public isOk:boolean=false;
  public pageIndex='1';
  public orgType:any;
  public meType:any;
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
      this.memberId = data.id
    });
    let meType = this.params.get("ME_TYPE"); //我类型
    this.meType = meType;
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
  }



  //搜索
  search(pageIndex){
    this.banklists=[];
    let data:any={
      pageIndex:pageIndex,
        memberId:this.memberId,
      bank_branch:this.keyword,
    };
    this.apiSev.api("newpost", "cib/query/back", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.isOk=false;
        if(objs.data.banks==null||objs.data.banks=='') {
          this.apiSev.itip("未找到该银行信息,请重新填写");
        }else {
          if (objs.data.banks.length > 0) {
            if (objs.data.banks.length == 10) {//说明可能还有数据
              this.isOk = true;
            }
            for (let i = 0; i < objs.data.banks.length; i++) {
              this.banklists.push(objs.data.banks[i]);
            }
          }
        }
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
  myColumn(cnaps_code, bank_branch, bank_no) {
    this.apiSev.parmData = {
      bankNo: bank_no,
      bankName: bank_branch,
      CnapsCode: cnaps_code
    };
    this.navCtrl.pop();
  }
}
