//목록버튼 -목록으로
$(".toList_btn").click(()=>{
    $(location).attr("href","/order/orderList");
});

//자리수제한
function handleInputLength(el, max) {
    if (el.value.length > max) {
        el.value = el.value.substr(0, max);
    }
};

//submit
$("#album_modify_btn").click(()=>{
    if(confirm("수정하시겠습니까?")){
        $("#modifyAlbumForm").attr("action", "/order/albumModify").submit();
    }
})