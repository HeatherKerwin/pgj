import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'banksput-tools',
  templateUrl: 'banksoput.html'
})
export class BankPage {

  public downall=false;

  public items=[];
  selectbank:any;
  province:any;
  city:any;
  keyword:any;



  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService, public params: NavParams) {

    this.province=params.get('province');
    this.selectbank=params.get('selectbank');
    this.city=params.get('city');
    this.keyword=params.get('keyword');
    this.getLianhang();
  }

  getLianhang() {
    let backsoputs = {
      yinhang: this.selectbank,
      provice: this.province,
      city: this.city,
      keyword: this.keyword,
      pagenumber: 1,
      belong: 1
    };
    this.apiSev.api("post", "app/getLianhang/", (res) => {
      if (res[0].response == 'success') {
        if(res[0].msg[0].length!=0){
          this.items=res[0].msg[0];
        }else {
          this.downall=true;
        }
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');}, 500, backsoputs)

  }
}
