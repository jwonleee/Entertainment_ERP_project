
/*
		<div>
			<a th:href="@{'/product/'+ ${list[0].prod_artist}}"  class="celeb_button"> <!-- 연예인 별 상품 페이지로 이동 -->
				<span>여기 아티스트 이름</span>
				<span style="font-size: 14px; font-style: italic;">right now</span>
			</a>	
		</div>
				
		<div>
			<ul class="celebGoods_box_list1" th:each="vo:${list}" th:if="${vo.prod_artist == 'NEWJEANS'}">
				<li>
					<img style="width:240px; height:240px" th:src="@{${vo.prod_img_path}}"/>
				 </li>
			</ul>
						
		 </div>*/


/* 셀럽 굿즈 파트 */

/*const celeb =['NEWJEANS', 'BLACKPINK', 'IVE', 'LEEDOHYUN', 'CHAESOOBIN'];

	var str ='';
	
	str += '<div>';
		str += '<a th:href="@{'/product/'+ ${list[0].prod_artist}}"  class="celeb_button">';
			str += '<span style ="font-size: 16px;>';
				str += 'event.target.innerHTML';
			str += '</span>';
			str += '<span style ="font-size: 14px; font-style: italic;">';
				str += 'right now';
			str += '</span>';
		str += '</a>';
	str += '</div>';
		
	$(".celebGoods_box2").append(str);
	
	for(var i =0; i < celeb.length; i++){
		var str2 = '';
		str2  += '<div>';
			str2 += '<ul class="celebGoods_box_list1" th:each="vo:${list}" th:if="${vo.prod_artist == celeb[' + i + ']>';
			 	str2 += '<li>';
					str2 += '<img style="width:240px; height:240px" th:src="@{${vo.prod_img_path}}">';
				str2 += '</li>';
			str2 += '</ul>';
		str2 += '</div>';
		
	}
		$(".celebGoods_box2").append(str2);
*/


/*about goods 누르면 전체 목록 나오게 구현*/

$(".about_goods > span ").click(function(){
	$(".allProduct_list").css("display", "block")
	$(".allProduct_list1").css("display", "none")
	
})

/* 판매량순, 최신순 기능*/
$(".product_classfication").on("click", "span", function(){


	if(event.target.innerHTML == '최신순'){
		$(".allProduct_list1").css("display", "block")
		$(".allProduct_list").css("display", "none")
	}
});