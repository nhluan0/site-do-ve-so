
// co 3 chuc nang
// 1: lay gia tri trong the input an giu ten dai va cho hien thi gia tri len nut select
// 2: khi nguoi dung nhan vao nut select thi tu dong dien gia tri nguoi dung chon dien vao gia tri the input ten dai an
// 3: nếu đang  trang thêm thì cho hiển thị nút select tên đài mặc định là Phú Yên
// lay nut select hien thi ten dai
const selectTenDai = document.getElementById("selectDaiTen");
// lay nut input giu ten dai
const inputTenDai = document.getElementById("input_tenDai");

// lay pathUrl hiện tại
var currentPath = window.location.pathname;

// 1: lay gia tri trong the input an giu ten dai va cho hien thi gia tri len nut select
selectTenDai.value = inputTenDai.value;
// 2: khi nguoi dung nhan vao nut select thi tu dong dien gia tri nguoi dung chon dien vao gia tri the input ten dai an
selectTenDai.addEventListener('click',()=>{
    inputTenDai.value = selectTenDai.value;
})


