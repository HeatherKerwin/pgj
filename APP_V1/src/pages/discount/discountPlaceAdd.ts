import { Component } from '@angular/core';
import { NavController, ViewController , NavParams ,AlertController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-discountPlaceAdd',
  templateUrl: 'discountPlaceAdd.html'
})
export class DiscountPlaceAddPage {
  public headTetile='新增地址';
  //省市区三级联动
  cityData: any[] = []; //城市数据
  cityName:string = '请选择'; //初始化城市名
  code: string; //城市编码

  public place={
    sex: 2,
    name: '',
    mobile: '',
    address: '',
    other: '',
    cityId: '',
    place:'',

    addressId:'',
    memberId: '',//用户id
    orgId: '',//机构id
  }
  public URL='';
  public LOADDATA:any;
  public state:any;

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public alertCtrl: AlertController
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.place.memberId=data.id;
      this.headIf();
    })
    this.storage.get('ORGID').then((data)=>{
      this.place.orgId=data;
    })
    this.URL = this.params.get("ADDRESSCALLBACKURL");
    this.LOADDATA = this.params.get("LOADDATA");
  }

  //区分编辑或新增
  headIf(){
    if (this.params.get("placeType")==0){
      this.headTetile='编辑地址';
      this.state=this.params.get("state");
      this.initialData();
    }else if (this.params.get("placeType")==1){
      this.headTetile='新增地址'
    };
  }

  initialData(){
    let addressId = this.params.get("ADDRESSID");
    if(addressId!=null){
      this.place.addressId=addressId;
      this.loadData();
    }else{
      let name = this.params.get("login_username");
      let mobile = this.params.get("login_mobile");
      if(name != undefined)this.place.name=name;
      if(mobile != undefined)this.place.mobile=mobile;
      this.save();
    }

  }


  loadData(){
    let data:any={
      addressId: this.place.addressId,
      memberId: this.place.memberId,
      belong: 0
    }
    if (this.place.orgId!=null&&this.place.orgId!= ""&&this.place.orgId!=undefined){
      data.orgId=this.place.orgId
    }
    this.apiSev.api("post", "app/address/get", (res) => {
      this.place.sex=res.data.sex;
      this.place.name=res.data.name;
      this.place.mobile=res.data.mobile;
      this.place.address=res.data.address;
      this.place.other=res.data.other;
      this.place.cityId=res.data.cityId;
      this.place.place=res.data.place;
      this.cityName=res.data.place;
    },(error) => {}, 500,data);
  }

  //选择性别
  radioBtn(data) {

  }

  //选择交易城市
  ionViewDidLoad() {
    //获取城市数据
    this.apiSev.geCityData().subscribe(res => {
      this.cityData = res;
    })
  }
  //城市选择器被改变时触发的事件
  cityChange(event) {
    this.place.place = this.cityName.trim().split('-')[1];//传参place
  }

  /*保存*/
  save() {
    if (this.place.name==null || this.place.name.trim()=="" || this.place.name=="undefined") {
      this.apiSev.itip("请输入您的姓名");
      return;
    }
    if (this.place.mobile==null || this.place.mobile.trim()=="" || !this.apiSev.checkMobile(this.place.mobile)) {
      this.apiSev.itip("请输入正确的联系电话");
      return;
    }
    if (this.place.place==null || this.place.place.trim() == ""){
      this.apiSev.itip("请选择一个交易城市");
      return;
    }

    let saveData:any={
      state:this.state,
      memberId: this.place.memberId,
      name: this.place.name,
      sex: this.place.sex,
      mobile: this.place.mobile,
      address: this.place.address,
      other: this.place.other,
      cityId: this.place.cityId,
      place: this.place.place,
      belong: 0
    }
    if (this.place.orgId!=null&&this.place.orgId!= ""&&this.place.orgId!=undefined){
      saveData.orgId=this.place.orgId;
    }
    if(this.place.addressId!=null){
      saveData.id=this.place.addressId;
    }

    this.apiSev.api("post", "app/address/save", (res) => {
      if (res.response == 'success') {
        this.showConfirm();
      }else{

      }
    },(error) => {}, 500,saveData);
  }

  showConfirm() {
    this.apiSev.itip("操作成功");
    let DiscountPlaceListPage={
      ADDRESSCALLBACKURL:this.URL,
      LOADDATA:this.LOADDATA
    }
    this.apiSev.parmData = DiscountPlaceListPage;
    this.navCtrl.pop();
  }

}


