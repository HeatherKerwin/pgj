import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-result',
  templateUrl: 'result.html'
})



export class ResultPage {

  public setNumber='';  //公催号
  public ctresults=false; //结果判断
  public memberid;
  public gongcui={        //返回值
    gongcuinumber:'',
    gongcuimember:'',
    fayuan:'',
    gongcuidateStr:''
  };


  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberid = data.id
    });
  }

  //未输入汇号
  recordBInfor() {
    if (this.setNumber.length == 16 || this.setNumber.length == 30 ){
    }else {
      this.apiSev.itip('请先输入正确的汇票票号！');
      this.ctresults=false;
      return;
    }

    //get方法
/*    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "app/gongcui/?gongcuinumber="+this.setNumber+"&memberid="+data.id+"&belong=1", (res) => {
        if(res[0].response=='success'){
          this.gongcui = res[0].msg;
          this.ctresults = true;
        }else {
          this.apiSev.itip('暂无查询结果');
          this.ctresults = false;
          return;
      }

      }, (error) => {},1,{});
    })*/

//post方法
    let result = {
      gongcuinumber: this.setNumber,
      memberid: this.memberid,
      belong: 1
    };
    this.apiSev.api("post", "app/gongcui/", (res) => {

      if (res[0].response == 'success') {
        this.gongcui = res[0].msg;
        this.ctresults = true;
      }else{
        this.apiSev.itip('暂无查询结果');
        this.ctresults = false;
        return;
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');}, 500, result)
}
}
