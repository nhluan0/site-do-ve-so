// thuc hien 2 chuc năng
// 1: doc du lieu the input an set giá trị này cho thẻ select
// 2: khi giá trị select thay đổi thì set giá trị ngược lại cho thẻ input ẩn
// lấy thẻ select
const selectAria = document.getElementById("selectAria");
// lấy thẻ input ẩn chứa value khu vực
const inputKhuVuc = document.getElementById("khuVuc");

// 1: doc du lieu the input an set giá trị này cho thẻ select
selectAria.value = inputKhuVuc.value;

// 2: khi giá trị select thay đổi thì set giá trị ngược lại cho thẻ input ẩn
selectAria.addEventListener("change",function(){

    inputKhuVuc.value = this.value;
})