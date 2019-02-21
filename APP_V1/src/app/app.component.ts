import { Component } from '@angular/core';
import { Platform,App,ToastController  } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { Login } from '../pages/login/login';
import { Storage } from '@ionic/storage';
import { TabsPage } from '../pages/tabs/tabs';
import { TabsPageB } from '../pages/tabs/tabsB';
import { welcomePage } from '../pages/login/welcome';
import { JPush } from 'ionic3-jpush';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  public rootPage:any = Login;
  public info:any = {};
  public registerBackButton:boolean;
  public checkPage:boolean;
  private registerBackEvent: Function;

  constructor(public storage: Storage,public platform: Platform, statusBar: StatusBar,private app: App,splashScreen: SplashScreen ,public toastCtrl: ToastController ,  public jPush: JPush) {
    platform.ready().then(() => {
      this.registerBackEvent = this.platform.registerBackButtonAction(() => {
        this.goBackLogic();
        console.log('监听右键Boolean值：' + this.checkPage);
        if (this.checkPage) {
          //如果是根目则按照需求1处理
          this.exitApp()
        } else {
          //非根目录返回上一级页面
          this.app.goBack()
        }
      }, 10);
// 后面的数字10是必要参数，如果不写默认是0，数字越大优先级越高


      //JPush第三方插件
      this.jPush.init();
      if(this.platform.is('ios')){
        this.jPush.setApplicationIconBadgeNumber(0);
      }
      setTimeout(function () {
        jPush.openNotification()
          .subscribe( res => {
            console.log('收到推送');
            console.log(res)
          });

        jPush.receiveNotification()
          .subscribe( res => {
            console.log('收到推送');
            console.log(res)
          });

        jPush.receiveMessage()
          .subscribe( res => {
            console.log('收到推送');
            console.log(res)
          });
      }, 1000);

//if ios 打开倒计时 否则 不用timeout


      splashScreen.hide();
      statusBar.styleDefault();

      this.storage.get('firstIn').then((result) => {
        if(result){
          this.storage.get('lgtoken').then((lgtoken)=>{
            if (lgtoken === null || lgtoken === undefined || lgtoken === ''){
              this.rootPage = Login;
            } else{
              this.storage.get('role').then((data)=>{
                if (data == "baojia") {
                  this.rootPage = TabsPage;
                }else{
                  this.rootPage = TabsPageB;
                }
              })
            }
          });
        }
        else{
          this.storage.set('firstIn', true);
          this.rootPage = welcomePage;

        }
      });


      /*
      if (platform.is('android')) {

      } else if (platform.is('ios')) {
        setTimeout(function () {
          this.storage.get('firstIn').then((result) => {
            if (result) {
              //this.storage.set('firstIn', false);
              this.storage.get('lgtoken').then((lgtoken) => {
                console.log(lgtoken);
                if (lgtoken === null || lgtoken === undefined || lgtoken === '') {
                  this.rootPage = Login;
                  splashScreen.hide();
                }
                else {
                  this.storage.get('role').then((data) => {
                    if (data == "baojia") {
                      this.rootPage = TabsPage;
                      splashScreen.hide();
                    } else {
                      this.rootPage = TabsPageB;
                      splashScreen.hide();
                    }
                  })
                }
              });
            }
            else {
              this.storage.set('firstIn', true);
              this.rootPage = welcomePage;
              splashScreen.hide();
            }
          });
        }, 1000);
      }*/

    });


  }

  exitApp () {
    if (this.registerBackButton) {
      this.platform.exitApp()
    } else {
      this.registerBackButton = true;
      this.toastCtrl.create({
        message: '再按一次退出应用',
        duration: 2000,
        position: 'top',
        cssClass: 'toast-black'
      }).present();
      setTimeout(() => this.registerBackButton = false, 2000);//2秒内没有再次点击返回则将触发标志标记为false
    }
  }

  goBackLogic() {
    let currentCmp = this.app.getActiveNav().getActive().component;
    let isLogin= currentCmp === Login;
    let isTabs= currentCmp === TabsPage;
    let isTabsB= currentCmp === TabsPageB;
    if (isLogin ||isTabs || isTabsB ) {
      this.checkPage = true
    } else {
      this.checkPage = false
    }
  }
}
