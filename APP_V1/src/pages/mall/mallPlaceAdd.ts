import { Component } from '@angular/core';
import { NavController, ViewController , NavParams ,AlertController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-mallPlaceAdd',
  templateUrl: 'mallPlaceAdd.html'
})
export class mallPlaceAddPage {
  public ischecked:boolean;
  public headTetile='新增地址';
  //省市区三级联动
  cityData: any[] = []; //城市数据
  cityName:string = '请选择'; //初始化城市名
  code: string; //城市编码

  public place={
    consignee: '',
    tel: '',
    address: '',
    detailAddress: '',
    state:'',

    address_index:'',
    addressId:'',
    memberId: '',//用户id
    orgId: '',//机构id
  }

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams,
    public alertCtrl: AlertController
  ) {
    this.place.addressId = this.params.get("UPDATEADDRESSID");
    this.storage.get('userInfo').then((data)=>{
      this.place.memberId=data.id;
      this.headIf();
    })
    this.storage.get('ORGID').then((data)=>{
      this.place.orgId=data;
    })
    this.place.state=this.params.get("state");

  }

  //区分编辑或新增
  headIf(){
    if (this.params.get("placeType")==0){
      this.headTetile='编辑地址';
      this.loadAddress();
    }else if (this.params.get("placeType")==1){
      this.headTetile='新增地址'
      this.loadAddress();
    };
  }

  loadAddress(){
    if(this.place.addressId != null && this.place.addressId != ""){
      let data:any={
        addressId:this.place.addressId
      }
      this.apiSev.api("post", "app/goodsaddress/get", (res) => {
        if (res.response == 'success') {
          let objs=res.data;
          this.cityName = objs.address;
          this.place.consignee=objs.consignee;
          this.place.tel=objs.tel;
          this.place.address=objs.address;
          this.place.detailAddress=objs.detailAddress;
          this.place.address_index = objs.address_index ;
        } else {
          this.apiSev.itip(data.msg);
        }
      },(error) => {
        this.apiSev.itip('操作失败！');
        return;
      }, 500,data);
    }else{
    }
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
    this.place.address = this.cityName.trim().split('-')[1];//传参place
  }

  /*保存*/
  save(e) {
    let name=this.place.consignee;
    if (name==null || name.trim()=="" || name=="undefined") {
      this.apiSev.itip("请输入您的姓名");
      return;
    }

    let p = this.apiSev.validateMobile(this.place.tel);//验证手机
    if(this.place.tel==''){
      this.apiSev.itip('手机号码不能为空');
      return false;
    }else if(this.place.tel.length!=11){
      this.apiSev.itip('请输入11位有效的手机号码！');
	  return false;
    }else if(!p){
      this.apiSev.itip('你输入的手机不符合格式');
      return false;
    }

    let address=this.place.address;
    if (address == null || address.trim()=="" || address=="undefined") {
      this.apiSev.itip("地址不能够为空");
      return;
    }
    let detailAddress=this.place.detailAddress;
    if (detailAddress == null || detailAddress.trim()=="" || detailAddress=="undefined") {
      this.apiSev.itip("请填写详细地址");
      return;
    }

    let data:any={
      memberId:this.place.memberId,
      consignee:this.place.consignee,
      tel:this.place.tel,
      address:this.place.address,
      detailAddress:this.place.detailAddress,
      address_index:'',
      state:this.place.state,
    }
    if(this.place.addressId!=null){
      data.id=this.place.addressId;
    }
    this.apiSev.api("post", "app/goodsaddress/save", (res) => {
      if (res.response == 'success') {
        this.apiSev.itip('操作成功！');
		this.navCtrl.pop();
      } else {
        this.apiSev.itip(data.msg);
      }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
    e.preventDefault();
  }

}


