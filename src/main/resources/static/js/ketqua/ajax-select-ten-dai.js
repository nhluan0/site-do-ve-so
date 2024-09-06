// có 2 chức năng
// 1: khi chọn tên đài sẽ hiển thị 8 kết quả xổ số theo tên đài sắp xếp theo ngày giảm dần
// 2: thực hiện phân trang cho kết qủa lấy theo tên đài nhận được
// lấy thẻ select tên đài
const select__Dai = document.getElementById("select__Dai");
// lấy thẻ tbody để làm mới hiển thị
const select__Dai__tbody = document.getElementsByTagName("tbody");
// lay the phan trang de sap xep hien thi phan trang
const select__Dai__pagination = document.getElementsByClassName("pag-container")
// bien giu gia tri nut select dai
let select__Dai__Value = {
    id:1,
    nameLottery: select__Dai.value,
    aria:"Miền Trung"
};


// 1: khi chọn tên đài sẽ hiển thị 8 kết quả xổ số theo tên đài sắp xếp theo ngày giảm dần
// khi event onchange xay ra tren nut select ten dai se thuc hien gui du lieu ten dai bang ajax den server
// sau khi nhan duoc phan hoi thi hien thi du lieu len the tbody va sau do phan trang
select__Dai.addEventListener("change",function(){
   // step 1: kiem tra gia tri nhan duoc khac the option chon dai
    if(this.value !=1){
        select__Dai__Value.nameLottery = this.value;
        // step 2: gui du lieu len server bang ajax
        $.ajax({
        type: "POST",
        contentType: "application/json", // Loại dữ liệu được gửi đi là JSON.
        url: "/ad/kq/json-select-tenDai", // api cua server
        data: JSON.stringify(select__Dai__Value),// chuyen doi data sang json va truyen kieu json to server
        success: function (data) {
//            console.log(data)
//            console.log("lay mang da ta nhan duoc",data.ketQuaXoSoList)
//            console.log("lay gia tri dau tien cua mang",data.ketQuaXoSoList[0])
//            console.log("lay tong so trang nhan duoc",data.totalPage)
            let listKqxs = data.ketQuaXoSoList;
            let content ="";
            // step 3: hien thi du lieu len the tbody
            // kiem tra mang nhan duoc khong rong

            if(listKqxs.length >0){
                for (let i = 0; i <listKqxs.length;i++ ){
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
            }
            // hien thi noi dung moi len the tbody
            select__Dai__tbody[0].innerHTML = content;
            // phan trang
            if(data.totalPage <= 1){ // kiểm tra số trang trả về để biết hiển thị phần phân trang
                select__Dai__pagination[0].innerHTML="";

            }else{
                // hien thi noi dung the phan trang moi
                let contentPagination ="";
                for(let i=1;i <= data.totalPage;i++){

                    contentPagination +=`
                          <a class="pagination--num phantrang--select" href="/ad/kq/select_phantrang?tenDai=${listKqxs[0].daiXoSo.nameLottery}&page=${i}">${i}</a>

                    `;
                }

                // them noi dung cho the phan trang
                select__Dai__pagination[0].innerHTML = `
                        <a class="prev phantrang--select" href="/ad/kq/select_phantrang?tenDai=${listKqxs[0].daiXoSo.nameLottery}&page=1">Đầu</a>

                            ${contentPagination}

                        <a class="next phantrang--select" href="/ad/kq/select_phantrang?tenDai=${listKqxs[0].daiXoSo.nameLottery}&page=${data.totalPage}">Cuối</a>`;
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

        },
        error: function(e){

        }
    });

    }
})



