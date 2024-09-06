// lay the container chua tat ca cac the phan trang
const pagContainer = document.getElementsByClassName("pag-container");
// lay tat ca cac the <a> hien thi phan trang
const pagNum = document.getElementsByClassName("pagination--num");
// lay the prev
const prev = document.getElementsByClassName("prev");
// lay the next
const next = document.getElementsByClassName("next");

const parameterUrl = window.location.search; // lay tham so tren
// vi tri đầu tiên của tham số page
let position = parameterUrl.indexOf("page");
//console.log("tham so nhan dc la :",parameterUrl,position)

//console.log("parameterURL : ",parameterUrl.length , parameterUrl.substring(1, 5));
//console.log("tham so dc cat ra la: ", parameterUrl.substring(6, 7) , pagNum[0] )
const lengthParam = parameterUrl.length;
//console.log("Độ dài tham số là ",lengthParam)
const numPage = parameterUrl.substring(position+5).trim();
//console.log("so trang nhan duoc la : ", numPage)
const nameParam = parameterUrl.substring(position,position+4);
//console.log("tham so la gi : ",nameParam , nameParam =="user")


// ham to mau cho phan trang
function backgroundPagi(index, tag){
    if(tag.length > 0){
        tag[index].style.backgroundColor = "#7e4087";
        tag[index].style.color = "white";
        tag[index].style.fontWeight = "normal";
    }

}
// function an tat ca cac trang
function hiddenPage(tag){
    if(tag.length > 0){
        for (let i=0;i<tag.length;i++){
            tag[i].style.display = "none";
        }
    }
}
// muốn tô màu trang nào đang hiển thị dữ liệu
// Step 1: kiểm tra url có tham số hay, để tô maàu vị trí trang 1
if(lengthParam == 0 || (nameParam == "page" && numPage == 1) || nameParam !="page"){
    backgroundPagi(0,pagNum);
    // hiển thị 3 phân trang
    for (let i=3;i<pagNum.length;i++){
        pagNum[i].style.display = "none";
    }
}else if(nameParam == "page" && numPage > 1){
    let indexPage = parseInt(numPage) -1; // vị trí index của thẻ phân trang <a>
    backgroundPagi(indexPage,pagNum); // tô màu cho thẻ phân trang
    console.log("index trang hien giu la : " + indexPage)
    if(pagNum.length > 3){
        // cho an tat ca cac the phan trang
        hiddenPage(pagNum);
        let totalPage = indexPage+2;// dung so sanh minh muon hien 3 phan tu voi do dai mang chua the a phan trang
        console.log("totalpage :",totalPage ,"lengParam :",lengthParam)
        if(totalPage < pagNum.length){
            for (let i=indexPage-1;i<totalPage;i++){
                pagNum[i].style.display = "block";
            }
        }else if( 0<=(totalPage - pagNum.length) && (totalPage - pagNum.length)<= 1 ){ // cho 3 so cuoi
            for (let i=pagNum.length-3;i<pagNum.length;i++){
                pagNum[i].style.display = "block";
            }
        }


    }


}