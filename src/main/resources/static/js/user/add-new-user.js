// thuc hien 2 chuc nang
// 1. sau khi load đầu tiên đọc giá trị hiện tại thẻ select điền vào value thẻ input role
// 2. khi click chọn vào nút select sẽ điền vào thẻ input role ẩn

// lay the select
const selectAdd = document.getElementById("selectRole");
// lay the input tên role
const roleAdd = document.getElementById("role")

// 1. sau khi load đầu tiên đọc giá trị hiện tại thẻ select điền vào value thẻ input role
roleAdd.value = selectAdd.value;
// 2. khi click chọn vào nút select sẽ điền vào thẻ input role ẩn
selectAdd.addEventListener('click',function(){
    // gán giá trị vào thẻ input có id role
    roleAdd.value = this.value;

})