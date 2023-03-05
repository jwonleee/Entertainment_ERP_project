/*달력*/
$(function () {
    $("#datepicker").datepicker(
        { showButtonPanel: true }
    );
    $("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd")
});

/*금액표시*/
$(document).ready(() => {
    const input = document.querySelector('#price');
    input.addEventListener('keyup', function(e) {
      let value = e.target.value;
      value = Number(value.replaceAll(',', ''));
      if(isNaN(value)) {         //NaN인지 판별
        input.value = 0;   
      }else {                   //NaN이 아닌 경우
        const formatValue = value.toLocaleString('ko-KR');
        input.value = formatValue;
      }
    })
});

/*취소버튼*/
$("#rediList").click(()=>{
	if(confirm("변경사항이 저장되지 않습니다. 목록으로 돌아가시겠습니까?")){
		location.href="orderList";
	}
})

