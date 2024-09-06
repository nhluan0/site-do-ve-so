// an hien thanh slide bar cho phan mobile
// lay the icon menu
const iconMenuBar = document.getElementById("iconMenuBar");
// lay the container slide bar menu cho phan mobile
const slideBar__mobile = document.getElementsByClassName("slideBar__mobile");
// lay icon close
const icon__close =  document.getElementsByClassName("icon__close");

// han slide bar
function hiddenSildeBar(){
    slideBar__mobile[0].style.display = "none";
}
// ham hien slide bar
function showSildeBar(){
    slideBar__mobile[0].style.display = "block";
}

// bat su kien co icon menu khi nhan vao slide bar hien len
iconMenuBar.addEventListener("click",()=>{
    showSildeBar();
})

// bat su kien icon close khi nhan vao an slide bar
icon__close[0].addEventListener("click",()=>{
    hiddenSildeBar();
})
