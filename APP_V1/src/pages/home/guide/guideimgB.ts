import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-guideimgB',
  templateUrl: 'guideimgB.html'
})


export class GuideimgBPage {
  public memberId='';
  public guideA=true;
  public guideB=false;
  public guideC=false;
  public st= 's1';



  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id
    });
  }

  //头部右侧图标按钮
  Changed(){
    if (this.st=='s1') {
      this.guideA = true;
      this.guideB = false;
      this.guideC = false;
    }
    if(this.st=='s2') {
      this.guideA = false;
      this.guideB = true;
      this.guideC = false;
    }
    if(this.st=='s3') {
      this.guideA = false;
      this.guideB = false;
      this.guideC = true;
    }
  }

  close(e) {
    this.viewCtrl.dismiss();
    e.preventDefault();
  }

  }
