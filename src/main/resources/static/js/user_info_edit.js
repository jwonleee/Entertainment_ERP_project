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
		alert("비밀번호가 일치하지 않습니다");
		location.reload();
	} else {
		pw2.setCustomValidity(''); // 오류가 없으면 메시지를 빈 문자열로 설정해야한다. 오류 메시지가 비어 있지 않은 한 양식은 유효성 검사를 통과하지 않고 제출되지 않는다.
	}
}
/*pw.onsubmit = validatePassword;*/
pw2.oninvalid = validatePassword;

/* user_contact 조합 구문 */
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

