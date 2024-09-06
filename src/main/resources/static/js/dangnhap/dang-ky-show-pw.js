// thuc hien hien/ an password khi nhấn vào icon hình mắt
// lay icon hinh mat
const icon__eye = document.getElementById("icon-eye");
// lay the input password
const inputPassword = document.getElementById("input__pw");
// lay icon hinh mat co gach cheo
const icon__eye__slash = document.getElementById("icon-eye-slash");

// khi click vao icon hinh mat
icon__eye.addEventListener("click",function(){
        inputPassword.type = "text";
        this.style.display = "none";
        icon__eye__slash.style.display = "block";

})
icon__eye__slash.addEventListener("click",function(){
        inputPassword.type = "password";
        this.style.display = "none";
        icon__eye.style.display = "block";

})