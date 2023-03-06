/*달력*/
$(function () {
    $("#datepick").datepicker(
        { showButtonPanel: true }
    );
    $("#datepick").datepicker("option", "dateFormat", "yy-mm-dd")
});

/*취소버튼*/
$("#redirectList").click(()=>{
	if(confirm("변경사항이 저장되지 않습니다. 목록으로 돌아가시겠습니까?")){
		location.href="orderList";
	}
})

/*카테고리*/
// 화면 로딩시 대분류 카테고리 생성
$(document).ready(()=>{
		$.ajax({
			url:"../getCategory",
			type:"get",
			success:function(result){
				var str='';
				str+='<ul class="categoryList" style="position:relative;" onclick="getAllCategory(event);">';
					result.forEach((item)=>{str+='<li><a href="#" data-set='+JSON.stringify(item)+'>'+item.category_detail_nm+'</a></li>';});
				str+='</ul>';

				$(".categoryListWrap").append(str);
			},
			error:function(err){
				alert("카테고리 조회 실패! 담당자에게 문의하세요.")
			}
		})
	});

//카테고리 생성
function getAllCategory(e){
    e.preventDefault(); //고유 이벤트 중지
    if(e.target.tagName !='A') return; //태그 검증하기
    var dcate=$(e.target).data("set"); //jquery 데이터셋

    //카테고리 세분화
    if(dcate.category_lv == 1 || dcate.category_lv == 2){ //대분류, 중분류일때만
        $(e.currentTarget).category_remove(); //이전 카테고리 삭제
        console.log(dcate);
        $.ajax({
            url:"../getCategoryChild/"+dcate.category_group_id+"/"+dcate.category_lv+"/"+dcate.category_detail_lv,
            type:"get",
            success:function(result){category_create(result)},
            error:function(err){alert("카테고리 조회 실패! 담당자에게 문의하세요.")}
        })
    }
    $(e.target).category_set();

};

//카테고리세팅
$.fn.category_set=function(){
    var category_id=this.data("set").category_id;
    var category_group_id=this.data("set").category_group_id;
    $("input[name='admin_order_prod_category']").val(category_group_id+category_id);
};

//이전 카테고리 삭제
$.fn.category_remove=function(){
    while(this.next().length!=0){
        $(this).next().remove();
    }
};


//다음카테고리 생성
function category_create(result){
    var category="";
    category+='<ul class="categoryList" style="position:relative;" onclick="getAllCategory(event);">';
    result.forEach((item)=>{category+='<li><a href="#" data-set='+JSON.stringify(item)+'>'+item.category_detail_nm+'</a></li>'});
    category+='</ul>';
    $(".categoryListWrap").append(category);
};

