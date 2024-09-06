// thuc hien an hien password theo con mat
// lay the mat hien
const eyeShow = document.getElementsByClassName("eye-show");
// lay the mat an
const eyeHidden = document.getElementsByClassName("eye-hidden");
// lay cac the input password
const inputPassword = document.getElementsByClassName("input-password");

// bắt sự kiện cho từng thẻ mắt hiện
for(let i =0; i < eyeShow.length;i++){
    eyeShow[i].addEventListener("click",function(){
        // ẩn thẻ eyeShow
        this.style.display = "none";
        // chuyển đổi type input tương ứng thành loại text
        inputPassword[i].type = "password";
        // hiện thẻ mắt gạch chéo
        eyeHidden[i].style.display = "block";
    })
}
// bắt sự kiện cho từng thẻ mắt gach chéo
for(let i =0; i <eyeHidden.length;i++){
    eyeHidden[i].addEventListener("click",function(){
        // ẩn thẻ eyeHidden
        this.style.display = "none";
        // chuyển đổi type input tương ứng thành loại text
        inputPassword[i].type = "text";
        // hiện thẻ mắt gạch chéo
        eyeShow[i].style.display = "block";
    })
}