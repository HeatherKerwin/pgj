import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'page-noticeDetail',
  templateUrl: 'noticeDetail.html'
})
export class NoticeDetailPage {
  newsid:any;

  public news = [];
  constructor(
    private sanitizer: DomSanitizer,
    public viewCtrl: ViewController,
    public navCtrl: NavController,
    public apiSev: apiService,
    public params: NavParams
    ) {

  }

  ionViewDidLoad() {
    this.newsid=this.params.get('newsid');
    this.apiSev.api("get", "app/newsdetail/?newsid="+this.newsid, (res) => {
      if(res[0].response == 'success'){
        this.news=res[0].msg;
      }
    }, (error) => {},1,{});
  }

  //转译HTML为文本
  assembleHTML(strHTML:any) {
    return this.sanitizer.bypassSecurityTrustHtml(strHTML);
  }

}
