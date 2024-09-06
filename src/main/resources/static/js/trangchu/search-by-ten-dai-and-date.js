// thuc hien chuc nang khi pick nut select thi gan du lieu vao form va thuc hien request den server
// lay the select
const select__pick__Dai = document.getElementById("select__pick--Dai");
// lay the form
const formSelect = document.getElementById("formSelect");
// lay the input giu value ten dai
const inputTenDai = document.getElementById("tenDai");
// lay the input giu gia tri value ngay
const inputDate = document.getElementById("date-xs");
// lay the input__datePicker
const input__datePicker = document.getElementById("input__datePicker");


// ham chuyen doi dinh dang ngay thang nam  "yyyy-mm-dd" thanh "dd/mm/yyyy"
function convertDateForMatSlash(inputDate){
    // Chuyển đổi ngày tháng từ "dd/mm/yyyy" thành "yyyy-mm-dd"
    var parts = inputDate.split('-');
    var formattedDate = parts[2] + '/' + parts[1] + '/' + parts[0]; // Đảo ngược thứ tự ngày và tháng

    return formattedDate;
}
// bat su kien onchange tren the select
select__pick__Dai.addEventListener("change",function(){
    // check gia tri nhan duoc khac the option co noi dung " chon dai"
    if(this.value != 1){
        // gan gia tri vao the input tenDai
        inputTenDai.value = this.value;
        // gan gia tri vao the input date-xs
        if(input__datePicker.value !=""){
            inputDate.value = convertDateFormat(input__datePicker.value);
        }else{
            inputDate.value = 1; // gui gia tri nay len server thi server biet ngay thang nam ko can dung
        }

        // thuc hien gui submit len server bang method post voi api "/us/chon-dai"
        formSelect.submit();
    }
});
// cho hien thi lai gia tri da selectlen nut select
const inputHoldDateSelect =  document.getElementById("inputHoldDateSelect");
if(inputHoldDateSelect.value){
select__pick__Dai.value = inputHoldDateSelect.value;
}
// hien thi ngay thang cho nut input ngay xo so
const inputHoldDateXs = document.getElementById("inputHoldDateXs");
if(inputHoldDateXs.value){
    document.getElementById("input__datePicker").value = convertDateForMatSlash(inputHoldDateXs.value);
}
