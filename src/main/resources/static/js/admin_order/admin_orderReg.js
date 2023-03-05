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