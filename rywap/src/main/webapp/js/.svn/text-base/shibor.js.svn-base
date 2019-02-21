/**
 * Created by Ry-kaifa on 2017/7/19.
 */
// 选择时间
var timestamp1 = Date.parse(new Date());
$("#dayshow").html(timetostr(timestamp1));

function timetostr(timestamp){
    var myDate = new Date(timestamp);
    var fullyear = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
    if(month<10){
        month="0"+month;
    }
    var date = myDate.getDate();        //获取当前日(1-31)
    if(date<10){
        date = "0"+date;
    }
    return fullyear+"-"+month+"-"+date;
}
function leftday(){
    var dayshow = $("#dayshow").html();
    dayshow = new Date(Date.parse(dayshow.replace(/-/g, "/")));
    dayshow = dayshow.getTime()-24*60*60*1000;
    $("#dayshow").html(timetostr(dayshow));
    loaddata();
}
function rightday(){
    var dayshow = $("#dayshow").html();
    dayshow = new Date(Date.parse(dayshow.replace(/-/g, "/")));
    dayshow = dayshow.getTime()+24*60*60*1000;
    $("#dayshow").html(timetostr(dayshow));
    loaddata();
}
