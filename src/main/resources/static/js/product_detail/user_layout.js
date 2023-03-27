//상단 메뉴바 드롭다운
$('.header_main_menu > ul > li > span').mouseenter(function(){
 $('.header_main').css("height", "320px");
  $('.header_main_menu > ul > li > .menu_bar1').css("height", "370px");
 /* $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");*/
});

//상단 메뉴바 드롭다운 해제
$('.header_main').mouseleave(function(){
  $('.header_main').css("height", "70px");
  $('.header_main_menu > ul > li > .menu_bar1').css("height", "0px");
/*  $('.menu-outer .header-bottom').css("borderBottom", "none");*/
});