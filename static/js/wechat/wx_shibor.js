// JavaScript Document
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
	/*function leftday(){
		var dayshow = $("#dayshow").html();
		dayshow = new Date(Date.parse(dayshow.replace(/-/g, "/")));
		dayshow = dayshow.getTime()-24*60*60*1000;
		$("#dayshow").html(timetostr(dayshow));
	}
	function rightday(){
		var dayshow = $("#dayshow").html();
		dayshow = new Date(Date.parse(dayshow.replace(/-/g, "/")));
		dayshow = dayshow.getTime()+24*60*60*1000;
		$("#dayshow").html(timetostr(dayshow));
	}*/
	
    /*var html = "";
    html += "<li>";
    html += "<div class='shibor_col'>";
    html += "<div class='termDiv'><img class='termIcon' src='/images/shibor/arrow.png'></div>";
    html += "<div class='termText'>O/N</div>";
    html += "</div>";
    html += "<div class='shibor_col'>" + "000" + "</div>";
    html += "<div class='shibor_col'>";
	html += "<div class='changeDiv'><img class='changeIcon' src='/images/shibor/up.png'></div>";
	html += "<div class='changeText'>0.00</div>";
    html += "</div>";
    html += "</div>";
    html += "</li>";
	
	
	html += "<li>";
    html += "<div class='shibor_col'>";
    html += "<div class='termDiv'><img class='termIcon' src='/images/shibor/arrow.png'></div>";
    html += "<div class='termText'>1W</div>";
    html += "</div>";
    html += "<div class='shibor_col'>" + "000" + "</div>";
    html += "<div class='shibor_col'>";
	html += "<div class='changeDiv'><img class='changeIcon' src='/images/shibor/down.png'></div>";
	html += "<div class='changeText'>0.00</div>";
    html += "</div>";
    html += "</div>";
    html += "</li>";

    
    $(".shibor_nav").html(html);*/