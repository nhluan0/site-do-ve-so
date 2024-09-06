// check gia trị trường input có bỏ trống hay không nếu có thì hiện lên thông báo
// lấy thẻ input
const username = document.getElementById("username");
// lấy thẻ form
const form = document.getElementsByTagName("form");

form[0].addEventListener("submit",e=>{
    if(username){
        if(username.value == ""){
            e.preventDefault();
            alert("Email hoặc tên đăng nhập không được bỏ trống");
        }
    }
})

