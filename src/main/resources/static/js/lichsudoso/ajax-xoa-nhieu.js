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

    // step 2: neu mang chua du lieu thuc hien cuoc goi den api server xoa nhieu ( ad/deletes )
    $.ajax({
        type: "POST",
        contentType: "application/json", // Loại dữ liệu được gửi đi là JSON.
        url: "/ad/lich-su-do-so/xoa-nhieu", // api cua server
        data: JSON.stringify(arrDeletes),// chuyen doi data sang json va truyen kieu json to server
        success: function (data) { // nhan reponesive success ham nay de xu ly du lieu
            console.log(data)
            // step 3: kiem tra du lieu truyen ve xoa thanh cong hay that bai
            if(data.content == null){ // du lieu truyen ve cho viec xoa that bai
                // add tin nhan that bai toi the div chua tin nhan that bai
                messDelete.innerHTML = "Xóa nhiều thất bại";
                // cho hien thi tin nhan xoa thanh cong hay that bai
                messageStatusDelete();
            }else{ // xoa thanh cong
                const listLsDs = data.content; // bien chua mang du lieu chua thong tin mang lich su do so
                let content =""; // noi dung the tbody
                // vong lap for lay dien them vao noi dung the tbody
                console.log("mang xa hoi la",listLsDs[0].social == null)
                for(let i = 0; i < listLsDs.length ; i++){
                    content +=`
                            <tr>
                <td >${i+1}</td>
                <td><input class="checkBox" type="checkbox" value="${listLsDs[i].id}"/></td>

                <td> ${listLsDs[i].ketQuaXoSo.daiXoSo.nameLottery}</td>
                <td>${listLsDs[i].ketQuaXoSo.dateLottery}</td>

                ${listLsDs[i].social != null ? `<td>${listLsDs[i].social}</td>` : ''}
                ${listLsDs[i].user != null ? `<td>${listLsDs[i].user.userName}</td>` : ''}
                <td>${listLsDs[i].ngayDo}</td>
                <td>${listLsDs[i].soDo}</td>
                <td>${listLsDs[i].hourDoSo}</td>

                <td>${listLsDs[i].messKq}</td>
                <td>${listLsDs[i].status}</td>
                ${listLsDs[i].tienThuong != null ? `<td>${listLsDs[i].tienThuong.tienThuong}</td>`:'<td>0đ</td>'}

                <td>
                    <div class="d-flex add-delete">

                        <a href="/ad/lich-su-do-so/delete?id=${listLsDs[i].id}"
                           class="btn btn-sm btn-danger me-1 "
                           onclick="if(!(confirm('Bạn có chắc xóa người dùng không ? '))) return false">
                            Xóa
                        </a>

                    </div>
                </td>

            </tr>`;
                }
                // hien thi noi dung moi len the tbody
                tbodyDeletes[0].innerHTML = content;
                // add tin nhan that bai hay thành công toi the div chua tin nhan thông báo việc xáo
                messDelete.innerHTML = "Xóa nhiều thành công";
                // hien thi tin nhan xoa thanh cong
                messageStatusDelete();
                // phan trang
                console.log(data.totalPages)
                if(data.totalPages <= 1){ // kiểm tra số trang trả về để biết hiển thị phần phân trang
                    pagiContainer[0].innerHTML="";

                }else{
                    // hien thi noi dung the phan trang moi
                    let contentPagination ="";
                    for(let i=1;i <= data.totalPages;i++){
                        contentPagination +=`
                          <a class="pagination--num" href="/ad/lich-su-do-so/phantrang?page=${i}">${i}</a>
                    `;
                    }

                    // them noi dung cho the phan trang
                    pagiContainer[0].innerHTML = `
                        <a class="prev" href="/ad/lich-su-do-so/phantrang?page=1">Đầu</a>

                            ${contentPagination}

                        <a class="next" href="/ad/lich-su-do-so/phantrang?page=${data.totalPages}">Cuối</a>`;
                    // lay ra day the chua the phan trang chi co hien thi 3 trang dau cac trnag con lai an di
                    const pagiArr = document.getElementsByClassName("pagination--num")

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