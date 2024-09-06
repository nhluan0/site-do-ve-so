// thuc hien 3 chuc năng
// 1. hien thi the mat khau sang định dạng ***
// 2. hiển thị lên nút select role nhận được cơ sở dữ liệu
// 3. khi click vào nút select role thì set giá trị thẻ selelect này đến nút input role
// lay the mat khau
const pwUpdate = document.getElementById("password");
console.log("the mat khau la: ",pwUpdate)
// lay the select
const selectUpdate = document.getElementById("selectRole");
// lay the input tên role
const roleUpdate = document.getElementById("role")


// 1. hien thi the mat khau sang định dạng ***
pwUpdate.value = "*******";

// 2. hiển thị lên nút select role nhận được cơ sở dữ liệu
// láy giá trị thẻ input role hiện tại
const valueRole = roleUpdate.value;
// gán giá trị cho thẻ select hiển thị
selectUpdate.value = valueRole;
console.log("role lay len duoc: ",valueRole)
if(valueRole == "ROLE_USER"){
    // hiển thị thẻ select với nội dung sau
    selectUpdate.innerHTML =`
     <option >ROLE_USER</option>
     <option >ROLE_ADMIN</option>
    `;
}else{
    // hiển thị thẻ select với nội dung sau
    selectUpdate.innerHTML =`
     <option >ROLE_ADMIN</option>
     <option >ROLE_USER</option>
    `;
}

console.log("select có giá trị ",selectUpdate.value)

// 3. khi click vào nút select role thì set giá trị thẻ select này đến nút input role
selectUpdate.addEventListener('click',function(){
    // gán giá trị vào thẻ input có id role
    roleUpdate.value = this.value;

})
