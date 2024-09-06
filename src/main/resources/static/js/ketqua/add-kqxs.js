// 3: nếu đang  trang thêm thì cho hiển thị nút select tên đài mặc định là Phú Yên
// lay gia tri nut current hien tai
const tenDaiCurrent = document.getElementById("tenDaiCurrent");
if(currentPath == "/ad/add-kqxs" ){
    selectTenDai.value = "Phú Yên";
    inputTenDai.value = "Phú Yên";
}
if(currentPath == "/ad/them_kq_xs"){
    if(tenDaiCurrent.value == ""){
        selectTenDai.value = "Phú Yên";
        inputTenDai.value = "Phú Yên";
    }else{
        selectTenDai.value = tenDaiCurrent.value;
        inputTenDai.value = tenDaiCurrent.value;
    }
}