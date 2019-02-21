/**
 * Created by Ry-kaifa on 2017/3/3.
 */

function showtime(t){
    var phoneNo = document.getElementById("phone1").value;
    if (checkPhone(phoneNo)) {
        document.getElementById("send").disabled=true;
        for(i=1;i<=t;i++) {
            window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
        }
        window.setTimeout(function () {
            document.getElementById("send").disabled=false;
        }, t * 1000 + 1000);
    } else {
        alert("请输入正确的手机号！");
    }
}
function update_p(num,t) {
    if(num == t) {
        document.getElementById("send").value =" 重新发送 ";
    }
    else {
        printnr = t-num;
        document.getElementById("send").value =printnr +"s重新发送";
    }
}