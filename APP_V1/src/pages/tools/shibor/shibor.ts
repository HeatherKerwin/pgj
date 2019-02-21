 import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-shibor',
  templateUrl: 'shibor.html'
})



export class ShiborPage {

  public dayshow = new Date().toISOString();
  public shiboRefresh=false; //数据结果页面
  public memberid;
  public picture1=true;
  public picture2=true;
  public picture3=true;
  public picture4=true;
  public picture5=true;
  public picture6=true;
  public picture7=true;
  public picture8=true;
  public picture9=true;
  public picture10=true;
  public picture11=true;
  public picture12=true;
  public picture13=true;
  public picture14=true;
  public picture15=true;
  public picture16=true;

  public shiBor = {
    shibor1: 0,
    shibor2: 0,
    shibor3: 0,
    shibor4: 0,
    shibor5: 0,
    shibor6: 0,
    shibor7: 0,
    shibor8: 0,
    updown1: 0,
    updown2: 0,
    updown3: 0,
    updown4: 0,
    updown5: 0,
    updown6: 0,
    updown7: 0,
    updown8: 0
  };


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {

    this.storage.get('userInfo').then((data) => {
      this.memberid = data.id;
      this.loadData();
    });
  }

  ToDay(){
    this.loadData();
  }

  leftDay() {
    let timeChange = new Date(this.dayshow).getTime() - 24 * 60 * 60 * 1000;
    this.dayshow = new Date(timeChange).toISOString();
  }

  rightDay() {
    let timeChange = new Date(this.dayshow).getTime() + 24 * 60 * 60 * 1000;
    this.dayshow = new Date(timeChange).toISOString();
  }



  loadData() {
    let dayshow = new Date(this.dayshow).getFullYear() + "-" + ("0"+(new Date(this.dayshow).getMonth() + 1)).slice(-2) + "-" + new Date(this.dayshow).getDate();


/*    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "app/shibor/?day="+dayshow+"&memberid="+data.id+"&belong=1", (res) => {
        if(res[0].response=='success') {
          this.shiBor = res[0].msg;
          this.shiboRefresh = true;
        }
          else {
          this.apiSev.itip('暂无数据');
          this.shiboRefresh = false;
          return;
            }
      }, (error) => {},1,{});
    });*/


    let shibor = {
      day: dayshow,
      memberid: this.memberid,
      belong: 1
    };
    this.apiSev.api("post", "app/shibor/", (res) => {

        if (res[0].response == 'success') {
          this.shiBor = res[0].msg;
          this.shiboRefresh = true;
          if (this.shiBor.updown1 > 0) {
            this.picture1 = true;
            this.picture2 = false;
          }
          else {
            if (this.shiBor.updown1 < 0) {
              this.picture1 = false;
              this.picture2 = true;
            } else {
              this.picture1 = false;
              this.picture2 = false;
            }
          }
          if (this.shiBor.updown2 > 0) {
            this.picture3 = true;
            this.picture4 = false;
          } else {
            if (this.shiBor.updown2 < 0) {
              this.picture3 = false;
              this.picture4 = true;
            } else {
              this.picture3 = false;
              this.picture4 = false;
            }
          }
          if (this.shiBor.updown3 > 0) {
            this.picture5 = true;
            this.picture6 = false;
          } else {
            if (this.shiBor.updown3 < 0) {
              this.picture5 = false;
              this.picture6 = true;
            } else {
              this.picture5 = false;
              this.picture6 = false;
            }
          }
          if (this.shiBor.updown4 > 0) {
            this.picture7 = true;
            this.picture8 = false;
          } else {
            if (this.shiBor.updown4 < 0) {
              this.picture7 = false;
              this.picture8 = true;
            } else {
              this.picture7 = false;
              this.picture8 = false;
            }
          }
          if (this.shiBor.updown5 > 0) {
            this.picture9 = true;
            this.picture10 = false;
          } else {
            if (this.shiBor.updown5 < 0) {
              this.picture9 = false;
              this.picture10 = true;
            } else {
              this.picture9 = false;
              this.picture10 = false;
            }
          }
          if (this.shiBor.updown6 > 0) {
            this.picture11 = true;
            this.picture12 = false;
          } else {
            if (this.shiBor.updown6 < 0) {
              this.picture11 = false;
              this.picture12 = true;
            } else {
              this.picture11 = false;
              this.picture12 = false;
            }
          }
          if (this.shiBor.updown7 > 0) {
            this.picture13 = true;
            this.picture14 = false;
          } else {
            if (this.shiBor.updown7 < 0) {
              this.picture13 = false;
              this.picture14 = true;
            } else {
              this.picture13 = false;
              this.picture14 = false;
            }
          }
          if (this.shiBor.updown8 > 0) {
            this.picture15 = true;
            this.picture16 = false;
          } else {
            if (this.shiBor.updown8 < 0) {
              this.picture15 = false;
              this.picture16 = true;
            } else {
              this.picture15 = false;
              this.picture16 = false;
            }
          }
        }else {
          this.apiSev.itip('暂无数据');
          this.shiboRefresh = false;
          return;
        }
      }, (error) => {
        this.apiSev.itip('暂无数据');}, 500, shibor)
  }
}

