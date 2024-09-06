// co 1 chuc nang xem the input an co chua gia tri hay khong neu co cho hien thi len nut select chua ten dai
// lay the input an chua ten dai
const input__tenDai = document.getElementById("input__tenDai");
// lay nut select dai
const select__tenDai = document.getElementById("select__Dai");

// kiem tra gia tri cua the input co ton tai ten dai thi cho hien gia tri tenDai len the select Ten dai
if(input__tenDai.value !=""){
    select__tenDai.value = input__tenDai.value;
}