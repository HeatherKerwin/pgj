var _mvq = _mvq || [];
_mvq.push(['$setAccount', 'm-236927-0']);

_mvq.push(['$logConversion']);
(function() {
var mvl = document.createElement('script');
mvl.type = 'text/javascript'; mvl.async = true;
mvl.src = ('https:' == document.location.protocol ? 'https://static-ssl.mediav.com/mvl.js' : 'http://static.mediav.com/mvl.js');
var s = document.getElementsByTagName('script')[0];
s.parentNode.insertBefore(mvl, s);
})();

//以上是流量统计(到达转化)代码，后续转化代码需要配合流量统计代码一同使用

(function() {

    var REQUEST_NAME = {
       
        'bridge-popup' : 'qiao-invite-accept',
        'bridge-float' : 'BDBridgeIconWrap',
        'bridge-popup1' : 'BDBridgeInviteWrap',
        'bridge-offline' : 'BdBPSend',
        'qiao-icon-wrap' : 'qiao-icon-wrap' 
        
    };

    window._hmt = window._hmt || [];

    var getElementsByClassName = function(elem, className) {
        if (elem.getElementsByClassName) {
            return elem.getElementsByClassName(className);
        }
        var elems = [];
        var children = elem.getElementsByTagName('*');
        className = new RegExp("(^|\s)" + className.replace(/\-/g, "\-") + "(\s|$)");
        for (var i = 0,
        l = children.length; i < l; i++) {
            if (className.test(children[i].className)) {
                elems.push(children[i]);
            }
        }
        return elems;
    };

    var on = function(elem, event, handler) {
        if (elem.addEventListener) {
            elem.addEventListener(event, handler, false);
        } else if (elem.attachEvent) {
            elem.attachEvent('on' + event, handler);
        }
    };

    var isType = function(object, type) {
        return Object.prototype.toString.call(object) === '[object ' + type + ']';
    };

    var iterate = function(obj, func) {
        for (var i in obj) {
            if (obj.hasOwnProperty(i)) {
                func.call(obj, i, obj[i]);
            }
        }
    };

    on(document, 'mouseup',
    function(e) {
        e = e || window.event;
        var target = e.target || e.srcElement;
        var request = {};
        var flag = 0;
        iterate(REQUEST_NAME,
        function(name, identifier) {
            request[name] = isType(identifier, 'Function') ? identifier() : document.getElementById(identifier);
        });
        while (target && target != document) {
            iterate(request,
            function(name, object) {
                if (isType(object, 'Array') || isType(object, 'NodeList') || isType(object, 'HTMLCollection')) {
                    for (var i = 0,l = object.length; i < l; i++) {
                        if (target === object[i]) {
                            _mvq.push(['$setAccount', 'm-236927-0']);
                            _mvq.push(['custom', 'jzqu2', /*咨询数*/ Math.random(),  '']);
                            _mvq.push(['$logConversion']);
                            flag = 1;
                        }
                    }
                } else {
                    if (target === object) {
                         _mvq.push(['$setAccount', 'm-236927-0']);
                         _mvq.push(['custom', 'jzqu2', /*咨询数*/ Math.random(),  '']);
                         _mvq.push(['$logConversion']);
                        flag = 1;
                    }
                }

            });
            if (flag == 1) {
                target = document;
            } else {
                target = target.parentNode;
            }
        }
    });
})();
//以上是咨询的转化跟踪代码，请将转化明细信息回传到相应字段         