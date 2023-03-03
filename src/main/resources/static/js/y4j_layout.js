//메뉴바 드롭다운
$('.menu_bar > ul > li > span').mouseenter(function(){
  $('.header-bottom').css("height", "230px");
  $('.menu_bar > ul > li > .snb').css("height", "170px");
});

//메뉴바 드롭다운 해제
$('.header-bottom').mouseleave(function(){
  $('.header-bottom').css("height", "60px");
  $('.menu_bar > ul > li > .snb').css("height", "0px");
});

//메뉴바 상단 고정
var topMenu = document.querySelector(".header-bottom");
var headerBottomInner =  document.querySelector(".header-bottom .header-bottom-inner");
var stickyLogo = document.querySelector(".sticky-logo");

function menuBarFixed() {
  if(scrollY > 107) {
    topMenu.classList.add("sticky");
    headerBottomInner.style.backgroundImage = "linear-gradient(to right, rgb(45, 45, 45), rgb(111, 111, 111) 60%, rgb(200, 200, 200))";
    stickyLogo.style.display = "inline-block";

  } else {
    topMenu.classList.remove("sticky");
    headerBottomInner.style.backgroundImage = "linear-gradient(to right, rgb(111, 111, 111), rgb(166, 166, 166) 50%, rgb(222, 222, 222))";
    stickyLogo.style.display = "none";
  };
}
document.addEventListener('scroll', menuBarFixed);

// 사이드 메뉴바 고정
var sideMenuBar = document.querySelector("aside");
function sideMenuBarFixed() {
  if(scrollY > 106) {
    sideMenuBar.style.position = "fixed";
    sideMenuBar.style.top = "0px";
    document.querySelector(".aside-wrap").style.backgroundColor = "#333";
    sideMenuBar.style.color = "#dddddd";
    document.querySelector("aside .aside-wrap .sideMenuBarBtn").style.backgroundColor="#333";

  } else {
    sideMenuBar.style.position = "absolute";
    sideMenuBar.style.top = "106px";
    document.querySelector(".aside-wrap").style.backgroundColor = "#777";
    sideMenuBar.style.color = "black";
    document.querySelector("aside .aside-wrap .sideMenuBarBtn").style.backgroundColor="#777";
  }
}
document.addEventListener('scroll', sideMenuBarFixed);

// 사이드 메뉴바 탭 열고 닫기
$('aside .aside-wrap .sideMenuBar .mngBar > div').click(function(e) {
  if(!e.target.classList.contains("smt")) return;
  if(e.target.nextElementSibling.style.height == "0px" || e.target.nextElementSibling.style.height == 0) {
    if(e.target.classList.contains("mngBar3")) {
      e.target.nextElementSibling.style.height = "80px";
      e.target.style.borderBottom = "2px solid black";
      e.target.nextElementSibling.style.border = "2px solid black";
    } else {
      e.target.nextElementSibling.style.height = "55px";
      e.target.style.borderBottom = "2px solid black";
      e.target.nextElementSibling.style.border = "2px solid black";
    }
  } else {
    e.target.nextElementSibling.style.height = 0;
  }
});

// 사이드 메뉴바 전체 열고 닫기
$('aside .aside-wrap .sideMenuBarBtn').click(function(){
  if($('aside .aside-wrap .sideMenuBarBtn').hasClass("active")){
    $('aside .aside-wrap').css("width", "24px");
    $('aside .aside-wrap .sideMenuBarBtn').removeClass("active");
    $('.section-outer1').css("marginLeft", 0);
  
  } else {
    $('aside .aside-wrap').css("width", "200px");
    $('aside .aside-wrap .sideMenuBarBtn').addClass("active");
    $('.section-outer1').css("marginLeft", "160px");
  }
});
