/* admin_audition_list.js */

// 모달 내부 내용 생성 및 열기
$(".showModal").click(function (e) {

  var audition_cv_no = $(this).parent().parent().children(1).html();

  $.ajax({
    url: "../getAudCv",
    type: "post",
    data: JSON.stringify({ audition_cv_no: audition_cv_no }),
    contentType: "application/json",
    success: function (vo) {

      var str = '';
      str += '<div class="modal-body-inner">';
        str += '<fieldset class="row mb-3">';
          str += '<legend class="col-form-label col-sm-2 pt-0">지원 분야</legend>';
          str += '<div class="col-sm-10">';
            str += '<div class="form-check">';
              str += '<input class="form-check-input" type="radio" name="audition_cv_type" id="gridRadios1" value="가수" ' + (vo.audition_cv_type == "가수" ? "checked" : "") + ' >';
              str += '<label class="form-check-label" for="gridRadios1">';
              str += '가수';
              str += '</label>';
            str += '</div>';
            str += '<div class="form-check">';
              str += '<input class="form-check-input" type="radio" name="audition_cv_type" id="gridRadios2" value="배우" ' + (vo.audition_cv_type == "배우" ? "checked" : "") + ' >';
              str += '<label class="form-check-label" for="gridRadios2">';
                str += '배우';
              str += '</label>';
            str += '</div>';
          str += '</div>';
        str += '</fieldset>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_name" class="form-label">이름</label>';
          str += '<input type="text" name="audition_cv_name" class="form-control" id="audition_cv_name" value="' + vo.audition_cv_name + '" />';
          str += '<div class="invalid-feedback">이름은 필수 항목입니다.</div>';
        str += '</div>';
        str += '<fieldset class="row mb-3">';
          str += '<legend class="col-form-label col-sm-2 pt-0">성별</legend>';
          str += '<div class="col-sm-10">';
            str += '<div class="form-check">';
              str += '<input class="form-check-input auditionGender" type="radio" name="audition_cv_gender" id="gridRadios3" value="M" th:checked=\'${userVO.user_gender == "M" ? "true" : "false"}\' >';
              str += '<label class="form-check-label" for="gridRadios3">';
                str += '남';
              str += '</label>';
            str += '</div>';
            str += '<div class="form-check">';
              str += '<input class="form-check-input auditionGender" type="radio" name="audition_cv_gender" id="gridRadios4" value="F" th:checked=\'${userVO.user_gender == "F" ? "true" : "false"}\'>';
              str += '<label class="form-check-label" for="gridRadios4">';
                str += '여';
              str += '</label>';
            str += '</div>';
          str += '</div>';
        str += '</fieldset>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_age" class="form-label">나이</label>';
          str += '<input type="number" name="audition_cv_age" class="form-control" id="audition_cv_age" placeholder="만 나이 입력" />';
          str += '<div class="invalid-feedback">나이는 필수 항목입니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_height" class="form-label">신장</label>';
          str += '<input type="number" name="audition_cv_height" class="form-control" id="audition_cv_height" placeholder="일의 자리까지만 입력" />';
          str += '<div class="invalid-feedback">신장은 필수 항목입니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_weight" class="form-label">체중</label>';
          str += '<input type="number" name="audition_cv_weight" class="form-control" id="audition_cv_weight" placeholder="일의 자리까지만 입력" />';
          str += '<div class="invalid-feedback">체중은 필수 항목입니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_contact" class="form-label">연락처</label>';
          str += '<input type="text" name="audition_cv_contact" class="form-control" id="audition_cv_contact" th:value="${userVO.user_contact}" />';
          str += '<div class="invalid-feedback">연락처는 필수 항목입니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_email" class="form-label">이메일</label>';
          str += '<input type="email" name="audition_cv_email" class="form-control" id="audition_cv_email" th:value="${userVO.user_email}" />';
          str += '<div class="invalid-feedback">이메일은 필수 항목입니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_file1" class="form-label">정면 사진 (jpg, jpeg 파일만 가능)</label>';
          str += '<label class="upload-display" for="audition_cv_file1">';
            str += '<span class="upload-thumb-wrap">';
                str += '<img class="upload-thumb" src="https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/plus_black.svg"/>';
            str += '</span>';
          str += '</label>';
          str += '<!-- <input class="upload-name" value="파일선택" disabled="disabled"> -->';
          str += '<input type="file" name="file" class="form-control upload-file" id="audition_cv_file1" aria-label="file example" />';
          str += '<div class="invalid-feedback">선택된 파일이 없습니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_file2" class="form-label">전신 사진 (이미지 파일만 가능)</label>';
          str += '<label class="upload-display" for="audition_cv_file2">';
            str += '<span class="upload-thumb-wrap">';
                str += '<img class="upload-thumb" src="https://jafp.s3.ap-northeast-2.amazonaws.com/y4j/plus_black.svg"/>';
            str += '</span>';
          str += '</label>';
        str += ' <!-- <input class="upload-name" value="파일선택" disabled="disabled"> -->';
          str += '<input type="file" name="file" class="form-control upload-file" id="audition_cv_file2" aria-label="file example" />';
          str += '<div class="invalid-feedback">선택된 파일이 없습니다.</div>';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_file3" class="form-label">영상 (mp4 확장자만 가능) (선택)</label>';
          str += '<input type="file" name="file" class="form-control upload-file" id="audition_cv_file3" aria-label="file example" />';
        str += '</div>';
        str += '<input type="hidden" name="audition_cv_user_id" class="form-control" id="audition_cv_user_id" th:value="${userVO.user_id}">';
      str += '<div class="modal-body-inner">';

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
  if (e.target.parentElement.classList.contains("audApplyForm")) {
    $(".modal-body-inner").remove();
  }
});

