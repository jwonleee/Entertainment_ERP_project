
/* 주문 배송/ 환불 정보 / 리뷰 탭..*/
$(".productTabWrap1 .productTabBox").on("click", "li", function(){
	
	if(event.target.innerHTML == "환불 및 교환 정보"){
		$(".changeContent").css("display", "block");
		$(".orderContent").css("display", "none");
	}else{
		$(".orderContent").css("display", "block");
		$(".changeContent").css("display", "none");
	}
});


/* 상품 상세 정보 中 수량 + / - 버튼 기능*/
/* 빼기 기능 */
$("#decide_cnt_minus").click(function(){
	
	if($('#decide_cnt').val() ==  1) {
		return;
	}else if($('#decide_cnt').val() != 0){
	
		var number = $('#decide_cnt').val() ;
		var n = parseInt(number) - 1;
		$('#decide_cnt').val(n);
		var total = $("#total_price").attr('value')* n;
		total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); //정규식으로 1000단위 마다 콤마 생성
		$("#total_price").text(total +'원');
		$("#total_price2").text(total+'원');
		$("#total_price2").val(total);
}
	
})
/* 더하기 기능 */
$("#decide_cnt_plus").click(function(){
	
		var number = $('#decide_cnt').val() ; 
		var n = parseInt(number) + 1; //수량 늘어날 때마다 value 바꾸기
		$('#decide_cnt').val(n);	
		var total = $("#total_price").attr('value')* n; //할인된 가격 x 수량
		total = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');//정규식으로 1000단위 마다 콤마 생성
		$("#total_price").text(total+'원');
		$("#total_price2").text(total +'원');
		$('#total_price2').val(total)
		
})

//바로 구매하기 버튼 눌렀을 때 값 보내기
$("#buyButton").click(function(){
	
	var discount=$("#discount_sum").val()*$('#decide_cnt').val();
	$("#discount_sum").val(discount);
 	if($('#decide_cnt').val()== 1){
		var newTotal = Math.floor( $("#total_price").attr('value'));
		$("#user_order_total_price").val(newTotal);		 
	}else{
		var newTotal2 = Math.floor($("#total_price").attr('value')*$('#decide_cnt').val());
		$("#user_order_total_price").val(newTotal2);
	}
})

//장바구니 버튼 눌렀을 때 데이터 보내기
$("#cartButton").click(function(){
	
	var cart_prod_name = $("#prod_name_rightnow").val();
	var cart_prod_image_path = $("#prod_img_cart").val();
	var cart_prod_cnt = $("#decide_cnt").val();
	
	var discount=$("#discount_sum").val()*$('#decide_cnt').val();
	$("#discount_sum").val(discount);
 	if($('#decide_cnt').val()== 1){
		var newTotal = Math.floor( $("#total_price").attr('value'));
		$("#user_order_total_price").val(newTotal);		 
	}else{
		var newTotal2 = Math.floor($("#total_price").attr('value')*$('#decide_cnt').val());
		$("#user_order_total_price").val(newTotal2);
	}
	
	
	var cart_prod_price = $("#user_order_total_price").val();
	
	var data = {
		
			cart_prod_name : cart_prod_name,
			cart_prod_image_path : cart_prod_image_path,
			cart_prod_price:cart_prod_price,
			cart_prod_cnt: cart_prod_cnt
		
	};
	
	$.ajax ({
		url:'/prod_addCart',
		type:'post',
		data: data,
		success:function(){
			alert("장바구니 담기가 정상적으로 완료되었습니다.")
			location.replace("/product/product_cart");
		}, error: function(){
			alert("장바구니 담기에 실패하였습니다.")
			
		}
		
	})
	
	
})