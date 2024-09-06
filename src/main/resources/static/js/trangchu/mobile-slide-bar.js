// khi click vao dau x thi dong slide bar
// lay the slide bar
const slideBar = document.getElementsByClassName("header__mobile");
// lay the che phu toan man hinh
const divMobile = document.getElementsByClassName("mobile");
// lay the icon dau x
const iconClose = document.getElementsByClassName("icon-close");
// lay the icon mennu bar
const iconMenuBar = document.getElementById("icon_home");

// khi an the icon x thi an het cac the
function hiddenSlideBar(){
    slideBar[0].style.display = "none";
    divMobile[0].style.display = "none";
}
// ham hien the slide bar
function showSlideBar(){
    slideBar[0].style.display = "block";
    divMobile[0].style.display = "block";
}
iconClose[0].addEventListener("click",function(){
    hiddenSlideBar();
})
iconMenuBar.addEventListener("click",()=>{
    showSlideBar();
})
divMobile[0].addEventListener("click",function(){
    hiddenSlideBar();
})


