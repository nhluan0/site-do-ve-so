// thuc hien cac chuc nang hien thi day du , 3 so ,2 so tren bnag table
// 1: phai biet duoc the chua input checkbox nao dang co event click vao
// 2: khi da biet the input nao dang xay ra su kien thi lay nguoc lai du lieu cuar bang do
// 3: sau khi da lay duoc du lieu, thi check the input do dang o input chuc nang nao va cho hien thi
// nguoc lai theo chuc nang do
// lay tat ca cac the div chua input type checkbox
const divInputCheckbox = document.getElementsByClassName("content__main__table__show");

// ham add du lieu vao object
function addDataToObjectXs(objectData,attribute,tagData){
    for (let i = 0;i<tagData.length;i++){
        objectData[attribute].push(tagData[i].innerHTML.trim());
    }
}
// ham hien thi so them chuc nang day du , 3 so , 2 so
function showDataByFunction(tag,objectData,attribute,num){
    for(let i =0; i<tag.length;i++){
        tag[i].innerHTML = objectData[attribute][i].substring(objectData[attribute][i].length-num);
    }
}
// ham hien thi noi dung giai xo so theo so luong truyen vao , day du , 3 so ,2 so
function showTableByNumBerPasses(objectDataXs,num,...args) {
    const dataSets = [
        'g7', 'g61', 'g62', 'g63', 'g5', 'g41', 'g42', 'g43', 'g44', 'g45', 'g46', 'g47', 'g31', 'g32', 'g2', 'g1', 'db'
    ];

    args.forEach((td, index) => {
        showDataByFunction(td, objectDataXs, dataSets[index], num);
    });
}

// object giu gia tri cho lan dau tien add du lieu ket qua dai ve so
let objectHoldData ={

};
// 1: phai biet duoc the chua input checkbox nao dang co event click vao
// lap qua tung the va add su kien cho tung the
[...divInputCheckbox].forEach((item,index)=>{
    // them thuoc tinh theo index cua the div chua cac nut input radio
    objectHoldData[index] = "";
    // add event cho tung the div
    item.addEventListener("click",function(e){

        if(e.target.classList.contains("input__checkBox")){
//            console.log("ben trong the input xay ra su kien",e.target.getAttribute("name"))
//            console.log(item.getElementsByTagName("tbody")[0].getElementsByClassName("g8")[0].innerHTML);
            let objectDataXs = {
                'g8':[],
                'g7':[],
                'g61':[],
                'g62':[],
                'g63':[],
                'g5':[],
                'g41':[],
                'g42':[],
                'g43':[],
                'g44':[],
                'g45':[],
                'g46':[],
                'g47':[],
                'g31':[],
                'g32':[],
                'g2':[],
                'g1':[],
                'db':[],
            }
            // 1: lay the tbody cua bang table dang xay ra
            const tbody = item.getElementsByTagName("tbody");
            // 2: lay the td chua cac giai xo so de lay duoc du lieu cac g8
            const tdG8 = tbody[0].getElementsByClassName("g8");
            const tdG7 = tbody[0].getElementsByClassName("g7");
            const tdG61 = tbody[0].getElementsByClassName("g61");
            const tdG62 = tbody[0].getElementsByClassName("g62");
            const tdG63 = tbody[0].getElementsByClassName("g63");
            const tdG5 = tbody[0].getElementsByClassName("g5");
            const tdG41 = tbody[0].getElementsByClassName("g41");
            const tdG42 = tbody[0].getElementsByClassName("g42");
            const tdG43 = tbody[0].getElementsByClassName("g43");
            const tdG44 = tbody[0].getElementsByClassName("g44");
            const tdG45 = tbody[0].getElementsByClassName("g45");
            const tdG46 = tbody[0].getElementsByClassName("g46");
            const tdG47 = tbody[0].getElementsByClassName("g47");
            const tdG31 = tbody[0].getElementsByClassName("g31");
            const tdG32 = tbody[0].getElementsByClassName("g32");
            const tdG2 = tbody[0].getElementsByClassName("g2");
            const tdG1 = tbody[0].getElementsByClassName("g1");
            const tdGdb = tbody[0].getElementsByClassName("gdb");
            //3: add du lieu xo so vao object giu data
            // kiem tra objectDataXs co du lieu chua chi add du lieu cho lan dau tien
            // tuc la thuoc tinh objectHoldData[index] = "" thi no chua co du lieu
            // muc dich chi add data cho lan dau tien
            if(!objectHoldData[index]){
                addDataToObjectXs(objectDataXs,'g8',tdG8);
                addDataToObjectXs(objectDataXs,'g7',tdG7);
                addDataToObjectXs(objectDataXs,'g61',tdG61);
                addDataToObjectXs(objectDataXs,'g62',tdG62);
                addDataToObjectXs(objectDataXs,'g63',tdG63);
                addDataToObjectXs(objectDataXs,'g5',tdG5);
                addDataToObjectXs(objectDataXs,'g41',tdG41);
                addDataToObjectXs(objectDataXs,'g42',tdG42);
                addDataToObjectXs(objectDataXs,'g43',tdG43);
                addDataToObjectXs(objectDataXs,'g44',tdG44);
                addDataToObjectXs(objectDataXs,'g45',tdG45);
                addDataToObjectXs(objectDataXs,'g46',tdG46);
                addDataToObjectXs(objectDataXs,'g47',tdG47);
                addDataToObjectXs(objectDataXs,'g31',tdG31);
                addDataToObjectXs(objectDataXs,'g32',tdG32);
                addDataToObjectXs(objectDataXs,'g2',tdG2);
                addDataToObjectXs(objectDataXs,'g1',tdG1);
                addDataToObjectXs(objectDataXs,'db',tdGdb);
                objectHoldData[index] = objectDataXs;// them du lieu vao data
            }
//            console.log(objectHoldData)
            // 4: kiem tra chuc nang nhan duoc tu the input checkbox la day du, hay 2 so, hay 3 so ma thuc hien
            // hien thi du lieu len bang lai
            let valueInputRadio = e.target.value;
//            console.log(valueInputRadio)
            // hien thi noi dung the tbody 3 so
            if(valueInputRadio == '3 so'){
                showTableByNumBerPasses(objectHoldData[index],3,tdG7,tdG61,tdG62,tdG63,tdG5,tdG41,tdG42,tdG43,tdG44,tdG45,tdG46,tdG47,
                tdG31,tdG32,tdG2,tdG1,tdGdb);
            }
            // hien thi noi dung 2 so
            if(valueInputRadio == '2 so'){
                showTableByNumBerPasses(objectHoldData[index],2,tdG7,tdG61,tdG62,tdG63,tdG5,tdG41,tdG42,tdG43,tdG44,tdG45,tdG46,tdG47,
                    tdG31,tdG32,tdG2,tdG1,tdGdb);
            }
            // hien thi noi dung day du
            if(valueInputRadio == 'full'){
                showTableByNumBerPasses(objectHoldData[index],6,tdG7,tdG61,tdG62,tdG63,tdG5,tdG41,tdG42,tdG43,tdG44,tdG45,tdG46,tdG47,
                    tdG31,tdG32,tdG2,tdG1,tdGdb);
            }


        }

    });

});
