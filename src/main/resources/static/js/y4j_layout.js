//메뉴바 드롭다운
$('.menu_bar > ul > li > span').mouseenter(function(){
  $('.header-bottom').css("height", "245px");
  $('.menu_bar > ul > li > .snb').css("height", "170px");
  $('.menu-outer .header-bottom').css("borderBottom", "1px solid #999");
});

//메뉴바 드롭다운 해제
$('.header-bottom').mouseleave(function(){
  $('.header-bottom').css("height", "60px");
  $('.menu_bar > ul > li > .snb').css("height", "0px");
  $('.menu-outer .header-bottom').css("borderBottom", "none");
});

//메뉴바 상단 고정
var topMenu = document.querySelector(".header-bottom");
var headerBottomInner =  document.querySelector(".header-bottom .header-bottom-inner");
var stickyLogo = document.querySelector(".sticky-logo");
var footerOuter = document.querySelector(".footer-outer");

function menuBarFixed() {
  if(scrollY > 96) {
    topMenu.classList.add("sticky");
    headerBottomInner.style.backgroundImage = "linear-gradient(to right, rgb(45, 45, 45), rgb(111, 111, 111) 60%, rgb(200, 200, 200))";
    stickyLogo.style.display = "inline-block";
    footerOuter.style.backgroundColor = "rgb(51, 51, 51)";
    $('footer .footer-inner .footer-left').css("color", "#777");
    $('footer .footer-inner .footer-right').css("color", "#777");

  } else {
    topMenu.classList.remove("sticky");
    headerBottomInner.style.backgroundImage = "linear-gradient(to right, rgb(111, 111, 111), rgb(166, 166, 166) 50%, rgb(222, 222, 222))";
    stickyLogo.style.display = "none";
    footerOuter.style.backgroundColor = "rgb(119, 119, 119)";
    $('footer .footer-inner .footer-left').css("color", "black");
    $('footer .footer-inner .footer-right').css("color", "black");
  };
}
document.addEventListener('scroll', menuBarFixed);

// 사이드 메뉴바 고정
var sideMenuBar = document.querySelector("aside");
function sideMenuBarFixed() {
  if(scrollY > 96) {
    sideMenuBar.style.position = "fixed";
    sideMenuBar.style.top = "10px";
    document.querySelector(".aside-wrap").style.backgroundColor = "#333";
    sideMenuBar.style.color = "#dddddd";
    document.querySelector("aside .aside-wrap .sideMenuBarBtn").style.backgroundColor="#333";
    $('aside .aside-wrap .sideMenuBar .mngBar > ul > li > a:not(.on)').css("color", "rgb(200, 200, 200)");

  } else {
    sideMenuBar.style.position = "absolute";
    sideMenuBar.style.top = "96px";
    document.querySelector(".aside-wrap").style.backgroundColor = "#777";
    sideMenuBar.style.color = "black";
    document.querySelector("aside .aside-wrap .sideMenuBarBtn").style.backgroundColor="#777";
    $('aside .aside-wrap .sideMenuBar .mngBar > ul > li > a:not(.on)').css("color", "black");
  }
}
document.addEventListener('scroll', sideMenuBarFixed);

// 사이드 메뉴바 탭 열고 닫기
function sideMenuBarOpenClose(e) {
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
};
$('aside .aside-wrap .sideMenuBar .mngBar > .smt').click(sideMenuBarOpenClose);

// fragment 부분 window.innerWidth에 따른 UI 설정
function resizeFragment() {
  if($('aside .aside-wrap .sideMenuBarBtn').hasClass("active")) {

    if(this.window.innerWidth < 1550) {
      $('.section-outer1').css({marginLeft: "120px", padding:"60px 50px 30px 30px"});
      $('.section-outer2').css("marginTop", "10px");
      $('.fragment').css("width", window.innerWidth - 250 + "px");

      $('.main-header').css({marginLeft: "80px", padding: "10px 0 0 0"});
      $('.main-header-contents').css("width", window.innerWidth - 250 + "px");

      $('.footer-outer').css({marginLeft: "200px"});
      $('footer').css("width", "auto");

      $('.menuTable-outer').css({marginLeft: "235px"});
      $('.menuTable-header').css("width", "auto");

    } else {
      $('.section-outer1').css({marginLeft: "0px", padding:"0px"});
      $('.section-outer2').css("marginTop", "70px");
      $('.fragment').css("width", "1080px");

      $('.main-header').css({marginLeft: "0px"});
      $('.main-header-contents').css("width", "1080px");

      $('.footer-outer').css({marginLeft: "0px"});
      $('footer').css("width", "1080px");

      $('.menuTable-outer').css({marginLeft: "0px"});
      $('.menuTable-header').css("width", "1080px");
    }
  
  } else {

    if(this.window.innerWidth < 1200) {
      $('.section-outer1').css({width: "100%", marginLeft: "60px", padding:"0px"});
      $('.section-outer2').css("marginTop", "70px");
      $('.fragment').css("width", "auto");

      $('.main-header').css({marginLeft: "60px"});
      $('.main-header-contents').css("width", "auto");

      $('.footer-outer').css({marginLeft: "35px"});
      $('footer').css("width", "auto");

      $('.menuTable-outer').css({marginLeft: "60px"});
      $('.menuTable-header').css("width", "auto");

    } else {
      $('.section-outer1').css({width: "100%", marginLeft: "0px", padding:"0px"});
      $('.section-outer2').css("marginTop", "70px");
      $('.fragment').css("width", "1080px");

      $('.main-header').css({marginLeft: "0px"});
      $('.main-header-contents').css("width", "1080px");

      $('.footer-outer').css({marginLeft: "0px"});
      $('footer').css("width", "1080px");

      $('.menuTable-outer').css({marginLeft: "0px"});
      $('.menuTable-header').css("width", "1080px");
    }
  }
}
window.addEventListener('resize', resizeFragment);

// 사이드 메뉴바 전체 열고 닫기
$('aside .aside-wrap .sideMenuBarBtn').click(function(){

  if($('aside .aside-wrap .sideMenuBarBtn').hasClass("active")){
    $('aside .aside-wrap').css("width", "26px");
    $('aside .aside-wrap .sideMenuBarBtn').removeClass("active");

  } else {
    $('aside .aside-wrap').css("width", "200px");
    $('aside .aside-wrap .sideMenuBarBtn').addClass("active");
    // $('.section-outer1').css("marginLeft", "160px");
  }
  resizeFragment();
});

// 푸터 고정
var fragmentHeight = document.querySelector(".fragment").scrollHeight;
function footerFixed() {
  if(fragmentHeight + 170 < window.innerHeight) {
    $(".footer-outer").css({position: "fixed", bottom: "0px"});

  } else {
    $(".footer-outer").css({position: "static", })
  }
}
window.addEventListener('resize', footerFixed);
