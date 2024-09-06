// thuc hien thong bao khi do so, nguoi dung nhap so hay ngay ko dung
// lay form
const formDoSo = document.getElementsByClassName("form-doSo");


// lap qua tung form va bắt sự kiện submit từng form
[...formDoSo].forEach(item=>{
    // bat su kien submit tung form
    item.addEventListener("submit",function(e){
        // lay the input bo so
        const inputBoSo = item.getElementsByClassName("boSo--doSo");
        // lay the input ngay thang nam
        const inputDateDoSo = item.getElementsByClassName("date__doSo");
        // check gia tri bo so co hợp lệ hay không
        if(inputBoSo[0].value == "" || inputBoSo[0].value.length < 6 || inputBoSo[0].value.length > 6){
            e.preventDefault();
            alert("Nhập dãy số bao gồm 6 số");

        }else if(inputDateDoSo[0].value == ""){
            e.preventDefault();
            alert("Ngày chưa nhập vào");
        }
    })
});

// thuc hien gan gia trị select cho các lần load
const input_tinhThanh = document.getElementById("input_tinhThanh");
// kiểm tra giá trị có tồn tại hay không nếu có thì mới gán
if(input_tinhThanh.value){
    document.getElementById("tinhThanh_doSo").value = input_tinhThanh.value;
}

