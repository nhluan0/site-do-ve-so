const modalParent = document.getElementsByClassName("modal-parent");

const modalContainer = document.getElementsByClassName("modal-container");

const btnOk = document.getElementsByClassName("btn__active--ok");
const btnCancel = document.getElementsByClassName("btn__active--cancel");

// nut showModal can thay doi cho phu hop voi tung ngu canh
const showModal = document.getElementsByClassName("btn--deletes")
const xClose = document.getElementsByClassName("close")

console.log("modal parent",modalContainer)
console.log("btn ok",btnOk)

console.log("btn huy",btnCancel)
console.log("div show modal",showModal)
function modalShow(){
    modalParent[0].classList.add("show");
    modalContainer[0].classList.add("show");
}

function modalHidden(){
    modalParent[0].classList.remove("show");
    modalContainer[0].classList.remove("show");
}

// show modal can viet o ajax
showModal[0].addEventListener('click',()=>{

    modalShow();
    console.log("loi tai sao")

})
modalParent[0].addEventListener('click',function(e){
    if(e.target == this){
        modalHidden();
        arrDeletes = [];// set lai mang chua du lieu o file css Ajax-deletes
    }


})
btnCancel[0].addEventListener("click",()=>{
    modalHidden();
    arrDeletes = [];// set lai mang chua du lieu o file css Ajax-deletes
})
xClose[0].addEventListener("click",()=>{
    modalHidden();
    arrDeletes = [];// set lai mang chua du lieu o file css Ajax-deletes
})
