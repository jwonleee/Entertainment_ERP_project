/*<!-- 아이디 중복 검사 -->*/
function checkId2() {
    var admin_id = $('#admin_id').val(); //id값이 "id"인 입력란의 값을 저장
    $.ajax({
        url: '../idCheck2', //Controller에서 요청 받을 주소
        type: 'post', //POST 방식으로 전달
        data: { "admin_id": admin_id },
        success: function (cnt) { //컨트롤러에서 넘어온 cnt값을 받는다 
            if (cnt == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                $('.id_ok').css("display", "inline-block");
                $('.id_notok').css("display", "none");

            } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                $('.id_notok').css("display", "inline-block");
                $('.id_ok').css("display", "none");
                /* alert("아이디를 다시 입력해주세요"); */
                /*  $('#admin_id').val(''); */
            }
        },
        error: function () {
            alert("에러입니다");
        }
    });
};

/*<!-- 약관 동의 전체 선택 -->*/
function agreeContractAll() {

    $(function () {

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

//카카오 우편 API
function kakaopost() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.querySelector("#zipcode").value = data.zonecode;
            document.querySelector("#address").value = data.address;
        }
    }).open();
}

//최상단, 최단 이동 버튼
$(".top_btn").click(function () {
    $('html, body').animate({
        scrollTop: 0
    }, 400);
    return false;
});

$(".bottom_btn").click(function () {
    $('html, body').animate({
        scrollTop: document.body.scrollHeight
    }, 400);
    return false;
});

// admin_contact 조합 구문
var c1 = document.querySelector(".region_num");
var c2 = document.querySelector(".phone_num1");
var c3 = document.querySelector(".phone_num2");

var adminContact = document.querySelector(".adminContact");
function contactCombine() {
    adminContact.value = c1.value + "-" + c2.value + "-" + c3.value;
}
c1.addEventListener("change", contactCombine);
c2.addEventListener("change", contactCombine);
c3.addEventListener("change", contactCombine);

// // admin_gender 값 추출
// function getAdminGender() {
//     var adminGender = document.getElementsByName("adminGender");
//     var admin_gender = document.querySelector(".admin_gender");

//     adminGender.forEach((v) => {
//         if (v.checked) {
//             admin_gender.value = v.value;
//         }
//     });
// }
// $(".adminGender").change(getAdminGender);
// $(document).ready(getAdminGender);

// // admin_type 값 추출
// function getAdminType() {
//     var adminType = document.getElementsByName("adminType");
//     var admin_type = document.getElementById("admin_type");

//     adminType.forEach((v) => {
//         if (v.checked) {
//             admin_type.value = v.value;
//         }
//     });
// }
// $(".adminType").change(getAdminType);
// $(document).ready(getAdminType);

// // admin_address 값 추출
// function getAddress() {
//     $(".admin_address").val($(".admin_address1").val() + " " + $(".admin_address2").val());
// }
// $(".admin_addresses").change(getAddress);
// $(document).ready(getAddress);

// console.log($(".adminType").is(":checked"));

//매니저 선택 시, 담당 연예인 선택 필수 처리
// $(".adminType").change(function () {
//     console.log($(".adminType").is(":checked"));
// });

//비밀번호 일치 확인
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