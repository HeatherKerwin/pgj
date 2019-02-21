import { Component } from '@angular/core';
import { NavController, ViewController ,NavParams, AlertController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManagePageB } from '../manage';

@Component({
  selector: 'page-edit',
  templateUrl: 'edit.html'
})
export class ManageeditPageB {
  public  tiexianType = '';
  remindDate :any;

  public isTiexian:any;

  //获取memberid
  public memberId:'';
  //获取id
  public tiexianId:any;
  //获取的参值
  public tempidex:any;

  //查询数据
  public result:any = {
    istiexian:0,
    piaojushuxing:'',//票据属性
    pjtype:1,//票据属性选择
    qixianShow:true,//承兑期限显示隐藏
    limit: '1',//承兑期限
    chengduihang: '1',//承兑行
    allprice: "",//金额
    yuelilv:'',//月利率
    nianlilv:0,//年利率
    tiexianlixi:'',//贴现利息
    tiexianjine:0,//贴现金额
    begintime: '',//下单日期
    endtime:'',//到期日期
    min: '',//到期日期最小值
    infomemo:false,//提醒check
    remindtime: '',//提醒日期
    endChok:'disabled',
    isShow2:false,//备注显示隐藏
    accountdesc:'',//备注
    undiscount:false
  };
  public orgId='';

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController,
    public params: NavParams,
    public alertCtrl: AlertController,
  ) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    });
  }

  ionViewDidEnter(){
    this.init();
  }

  //获取数据
  init() {
    this.tiexianId=this.params.get('tiexianId');
    if (this.tiexianId == null && this.tiexianId == undefined){
      return;
    }
    if (this.tiexianId != null && this.tiexianId != undefined) {
      this.apiSev.api("get", "zhangbu/init/?&id="+this.tiexianId, (res) => {
        this.result=res;
        this.result.istiexian=res.isTiexian;
        if(res.piaojushuxing=='纸票'){
          this.result.pjtype=0;
        }else if(res.piaojushuxing=='电票'){
          this.result.pjtype=1;
        }
        this.result.chengduihang = res.type1;
        this.result.begintime=new Date(res.tiexiandate).toISOString();
        this.result.endtime=new Date(res.daoqidate).toISOString();
        this.result.remindtime=new Date(res.createTime).toISOString();
        this.isTiexian=res.isTiexian;
        if (res.isTiexian==1){
          this.result.undiscount=true;
        }else if (res.isTiexian==0){
          this.result.undiscount=false;
        };
        if (this.result.pjtype==1){
          this.result.qixianShow=true;
          if (res.acceptTime!=null||res.acceptTime!=''){
            this.result.limit=res.acceptTime;
          }else {
            this.result.limit='1'
          }

        }else if (this.result.pjtype==0){
          this.result.qixianShow=false;
        };
      }, (error) => {}, 500,{});
    }
  }

  //删除
  shanchu(){
    let prompt = this.alertCtrl.create({
      title: '确认删除',
      buttons: [
        {
          text: '取消',
        },
        {
          text: '确认',
          // 确认删除
          handler: data => {
            this.tiexianId = this.params.get('tiexianId');
            this.tempidex = this.params.get('tempidex');
            if (this.tiexianId != null && this.tiexianId != undefined) {
              let del: any = {
                id: this.tiexianId,
              };
              this.apiSev.api("post", "zhangbu/shanchu/", (res) => {
                if (res.status == 'ok') {
                  let alert = this.alertCtrl.create({
                    title: '提示',
                    subTitle: '删除成功!',
                    buttons: [
                      {
                        text: '确定',
                        handler: data => {
                          this.tempidex = this.params.get('tempidex');
                          this.apiSev.tempId=this.tempidex;
                          this.navCtrl.pop();
                        }
                      },
                    ]
                  });
                  alert.present();
                }
              }, (error) => {
                this.apiSev.itip('操作失败！');
                return;
              }, 500, del);
            }
          }
        }
      ]
    });
    prompt.present();
  }



  //选择票据属性
  attribute(pjtype){
    if(this.result.pjtype == 1) {
      this.result.piaojushuxing = "0";
      this.result.qixianShow = true;
      if(this.result.limit == 1){
        let timeEnd= new Date(this.result.begintime).getTime()+360*24*3600*1000;
        this.result.endtime = new Date(timeEnd).toISOString();
      }else if(this.result.limit == 0){
        let timeEnd= new Date(this.result.begintime).getTime()+180*24*3600*1000;
        this.result.endtime = new Date(timeEnd).toISOString();
      }
    }else {
      this.result.piaojushuxing = "1";
      this.result.qixianShow = false;
      let timeEnd= new Date(this.result.begintime).getTime()+180*24*3600*1000;
      this.result.endtime = new Date(timeEnd).toISOString();
    };
  }

  //计算贴现利息
  onKeyup(){
    if(this.result.allprice == "" || this.result.yuelilv == ""){
      return;
    }
    let num1 = 0;
    let num2 = 0;
    let num3 = 0;

    if(this.result.allprice!=null && this.result.allprice.trim()!=''){
      num1 = Number(this.result.allprice)/100000;
    }
    num2 = this.apiSev.getDateDiff(this.result.begintime,this.result.endtime);
    if(this.result.yuelilv!=null){
      num3 = Number(this.result.yuelilv)/30/1000*100000;
    }

    let txlx = Number(num1)*Number(num2)*Number(num3)*10000;
    this.result.tiexianlixi = txlx.toFixed(2);
    this.result.tiexianjine = (Number(this.result.allprice)-txlx/10000).toFixed(2);
    let yearRate = Number(this.result.yuelilv) * 1.2 * 10000 / 10000;
    this.result.nianlilv = Number(yearRate.toFixed(3));
  }


  blurEvent(){
    if(this.result.allprice == "" || this.result.yuelilv == ""){
      return;
    }
    let num1 = 0;
    let num2 = 0;
    let num3 = 0;

    if(this.result.allprice!=null && this.result.allprice.trim()!="")num1 = Number(this.result.allprice)/100000;
    num2 = this.apiSev.getDateDiff(this.result.begintime,this.result.endtime);
    if(this.result.yuelilv!=null && this.result.yuelilv.trim()!="")num3 = Number(this.result.yuelilv)/30/1000*100000;
    let txlx = Number(num1)*Number(num2)*Number(num3)*10000;
    this.result.tiexianlixi = txlx.toFixed(2);
    this.result.tiexianjine = (Number(this.result.allprice)-txlx/10000).toFixed(2);
  }

  tiexianlixi(){
    if(this.result.allprice == "" || this.result.yuelilv == ""){
      return;
    }
    this.result.tiexianjine=Number(this.result.allprice) - Number(this.result.tiexianlixi)/10000;
  }

  nianlilv(){
    let monthRate = this.result.nianlilv / 1.2 * 10000 / 10000;
    this.result.yuelilv = monthRate.toFixed(3);
  };


  //设置交易提醒
  checkInfo() {
    if(this.result.infomemo){
      this.result.infomemo = false;
      this.result.isShow2 = false;
      this.result.endChok = '';
    }else{
      this.result.infomemo = true;
      this.result.isShow2 = true;
      this.result.endChok = 'disabled';
    }
  }

  //已贴现保存
  baocun() {

    if(this.result.allprice ==''){
      this.apiSev.itip('总金额必填');
      return;
    }
    if(this.result.tiexianlixi ==''){
      this.apiSev.itip('贴现利息必填');
      return;
    }


    let data:any={
      allprice:this.result.allprice,
      type1:this.result.chengduihang,
      accountdesc:this.result.accountdesc,
      begin:new Date(this.result.begintime).getFullYear() + "-" + ("0"+(new Date(this.result.begintime).getMonth() + 1)).slice(-2) + "-" + new Date(this.result.begintime).getDate(),
      end:new Date(this.result.endtime).getFullYear() + "-" + ("0"+(new Date(this.result.endtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.result.endtime).getDate(),
      memberid:this.orgId,
      id:this.tiexianId,
      belong:1,
    };
    //是否设置提醒时间
    if (this.result.infomemo==true){
      data.remindtime=new Date(this.result.remindtime).getFullYear() + "-" + ("0"+(new Date(this.result.remindtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.result.remindtime).getDate();
    }
    //区分已贴现未贴现
    if (this.isTiexian == 1) {
      data.yuelilv = this.result.yuelilv;
      data.nianlilv = this.result.nianlilv;
      data.tiexianlixi = this.result.tiexianlixi;
      data.tiexianjine = this.result.tiexianjine;
      data.flag=1;
    }else if(this.isTiexian == 0 ){
      data.flag=0;
    }

    this.apiSev.api("post", "zhangbu/editpiaoju/", (res) => {
      if (res.status == 'ok') {
        let alert = this.alertCtrl.create({
          title: '提示',
          subTitle: '操作成功!',
          buttons: [
            {
              text: '确定',
              handler: data => {
                this.tempidex = this.params.get('tempidex');
                this.navCtrl.push(ManagePageB, {
                  tempidex: this.tempidex
                })
              }
            },
          ]
        });
        alert.present();
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);


/*    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "zhangbu/resultpiaoju/?allprice="+this.result.allprice+"&type1="+this.result.chengduihang+"&yuelilv="+this.result.yuelilv+"&nianlilv="+this.result.nianlilv+"&tiexianlixi="+this.result.tiexianlixi+"&tiexianjine="+this.result.tiexianjine+"&remindtime="+this.remindDate+"&begin="+this.result.begintime+"&end="+this.result.endtime+"&accountdesc="+this.result.accountdesc+"&memberid="+data.id+"&id"+this.tiexianId+"&flag=1&belong=1", (res) => {}, (error) => {}, 500,{});
    });

    this.navCtrl.push(ManagePageB,{
      tempidex:1
    });*/

  }

}
