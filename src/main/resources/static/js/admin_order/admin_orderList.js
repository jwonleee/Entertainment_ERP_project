//오늘시간 yyyy-mm-dd로
var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
var day = date.getDate();
month = month >= 10 ? month : '0' + month;
day = day >= 10 ? day : '0' + day;
var today = year + "-" + month + "-" + day;

//재고 변화시 화면에 가격 출력
$('input[name="admin_order_prod_cnt"]').on("propertychange change paste input",function(){
	var albumPrice = $("#albumPrice").val();
    var prodPrice = $("#prodPrice").val();
     if ($("#albumPrice").val() == undefined) {//상품이면
    	 $('input[name="admin_order_price"]').val(prodPrice*$('input[name="admin_order_prod_cnt"]').val());
     } else if ($("#prodPrice").val() == undefined) {//앨범이면
    	 $('input[name="admin_order_price"]').val(albumPrice*$('input[name="admin_order_prod_cnt"]').val());
     }
});




function drawModal(admin_order_album_no, admin_order_prod_no, admin_order_no) {
    //모달창 켜졌을 때 외부 스크롤 막기
    $("body").css("overflowY", "hidden");
    //이전 내용 지우기
    $(".detailTable").html('');
    //모달창 켜졌을 때 구별용 hidden input value값 지우기
    $("#forCheckAlbum").val(0);
    $("#forCheckProduct").val(0);

    //입력칸 토글
    $(".insertToggle").toggleClass("td_show_none");

    if ($("#three_btn").hasClass("td_show_none")) {
        $("#three_btn").toggleClass("td_show_none");
    }
    if ($("#two_btn").hasClass("td_show_none") == false) {
        $("#two_btn").toggleClass("td_show_none");
    }

    //구별용
    var detailTitle = admin_order_prod_no == 0 ? `앨범상세` : `상품상세`;
    $("#modalTitle").html(detailTitle);

    var str = '';

    //admin관련 영역 뿌리기
    $.ajax({
        url: "/getAdmin/" + admin_order_no,
        type: "get",
        async: false,
        success: (result) => {
            str += `<tbody id="admin_order_tbx" class="tbx">`;
            str += `<input type="hidden" value="${result.admin_order_id}" name="admin_order_id" readonly/>`;
            str += `<input type="hidden" value="${result.admin_order_album_no}" name="admin_order_album_no" readonly/>`;
            str += `<input type="hidden" value="${result.admin_order_prod_no}" name="admin_order_prod_no" readonly/>`;
            str += `<tr>`;
            str += `<th>`;
            str += `<div class="th">발주일자</div>`;
            str += `</th>`;
            str += `<td><div id="adminDate_td">`;
            str += `<div id="adminDate_div" class="insertToggle">${result.admin_order_regdate}</div>`;
            str += `<input type="text" readonly required="required" value="${today}" name="admin_order_regdate" class="insertToggle td_show_none"/>`;
            str += `</div></td>`;
            str += `</tr>`;
            str += `<tr>`;
            str += `<th>`;
            str += `<div class="th">카테고리</div>`;
            str += `</th>`;
            str += `<td><div id="adminCategory_td">${result.admin_order_category}`;
            str += `<input type="hidden" value="${result.admin_order_category}" name="admin_order_category"/></div</td>`;
            str += `</tr>`;
            str += `<tr>`;
            str += `<th>`;
            str += `<div class="th">발주가격</div>`;
            str += `</th>`;
            str += `<td><div id="adminPrice_td">`;
            str += `<div id="adminPrice_div" class="insertToggle">${result.admin_order_price}원</div>`;
            str += `<input type="text" value="" name="admin_order_price" placeholder="상품가격 * 재고" readonly required="required" class="insertToggle td_show_none"/>`;
            str += `</div></td>`;
            str += `</tr>`;
            str += `<tr>`;
            str += `<th>`;
            str += `<div class="th">발주량</div>`;
            str += `</th>`;
            str += `<td><div id="adminProdCnt_td">`;
            str += `<div class="insertToggle">${result.admin_order_prod_cnt}개</div>`;
            str += `<input type="number" value="${result.admin_order_prod_cnt}" name="admin_order_prod_cnt" oninput="handleInputLength(this, 8)" required="required" class="insertToggle td_show_none"/>`;
            str += `</div></td>`;
            str += `</tr>`;
            str += `<tr>`;
            str += `<th>`;
            str += `<div class="th">발주회사</div>`;
            str += `</th>`;
            str += `<td><div id="adminCompany_td">`;
            str += `<div id="adminCompany_div" class="insertToggle">(주)${result.admin_order_company}</div>`;
            str += `<input type="text" value="${result.admin_order_company}" name="admin_order_company" required="required" class="insertToggle td_show_none"/>`;
            str == `</div></td>`;
            str += `</tr>`;
            str += `</tbody>`;
            if (admin_order_prod_no == 0) {//앨범이라면
                $.ajax({
                    url: "/getAlbum/" + admin_order_album_no,
                    type: "get",
                    async: false,
                    success: (result) => {
                        $("#forCheckAlbum").val(result.album_no);
                        str += `<tbody id="album_tbx" class="tbx">`;
                        str += `<input type="hidden" value="${result.album_title}" name="admin_order_prod_name" readonly required="required"/>`;
                        str += `<input type="hidden" value="${result.album_version}" name="admin_order_sizetype" required="required" readonly/>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">앨범번호</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumNo_td">${result.album_no}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">타이틀</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumTitle_td">${result.album_title}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">아티스트</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumArtist_td">${result.album_artist}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">앨범이미지</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumImg_td"><img src="${result.album_img_path}"/></div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">앨범버전</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumVersion_td">${result.album_version}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">발매일</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumDate_td">${result.album_release_date}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">앨범가격</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumPrice_td">${result.album_price}`;
                        str += `<input type="hidden" id="albumPrice" value="${result.album_price}"/></div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">할인율</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumDiscount_td">${result.album_discount_rate}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">현재재고</div>`;
                        str += `</th>`;
                        str += `<td><div id="albumStock">${result.album_stock}</div></td>`;
                        str += `</tr>`;
                        str += `</tbody>`;
                        $(".detailTable").append(str);
                        $(".modal").fadeIn();
                        $(".modal").scrollTop(0); //모달창 켜졌을 때 화면 스크롤이 위로 가도록.
                    },
                    error: (err) => {
                        alert('앨범 조회 실패!');
                        return false;
                    }
                })
            } else {//상품이라면
                $.ajax({
                    url: "/getProduct/" + admin_order_prod_no,
                    type: "get",
                    async: false,
                    success: (result) => {
                        $("#forCheckProduct").val(result.prod_no);
                        str += `<tbody id="prod_tbx" class="tbx">`;
                        str += `<input type="hidden" value="${result.prod_name}" name="admin_order_prod_name" readonly required="required"/>`;
                        str += `<input type="hidden" value="${result.prod_sizetype}" name="admin_order_sizetype" required="required" readonly/>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">상품번호</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodNo_td">${result.prod_no}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">상품명</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodName_td">${result.prod_name}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">엔터테이너</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodArtist">${result.prod_artist}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">상품이미지</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodImg_td"><img src="${result.prod_img_path}"/></div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">상품타입</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodType_td">${result.prod_type}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">상품사이즈</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodSizeType_td">${result.prod_sizetype}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">등록일</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodDate_td">${result.prod_regdate}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">상품가격</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodPrice_td">${result.prod_price}`;
                        str += `<input type="hidden" id="prodPrice" value="${result.prod_price}"/></div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">할인율</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodDiscount_td">${result.prod_discount_rate}</div></td>`;
                        str += `</tr>`;
                        str += `<tr>`;
                        str += `<th>`;
                        str += `<div class="th">현재재고</div>`;
                        str += `</th>`;
                        str += `<td><div id="prodStock_td">${result.prod_stock}</div></td>`;
                        str += `</tr>`;
                        str += `</tbody>`;
                        $(".detailTable").append(str);
                        $(".modal").fadeIn();
                        $(".modal").scrollTop(0); //모달창 켜졌을 때 화면 스크롤이 위로 가도록.
                    },
                    error: (err) => {
                        alert('상품 조회 실패!');
                        return false;
                    }
                });
            }
        },
        error: (err) => {
            alert('발주정보 조회 실패!')
            return false;
        }
    })
};


//모달창 닫기
$("#close_btn").click(() => {
    $("body").css("overflowY", "auto");
    $(".modal").fadeOut();
});

$("#close_btn2").click(() => {
    $("body").css("overflowY", "auto");
    $(".modal").fadeOut();
});

$(".modal_close").click(() => {
    $("body").css("overflowY", "auto");
    $(".modal").fadeOut();
});




/***********수정버튼***********/
$("#modify_btn").click(() => {
    if ($("#forCheckAlbum").val() != 0) {//앨범이라면
        $("#handleForm").attr("action", "/order/albumDetail").submit();
    } else if ($("#forCheckProduct").val() != 0) {//상품이라면
        $("#handleForm").attr("action", "/order/productDetail").submit();
    }
});


//자리수제한
function handleInputLength(el, max) {
    if (el.value.length > max) {
        el.value = el.value.substr(0, max);
    }
};


/****************************************************************************************************************** */
/****추가발주****/
$("#additional_btn").click(() => {
    alert("상품 가격과 할인율은 상세페이지에서 수정 가능합니다.");
    $(".modal").scrollTop(0); //스크롤이 위로 가도록.
    $(".insertToggle").toggleClass("td_show_none");//보여주기에서 입력으로 전환
});



//추가발주넣기
$("#additional_confirm_btn").click(()=>{
	//유효성검사
	//해당부분으로 이동
    //$(".modal").animate({scrollTop:$("#adminCategory_td").offset().top},500);
    
    
    var albumPrice = $("#albumPrice").val();
    var prodPrice = $("#prodPrice").val();
    
     
     
     if ($("#albumPrice").val() == undefined) {//상품이면
         $('input[name="admin_order_price"]').val(prodPrice*$('input[name="admin_order_prod_cnt"]').val());
     } else if ($("#prodPrice").val() == undefined) {//앨범이면
         $('input[name="admin_order_price"]').val(albumPrice*$('input[name="admin_order_prod_cnt"]').val());
     }
	
});





