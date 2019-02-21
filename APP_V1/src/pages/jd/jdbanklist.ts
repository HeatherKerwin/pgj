import {Component} from '@angular/core';
import {NavController, ViewController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {jdbankbdPage} from "./jdbankbd";
import {jdbankbdBPage} from "./jdbankbdB";

@Component({
  selector: 'page-jdbanklist',
  templateUrl: 'jdbanklist.html'
})


export class jdbanklistPage {

  public messagecontent: '';
  public memberId;

  //银行卡列表
  public accountList:any=[];
  //银行卡列表
  public accountListB:any=[];


  public pet:any='manage1';

  //公司名称
  public accName:any;

  //京东开户银行卡
  public jdkhbank:any={};

  public Mebank:boolean=false;


  constructor(public storage: Storage,public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.jdkhbank.accountNo = this.params.get("accountNo");
    this.jdkhbank.bankName = this.params.get("bankName");
  }


  //前往京东绑定银行页面
  jdbangd() {
    this.navCtrl.push(jdbankbdPage)
  }

  ionViewDidEnter(){
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      //京东开户
      this.queryUserBindAccounts();
    })
  }


  //可收票
  queryUserBindAccountsA() {
    let data: any = {
      memberId:this.memberId,
      type:2,
      status:1
    };
    this.apiSev.api("newpost", "jdjrcard/queryUserBindAccounts", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.accountListB=data.data.accountList;

      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //银行列表
  queryUserBindAccounts() {
    let data: any = {
      memberId:this.memberId,
      type:1
    };
    this.apiSev.api("newpost", "jdjrcard/list", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.accountList=data.data;
        this.accName=this.accountList[0].bankAccountName;
        if(this.jdkhbank.accountNo!=this.accountList[0].bankAccountNo){
          this.Mebank=true;
        }
        this.queryUserBindAccountsA();
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //卡片选择
  cardxuanz(item){
    if(item.status==0){
      this.navCtrl.push(jdbankbdBPage,{
        BANK:item
      });
    }
  }

}
