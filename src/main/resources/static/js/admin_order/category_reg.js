//예시 카테고리 생성


var bigcateDetailNameMap = [];//대분류 detail_nm 저장할 맵을 원소로 가지는 배열

var bigcateDetailNameArr = [];//대분류 detail_nm 저장할 배열
var midcateDetailNameArr = [];//중분류 detail_nm 저장할 배열


// 화면 로딩시 대분류 카테고리 생성
$(document).ready(() => {
    $.ajax({
        url: "../get_category",
        type: "get",
        async: false,
        success: function (result) {
            var str = '';
            str += '<ul class="categoryList" onclick="getAllCategory(event);">';
            result.forEach((item) => {
                str += '<li class="forOn"><a href="#" data-set=' + JSON.stringify(item) + '>' + item.category_detail_nm + '</a></li>';
                var dkey = item.category_group_id; //A B C D
                var dval = item.category_detail_nm; //가수 배우 개그맨
                var obj = {};
                obj[dkey] = dval; //{키:값}의 모양으로 넣는 것이 아님. 또한, obj.dkey도 동작하지 않는다. key를 넣을 때 객체.[]로 넣을 것.
                bigcateDetailNameMap.push(obj);
                bigcateDetailNameArr.push(item.category_detail_nm)
            });

            str += '</ul>';
            $(".categoryListWrap").append(str);
        },
        error: function (err) {
            alert("카테고리 조회 실패! 담당자에게 문의하세요.")
        }
    })
    //중복검사를 위해 중분류 가져오기
    $.ajax({
        url: "../get_mid_category",
        type: "get",
        async: false,
        success: function (result) {
            result.forEach((item) => { midcateDetailNameArr.push(item.category_detail_nm) });
        },
        error: function (err) {
            alert('중분류 카테고리를 가져오는 데 실패했습니다.')
        }
    });
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

    if ($("#category_reg_selector").val() == 'big' || $("#category_reg_selector").val() == 'middle') {//대분류만 필요할 경우.
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
    } else if ($("#category_reg_selector").val() == 'small') {//소분류 카테고리 선택시
        $.ajax({
            url: "../get_mid_category",
            type: "get",
            success: function (result) {
                str += `<tbody id="small" class="tbx">`;
                str += `<tr>`;
                str += `<th>중분류</th>`;
                str += `<td>`;
                var setArr = [];
                result.forEach((item) => {
                    str += `<input type="checkbox" class="${item.category_group_id}" value="${item.category_detail_nm}" name="check_mid" data-set=${JSON.stringify(item)}>${item.category_detail_nm}`;
                    setArr.push(item.category_group_id);
                })
                str += `<br/>`;
                str += `<input type="checkbox" id="check_all" class="partial_select"/>모두선택`;
                setArr = [...new Set(setArr)]; //배열중복제거
                bigcateDetailNameMap.forEach((item, index) => {
                    str += `<input type="checkbox" class="partial_select" name="bigCateCheck" id="${setArr[index]}" value="${setArr[index]}"/>${item[setArr[index]]}`;
                });
                str += `</td>`;
                str += `<tr>`;
                str += `<tr>`;
                str += `<th>등록할 소분류 카테고리</th>`;
                str += `<td><input type="text" name="category_detail_nm"/></td>`;
                str += `<tr>`;
                str += `</tbody>`;
                $("#reg_table_sector").html(str);//내용그리기
            },
            error: function (err) {
                alert("카테고리 조회 실패! 담당자에게 문의하세요.")
            }

        });//end second ajax
    } else if ($("#category_reg_selector").val() == 'none') {//대중소 카테고리 이외를 선택했을 때
        str += `<h5 class="exp_h5">등록할 카테고리를 선택하세요</h5>`;
        $("#reg_table_sector").html(str);//내용그리기
    }
});

//모두선택 체크박스
$("#reg_table_sector").on("click", '#check_all', () => {
    var checked = $('#check_all').is(':checked');
    if (checked) {
        $("input[name='check_mid']").prop("checked", true);//전체 소분류 체크박스
        $("input[name='bigCateCheck']").prop("checked", true);//대분류 체크박스
    } else {
        $("input[name='check_mid']").prop("checked", false);
        $("input[name='bigCateCheck']").prop("checked", false);
    }
});

//대분류별 체크박스
$("#reg_table_sector").on("click", "input[name='bigCateCheck']", (e) => {
    var checked = $(e.currentTarget).is(":checked")
    if (checked) {
        $(`.${e.currentTarget.defaultValue}`).prop("checked", true);
    } else {
        $(`.${e.currentTarget.defaultValue}`).prop("checked", false);
    }
});



//submit
$("#category_submit_btn").click(() => {
    if ($("#category_reg_selector").val() == 'big') {//대분류 카테고리 등록시
        //유효성검사
        //중복검사
        var dupleCnt = '';
        bigcateDetailNameArr.forEach((item) => { if ($("input[name='category_detail_nm']").val() == item) { dupleCnt += 1; } })
        if ($("input[name='category_detail_nm']").val() == '') {
            alert('등록할 내용을 입력하세요!');
            $("html, body").animate({ scrollTop: $("input[name='category_detail_nm']").offset().top }, 400);
            $("input[name='category_detail_nm']").focus();
            return;
        } else if (dupleCnt != 0) {//중복검사
            alert('중복된 카테고리가 존재합니다.')
            $("html, body").animate({ scrollTop: $("input[name='category_detail_nm']").offset().top }, 400);
            $("input[name='category_detail_nm']").focus();
            return;
        }
        $("#category_reg_form").attr("action", "/order/bigRegForm").submit();
    } else if ($("#category_reg_selector").val() == 'middle') {//중분류 카테고리 선택시
        //유효성검사
        var dupleCnt = '';
        midcateDetailNameArr.forEach((item) => { if ($("input[name='category_detail_nm']").val() == item) { dupleCnt += 1; } })
        if (!$("input:radio[name='radio_big']").is(':checked')) {//라디오박스 선택 검사
            alert("대분류를 선택하세요!");
            $("html, body").animate({ scrollTop: $("input:radio[name='radio_big']").offset().top }, 400);
            $("input:radio[name='radio_big']").focus();
            return;
        } else if ($("input[name='category_detail_nm']").val() == '') {
            alert('등록할 내용을 입력하세요!');
            $("html, body").animate({ scrollTop: $("input[name='category_detail_nm']").offset().top }, 400);
            $("input[name='category_detail_nm']").focus();
            return;
        } else if (dupleCnt != 0) {//중복검사
            alert('중복된 카테고리가 존재합니다.')
            $("html, body").animate({ scrollTop: $("input[name='category_detail_nm']").offset().top }, 400);
            $("input[name='category_detail_nm']").focus();
            return;
        }
        var data = $("input[name='radio_big']:checked").data("set");
        $("input[name='category_group_id']").val(data.category_group_id)
        $("input[name='category_detail_parent_lv']").val(data.category_detail_lv)
        $("#category_reg_form").attr("action", "/order/midRegForm").submit();
    } else if ($("#category_reg_selector").val() == 'small') {//소분류 카테고리 등록시
        //유효성검사
        
        //중복검사를 위해 소분류 가져오기
        var duplecnt=0;
        var midArr=[];
        $("input:checkbox[name='check_mid']:checked").each((index, item) => {
			var midObj={"category_group_id":$(item).data("set").category_group_id, "category_detail_lv":$(item).data("set").category_detail_lv}
			midArr.push(JSON.stringify(midObj));			
        });//여기까지가 컨트롤러에서 mapper를 타기 위해 필요한 값을 배열에 담는 과정.
            $.ajax({
                url: "/get_small_category",
                type: "post",
                data: {"midArr":midArr},
                traditional:true, //ajax 배열 넘기기 옵션
                async: false,
                success: function (result) {
                    result.forEach((item)=>{
						if(item==$("input[name='category_detail_nm']").val()){
							duplecnt++;
						}
					})
                },
                error: function (err) {
                    alert('소분류 카테고리를 가져오는 데 실패했습니다.')
                }
            });



        if (!$("input:checkbox[name='check_mid']").is(":checked")) {//체크박스 체크 검사. 최소 한개는 체크하기
            alert("중분류를 최소 한개 선택하세요!");
            $("html, body").animate({ scrollTop: $("input:radio[name='check_mid']").offset().top }, 400);
            $("input:radio[name='check_mid']").focus();
            return;
        } else if ($("input[name='category_detail_nm']").val() == '') {
            alert('등록할 내용을 입력하세요!');
            $("html, body").animate({ scrollTop: $("input[name='category_detail_nm']").offset().top }, 400);
            $("input[name='category_detail_nm']").focus();
            return;
        }else if(duplecnt!=0){//중복되는 소분류가 있으면
			alert('중복된 카테고리가 존재합니다. 기존 카테고리를 확인해 주세요.')
            $("html, body").animate({ scrollTop: $("input[name='category_detail_nm']").offset().top }, 400);
            $("input[name='category_detail_nm']").focus();
            return;
		}
         var successCount = 0; //등록성공 cnt
         $("input[name='check_mid']:checked").each((index, item) => {//체크된 박스 반복문
             //console.log($(item).data("set")); //dataset
             var formData = new FormData();
             formData.append("category_group_id", $(item).data("set").category_group_id);
             formData.append("category_detail_nm", $("input[name='category_detail_nm']").val());
             formData.append("category_detail_parent_lv", $(item).data("set").category_detail_lv);
 
             $.ajax({
                 url: '/small_category_reg',
                 type: 'post',
                 data: formData,
                 contentType: false, //보내는 데이터 타입 multipart/form-data로
                 processData: false, //폼데이터가 name=값&형식으로 변경되는 것 막기
                 async: false,
                 success: (result) => { successCount += result },
                 error: (err) => { alert('카테고리 등록 실패! 담당자에게 문의하세요.') }
             })
         });//end each문
         if ($("input[name='check_mid']:checked").length == successCount) {//정상등록시
             alert('등록성공!');
             location.replace('/order/orderList');
         } else {
             alert('등록실패');
         }
         
    } else if ($("#category_reg_selector").val() == 'none') {//카테고리 등록을 안했을 때
        if (confirm('작성한 내용이 없습니다. 목록으로 이동하시겠습니까?')) $(location).attr("href", "/order/orderList");
    }
});





//목록버튼 -목록으로
$("#back_to_list").click(() => {
    $(location).attr("href", "/order/orderList");
});


