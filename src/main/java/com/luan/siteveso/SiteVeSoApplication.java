package com.luan.siteveso;

import com.luan.siteveso.dao.KetQuaXoSoRepository;
import com.luan.siteveso.dao.LichSuDoSoRepository;
import com.luan.siteveso.dao.LichSuSuaRepository;
import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.date.DateSql;
import com.luan.siteveso.email.EmailService;
import com.luan.siteveso.entity.KetQuaXoSo;
import com.luan.siteveso.entity.LichSuDoSo;
import com.luan.siteveso.entity.LichSuSua;
import com.luan.siteveso.entity.User;
import com.luan.siteveso.password.GenerateRandomPw;
import com.luan.siteveso.service.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SiteVeSoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteVeSoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(LichSuDoSoServiceImpl lichSuDoSoService) {
		return Runtime ->{
//			getPageByUserName(userRepository,0,3);

//			getUserByID(userServiceImpl,1);
//			updateUser(userServiceImpl);
			// tao password random
//			passwordRandom(generateRandomPw);
//			sendEmail(emailService);
//			System.out.println("ngay hien tai theo sql la: "+dateSql.getDateSqlCurrent());
//			getKetQuaXoSo(ketQuaXoSoRepository);
//			getKetQuaXoSoId(ketQuaXoSoService);
//			getAllKetQuaXoSo(ketQuaXoSoService);
//			getAllKetQua(ketQuaXoSoService);
//			getLichSuSuaByIdKq( lichSuSuaService);
//			deleteKqById( ketQuaXoSoRepository);
//			get8KqXsPhuYen( ketQuaXoSoServiceImpl);
//			getKqXsByTenDaiAndDateXs(ketQuaXoSoServiceImpl);
//			getKqXsByDateLottery("2023-03-13", ketQuaXoSoServiceImpl);
//			getKhuVucDaiXoSo(daiVeSoServiceImpl);
//			getByDateXS(ketQuaXoSoService);
//			getKqXs(ketQuaXoSoService);
//			getAllLichSuDoSo(lichSuDoSo,userService);
//			getLichSuSuaByUserName(lichSuDoSoService);
//			getAutoUpdateKqXsByApiFree();
		};


	}

	public  void  getAutoUpdateKqXsByApiFree() throws IOException {
		// Khởi tạo RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// URL của API RESTful cần gửi yêu cầu GET đến
		String apiUrl = "https://www.xoso.net/jquery/jquery-1.7.2.js";

		// Thực hiện yêu cầu GET và nhận phản hồi
		String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

		// In ra phản hồi từ API
		System.out.println("Phản hồi từ API: " + jsonResponse);
	}

	private void getLichSuSuaByUserName(LichSuDoSoServiceImpl lichSuDoSoService) {
		String userName = "Lientran";
		System.out.println("Lich su do so lay duon theo user name " + userName);
		List<LichSuDoSo> lichSuDoSoList = lichSuDoSoService.getLichSuDoSoByUserName(userName);
		for (LichSuDoSo ls: lichSuDoSoList
		) {
			System.out.println(ls);
		}
		System.out.println("Done!");
	}

	public void getAllLichSuDoSo(LichSuDoSoRepository lichSuDoSoRepository,UserServiceImpl userService){
		System.out.println("Lay tat ca thong tin lich su do so ");
		User user = userService.getUserById(2);
		System.out.println("thong tin user lay duoc : "+ user);
		List<LichSuDoSo> list = lichSuDoSoRepository.findByUser(user);
		for (LichSuDoSo ls: list
		) {
			System.out.println("THong tin lich su do so lay duoc");
			System.out.println(ls);
		}
		// Lấy thời gian hiện tại
		java.util.Date currentDate = new java.util.Date();

// Định dạng hiển thị giờ
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

// In ra giờ hiện tại
		System.out.println("Giờ hiện tại: " + formatter.format(currentDate));
	}

	private void getKqXs(KetQuaXoSoServiceImpl ketQuaXoSoService) {
		System.out.println("Lay danh sach ket qua xo theo ngay truyen vao ");
		List<List<KetQuaXoSo>> lists = ketQuaXoSoService.getListKqXsForPageHomeByNumPass(3);
		int i =0;
		for (List<KetQuaXoSo> ls:lists
		) {
			System.out.println("ket qua xo so theo vi tri so thu " + i);
			for (KetQuaXoSo kq:ls
			) {
				System.out.println(kq);
			}
			i++;
		}
		System.out.println("Done!");
	}

	private void getByDateXS(KetQuaXoSoServiceImpl ketQuaXoSoService) {
		// lay 3 ngay xo so
		System.out.println("Lay ket qua 3 ngay xo so ");
		List<Date> list = ketQuaXoSoService.getDateByNumberParamPasses(3);
		for (java.sql.Date date: list
		) {
			System.out.println(date);
		}
		System.out.println("Done!");
	}

	// lay mot trang theo ten sap xep tang dan theo ten
	public Page<User> getPageByUserName(UserRepository userRepository,int num,int size){
		Page<User> userList =
				userRepository.findAll(PageRequest.of(num,size,Sort.by("userName").ascending()));

		System.out.println("size Lise "+ userList.getSize() );
		System.out.println("total page " + userList.getTotalPages());
		System.out.println("So luong thanh vien " + userList.getTotalElements());
		return userList;
	}

	// tim 1 user theo id
    public  void getUserByID(UserServiceImpl userServiceImpl,int id){
		User user=null;
		try {
			user= userServiceImpl.getUserById(id);
		}catch (Exception e){
			System.out.println(e);
		}

		if(user == null){
			System.out.println("User khong ton tai "+ user);
		}else{
			System.out.println("User ton tai "+ user);
		}

	}

	// update 1 user
	public void updateUser(UserServiceImpl userServiceImpl){
		User user = userServiceImpl.updateUser(2);
		System.out.println("User da update " + user);
	}

	public void  passwordRandom(GenerateRandomPw generateRandomPw){
		String pw =  generateRandomPw.generateSecurePassword();
		System.out.println("Password random la : " + pw);
		System.out.println("Password dc ma hoa la : " + generateRandomPw.encodePw(pw));

	}

	public void sendEmail(EmailService emailService){
		String to = "nhluan0@gmail.com";
		String subject ="Reset lại mật khẩu mới";
		String body = "Abvxaaa#";
		emailService.sendEmail(to,subject,body);
	}

	// lay ket qua xo so theo id
	public void getKetQuaXoSo(KetQuaXoSoRepository ketQuaXoSoRepository){
		int id = 1;
		KetQuaXoSo ketQuaXoSo = ketQuaXoSoRepository.getReferenceById(id);
		System.out.println("Thong tin ket qua xo so: ");
		System.out.println(ketQuaXoSo);
//		System.out.println("thong tin nguoi tao : " + ketQuaXoSo.getUser());
		System.out.println("Done!");
	}

	// lay ket qua xo so theo id
	public void getKetQuaXoSoId(KetQuaXoSoServiceImpl ketQuaXoSoService){
		int id = 2;
		KetQuaXoSo ketQuaXoSo = ketQuaXoSoService.getKetQuaXoSoById(id);
		System.out.println("Thong tin ket qua xo so: ");
		System.out.println(ketQuaXoSo);
//		System.out.println("thong tin nguoi tao : " + ketQuaXoSo.getUser());
		System.out.println("Done!");
	}

	// get All ket qua xo so
	public void getAllKetQuaXoSo(KetQuaXoSoServiceImpl ketQuaXoSoService){
		System.out.println("Get all Ket qua xo so ");
		List<KetQuaXoSo>  ketQuaXoSos = ketQuaXoSoService.getAllKetQuaXoSo();
		for (KetQuaXoSo kq: ketQuaXoSos
		) {
			System.out.println(kq);
//			System.out.println("Thong tin user : " + kq.getUser());
		}
		System.out.println("Done!");
	}

	public void getAllKetQua(KetQuaXoSoServiceImpl  ketQuaXoSoService){
		//xet id
		int id = 2;
		System.out.println("get full info , have info user create :");
		System.out.println("get kq xo so theo id "+ id);
		KetQuaXoSo ketQuaXoSo = ketQuaXoSoService.getUserForKetQuaXoSoById(id);
		System.out.println(ketQuaXoSo);
		System.out.println("Thong tin admin tao ");
		System.out.println(ketQuaXoSo.getUser());
		System.out.println("Done!");
	}

	// lay danh sach lich su sua  theo idkq xo so  truyen vao
	public void getLichSuSuaByIdKq(LichSuSuaServiceImpl lichSuSuaService){
		//set id cua ket qua Xo so
		int id =1;
		System.out.println("Lay danh sach bang LichSuSua theo id= "+id);
		List<LichSuSua> lichSuSuas = lichSuSuaService.getLichSuSuaByIdKq(id);
		for (LichSuSua o: lichSuSuas
		) {
			System.out.println(o);

		}
		System.out.println("Done!");
	}

	public void deleteKqById(KetQuaXoSoRepository ketQuaXoSoRepository){
		int id = 1;
		System.out.println("id xoa of ket qua la id= "+id);
		ketQuaXoSoRepository.deleteById(id);
		System.out.println("Đã xóa xong");
	}

	// lay thu ket qua dai phu yen chi lay 8 ket qua
	public void get8KqXsPhuYen(KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl){
		String tenDai = "Phú Yên";
		List<KetQuaXoSo> listKqXs= new ArrayList<>();
		listKqXs = ketQuaXoSoServiceImpl.get8KetQuaXoSoByTenDai(tenDai,1,10);
		System.out.println("tam Ket qua xo so Phu Yen ");
		for (KetQuaXoSo kq: listKqXs
		) {
			System.out.println(kq);
		}
		System.out.println("Done!");
		System.out.println("Dem bao nhieu ket qua xo so theo ten đài Phú Yên");
		System.out.println(ketQuaXoSoServiceImpl.countKqXsByTenDai(tenDai));
	}

	// lay ket qua ve so theo ten dai va ngay
	public void getKqXsByTenDaiAndDateXs(KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl){
		String tenDai = "Phú Yên";
		String dateString = "2024-03-28";
		DateSql dateSql = new DateSql();
		dateSql.parseStringToDateSql(dateString);
		List<KetQuaXoSo> ketQuaXoSo = ketQuaXoSoServiceImpl.getKqXsByTenDaiAndDateXs(tenDai,dateSql.parseStringToDateSql(dateString));
		System.out.println("Ket qua xo so theo ten dai va ngay truyen vao ");

		for (KetQuaXoSo kq: ketQuaXoSo
		) {
			System.out.println(kq);
		}
	}

	// lay kqxs theo ngay xo so truyen vao
//	public void getKqXsByDateLottery(String dateString,KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl){
//		DateSql dateSql = new DateSql();
//		dateSql.parseStringToDateSql(dateString);
//		List<KetQuaXoSo> ketQuaXoSos = ketQuaXoSoServiceImpl.getKqXsByDateLottery(dateSql.parseStringToDateSql(dateString));
//		for (KetQuaXoSo kq:ketQuaXoSos
//		) {
//			System.out.println("Ket qua theo Date Lottery ");
//			System.out.println(kq);
//		}
//	}
	public void getKhuVucDaiXoSo(DaiVeSoServiceImpl daiVeSoServiceImpl){
		List<String> kqList = daiVeSoServiceImpl.getDistinctByKhuVuc();
		System.out.println("Ket qua khu vuc");
		for (String kq:kqList
		) {
			System.out.println(kq);
		}
		System.out.println("Done!");
	}

}
