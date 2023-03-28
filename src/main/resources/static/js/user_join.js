/* 카카오 우편번호 API  */
function kakaopost() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.querySelector("#zipcode").value = data.zonecode;
			document.querySelector("#address").value = data.address
		}
	}).open();
}

/*<!-- 약관 동의 전체 선택 -->*/
function agreeContractAll() {
	$(function() {

		var checked = $('#check_all').is(':checked');

		if (checked) {
			$("input:checkbox").prop("checked", true)
		} else {
			$("input:checkbox").prop("checked", false)
		}

	});
};

/*$(".contract_content input").prop("checked", this.checked)*/
/* 전체 선택 후 선택된 체크박스 해제하면 전체 동의 체크박스가 해제됨 */
function agreeContract() {
	$("#check_all").prop("checked", false);
};



/*<!-- 아이디 중복 검사 -->*/

function checkId() {
	var user_id = $('#user_id').val(); //id값이 "id"인 입력란의 값을 저장
	$.ajax({
		url: '../idCheck', //Controller에서 요청 받을 주소
		type: 'post', //POST 방식으로 전달
		data: { "user_id": user_id },
		success: function(cnt) { //컨트롤러에서 넘어온 cnt값을 받는다 
			if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
				$('.id_ok').css("display", "inline-block");
				$('.id_notok').css("display", "none");

			} else { // cnt가 1일 경우 -> 이미 존재하는 아이디
				$('.id_notok').css("display", "inline-block");
				$('.id_ok').css("display", "none");
				/* alert("아이디를 다시 입력해주세요"); */
				/*  $('#user_id').val(''); */
			}
		},
		error: function() {
			alert("에러입니다");
		}
	});
};

/* 비밀번호 일치 확인 */
var pw = document.getElementById("pw")
var pw2 = document.getElementById("pw2");

function validatePassword() {
	if (pw.value != pw2.value) {
		pw2.setCustomValidity("비밀번호가 일치하지 않습니다");
	} else {
		pw2.setCustomValidity(''); // 오류가 없으면 메시지를 빈 문자열로 설정해야한다. 오류 메시지가 비어 있지 않은 한 양식은 유효성 검사를 통과하지 않고 제출되지 않는다.
	}
}
pw.onchange = validatePassword;
pw2.onkeyup = validatePassword;


/* 이메일 인증 발송 */
function checkEmail() {
	var user_email = $('#user_email').val();
	$.ajax({
		type: "POST",
		url: "user/emailConfirm",
		data: {
			"user_email": user_email
		},
		success: function() {
			alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
			/*console.log("data : "+data);
			chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt);*/
		}
	})
}
/* 상단, 하단 버튼 jQuery */

$(".top_btn").click(function() {
	$('html, body').animate({
		scrollTop: 0
	}, 400);
	return false;
});

$(".bottom_btn").click(function() {
	$('html, body').animate({
		scrollTop: document.body.scrollHeight
	}, 400);
	return false;
});

// admin_contact 조합 구문
var c1 = document.querySelector(".region_num");
var c2 = document.querySelector(".phone_num1");
var c3 = document.querySelector(".phone_num2");

var userContact = document.querySelector(".userContact");
function contactCombine() {
    userContact.value = c1.value + "-" + c2.value + "-" + c3.value;
}
c1.addEventListener("change", contactCombine);
c2.addEventListener("change", contactCombine);
c3.addEventListener("change", contactCombine);


