//권한 신청 승인 처리
$(".approveBtn").click(function(e) {

  $("#authority_mng_no").val(e.target.parentElement.parentElement.children[1].innerHTML);
  $("#authority_mng_admin_no").val(e.target.parentElement.parentElement.children[2].innerHTML);
  $("#authority_mng_admin_apply_type").val(e.target.parentElement.parentElement.children[5].innerHTML.split(" - ")[0]);
  
  // if(e.target.parentElement.parentElement.children[5].innerHTML.split(" - ")[0] == "manager") {
  //   $("#ent_name").val(e.target.parentElement.parentElement.children[5].innerHTML.split(" - ")[1]);
  // }

  $("#updateAuthForm").submit();
});

//권한 신청 반려 처리
$(".rejectBtn").click(function(e) {

  $("#authority_mng_no").val(e.target.parentElement.parentElement.children[1].innerHTML);
  $("#authority_mng_admin_no").val(e.target.parentElement.parentElement.children[2].innerHTML);

  $("#updateAuthForm").attr("action","/authority/rejectAuthForm").submit();
});



