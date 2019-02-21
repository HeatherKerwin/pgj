import {Component} from '@angular/core';
import {NavController, ViewController, NavParams} from 'ionic-angular';
import {Storage} from '@ionic/storage';
import {ToastController} from 'ionic-angular';
import {apiService} from "../../api.service";
import {DiscountPlaceListPage} from '../../pages/discount/discountPlaceList';//银票贴现列表
import {DiscountSPNextPage} from '../../pages/discount/discountSPNext';//银票贴现下一步
import { Camera, CameraOptions } from '@ionic-native/camera';


@Component({
  selector: 'page-discountSP',
  templateUrl: 'discountSP.html'
})
export class DiscountSPPage {
  public note='提示：每日17：30至次日08：30发布的信息将在次日09：00后受理';//顶部提示

  public maskDiv=false; //弹出层
  public Authentication=false; //开户
  public active:any={
  };
  public cib:any={};
  public memberId:any;

  public isMask=false;//不显示上传图片
  public isUpload=true;//不显示上传图片
  public isPicpath=false;//不显示上传图片
  public uploadFlag:boolean=false;//是否上传图片成功
  public CameraRoll:boolean=false; //相机
  public Distinguish:boolean=false; //弹窗

  public discount:any = {
    type1: 2,//票据属性
    limit: 1,//承兑期限
    bankname:'',//承兑企业
    allmoney: null,//金额
    type2: 1,//承兑行类型
    printtime: new Date().toISOString(),//开票日期
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date(new Date()).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString(),//到期日期
    min: new Date().toISOString(),//到期日最小值
    draftNo:null,
    cnapsCode:'',  //承兑行行号

    //贴现地址
    placeNot: true,//无地址
    placeShow: false,//有地址
    membername: '',//姓名
    membersex: '',//性别
    membermobile: '',//手机号
    address: '',//
    place: '',//详细地址
    cityId: '',//


    addressId: '',//地址id
    memberId: '',//用户id
    orgId: '',//机构id
  };

  public SPDATA:any;//贴现数据
  public orderinventory:any={}; //票据清单

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public camera: Camera,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.orderinventory = this.params.get("ORDERINVENTORY");
    this.storage.get('userInfo').then((data) => {this.discount.memberId=data.id});
    this.storage.get('ORGID').then((data)=>{this.discount.orgId=data;});

    let type1 = this.params.get("TEMP_TYPE1");//票据属性
    if(type1==1){
      if(this.orderinventory!=null&&this.orderinventory!=''&&this.orderinventory!='undefined'){
        if(this.orderinventory.allmoney!=null&&this.orderinventory.allmoney!=''&&this.orderinventory.allmoney!='undefined'){this.discount.allmoney=this.orderinventory.allmoney*1;}
        if(this.orderinventory.printtime!=null&&this.orderinventory.printtime!=''&&this.orderinventory.printtime!='undefined'){this.discount.printtime=this.orderinventory.printtime;}
        if(this.orderinventory.endtime!=null&&this.orderinventory.endtime!=''&&this.orderinventory.endtime!='undefined'){this.discount.endtime=this.orderinventory.endtime;}
        if(this.orderinventory.bank!=null&&this.orderinventory.bank!=''&&this.orderinventory.bank!='undefined'){this.discount.bankname=this.orderinventory.bank;}

      }
    }
    if(type1!=null&&type1!=''&&type1!='undefined'){this.discount.type1=type1;this.radioBtn(this.discount.type1)}
    this.topBar();//初始化提示语


  }
  //每次进入页面都加载
  ionViewDidEnter(){
    this.initFromCalOrInq();//获取默认贴现地址
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    if(this.discount.membersexs == "1"){
      this.discount.membersex = "先生";
    }else if(this.discount.membersexs == "2"){
      this.discount.membersex = "女士";
    }
  }

  // 初始化提醒
  topBar() {
    let data:any={};
      this.apiSev.api("post", "app/topbar/", (res) => {
        if(res.content!=null)this.note=res.content;
      },(error) => {}, 500,data);
  }

  //加载数据来源
  initFromCalOrInq(){
    let allmoney = this.params.get("TEMP_ALLMONEY");//总金额
    let no = this.params.get("TEMP_NO");//票号
    let bankNo = this.params.get("TEMP_BANKNO");//联行号行号
    let bankName = this.params.get("TEMP_BANKNAME");//联行号全称
    let start = this.params.get("TEMP_START");//开票日期
    let end = this.params.get("TEMP_END");//到期日期
    if(allmoney!=null&&allmoney!=''&&allmoney!='undefined'){this.discount.allmoney=allmoney*1;}
    if(no!=null&&no!=''&&no!='undefined'){this.discount.draftNo=no;}
    if(bankNo!=null&&bankNo!=''&&bankNo!='undefined'){this.discount.cnapsCode=bankNo;}
    if(bankName!=null&&bankName!=''&&bankName!='undefined'){this.discount.bankname=bankName;}
    if(start!=null&&start!=''&&start!='undefined')this.discount.printtime=start;
    if(end!=null&&end!=''&&end!='undefined')this.discount.endtime=end;
    let front = this.params.get("TEMP_FRONT");//上传图片
    let picpath = this.params.get("TEMP_PICPATH");//展示图片

    if(front!=null && front!=""){
      this.discount.front=front;
    }

    if(picpath!=null && picpath!=""){
      this.discount.picpath=picpath;
    }
    let SPDATA=this.params.get("SPDATA");
    let parmData=this.apiSev.parmData;
    if (parmData!=null){
      SPDATA = this.apiSev.parmData.SPDATA;
      this.apiSev.parmData=null;
    }
    this.myExcel();//计算计息天数等
    if(SPDATA!=null){
      let addressId=this.params.get("ADDRESSID");
      if (parmData!=null){
        addressId = parmData.ADDRESSID;
      }
      this.discount=SPDATA;
      if(SPDATA.type1==1){
        this.discount.addressId=addressId;
        if (this.discount.addressId != null) {//是否有地址?---有
          this.discount.placeNot = false;
          this.discount.placeShow = true;
          this.storage.get('userInfo').then((data) => {
            this.memberId = data.id;
            this.loadAddress();//显示默认地址
          });
        }
      }
    }
  }

  //获取默认贴现地址
  loadAddress() {
    let data:any={
      memberId : this.discount.memberId,
      belong : 0
    }
    if (this.discount.orgId!=null&&this.discount.orgId!= ""&&this.discount.orgId!=undefined){
      data.orgId=this.discount.orgId;
    }
    if (this.discount.addressId != null&&this.discount.addressId !=''){
      data.addressId=this.discount.addressId;
    }
    this.apiSev.api("post", "app/address/get/default", (res) => {
      if (res.response == 'success') {
        let result = res.data;
        if(result!=null){
          this.discount.membername=result.name;
          this.discount.membermobile=result.mobile;
          let address = result.address;
          if(result.other!=null){
            address += " "+result.other;
          }
          this.discount.address=address;
          this.discount.place=result.place;
          this.discount.cityId=result.cityId;

          let sex = result.sex;
          if(sex==1){
            this.discount.membersex = "先生";
          }else if(sex==2){
            this.discount.membersex = "女士";
          }
          this.discount.placeNot = false;
          this.discount.placeShow = true;
        }

      }
    },(error) => {}, 500,data);

  }

  //选择地址
  chosePlace(e){
    this.navCtrl.push(DiscountPlaceListPage,{
      ADDRESSCALLBACKURL:'DiscountSPPage',
      SPDATA:this.discount
    });
    e.preventDefault();
  }


  //选择票据属性
  radioBtn(data) {
    if (data == this.discount.type1) {
      if (this.discount.type1 == 2) {
        this.changeStart();
        this.myExcel();
      } else {
        this.changeStart();
        this.myExcel();
        this.storage.get('userInfo').then((data) => {
          this.memberId = data.id;
          this.loadAddress();
        });
      }
    }
    if (data == this.discount.limit) {
      if (this.discount.limit == 0) {
        this.changeStart();
      } else {
        this.changeStart();
      }
    }
  }

  //选择贴现时间
  changeStart(){
/*    if (this.discount.limit=='0'){
      //this.discount.endtime = new Date(new Date(this.discount.begintime).getTime() + 180 * 24 * 60 * 60 * 1000).toISOString();
      //this.discount.min = new Date(this.discount.begintime).toISOString();
      this.myExcel();
    }else if(this.discount.limit=='1') {
      //this.discount.endtime = new Date(new Date(this.discount.begintime).getTime() + 360 * 24 * 60 * 60 * 1000).toISOString();
      //this.discount.min = new Date(this.discount.begintime).toISOString();
    };*/
    this.myExcel();
  }

  //计算计息天数、贴现利息
  myExcel() {
    if (this.discount.begintime == null || this.discount.begintime == "") {
      return;
    } else if (this.discount.begintime == null || this.discount.begintime== "") {
      return;
    } else if (this.discount.allmoney == null || this.discount.allmoney == "") {
      return;
    }
    if (this.discount.type1==1) {
      if (Number(this.discount.allmoney) > 300) {
        this.apiSev.itip("纸票票面金额不能高于300万");
        return;
      }
    }
    let data:any={
      type1: this.discount.type1,
      type2: this.discount.type2,
      start: new Date(this.discount.begintime).getFullYear()+ "-" + ("0"+(new Date(this.discount.begintime).getMonth() + 1)).slice(-2) + "-" +new Date(this.discount.begintime).getDate(),
      end: new Date(this.discount.endtime).getFullYear()+ "-" + ("0"+(new Date(this.discount.endtime).getMonth() + 1)).slice(-2) + "-" +new Date(this.discount.endtime).getDate(),
      allmoney: this.discount.allmoney,
    }
    if (this.discount.type1 == 2){
      data.limit=this.discount.limit;
    }
    this.apiSev.api("post", "app/excel/price", (res) => {
      let temp = this.params.get("TEMP_TXLX");
      if(temp!=null){
        this.discount.price = temp;
      }else{
        if (res.txlx!=undefined){
          this.discount.price = res.txlx;
        }else {
          this.discount.price = '--';
        }
      }
      this.discount.day = res.jxts;
    },(error) => {
      this.apiSev.itip('操作失败！');
    }, 500,data);
  }


  //下一步
  next() {
    if (this.discount.type1==1) {
      if (Number(this.discount.allmoney) > 300) {
        this.apiSev.itip("纸票票面金额不能高于300万");
        return;
      }
    }
    if(this.discount.type1==1){
      if(this.discount.address==null || this.discount.address==""){
        this.apiSev.itip("出票地址不完整");
        return;
      }
    }else if (this.discount.allmoney == null || this.discount.allmoney == "") {
      this.apiSev.itip('请输入票面金额！');
      return;
    } else if (this.discount.bankname == null || this.discount.bankname == "") {
      this.apiSev.itip('请输入承兑企业全称！');
      return;
    }else if(this.discount.begintime==null || this.discount.begintime==""){
      this.apiSev.itip("请输入下单日期");
      return;
    }else if(this.discount.endtime==null || this.discount.endtime ==""){
      this.apiSev.itip("请输入到期日期");
      return;
    }else if(this.discount.type1 = 2){
      if(this.checkdraftNo()){
        return;
      }
      if(this.discount.cnapsCode.length!=12) {
        this.apiSev.itip('请输入12位承兑行行号！');
        return;
      }
    }
    if(this.discount.membersex == "先生"){
      this.discount.membersexs = "1";
    }else if(this.discount.membersex == "女士"){
      this.discount.membersexs = "2";
    }
    this.navCtrl.push(DiscountSPNextPage, {
      TEMP_YPdiscount: this.discount
    });
  }

  checkdraftNo(){
    if(this.discount.draftNo == null){
      this.apiSev.itip('请输入票号');
      return true;
    }else {
      var prefix = (this.discount.draftNo).substring(0, 1);//第一位表示票据种类，1代表电子银行承兑汇票，2代表电子商业承兑汇票;
      var date = (this.discount.draftNo).substring(13, 21);
      if(this.discount.draftNo.length != 30){
        this.apiSev.itip('票号输入有误');
        return true;
      }else if(!("1"==prefix || "2"==prefix)){
        this.apiSev.itip('票号输入有误');
        return true;
      }else{
        var str = date.substring(0,4)+'/'+date.substring(4,6)+'/'+date.substring(6,8);
        var isOk = new Date(str);
        if(isOk.toString() == 'Invalid Date'){
          this.apiSev.itip('票号输入有误');
          return true;
        }
      }
    }
  }

  //上传图片
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
  //调取相机上传
  cameraPic(){
    const options: CameraOptions = {
      quality: 100,
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: 1,
      targetWidth: 1280,
      targetHeight: 1280,
      saveToPhotoAlbum: false,
      correctOrientation:true
    }
    this.findPic(options);
    this.isMask=true;
    this.CameraRoll=false;
    this.Distinguish=true;
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
    this.isMask=true;
    this.CameraRoll=false;
    this.Distinguish=true;
  }


  //上传图弹出层
  uploadBtn(){
    //this.navCtrl.push(ScrawlPanelPage);
    this.isMask = true;
    this.CameraRoll=true;
    this.Distinguish = false;
  }
  closeMask(){
    this.isMask = false;
    this.Distinguish = false;
    this.CameraRoll=false;
  }
  //遮挡层点击事件
  backdropclick(e){
    //判断点击的是否为遮罩层，是的话隐藏遮罩层
    if(e.srcElement.className == 'mask'){
      this.isMask = false;
      this.Distinguish = false;
      this.CameraRoll=false;
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

}
