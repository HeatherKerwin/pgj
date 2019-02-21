import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-mallStrategy',
  templateUrl: 'mallStrategy.html'
})
export class mallStrategyPage {

  public strategys=[
    {
      title:'签到',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'报价',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'出票',
      integral:'+20',
      limit:'每日5次'
    },
    {
      title:'接单',
      integral:'+20',
      limit:'每日5次'
    },
    {
      title:'查询查复',
      integral:'+20',
      limit:'每日5次'
    },
    {
      title:'确认交易',
      integral:'+30',
      limit:'每日1次'
    },
    {
      title:'评价',
      integral:'+10',
      limit:'每日5次'
    },
    {
      title:'意见反馈',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'访问论坛',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'询价',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'计算器',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'推荐好友',
      integral:'+20',
      limit:'每日1次'
    },
    {
      title:'查公催',
      integral:'+10',
      limit:'每日1次'
    },
    {
      title:'记笔票据',
      integral:'+20',
      limit:'每日1次'
    }
  ];
  public strategy:any={
    title:'',
    integral:'',
    limit:''
  };

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
  }

}
