$(function () {
    $("#datepicker").datepicker(
        { showButtonPanel: true }
    );
    $("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd")
});

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


