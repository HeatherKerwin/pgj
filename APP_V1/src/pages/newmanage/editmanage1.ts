import { Component } from '@angular/core';
import { NavController,ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { CounterPage } from '../tools/counter/counter';
import * as $ from 'jquery';

@Component({
  selector: 'page-editmanage1',
  templateUrl: 'editmanage1.html'
})


export class Editmanage1Page {

  value: HTMLImageElement;

  segmentsArray = ['manage1', 'manage2', 'manage3'];
  pet: any = this.segmentsArray[1];

  public messagecontent: '';
  public memberId;
  public titlename: any = '票据编辑';

  public discounttype:any='YD';

  //扩展字段
  public jsonlist:any={};

  public manage: any = {
    tiexianDate: new Date().toISOString(),//贴现日期
    inData: new Date().toISOString(),//入账日期
    expiryDate: new Date(new Date().getTime() + 183 * 24 * 3600 * 1000).toISOString(),//到期日期
    noticeDate: new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//提醒日期
    pledgeoutdate: new Date().toISOString(),//质押日期
    pledgeindate: new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString(),//赎回日期
    min: '1990-01-01',//到期日期最小值
  };

  public item: any = {};

  //指引
  public zy9: boolean = false;

  public maxI:any=0;

  //默认弹窗隐藏
  public maskDiv:boolean=false;



  constructor(public storage: Storage, public params: NavParams, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    let titlename = this.params.get("NAME");
    this.item = this.params.get("ITEM");
    this.draftrecordget();
    if (titlename != null && titlename != '' && titlename != 'undefined') {
      this.titlename = titlename;
      if (titlename == '票据编辑(持有中)') {
        this.pet = this.segmentsArray[1];
      } else {
        this.pet = this.segmentsArray[0];
      }
    }

    this.storage.get('manage9').then((data) => {
      if(data==null||data==''||data=='undefined'){
        this.zy9=true;
        this.storage.set('manage9', 1);
      }
    });
  }

  //到期时间和提醒时间联调
  timechanger(){
    this.manage.noticeDate=new Date(new Date(this.manage.expiryDate).getTime() - 10 * 24 * 3600 * 1000).toISOString();
  }


  //查看详情
  draftrecordget() {
    let data: any = {
      id: this.item.id,
    };
    this.apiSev.api("newpost", "draftrecord/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.manage=data.data;
        this.manage.allmoney=this.manage.allmoney/10000;
        this.manage.txje=this.manage.txje;
        this.discounttype=this.manage.draftType;
        if(this.manage.expiryDate!=null&&this.manage.expiryDate!=''&&this.manage.expiryDate!='undefined'){
          this.manage.expiryDate=new Date(new Date(this.manage.expiryDate).getTime() + 24 * 3600 * 1000).toISOString();
        }
        if(this.manage.noticeDate!=null&&this.manage.noticeDate!=''&&this.manage.noticeDate!='undefined'){
          this.manage.noticeDate=new Date(new Date(this.manage.noticeDate.getTime() + 24 * 3600 * 1000)).toISOString();
        }
        if(this.manage.expiryDate!=null&&this.manage.expiryDate!=''&&this.manage.expiryDate!='undefined'){
          this.manage.tiexianDate=new Date(new Date(this.manage.tiexianDate).getTime() + 24 * 3600 * 1000).toISOString();
        }
        if(this.manage.inData!=null&&this.manage.inData!=''&&this.manage.inData!='undefined'){
          this.manage.inData=new Date(new Date(this.manage.inData).getTime() + 24 * 3600 * 1000).toISOString();
        }

        if(data.data.json!=null&&data.data.json!=''&&data.data.json!='undefined'){
          this.jsonlist=JSON.parse(data.data.json);
          let i=1;
          $.each(this.jsonlist,function (n, value) {
            if(value.key!=""&&value.key!=null){

            }else{
              var oDiv = document.createElement('div');
              oDiv.innerHTML = '<input id="n'+i+'" type="text" data-name="' + n + '" placeholder="' + n + '" value="' + value.value + '" class="kzstyle"></input>';
              document.getElementById('title1').appendChild(oDiv);
              //遍历
              i=i+1;
            }
          });
          //最大遍历
          this.maxI=i;
        }
        this.manage.pledgeoutdate = new Date().toISOString();//质押日期
        this.manage.pledgeindate = new Date(new Date().getTime() + 173 * 24 * 3600 * 1000).toISOString();//赎回日期
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }


  //保存
  draftrecordsave() {
    if (this.manage.allmoney == null || this.manage.allmoney == '' || this.manage.allmoney == 'undefined') {
      this.apiSev.itip("请输入票面金额");
      return;
    }

    let data: any = {
      id:this.item.id,
      memberId: this.memberId, //用户主键
      //recordType:this.manage.recordType, //类型（HOLD持有、OUT已出票、IN待入账）
      draftType:this.discounttype, //票据类型（YZ银纸、YD银电、SZ商纸、SD商电）
      //bank:this.manage.bank, //承兑行全称
      //type2:'', //承兑行类型（1国股、2城商（小商）、3外资、4农商、5农合、6农信、7村镇、8城商（大商））
      //draftNo:this.manage.draftNo, //票号
      allmoney:(this.manage.allmoney*10000), //票面金额（元）
      //txje:this.manage.txje, //贴现金额（元）
      //inData:this.manage.inData, //入账日期
      //tiexianDate:this.manage.tiexianDate, //贴现日期
      //expiryDate:this.manage.expiryDate, //到期日期
      //noticeDate:this.manage.noticeDate, //提醒日期（默认设置为提醒日期前十天）仅限持有
      //draftFrom:this.manage.draftFrom, //票据来源
      //draftTo:this.manage.draftTo //票据去处
    };
    if(this.pet=='manage1'){
      if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
        this.apiSev.itip("请输入承兑行全称");
        return;
      }
      if (this.manage.txje == null || this.manage.txje == '' || this.manage.txje == 'undefined') {
        this.apiSev.itip("请输入实收票款");
        return;
      }
      data.recordType='OUT';
      data.tiexianDate=new Date(this.manage.tiexianDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.tiexianDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.tiexianDate).getDate(); //贴现日期
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      //data.txje=(this.manage.txje*10000); //贴现金额（元）
      data.txje=this.manage.txje;
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
    }else if(this.pet=='manage2'){
      if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
        this.apiSev.itip("请输入承兑行全称");
        return;
      }
      if(this.manage.txje>0){
        data.txje=this.manage.txje;
      }
      data.recordType='HOLD';
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      data.noticeDate=new Date(this.manage.noticeDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.noticeDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.noticeDate).getDate();
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
    }else {
      data.recordType='IN';
      data.inData=new Date(this.manage.inData).getFullYear()+ "-" + ("0"+(new Date(this.manage.inData).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.inData).getDate();
      data.draftFrom=this.manage.draftFrom; //票据来源
    }


    if(this.jsonlist!=null&&this.jsonlist!=''&&this.jsonlist!='undefined'){
      for (let i = 1; i < this.maxI; i++) {
        let text:any = $("#n"+i).val();
        let Entry:any = $("#n"+i).attr("data-name");
        this.jsonlist[Entry]={};
        this.jsonlist[Entry].value=text;
      }
      data.json=JSON.stringify(this.jsonlist);
    }


    this.apiSev.api("newpost", "draftrecord/save", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.apiSev.itip("保存成功");
        this.navCtrl.pop();
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }


  //保存
  draftrecordsaveB() {
    if (this.manage.allmoney == null || this.manage.allmoney == '' || this.manage.allmoney == 'undefined') {
      this.apiSev.itip("请输入票面金额");
      return;
    }

    let data: any = {
      id:this.item.id,
      memberId: this.memberId, //用户主键
      //recordType:this.manage.recordType, //类型（HOLD持有、OUT已出票、IN待入账）
      draftType:this.discounttype, //票据类型（YZ银纸、YD银电、SZ商纸、SD商电）
      //bank:this.manage.bank, //承兑行全称
      //type2:'', //承兑行类型（1国股、2城商（小商）、3外资、4农商、5农合、6农信、7村镇、8城商（大商））
      //draftNo:this.manage.draftNo, //票号
      allmoney:(this.manage.allmoney*10000), //票面金额（元）
      //txje:this.manage.txje, //贴现金额（元）
      //inData:this.manage.inData, //入账日期
      //tiexianDate:this.manage.tiexianDate, //贴现日期
      //expiryDate:this.manage.expiryDate, //到期日期
      //noticeDate:this.manage.noticeDate, //提醒日期（默认设置为提醒日期前十天）仅限持有
      //draftFrom:this.manage.draftFrom, //票据来源
      //draftTo:this.manage.draftTo //票据去处
    };
    if(this.pet=='manage1'){
      if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
        this.apiSev.itip("请输入承兑行全称");
        return;
      }
      if (this.manage.txje == null || this.manage.txje == '' || this.manage.txje == 'undefined') {
        this.apiSev.itip("请输入实收票款");
        return;
      }
      data.recordType='OUT';
      data.tiexianDate=new Date(this.manage.tiexianDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.tiexianDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.tiexianDate).getDate(); //贴现日期
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      data.txje=(this.manage.txje*10000); //贴现金额（元）
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
    }else if(this.pet=='manage2'){
      if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
        this.apiSev.itip("请输入承兑行全称");
        return;
      }
      data.recordType='OUT';
      data.expiryDate=new Date(this.manage.expiryDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.expiryDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.expiryDate).getDate(); //到期日期
      data.noticeDate=new Date(this.manage.noticeDate).getFullYear()+ "-" + ("0"+(new Date(this.manage.noticeDate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.noticeDate).getDate();
      data.bank=this.manage.bank; //承兑行全称
      data.draftNo=this.manage.draftNo; //票号
      data.draftFrom=this.manage.draftFrom; //票据来源
      data.draftTo=this.manage.draftTo; //票据去处
    }else {
      data.recordType='IN';
      data.inData=new Date(this.manage.inData).getFullYear()+ "-" + ("0"+(new Date(this.manage.inData).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.inData).getDate();
      data.draftFrom=this.manage.draftFrom; //票据来源
    }

    if(this.jsonlist!=null&&this.jsonlist!=''&&this.jsonlist!='undefined'){
      for (let i = 1; i < this.maxI; i++) {
        let text:any = $("#n"+i).val();
        let Entry:any = $("#n"+i).attr("data-name");
        this.jsonlist[Entry]={};
        this.jsonlist[Entry].value=text;
      }
      data.json=JSON.stringify(this.jsonlist);
    }


    this.apiSev.api("newpost", "draftrecord/save", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.apiSev.itip("票据出库保存成功");
        this.navCtrl.pop();
      }else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
    }, 500, data);
  }


  //前往利率测算
  llcsData(){
    this.navCtrl.push(CounterPage);
  }


  //分享弹出层
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.maskDiv = false;
    }
    //隐藏滚动条
    this.hiddenscroll();
    e.stopPropagation();
  }

  //弹出下拉框时，取消scroll
  hiddenscroll(){
    //获取当前组件的ID
    let aboutContent = document.querySelector("ion-content");
    //获取当前组件下的scroll-content元素
    let scroll:any = aboutContent.querySelector(".scroll-content");
    if(this.maskDiv){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }

  //关闭弹窗
  closeMask(){
    this.maskDiv = false;
  }


  //票据质押
  outchange(){
    //保存
      if (this.manage.allmoney == null || this.manage.allmoney == '' || this.manage.allmoney == 'undefined') {
        this.apiSev.itip("请输入票面金额");
        return;
      }

      let data: any = {
        id:this.item.id,
        memberId: this.memberId, //用户主键
        draftType:this.discounttype, //票据类型（YZ银纸、YD银电、SZ商纸、SD商电）
        allmoney:(this.manage.allmoney*10000), //票面金额（元）
      };

        if (this.manage.bank == null || this.manage.bank == '' || this.manage.bank == 'undefined') {
          this.apiSev.itip("请输入承兑行全称");
          return;
        }

        if(this.manage.pledgemoney>0){

        }else {
          this.apiSev.itip("请输入质押金额");
          return;
        }

        data.recordType='PLEDGE';
        data.bank=this.manage.bank; //承兑行全称
        data.draftNo=this.manage.draftNo; //票号
        data.draftFrom=this.manage.draftFrom; //票据来源
        data.draftTo=this.manage.draftTo; //票据去处
        data.pledgeMoney=this.manage.pledgemoney;            //质押金额
        data.pledgeOutDate=new Date(this.manage.pledgeoutdate).getFullYear()+ "-" + ("0"+(new Date(this.manage.pledgeoutdate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.pledgeoutdate).getDate();         //质押时间
        data.pledgeInDate=new Date(this.manage.pledgeindate).getFullYear()+ "-" + ("0"+(new Date(this.manage.pledgeindate).getMonth() + 1)).slice(-2) + "-" +new Date(this.manage.pledgeindate).getDate();          //赎回时间
        this.apiSev.newState=9;

    if(this.jsonlist!=null&&this.jsonlist!=''&&this.jsonlist!='undefined'){
      for (let i = 1; i < this.maxI; i++) {
        let text:any = $("#n"+i).val();
        let Entry:any = $("#n"+i).attr("data-name");
        this.jsonlist[Entry]={};
        this.jsonlist[Entry].value=text;
      }
      data.json=JSON.stringify(this.jsonlist);
    }


      this.apiSev.api("newpost", "draftrecord/save", (res) => {
        let data=res.data;
        if (data.response == 'success') {
          this.apiSev.itip("票据质押保存成功");
          this.navCtrl.pop();
        }else {
          this.apiSev.itip(data.msg);
        }
      }, (error) => {
      }, 500, data);
  }

}
