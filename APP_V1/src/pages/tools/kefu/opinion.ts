import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-opinion',
  templateUrl: 'opinion.html'
})


export class OpinionPage {

  public messagecontent='';
  public memberId='';



  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id
    });
  }


  opinioninfor() {

/*    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "app/message/?messagecontent="+this.messagecontent+"&memberid="+data.id+"&belong=1", (res) => {
        if(res[0].response=='success'){
          this.apiSev.itip('反馈成功');
        }else {
          this.apiSev.itip('反馈失败');
        }

      }, (error) => {},1,{});
    })*/
    if(this.messagecontent==null ||this.messagecontent==''){
      this.apiSev.itip("内容不能为空");
      return;
    }

    let opinion = {
      messagecontent: this.messagecontent,
      memberid: this.memberId,
      belong: 1
    };
    this.apiSev.api("post", "app/message/", (res) => {

      if (res[0].response == 'success') {
        this.apiSev.itip('反馈成功');
        this.messagecontent='';
      }else{
        this.apiSev.itip('反馈失败');
        return;
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');}, 500, opinion)
    }
  }
