/* 체크박스 전체 선택 */
function checkAll() {
	$(function() {

		var checked = $('#check_all').is(':checked');

		if (checked) {
			$("input:checkbox").prop("checked", true)
		} else {
			$("input:checkbox").prop("checked", false)
		}

	});
};

/* 전체 선택 후 선택된 체크박스 해제하면 전체 동의 체크박스가 해제됨 */
function checkOne() {
	$("#check_all").prop("checked", false);
};

/* 장바구니 상품 수량 버튼 */
function quantity_add() {

	var result = parseInt($(event.target).prev().val());
	var result2 = parseInt($(event.target).parent().next().find("input").val());
	var total = parseInt($(".total_price").html());
	var prod_price = parseInt($(".cart_prod_price").val());
	total += result2;
	
	$(event.target).prev().val(++result);
	$(".total_price").html(total + "원");
	console.log($(".cart_prod_price").val()*result);
}

function quantity_minus() {

	var quantity = parseInt($(event.target).next().val());
	var quantity2 = parseInt($(event.target).parent().next().find("input").val());
	var total = parseInt($(".total_price").html());
	total -= quantity2;

	if (quantity > 1) {
		$(event.target).next().val(--quantity);
		$(".total_price").html(total + "원");
		console.log($(".cart_prod_price").val()*quantity);
	}
}

/* 장바구니 상품별 삭제 */
function deleteCartOne() {

	if (confirm("정말 삭제하시겠습니까?")) {
		var cart_no = $(event.target).val();

		$.ajax({
			type: "POST",
			url: "../deleteCartOne",
			dataType: "json",
			data: { "cart_no": cart_no },
			success: function(data) {
				
				if (data == 1) {
					alert("삭제되었습니다");
					location.reload();
				}
			},
			error: function() {
				alert("삭제 실패");
			}
		});

	}
}

/* 장바구니 선택 상품 삭제 */
function checkedDelCart() {

	if (confirm("선택한 상품을 삭제하시겠습니까?")) {
		var checkArr = new Array();
		$("input[class='check']:checked").each(function() {
			checkArr.push($(this).attr("value"));
		});

		$.ajax({
			type: "post",
			url: "../checkedDelCart",
			dataType: "json",
			data: { "check": checkArr },
			success: function(result) {
				if (result == 1) {
					alert("선택한 상품이 삭제되었습니다")
					location.reload();
				}
			},
			error: function() {
				alert("삭제 실패");
			}
		});
	}
}

/* 장바구니 개별 상품 주문 */
function orderOne(){
	confirm("해당 상품을 주문하시겠습니까?")
}

function checkedOrder(){
	confirm("선택한 상품을 주문하시겠습니까?")
}




