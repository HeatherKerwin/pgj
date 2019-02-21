import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-expiremanage',
  templateUrl: 'expiremanage.html'
})


export class ExpiremanagePage {

    public messagecontent: '';
    public memberId;

  public pageNo1=1;
  public pageNo2=1;
  public pageMax1=1;
  public pageMax2=1;
  public guoqiLists:any=[];


    constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
      this.storage.get('userInfo').then((data) => {
        this.memberId = data.id;
        this.guoqiData(1);
      });

    }


  //显示过期
  guoqiData(pagenumber) {
    let data:any={
      pageIndex:pagenumber,
      pageSize:10,
      memberId:this.memberId,            //用户主键
      recordType :'HOLD',    //类型（HOLD持有、OUT已出票、IN待入账）
    };
    this.apiSev.api("newpost", "draftrecord/page/notice", (res) => {
      let data=res.data;
      this.pageMax1=data.data.pages;
      if (data.response == 'success') {
        let objs = data.data.list;
        if(objs.length>0) {
          if(objs.length==10){//说明可能还有数据
          }
          for (let i = 0; i < objs.length; i++) {
            this.guoqiLists.push(objs[i]);
          }
        }
        console.log(this.guoqiLists);
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }


  doInfinite1(infiniteScroll) {
    setTimeout(() => {
      if (this.pageMax1<this.pageNo1+1)
      {
        infiniteScroll.complete();
      }
      else{
        this.pageNo1=this.pageNo1+1;
        this.guoqiData(this.pageNo1);
        infiniteScroll.complete();
      }
    }, 500);
  }
}
