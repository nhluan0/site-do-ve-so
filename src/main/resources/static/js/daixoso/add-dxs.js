// thuc hien 2 chuc năng
// 1: doc du lieu the select sau đó set giá trị này cho thẻ input an
// 2: khi giá trị select thay đổi thì set giá trị ngược lại cho thẻ input ẩn
// lấy thẻ select
const selectAria1 = document.getElementById("selectAria");
// lấy thẻ input ẩn chứa value khu vực
const inputKhuVuc1 = document.getElementById("khuVuc");

// 1: doc du lieu the select sau đó set giá trị này cho thẻ input an
if(inputKhuVuc1.value == ''){ // trường hợp load lần đầu tiên
    inputKhuVuc1.value = selectAria1.value;
}else{ // trường hợp xử lý load lại khi xảy ra lỗi
    selectAria1.value = inputKhuVuc1.value;
}

// 2: khi giá trị select thay đổi thì set giá trị ngược lại cho thẻ input ẩn
selectAria1.addEventListener("change",function(){

    inputKhuVuc1.value = this.value;
})