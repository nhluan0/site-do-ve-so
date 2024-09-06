const queryString = window.location.search; // lay tham so tren URL
const msgContent = document.querySelector(".msg--content");
console.log(queryString.length);

// ham hien thi tin nhan trang thai xoa thanh cong hay that bai
function messageStatusDelete(){
    msgContent.style.display ="block";
    msgContent.style.animationName="slideDown"
    // reset lai thuoc tinh animationName = none cho element div class = msg--content
    setTimeout(()=>{
        msgContent.style.animationName="none"
        msgContent.style.display ="none";
        msgContent.innerHTML = "";
    },2000);
}

// check tham so Url co chua tin nhan hay khong neu khong cho animation-name = none
// neu co thi add property animationName = slideDown
if (  msgContent.innerHTML != ""){
    messageStatusDelete();
    console.log(msgContent.innerHTML)

}




