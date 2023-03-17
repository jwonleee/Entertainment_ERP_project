
//예시 카테고리 생성
// 화면 로딩시 대분류 카테고리 생성
$(document).ready(() => {
    $.ajax({
        url: "../get_category",
        type: "get",
        success: function (result) {
            var str = '';
            str += '<ul class="categoryList" onclick="getAllCategory(event);">';
            result.forEach((item) => { str += '<li class="forOn"><a href="#" data-set=' + JSON.stringify(item) + '>' + item.category_detail_nm + '</a></li>'; });
            str += '</ul>';
            $(".categoryListWrap").append(str);
        },
        error: function (err) {
            alert("카테고리 조회 실패! 담당자에게 문의하세요.")
        }
    })
});


//카테고리 생성
function getAllCategory(e) {
    e.preventDefault(); //고유 이벤트 중지
    if (e.target.tagName != 'A') return; //태그 검증하기
    var dcate = $(e.target).data("set"); //jquery 데이터셋
    //카테고리 확인용
    if (dcate.category_lv == 1) $("#category_count").val(1);
    if (dcate.category_lv == 2) $("#category_count").val(2);
    if (dcate.category_lv == 3) $("#category_count").val(3);




    //카테고리 세분화
    if (dcate.category_lv == 1 || dcate.category_lv == 2) { //대분류, 중분류일때만
        $(e.currentTarget).category_remove(); //이전 카테고리 삭제
        $.ajax({
            url: "../get_category_child/" + dcate.category_group_id + "/" + dcate.category_lv + "/" + dcate.category_detail_lv,
            type: "get",
            success: function (result) { category_create(result) },
            error: function (err) { alert("카테고리 조회 실패! 담당자에게 문의하세요.") }
        })
    }
};



//이전 카테고리 삭제
$.fn.category_remove = function () {
    while (this.next().length != 0) {
        $(this).next().remove();
    }
};


//다음카테고리 생성
function category_create(result) {
    var category = "";
    category += '<ul class="categoryList" onclick="getAllCategory(event);">';
    result.forEach((item) => { category += '<li class="forOn"><a href="#" data-set=' + JSON.stringify(item) + '>' + item.category_detail_nm + '</a></li>' });
    category += '</ul>';
    $(".categoryListWrap").append(category);
};

//카테고리 클릭시 활성화
$(".categoryListWrap").on("click", ".forOn>a", (e) => {
    $(e.currentTarget).closest('li').addClass("categoryOn");
    $(e.currentTarget).closest('li').siblings().removeClass("categoryOn");

    //카테고리 설명창 변화
    if ($("#category_count").val() == 1) {//대분류
        $("#categoryLV1").addClass("category_explain_on");
        if ($("#categoryLV1").siblings().hasClass("category_explain_on")) $("#categoryLV1").siblings().removeClass("category_explain_on");
    } else if ($("#category_count").val() == 2) {//중분류
        $("#categoryLV2").addClass("category_explain_on");
        if ($("#categoryLV2").siblings().hasClass("category_explain_on")) $("#categoryLV2").siblings().removeClass("category_explain_on");
    } else if ($("#category_count").val() == 3) {//소분류
        $("#categoryLV3").addClass("category_explain_on");
        if ($("#categoryLV3").siblings().hasClass("category_explain_on")) $("#categoryLV3").siblings().removeClass("category_explain_on");

    }
});



//대분류카테고리
$("#category_reg_selector").change(() => {
    if ($("#category_reg_selector").val() == 'big') {//대분류 카테고리 선택시
        $.ajax({
            url: "../get_category",
            type: "get",
            success: function (result) {
				/*
				var str='';
				str += `<tbody id="big">`;
				str += 	`<tr>`;
				str += 	`<th>대분류</th>`;
				str += 	`<td><input type="text" />`;
				str += 	`<td>`;
				str += 	`</tr>`;
				str += 	`</tbody>`;
				*/

                result.forEach((item) => {
					 console.log(item.category_group_id.charCodeAt()) 
					 console.log(String.fromCharCode(item.category_group_id.charCodeAt()+1)) 
					})
					
					
            },
            error: function (err) {
                alert("카테고리 조회 실패! 담당자에게 문의하세요.")
            }
        })
    }
})



