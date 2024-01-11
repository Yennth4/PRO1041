CREATE DATABASE db_duan1
go
USE db_duan1
go

-- Mạnh
CREATE TABLE nhan_vien (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    ten NVARCHAR(100) NOT NULL,
    ma_dinh_danh NVARCHAR(20) NOT NULL,
    so_dien_thoai NVARCHAR(15) NOT NULL,
    ngay_sinh DATE NOT NULL,
    email NVARCHAR(100) NOT NULL,
    gioi_tinh NVARCHAR(10) NOT NULL,
    dia_chi NVARCHAR(200) NOT NULL,
    trang_thai NVARCHAR(20) NOT NULL,
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE()
);

INSERT INTO nhan_vien (username, ten, ma_dinh_danh, so_dien_thoai, ngay_sinh, email, gioi_tinh, dia_chi, trang_thai)
VALUES
    ('manhsiu1', N'Nguyễn Văn Anh', '01020038122', '0901234561', '1990-01-01', 'anhnv123@email.com', N'Nam', N'Hà Giang', N'Đang Làm'),
    ('manhsiu2', N'Nguyễn Thị Bình', '01020038123', '0901234562', '1992-05-15', 'binhnt@email.com', N'Nữ', N'Hà Nội', N'Đang Làm'),
    ('manhsiu3', N'Trần Văn Minh', '01020038124', '0901234563', '1985-08-20', 'minhtv@email.com', N'Nam', N'Thái Nguyên', N'Đang Làm'),
    ('manhsiu4', N'Lê Thị Hương', '01020038125', '0901234564', '1998-03-10', 'huonglt@email.com', N'Nữ', N'Quảng Ninh', N'Đang Làm'),
    ('manhsiu5', N'Ngô Văn Đức', '01020038126', '0901234565', '1987-11-28', 'ducnv@email.com', N'Nam', N'Hải Phòng', N'Đang Làm'),
    ('manhsiu6', N'Phạm Thị Mai', '01020038127', '0901234566', '1995-09-03', 'maipt@email.com', N'Nữ', N'Thái Bình', N'Đang Làm'),
    ('manhsiu7', N'Hoàng Văn Tuấn', '01020038128', '0901234567', '1991-07-12', 'tuanhv@email.com', N'Nam', N'Bắc Ninh', N'Đang Làm'),
    ('manhsiu8', N'Mai Thị Hạnh', '01020038129', '0901234568', '1989-04-18', 'hanhmt@email.com', N'Nữ', N'Hưng Yên', N'Đang Làm'),
    ('manhsiu9', N'Đỗ Văn Đông', '01020038130', '0901234569', '1986-02-25', 'dongdv@email.com', N'Nam', N'Nam Định', N'Đang Làm'),
    ('manhsiu10', N'Lê Thị Thu', '01020038131', '0901234570', '1993-06-14', 'thult@email.com', N'Nữ', N'Ninh Bình', N'Đang Làm'),
    ('manhsiu11', N'Trần Văn Trung', '01020038132', '0901234571', '1984-12-08', 'trungtv@email.com', N'Nam', N'Thanh Hóa', N'Đang Làm'),
    ('manhsiu12', N'Nguyễn Thị Ngọc', '01020038133', '0901234572', '1997-10-05', 'ngocnt@email.com', N'Nữ', N'Hà Nam', N'Đang Làm'),
    ('manhsiu13', N'Bùi Văn Long', '01020038134', '0901234573', '1994-04-30', 'longbv@email.com', N'Nam', N'Phú Thọ', N'Đang Làm'),
    ('manhsiu14', N'Phạm Thị Hoa', '01020038135', '0901234574', '1988-08-15', 'hoapt@email.com', N'Nữ', N'Vĩnh Phúc', N'Đang Làm'),
    ('manhsiu15', N'Nguyễn Văn Hoàng', '01020038136', '0901234575', '1996-01-20', 'hoangnv@email.com', N'Nam', N'Lào Cai', N'Đang Làm'),
    ('manhsiu16', N'Vũ Thị Lan', '01020038137', '0901234576', '1990-09-22', 'lanvt@email.com', N'Nữ', N'Yên Bái', N'Đang Làm'),
    ('manhsiu17', N'Đinh Văn Hải', '01020038138', '0901234577', '1985-03-17', 'haidv@email.com', N'Nam', N'Hòa Bình', N'Đang Làm'),
    ('manhsiu18', N'Lê Thị Huệ', '01020038139', '0901234578', '1998-07-03', 'huetl@email.com', N'Nữ', N'Sơn La', N'Đang Làm'),
    ('manhsiu19', N'Phan Văn Phúc', '01020038140', '0901234579', '1987-05-28', 'phucpv@email.com', N'Nam', N'Lạng Sơn', N'Đang Làm'),
    ('manhsiu20', N'Trần Thị Loan', '01020038141', '0901234580', '1991-11-10', 'loanth@email.com', N'Nữ', N'Bắc Giang', N'Đang Làm'),
('manhsiu21', N'Nguyễn Thanh Hải', '01020038142', '0901234581', '1993-04-25', 'hainguyen@email.com', N'Nam', N'Quảng Nam', N'Đang Làm'),
    ('manhsiu22', N'Lê Thị Mai Anh', '01020038143', '0901234582', '1995-08-18', 'maianh@email.com', N'Nữ', N'Đà Nẵng', N'Đang Làm'),
    ('manhsiu23', N'Trần Văn Bình', '01020038144', '0901234583', '1988-12-30', 'binhtv@email.com', N'Nam', N'Bình Định', N'Đang Làm'),
    ('manhsiu24', N'Phạm Thị Thúy', '01020038145', '0901234584', '1990-02-10', 'thuypt@email.com', N'Nữ', N'Phú Yên', N'Đang Làm'),
    ('manhsiu25', N'Nguyễn Văn Hòa', '01020038146', '0901234585', '1986-06-08', 'hoanv@email.com', N'Nam', N'Khánh Hòa', N'Đang Làm');





CREATE TABLE phan_quyen (
    ID BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    password NVARCHAR(255) NOT NULL, 
    role NVARCHAR(50) NOT NULL,
    nhan_vien_id BIGINT,
    FOREIGN KEY (nhan_vien_id) REFERENCES nhan_vien(id)
);

	INSERT INTO phan_quyen (username, password, role, nhan_vien_id)
VALUES
	('1', '1', 'admin', 1),
    ('admin', '123', 'admin', 2),
    ('user', '123', 'nhanvien', 3),
    ('manhsiu3', 'pass3', 'nhanvien', 3),
	('manhsiu4', 'pass4', 'nhanvien', 4),
	('manhsiu5', 'pass5', 'admin', 5),
	('manhsiu6', 'pass6', 'nhanvien', 6),
	('manhsiu7', 'pass7', 'nhanvien', 7),
	('manhsiu8', 'pass8', 'admin', 8),
	('manhsiu9', 'pass9', 'nhanvien', 9),
	('manhsiu10', 'pass10', 'admin', 10),
	('manhsiu11', 'pass11', 'admin', 11),
	('manhsiu12', 'pass12', 'nhanvien', 12),
	('manhsiu13', 'pass13', 'nhanvien', 13),
	('manhsiu14', 'pass14', 'admin', 14),
	('manhsiu15', 'pass15', 'nhanvien', 15),
	('manhsiu16', 'pass16', 'admin', 16),
	('manhsiu17', 'pass17', 'nhanvien', 17),
	('manhsiu18', 'pass18', 'admin', 18),
	('manhsiu19', 'pass19', 'nhanvien', 19),
	('manhsiu20', 'pass20', 'admin', 20),
	('manhsiu21', 'pass21', 'nhanvien', 21),
	('manhsiu22', 'pass22', 'nhanvien', 22),
	('manhsiu23', 'pass23', 'admin', 23),
	('manhsiu24', 'pass24', 'nhanvien', 24),
	('manhsiu25', 'pass25', 'admin', 25);

	

-- Long
CREATE TABLE NhaCungCap(
	IDNhaCungCap BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenNhaCungCap NVARCHAR(50) UNIQUE NOT NULL,
	MoTaNhaCungCap NVARCHAR(255) DEFAULT NULL,
	TrangThai BIT NOT NULL,
	SDTNhaCungCap VARCHAR(11) NOT NULL,
	EmailNhaCungCap VARCHAR(255) NOT NULL,
	HopDongThoaThuan VARCHAR(255) NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE(),
	Ma VARCHAR(6) NOT NULL UNIQUE
) 


CREATE TABLE Mau(
	IDMau BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenMau NVARCHAR(50) UNIQUE NOT NULL,
	MoTaMau NVARCHAR(255) DEFAULT NULL,
	TrangThai BIT NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE(),
	Ma VARCHAR(6) NOT NULL
)

			
CREATE TABLE TheLoai(
	IDTheLoai BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MaTheLoai VARCHAR(6) UNIQUE NOT NULL,
	TenTheLoai NVARCHAR(255) UNIQUE NOT NULL,
	MoTaTheLoai NVARCHAR(255) DEFAULT NULL,
	TrangThai BIT NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE()
)


	

CREATE TABLE SanPham(
	IDSanPham BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MaSanPham VARCHAR(6) NOT NULL,
	TenSanPham NVARCHAR(255) UNIQUE NOT NULL,
	MoTaSanPham NVARCHAR (255) NOT NULL,
	IDNhaCungCap BIGINT NOT NULL FOREIGN KEY REFERENCES NhaCungCap(IDNhaCungCap),
	TrangThai BIT NOT NULL,
	IDTheLoai BIGINT NOT NULL FOREIGN KEY REFERENCES TheLoai(IDTheLoai),
	IDNhanVien BIGINT NOT NULL FOREIGN KEY REFERENCES nhan_vien(id),
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE()
)



	
CREATE TABLE SanPhamChiTiet(
	IDSanPhamChiTiet BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SoLuong INT NOT NULL,
	TrangThai BIT NOT NULL,
	MainImage VARCHAR(255) NOT NULL,
	IDSanPham BIGINT NOT NULL FOREIGN KEY REFERENCES SanPham(IDSanPham),
	IDMau BIGINT NOT NULL FOREIGN KEY REFERENCES Mau(IDMau),
	GiaBan FLOAT NOT NULL,
	ThoiGianTao DATETIME DEFAULT GETDATE(),
	ThoiGianSua DATETIME DEFAULT GETDATE(),
	Ma VARCHAR(6) NOT NULL UNIQUE,
	GiaNiemYet FLOAT NOT NULL
)


	

-- NCC
INSERT INTO NhaCungCap(TenNhaCungCap,MoTaNhaCungCap,TrangThai,SDTNhaCungCap,EmailNhaCungCap,HopDongThoaThuan, Ma) 
	VALUES	(N'Xưởng Long Biên', N'Cung cấp đồ da bò chất lượng cao cấp',1,'0969315806', 'philong.inco@gmail.com','documents\ncc_hdcc\xuongdodacaugiay.docx','CC98MK'),
			(N'Xưởng Thái Thịnh', N'Cung cấp đồ da cừu',1,'0926815127', 'dathaithinh8@gmail.com','documents\ncc_hdcc\xuongdodacaugiay.docx','CCAAC5'),
			(N'Xưởng Hoàng Cầu', N'Cung cấp đồ da bê',1,'0969315278', 'dahoangcau2@gmail.com','documents\ncc_hdcc\xuongdodacaugiay.docx','CCA4H7'),
			(N'Xưởng Ngọc Thụy', N'Cung cấp đồ da trâu',1,'0929315231', 'dangocthuy9@gmail.com','documents\ncc_hdcc\xuongdodacaugiay.docx','CCKF5H'),
			(N'Xưởng Gia Quất', N'Cung cấp đồ da cá sấu',1,'0959315117', 'giaquay21caocap@gmail.com','documents\ncc_hdcc\xuongdodacaugiay.docx','CCDJRV'),
			(N'Xưởng Thái Bình', N'Cung cấp đồ da đà điểu',1,'0939315890', 'thaibinhxuongda22@gmail.com','documents\ncc_hdcc\xuongdodacaugiay.docx','CCVH51')


-- Mau
INSERT INTO Mau(TenMau,MoTaMau,TrangThai, Ma)
	VALUES	(N'Đỏ', N'Màu đỏ', 1, 'MAA111'),
			(N'Xanh lá', N'Màu xanh lá cây', 1, 'MAA222'),
			(N'Xanh dương', N'Màu xanh nước biển', 1, 'MAA333'),
			(N'Đen', N'Màu đen huyền bí', 1, 'MAA444'),
			(N'Trắng', N'Màu trắng', 1, 'MAA555'),
			(N'Vàng', N'Màu vàng', 1, 'MAA666'),
			(N'Hồng', N'Màu hồng', 1, 'MAA777'),
			(N'Nâu', N'Màu nâu', 1, 'MAA888')

-- TL
INSERT INTO TheLoai(MaTheLoai,TenTheLoai,MoTaTheLoai,TrangThai) 
VALUES		('TLA111', N'Chưa phân loại', N'Các sản phẩm chưa được phân loại', 1),
			('TLA222', N'Túi hàng hiệu', N'Túi xách hàng hiệu từ các nhãn hàng nổi tiếng', 1),
			('TLA333', N'Túi xách tay', N'Túi xách tay nhỏ gọn thời trang', 1),
			('TLA444', N'Túi đeo vai', N'Túi đeo vai tinh tế cách điệu', 1),
			('TLA555', N'Túi đeo chéo', N'Túi xách đeo chéo linh hoạt', 1),
			('TLA666', N'Túi cầm tay', N'Túi cầm tay thời trang', 1)

-- SP
INSERT INTO SanPham(MaSanPham,TenSanPham,MoTaSanPham,IDNhaCungCap,TrangThai,IDTheLoai,IDNhanVien)
	VALUES	('SPA111',N'Túi đeo vai cao cấp', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,1,1),
			('SPA222',N'Túi xách cao cấp', N'1 Ngăn lớn, 3 ngăn nhỏ',1,2,1,1),
			('SPA333',N'Túi nữ dây đeo', N'1 Ngăn lớn, 3 ngăn nhỏ',1,3,1,1),
			('SPA444',N'Túi hoàng kim', N'1 Ngăn lớn, 3 ngăn nhỏ',1,4,1,1),
			('SPA555',N'Túi xách nữ cao cấp', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,1,1),

			('SPA666',N'Túi xách dây kéo', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPA777',N'Túi xách da bò', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPA888',N'Túi xách da cừu', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPA999',N'Túi xách da đà điểu', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPB111',N'Túi xách da trâu', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),

			('SPB222',N'Túi xách cách điệu dây gài', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,3,1),
			('SPB333',N'Túi xách cách điệu kéo kim loại', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,3,1),
			('SPB444',N'Túi xách cách điệu thắt dây', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,3,1),
			('SPB555',N'Túi xách cách điệu PU', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,4,1),
			('SPB666',N'Túi xách cách điệu chéo', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,4,1),

			('SPB777',N'Túi cầm tay cao cấp', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPB888',N'Túi cầm tay dáng xinh', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPB999',N'Túi cầm tay nữ da', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPC111',N'Túi cầm tay chất lượng cao', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPC222',N'Túi cầm tay xu thế', N'1 Ngăn lớn, 3 ngăn nhỏ',1,3,5,1),

			('SPC333',N'Túi xách tay sang trọng mùa hè', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC444',N'Túi xách tay sang trọng mùa thu', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC555',N'Túi xách tay sang trọng mùa đông', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC666',N'Túi xách tay sang trọng tiệc cưới', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC777',N'Túi xách tay sang trọng dạ hội', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),

			('SPC888',N'Túi kỷ niệm hàng limited', N'1 Ngăn lớn, 3 ngăn nhỏ',6,4,1,1),
			('SPC999',N'Túi kỷ niệm Channel', N'1 Ngăn lớn, 3 ngăn nhỏ',6,4,1,1)


-- SPCT
INSERT INTO SanPhamChiTiet(SoLuong,TrangThai,MainImage,IDSanPham,IDMau,GiaBan,GiaNiemYet, Ma) VALUES
	(100, 1, 'documents\img_sp\1a.png',1 , 4, 350000,350000, 'CTA111'),
	(100, 1, 'documents\img_sp\1b.png',1 , 5, 350000,350000, 'CTA222'),

	(100, 1, 'documents\img_sp\2a.png',2 , 7, 420000,420000, 'CTB111'),
	(100, 1, 'documents\img_sp\2b.png',2 , 4, 420000,420000, 'CTB222'),

	(100, 1, 'documents\img_sp\3a.png',3 , 5, 230000,230000, 'CTC111'),
	(100, 1, 'documents\img_sp\3b.png',3 , 4, 230000,230000, 'CTC222'),
	(100, 1, 'documents\img_sp\3c.png',3 , 3, 230000,230000, 'CTC333'),

	(100, 1, 'documents\img_sp\4a.png',4 , 5, 330000,330000, 'CTD111'),
	(100, 1, 'documents\img_sp\4b.png',4 , 4, 330000,330000, 'CTD222'),

	(100, 1, 'documents\img_sp\5a.png',5 , 5, 410000,410000, 'CTE111'),
	(100, 1, 'documents\img_sp\5b.png',5 , 4, 410000,410000, 'CTE222'),

	(100, 1, 'documents\img_sp\6a.png',6 , 4, 550000,550000, 'CTG111'),
	(100, 1, 'documents\img_sp\6b.png',6 , 5, 550000,550000, 'CTG222'),

	(100, 1, 'documents\img_sp\7a.png',7 , 6, 270000,270000, 'CTH111'),
	(100, 1, 'documents\img_sp\7b.png',7 , 4, 270000,270000, 'CTH222'),
	(100, 1, 'documents\img_sp\7c.png',7 , 5, 270000,270000, 'CTH333'),

	(100, 1, 'documents\img_sp\8a.png',8 , 4, 370000,370000, 'CTF111'),
	(100, 1, 'documents\img_sp\8b.png',8 , 5, 370000,370000, 'CTF222'),
	(100, 1, 'documents\img_sp\8c.png',8 , 2, 370000,370000, 'CTF333'),

	(100, 1, 'documents\img_sp\9a.png',9 , 4, 325000,325000, 'CTI111'),
	(100, 1, 'documents\img_sp\9b.png',9 , 5, 325000,325000, 'CTI222'),

	(100, 1, 'documents\img_sp\10a.png',10 , 6, 190000,190000, 'CTK111'),
	(100, 1, 'documents\img_sp\10b.png',10 , 5, 190000,190000, 'CTK222'),
	(100, 1, 'documents\img_sp\10c.png',10 , 6, 190000,190000, 'CTK333'),

	(100, 1, 'documents\img_sp\11a.png',11 , 4, 790000,790000, 'CTL111'),
	(100, 1, 'documents\img_sp\11b.png',11 , 8, 790000,790000, 'CTL222'),

	(100, 1, 'documents\img_sp\12a.png',12 , 4, 880000,880000, 'CTO111'),
	(100, 1, 'documents\img_sp\12b.png',12 , 7, 880000,880000, 'CTO222'),
	(100, 1, 'documents\img_sp\12c.png',12 , 6, 880000,880000, 'CTO333'),

	(100, 1, 'documents\img_sp\13a.png',13 , 4, 740000,740000, 'CTN111'),
	(100, 1, 'documents\img_sp\13b.png',13 , 1, 740000,740000, 'CTN222'),

	(100, 1, 'documents\img_sp\14a.png',14 , 4, 660000,660000, 'CTM111'),
	(100, 1, 'documents\img_sp\14b.png',14 , 8, 660000,660000, 'CTM222'),

	(100, 1, 'documents\img_sp\15a.png',15 , 4, 690000,690000, 'CTX111'),
	(100, 1, 'documents\img_sp\15b.png',15 , 1, 690000,690000, 'CTX222'),
	(100, 1, 'documents\img_sp\15c.png',15 , 7, 690000,690000, 'CTX333'),

	(100, 1, 'documents\img_sp\16a.png',16 , 4, 880000,880000, 'CTW111'),
	(100, 1, 'documents\img_sp\16b.png',16 , 1, 880000,880000, 'CTW222'),
	(100, 1, 'documents\img_sp\16c.png',16 , 7, 880000,880000, 'CTW333'),
(100, 1, 'documents\img_sp\16d.png',16 , 3, 880000,880000, 'CTW444'),
	(100, 1, 'documents\img_sp\16e.png',16 , 5, 880000,880000, 'CTW555'),

	(100, 1, 'documents\img_sp\17a.png',17 , 4, 340000,340000, 'CTT111'),
	(100, 1, 'documents\img_sp\17b.png',17 , 1, 340000,340000, 'CTT222'),
	(100, 1, 'documents\img_sp\17c.png',17 , 7, 340000,340000, 'CTT333'),

	(100, 1, 'documents\img_sp\18a.png',18 , 4, 440000,440000, 'CTAA11'),
	(100, 1, 'documents\img_sp\18b.png',18 , 7, 440000,440000, 'CTAA22'),
	(100, 1, 'documents\img_sp\18c.png',18 , 5, 440000,440000, 'CTAA33'),
	(100, 1, 'documents\img_sp\18d.png',18 , 6, 440000,440000, 'CTAA44'),

	(100, 1, 'documents\img_sp\19a.png',19 , 5, 459000,459000, 'CTBB11'),
	(100, 1, 'documents\img_sp\19b.png',19 , 4, 459000,459000, 'CTBB22'),
	(100, 1, 'documents\img_sp\19c.png',19 , 2, 459000,459000, 'CTBB33'),

	(100, 1, 'documents\img_sp\20a.png',20 , 7, 910000,910000, 'CTCC11'),
	(100, 1, 'documents\img_sp\20b.png',20 , 2, 910000,910000, 'CTCC22'),

	(100, 1, 'documents\img_sp\21a.png',21 , 4, 720000,720000, 'CTDD11'),
	(100, 1, 'documents\img_sp\21b.png',21 , 8, 720000,720000, 'CTDD22'),

	(100, 1, 'documents\img_sp\22a.png',22 , 4, 888000,888000, 'CTEE11'),
	(100, 1, 'documents\img_sp\22b.png',22 , 1, 888000,888000, 'CTEE22'),

	(100, 1, 'documents\img_sp\23a.png',23 , 4, 410000,410000, 'CTEE33'),
	(100, 1, 'documents\img_sp\23b.png',23 , 7, 410000,410000, 'CTEE44'),

	(100, 1, 'documents\img_sp\24a.png',24 , 7, 987000,987000, 'CTEE55'),
	(100, 1, 'documents\img_sp\24b.png',24 , 2, 987000,987000, 'CTEE66'),

	(100, 1, 'documents\img_sp\25a.png',25 , 4, 344000,344000, 'CTEE77'),
	(100, 1, 'documents\img_sp\25b.png',25 , 8, 344000,344000, 'CTEE88'),

	(100, 1, 'documents\img_sp\26a.png',26 , 4, 280000,280000, 'CTEE99'),
	(100, 1, 'documents\img_sp\26b.png',26 , 5, 280000,280000, 'CTVV11'),

	(100, 1, 'documents\img_sp\27a.png',27 , 4, 705000,705000, 'CTVV22'),
	(100, 1, 'documents\img_sp\27b.png',27 , 1, 705000,705000, 'CTVV33')

-- Yến
-- Tạo bảng trang_thai_voucher
CREATE TABLE trang_thai_voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_trang_thai NVARCHAR(100) NOT NULL,
    ten_trang_thai NVARCHAR(100) NOT NULL,
	mo_ta NVARCHAR(100)
);

-- Chèn dữ liệu vào bảng trang_thai_voucher
INSERT INTO trang_thai_voucher (ma_trang_thai, ten_trang_thai, mo_ta)
VALUES
    ('TT001', N'Hoạt động', N'Voucher hiện đang hoạt động'),
    ('TT002', N'Hết hạn', N'Voucher đã hết hạn'),
    ('TT003', N'Đã sử dụng', N'Voucher đã được sử dụng');

-- Tạo bảng loai_voucher
CREATE TABLE loai_voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_loai NVARCHAR(100) NOT NULL,
    ten_loai NVARCHAR(100) NOT NULL,
	mo_ta NVARCHAR(100)
);

-- Chèn dữ liệu vào bảng loai_voucher
INSERT INTO loai_voucher (ma_loai, ten_loai, mo_ta)
VALUES
    ('LV001', N'Giảm giá theo tiền', N'Voucher cung cấp giảm giá'),
    ('LV002', N'Giảm giá theo phần trăm', N'Voucher tặng sản phẩm kèm theo mua hàng');

CREATE TABLE voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_voucher NVARCHAR(100) NOT NULL,
    ten_voucher NVARCHAR(100) NOT NULL,
	muc_chi_tieu_toi_thieu INT NOT NULL,
	loai_voucher BIGINT FOREIGN KEY REFERENCES loai_voucher(id),
    muc_giam_gia INT NOT NULL,
    thoi_gian_bat_dau DATETIME NOT NULL,
    thoi_gian_ket_thuc DATETIME NOT NULL,
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE(),
	so_luong INT NOT NULL,
	trang_thai BIGINT FOREIGN KEY REFERENCES trang_thai_voucher(id)
);

-- Thêm 10 bản ghi cho bảng voucher
INSERT INTO voucher (ma_voucher, ten_voucher, muc_chi_tieu_toi_thieu, loai_voucher, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai)
VALUES
	('V001', N'Giảm 10% cho hóa đơn từ 1,000,000 VNĐ', 1000000,  2, 10, '2023-01-01 08:00:00', '2023-02-01 23:45:00', 100, 1),
    ('V002', N'Giảm 15% cho hóa đơn từ 1,500,000 VNĐ', 1500000,  2, 15, '2023-01-15 05:00:00', '2023-02-15 22:45:00', 200, 2),
    ('V003', N'Giảm 20% cho hóa đơn từ 2,000,000 VNĐ', 2000000, 2, 20, '2023-02-01 06:00:00', '2023-03-01 18:45:00', 150, 1),
    ('V004', N'Giảm 25% cho hóa đơn từ 2,500,000 VNĐ', 2500000, 2, 25, '2023-02-15 00:00:00', '2023-03-15 12:45:00', 50, 2),
    ('V005', N'Giảm 30% cho hóa đơn từ 3,000,000 VNĐ', 3000000, 2, 30, '2023-03-01 02:00:00', '2023-04-01 20:45:00', 120, 1),
    ('V006', N'Giảm 35% cho hóa đơn từ 3,500,000 VNĐ', 3500000, 2, 35, '2023-03-15 03:00:00', '2023-04-15 17:00:00', 80, 1),
    ('V007', N'Giảm 40% cho hóa đơn từ 4,000,000 VNĐ', 4000000, 2, 40, '2023-04-01 08:00:00' , '2023-05-01 23:45:00', 150, 2),
    ('V008', N'Giảm 45%cho hóa đơn từ 4,500,000 VNĐ', 4500000, 2, 45, '2023-04-15 12:00:00', '2023-05-15 22:45:00', 60, 1),
    ('V009', N'Giảm 50% cho hóa đơn từ 5,000,000 VNĐ', 5000000, 2, 50, '2023-05-01 10:00:00', '2023-06-01 23:45:00', 180, 2),
    ('V010', N'Giảm 55% cho hóa đơn từ 5,500,000 VNĐ', 5500000, 2, 55, '2023-05-15 07:00:00', '2023-06-15 19:00:00', 100, 1);
	

	
-- Hùng --
CREATE TABLE khach_hang(
	id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ho_ten NVARCHAR(50) NOT NULL,
	gioi_tinh BIT,
	sdt VARCHAR(12),
	dia_chi NVARCHAR(255) ,
	email VARCHAR(125) NOT NULL,
	ngay_sinh DATE ,
	cap_bac int,
	thoi_gian_tao DATE,
	thoi_gian_sua DATE,
	is_delete bit,
	ma varchar(20),
	id_nhanvien BIGINT,
	FOREIGN KEY (id_nhanvien) REFERENCES nhan_vien(id),
)

INSERT INTO khach_hang (ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua, is_delete, ma,id_nhanvien)
VALUES
    (N'Nguyễn Văn Tài', 1, '0987654321', N'Quỳnh Lưu - Nghệ An', 'nguyenvantai@example.com', '2000-01-15', 1, GETDATE(), GETDATE(), 0, 'KH001',1),
    (N'Trần Thị Bình', 0, '0123456789', N'Thanh Trì - Hà Nội', 'tranthibinh@example.com', '1995-05-20', 2, GETDATE(), GETDATE(), 0,'KH002',2),
    (N'Lê Văn Chính', 1, '0978563412', N'Đống đa - Hà Nội', 'levanchinh@example.com', '1980-09-10', 1, GETDATE(), GETDATE(),0,'KH003',1),
    (N'Phạm Văn Dũng', 1, '0956743218', N'63 Lê đức thọ - Hà Nội', 'phamvand@example.com', '1992-03-25', 3, GETDATE(), GETDATE(),0,'KH004',2),
    (N'Nguyễn Thị Em', 0, '0912345678', N'Thanh Hóa', 'nguyenthem@example.com', '1998-07-30', 1, GETDATE(), GETDATE(),0, 'KH005',1),
    (N'Trần Văn Phong', 1, '0945632178', N'Quỳnh Long - Nghệ An', 'tranvanf@example.com', '1975-11-05', 2, GETDATE(), GETDATE(),0,'KH006',2),
    (N'Lê thị giang', 0, '0909876543', N'Bình Định', 'lethig@example.com', '1988-12-12', 1, GETDATE(), GETDATE(),0,'KH007',1),
    (N'Phạm Thị Hương', 0, '0932167845', N'Đô Lương - Nghệ An', 'phamthih@example.com', '1990-02-28', 3, GETDATE(), GETDATE(),0,'KH008',1),
    (N'Nguyễn Văn Yến', 1, '0965432178', N'Tràng an - Ninh Bình', 'nguyenvani@example.com', '2002-08-18', 1, GETDATE(), GETDATE(),0,'KH009',1),
    (N'Trần Thị Hiên', 1, '0978563421', N'Kiểu mai - Hà Nội', 'tranvank@example.com', '1994-06-10', 2, GETDATE(), GETDATE(),0,'KH0010',2);

	

	-- Hóa đơn --
	CREATE TABLE HoaDon(
		IDHoaDon BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
		GhiChu NVARCHAR(255),
		TongTienHoaDon FLOAT,
		TongTienSauKhuyenMai FLOAT,
		IDNhanVien BIGINT NOT NULL FOREIGN KEY REFERENCES nhan_vien(id),
		IDKhachHang BIGINT FOREIGN KEY REFERENCES khach_hang(id),
		IDVoucher BIGINT FOREIGN KEY REFERENCES voucher(id),
		SoTienDaGiam FLOAT,
		TongTienPhaiTra FLOAT,
		MaHoaDon VARCHAR(6) NOT NULL,
		ThoiGianSua DATETIME DEFAULT GETDATE(),
		THoiGianTao DATETIME DEFAULT GETDATE(),
		TrangThai INT NOT NULL,
		TienMatKhachTra FLOAT, --
		HinhThucThanhToan INT, --
		HinhThuc INT 
	)


INSERT INTO HoaDon (GhiChu, TongTienHoaDon, TongTienSauKhuyenMai, IDNhanVien, IDKhachHang, IDVoucher, TrangThai, SoTienDaGiam, TongTienPhaiTra, MaHoaDon, ThoiGianSua, THoiGianTao, TienMatKhachTra,HinhThucThanhToan,HinhThuc)
VALUES
	(N'Giao dịch mua hàng', 1000000, 900000, 1, 1, 1, 1, 10000, 890000, 'HD0001', GETDATE(),GETDATE(),200000,1,1),
	(N'Giao dịch mua hàng', 1500000, 1400000, 2, 2, 2, 1, 10000, 1390000, 'HD0002', GETDATE(),GETDATE(),300000,1,2),
	(N'Giao dịch mua hàng', 1200000, 1100000, 4, 4, 4, 1, 10000, 1090000, 'HD0010', GETDATE(),GETDATE(),400000,2,1)
	

	-- của yến
	-- Tạo bảng voucher_history
CREATE TABLE voucher_history (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    ma_voucher_history uniqueidentifier DEFAULT NEWID(),
    id_hoa_don BIGINT  FOREIGN KEY REFERENCES HoaDon(IDHoaDon),
    id_voucher BIGINT FOREIGN KEY REFERENCES voucher(id),
    thoi_gian_su_dung DATETIME NOT NULL DEFAULT GETDATE(),
    so_tien_giam_gia INT NOT NULL,
    so_tien_before_giam_gia INT NOT NULL,
    so_tien_after_giam_gia INT NOT NULL,
    ghi_chu NVARCHAR(MAX)
);


-- Thêm 10 bản ghi cho bảng voucher_history
INSERT INTO voucher_history (id_hoa_don, id_voucher, so_tien_giam_gia, so_tien_before_giam_gia, so_tien_after_giam_gia, ghi_chu)
VALUES
    (1, 1, 10000, 50000, 40000, N'Giảm 10% cho hóa đơn'),
    (2, 3, 20000, 100000, 80000, N'Giảm 20% cho hóa đơn'),
	(3, 5, 15000, 30000, 15000, N'Giảm 50% cho hóa đơn'),
    (1, 6, 18000, 60000, 42000, N'Giảm 30% cho hóa đơn'),
    (2, 8, 35000, 100000, 65000, N'Giảm 35% cho hóa đơn'),
    (3, 10, 50000, 150000, 100000, N'Giảm 50% cho hóa đơn')


CREATE TABLE HoaDon_SanPhamChiTiet(
	ID BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	IDHoaDon BIGINT NOT NULL FOREIGN KEY REFERENCES HoaDon(IDHoaDon),
	IDSanPhamChiTiet BIGINT NOT NULL FOREIGN KEY REFERENCES SanPhamChiTiet(IDSanPhamChiTiet),
	GiaTien FLOAT NOT NULL,
	SoLuong INT NOT NULL
)
INSERT INTO HoaDon_SanPhamChiTiet(IDHoaDon, IDSanPhamChiTiet, GiaTien, SoLuong) VALUES 
	(1,1,470.83,2),
	(1,2,270.84,4),
	(2,3,470.81,7),
	(2,4,660.89,5), 
	(3,5,220.82,1)

-- Nghĩa --
CREATE TABLE phieu_giao_hang (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ma NVARCHAR(50),
	nguoi_nhan NVARCHAR(255),
	sdt_nguoi_nhan NVARCHAR(255),
	dia_chi NVARCHAR(255),
	nguoi_ship NVARCHAR(255),
	sdt_nguoi_ship NVARCHAR(255),
	thoi_gian_nhan BIGINT,
	mo_ta NVARCHAR(255),
	thoi_gian_giao BIGINT,
    trang_thai INT,
	thoi_gian_uoc_tinh BIGINT,
	so_tien_thu FLOAT,
	thoi_gian_sua DATETIME DEFAULT GETDATE()
)

INSERT INTO phieu_giao_hang (ma, nguoi_nhan, sdt_nguoi_nhan, dia_chi, nguoi_ship, sdt_nguoi_ship, thoi_gian_giao, thoi_gian_nhan, mo_ta, trang_thai, thoi_gian_uoc_tinh)
VALUES
    ('MSP001', N'Nguyễn Văn An', '0849070512', N'BacTuLiem 1', N'Nguyễn văn Đức', '0849070512', 1636336800, 1636336800, N'Mô tả cho bản ghi 1', 1, 1636336800),
    ('MSP002', N'Nguyen Văn Bình', '084907052', N'BacTuLiem 2', N'Nguyễn văn Toàn', '0849070512', 1636336900, 1636336900, N'Mô tả cho bản ghi 2', 2, 1636336800),
    ('MSP003', N'Nguyen Thi Công', '0849070515', N'BacTuLiem 3', N'Nguyễn văn Thành','0849070512', 1636337000, 1636337000, N'Mô tả cho bản ghi 3', 3, 1636336800),
    ('MSP004', N'Nguyen Bá Duy', '0849070516', N'BacTuLiem 4', N'Nguyễn văn Tuấn', '0849070512', 1636337100, 1636337100, N'Mô tả cho bản ghi 4', 4, 1636336800),
    ('MSP005', N'Nguyen Thi Em', '0849070542', N'BacTuLiem 5', N'Nguyễn văn Luật', '0849070512', 1636337200, 1636337200, N'Mô tả cho bản ghi 5', 5, 1636336800),
    ('MSP006', N'Nguyen Bảo Lam', '0849070562', N'BacTuLiem 6', N'Nguyễn văn Hải', '0849070512', 1636337300, 1636337300, N'Mô tả cho bản ghi 6', 6, 1636336800),
    ('MSP007', N'Nguyen Thi Long', '0849070512', N'BacTuLiem 7', N'Nguyễn văn Bình', '0849070512', 1636337400, 1636337400, N'Mô tả cho bản ghi 7', 7, 1636336800),
    ('MSP008', N'Nguyen Bách Hùng', '0849070517', N'BacTuLiem 8', N'Nguyễn văn Tài', '0849070512', 1636337500, 1636337500, N'Mô tả cho bản ghi 8', 8, 1636336800),
    ('MSP009', N'Nguyen Hợp Tuấn', '0849070512', N'BacTuLiem 9', N'Nguyễn văn Lan', '0849070512', 1636337600, 1636337600, N'Mô tả cho bản ghi 9', 9, 1636336800),
    ('MSP010', N'Nguyen Thi Hồng', '0849070562', N'BacTuLiem 10', N'Nguyễn văn chính', '0849070512', 1636337700, 1636337700, N'Mô tả cho bản ghi 10', 10, 1636336800);


-- khuyến mại sản phẩm 
CREATE TABLE khuyen_mai (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten_khuyen_mai NVARCHAR(100),
	ma_khuyen_mai NVARCHAR(50) UNIQUE,
    phan_tram_giam_gia INT,
    thoi_gian_bat_dau BIGINT,
    thoi_gian_ket_thuc BIGINT,
    thoi_gian_sua BIGINT,
    thoi_gian_tao BIGINT,
	trang_thai BIT,
);

-- Thêm 10 bản ghi vào bảng id_khuyen_mai
INSERT INTO khuyen_mai (ten_khuyen_mai ,ma_khuyen_mai, phan_tram_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, thoi_gian_sua, thoi_gian_tao, trang_thai)
VALUES
    (N'Khuyến mãi 1', N'KM01' , 10, 1636336800, 1732792306, 1636336800, 1636336800,1),
    (N'Khuyến mãi 2', N'KM02', 20, 1636336900, 1732792306, 1636336900, 1636336900,1),
    (N'Khuyến mãi 3', N'KM03', 30, 1636337000, 1732792306, 1636337000, 1636337000,1),
    (N'Khuyến mãi 4', N'KM04', 40, 1636337100, 1732792306, 1636337100, 1636337100,1),
    (N'Khuyến mãi 5', N'KM05', 50, 1636337200, 1732792306, 1636337200, 1636337200,1),
    (N'Khuyến mãi 6', N'KM06',10, 1636337300, 1732792306, 1636337300, 1636337300,1),
    (N'Khuyến mãi 7', N'KM07', 20, 1636337400, 1732792306, 1636337400, 1636337400,1),
    (N'Khuyến mãi 8', N'KM08', 30, 1636337500, 1732792306, 1636337500, 1636337500,1),
    (N'Khuyến mãi 9', N'KM09', 40, 1636337600, 1732792306, 1636337600, 1636337600,1),
    (N'Khuyến mãi 10',N'KM10',50, 1636337700, 1732792306, 1636337700, 1636337700,1);

CREATE TABLE sanphamchitiet_khuyenmai (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    id_san_pham_chi_tiet BIGINT,
    id_khuyen_mai BIGINT,
    xoa_mem BIT,
    thoi_gian_sua BIGINT,
    thoi_gian_tao BIGINT,
	CONSTRAINT FK_id_sanphamchitiet FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES SanPhamChiTiet(IDSanPhamChiTiet),
    CONSTRAINT FK_id_khuyenmai FOREIGN KEY (id_khuyen_mai) REFERENCES khuyen_mai(id)
);


-- Thêm 10 bản ghi vào bảng id_sanphamchitiet_khuyenmai
INSERT INTO sanphamchitiet_khuyenmai (id_san_pham_chi_tiet, id_khuyen_mai, xoa_mem, thoi_gian_sua, thoi_gian_tao)
VALUES
    (1, 1, 0, 1636336800, 1636336800),
    (2, 2, 1, 1636336900, 1636336900),
    (3, 3, 0, 1636337000, 1636337000),
    (4, 4, 1, 1636337100, 1636337100),
    (5, 5, 0, 1636337200, 1636337200),
    (6, 6, 1, 1636337300, 1636337300),
    (7, 7, 0, 1636337400, 1636337400),
    (8, 8, 1, 1636337500, 1636337500),
    (9, 9, 0, 1636337600, 1636337600),
    (10, 10, 1, 1636337700, 1636337700);



-- Luật --

create table trang_thai_phieu_doi(
	id BIGINT identity(1,1) primary key,
	ten_trang_thai nvarchar(50),
	mo_ta nvarchar(200)
)

create table ly_do_doi(
	id BIGINT identity(1,1) primary key,
	ly_do nvarchar(100),
	mo_ta nvarchar(200)
)

create table phieu_doi(
	id BIGINT identity(1,1) primary key,
	ghi_chu nvarchar(200),
	ngay_doi  date,
	id_hoa_don BIGINT  foreign key references HoaDon(IDHoaDon),
	id_trang_thai BIGINT foreign key references trang_thai_phieu_doi(id),
	id_nhan_vien BIGINT foreign key references nhan_vien(id),
	ngay_tao date,
	ngay_sua date
) 


create table phieu_doi_chi_tiet (
	id BIGINT identity(1,1) primary key,
	gia_ban float , 
	ten_san_pham nvarchar(100),
	mau nvarchar(50),
	chat_lieu nvarchar(100),
	so_luong_doi int,
	id_san_pham_chi_tiet BIGINT foreign key references SanPhamChiTiet(IDSanPhamChiTiet),
	id_ly_do_doi BIGINT foreign key references ly_do_doi(id),
	id_phieu_doi BIGINT foreign key references phieu_doi(id),
	mota nvarchar(200)
)



 INSERT INTO ly_do_doi (ly_do, mo_ta)
VALUES 
	(N'Sản phẩm lỗi', N'Sản phẩm bị lỗi trong quá trình sản xuất hoặc có vấn đề kỹ thuật.'),
    (N'Sản phẩm có dấu hiệu đã qua sử dụng', N'Sản phẩm có dấu vết cho thấy nó đã sử dụng trước đó.'),
	 (N'Sản phẩm bị rách', N'Sản phẩm bị rách do quá trình sản xuất hoặc quá trình vận chuyển.'),  
    (N'Sản phẩm không đúng với kích thước và màu sắc', N'Sản phẩm không đúng về kích thước hoặc màu sắc.'), 
    (N'Lỗi kỹ thuật do nhà sản xuất', N'Sản phẩm gặp lỗi hoặc vấn đề kỹ thuật do quá trình sản xuất.');
INSERT INTO trang_thai_phieu_doi (ten_trang_thai, mo_ta)
VALUES
    (N'Chờ xác nhận', N'Đang chờ xác nhận từ phía cửa hàng'),
    (N'Đã xác nhận', N'Phiếu đổi đã được xác nhận'),
    (N'Đã hoàn thành', N'Phiếu đổi đã hoàn thành'),
    (N'Đã hủy', N'Phiếu đổi đã bị hủy'),
    (N'Đang xử lý', N'Phiếu đổi đang trong quá trình xử lý');

 INSERT INTO  phieu_doi (ghi_chu, ngay_doi, id_hoa_don, id_trang_thai, id_nhan_vien,ngay_sua,ngay_tao)
 VALUES  
    (N'Khách hàng đổi sản phẩm lỗi', CURRENT_TIMESTAMP, 1, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (N'Khách hàng đổi sản phẩm không đúng màu', '2023-11-05', 2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (N'Khách hàng đổi sản phẩm không đúng kích thước', '2023-11-04', 3, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

  INSERT INTO phieu_doi_chi_tiet (gia_ban, ten_san_pham, mau, so_luong_doi, id_san_pham_chi_tiet, id_phieu_doi,id_ly_do_doi,mota)
VALUES
     (25.5, N'Túi sách da bò', 'Đen',  2, 1, 1,1,N'ảo thật đấy'),
   (30.0, N'Túi sách da trâu', 'Xanh',  1, 2, 2,2,N'ảo thật đấy'),
    (15.0, N'Túi sách da heo', 'Trắng',  3, 3, 3,3,N'ảo thật đấy')



CREATE TABLE LichSuGiaoDich (
    IDGiaoDich BIGINT IDENTITY(1,1) PRIMARY KEY,
    MaHoaDon VARCHAR(20) NOT NULL,
    IDKhachHang BIGINT FOREIGN KEY REFERENCES khach_hang(id),
    ThoiGianTao DATETIME,
    TongTien FLOAT,
    CapBacKhachHang INT
);

INSERT INTO LichSuGiaoDich (MaHoaDon, IDKhachHang, ThoiGianTao, TongTien, CapBacKhachHang)
VALUES ('HD001', 1, '2023-12-02 10:30:00', 2000000, 1),
       ('HD002', 1, '2023-12-09 10:30:00', 4000000, 2);


