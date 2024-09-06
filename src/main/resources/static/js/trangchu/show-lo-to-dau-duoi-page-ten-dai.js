// 1 chuc nang thuc hien show loto dau duoi vao bang table
// lay the contaner chua cac so lo to va ca bang loto
const divContainerLoto = document.getElementsByClassName("container__loto");
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
// 1: lặp qua các thẻ divContainerLoto
[...divContainerLoto].forEach(item=>{
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
    // 2: lay cac the div chua cac the span chua gia tri loto
    const divContainerResultLoto = item.getElementsByClassName("kqLoTo");
    // 3: lap qua cac the divContainerResultLoto
    [...divContainerResultLoto].forEach(item1=>{
        // 4: lay cac the span chua gia tri lo to
        const spanHoldValueLoto = item1.getElementsByTagName("span");
        // 5: lặp qua các thẻ span và lấy giá trị theo đầu đuôi
        [...spanHoldValueLoto].forEach(element=>{
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
    });
    // 6: sau khi da co ket qua thì lấy thẻ tbody và cho hiển thị lên màn hình
    const tbodyContainer = item.getElementsByTagName("tbody")
    console.log("the tbody lay duoc",tbodyContainer)
    // 7: hien thi noi dung len man hinh
    tbodyContainer[0].innerHTML = `
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
                </tr>`;

});


