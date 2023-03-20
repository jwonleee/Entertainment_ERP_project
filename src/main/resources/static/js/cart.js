/* 체크박스 전체 선택 */
function checkAll(){
	
    $(function(){
		
		var checked = $('#check_all').is(':checked');
		
		if(checked){
			$("input:checkbox").prop("checked", true)
		} else{
			$("input:checkbox").prop("checked", false)
		}
        
    });
};

/* 전체 선택 후 선택된 체크박스 해제하면 전체 동의 체크박스가 해제됨 */
function checkOne(){
  	$("#check_all").prop("checked", false);
};

/* 장바구니 상품 수량 버튼 */
function quantity(){
	$(function(){
		var quantity = $(".prod_quantity").val();
		
		$(".quantity_add").on("click", function(){
			$(".prod_quantity").val(++quantity);
		});
		
		$(".quantity_subtract").on("click", function(){
			if(quantity>1){
				$(".prod_quantity").val(--quantity);
			}
		});
	})
}


