/* admin_audition_list.js */

// 모달 내부 내용 생성 및 열기
$(".showModal").click(function (e) {

  // var audition_cv_no = $(this).parent().prev().prev().prev().prev().html();

  $.ajax({
    url: "../getAudCv",
    type: "post",
    data: JSON.stringify({ audition_cv_no: audition_cv_no }),
    contentType: "application/json",
    success: function (vo) {

      var str = '';

      str += '<div>';
      str += '</div>';

      $(".modal-body").append(str);
    },
    error: function (err) {
      alert("지원서 불러오기에 실패했습니다.");
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
// $("#updateBtn").on("click", function () {
//   //모달 창 내부 수정된 admin_type 값 추출(라디오 타입)
//   var adminType = $(".adminType");
//   for (let i = 0; i < adminType.length; i++) {
//     if (adminType[i].checked) $(".admin_type").val(adminType[i].value);
//   }

//   $("#updateForm").submit();
// });





