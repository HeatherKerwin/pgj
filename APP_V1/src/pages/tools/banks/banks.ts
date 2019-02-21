import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import { BankPage } from "./banksoput"

@Component({
  selector: 'banks-tools',
  templateUrl: 'banks.html'
})
export class Bankline {


  public cityList = [];
  public search1:any = {};

  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
    this.cityList = ["请选择城市"];
    this.selectcity = "请选择城市";

  }
  banksPage() {
    if (this.search1.keyword=="" || this.search1.keyword=="undefined" || this.search1.keyword==null){
      this.apiSev.itip("请完善查询条件");
      return ;
    }

    this.navCtrl.push(BankPage, {
      province: this.province,
      city: this.selectcity,
      selectbank: this.selectbank,
      keyword: this.search1.keyword,
    });
  }

  selectbank: string = "";
  province: string = "";
  selectcity: string = "请选择城市"


  chageProv(){
    this.bindCity(this.province)
  }

  bindCity(Prov){
    if (Prov==''){
      this.cityList = ["请选择城市"];
      this.selectcity = "请选择城市";
    }
    if (Prov=='北京'){
      this.cityList = ["北京市"];
      this.selectcity = "北京市";
    }
    if (Prov=='天津'){
      this.cityList = ["天津市"];
      this.selectcity = "天津市";
    }
    if (Prov=='上海'){
      this.cityList = ["上海市"];
      this.selectcity = "上海市";
    }
    if (Prov=='重庆'){
      this.cityList = ["重庆市"];
      this.selectcity = "重庆市";
    }
    if (Prov=='河北省'){
      this.cityList = ["石家庄市","张家口市","承德市","秦皇岛市","唐山市","廊坊市","保定市","衡水市","沧州市","邢台市","邯郸市"];
      this.selectcity = "石家庄市";
    }
    if (Prov=='山西省'){
      this.cityList = ["太原市","朔州市","大同市","阳泉市","长治市","晋城市","忻州市","晋中市","临汾市","吕梁市","运城市"];
      this.selectcity = "太原市";
    }
    if (Prov=='内蒙古'){
      this.cityList = ["呼和浩特市","包头市","乌海市","赤峰市","通辽市","呼伦贝尔市","鄂尔多斯市","乌兰察布市","巴彦淖尔市","兴安盟","锡林郭勒盟","阿拉善盟"];
      this.selectcity = "呼和浩特市";
    }
    if (Prov=='辽宁省'){
      this.cityList = ["沈阳市","朝阳市","阜新市","铁岭市","抚顺市","本溪市","辽阳市","鞍山市","丹东市","大连市","营口市","盘锦市","锦州市","葫芦岛市"];
      this.selectcity = "沈阳市";
    }
    if (Prov=='吉林省'){
      this.cityList = ["长春市","白城市","松原市","吉林市","四平市","辽源市","通化市","白山市","延边州"];
      this.selectcity = "长春市";
    }
    if (Prov=='黑龙江省'){
      this.cityList = ["哈尔滨市","齐齐哈尔市","七台河市","黑河市","大庆市","鹤岗市","伊春市","佳木斯市","双鸭山市","鸡西市","牡丹江市","绥化市","大兴安岭地区"];
      this.selectcity = "哈尔滨市";
    }
    if (Prov=='江苏省'){
      this.cityList = ["南京市","徐州市","连云港市","宿迁市","淮安市","盐城市","扬州市","泰州市","南通市","镇江市","常州市","无锡市","苏州市"];
      this.selectcity = "南京市";
    }
    if (Prov=='浙江省'){
      this.cityList = ["杭州市","湖州市","嘉兴市","舟山市","宁波市","绍兴市","衢州市","金华市","台州市","温州市","丽水市"];
      this.selectcity = "杭州市";
    }
    if (Prov=='安徽省'){
      this.cityList = ["合肥市","宿州市","淮北市","亳州市","阜阳市","蚌埠市","淮南市","滁州市","马鞍山市","芜湖市","铜陵市","安庆市","黄山市","六安市","巢湖市","池州市","宣城市"];
      this.selectcity = "合肥市";
    }
    if (Prov=='福建省'){
      this.cityList = ["福州市","南平市","莆田市","三明市","泉州市","厦门市","漳州市","龙岩市","宁德市"];
      this.selectcity = "福州市";
    }
    if (Prov=='江西省'){
      this.cityList = ["南昌市","九江市","景德镇市","鹰潭市","新余市","萍乡市","赣州市","上饶市","抚州市","宜春市","吉安市"];
      this.selectcity = "南昌市";
    }
    if (Prov=='山东省'){
      this.cityList = ["济南市","青岛市","聊城市","德州市","东营市","淄博市","潍坊市","烟台市","威海市","日照市","临沂市","枣庄市","济宁市","泰安市","莱芜市","滨州市","菏泽市"];
      this.selectcity = "济南市";
    }
    if (Prov=='河南省'){
      this.cityList = ["郑州市","开封市","三门峡市","洛阳市","焦作市","新乡市","鹤壁市","安阳市","濮阳市","商丘市","许昌市","漯河市","平顶山市","南阳市","信阳市","周口市","驻马店市","济源市"];
      this.selectcity = "郑州市";
    }
    if (Prov=='山西省'){
      this.cityList = ["太原市","朔州市","大同市","阳泉市","长治市","晋城市","忻州市","晋中市","临汾市","吕梁市","运城市"];
      this.selectcity = "太原市";
    }
    if (Prov=='湖北省'){
      this.cityList = ["武汉市","十堰市","襄樊市","荆门市","孝感市","黄冈市","鄂州市","黄石市","咸宁市","荆州市","宜昌市","随州市","省直辖县级行政单位","恩施州"];
      this.selectcity = "武汉市";
    }
    if (Prov=='湖南省'){
      this.cityList = ["长沙市","张家界市","常德市","益阳市","岳阳市","株洲市","湘潭市","衡阳市","郴州市","永州市","邵阳市","怀化市","娄底市","湘西州"];
      this.selectcity = "长沙市";
    }
    if (Prov=='广东省'){
      this.cityList = ["广州市","深圳市","清远市","韶关市","河源市","梅州市","潮州市","汕头市","揭阳市","汕尾市","惠州市","东莞市","珠海市","中山市","江门市","佛山市","肇庆市","云浮市","阳江市","茂名市","湛江市"];
      this.selectcity = "广州市";
    }
    if (Prov=='广西'){
      this.cityList = ["南宁市","桂林市","柳州市","梧州市","贵港市","玉林市","钦州市","北海市","防城港市","崇左市","百色市","河池市","来宾市","贺州市"];
      this.selectcity = "南宁市";
    }
    if (Prov=='海南省'){
      this.cityList = ["海口市","三亚市","省直辖行政单位"];
      this.selectcity = "海口市";
    }
    if (Prov=='四川省'){
      this.cityList = ["成都市","广元市","绵阳市","德阳市","南充市","广安市","遂宁市","内江市","乐山市","自贡市","泸州市","宜宾市","攀枝花市","巴中市","达州市","资阳市","眉山市","雅安市","阿坝州","甘孜州","凉山州"];
      this.selectcity = "成都市";
    }
    if (Prov=='贵州省'){
      this.cityList = ["贵阳市","六盘水市","遵义市","安顺市","毕节地区","铜仁地区","黔东南州","黔南州","黔西南州"];
      this.selectcity = "贵阳市";
    }
    if (Prov=='云南省'){
      this.cityList = ["昆明市","曲靖市","玉溪市","保山市","昭通市","丽江市","思茅市","临沧市","德宏州","怒江州","迪庆州","大理州","楚雄州","红河州","文山州","西双版纳州"];
      this.selectcity = "昆明市";
    }
    if (Prov=='西藏'){
      this.cityList = ["拉萨市","那曲地区","昌都地区","林芝地区","山南地区","日喀则地区","阿里地区"];
      this.selectcity = "拉萨市";
    }
    if (Prov=='陕西省'){
      this.cityList = ["西安市","延安市","铜川市","渭南市","咸阳市","宝鸡市","汉中市","榆林市","安康市","商洛市"];
      this.selectcity = "西安市";
    }
    if (Prov=='甘肃省'){
      this.cityList = ["兰州市","嘉峪关市","白银市","天水市","武威市","酒泉市","张掖市","庆阳市","平凉市","定西市","陇南市","临夏州","甘南州"];
      this.selectcity = "兰州市";
    }
    if (Prov=='青海省'){
      this.cityList = ["西宁市","海东地区","海北州","海南州","黄南州","果洛州","玉树州","海西州"];
      this.selectcity = "西宁市";
    }
    if (Prov=='宁夏'){
      this.cityList = ["银川市","石嘴山市","吴忠市","固原市","中卫市"];
      this.selectcity = "银川市";
    }
    if (Prov=='新疆'){
      this.cityList = ["乌鲁木齐市","克拉玛依市","自治区直辖县级行政单位","喀什地区","阿克苏地区","和田地区","吐鲁番地区","哈密地区","克孜勒苏柯州","博尔塔拉州","昌吉州","巴音郭楞州","伊犁州","塔城地区","阿勒泰地区"];
      this.selectcity = "乌鲁木齐市";
    }
    if (Prov=='香港'){
      this.cityList = ["香港特别行政区"];
      this.selectcity = "香港特别行政区";
    }
    if (Prov=='澳门'){
      this.cityList = ["澳门特别行政区"];
      this.selectcity = "澳门特别行政区";
    }
    if (Prov=='台湾省'){
      this.cityList = ["台北","高雄","台中","花莲","基隆","嘉义","金门","连江","苗栗","南投","澎湖","屏东","台东","台南","桃园","新竹","宜兰","云林","彰化"];
      this.selectcity = "台北";
    }
  }
}
