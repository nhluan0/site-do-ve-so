// checkbox trong table
const checkBoxs = document.getElementsByClassName('checkBox');
// nut xoa nhieu
const deletes = document.getElementById("deletes");
// lay the tbody trong bang table
const tbodyDeletes = document.getElementsByTagName('tbody');
// lay the cha lon nhat chua phan trang
const pagiContainer = document.getElementsByClassName('pag-container');
// lay the cha chua cac the chua so phan trang  pagination__parent
const pagiParent = document.getElementsByClassName(' pagination__parent');
// lay the chua noi dung thong bao xoa nhieu thanh cong hay that bai voi id la: mess--deletes
const messDelete = document.getElementById('mess--deletes');

// lay the tbody
const tbBoday1 = document.getElementsByTagName("tbody");
// lay the tr trong the tbody
const trInTbody1 = tbBoday1[0].getElementsByTagName('tr')

let arrDeletes = []; // array chua gia tri id cua js Ajax-deletes gui len server
console.log(checkBoxs,deletes)


deletes.addEventListener("click",(e)=>{
    // add du lieu trong cac o check den mang chua du lieu chuan bi truyen id dc giu trong nay mang len server
    for (let i = 0; i < checkBoxs.length; i++) {
        console.log(checkBoxs[i].checked)
        if(checkBoxs[i].checked == true)arrDeletes.push(checkBoxs[i].value)
    }
    // Step 1: kiem tra do dai check box neu chua co gia tri nao thoat dung su kien
    if(arrDeletes.length == 0){
        // add tin nhan that bai toi the div chua tin nhan that bai
        messDelete.innerHTML = "chọn đối tượng trước khi xóa";
        // cho hien thi tin nhan xoa thanh cong hay that bai
        messageStatusDelete();
        modalHidden();// an modal , ham o file modal-share.js
        return;
    }
//    console.log(arrDeletes)
    // step 2: neu mang chua du lieu thuc hien cuoc goi den api server xoa nhieu ( ad/deletes )
    $.ajax({
    type: "POST",
    contentType: "application/json", // Loại dữ liệu được gửi đi là JSON.
    url: "/ad/kq/xoa-nhieu", // api cua server
    data: JSON.stringify(arrDeletes),// chuyen doi data sang json va truyen kieu json to server
    success: function (data) { // nhan reponesive success ham nay de xu ly du lieu
        // step 3: kiem tra du lieu truyen ve xoa thanh cong hay that bai
        if(data.ketQuaXoSos == null){ // du lieu truyen ve chua viec xoa that bai
            // add tin nhan that bai toi the div chua tin nhan that bai
            messDelete.innerHTML = data.mess;
            // cho hien thi tin nhan xoa thanh cong hay that bai
            messageStatusDelete();
        }else{ // xoa thanh cong
            const listKqxs = data.ketQuaXoSos.content; // bien chua mang du lieu chua thong tin user
            let content =""; // noi dung the tbody
            // vong lap for lay dien them vao noi dung the tbody
            for(let i = 0; i < listKqxs.length ; i++){
                content +=`
                            <tr>
                <td >${i+1}</td>
                <td><input class="checkBox" type="checkbox" value="${listKqxs[i].id}"/></td>

                <td> ${listKqxs[i].daiXoSo.nameLottery}</td>
                <td>${listKqxs[i].g8}</td>
                <td>${listKqxs[i].g7}</td>

                <td >
                    <p>${listKqxs[i].g61}</p>
                    <p>${listKqxs[i].g62}</p>
                    <p>${listKqxs[i].g63}</p>
                </td>
                 <td>${listKqxs[i].g5}</td>
                <td >
                    <p>${listKqxs[i].g41}</p>
                    <p>${listKqxs[i].g42}</p>
                    <p>${listKqxs[i].g43}</p>
                    <p>${listKqxs[i].g44}</p>
                    <p>${listKqxs[i].g45}</p>
                    <p>${listKqxs[i].g46}</p>
                    <p>${listKqxs[i].g47}</p>
                </td>
                <td >
                    <p>${listKqxs[i].g31}</p>
                    <p>${listKqxs[i].g32}</p>
                </td>
                <td>${listKqxs[i].g2}</td>
                <td>${listKqxs[i].g1}</td>
                <td>${listKqxs[i].db}</td>
                <td>${listKqxs[i].dateLottery}</td>
                <td>
                    <div class="d-flex add-delete">
                        <!--  Add tag a for button update  -->
                        <a href="/ad/kq/update?id=${listKqxs[i].id}"
                           class="btn btn-sm btn-primary me-1" >
                            Sửa
                        </a>
                        <!--  Add tag a for button delete  -->
                        <a href="/ad/kq/delete?id=${listKqxs[i].id}"
                           class="btn btn-sm btn-danger me-1 "
                           onclick="if(!(confirm('Bạn có chắc xóa người dùng không ? '))) return false">
                            Xóa
                        </a>
                        <a href="/ad/kq/chitiet?id=${listKqxs[i].id}"
                           class="btn btn-sm btn-info me-1 text-bg-primary" >
                            Xem chi tiết
                        </a>

                    </div>
                </td>

            </tr>`;
            }
            // hien thi noi dung moi len the tbody
            tbodyDeletes[0].innerHTML = content;
            // add tin nhan that bai hay thành công toi the div chua tin nhan thông báo việc xáo
            messDelete.innerHTML = data.mess;
            // hien thi tin nhan xoa thanh cong
            messageStatusDelete();
            // phan trang
            if(data.totalPage <= 1){ // kiểm tra số trang trả về để biết hiển thị phần phân trang
                pagiContainer[0].innerHTML="";

            }else{
                // hien thi noi dung the phan trang moi
                let contentPagination ="";
                for(let i=1;i <= data.totalPage;i++){

                    contentPagination +=`
                          <a class="pagination--num" href="/ad/kq/phantrang?page=${i}">${i}</a>

                    `;
                }

                // them noi dung cho the phan trang
                pagiContainer[0].innerHTML = `
                        <a class="prev" href="/ad/kq/phantrang?page=1">Đầu</a>

                            ${contentPagination}

                        <a class="next" href="/ad/kq/phantrang?page=${data.totalPage}">Cuối</a>`;
                // lay ra day the chua the phan trang chi co hien thi 3 trang dau cac trnag con lai an di
                const pagiArr = document.getElementsByClassName("pagination--num")
                console.log(pagiArr)
                // lặp qua thẻ a và ẩn hết các thẻ còn lại chỉ hiển thị 3 thẻ đầu đồng thời tô màu thẻ đầu tiên
                for(let i = 0; i < pagiArr.length ; i++){
                    if(i == 0){ // tô màu thẻ đầu tiên
                        pagiArr[i].style.backgroundColor = "#7e4030";
                        pagiArr[i].style.color = "white";
                        pagiArr[i].style.fontWeight = "normal";
                    }
                    if(i > 2){ // ẩn các thẻ sau vị trí thứ 3
                        pagiArr[i].style.display = "none";
                    }
                }
            }
        }
    },
        error: function(e){
            messDelete.innerHTML = "Xóa thất bại";
            // hien thi tin nhan xoa that bai
            messageStatusDelete();
        }
    });
    modalHidden();// an modal , ham o file modal-share.js
    arrDeletes = [];// set lai mang chua du lieu o file css Ajax-deletes
});