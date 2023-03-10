//날짜 형식 0000-00-00 으로 변경
function changeDateFormat(regdate) {

  function pad(number) {
    if (number < 10) {
      return '0' + number;
    }
    return number;
  }

  var date = new Date(regdate);

  return date.getFullYear() + "-" + (pad(date.getMonth() + 1)) + "-" + pad(date.getDate());
}

// 모달 내부 내용 생성 및 열기
$(".showModal").click(function (e) {

  var admin_no = $(this).parent().prev().prev().prev().prev().html();

  $.ajax({
    url: "../getAdminInfo",
    type: "post",
    data: JSON.stringify({ admin_no: admin_no }),
    contentType: "application/json",
    success: function (vo) {

      var str = '';

      str += '<div class="modal-body-inner">';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">번호</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputText1" name="admin_no" value=' + vo.admin_no + ' readonly>';
      str += '</div>';
      str += '</div>';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">이름</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputText2" value=' + vo.admin_name + ' readonly>';
      str += '</div>';
      str += '</div>';
      str += '<fieldset class="row mb-3">';
      str += '<legend class="col-form-label col-sm-2 pt-0">유형</legend>';
      str += '<div class="col-sm-10">';
      str += '<div class="form-check">';
      str += '<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios0" value="master" disabled>';
      str += '<label class="form-check-label" for="gridRadios0">';
      str += '사이트 관리자';
      str += '</label>';
      str += '</div>';
      str += '<div class="form-check">';
      str += '<input class="form-check-input adminType" type="radio" name="gridRadios" id="gridRadios1" value="manager" ' + (vo.admin_type == "manager" ? "checked" : "") + ' >';
      str += '<label class="form-check-label" for="gridRadios1">';
      str += '매니저';
      str += '</label>';
      str += '</div>';
      str += '<div class="form-check">';
      str += '<input class="form-check-input adminType" type="radio" id="gridRadios2" name="gridRadios" value="audition" ' + (vo.admin_type == "audition" ? "checked" : "") + ' >';
      str += '<label class="form-check-label" for="gridRadios2">';
      str += '오디션 관리자';
      str += '</label>';
      str += '</div>';
      str += '<div class="form-check">';
      str += '<input class="form-check-input adminType" type="radio" id="gridRadios3" name="gridRadios" value="sale" ' + (vo.admin_type == "sale" ? "checked" : "") + ' >';
      str += '<label class="form-check-label" for="gridRadios3">';
      str += '상품 관리자';
      str += '</label>';
      str += '</div>';
      str += '</div>';
      str += '<input type="hidden" class="admin_type" name="admin_type" value="" />'
      str += '</fieldset>';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">그룹/배우</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputText3" name="ent_name" value=' + (vo.ent_name != "none" ? vo.ent_name : '') + '>';
      str += '</div>';
      str += '</div>';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">아이디</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputText4" value=' + vo.admin_id + ' readonly>';
      str += '</div>';
      str += '</div>';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">연락처</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputText5" value=' + vo.admin_contact + ' readonly>';
      str += '</div>';
      str += '</div>';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">이메일</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputEmail1" value=' + vo.admin_email + ' readonly>';
      str += '</div>';
      str += '</div>';
      str += '<div class="row mb-3">';
      str += '<label for="inputEmail3" class="col-sm-2 col-form-label">등록일</label>';
      str += '<div class="col-sm-10">';
      str += '<input type="text" class="form-control" id="inputEmail2" value=' + changeDateFormat(vo.admin_regdate) + ' readonly>';
      str += '</div>';
      str += '</div>';
      str += '</div>';

      $(".modal-body").append(str);
    },
    error: function (err) {
      alert("관리자 정보 조회에 실패했습니다.");
    }
  });

});

//모달 닫기 버튼 클릭 시, 모달 내부 삭제
$(".closeModal").click(function () {
  $(".modal-body-inner").remove();
});

//모달 외부 영역 클릭 시, 모달 내부 삭제
$(".modal").click(function (e) {
  if (e.target.parentElement.classList.contains("adminUpdateForm")) {
    $(".modal-body-inner").remove();
  }
});

// //모달 창 내 수정 버튼 클릭 시, 권한 정보 수정
$("#updateBtn").on("click", function () {
  //모달 창 내부 수정된 admin_type 값 추출
  var adminType = $(".adminType");
  for (let i = 0; i < adminType.length; i++) {
    if (adminType[i].checked) $(".admin_type").val(adminType[i].value);
  }

  $("#updateForm").submit();
});


