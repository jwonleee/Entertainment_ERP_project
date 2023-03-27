 /*-- 기간 조회하는 date-picker --*/ 
  $("#start_date").datepicker({
            dateFormat: 'yy-mm-dd',
            showOn: "button",
            buttonImage: "https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/calendar1.png",
            buttonImageOnly: true,
            changeMonth: true,
            changeYear: true,
          /*   yearRange: "-10:100", */
            nextText: "next",
            prevText: "prev",
            maxDate: new Date()
        });
        $("#end_date").datepicker({
            dateFormat: 'yy-mm-dd',
            showOn: "button",
            buttonImage: "https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/calendar1.png",
            buttonImageOnly: true,
            changeMonth: true,
            changeYear: true,
            /* yearRange: "-10:100", */
            nextText: "next",
            prevText: "prev",
            maxDate: new Date()
        });
        
 $("img.ui-datepicker-trigger").attr("style", "width:30px; heigt:30px; vertical-align:middle; cursor: pointer;");
 
 $(".choose_period").on ("click", "button", function(){
	 
	 if(event.target.innerHTML == "오늘"){
		$("#date_search_btn").val(event.target.innerHTML);
		$("#start_date").val("") && $("#end_date").val("");
		
	 }else if(event.target.innerHTML == "1주일"){
		$("#date_search_btn").val(event.target.innerHTML);	
			$("#start_date").val("") && $("#end_date").val("");
		
	 }else if(event.target.innerHTML == "1개월"){
		$("#date_search_btn").val(event.target.innerHTML);
			$("#start_date").val("") && $("#end_date").val("");
			
	 }else if(event.target.innerHTML =="3개월"){
		$("#date_search_btn").val(event.target.innerHTML); 
			$("#start_date").val("") && $("#end_date").val("");
	 }
	 
 });
 
// 데이트 피커로 기간 조회하면 버튼값 삭제
if($("#start_date").val() != ""){
		$("#date_search_btn").val("");
}