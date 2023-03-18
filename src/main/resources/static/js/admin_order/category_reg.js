
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



//카테고리 select박스
$("#category_reg_selector").change(() => {
    var str = '';//테이블용
    CateGroupId = [];//배열 초기화
    $(".exp_h5").remove();//설명창 지우기
    $.ajax({
        url: "../get_category",
        type: "get",
        success: function (result) {
            if ($("#category_reg_selector").val() == 'big') {//대분류 카테고리 선택시
                str += `<tbody id="big" class="tbx">`;
                str += `<input type="hidden" name="category_lv" value="1"/>`;
                str += `<input type="hidden" name="category_nm" value="대분류"/>`;
                str += `<input type="hidden" name="category_parent_lv" value="0"/>`;
                str += `<input type="hidden" name="category_detail_parent_lv" value="0"/>`;
                str += `<tr>`;
                str += `<th>등록할 대분류 카테고리</th>`;
                str += `<td><input type="text" name="category_detail_nm"/></td>`;
                var gstr = '';
                var lstr = '';
                result.forEach((item) => {
                    gstr = `<input type="hidden" name="category_group_id" value="${String.fromCharCode(item.category_group_id.charCodeAt() + 1)}"/>`;//아스키코드로 바꾸고 +1 후 문자열로 변환
                    lstr = `<input type="hidden" name="category_detail_lv" value="${item.category_detail_lv + 1}"/>`;
                })
                str += gstr;
                str += lstr;
                str += `</tr>`;
                str += `</tbody>`;
                $("#reg_table_sector").html(str);//내용그리기
            } else if ($("#category_reg_selector").val() == 'middle') {//중분류 카테고리 선택시
                str += `<tbody id="middle" class="tbx">`;
                str += `<input type="hidden" name="category_group_id" value=""/>`
                str += `<input type="hidden" name="category_lv" value="2"/>`;
                str += `<input type="hidden" name="category_nm" value="중분류"/>`;
                str += `<input type="hidden" name="category_parent_lv" value="1"/>`;
                str += `<input type="hidden" name="category_detail_parent_lv" value=""/>`
                str += `<tr>`;
                str += `<th>대분류</th>`;
                str += `<td>`;
                result.forEach((item) => {
                    str += `<input type="radio" name="radio_big" data-set=${JSON.stringify(item)}>${item.category_detail_nm}`;
                })
                str += `</td>`;
                str += `</tr>`;
                str += `<tr>`;
                str += `<th>등록할 중분류 카테고리</th>`;
                str += `<td><input type="text" name="category_detail_nm"/></td>`;
                str += `</tr>`;
                str += `</tbody>`;
                $("#reg_table_sector").html(str);//내용그리기
            }
        }, //end success
        error: function (err) {
            alert("카테고리 조회 실패! 담당자에게 문의하세요.")
        }
    })//end first ajax
    if ($("#category_reg_selector").val() == 'small') {//소분류 카테고리 선택시
        str += `<tbody id="small" class="tbx">`;
        str += `<input type="hidden" name="category_lv" value="3"/>`;
        str += `<input type="hidden" name="category_nm" value="소분류"/>`;
        str += `<input type="hidden" name="category_parent_lv" value="2"/>`;
        str += `<tr>`;
        str += `<th>중분류</th>`;
        str += `<td>`;

        str += `</td>`;
        str += `<tr>`;
        str += `<tr>`;
        str += `<th>등록할 소분류 카테고리</th>`;
        str += `<td><input type="text" name="category_detail_nm"/></td>`;
        str += `<tr>`;
        str += `</tbody>`;
        $("#reg_table_sector").html(str);//내용그리기
    } else if ($("#category_reg_selector").val() == 'none') {//대중소 카테고리 이외를 선택했을 때
        str += `<h5 class="exp_h5">등록할 카테고리를 선택하세요</h5>`;
        $("#reg_table_sector").html(str);//내용그리기
    }





});


//submit
$("#category_submit_btn").click(() => {
        if ($("#category_reg_selector").val() == 'big') {//대분류 카테고리 등록시
        
            $("#category_reg_form").attr("action", "/order/bigRegForm").submit();
        } else if ($("#category_reg_selector").val() == 'middle') {//중분류 카테고리 선택시
        	var data=$("input[name='radio_big']:checked").data("set");
        	$("input[name='category_group_id']").val(data.category_group_id)
        	$("input[name='category_detail_parent_lv']").val(data.category_detail_lv)
        	
        	$("#category_reg_form").attr("action", "/order/midRegForm").submit();
        }else if($("#category_reg_selector").val() == 'none'){
		}
});


