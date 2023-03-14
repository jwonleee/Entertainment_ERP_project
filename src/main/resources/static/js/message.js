//모달 창 내 발송 버튼 클릭 시, 쪽지 발송
$("#sendMsgBtn").on("click", function () {
  $("#sendMsgForm").submit();
});

// 수신 메시지 조회
function getReceivedMsgList() {
  $(".accordion").html("");

  var msg_receiver_id = $('.msg_writer_id').val();

  $.ajax({
    url: "/getReceivedMsg",
    type: "post",
    data: JSON.stringify({ msg_receiver_id: msg_receiver_id }),
    contentType: "application/json",
    success: function (arr) {

      var str = '';
      for (var i = 0; i < arr.length; i++) {

        str += '<div class="accordion-item receivedMsg">';
        str += '<h2 class="accordion-header" id="panelsStayOpen-heading' + i + '">';
        str += '<button class="accordion-header-left accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapse' + i + '" aria-expanded="false" aria-controls="panelsStayOpen-collapse' + i + '">' + arr[i].msg_title;
          if(arr[i].msg_checkdate == null) {
            str += '<span class="position-absolute top-0 start-100 translate-middle p-2 bg-danger border border-light rounded-circle">';
              str += '<span class="visually-hidden">New alerts</span>';
            str += '</span>';
          }
          str += '<div class="accordion-header-right">';
            str += '<span class="badge text-bg-light msgDateData msgCheckDate" style="font-size: 13px;">확인 일자  : ' + (arr[i].msg_checkdate == null ? "미확인" : arr[i].msg_checkdate.replace("T", " ")) + '</span>';
            str += '<span class="badge text-bg-light msgDateData msgSendDate" style="font-size: 13px;">수신 일자 : ' + arr[i].msg_senddate.replace("T", " ") + '</span>';
          str += '</div>';
        str += '</button>';
        str += '</h2>';
        str += '<div id="panelsStayOpen-collapse' + i + '" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-heading' + i + '">';
        str += '<div class="accordion-body">';
        str += '<div class="mb-3">';
        str += '<input type="hidden" class="msg_no" value="' + arr[i].msg_no + '" />';
        str += '<label for="formGroupExampleInput" class="form-label">작성자</label>';
        str += '<input type="text" class="form-control" id="formGroupExampleInput1-' + i + '" value="' + arr[i].msg_writer_name + '(' + arr[i].msg_writer_id + ')' + '" readonly>';
        str += '</div>';
        str += '<div class="mb-3">';
        str += '<label for="formGroupExampleInput2" class="form-label">수신 일자</label>';
        str += '<input type="text" class="form-control" id="formGroupExampleInput2-' + i + '" value="' + arr[i].msg_senddate.replace("T", " ") + '" readonly>';
        str += '</div>';
        str += '<div class="mb-3">';
        str += '<label for="formGroupExampleInput3" class="form-label">확인 일자</label>';
        str += '<input type="text" class="form-control" id="formGroupExampleInput3-' + i + '" value="' + (arr[i].msg_checkdate == null ? "미확인" : arr[i].msg_checkdate.replace("T", " ")) + '" readonly>';
        str += '</div>';
        str += '<div class="mb-3">';
        str += '<label for="exampleFormControlTextarea' + i + '" class="form-label">내용</label>';
        str += '<textarea name="msg_content" class="form-control" id="exampleFormControlTextarea' + i + '" rows="4" readonly>' + arr[i].msg_content + '</textarea>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
      }
      $(".accordion").append(str);
    },
    error: function (err) {
      alert("받은 쪽지 조회에 실패했습니다.");
    }
  });
}

// 발신 메시지 조회
function getSentMsgList() {
  $(".accordion").html("");

  var msg_writer_id = $('.msg_writer_id').val();

  $.ajax({
    url: "/getSentMsg",
    type: "post",
    data: JSON.stringify({ msg_writer_id: msg_writer_id }),
    contentType: "application/json",
    success: function (arr) {

      var str = '';
      for (var i = 0; i < arr.length; i++) {

        str += '<div class="accordion-item">';
        str += '<h2 class="accordion-header" id="panelsStayOpen-heading' + i + '">';
        str += '<button class="accordion-header-left accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapse' + i + '" aria-expanded="false" aria-controls="panelsStayOpen-collapse' + i + '">' + arr[i].msg_title;
          str += '<div class="accordion-header-right">';
            str += '<span class="badge text-bg-light" style="font-size: 13px;">수신 일자  : ' + (arr[i].msg_checkdate == null ? "미확인" : arr[i].msg_checkdate.replace("T", " ")) + '</span>';
            str += '<span class="badge text-bg-light" style="font-size: 13px;">발송 일자 : ' + arr[i].msg_senddate.replace("T", " ") + '</span>';
          str += '</div>';
        str += '</button>';
        str += '</h2>';
        str += '<div id="panelsStayOpen-collapse' + i + '" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-heading' + i + '">';
        str += '<div class="accordion-body">';
        str += '<div class="mb-3">';
        str += '<label for="formGroupExampleInput" class="form-label">수신자</label>';
        str += '<input type="text" class="form-control" id="formGroupExampleInput1-' + i + '" value="' + arr[i].msg_receiver_name + '(' + arr[i].msg_receiver_id + ')' + '" readonly>';
        str += '</div>';
        str += '<div class="mb-3">';
        str += '<label for="formGroupExampleInput2" class="form-label">발송 일자</label>';
        str += '<input type="text" class="form-control" id="formGroupExampleInput2-' + i + '" value="' + arr[i].msg_senddate.replace("T", " ") + '" readonly>';
        str += '</div>';
        str += '<div class="mb-3">';
        str += '<label for="formGroupExampleInput3" class="form-label">수신 일자</label>';
        str += '<input type="text" class="form-control" id="formGroupExampleInput3-' + i + '" value="' + (arr[i].msg_checkdate == null ? "미확인" : arr[i].msg_checkdate.replace("T", " ")) + '" readonly>';
        str += '</div>';
        str += '<div class="mb-3">';
        str += '<label for="exampleFormControlTextarea' + i + '" class="form-label">내용</label>';
        str += '<textarea name="msg_content" class="form-control" id="exampleFormControlTextarea' + i + '" rows="3" readonly>' + arr[i].msg_content + '</textarea>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
      }
      $(".accordion").append(str);
    },
    error: function (err) {
      alert("발신 쪽지 조회에 실패했습니다.");
    }
  });
}

//처음 쪽지 페이지 접속 시, 받은 쪽지 리스트 표출
$(document).ready(getReceivedMsgList);

// 모달 내부 내용 생성 및 열기
// 1. 받은 쪽지 조회
$(".received").click(function (e) {
  getReceivedMsgList();
});

// 2. 보낸 쪽지 조회
$(".sent").click(function (e) {
  getSentMsgList();
});

//모달 창 내 발송 버튼 클릭 시, 쪽지 발송
$("#sendMsgBtn").on("click", function () {
  $("#sendMsgForm").submit();
});

//쪽지 확인 시, 수신일 업데이트
$(document).on("click", ".receivedMsg .accordion-button", function(e) {

  if(e.target.classList.contains("accordion-button")) {
    var checkedDate = e.target.parentElement.nextElementSibling.firstElementChild.children[2].children[1];
    if(checkedDate.value != "미확인") return;

    var uncheckedAlertDot = e.target.firstElementChild;
    if(uncheckedAlertDot.classList.contains("rounded-circle")) $(uncheckedAlertDot).remove();

    var headerCheckDate = e.target.firstElementChild.firstElementChild;
    var msgNo = e.target.parentElement.nextElementSibling.firstElementChild.firstElementChild.firstElementChild.value;

  } else if(e.target.classList.contains("accordion-header-right")) {
    var checkedDate = e.target.parentElement.parentElement.nextElementSibling.firstElementChild.children[2].children[1];
    if(checkedDate.value != "미확인") return;

    var uncheckedAlertDot = e.target.previousElementSibling;
    if(uncheckedAlertDot.classList.contains("rounded-circle")) $(uncheckedAlertDot).remove();

    var headerCheckDate = e.target.firstElementChild;
    var msgNo = e.target.parentElement.parentElement.nextElementSibling.firstElementChild.firstElementChild.firstElementChild.value;

  } else if(e.target.classList.contains("msgDateData")) {
    var checkedDate = e.target.parentElement.parentElement.parentElement.nextElementSibling.firstElementChild.children[2].children[1];
    if(checkedDate.value != "미확인") return;    

    var uncheckedAlertDot = e.target.parentElement.previousElementSibling;
    if(uncheckedAlertDot.classList.contains("rounded-circle")) $(uncheckedAlertDot).remove();

    var headerCheckDate = e.target.parentElement.firstElementChild;
    var msgNo = e.target.parentElement.parentElement.parentElement.nextElementSibling.firstElementChild.firstElementChild.firstElementChild.value;
  }

  // 쪽지 확인 시, 확인일자 업데이트 및 미확인 쪽지 수 변경값 실시간 반영
  $.ajax({
    url: "/checkMsg",
    type: "post",
    data: JSON.stringify({msg_no: msgNo}),
    contentType: "application/json",
    success: function (data) {
      $(".uncheckedMsgNum").html(data);
      getMsgInfo(msgNo, headerCheckDate, checkedDate);
    },
    error: function (err) {
      console.log("확인 일자 업데이트 실패");
    }
  });
});

// 쪽지 확인 시, 업데이트 된 확인일자 불러와서 실시간 반영
function getMsgInfo(msgNo, headerCheckDate, checkedDate) {
  $.ajax({
    url: "/getMsgInfo",
    type: "post",
    data: JSON.stringify({msg_no: msgNo}),
    contentType: "application/json",
    success: function (vo) {
      headerCheckDate.innerHTML = "확인 일자 : " + vo.msg_checkdate.replace("T", " ");
      checkedDate.value = vo.msg_checkdate.replace("T", " ");
    },
    error: function (err) {
      console.log("확인 일자 불러오기 실패");
    }
  });
}