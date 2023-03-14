/*<!-- 약관 동의 전체 선택 -->*/

function agreeContractAll(){
	
    $(function(){
		
		var checked = $('#check_all').is(':checked');
		
		if(checked){
			$("input:checkbox").prop("checked", true)
		} else{
			$("input:checkbox").prop("checked", false)
		}
        
    });
};

/*$(".contract_content input").prop("checked", this.checked)*/
/* 전체 선택 후 선택된 체크박스 해제하면 전체 동의 체크박스가 해제됨 */
function agreeContract(){
  	$("#check_all").prop("checked", false);
  };
  


/*<!-- 아이디 중복 검사 -->*/

function checkId(){
    var user_id = $('#user_id').val(); //id값이 "id"인 입력란의 값을 저장
    $.ajax({
        url:'../idCheck', //Controller에서 요청 받을 주소
        type:'post', //POST 방식으로 전달
        data:{"user_id":user_id},
        success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
            if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                $('.id_ok').css("display","inline-block"); 
                $('.id_notok').css("display", "none");
                
            } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                $('.id_notok').css("display","inline-block");
                $('.id_ok').css("display", "none");
                /* alert("아이디를 다시 입력해주세요"); */
               /*  $('#user_id').val(''); */
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
    };
