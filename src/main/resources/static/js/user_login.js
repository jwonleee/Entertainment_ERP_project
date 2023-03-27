
/* 아이디 저장 체크박스 쿠키 생성*/
window.onload = function () {

    if (getCookie("user_id")) { // getCookie함수로 user_id라는 이름의 쿠키를 불러와서 있을경우
        document.userLoginForm.user_id.value = getCookie("user_id"); //input 이름이 id인곳에 getCookie("user_id")값을 넣어줌
        document.userLoginForm.save_id.checked = true; // 체크는 체크됨으로
    }

}

//쿠키 저장함수
function setCookie(name, value, expiredays) {
    var todayDate = new Date();
    todayDate.setDate(todayDate.getDate() + expiredays);
    document.cookie = name + "=" + escape(value) + "; path=/; expires="
        + todayDate.toGMTString() + ";"
}

function getCookie(Name) { // 쿠키 불러오는 함수
    var search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length; // set index of beginning of value
            end = document.cookie.indexOf(";", offset); // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
}

function sendit() {
    var msg = document.userLoginForm;
    if (!msg.user_id.value) { //아이디를 입력하지 않으면.
        alert("아이디를 입력 해주세요");
        msg.user_id.focus();
        return;
    }
    if (!msg.user_pw.value) { //패스워드를 입력하지 않으면.
        alert("패스워드를 입력 해주세요");
        msg.user_pw.focus();
        return;
    }

    if (document.userLoginForm.save_id.checked == true) { // 아이디 저장을 체크 하였을때
        setCookie("user_id", document.userLoginForm.user_id.value, 7); //쿠키이름을 id로 아이디입력필드값을 7일동안 저장
    } else { // 아이디 저장을 체크 하지 않았을때
        setCookie("user_id", document.userLoginForm.user_id.value, 0); //날짜를 0으로 저장하여 쿠키삭제
    }

    document.userLoginForm.submit(); //유효성 검사가 통과되면 서버로 전송.

}
