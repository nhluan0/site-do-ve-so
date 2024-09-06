// co 3 chuc nang
// 1. hien thi loi giai 6
// 2. hien thi loi giai 4
// 3. hien thi loi giai 3
// lay tap hop the hien thi loi giai 6
const tagErrorG6 = document.getElementsByClassName("error-g6");
// lay the hien thi loi giai 6
const tagShowErrorG6 = document.getElementById("g6Error");
// lay tap hop the hien thi loi giai 4
const tagErrorG4 = document.getElementsByClassName("error-g4");
// lay the hien thi loi giai 4
const tagShowErrorG4 = document.getElementById("g4Error");
// lay tap hop the hien thi loi giai 4
const tagErrorG3 = document.getElementsByClassName("error-g3");
// lay the hien thi loi giai 4
const tagShowErrorG3 = document.getElementById("g3Error");
console.log("the giai 6 :", tagShowErrorG3,tagErrorG6)


// 1. hien thi loi giai 6
if(tagErrorG6.length != 0){ // khi khong co loi thi cac the tagErrorG6 không được tạo nên ko lấy được giá trị => độ dài bằng 0
    tagShowErrorG6.innerHTML = "Giải 6 chỉ có 4 số và không được bỏ trống";
    tagShowErrorG6.style.display = "block";
}

// 2. hien thi loi giai 4
if(tagErrorG4.length != 0){
    tagShowErrorG4.innerHTML = "Giải 4 chỉ có 5 số và không được bỏ trống";
    tagShowErrorG4.style.display = "block";
}

// 3. hien thi loi giai 3
if(tagErrorG3.length != 0){
    tagShowErrorG3.innerHTML = "Giải 3 chỉ có 5 số và không được bỏ trống";
    tagShowErrorG3.style.display = "block";
}