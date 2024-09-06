const btnSearch = document.getElementById("find__user");
let link = btnSearch.href;
const inputSearch = document.getElementById("input__find");
let valueInputSearch = 1;


btnSearch.addEventListener("click",(e)=>{
    if(inputSearch.value ==""){ // neu o input trong ngan chan gui len server
        e.preventDefault();
        return;
    }

    // ngược lại giá trị input không trống
    valueInputSearch = inputSearch.value; // lay gia trị input hiện tại
    link = link.concat(`?daiVeSo=${valueInputSearch}`);// liên kết link hiện tại với giá trị nhập vào
    btnSearch.href = link;// set lại link đầy đủ với tham số được gắn vào và gửi lên server
//    console.log(btnSearch.href)

})