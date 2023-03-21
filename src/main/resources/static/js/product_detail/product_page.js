
/* 검색창 기능 */
$('.productSearch_box img').click(function(){
	
	var all = $(this).prev('input').val();

	$('#AllprodSearch').val(all);
	console.log(all);
		
	if($('#AllprodSearch').val() == null || $('#AllprodSearch').val() == ''){
		alert("검색어를 입력해주세요");	
	}
	$('#prod_search').submit();

});

/*해시태그 버튼 기능*/
$(".prod_hashTag_button").on("click", "span", function(){
	var h = $(this).data('value');
	$('#AllprodSearch').val(h);
	$('#prod_search').submit();
})


/* 최신순, 판매량순 버튼 기능*/
$(".product_classification").on("click", "span", function(){
	$('#prod_classify').val(event.target.innerHTML);
	$('#prod_search').submit();
});



/*about goods 누르면 전체 목록 나오게 구현*/
$(".about_goods > span ").click(function(){
$('.allProduct_list').val();
$('#prod_search').submit();
});


/*var menuHeight = document.querySelector(".menu").offsetHeight;
var location = document.querySelector("#move").offsetTop;
window.scrollTo({top:location - menuHeight, behavior:'smooth'});
*/