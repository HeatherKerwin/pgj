import { Component } from '@angular/core';
import { NavController, ViewController , NavParams , AlertController} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Camera, CameraOptions } from '@ionic-native/camera';
import { DiscountOrderPage } from '../../pages/discount/discountOrder';

@Component({
  selector: 'page-discountSPNext',
  templateUrl: 'discountSPNext.html'
})
export class DiscountSPNextPage {
  public SPDATA:any={
    type1:null
  };//获取上一步数据

  public OnePrice:any={
    price:0,  //一口价总扣息
    priceSW:0,  //一价每十万
    yearRate:0.00, //年利率
    allmoney:'' //票款金额：万元
  };  //一口价

  public discount:any={
    type1:null,//票据属性
    isQixian:true,//承兑期限是否显示
    limit:null,//承兑期限
    allmoney:null,//金额
    type2:null,//承兑行类型
    printtime:'', //开票日期
    begintime:'',//下单日期
    endtime:'',//到期日期
    min:'',//到期日最小值
    day:'',//计息天数,
    backEndorse:'',  //是否回头票
    price:null,//参考贴现利息
    memberother:'',//备注
    needTodoor:1,//是否上门
    endorse:0,//背书手数
    hasTicket:0,//票是否开出
    picpath:'',//展示图片路径
    front:'',//上传图片路径
    OnePrice:'F',  //一口价

    memberId: '',//用户id
    orgId: '',//机构id
  };

  public isMask=false;//不显示上传图片
  public isUpload=true;//不显示上传图片
  public isPicpath=false;//不显示上传图片
  public uploadFlag:boolean=false;//是否上传图片成功
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗
  public DistinguishB:boolean=false;

  public dcrdId:any;//下单主键
  public orderinventory:any={}; //票据清单

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public camera: Camera,
    public alertCtrl: AlertController
  ) {
    this.orderinventory = this.params.get("ORDERINVENTORY");
    this.SPDATA=this.params.get("TEMP_YPdiscount");//上一步操作数据
    if(this.SPDATA.picpath!=null&&this.SPDATA.picpath!=''){this.discount.picpath = this.SPDATA.picpath}
    if(this.SPDATA.front!=null&&this.SPDATA.front!=''){this.discount.front = this.SPDATA.front}
  }

  //背书加减
  incr(){
    this.discount.endorse += 1;
  }
  decr(){
    //只有大于一的时候才减
    if(this.discount.endorse>0){
      this.discount.endorse -= 1;
    }else {
      this.apiSev.itip("背书手数最少为0");
    }
  }

  // 选择按钮
  radioBtn(data) {
    if (data == this.discount.hasTicket) {//票已开出
      if (this.discount.hasTicket == 0) {
        this.isUpload=true;
        this.isPicpath=true;
      } else {
        this.isUpload=false;
        this.isPicpath=false;
      }
    }

  }

  //确认订单
  save(){
    if(this.discount.backEndorse==''||this.discount.backEndorse==''||this.discount.backEndorse==null){
      this.apiSev.itip("请选择是否回头");
      return
    }

    if(this.SPDATA.type1==2&&this.discount.OnePrice=='T'){
      if(this.OnePrice.allmoney!=null&&this.OnePrice.allmoney!=''&&this.OnePrice.allmoney!='undefined'){
        if(Number(this.OnePrice.allmoney)>Number(this.SPDATA.allmoney)){
          this.apiSev.itip("您输入的金额有误,请重新输入");
          return;
        }
        this.isMask=true;
        this.DistinguishB=true;
        return;
      }else {
        this.apiSev.itip("请输入您的买断价格");
        return;
      }
    }


    this.discount.printtime = new Date(this.SPDATA.printtime).getFullYear()+ "-" + ("0"+(new Date(this.SPDATA.printtime).getMonth() + 1)).slice(-2) + "-" +new Date(this.SPDATA.printtime).getDate();
    this.discount.begintime= new Date(this.SPDATA.begintime).getFullYear()+ "-" + ("0"+(new Date(this.SPDATA.begintime).getMonth() + 1)).slice(-2) + "-" +new Date(this.SPDATA.begintime).getDate();
    this.discount.endtime = new Date(this.SPDATA.endtime).getFullYear()+ "-" + ("0"+(new Date(this.SPDATA.endtime).getMonth() + 1)).slice(-2) + "-" +new Date(this.SPDATA.endtime).getDate();
    let data:any={
      jxts:this.SPDATA.day,
      allmoney: this.SPDATA.allmoney,
      print:this.discount.printtime,
      start: this.discount.begintime,
      end: this.discount.endtime,
      memberId: this.SPDATA.memberId,
      type1: this.SPDATA.type1,
      type2: this.SPDATA.type2,
      remarks: this.discount.memberother,
      hasTicket: this.discount.hasTicket,
      endorse : this.discount.endorse,//背书
      bank:this.SPDATA.bankname,
      backEndorse:this.discount.backEndorse//是否回头票
    }
    //票据清单来源
    if(this.orderinventory!=null&&this.orderinventory!=''&&this.orderinventory!='undefined'){
      if(this.orderinventory.id!=null&&this.orderinventory.id!=''&&this.orderinventory.id!='undefined'){
        data.inventoryId=this.orderinventory.id
      }
    }
    if(this.SPDATA.type1==1){//纸票
      data.needTodoor = this.discount.needTodoor;//是否上门
      data.memberName=this.SPDATA.membername;//纸票有地址
      data.memberSex=this.SPDATA.membersexs;
      data.memberMobile=this.SPDATA.membermobile;
      data.address=this.SPDATA.address;
      data.place=this.SPDATA.place;
      data.cityId=this.SPDATA.cityId;
    }
    if (this.SPDATA.type1==2){//电票
      data.acceptTime = this.SPDATA.limit;//票据期限
      data.draftNo = this.SPDATA.draftNo;
      data.cnapsCode = this.SPDATA.cnapsCode; //承兑行行号
    }
    if (this.discount.hasTicket==0){//票已开出（才上传图片）
      if (this.discount.front!=null && this.discount.front.trim()!="" && this.discount.front.length>0){//是否有图
        data.picpath = this.discount.front;//获取的图片
      }else{
        this.apiSev.itip("请上传票据图片！");
        return;
      }
    }
    if (this.discount.orgId!=null&&this.discount.orgId!= ""&&this.discount.orgId!=undefined){
      data.orgId = this.discount.orgId;
    }
    this.apiSev.api("newpost", "/discountrecordsp/tiexian", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.dcrdId = data.data.id;
        let type1 = data.data.type1;
        this.navCtrl.push(DiscountOrderPage,{
          DISCOUNTRECORDID:this.dcrdId,
          order_type:'sp',//商票
          type1:type1   //票据属性
        });
      }else{
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);

  }

  Newsave(){
    this.discount.printtime = new Date(this.SPDATA.printtime).getFullYear()+ "-" + ("0"+(new Date(this.SPDATA.printtime).getMonth() + 1)).slice(-2) + "-" +new Date(this.SPDATA.printtime).getDate();
    this.discount.begintime= new Date(this.SPDATA.begintime).getFullYear()+ "-" + ("0"+(new Date(this.SPDATA.begintime).getMonth() + 1)).slice(-2) + "-" +new Date(this.SPDATA.begintime).getDate();
    this.discount.endtime = new Date(this.SPDATA.endtime).getFullYear()+ "-" + ("0"+(new Date(this.SPDATA.endtime).getMonth() + 1)).slice(-2) + "-" +new Date(this.SPDATA.endtime).getDate();
    let data:any={
      jxts:this.SPDATA.day,
      allmoney: this.SPDATA.allmoney,
      print:this.discount.printtime,
      start: this.discount.begintime,
      end: this.discount.endtime,
      memberId: this.SPDATA.memberId,
      type1: this.SPDATA.type1,
      type2: this.SPDATA.type2,
      remarks: this.discount.memberother,
      hasTicket: this.discount.hasTicket,
      endorse : this.discount.endorse,//背书
      bank:this.SPDATA.bankname,
      backEndorse:this.discount.backEndorse//是否回头票
    }
    //票据清单来源
    if(this.orderinventory!=null&&this.orderinventory!=''&&this.orderinventory!='undefined'){
      if(this.orderinventory.id!=null&&this.orderinventory.id!=''&&this.orderinventory.id!='undefined'){
        data.inventoryId=this.orderinventory.id
      }
    }
    if(this.SPDATA.type1==1){//纸票
      data.needTodoor = this.discount.needTodoor;//是否上门
      data.memberName=this.SPDATA.membername;//纸票有地址
      data.memberSex=this.SPDATA.membersexs;
      data.memberMobile=this.SPDATA.membermobile;
      data.address=this.SPDATA.address;
      data.place=this.SPDATA.place;
      data.cityId=this.SPDATA.cityId;
    }
    if (this.SPDATA.type1==2){//电票
      data.acceptTime = this.SPDATA.limit;//票据期限
      data.draftNo = this.SPDATA.draftNo;
      data.cnapsCode = this.SPDATA.cnapsCode; //承兑行行号
      data.buyoutPrice = this.OnePrice.allmoney*10000
    }
    if (this.discount.hasTicket==0){//票已开出（才上传图片）
      if (this.discount.front!=null && this.discount.front.trim()!="" && this.discount.front.length>0){//是否有图
        data.picpath = this.discount.front;//获取的图片
      }else{
        this.apiSev.itip("请上传票据图片！");
        return;
      }
    }
    if (this.discount.orgId!=null&&this.discount.orgId!= ""&&this.discount.orgId!=undefined){
      data.orgId = this.discount.orgId;
    }
    this.apiSev.api("newpost", "/discountrecordsp/tiexian", (res) => {
      let data=res.data;
      if (data.response == 'success') {
        this.dcrdId = data.data.id;
        let type1 = data.data.type1;
        this.navCtrl.push(DiscountOrderPage,{
          DISCOUNTRECORDID:this.dcrdId,
          order_type:'sp',//商票
          type1:type1,   //票据属性
          OnePrice:(this.OnePrice.allmoney*10000) //是否一口价
        });
      }else{
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }

//点击相机上传
  findPic (options) {
    this.camera.getPicture(options).then((imageData) => {
      let url= imageData;
      let data= {
        base64Image:url
      }
      this.apiSev.api("newpost", "upload/image", (res) => {
        let data = res.data;
        if (data.response == 'success') {
          this.discount.picpath= this.apiSev.getOrderImgUrl+data.data.base64Image; //展示的图片
          this.discount.front=data.data.base64Image;
          this.uploadFlag = true;
          this.isUpload = false;
          this.isPicpath = true;
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
      }, 6100,data);

    }, (err) => {
    });
  }

  //点取相册上传
  albumPic(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 0,
      targetWidth: 1280,
      targetHeight: 1280
    }
    this.findPic(options);
    if(this.SPDATA.type1==1){
      this.closeMask();
    }else if(this.SPDATA.type1==2){
      this.isMask=true;
      this.CameraRoll=false;
      this.Distinguish=true;
    }
  }


  //上传图弹出层
  uploadBtn(){
    //this.navCtrl.push(ScrawlPanelPage);
    this.isMask = true;
    this.CameraRoll=true;
    this.Distinguish = false;
    this.DistinguishB = false;
  }
  closeMask(){
    this.isMask = false;
    this.Distinguish = false;
    this.CameraRoll=false;
    this.DistinguishB=false;
  }
  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.Distinguish = false;
      this.CameraRoll=false;
      this.DistinguishB=false;
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
    if(this.isMask){
      scroll.style.overflow='hidden';
    }else {
      scroll.style.overflow='';
    }
  }

  ToAccountPage(){
    this.isMask=true;
    this.Distinguish=true;
  }

  //一口价
  OnePrices() {
    this.OnePrice.price = ((this.SPDATA.allmoney * 1 - this.OnePrice.allmoney * 1) * 10000).toFixed(2);   //总扣息
    //实际支付总金额调用
    //传值贴现票款
    let txje = this.OnePrice.allmoney*10000;
    let money = this.SPDATA.allmoney*10000;
    let data: any = {
      money:money,
      txje:txje,
      jxts:this.SPDATA.day
    };
    this.apiSev.api("newpost", "dispatch/fee", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.OnePrice.priceSW=data.data.price2;
        this.OnePrice.yearRate=data.data.price;
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

}
