//상단 메뉴바 드롭다운
$('.header_main_menu > ul > li > span').mouseenter(function(){
 $('.header_main').css("height", "250px");
  $('.header_main_menu > ul > li > .menu_bar1').css("height", "270px");
 /* $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");*/
});

//상단 메뉴바 드롭다운 해제
$('.header_main').mouseleave(function(){
  $('.header_main').css("height", "60px");
  $('.header_main_menu > ul > li > .menu_bar1').css("height", "0px")
/*  $('.menu-outer .header-bottom').css("borderBottom", "none");*/
});


// 사이드 메뉴바 드롭다운
$('.aside_newjeans_goods_box4 #down_img').click(function(){
/* $('.aside_newjeans_goods_box3').css("height", "250px");*/
  $('.aside_newjeans_goods_box3 .aside_newjeans_sidebar > ul' ).css("height", "270px");
  $('.aside_newjeans_goods_box4 #down_img').css("display", "none");
  $('.aside_newjeans_goods_box4 #up_img').css("display", "block");
  
 /* $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");*/
});

//사이드 메뉴바 드롭다운 해제
$('.aside_newjeans_goods_box4 #up_img').click(function(){
  $('.aside_newjeans_goods_box3 .aside_newjeans_sidebar > ul' ).css("height", "0px");
 $('.aside_newjeans_goods_box4 #up_img').css("display", "none");
  $('.aside_newjeans_goods_box4 #down_img').css("display", "block");
});

/*사이드 바 목록 나오게 하기 - 앨범, 포토카드, 의류, 케이스*/

$('.aside_newjeans_sidebar_check').on("click", "li", function(){
	console.log(event.target.innerHTML);
	$('#aside_newjeans_input1').val(event.target.innerHTML);
	$("#productList_nj").submit();
});

$('.product_classfication_nj').on("click", "span", function(){
	console.log(event.target.innerHTML);
	$("#aside_newjeans_input2").val(event.target.innerHTML);
	$("#productList_nj").submit();
});


/* NEWJEANS'S GOODS 버튼 기능*/
$("#newjeansButton ").click(function(){
	$('.newjeans_allproduct').val();
	$('#productList_nj').submit();
});

/*ive 검색창*/
/*$("#header_real_search_nj").change(function(){
	if($("#header_real_search_nj").val() == 'IVE' || 
	$("#header_real_search_nj").val() == 'LEEDOHYUN' || 
	$("#header_real_search_nj").val() == 'CHAESOOBIN' || 
	$("#header_real_search_nj").val() == 'BLACKPINK' ){
		alert("해당 페이지에서는 다른 아티스트 검색이 불가합니다");
	}else{
		
		$("#header_real_search_nj").val();
		$('#productList_nj').submit();
		
	}
	
});*/
