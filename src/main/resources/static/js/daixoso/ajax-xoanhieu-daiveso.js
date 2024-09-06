// checkbox trong table
const checkBoxs = document.getElementsByClassName('checkBox');
// nut xoa nhieu
const deletes = document.getElementById("deletes");
// lay the tbody trong bang table
const tbodyDeletes = document.getElementsByTagName('tbody');

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
        url: "/ad/xoa-nhieu-dai-ve-so", // api cua server
        data: JSON.stringify(arrDeletes),// chuyen doi data sang json va truyen kieu json to server
        success: function (data) {
            console.log(data,data.length)
            // step 3: kiem tra du lieu truyen ve xoa thanh cong hay that bai
            if(data.length == 0){ // du lieu truyen ve chua viec xoa that bai
                // add tin nhan that bai toi the div chua tin nhan that bai
                messDelete.innerHTML = "Xóa thất bại!";
                // cho hien thi tin nhan xoa thanh cong hay that bai
                messageStatusDelete();
            }else{ // xoa thanh cong
                let content =""; // noi dung the tbody
                // vong lap for lay dien them vao noi dung the tbody
                for(let i = 0; i < data.length ; i++){
                    content +=`
                    <tr>
                        <td>${i+1}</td>
                        <td><input class="checkBox" type="checkbox" value="${data[i].id}"/></td>

                        <td>${data[i].aria}</td>
                        <td>${data[i].nameLottery}</td>

                        <td>
                            <div class="d-flex add-delete">
                                <!--  Add tag a for button update  -->
                                <a href="/ad/sua-dai-ve-so?id=${data[i].id}" class="btn btn-sm btn-primary me-1">
                                    Sửa
                                </a>
                                <!--  Add tag a for button delete  -->
                                <a href="/ad/xoa-dai-ve-so?id=${data[i].id}" class="btn btn-sm btn-danger me-1 "
                                   onclick="if(!(confirm('Bạn có chắc xóa đài vé số không ? '))) return false">
                                    Xóa
                                </a>
                            </div>
                        </td>

                    </tr>`;

                }
                // hien thi noi dung moi len the tbody
                tbodyDeletes[0].innerHTML = content;
                // add tin nhan that bai toi the div chua tin nhan that bai
                messDelete.innerHTML = "Xóa thành công!";
                // hien thi tin nhan xoa thanh cong
                messageStatusDelete();

            }

        },
        error: function(e){
            messDelete.innerHTML = "Xóa thất bại!";
            // hien thi tin nhan xoa thanh cong
            messageStatusDelete();
        }

    });

    modalHidden();// an modal , ham o file modal-share.js
    arrDeletes = [];// set lai mang chua du lieu o file css Ajax-deletes

});
