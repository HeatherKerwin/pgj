import { Component } from '@angular/core';
import { NavController, ViewController , AlertController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManagePage } from '../manage';
import { DiscountYPPage } from '../../../discount/discountYP';

@Component({
  selector: 'page-record',
  templateUrl: 'record.html'
})
export class RecordPage {
  public  tiexianType = '';
  remindDate :any;

  //查询数据
  //已贴现
  public recordA:any = {
    istiexian:'0',//已贴现、未贴现
    piaojushuxing:'',//票据属性
    pjtype:2,//票据属性选择
    isShow1:true,//承兑期限显示隐藏
    limit: '1',//承兑期限
    chengduihang: '1',//承兑行
    allprice: '',//金额
    yuelilv:'',//月利率
    nianlilv:0,//年利率
    tiexianlixi:'',//贴现利息
    tiexianjine:0,//贴现金额
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期
    min: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期最小值
    infomemo:false,//提醒check
    endChok:true,
    remindtime:new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//提醒日期
    isShow2:false,//备注显示隐藏
    accountdesc:''//备注
  };
  //未贴现
  public recordB:any = {
    istiexian:'0',//已贴现、未贴现
    //piaojushuxing:'',//票据属性
    pjtype:2,//票据属性选择
    isShow1:true,//承兑期限显示隐藏
    limit: '1',//承兑期限
    chengduihang: '1',//承兑行
    allprice: '',//金额
    yuelilv:'',//月利率
    nianlilv:0,//年利率
    tiexianlixi:'',//贴现利息
    tiexianjine: 0,//贴现金额
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期
    min: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期最小值
    infomemo:false,//提醒check
    endChok:true,
    remindtime:new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//提醒日期
    isShow2:false,//备注显示隐藏
    accountdesc:''//备注
  };

  public memberId='';

  //切换
  segmentsArray = ['discount','undiscount'];
  pet: string = this.segmentsArray[0];

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController,
    public alertCtrl: AlertController
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    });

    this.recordB.min = new Date().toISOString();
    this.recordB.remindtime = new Date().toISOString();
  }


  //选择票据属性
  attribute(pjtype){
    if(this.recordA.pjtype == 2) {
      this.recordA.piaojushuxing = "0";
      this.recordA.isShow1 = true;
      if(this.recordA.limit == 1){
        let timeEnd= new Date(this.recordA.begintime).getTime()+360*24*3600*1000;
        this.recordA.endtime = new Date(timeEnd).toISOString();
      }else if(this.recordA.limit == 0){
        let timeEnd= new Date(this.recordA.begintime).getTime()+180*24*3600*1000;
        this.recordA.endtime = new Date(timeEnd).toISOString();
      }
    }else {
      this.recordA.piaojushuxing = "1";
      this.recordA.isShow1 = false;
      let timeEnd= new Date(this.recordA.begintime).getTime()+180*24*3600*1000;
      this.recordA.endtime = new Date(timeEnd).toISOString();
    };

    if(this.recordB.pjtype == 2) {
      this.recordB.piaojushuxing = "0";
      this.recordB.isShow1=true;
      if(this.recordB.limit == 1){
        let timeEnd= new Date(this.recordB.begintime).getTime()+360*24*3600*1000;
        this.recordB.endtime = new Date(timeEnd).toISOString();
      }else if(this.recordB.limit == 0){
        let timeEnd= new Date(this.recordB.begintime).getTime()+180*24*3600*1000;
        this.recordB.endtime = new Date(timeEnd).toISOString();
      }
    }else {
      this.recordB.piaojushuxing = "1";
      this.recordB.isShow1 = false;
      let timeEnd= new Date(this.recordB.begintime).getTime()+180*24*3600*1000;
      this.recordB.endtime = new Date(timeEnd).toISOString();
    };
  }

  //计算贴现利息
  onKeyup(){
    //计算贴现利息
      if(this.recordA.allprice == "" || this.recordA.yuelilv == ""){
        return;
      }
      let num1 = 0;
      let num2 = 0;
      let num3 = 0;

      if(this.recordA.allprice!=null && this.recordA.allprice.trim()!="")num1 = Number(this.recordA.allprice)/100000;
      num2 = this.apiSev.getDateDiff(this.recordA.begintime,this.recordA.endtime);
      if(this.recordA.yuelilv!=null && this.recordA.yuelilv.trim()!="")num3 = Number(this.recordA.yuelilv)/30/1000*100000;

      let txlx = Number(num1)*Number(num2)*Number(num3)*10000;
      this.recordA.tiexianlixi = txlx.toFixed(2);
      this.recordA.tiexianjine = Number(this.recordA.allprice)-txlx/10000;

      let yearRate = Number(this.recordA.yuelilv) * 1.2 * 10000 / 10000;
      this.recordA.nianlilv = Number(yearRate.toFixed(3));
  }

  blurEvent(){
    if(this.recordA.allprice == "" || this.recordA.yuelilv == ""){
      return;
    }
    let num1 = 0;
    let num2 = 0;
    let num3 = 0;

    if(this.recordA.allprice!=null && this.recordA.allprice.trim()!="")num1 = Number(this.recordA.allprice)/100000;
    num2 = this.apiSev.getDateDiff(this.recordA.begintime,this.recordA.endtime);
    if(this.recordA.yuelilv!=null && this.recordA.yuelilv.trim()!="")num3 = Number(this.recordA.yuelilv)/30/1000*100000;
    let txlx = Number(num1)*Number(num2)*Number(num3)*10000;
    this.recordA.tiexianlixi = txlx.toFixed(2);
    this.recordA.tiexianjine = Number(this.recordA.allprice)-txlx/10000;
  }

  nianlilv() {
    let monthRate = this.recordA.nianlilv / 1.2 * 10000 / 10000;
    this.recordA.yuelilv = monthRate.toFixed(3);
  };


  //设置交易提醒
  checkInfo() {
    if(this.recordA.infomemo){
      this.recordA.infomemo = false;
      this.recordA.isShow2 = false;
      this.recordA.endChok = true;
    }else{
      this.recordA.infomemo = true;
      this.recordA.isShow2 = true;
      this.recordA.endChok = false;
    };
    if(this.recordB.infomemo){
      this.recordB.infomemo = false;
      this.recordB.isShow2 = false;
      this.recordB.endChok = true;
    }else{
      this.recordB.infomemo = true;
      this.recordB.isShow2 = true;
      this.recordB.endChok = false;
    }
  }

  //已贴现保存
  recordAInfor(tempidex) {
    if(this.recordA.allprice ==''){
      this.apiSev.itip('总金额必填');
      return;
    };
    if(this.recordA.tiexianlixi ==''){
      this.apiSev.itip('贴现利息必填');
      return;
    };
    if(this.recordA.pjtype==1){
      this.recordA.piaojushuxing='纸票'
    }else if(this.recordA.pjtype==2){
      this.recordA.piaojushuxing='电票'
    }

    let data:any={
      allprice:this.recordA.allprice,
      type1:this.recordA.chengduihang,
      begin:this.recordA.begintime,
      end:this.recordA.endtime,
      isTiexian:1,
      piaojushuxing:this.recordA.piaojushuxing,
      memberid:this.memberId,
      tiexianType:'手动',
      belong:'0',
      limit:this.recordA.limit,
      yuelilv:this.recordA.yuelilv,
      nianlilv:this.recordA.nianlilv,
      tiexianlixi:this.recordA.tiexianlixi,
      tiexianjine:this.recordA.tiexianjine,
    }
    if (this.recordA.infomemo==true){
      data.remindtime=new Date(this.recordA.remindtime).getFullYear() + "-" + ("0"+(new Date(this.recordA.remindtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.recordA.remindtime).getDate();
      data.accountdesc=this.recordA.accountdesc
    }
    this.apiSev.api("post", "zhangbu/addpiaoju/", (res) => {
      if (res.status == 'ok') {
        let alert = this.alertCtrl.create({
          title: '提示',
          subTitle: '操作成功!',
          buttons: [
            {
              text: '确定',
              handler: data => {
                this.navCtrl.push(ManagePage,{
                  tempidex:1
                });
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
  }

  //未贴现保存
  recordBInfor() {
    if(this.recordB.allprice ==''){
      this.apiSev.itip('总金额必填');
      return;
    }
    if(this.recordB.pjtype==1){
      this.recordB.piaojushuxing='纸票'
    }else if(this.recordB.pjtype==2){
      this.recordB.piaojushuxing='电票'
    }
    let data:any={
      allprice:this.recordB.allprice,
      type1:this.recordB.chengduihang,
      begin:this.recordB.begintime,
      end:this.recordB.endtime,
      isTiexian:0,
      piaojushuxing:this.recordB.piaojushuxing,
      memberid:this.memberId,
      tiexianType:'手动',
      belong:'0',
      limit:this.recordB.limit,
    }
    if (this.recordB.infomemo==true){
      data.remindtime=new Date(this.recordB.remindtime).getFullYear() + "-" + ("0"+(new Date(this.recordB.remindtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.recordB.remindtime).getDate();
      data.accountdesc=this.recordB.accountdesc
    }
    this.apiSev.api("post", "zhangbu/addpiaoju/", (res) => {
      if (res.status == 'ok') {
        let alert = this.alertCtrl.create({
          title: '提示',
          subTitle: '操作成功!',
          buttons: [
            {
              text: '确定',
              handler: data => {
                this.navCtrl.push(ManagePage,{
                  tempidex:2
                });
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
  }

  //未贴现一键贴现
  tiexian(){
    let piaojushuxing = this.recordB.pjtype;
    let chengduihang = this.recordB.chengduihang;
    let begintime = this.recordB.begintime;
    let endtime = this.recordB.endtime;
    let allprice = this.recordB.allprice;
    let limit = this.recordB.limit;
    this.navCtrl.push(DiscountYPPage,{
      TEMP_TYPE1:piaojushuxing,
      TEMP_TYPE2:chengduihang,
      TEMP_START:begintime,
      TEMP_END:endtime,
      TEMP_ALLMONEY:allprice,
      TEMP_ACCEPTTIME:limit,
    });
  }

  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==2){
      if(this.segmentsArray.indexOf(this.pet)<3){
        this.pet = this.segmentsArray[this.segmentsArray.indexOf(this.pet)+1];
      }
    }
    //向右滑
    if(event.direction==4){
      if(this.segmentsArray.indexOf(this.pet)>0){
        this.pet = this.segmentsArray[this.segmentsArray.indexOf(this.pet)-1];
      }
    }
  }

}
