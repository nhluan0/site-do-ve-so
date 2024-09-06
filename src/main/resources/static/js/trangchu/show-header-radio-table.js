// 1: thuc hien hien thi header
// 2: thuc hien hien thi radio
// 3: to mau the span chua cac ten dai dau tien
const headerTableContent = document.getElementsByClassName("header__table");


// 1: thuc hien hien thi header
// lap qua tat ca cac the headerTableContent lay noi dung the span dau tien
// va lay noi dung the nay cho hien thi len the container header__table
[...headerTableContent].forEach(item=>{
    // a: lay cac the p co trong the container class header__table
    const tagPContent = item.getElementsByTagName("p");
    // b: lap qua cac the p nay va lay noi dung the dau tien cho hien thi len the cha container
    [...tagPContent].forEach((item1)=>{
        // c: cho hien thi noi dung len the cha container co class header__table
        item.innerHTML = item1.innerHTML;
        // chi lap qua 1 lan dau tien
        return;
    })
});

// 2: thuc hien hirn thi radio
// lay cac the header chua input radio
const divContanerInputRadio = document.getElementsByClassName("radio__option");
// lap qua tat ca the divContainerInputRadio sau do lay noi dung the raido dau tien va cho hien thi lai the cha
[...divContanerInputRadio].forEach(item=>{
    // a: lay the chua cac nut radio
    const tagContainerRadio = item.getElementsByClassName("container__radio");
    // b: lap qua the dau tien va cho hien thi lai trong the cha
    [...tagContainerRadio].forEach(item1=>{
        // c: lay noi dung the container radio dau tien va cho hien thi len the cha
        item.innerHTML = item1.innerHTML;
        // thoat khi vong lap dau tien
        return;

    });
});

// 3: phan to mau the span dau tien cua ten dai
// lay the container__loto
const container__loto = document.getElementsByClassName("container__loto");

// lap qua cac container__loto nay
[...container__loto].forEach(item=>{
    // a: lap the div chua class span ten dai
    const divLoto = item.getElementsByClassName("loto");
    // b: lay the cac the span chua ten dai trong divLoto nay
    const spanContainerTenDai = divLoto[0].getElementsByTagName("span");
    // c: lap qua cac the span nay va cho hien thi background mau trang
    [...spanContainerTenDai].forEach((item1,index)=>{
        // d: cho hien thi nen mau trang
        if(index == 0){
            item1.style.backgroundColor = "white";
            // chi hien thi 1 lan dau tien
            return;
        }
    })
});