
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

/* 더보기 버튼 기능 구현 */
$("#more").click(function(){

	const endIndex = $(".allProduct_list .allProduct_box").length;
	


	let str = "";
	$.ajax({
		url:'/getProduct',
		type: 'post',
		dataType : 'json',
		data : {
			"endIndex" : endIndex
		},
		success:function(result){

		 
			console.log("더보기에 성공하셨습니다!");
		 result.forEach(function(item, index){
			 str += `<a href="/product/detail_page?prod_no=${item.prod_no}">`;	 
				 str += `<div class="allProduct_list">`;
				   str += `<div class="allProduct_list_img" >`;
				     str += `<img style="width: 230px; height: 230px;" src="${item.prod_img_path}"/>`;
				   str += `</div>`;
				   str += `<div class="allProduct_list_info_box">`;
				     str += `<div class="allProduct_list_info">`;
				       str += `<span>${item.prod_name}</span>`;
				       str += `<p id="prod_price" style="color:rgba(227, 3, 3, 0.897)" >${item.prod_price}원</p>`;
				     str += `</div>`;
				    str += `</div>`
				 str += `</div>`; 
			 str += `</a>`;
		 }) /*반복문의 끝*/
		
		
  			$("#more").fadeOut();  
				
			$(".allProduct").append(str);
  			  
			
		},
		error:function(err){
			alert("더보기에 실패하셨습니다!");
		}
		
	})
})


/*var menuHeight = document.querySelector(".menu").offsetHeight;
var location = document.querySelector("#move").offsetTop;
window.scrollTo({top:location - menuHeight, behavior:'smooth'});
*/