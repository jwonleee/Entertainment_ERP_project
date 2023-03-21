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
$('.aside_dohyun_goods_box4 #down_img').click(function(){
/* $('.aside_dohyun_goods_box3').css("height", "250px");*/
  $('.aside_dohyun_goods_box3 .aside_dohyun_sidebar > ul' ).css("height", "270px");
  $('.aside_dohyun_goods_box4 #down_img').css("display", "none");
  $('.aside_dohyun_goods_box4 #up_img').css("display", "block");
  
 /* $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");*/
});

//사이드 메뉴바 드롭다운 해제
$('.aside_dohyun_goods_box4 #up_img').click(function(){
  $('.aside_dohyun_goods_box3 .aside_dohyun_sidebar > ul' ).css("height", "0px");
 $('.aside_dohyun_goods_box4 #up_img').css("display", "none");
  $('.aside_dohyun_goods_box4 #down_img').css("display", "block");
});



/*사이드 바 목록 나오게 하기 - 앨범, 포토카드, 의류, 케이스*/
/*사이드 바 목록 나오게 하기 - 앨범, 포토카드, 의류, 케이스*/

$('.aside_dohyun_sidebar_check').on("click", "li", function(){
	console.log(event.target.innerHTML);
	$('#aside_dohyun_input1').val(event.target.innerHTML);
	$("#productList_dh").submit();
});

$('.product_classfication_dh').on("click", "span", function(){
	console.log(event.target.innerHTML);
	$("#aside_dohyun_input2").val(event.target.innerHTML);
	$("#productList_dh").submit();
});


/* dohyun'S GOODS 버튼 기능*/
$("#dohyunButton ").click(function(){
	$('.dohyun_allproduct').val();
	$('#productList_dh').submit();
});