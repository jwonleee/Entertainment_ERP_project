//popupPage Image Swipe
var arr = document.querySelectorAll(".popupPage > .popupImage > .swipeImg");
var pagination = document.querySelectorAll(".pagination-bullet");
pagination[0].style.backgroundColor = "rgb(79, 17, 90)";

var i = 0;
var popupSetIntervalNum = 0;
//3초마다 이미지 자동 변경
popupSetIntervalNum = setInterval(controllImgSwipe, 3000);
function controllImgSwipe() {
  arr[i].classList.toggle("active");
  pagination[i].style.backgroundColor = "rgb(196, 194, 194)";
  i++;
  if (i == arr.length) i = 0;
  pagination[i].style.backgroundColor = "rgb(79, 17, 90)";
  arr[i].classList.toggle("active");
};

//클릭 시 이미지 변경
var pagination2 = document.querySelector(".pagination");
pagination2.onclick = function (e) {
  if (e.target.tagName != "SPAN") return;

  if (playStopBtnToggle.alt === "stopIcon") {
    window.clearInterval(popupSetIntervalNum);

    var i = e.target.dataset.pagination;
    for (let i = 0; i < arr.length; i++) {
      if (arr[i].classList.contains("active")) {
        arr[i].classList.remove("active");
        pagination[i].style.backgroundColor = "rgb(196, 194, 194)";
      }
    };

    arr[i].classList.add("active");
    pagination[i].style.backgroundColor = "rgb(79, 17, 90)";

    popupSetIntervalNum = setInterval(function () {
      arr[i].classList.toggle("active");
      pagination[i].style.backgroundColor = "rgb(196, 194, 194)";
      i++;
      if (i == arr.length) i = 0;
      pagination[i].style.backgroundColor = "rgb(79, 17, 90)";
      arr[i].classList.toggle("active");
    }, 3000);

  } else if (playStopBtnToggle.alt === "playIcon") {
    var i = e.target.dataset.pagination;
    for (let i = 0; i < arr.length; i++) {
      if (arr[i].classList.contains("active")) {
        arr[i].classList.remove("active");
        pagination[i].style.backgroundColor = "rgb(196, 194, 194)";
      }
    };

    arr[i].classList.add("active");
    pagination[i].style.backgroundColor = "rgb(79, 17, 90)";
  }
};

//쿠키 생성
var popupControll = document.querySelector(".popupControll");
var popupControllCheckbox = document.querySelector('.popupControll input[type="checkbox"]');

var date = new Date();
date.setDate(date.getDate() + 1);
date.setHours(0);
date.setMinutes(0);
date.setSeconds(0);

popupControllCheckbox.onclick = function () {
  if (popupControllCheckbox.checked) {
    document.cookie = "y4jPopupPage=123456; path=/; expires=" + date.toUTCString();
  }
  window.close();
};

//play & stop button image toggle
var playStopBtnToggle = document.querySelector(".playStopBtnToggle");
var cnt = 1;
playStopBtnToggle.addEventListener('click', playStopToggle);
function playStopToggle(e) {
  if (e.target.tagName != "IMG") return;

  if (e.target.alt === "stopIcon") {

    window.clearInterval(popupSetIntervalNum);
    e.target.src = "https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/popupPlayIcon.png";
    e.target.alt = "playIcon";

  } else if (e.target.alt === "playIcon") {
    var i = 0;
    for(let j=0; j<arr.length; j++) {
      if(arr[j].classList.contains("active")) i = j;
    }

    arr[i].classList.add("active");
    pagination[i].style.backgroundColor = "rgb(79, 17, 90)";

    popupSetIntervalNum = setInterval(function () {
      arr[i].classList.toggle("active");
      pagination[i].style.backgroundColor = "rgb(196, 194, 194)";
      i++;
      if (i == arr.length) i = 0;
      pagination[i].style.backgroundColor = "rgb(79, 17, 90)";
      arr[i].classList.toggle("active");
    }, 3000);
    
    e.target.src = "https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/popupStopIcon.png";
    e.target.alt = "stopIcon";
  }
};

