var IS_IOS = navigator.userAgent.match(/(iPad|iPhone|iPod)/g) ? !0 : !1,
IS_ANDROID = -1 < navigator.userAgent.indexOf("Android");

var queue = null, l = null, h = null;
init = function(c) {
    h = c;
    IS_ANDROID && (createjs.Sound.play = function(c, b) {
        var e = queue.getResult(c);
        e.currentTime = this.soundSprite[c] ;
        e.play();
        !0 == b && (null != l && (clearTimeout(l), l = null), l = setTimeout(function() { createjs.Sound.play("silenttail") },1E3))
    },
    createjs.Sound.registMySound = function(a, c) {
        this.soundSprite || (this.soundSprite = {});
        this.soundSprite[a] = c ;
    })
};
window.onload = function() {
    queue = new createjs.LoadQueue(!1);
    queue.setMaxConnections(30);
    queue.on("complete", h.startFunc, null, !0);
    
    if(h.audio){
    	if(IS_ANDROID){
    		queue.loadFile({id:"qian", src: "http://www.h6app.com/data/case/xsjsq/media/qian.mp3"});
    		queue.loadFile({id:"bg", src: "http://www.h6app.com/data/case/xsjsq/media/bg.mp3"});
    	}else{
    		createjs.Sound.alternateExtensions = ["ogg"];
    		queue.installPlugin(createjs.Sound);
    		queue.loadManifest(h.audio, !1);
    	}
    }
    queue.load();
};
function setup() {
    if(IS_ANDROID){
    	createjs.Sound.registMySound("qian", 0);
    	createjs.Sound.registMySound("bg", 0);
    	createjs.Sound.registMySound("silenttail", 0.5);
    }
}
var _cfg = {
    startFunc: setup,
    audio: {
        path: "http://www.h6app.com/data/case/xsjsq/media/",
        manifest: [{src:"qian.mp3",id:"qian"}, {src:"bg.mp3",id:"bg"}]
    }
};


$(function(){
    
    init(_cfg);
    
    
//  var touched=false;
//  $(document).on('touchstart', function(){
//  	if(touched==false){
//		    touched=true;
//		    createjs.Sound.play("bg", !0);
//		    createjs.Sound.play("qian", !0);
//	    }
//  })
    
    
    
})