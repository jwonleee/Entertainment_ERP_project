// //이미지 수정버튼을 클릭했을 때 modalOn();
// $(".modalOn").click(function(e) { //jquery 사용
//     e.preventDefault(); //a링크의 고유이벤트 중지
    
//     //ajax - 이미지데이터 조회 (prod_id 기준으로 조회)
//     //1. 클릭한 대상의 prod_id값
//     var prod_id = $(e.target).closest("td").next().html();
//     //console.log(prod_id) 확인
    
//     //2. post방식으로 img데이터 조회
//     $.ajax({
//         url: "../getProductImg",
//         type: "post",
//         data: JSON.stringify({prod_id: prod_id}), //제이슨 데이터
//         contentType: "application/json", //보내는 데이터에 대한 타입
//         success: function(result){
//             //console.log(result); //반환된 데이터
            
//             var str = "";
//             var arr = ['a', 'b', 'c']; //배열 생성
            
//             for(var i = 0; i < result.length; i++) {
//                 str += '<div class="left">';
//                 str += '<span>추가이미지</span>';
//                 str += '<label class="upload-display" for="' + arr[i] +'_file">';
//                 str += '<span class="upload-thumb-wrap">';
//                 str += '<img class="upload-thumb" src="' + '../display' + '/' + result[i].filepath + '/' + result[i].uuid + '/' + result[i].filename + '">'; /* 상대경로, 한단계 위로 올라가서 display... */
//                 str += '</span>';
//                 str += '</label>';
//                 str += '<input class="upload-name" value="파일선택" disabled="disabled">';
//                 str += '<input type="file" name="file" id="' + arr[i] + '_file" class="upload-hidden">';
//                 str += '<input type="hidden" value="">';
//                 str += '<input type="hidden" value="">';
//                 str += '<button type="button" class="normal_btn" style="display: block;">삭제</button>';
                
//                 /* 다운로드 기능 추가 */
//                 //1번 방법 - a 링크로 바꿔서 보내기
//                 //str += '<a href="'+ '../download' + '/' + result[i].filepath + '/' + result[i].uuid + '/' + result[i].filename +'" class="normal_btn" style="display: block;">다운로드</a>';
                
//                 //2번 방법 - js로 onclick 사용
//                 //str += '<button type="button" class="normal_btn" style="display: block;" onclick="location.href=`'+'../download/' + result[i].filepath + '/' + result[i].uuid + '/' + result[i].filename +'`">다운로드</button>';
                
//                 str += '<button type="button" class="normal_btn" style="display: block;">다운로드</button>';
                
//                 str += '</div>';
//             }
            
//                $(".filebox").html(str);
               
//                $(".filebox").on("click", "button", function(e) {
                  
//                   var id = $(e.target).prev().prev().prev().prev().attr("id").replace('_file', '');
                  
//                   var index = arr.indexOf(id);
                 
//                   e.preventDefault(); //고유이벤트 중지
//                   location.href="../download/" + result[index].filepath + "/" + result [index].uuid + "/" + result[index].filename;
//                })
            
//         }, 
//         error: function(err) {
//             alert("이미지 조회에 실패했습니다.");
//         }
//     })
    
    
// });


// 리스트 상세보기
// $(document).ready(function() {

//     $('#schedule_list tr').on(function (e) {

//         var tr = $(this);
//         var td = tr.children();
//         var schedule_no = td.eq(0).text();

//         console.log(schedule_no);

//         $.ajax({
//             url: '../getDetail',
//             type: 'post',
//             data: JSON.stringify({schedule_no:schedule_no}),
//             contentType: "application/json",
//             success: function (result) {

//                 console.log('success');

//             },
//             error: function (err) {
//                 console.log(err);
//             }
//         });
//     });
// });