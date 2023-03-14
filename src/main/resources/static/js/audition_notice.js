// 지원서 접수 버튼 클릭
$('#audApplyBtn').click(function() {
  $("#audApply").submit();
});

//이미지 미리보기
$(document).ready(function() {

	// var fileTarget = $('.upload-hidden');
	// fileTarget.on('change', function(){
	// 	if(window.FileReader){ // modern browser 
	// 		var filename = $(this)[0].files[0].name;
	// 	} else { // old IE 
	// 		var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	// 	} // 추출한 파일명 삽입 
	// 	$(this).siblings('.upload-name').val(filename);
	// });

	var imgTarget = $('.upload-file'); 
	imgTarget.on('change', function() { 
		var parent = $(this).parent(); 
		// parent.children('.upload-display').remove(); 
		
		if(window.FileReader){ //image 파일만 
// console.log($(this)[0].files[0].type); // image/jpeg, video/mp4
			if (!$(this)[0].files[0].type.match(/image\//) && !$(this)[0].files[0].type.match(/video\//)) return; 

			var reader = new FileReader(); 
			reader.onload = function(e){ 
				var src = e.target.result; 
        
        console.log(e.target);
        console.log(e.target.result);

				// parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>'); 
        parent.find(".upload-thumb-wrap").children().attr("src", src);
        parent.find(".upload-thumb-wrap").css("border", "none");
        parent.find(".upload-thumb-wrap").children().css({width: "150px", height: "180px"});
			} 
			reader.readAsDataURL($(this)[0].files[0]); 
		} else { 
			$(this)[0].select(); 
			$(this)[0].blur(); 
			var imgSrc = document.selection.createRange().text; 
			parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>'); 

			var img = $(this).siblings('.upload-display').find('img'); 
			img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")"; 
		} 
	});

});

