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
$('.aside_ive_goods_box4 #down_img').click(function(){
/* $('.aside_ive_goods_box3').css("height", "250px");*/
  $('.aside_ive_goods_box3 .aside_ive_sidebar > ul' ).css("height", "270px");
  $('.aside_ive_goods_box4 #down_img').css("display", "none");
  $('.aside_ive_goods_box4 #up_img').css("display", "block");
  
 /* $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");*/
});

//사이드 메뉴바 드롭다운 해제
$('.aside_ive_goods_box4 #up_img').click(function(){
  $('.aside_ive_goods_box3 .aside_ive_sidebar > ul' ).css("height", "0px");
 $('.aside_ive_goods_box4 #up_img').css("display", "none");
  $('.aside_ive_goods_box4 #down_img').css("display", "block");
});


//데이터 끌어오기!!!!!!
//전체 상품 목록 (아이브)
$(".ive_sidebar").on("click", "li", function(){
	$("#product_value").val(event.target.innerHTML);
	$('#ive_page').submit();
})

$('.product_classfication_ive').on("click", "span", function(){
	$("#product_classfication_button").val(event.target.innerHTML);
	$('#ive_page').submit();
});

/* IVE'S GOODS 버튼 기능*/
$("#iveButton ").click(function(){
	$('.ive_allproduct').val();
	$('#ive_page').submit();
});
