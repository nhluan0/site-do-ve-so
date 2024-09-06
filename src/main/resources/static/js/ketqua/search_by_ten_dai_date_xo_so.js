// co 1 chuc nang la gan 2 tham so tenDai va ngay xo so len href
// 1: gan 2 tham so tenDai va ngay xo so len href
// lay the a chua icon search
const tagArcho__tenDai = document.getElementById("find__tenDai");
// lay href
let href__archo__tenDai = tagArcho__tenDai.href;
// lay the select ten dai
const select__Dai__search = document.getElementById("select__Dai");
// lay the input chua ngay xo so
const input__find = document.getElementById("input__find");


// kiem tra 2 the select va input khong co gia tri trong thi gan tham so cho the <a>
tagArcho__tenDai.addEventListener("click",(e)=>{
    // neu mot trong hai the select chua ten dai va the input chua ngay thang nam khong giu gia tri nao thi ngan chan viec gui len server
    if(select__Dai__search.value =='' || input__find.value =="" || select__Dai__search.value == 1 ){
        e.preventDefault();
        return;
    }

    // neu va 2 the deu co giu gia tri , gan 2 tham so de gui len server
    href__archo__tenDai = href__archo__tenDai.concat(
        `?tenDai=${select__Dai__search.value}`,`&date=${input__find.value}`);
    // 1: gan 2 tham so tenDai va ngay xo so len href
    tagArcho__tenDai.href = href__archo__tenDai;

})