
//상단 메뉴바 드롭다운
$('.header_main_menu > ul > li > span').mouseenter(function(){
 $('.header_main').css("height", "350px");
  $('.header_main_menu > ul > li > .menu_bar1').css("height", "380px");
 /* $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");*/
});

//상단 메뉴바 드롭다운 해제
$('.header_main_menu > ul > li > span').mouseleave(function(){
  $('.header_main').css("height", "60px");
  $('.header_main_menu > ul > li > .menu_bar1').css("height", "0px")
/*  $('.menu-outer .header-bottom').css("borderBottom", "none");*/
});


/* 주문 배송/ 환불 정보 / 리뷰 탭..*/
$(".productTabWrap1 .productTabBox").on("click", "li", function(){
	
	if(event.target.innerHTML == "환불 및 교환 정보"){
		$(".changeContent").css("display", "block");
		$(".orderContent").css("display", "none");
	}else{
		$(".orderContent").css("display", "block");
		$(".changeContent").css("display", "none");
	}
});


/* 상품 상세 정보 中 수량 + / - 버튼 기능*/
/* 빼기 기능 */
$("#decide_cnt_minus").click(function(){
	
	if($('#decide_cnt').val() ==  1) {
		return;
	}else if($('#decide_cnt').val() != 0){
	
		var number = $('#decide_cnt').val() ;
		var n = parseInt(number) - 1;
		$('#decide_cnt').val(n);
		var total = $("#total_price").attr('value')* n;
		$("#total_price").text(total+'원');
		$("#total_price2").text(total +'원');
		$("#user_order_total_price").val(total).submit();
}
	
})
/* 더하기 기능 */
$("#decide_cnt_plus").click(function(){
	
		var number = $('#decide_cnt').val() ;
		var n = parseInt(number) + 1;
		$('#decide_cnt').val(n);
		var total = $("#total_price").attr('value')* n;
		$("#total_price").text(total+'원');
		$("#total_price2").text(total +'원');
		$("#user_order_total_price").val(total).submit();
		
})


/*$("#buyButton").click(function(){
	$("#order_date_now").val().submit();
	$("#order_prod_name").val().submit();
	$("#user_order_total").val().submit();
	$("#decide_cnt").val().submit();
	
})*/