/**
 * Created by Ry-kaifa on 2016/10/15.
 */
//背书手数加减
var setAmount1 = {
    min:0,
    max:999,
    reg:function(x) {
        return new RegExp("^[0-9]\\d*$").test(x);
    },
    amount:function(obj, mode) {
        var x = $(obj).val();
        if (this.reg(x)) {
            if (mode) {
                x++;
            } else {
                x--;
            }
        } else {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
        return x;
    },
    reduce:function(obj) {
        var x = this.amount(obj, false);
        if (x >= this.min) {
            $(obj).val(x);
            recalc();
        } else {
            uexToast("背书手数最少为" + this.min);
            $(obj).val(0);
        }
    },
    add:function(obj) {
        var x = this.amount(obj, true);
        if (x <= this.max) {
            $(obj).val(x);
        } else {
            uexToast("背书手数最多为" + this.max);
            $(obj).val(999);
        }
    },
    modify:function(obj) {
        var x = $(obj).val();
        if (x < this.min || x > this.max || !this.reg(x)) {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
    }
}
/**
 * 价格微调 加减
 */
var setAmount3 = {
    min:0.00,
    max:100.00,
    reg:function(x) {
        return new RegExp("^\\d+(\\.\\d+)?$").test(x);
    },amount:function(obj, mode) {
        var x = $(obj).val();
        if (this.reg(x)) {
            if (mode) {
                x=myNum(x,0.01);
            } else {
                x=myNum(x,-0.01);
            }
        } else {
            $(obj).val(0.00);
        }
        return x;
    },reduce:function(obj) {
        var x = this.amount(obj, false);
        if (x >= this.min) {
            $(obj).val(x);
        } else {
            $(obj).val(0.00);
        }
    },add:function(obj) {
        var x = this.amount(obj, true);
        if (x <= this.max) {
            $(obj).val(x);
        } else {
            $(obj).val(1.00);
        }
    },modify:function(obj) {
        var x = $(obj).val();
        if (x < this.min || x > this.max || !this.reg(x)) {
            $(obj).val(0.00);
        }
    }
}
function myNum(num,add) {
    var temp = parseFloat(num) + add;
    return Number(temp.toFixed(2));
}

/**
 * 价格微调 加减
 */
var setAmount2 = {
    min:0,
    max:9999,
    reg:function(x) {
        return new RegExp("^\\d+(\\.\\d+)?$").test(x);
    },amount:function(obj, mode) {
        var x = $(obj).val();
        if (this.reg(x)) {
            if (mode) {
                x = Number(x)+10;
            } else {
                x-=10;
            }
        } else {
            $(obj).val(0);
        }
        return x;
    },reduce:function(obj) {
        var x = this.amount(obj, false);
        if (x >= this.min) {
            $(obj).val(x);
        } else {
            $(obj).val(0);
        }
    },add:function(obj) {
        var x = this.amount(obj, true);
        if (x <= this.max) {
            $(obj).val(x);
        } else {
            $(obj).val(9999);
        }
    },modify:function(obj) {
        var x = $(obj).val();
        if (x < this.min || x > this.max || !this.reg(x)) {
            $(obj).val(0);
        }
    }
}
