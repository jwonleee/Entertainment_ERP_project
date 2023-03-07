//날짜 형식 0000-00-00 으로 변경
function changeDateFormat(regdate) {

  function pad(number) {
    if (number < 10) {
      return '0' + number;
    }
    return number;
  }

  var date = new Date(regdate);

  return date.getFullYear() + "-" + (pad(date.getMonth() + 1)) + "-" + pad(date.getDate());
}



