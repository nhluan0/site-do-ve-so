// co 2 chuc nang
// 1: hien thi dau duoi lo to
// 2: khi click vao ten dai thi hien len bang lo to do
const input__loTo__tenDai = document.getElementsByClassName("input__loTo__tenDai");
let arrTenDai = [];


// lap qua cac the input an input__loTo__tenDai va lay gia tri cua no cho vao mang\
for (let i=0 ;i<input__loTo__tenDai.length;i++){
    arrTenDai.push(input__loTo__tenDai[i].value);
}

// ham tim vi tri va kiem tra no co ton tai trong chuoi da cho hay khong
function findCharPositionInString(character, inputString) {
    let position = inputString.indexOf(character);
    if (position !== -1) {
        return position;
    } else {
        return position;
    }
}
//console.log( "10".substring(1) == 0)
// ham dien cac so vao object dau cuoi theo dau va duoi
function dienDauCuoi(character,inputString,objectLotoDau,objectLotoCuoi){
    if(findCharPositionInString(character,inputString) !== -1){
        let position = findCharPositionInString(character,inputString);
        if(position == 0){
            objectLotoDau[character].push(inputString);
        }
        if(position==1 || inputString.trim().substring(1) == character){
            objectLotoCuoi[character].push(inputString);
        }

    }
}
//let position = findCharPositionInString('0','11');
//console.log(position);
// lap qua tung phan tu cua mang va add thuoc tinh moi cho object
arrTenDai.forEach(item=>{
    let objectLotoDau ={
        '0':[],
        '1':[],
        '2':[],
        '3':[],
        '4':[],
        '5':[],
        '6':[],
        '7':[],
        '8':[],
        '9':[],
    };
    let objectLotoCuoi ={
        '0':[],
        '1':[],
        '2':[],
        '3':[],
        '4':[],
        '5':[],
        '6':[],
        '7':[],
        '8':[],
        '9':[],
    };
    // lay cac the chua gia tri lo theo ten
    const valueLoto = document.getElementsByClassName(item);
    // lay the tbody tuong ung voi dai
    const tbodyDai = document.getElementById(item);
    // lap qua the loto va sau do add gia tri vao objectLoto tuong ung voi thuoc tinh cua no
    if(tbodyDai){
        [...valueLoto].forEach(element=>{
            // kiem tra gia tri nhan duoc co cac so dau cuoi hay khong
            // kiem tra vi tri cac so co trong chuoi hay khong va them vao objec giu vi tri dau cuoi
            dienDauCuoi(0,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(1,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(2,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(3,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(4,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(5,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(6,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(7,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(8,element.textContent,objectLotoDau,objectLotoCuoi);
            dienDauCuoi(9,element.textContent,objectLotoDau,objectLotoCuoi);
        });
    }
    // sau khi da co ket qua dau cuoi , can dien noi dung vao the tbody
    tbodyDai.innerHTML =
    `
       <tr>
                    <th class="td_font_normal" scope="row">0</th>
                    <td>${objectLotoDau[0]}</td>
                    <td class="td_font_normal " >0</td>
                    <td>${objectLotoCuoi[0]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">1</th>
                    <td>${objectLotoDau[1]}</td>
                    <td class="td_font_normal " >1</td>
                    <td>${objectLotoCuoi[1]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">2</th>
                    <td>${objectLotoDau[2]}</td>
                    <td class="td_font_normal " >2</td>
                    <td>${objectLotoCuoi[2]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">3</th>
                    <td>${objectLotoDau[3]}</td>
                    <td class="td_font_normal " >3</td>
                    <td>${objectLotoCuoi[3]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">4</th>
                    <td>${objectLotoDau[4]}</td>
                    <td class="td_font_normal " >4</td>
                    <td>${objectLotoCuoi[4]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">5</th>
                    <td>${objectLotoDau[5]}</td>
                    <td class="td_font_normal " >5</td>
                    <td>${objectLotoCuoi[5]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">6</th>
                    <td>${objectLotoDau[6]}</td>
                    <td class="td_font_normal ">6</td>
                    <td>${objectLotoCuoi[6]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">7</th>
                    <td>${objectLotoDau[7]}</td>
                    <td class="td_font_normal ">7</td>
                    <td>${objectLotoCuoi[7]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">8</th>
                    <td>${objectLotoDau[8]}</td>
                    <td class="td_font_normal ">8</td>
                    <td>${objectLotoCuoi[8]}</td>
                </tr>
                <tr>
                    <th class="td_font_normal " scope="row">9</th>
                    <td>${objectLotoDau[9]}</td>
                    <td class="td_font_normal ">9</td>
                    <td>${objectLotoCuoi[9]}</td>
                </tr>
    `;
    //    console.log("dau ",objectLotoDau)
    //    console.log("cuoi ",objectLotoCuoi)
});

// 2: hien thi noi dung loto dai ve so dau tien
// lay the div cha chua noi dung the loto
const containerLoto = document.getElementsByClassName("container__loto");
// lap qua tung the div nay va cho hien thi noi dung loto theo ten dai dau tien
[...containerLoto].forEach(item=>{
    // a: lay the div se hien thi noi dung chinh
    const contentContainerLoto = item.getElementsByClassName("content_container--loto");
    // b: lay cac the chua noi dung table loto
    const divContentTableDataLoto = item.getElementsByClassName("container__loto__table");
   // c: lap qua cac the div se chua noi dung chinh data loto , sau do cho hien thi noi dung loto lan dau tien
    [...contentContainerLoto].forEach(item1=>{
        // d: lap qua cac the divContentTableDataLoto va lay du lieu cho vong lap dau tien
        // dau do chi hien thi tren the div chua noi dung chinh
        [...divContentTableDataLoto].forEach((item2,index)=>{
            if(index == 0){
                // e: hien thi noi dung dau tien
                item1.innerHTML = item2.innerHTML;
                // thoat vong lap cho lan dau tien
                return;
            }
        });
    });
});

// 3: khi click vao ten dai trong the span thi hien thi noi dung loto tuong ung voi ten dai do
[...containerLoto].forEach(item=>{
    // a: lay the div chua cac the span loto ten dai nay
    const divContainerSpanLoto = item.getElementsByClassName("loto");
    // b: lay cac the span trong the nay
    const spanLoto = divContainerSpanLoto[0].getElementsByTagName("span");
    // c: lay cac the div chua noi dung table tuong ung voi the span
    const divContentTableDataLoto = item.getElementsByClassName("container__loto__table");
    // d: lay the div se hien thi noi dung chinh
    const contentContainerLoto = item.getElementsByClassName("content_container--loto");
    // e: lap qua cac the span nay va bat su kien
    [...spanLoto].forEach((item1,index)=>{
        item1.addEventListener("click",function(){
            // f: hien thi noi dung tuong ung voi ten dai trong the span
            contentContainerLoto[0].innerHTML = divContentTableDataLoto[index].innerHTML;
            // g: lap qua cac the span va to mau xanh cho tat ca cac the span nay
            [...spanLoto].forEach(item3=>{
                item3.style.backgroundColor="#00ffff";
            });
            // j: to mau the span nay thanh mau trang
            this.style.backgroundColor="white";
        });
    });
});