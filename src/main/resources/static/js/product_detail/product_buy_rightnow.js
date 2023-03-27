

/* 이전 페이지로 이동 */
$(".next_icon").click(function(){
	history.back();
})
	
/* 결제 수단 */
$(".payment_real_method").on("click","li", function(){
	
	if($(this).hasClass('active')){
	  $(this).removeClass('active');
	}else{
		$(".payment_real_method li").each(function(){
			$(this).removeClass('active');
		
		})
		$(this).addClass('active');
		
	}
	

});

/* 기존 배송지 버튼 / 새로운 배송지 버튼 */

$("#shipping_chk").on("click", "input", function(){
	
	if($(this).val()=="same"){ //기존 배송지 버튼 클릭하면
		$("#receiver").val($("#user_name").val()); //주문자
		$("#zipcode").val($("#user_zipcode").val()); //우편번호
		var address = $("#user_address").val().split(','); //배송지 주소
		$("#address").val(address[0]);
		$("#address2").val(address[1]);
		var phone_number = $("#user_contact").val().split('-');
		$("#phone_front").val(phone_number[1]); //주문자 핸드폰번호
		$("#phone_back").val(phone_number[2]); 
	
		
	}else{
		$("#receiver").focus();
		$("#receiver").val(""); 
		$("#zipcode").val("");
		$("#address").val("");
		$("#address2").val("");
		$("#phone_front").val(""); 
		$("#phone_back").val("");
	}
	
})

/*결제 수단*/
$(".payment_real_method").on("click", "li" , function(){
	console.log(event.target.innerHTML);
	if(event.target.innerHTML == "카드 결제"){
		$("#payment_method").val(event.target.innerHTML);
	}else if(event.target.innerHTML == "카카오페이"){
		$("#payment_method").val(event.target.innerHTML);
	}else{
		$("#payment_method").val(event.target.innerHTML);
	}
	
})



	
// 수신인 연락처 데이터 조합
var c1 = document.querySelector(".phone_option");
var c2 = document.querySelector("#phone_front");
var c3 = document.querySelector("#phone_back");

var user_orderContact = document.querySelector("#user_orderContact");
function contactCombine() {
    user_orderContact.value = c1.value + "-" + c2.value + "-" + c3.value;
}
c1.addEventListener("change", contactCombine);
c2.addEventListener("change", contactCombine);
c3.addEventListener("change", contactCombine);