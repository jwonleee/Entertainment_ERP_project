/* admin_audition_list.js */

// 모달 내부 내용 생성 및 열기
$(".showModal").click(function (e) {

  $(".modal-body-inner").remove();

  var audition_cv_no = $(this).parent().parent().children(1).html();

  $.ajax({
    url: "../getAudCv",
    type: "post",
    data: JSON.stringify({ audition_cv_no: audition_cv_no }),
    contentType: "application/json",
    async: false,
    success: function (vo) {

      var str = '';
      str += '<div class="modal-body-inner">';
        str += '<fieldset class="row mb-3">';
          str += '<legend class="col-form-label col-sm-2 pt-0">지원 분야</legend>';
          str += '<div class="col-sm-10">';
            str += '<div class="form-check">';
              str += '<input class="form-check-input" type="radio" name="audition_cv_type" id="gridRadios1" value="가수" ' + (vo.audition_cv_type == "가수" ? "checked" : "") + ' disabled >';
              str += '<label class="form-check-label" for="gridRadios1">';
              str += '가수';
              str += '</label>';
            str += '</div>';
            str += '<div class="form-check">';
              str += '<input class="form-check-input" type="radio" name="audition_cv_type" id="gridRadios2" value="배우" ' + (vo.audition_cv_type == "배우" ? "checked" : "") + ' disabled >';
              str += '<label class="form-check-label" for="gridRadios2">';
                str += '배우';
              str += '</label>';
            str += '</div>';
          str += '</div>';
        str += '</fieldset>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_name" class="form-label">이름</label>';
          str += '<input type="text" name="audition_cv_name" class="form-control" id="audition_cv_name" value="' + vo.audition_cv_name + '" readonly />';
        str += '</div>';
        str += '<fieldset class="row mb-3">';
          str += '<legend class="col-form-label col-sm-2 pt-0">성별</legend>';
          str += '<div class="col-sm-10">';
            str += '<div class="form-check">';
              str += '<input class="form-check-input auditionGender" type="radio" name="audition_cv_gender" id="gridRadios3" value="M" ' + (vo.audition_cv_gender == "M" ? "checked" : "") + ' disabled >';
              str += '<label class="form-check-label" for="gridRadios3">';
                str += '남';
              str += '</label>';
            str += '</div>';
            str += '<div class="form-check">';
              str += '<input class="form-check-input auditionGender" type="radio" name="audition_cv_gender" id="gridRadios4" value="F" ' + (vo.audition_cv_gender == "F" ? "checked" : "") + ' disabled >';
              str += '<label class="form-check-label" for="gridRadios4">';
                str += '여';
              str += '</label>';
            str += '</div>';
          str += '</div>';
        str += '</fieldset>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_age" class="form-label">나이</label>';
          str += '<input type="number" name="audition_cv_age" class="form-control" id="audition_cv_age" value="' + vo.audition_cv_age + '" readonly />';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_height" class="form-label">신장</label>';
          str += '<input type="number" name="audition_cv_height" class="form-control" id="audition_cv_height" value="' + vo.audition_cv_height + '" readonly />';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_weight" class="form-label">체중</label>';
          str += '<input type="number" name="audition_cv_weight" class="form-control" id="audition_cv_weight" value="' + vo.audition_cv_weight + '" readonly />';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_contact" class="form-label">연락처</label>';
          str += '<input type="text" name="audition_cv_contact" class="form-control" id="audition_cv_contact" value="' + vo.audition_cv_contact + '" readonly />';
        str += '</div>';
        str += '<div class="mb-3">';
          str += '<label for="audition_cv_email" class="form-label">이메일</label>';
          str += '<input type="email" name="audition_cv_email" class="form-control" id="audition_cv_email" value="' + vo.audition_cv_email + '" readonly />';
        str += '</div>';

        str += '<div class="mb-3">';
          str += '<label for="audition_cv_file1" class="form-label">사진</label>';
          str += '<label class="upload-display" for="audition_cv_file1">';
            str += '<span class="upload-thumb-wrap">';
              str += '<img class="upload-thumb audImgFile1" />';
              str += '<img class="upload-thumb audImgFile2" />';
            str += '</span>';
          str += '</label>';
        str += '</div>';

        str += '<div class="mb-3 vid-outer">';
          str += '<label for="audition_cv_file3" class="form-label">영상</label>';
          str += '<video class="audVidFile" controls ></video>';
        str += '</div>';
        
      str += '<div class="modal-body-inner">';

      $(".modal-body").append(str);
    },
    error: function (err) {
      alert("지원서 불러오기에 실패했습니다.");
    }
  });

  // 특정 지원자의 이미지 및 영상 불러오기
  $.ajax({
    url: "../getAudFile",
    type: "post",
    data: JSON.stringify({ audition_cv_no: audition_cv_no }),
    contentType: "application/json",
    async: false,
    success: function (arr) {

      var imgCnt = 1;
      var vidCnt = 1;
      for(let i=0; i<arr.length; i++) {
        if(arr[i].audition_cv_file_type == "image") {
          $(".audImgFile" + imgCnt).attr("src", arr[i].audition_cv_file_path);
          imgCnt++;

        } else {
          $(".audVidFile").attr("src", arr[i].audition_cv_file_path);
          vidCnt++;
        }
      }

      if(vidCnt == 1) {
        $(".vid-outer").remove();
      }

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

//오디션 1차 합격 처리
$(".passBtn").click(function(e) {

  $("#audition_cv_no").val(e.target.parentElement.parentElement.children[1].innerHTML);
	
  $("#updateAudForm").submit();
});

//오디션 1차 불합격 처리
$(".failBtn").click(function(e) {

  $("#audition_cv_no").val(e.target.parentElement.parentElement.children[1].innerHTML);

  $("#updateAudForm").attr("action", "/audition/failFirstStageForm").submit();
});

