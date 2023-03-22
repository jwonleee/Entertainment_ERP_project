//오늘시간 yyyy-mm-dd로
var date = new Date();
var year = date.getFullYear();
var month = date.getMonth() + 1;
var day = date.getDate();
month = month >= 10 ? month : '0' + month;
day = day >= 10 ? day : '0' + day;
var today = year + "-" + month + "-" + day;

$("#datepick1").val(today);


/*취소버튼*/
$("#redirectList").click(() => {
    if (confirm("변경사항이 저장되지 않습니다. 목록으로 돌아가시겠습니까?")) {
        location.href = "orderList";
    }
});


var entertainer = ""; //카테고리에서 꺼낼 연예인

/*카테고리*/
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
    $(e.target).category_set();
};


//카테고리세팅
$.fn.category_set = function () {
    var category_id = this.data("set").category_id;
    var category_group_id = this.data("set").category_group_id;
    $("input[name='admin_order_category']").val(category_group_id + category_id);
    $("input[name='admin_order_category']").data('dnm',this.data("set").category_detail_nm);
    $("input[name='admin_order_category']").data('clv',this.data("set").category_lv);

    if (this.data("set").category_lv == 2) {
        entertainer = this.data("set").category_detail_nm;
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

var detailCnt = 0;//카테고리 선택 확인용 변수

//카테고리 클릭시 활성화
$(".categoryListWrap").on("click", ".forOn>a", (e) => {
    $(e.currentTarget).closest('li').addClass("categoryOn");
    $(e.currentTarget).closest('li').siblings().removeClass("categoryOn");
    detailCnt = 0;
    $(".tbx").remove();
});

//상세등록창 띄우기
$(".detail_btn").click((e) => {
    detailCnt = 0;
    var categorySelected = $("input[name='admin_order_category']").val();

    //상품타입넣기
    var categoryDetailName = ''; //상세이름. 앨범,케이스 등등
    categoryDetailName=$("input[name='admin_order_category']").data("dnm");
    var categoryLevel=''; //대중소 레벨. 1,2,3
    categoryLevel=$("input[name='admin_order_category']").data("clv");

    //사이즈넣기
    var sizesml = ['S', 'M', "L"];
    var sizephone = ["iPhone 14 Pro Max", "iPhone 14 Pro", "iPhone 14 Plus", "iPhone 14", "iPhone 13", "iPhone 13 mini", "iPhone 12"];
    var version = ["싱글", "미니", "정규"];
    $("#adCategory").val(categorySelected);//admin용 카테고리 저장
    if (categoryLevel != '3') {
        alert('상세카테고리를 선택하세요');
        $(e.currentTarget).detail_remove();
    } else {
        $(e.currentTarget).detail_remove();
        detailCnt += 1;
        if (categoryDetailName=='앨범') { //앨범일 때
            var astr = '';
            astr += '<tbody class="tbx">';
            astr += `<tr>`;
            astr += `<th>카테고리</th>`;
            astr += `<td><input type="text" required="required" readonly name="album_category" value="${categorySelected}"/></td>`;
            astr += `</tr>`;
            astr += '<tr>';
            astr += '<th>아티스트</th>';
            astr += `<td><input type="text" required="required" readonly name="album_artist" value="${entertainer}"/></td>`;
            astr += '</tr>';
            astr += `<tr>`;
            astr += `<th>앨범명</th>`;
            astr += `<td><input type="text" required="required" name="album_title"/></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>발매일</th>`;
            astr += `<td><input type="text" id="datepick2" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" placeholder="날짜는 yyyy-MM-dd로 입력하세요" required="required" name="album_release_date"/></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>가격</th>`;
            astr += `<td><input type="number" required="required" name="album_price" placeholder="숫자만 입력 가능합니다."/></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>할인율</th>`;
            astr += `<td><input type="number" min="0" max="99" required="required" name="album_discount_rate" oninput="handleInputLength(this, 2)" placeholder="0~99 사이의 숫자만 입력 가능합니다."/></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>재고</th>`;
            astr += `<td><input type="number" required="required" name="album_stock" oninput="handleInputLength(this, 8)" placeholder="숫자만 입력 가능합니다."/></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>앨범버전</th>`;
            astr += `<td><input type="hidden" value="싱글" name="album_version"/>`;
            astr += `<select id=selectAlbumBox class="versionAlbum">`;
            version.forEach((item) => { astr += `<option th:value="${item}">${item}</option>` });
            astr += `</select></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>이미지업데이트</th>`;
            astr += `<td><button id="showModal" class="btn-modal">이미지 업로드</button>`
            astr += `<input type="text" required="required" readonly name="albumImgPath" style="width:77.6%"/></td>`;
            astr += `</tr>`;
            astr += `<tr>`;
            astr += `<th>회사</th>`;
            astr += `<td>`;
            astr += `<input type="text" name="admin_order_company" required="required"/>`;
            astr += `</td>`;
            astr += `</tr>`;
            astr += `</tbody>`;
            $('.regTable').append(astr);
        } else {//앨범이 아닌 상품일 때
            var pstr = '';
            pstr += '<tbody class="tbx">';
            pstr += `<tr>`;
            pstr += `<th>카테고리</th>`;
            pstr += `<td><input type="text" required="required" readonly name="prod_category" value="${categorySelected}"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>연예인</th>`;
            pstr += `<td><input type="text" required="required" readonly name="prod_artist" value="${entertainer}"/></td>`;
            pstr += `</tr>`;
            pstr += '<tr>';
            pstr += '<th>상품타입</th>';
            pstr += `<td><input type="text" required="required" name="prod_type" readonly value="${categoryDetailName}"/></td>`;
            pstr += '</tr>';
            pstr += `<tr>`;
            pstr += `<th>상품명</th>`;
            pstr += `<td><input type="text" required="required" name="prod_name"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>사이즈</th>`;
            if (categoryDetailName == "옷") {
                pstr += `<td>`;
                pstr += `<select id="selectbox" name="prod_sizetype" class="selectVal">`;
                sizesml.forEach((item) => { pstr += `<option th:value="${item}">${item}</option>` });
                pstr += `</select></td>`;
            } else if (categoryDetailName == "케이스") {
                pstr += `<td>`;
                pstr += `<select id="selectbox" name="prod_sizetype" class="selectVal">`;
                sizephone.forEach((item) => { pstr += `<option th:value="${item}">${item}</option>` });
                pstr += `</select></td>`;
            }else{
                pstr += `<td><input type="text" readonly value="기본사이즈" class="selectVal" name="prod_sizetype"/></td>`;
			}
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>등록일</th>`;
            pstr += `<td><input type="text" id="datepick3" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" placeholder="날짜는 yyyy-MM-dd로 입력하세요" required="required" name="prod_regdate"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>가격</th>`;
            pstr += `<td><input type="number" id="pd_price" required="required" name="prod_price" placeholder="숫자만 입력 가능합니다."/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>할인율</th>`;
            pstr += `<td><input type="number" min="0" max="99" placeholder="0~99 사이의 숫자만 입력 가능합니다." required="required" name="prod_discount_rate" oninput="handleInputLength(this, 2)"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>재고</th>`;
            pstr += `<td><input type="number" required="required" name="prod_stock" placeholder="숫자만 입력 가능합니다." oninput="handleInputLength(this, 8)"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>이미지경로</th>`;
            pstr += `<td><button id="showModalP" class="btn-modal">이미지 업로드</button>`;
            pstr += `<input type="text" required="required" readonly name="prodImgPath" style="width:77.6%"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>상세설명이미지</th>`;
            pstr += `<td><button id="showModalPD" class="btn-modal">상세이미지 업로드</button>`;
            pstr += `<input type="text" required="required" readonly name="prodInfoImgPath" style="width:74.3%"/></td>`;
            pstr += `</tr>`;
            pstr += `<tr>`;
            pstr += `<th>회사</th>`;
            pstr += `<td>`;
            pstr += `<input type="text" name="admin_order_company" required="required"/>`;
            pstr += `</td>`;
            pstr += `</tr>`;
            pstr += '</tbody>';
            $('.regTable').append(pstr);
        }
    }
});


//이전 상세등록창 지우기
$.fn.detail_remove = function () {
    $(".tbx").remove();
};

//자리수제한
function handleInputLength(el, max) {
    if (el.value.length > max) {
        el.value = el.value.substr(0, max);
    }
};



/*달력*/
$(".regTable").on("focus", "#datepick2",
    function () {
        $("#datepick2").datepicker({ minDate: 0, dateFormat: "yy-mm-dd" });
    });
$(".regTable").on("focus", "#datepick3",
    function () {
        $("#datepick3").datepicker({ minDate: 0, dateFormat: "yy-mm-dd" });
    });

/*발주버튼*/
$("#submitOrder").click((e) => {
    // console.log(e.currentTarget); submit input태그
    e.preventDefault(); //고유이벤트제거


    //유효성검사
    var categorySelected = $("input[name='admin_order_category']").val();  //카테고리 선택값
    var categoryDetailName = ''; //상세이름. 앨범,케이스 등등
    categoryDetailName=$("input[name='admin_order_category']").data("dnm");
    var categoryLevel=''; //대중소 레벨. 1,2,3
    categoryLevel=$("input[name='admin_order_category']").data("clv");
    var datePattern = RegExp('[0-9]{4}-[0-9]{2}-[0-9]{2}'); //날짜형식확인
    var numPattern = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi; //숫자형식확인
    if ($("input[name='admin_order_regdate']").val() == '') {
        alert('발주일자를 입력하세요!');
        $("html, body").animate({ scrollTop: $("input[name='admin_order_regdate']").offset().top }, 400);
        $("input[name='admin_order_regdate']").focus();
        return; //함수종료
    } else if (!datePattern.test($("input[name='admin_order_regdate']").val())) {
        alert('발주일자 양식을 확인하세요!');
        $("html, body").animate({ scrollTop: $("input[name='admin_order_regdate']").offset().top }, 400);
        $("input[name='admin_order_regdate']").focus();
        return; //함수종료
    } else if (categorySelected == '') {
        alert('카테고리를 선택하세요!');
        $(".categoryList").focus($(".categoryList").css("animation", "fadeIn 1s ease-in-out"));
        return; //함수종료
    } else if (categoryLevel!='3') {
        alert('상세카테고리를 선택하세요!');
        $(".categoryList").focus($(".categoryList").css("animation", "fadeIn 1s ease-in-out"));
        return; //함수종료
    } else if ($(".tbx").length == 0) {
        alert("상세발주조회를 선택하세요!");
        $("#detailSubBtn").focus($("#detailSubBtn").css("animation", "fadeIn 1s ease-in-out"));
        return; //함수종료
    } else if (detailCnt == 0) {
        alert("카테고리를 확인하세요!");
        $(".categoryList").focus($(".categoryList").css("animation", "fadeIn 1s ease-in-out"));
        return; //함수종료
    } else {
        if (categoryDetailName=='앨범') { //앨범일 때
            if ($("input[name='album_title']").val() == '') {
                alert("앨범명을 입력하세요!");
                $("input[name='album_title']").focus();
                return; //함수종료
            } else if ($("input[name='album_release_date']").val() == '') {
                alert("발매일을 입력하세요!");
                $("input[name='album_release_date']").focus();
                return; //함수종료
            } else if (!datePattern.test($("input[name='album_release_date']").val())) {
                alert("발매일 양식을 확인하세요!");
                $("input[name='album_release_date']").focus();
                return; //함수종료
            } else if ($("input[name='album_price']").val() == '' || numPattern.test($("input[name='album_price']").val())) {
                alert("가격을 확인하세요! 숫자만 입력 가능합니다.");
                $("input[name='album_price']").focus();
                return; //함수종료
            } else if ($("input[name='album_discount_rate']").val() == '' || numPattern.test($("input[name='album_discount_rate']").val())) {
                alert("할인율을 확인하세요! 숫자만 입력 가능합니다.");
                $("input[name='album_discount_rate']").focus();
                return; //함수종료
            } else if ($("input[name='album_stock']").val() == '' || numPattern.test($("input[name='album_stock']").val())) {
                alert("재고를 확인하세요! 숫자만 입력 가능합니다.");
                $("input[name='album_stock']").focus();
                return; //함수종료
            } else if ($("input[name='album_version']").val() == '' || $(".versionAlbum").val() == '') {
                alert("앨범버전을 선택하세요!");
                $("input[name='album_version']").focus();
                return; //함수종료
            } else if ($("input[name='albumImgPath']").val() == '') {
                alert("이미지를 업데이트하세요!");
                $("#showModal").focus($("#showModal").css("animation", "fadeIn 1s ease-in-out"));
                return; //함수종료
            } else if ($("input[name='admin_order_company']").val() == '') {
                alert("회사를 입력하세요!");
                $("input[name='admin_order_company']").focus();
                return; //함수종료
            } else {
                $("#adCnt").val($("input[name='album_stock']").val()); // admin_order_prod_cnt 넣기
                $("input[name='album_version']").val($(".versionAlbum").val());// 앨범버전 넣기
                $("#adSizetype").val($("input[name='album_version']").val());//admin 앨범버전 넣기
                $("#adPrice").val($("input[name='album_price']").val() * $("input[name='album_stock']").val()); //가격 넣기
                $("#adName").val($("input[name='album_title']").val()); //admin_order_prod_name 넣기
            }
        } else {//상품일 때
            if ($("input[name='prod_type']").val() == '') {
                alert("상품타입을 입력하세요!");
                $("input[name='prod_type']").focus();
                return; //함수종료
            } else if ($("input[name='prod_name']").val() == '') {
                alert("상품명을 입력하세요!");
                $("input[name='prod_name']").focus();
                return; //함수종료
            } else if ($("input[name='prod_sizetype']").val() == '' || $(".selectVal").val() == '') {
                alert("사이즈타입을 입력하세요!");
                $("input[name='prod_sizetype']").focus();
                return; //함수종료
            } else if ($("input[name='prod_regdate']").val() == '') {
                alert("등록일을 입력하세요!");
                $("input[name='prod_regdate']").focus();
                return; //함수종료
            } else if (!datePattern.test($("input[name='prod_regdate']").val())) {
                alert("등록일 양식을 확인하세요!");
                $("input[name='prod_regdate']").focus();
                return; //함수종료
            } else if ($("input[name='prod_price']").val() == '' || numPattern.test($("input[name='prod_price']").val())) {
                alert("가격을 확인하세요! 숫자만 입력 가능합니다.");
                $("input[name='prod_price']").focus();
                return; //함수종료
            } else if ($("input[name='prod_discount_rate']").val() == '' || numPattern.test($("input[name='prod_discount_rate']").val())) {
                alert("할인율을 확인하세요! 숫자만 입력 가능합니다.");
                $("input[name='prod_discount_rate']").focus();
                return; //함수종료
            } else if ($("input[name='prod_stock']").val() == '' || numPattern.test($("input[name='prod_price']").val())) {
                alert("재고를 확인하세요! 숫자만 입력 가능합니다.");
                $("input[name='prod_stock']").focus();
                return; //함수종료
            } else if ($("input[name='prodImgPath']").val() == '') {
                alert("이미지경로를 입력하세요!");
                $("#showModalP").focus($("#showModalP").css("animation", "fadeIn 1s ease-in-out"));
                return; //함수종료
            } else if ($("input[name='prodInfoImgPath']").val() == '') {
                alert("상세설명이미지를 입력하세요!");
                $("#showModalPD").focus($("#showModalPD").css("animation", "fadeIn 1s ease-in-out"));
                return; //함수종료
            } else if ($("input[name='admin_order_company']").val() == '') {
                alert("회사를 입력하세요!");
                $("input[name='admin_order_company']").focus();
                return; //함수종료
            } else {
                $("#adCnt").val($("input[name='prod_stock']").val()); // admin_order_prod_cnt 넣기
                $("#adSizetype").val($(".selectVal").val());//사이즈/타입넣기
                $("#adPrice").val($("input[name='prod_price']").val() * $("input[name='prod_stock']").val())//가격 넣기
                $("#adName").val($("input[name='prod_name']").val()); //admin_order_prod_name 넣기
            }
        }
    }; //유효성검사 끝


    //aws업데이트하기
    //앨범 또는 상품이미지 aws에 업로드
    if (categoryDetailName=='앨범') { //앨범일 때 
        var formData = new FormData();
        var file = $("#imgReg");
        formData.append("file", file[0].files[0]);
        $.ajax({
            url: "/s3/resource",
            method: "post",
            data: formData,
            contentType: false, //보내는 데이터 타입 multipart/form-data로
            processData: false, //폼데이터가 name=값&형식으로 변경되는 것 막기
            async: false,
            success: (result) => {
                $('input[name="album_img_path"]').val(result.path);
            },
            error: (err) => {
                alert('이미지 업로드에 실패하였습니다.')
                return;
            }
        });
    } else {//상품일 때	
        //상품이미지 등록
        var formData = new FormData();
        var file = $("#imgReg");
        formData.append("file", file[0].files[0]);
        //상품상세설명 이미지 등록
        var detailFile = $("#PDimgReg");
        formData.append("PDfile", detailFile[0].files[0]);
        $.ajax({
            url: "/s3/resource2",
            method: "post",
            data: formData,
            contentType: false, //보내는 데이터 타입 multipart/form-data로
            processData: false, //폼데이터가 name=값&형식으로 변경되는 것 막기
            async: false,
            success: (result) => {
                $("input[name='prod_img_path']").val(result.pimg.path);
                $("input[name='prod_info_img_path']").val(result.pdimg.path);
            },
            error: (err) => {
                alert('상세설명 이미지 업로드에 실패하였습니다.')
                return;
            }
        });

    }

    //form보내기
    //document.registForm.submit();
    $("#registForm").attr("action", "/order/registForm").submit();
});


/******앨범, 상품 등록 모달******/
$('.regTable').on("click", function (e) {
	
    e.preventDefault();//form태그 안의 button이므로 고유이벤트 제거
    $("#showModal").click(() => { $(".modal").fadeIn() });
    $(".modal_close").click(() => { $(".modal").fadeOut() });
    
    $("#showModalP").click(() => { $(".modal").fadeIn() });
    $(".modal_close").click(() => { $(".modal").fadeOut() });
    
    ///상세설명이미지 모달
    $("#showModalPD").click(() => { $(".modal2").fadeIn() });
    $(".modal_close").click(() => { $(".modal2").fadeOut() });
    
});




/***이미지 미리보기****/
$("#imgReg").on('change', function (event) {
    $("#preview").attr('src', '');
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        $("#preview").attr('src', e.target.result);
    }
    reader.readAsDataURL(file);
    $("#imgDiv").css('display', 'block');
});

/***상세설명 이미지 미리보기****/
$("#PDimgReg").on('change', function (event) {
    $("#previewPD").attr('src', '');
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        $("#previewPD").attr('src', e.target.result);
    }
    reader.readAsDataURL(file);
    $("#imgDivPD").css('display', 'block');
});

/***이미지 등록***/
$("#imgUpload").click(function () {
	
	var categoryDetailName = ''; //상세이름. 앨범,케이스 등등
    categoryDetailName=$("input[name='admin_order_category']").data("dnm");

    var categorySelected = $("input[name='admin_order_category']").val();
    var obj = $("#imgReg");
    var pathpoint = obj.val().lastIndexOf('.');
    var filepoint = obj.val().substring(pathpoint + 1, obj.val().length);
    var filetype = filepoint.toLowerCase();

    var check_file_type = new Array();
    check_file_type = ['jpg', 'gif', 'png', 'jpeg', 'bmp'];
    if (check_file_type.indexOf(filetype) == -1) {//이미지파일인지 검사
        alert('이미지 파일만 선택가능합니다.');
        return;
    } else {
        if (categoryDetailName=='앨범') {
            /*앨범일 때*/
            $("input[name='albumImgPath']").val($("#imgReg").val().split('/').pop().split('\\').pop());//fakepath지우기
            $(".modal").fadeOut();
        } else {
            /*** 상품일 때***/
            $("input[name='prodImgPath']").val($("#imgReg").val().split('/').pop().split('\\').pop());//fakepath지우기
            $(".modal").fadeOut();
        }
    }
});

//상세설명이미지 등록
$("#PDimgUpload").click(function () {
    var obj = $("#PDimgReg");
    var pathpoint = obj.val().lastIndexOf('.');
    var filepoint = obj.val().substring(pathpoint + 1, obj.val().length);
    var filetype = filepoint.toLowerCase();
    var check_file_type = new Array();
    check_file_type = ['jpg', 'gif', 'png', 'jpeg', 'bmp', 'webp'];
    if (check_file_type.indexOf(filetype) == -1) {//이미지파일인지 검사
        alert('이미지 파일만 선택가능합니다.');
        return;
    } else {
        $("input[name='prodInfoImgPath']").val($("#PDimgReg").val().split('/').pop().split('\\').pop());//fakepath지우기
        $(".modal2").fadeOut();
    }
});
