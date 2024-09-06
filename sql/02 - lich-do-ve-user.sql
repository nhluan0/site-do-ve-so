
-- thông báo với MySQL là sẽ làm viêc vói database siteveso
use siteveso_2;

drop table if exists lichsudove;
drop table if exists tienthuong;

-- tao bang tien thuong
create table tienthuong(
id int not null auto_increment,
giai varchar(10),
tien_thuong varchar(14),
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; -- bat dau id bang 1 

-- tao bang table lichsudoso
create table lichsudove(
id int not null auto_increment,
id_user int,
id_kq integer,
date_search date,
hour_search  varchar(5),
sodo varchar(6),
kqds varchar(50),
username varchar(200),
id_tienthuong int,
status varchar(8),
primary key (id),
Constraint fk_kqlsds foreign key(id_kq) references ketqua(id), -- khoa ngoai
Constraint fk_userlsds foreign key(id_user) references accounts(id),
constraint fk_tienthuong foreign key(id_tienthuong) references tienthuong(id),
key index_dai (id_user) -- index  -- khoa ngoai
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; -- bat dau id bang 1 



-- chen bang tien thuong 
insert into  tienthuong(giai,tien_thuong)
value("g1","30.000.000đ"),
("g2","15.000.000đ"),
("g3","10.000.000đ"),
("g4","3.000.000đ"),
("g5","1.000.000đ"),
("g6","400.000đ"),
("g7","200.000đ"),
("g8","100.000đ"),
("db","2.000.000.000đ");

-- chen gia tri vao bang lichsudoso
insert into lichsudove(id_user,id_kq,date_search,hour_search,sodo,kqds,status)
values(2,1,"2024-04-05","10:45","123456","bạn không trúng giải","chưa xóa" ),
(3,2,"2024-04-01","08:20","123456","bạn không trúng giải","chưa xóa"),
(4,1,"2024-04-01","08:20","123456","bạn không trúng giải","chưa xóa"),
(5,1,"2024-03-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(2,1,"2024-04-01","08:20","123456", "bạn không trúng giải","chưa xóa"),
(2,348,"2024-04-02","08:20","123456", "bạn không trúng giải","chưa xóa"),
(2,1,"2024-04-03","08:20","123456", "bạn không trúng giải","chưa xóa"),
(2,1,"2025-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(2,6,"2024-04-04","08:20","123456", "bạn không trúng giải","chưa xóa"),
(2,1,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(7,9,"2024-03-01","08:20","123456", "bạn không trúng giải","chưa xóa"),
(8,1,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(26,11,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(13,11,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(16,17,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(18,19,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),

(1,22,"2024-04-01","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,11,"2024-04-01","08:20","123456", "bạn không trúng giải","chưa xóa"),
(11,11,"2024-03-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(22,12,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,348,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,12,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(22,13,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,61,"2024-04-04","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,12,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,92,"2024-03-01","08:20","123456", "bạn không trúng giải","chưa xóa"),
(1,121,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(26,111,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(13,111,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(16,11,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(18,11,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa"),
(21,11,"2024-04-05","08:20","123456", "bạn không trúng giải","chưa xóa");

