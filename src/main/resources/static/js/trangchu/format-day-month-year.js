
// 1: thuc hien chuyen doi hien thi ngay thang nam thanh dinh dang dd/mm/yyyy
// cho phan tieu de bang xo so va phan tieu de lo to
// a: lay the header cua bang xo so
const headerTitleTableKqXs = document.getElementsByClassName("header__table");
// b: lap qua tat ca the tieu de chuyen doi dinh dang va hien thi
[...headerTitleTableKqXs].forEach(item=>{
    // c: lay noi dung the header nay
    let contentTitleTableKqXs = item.innerHTML;
    // d: cat lay phan ngay
    let day = contentTitleTableKqXs.substring(contentTitleTableKqXs.length-10);
    // e: cat phan dau truoc ngay
    let beforDay = contentTitleTableKqXs.substring(0,contentTitleTableKqXs.length-10);
    // f: phan tach chuoi ngay the dau '-'
    let dateParts = day.trim().split("-");
    // g: tao dinh dang ngay moi "dd/MM/yyyy"
    let formattedDate = dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];
    // h: chuyen doi thanh thu trong tuan
    let date = new Date(day);
    // k:Lấy ngày trong tuần (0: Chủ Nhật, 1: Thứ 2, ..., 6: Thứ 7)
    let dayOfWeek = date.getDay();
    // lay 1 bien giu thu trong tuan
    let dayInWeek = "";
    if(dayOfWeek == 0){
        dayOfWeek = "Chủ Nhật";
    }else if(dayOfWeek == 1){
        dayOfWeek = "Thứ Hai";
    }else if(dayOfWeek == 2){
        dayOfWeek = "Thứ Ba";
    }else if(dayOfWeek == 3){
        dayOfWeek = "Thứ Tư";
    }else if(dayOfWeek == 4){
        dayOfWeek = "Thứ Năm";
    }else if(dayOfWeek == 5){
        dayOfWeek = "Thứ Sáu";
    }else if(dayOfWeek == 6){
        dayOfWeek = "Thứ Bảy";
    }
    // cho hiển thị lại nội dung mới
    item.innerHTML = beforDay + ' '+ dayOfWeek + " Ngày "+ formattedDate;

});