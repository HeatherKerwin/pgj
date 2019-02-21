import { Component } from '@angular/core';
import { NavController, ViewController ,NavParams ,AlertController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManagePage } from '../manage';

@Component({
  selector: 'page-remind-details',
  templateUrl: 'remind-details.html'
})
export class ReminddetailsPage {

  public remindid:any;
  public data ={
    ntid: '',
    allprice: '',
    expiredate:'',
    noticeaddtime:'',
    noticedesc:'',
  }


  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController,
    public alertCtrl: AlertController,
    public params: NavParams
  ) {
    this.remindid= this.params.get("accId");
    this.initData();
  }

  //获取数据
  initData() {
    let data = {
      accountrecordId:this.remindid,
    };
    this.apiSev.api("post", "app/noticerecord/getByAccountrecordId", (res) => {
      if (res.response == 'success') {
        this.data.ntid=res.data.id;
        this.data.noticedesc=res.data.noticedesc;
        this.data.allprice=res.data.allprice;
        this.data.expiredate=new Date(res.expiredate).toISOString();
        this.data.noticeaddtime=new Date(res.noticeaddtime).toISOString();
      }else{
        this.apiSev.itip('反馈失败');
        return;
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');}, 500, data)
  }

/*      this.apiSev.api("get", "app/noticerecord/getByAccountrecordId?accountrecordId="+this.remindid, (res) => {
        if(res.response=='success'){
          this.data.ntid=res.data.id;
          this.data.noticedesc=res.data.noticedesc;
          this.data.allprice=res.data.allprice;
          this.data.expiredate=new Date(res.expiredate).toISOString();
          this.data.noticeaddtime=new Date(res.noticeaddtime).toISOString();
        }
      }, (error) => {}, 500,{});
  }*/

  //确认修改
  confirmN(){
/*    let time = this.data.noticeaddtime;
    let note = this.data.noticedesc;
    if (time == this.data.noticeaddtime && note == this.data.noticedesc) {
      this.apiSev.itip("无修改");
      return
    } //无更改不操作*/
     let noticetime = new Date(this.data.noticeaddtime).getFullYear() + "-" + ("0"+(new Date(this.data.noticeaddtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.data.noticeaddtime).getDate();

    this.apiSev.api("get", "app/noticerecord/update?id="+this.data.ntid+"&noticedesc="+this.data.noticedesc+"&noticetime="+noticetime, (res) => {
      this.apiSev.itip("修改成功");
      this.navCtrl.push(ManagePage);
    }, (error) => {}, 500,{});
  }

  //删除
  deleteN() {
    let confirm = this.alertCtrl.create({
      title: '提示',
      message: '您确定要删除吗?',
      buttons: [
        {
          text: '取消',
          handler: () => {
            return;
          }
        },
        {
          text: '确定',
          handler: () => {
            this.apiSev.api("get", "app/noticerecord/del?id="+this.data.ntid, (res) => {
              if (res.response == 'success') {
                let alert = this.alertCtrl.create({
                  title: '提示',
                  subTitle: '删除成功!',
                  buttons: [
                    {
                      text: '确定',
                      handler: data => {
                        this.navCtrl.push(ManagePage);
                      }
                    },
                  ]
                });
                alert.present();
              }
            }, (error) => {}, 500,{});
          }
        }
      ]
    });
    confirm.present();
  }

}
