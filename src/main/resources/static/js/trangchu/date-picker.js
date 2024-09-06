// datepicke
let ngayTruyenVao = ["2024-03-13", "2024-03-20", "2024-03-23", "2024-03-26"];
// ham chuyen doi dinh dang ngay thang nam "dd/mm/yyyy" thanh "yyyy-mm-dd"
function convertDateFormat(inputDate) {
    // Chuyển đổi ngày tháng từ "dd/mm/yyyy" thành "yyyy-mm-dd"
    var parts = inputDate.split('/');
    var formattedDate = parts[2] + '-' + parts[1] + '-' + parts[0]; // Đảo ngược thứ tự ngày và tháng

    return formattedDate;
}
function khoaNgay(date) {
    var stringDate = $.datepicker.formatDate('yy-mm-dd', date);
    return [ngayTruyenVao.indexOf(stringDate) != -1, '']; // Trả về false để khóa ngày không nằm trong mảng
}
// 1: hien thi datePicker
$(document).ready(function () {
    $(function () {
        $("#input__datePicker").datepicker({
            showOtherMonths:true,
            selectOtherMonths:true,
            changeMonth:true,
            changeYear:true,
            dateFormat: "dd/mm/yy",
            maxDate: 0, // khoa ngay lon hon ngay hien tai
//            beforeShowDay: khoaNgay,
            onSelect: function(dateText, inst) { // lấy giá trị khi chọn ngày datepicker
                let dateSelected = $(this).datepicker('getDate');
                console.log("Ngày đã chọn: " + dateText); // Hiển thị ngày đã chọn trong console

                // gan gia tri vao the input tenDai
                inputTenDai.value = select__pick__Dai.value;
                inputDate.value = convertDateFormat(dateText);
//                console.log(inputDate.value)
                // thuc hien gui submit len server bang method post voi api "/us/chon-dai"
                formSelect.submit();
            }

    });
        // khi click vào icon thì hiển thị datepicker
        $("#icon__calender").on("click", function() {
            $("#input__datePicker").datepicker("show");
        });

        $("#datePicker-container").datepicker({
            showOtherMonths:true,
            selectOtherMonths:true,
            changeMonth:true,
            changeYear:true,
            dateFormat: "dd/mm/yy",
            maxDate: 0, // khoa ngay lon hon ngay hien tai
            inline: true, // Hiển thị Datepicker inline trong div
            onSelect: function(dateText, inst) {
                // Thêm bất kỳ xử lý nào khi chọn ngày
                // gan gia tri vao the input tenDai

                inputTenDai.value = 1;
                inputDate.value = convertDateFormat(dateText);// chuyen doi dinh dang sql gui len server
//                console.log("dang o day ne",inputDate.value)
                // thuc hien gui submit len server bang method post voi api "/us/chon-dai"
                formSelect.submit();
            }
        });
//        $("#datePicker-container").datepicker("setDate", new Date("2023-07-17"));

    });
});
