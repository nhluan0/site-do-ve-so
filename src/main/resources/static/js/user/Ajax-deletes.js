// checkbox trong table
const checkBoxs = document.getElementsByClassName('checkBox');
// nut xoa nhieu
const deletes = document.getElementById("deletes");
// lay the tbody trong bang table
const tbodyDeletes = document.getElementsByTagName('tbody');
// lay the cha lon nhat chua phan trang
const pagiContainer = document.getElementsByClassName('pag-container');
// lay the cha chua cac the chua so phan trang  pagination__parent
const pagiParent = document.getElementsByClassName('pagination__parent');
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
        url: "/ad/deletes", // api cua server
        data: JSON.stringify(arrDeletes),// chuyen doi data sang json va truyen kieu json to server
        success: function (data) { // nhan reponesive success ham nay de xu ly du lieu
            // step 3: kiem tra du lieu truyen ve xoa thanh cong hay that bai
            if(data.userPage == null){ // du lieu truyen ve chua viec xoa that bai
                // add tin nhan that bai toi the div chua tin nhan that bai
                messDelete.innerHTML = data.mess;
                // cho hien thi tin nhan xoa thanh cong hay that bai
                messageStatusDelete();
            }else{ // xoa thanh cong
                const listUser = data.userPage.content; // bien chua mang du lieu chua thong tin user
                let content =""; // noi dung the tbody
                // vong lap for lay dien them vao noi dung the tbody
                for(let i = 0; i < listUser.length ; i++){
                    content +=`
             <tr>
                <td >${i+1}</td>
                <td><input class="checkBox" type="checkbox" value="${listUser[i].id}"/>

                <td >${listUser[i].firstName}</td>
                <td >${listUser[i].lastName}</td>
                <td >${listUser[i].userName}</td>
                <td >${listUser[i].email}</td>
                <td >${listUser[i].phoneNumber}</td>

                <td >${listUser[i].registerDate}</td>
                <td >${listUser[i].role.role}</td>
                <td>${listUser[i].enable == 0 ? 'khóa': 'mở'}</td>
                <td>
                    <div class="d-flex add-delete">
                        <!--  Add tag a for button update  -->
                        <a href="/ad/update?userId=${listUser[i].id}" style="padding:4.5px 8px;"
                           class="btn btn-sm btn-primary me-1"
                           onclick="if(!(confirm('Bạn có muốn cập nhập thông tin lại không? '))) return false">
                            Sửa
                        </a>
                        <!--  Add tag a for button delete  -->
                        <a href="/ad/delete?userId=${listUser[i].id}" style="padding:4.5px 8px;"
                           class="btn btn-sm btn-danger me-1 "
                           onclick="if(!(confirm('Bạn có chắc xóa người dùng không ? '))) return false">
                            Xóa
                        </a>
                        <a href="/ad/reset?userId=${listUser[i].id}" style="padding:4.5px 8px;"
                           class="btn btn-sm btn-info me-1 text-bg-primary"
                           onclick="if(!(confirm('Bạn có muốn reset lại mật khẩu không? '))) return false">
                            Reset mật khẩu
                        </a>
                         <a style="display: ${listUser[i].enable === 0 ? 'block' : 'none'};padding:4.5px 8px;" href="/ad/mo-tai-khoan?userId=${listUser[i].id}"
                          class="btn btn-sm btn-info me-1 text-bg-primary"
                          onclick="return confirm('Bạn có muốn mở lại tài khoản không?')">
                             Mở
                         </a>

                    </div>
                </td>

            </tr> `;
                }
                // hien thi noi dung moi len the tbody
                tbodyDeletes[0].innerHTML = content;
                // add tin nhan that bai toi the div chua tin nhan that bai
                messDelete.innerHTML = data.mess;
                // hien thi tin nhan xoa thanh cong
                messageStatusDelete();
                // in ra content
                console.log("Noi Dung" ,listUser[0].role,listUser[0].role.role )

                // phan trang
                if(data.totalPage <= 1){ // kiểm tra số trang trả về để biết hiển thị phần phân trang
                    pagiContainer[0].innerHTML="";

                }else{
                    // hien thi noi dung the phan trang moi
                    let contentPagination ="";
                    for(let i=1;i <= data.totalPage;i++){
                        contentPagination +=`
                          <a class="pagination--num" href="/ad/phantrang?id=${i}">${i}</a>

                    `;
                    }

                    // them noi dung cho the phan trang
                    pagiContainer[0].innerHTML = `
                        <a class="prev" href="/ad/phantrang?id=1">Đầu</a>

                            ${contentPagination}

                        <a class="next" href="/ad/phantrang?id=${data.totalPage}">Cuối</a>`;
                    // lay ra day the chua the phan trang chi co hien thi 3 trang dau cac trnag con lai an di
                    const pagiArr = document.getElementsByClassName("pagination--num")
                    console.log(pagiArr)
                    // lặp qua thẻ a và ẩn hết các thẻ còn lại chỉ hiển thị 3 thẻ đầu đồng thời tô màu thẻ đầu tiên
                    for(let i = 0; i < pagiArr.length ; i++){
                        if(i == 0){ // tô màu thẻ đầu tiên
                            pagiArr[i].style.backgroundColor = "#7e4087";
                            pagiArr[i].style.color = "white";
                            pagiArr[i].style.fontWeight = "normal";
                        }
                        if(i > 2){ // ẩn các thẻ sau vị trí thứ 3
                            pagiArr[i].style.display = "none";
                        }
                    }
                    // kiem tra dang o trang find-user hay khong
                    const paramUrlFindUser = window.location.search.substring(1, 5); // lay tham so url hien tai
                    // kiem tra dang o trang user thuc hien phan trang
                    if(paramUrlFindUser == "user"){
                        // neu dang o trang find-user, or find-user-error thi cho an di
                        if(trInTbody1.length <= 1 && nameParam == "user"){
                            pagiContainer[0].style.display = "none";
                        }else{
                            pagiContainer[0].style.display = "flex";
                        }
                    }
                    console.log("tham so trang find user khi click vao nut xoa nhieu :",window.location.search.substring(1, 5))

                }

            }
//            console.log("SUCCESS : ", data , data.mess,data.totalPage);
//            console.log("data userPage la : " + data, data.userPage, data.userPage == null);
//            console.log("array lay duoc la : "+ data.userPage.content)
//            console.log("du lieu dau tien la : "+ data.userPage.content[0].id )
//            console.log("du lieu dau tien ten la gi : "+ data.userPage.content[0].firstName ,data.userPage.content[0].email)
        },
        error: function(e){
            messDelete.innerHTML = "Xóa thất bại";
            // hien thi tin nhan xoa thanh cong
            messageStatusDelete();
        }

    });
    console.log(arrDeletes)
    modalHidden();// an modal , ham o file modal-share.js
    arrDeletes = [];// set lai mang chua du lieu o file css Ajax-deletes

});
