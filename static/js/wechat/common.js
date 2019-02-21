/**
 * Created by Administrator on 2017/3/2.
 */
function autoWidth(){
    var w = window.innerWidth/7.50;
    if(w > 100){
        w=100;
    }
    document.documentElement.style.fontSize = w + 'px'
    window.onresize = function () {
        var w = window.innerWidth/7.50
        if(w > 100){
            w=100
        }
        document.documentElement.style.fontSize = w + 'px'
    }
}