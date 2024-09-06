--  xóa database siteveso nếu nó tồn tại
drop database if Exists siteveso_2;

-- tạo database siteveso
create database siteveso_2;

-- thông báo với MySQL là sẽ làm viêc vói database siteveso
use siteveso_2;


-- tạo bảng roles
create table roles(
id int not null auto_increment,
role varchar(20) not null,

primary key(id )

)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- tạo table User 
create table accounts(
id int auto_increment not null,
first_name varchar(30) not null,
last_name varchar(30) not null,
username varchar(30) not null,
pw varchar(70) not null,
email varchar(70) not null,
phone varchar(12) not null,
register_date date not null,
enable int not null,
status int not null,
id_role int ,
key index_id_role (id_role),
unique (phone),
constraint fk_idRole foreign key(id_role) references roles(id),

primary key(id ),-- khoa chinh,
constraint uni_account unique (username,email,phone)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;





-- thêm giá trị vào bảng roles
insert into roles(role)
value('ROLE_ADMIN'),
('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),

('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),

('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),

('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),

('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER'),
('ROLE_USER');

-- thêm giá trị vào table accounts , pw mac dinh = luan123
insert into accounts(first_name, last_name, username, pw, email,phone, register_date, enable,status,id_role)
values('Kien','Nguyen','Piennguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhluan0@gmail.com','0941233120','2024-01-30',1,1,10),
('Lien','Tran','Lientran','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','tLien0@gmail.com','0985912722','2024-01-30',1,0,11),
('Long','Nguyen','Longnguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nLong0@gmail.com','0985913723','2024-01-30',1,0,12),
('Uyen','Nguyen','Uyennguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhUyen0@gmail.com','0123723722','2024-01-30',1,0,13),
('Nhat','Nguyen','Nhatnguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nlongnhat0@gmail.com','0234524913','2024-01-30',1,0,14),
('Mo','Nguyen','Monguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nmo0@gmail.com','0982123145','2024-01-30',1,0,15),

('Kien','Nguyen','Pianguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhylan0@gmail.com','0941233121','2024-01-30',1,1,16),
('Lien','Tran','LientranQ','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','tLien110@gmail.com','0985912723','2024-01-30',1,0,17),
('Long','Nguyen','LongnguyenH','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nLong220@gmail.com','0985913724','2024-01-30',1,0,18),
('Uyen','Nguyen','UyennguyenM','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhUyen3330@gmail.com','0123723725','2024-01-30',1,0,19),
('Nhat','Nguyen','NhatnguyenN','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nlongnha11t0@gmail.com','0234524916','2024-01-30',1,0,20),
('Mo','Nguyen','MonguyenB','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nmo2220@gmail.com','0982123175','2024-01-30',1,0,21),

('Kien','Nguyen','Pienn3guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhlu23an0@gmail.com','0941236120','2024-01-30',1,1,22),
('Lien','Tran','Lien4tran','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','tLie11n0@gmail.com','0985912422','2024-01-30',1,0,23),
('Long','Nguyen','Longn5guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','n34Long0@gmail.com','0985915723','2024-01-30',1,0,24),
('Uyen','Nguyen','Uyenn3guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhUêyen0@gmail.com','0123753722','2024-01-30',1,0,25),
('Nhat','Nguyen','Nhatng4uyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nlon44gnhat0@gmail.com','0234534913','2024-01-30',1,0,26),
('Mo','Nguyen','Monguy5en','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','n444mo0@gmail.com','0982123125','2024-01-30',1,0,27),

('Kien','Nguyen','Pienn22guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhluadsfn0@gmail.com','0949233120','2024-01-30',1,1,28),
('Lien','Tran','Lientr23an','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','tLien0@gsdmail.com','0985512722','2024-01-30',1,0,29),
('Long','Nguyen','Long23nguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nLoáng0@gmail.com','0986913723','2024-01-30',1,0,30),
('Uyen','Nguyen','Uyen3nguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhUysen0@gmail.com','0124723722','2024-01-30',1,0,31),
('Nhat','Nguyen','Nhat3223nguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nlfongnhat0@gmail.com','0233524913','2024-01-30',0,0,32),
('Mo','Nguyen','Monguy332en','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nmoa0@gmail.com','0980123145','2024-01-30',1,0,33),

('Kien','Nguyen','Pie2nn22guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhlu3adsfn0@gmail.com','09492331201','2024-01-30',1,1,34),
('Lien','Tran','Li3entr23an','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','tL3ien0@gsdmail.com','09855127221','2024-01-30',1,0,35),
('Long','Nguyen','Lon2g23nguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nL2oáng0@gmail.com','09869137231','2024-01-30',1,0,36),
('Uyen','Nguyen','Uy3en3nguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhUy3sen0@gmail.com','01247237221','2024-01-30',1,0,37),
('Nhat','Nguyen','Nh4at3223nguyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nlf3ongnhat0@gmail.com','02335249131','2024-01-30',1,0,38),
('Mo','Nguyen','Mon2guy332en','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nm2oa0@gmail.com','09801231451','2024-01-30',1,0,39),

('Kien','Nguyen','Pienn22gu33yen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhl22uadsfn0@gmail.com','10949233120','2024-01-30',1,1,40),
('Lien','Tran','Lientr3323an','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','tLie23n0@gsdmail.com','10985512722','2024-01-30',1,0,41),
('Long','Nguyen','Long23n3guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nLo12áng0@gmail.com','10986913723','2024-01-30',1,0,42),
('Uyen','Nguyen','Uyen3ngu3yen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nhUys32en0@gmail.com','10124723722','2024-01-30',1,0,43),
('Nhat','Nguyen','Nhat3223n3guyen','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nlfo3ngnhat0@gmail.com','10233524913','2024-01-30',1,0,44),
('Mo','Nguyen','Monguy332e3n','$2a$10$1zIQA6ZOVjJ/7GFAI9PsTefKgNEhbU5s8ROX6OWXmactQckKsINIa','nm212oa0@gmail.com','10980123145','2024-01-30',1,0,45);
