/* 주문 배송/ 환불 정보 / 리뷰 탭..*/
$(".productTabBox").on("click", "li", function(){
	
	if(event.target.innerHTML == "환불 및 교환 정보"){
		$(".changeContent").css("display", "block")
		$(".orderContent").css("display", "none")
		$(".reviewContent_wrap").css("display", "none")
	}else if (event.target.innerHTML == "리뷰"){
		$(".reviewContent_wrap").css("display", "block")
		$(".changeContent").css("display", "none")
		$(".orderContent").css("display", "none")
	}else{
		$(".orderContent").css("display", "block")
		$(".reviewContent_wrap").css("display", "none")
		$(".changeContent").css("display", "none")
	}
});
