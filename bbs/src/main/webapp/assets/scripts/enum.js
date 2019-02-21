Date.prototype.format = function (fmt) {
    var paddNum = function(num){
      num += "";
      return num.replace(/^(\d)$/,"0$1");
    }
    //指定格式字符
    var cfg = {
       yyyy : this.getFullYear() //年 : 4位
      ,yy : this.getFullYear().toString().substring(2)//年 : 2位
      ,M  : this.getMonth() + 1  //月 : 如果1位的时候不补0
      ,MM : paddNum(this.getMonth() + 1) //月 : 如果1位的时候补0
      ,d  : this.getDate()   //日 : 如果1位的时候不补0
      ,dd : paddNum(this.getDate())//日 : 如果1位的时候补0
      ,hh : paddNum(this.getHours())  //时
      ,mm : paddNum(this.getMinutes()) //分
      ,ss : paddNum(this.getSeconds()) //秒
    }
    fmt || (fmt = "yyyy-MM-dd hh:mm:ss");
    return fmt.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
}