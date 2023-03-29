/*카카오 우편번호 API*/
function kakaopost() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.querySelector("#zipcode").value = data.zonecode;
			document.querySelector("#address").value = data.address
		}
	}).open();
}

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