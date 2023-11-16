CREATE DATABASE V2
USE V2 

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
	loai_voucher BIGINT FOREIGN KEY REFERENCES loai_voucher(id),
    muc_giam_gia INT NOT NULL,
    thoi_gian_bat_dau DATE NOT NULL,
    thoi_gian_ket_thuc DATE NOT NULL,
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE(),
	so_luong INT NOT NULL,
	trang_thai BIGINT FOREIGN KEY REFERENCES trang_thai_voucher(id)
);

-- Thêm 10 bản ghi cho bảng voucher
INSERT INTO voucher (ma_voucher, ten_voucher, loai_voucher, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai)
VALUES
    ('V001', 'Giảm 50k', 1, 50000, '2023-01-01', '2023-02-01', 100, 1),
    ('V002', 'Giảm 10%', 2, 10, '2023-01-15', '2023-02-15', 200, 2),
    ('V003', 'Giảm 100k', 1, 100000, '2023-02-01', '2023-03-01', 150, 1),
    ('V004', 'Giảm 20%', 2, 20, '2023-02-15', '2023-03-15', 50, 2),
    ('V005', 'Giảm 30k', 1, 30000, '2023-03-01', '2023-04-01', 120, 1),
    ('V006', 'Giảm 15%', 2, 15, '2023-03-15', '2023-04-15', 80, 1),
    ('V007', 'Giảm 200k', 1, 200000, '2023-04-01', '2023-05-01', 150, 2),
	('V008', 'Giảm 25k', 1, 25000, '2023-04-15', '2023-05-15', 60, 1),
    ('V009', 'Giảm 50%', 2, 50, '2023-05-01', '2023-06-01', 180, 2),
    ('V010', 'Giảm 150k', 1, 150000, '2023-05-15', '2023-06-15', 100, 1);
	
	-- Tạo bảng voucher_history
CREATE TABLE voucher_history (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_voucher_history NVARCHAR(100) NOT NULL,
	id_voucher BIGINT FOREIGN KEY REFERENCES voucher(id),
	thoi_gian_su_dung DATETIME NOT NULL DEFAULT GETDATE(),
    so_tien_giam_gia INT NOT NULL,
	so_tien_before_giam_gia INT NOT NULL,
	so_tien_after_giam_gia INT NOT NULL,
	ghi_chu NVARCHAR(MAX) 
); 

-- Them 10 ban ghi bang voucher_history
INSERT INTO voucher_history ( ma_voucher_history, id_voucher, thoi_gian_su_dung, so_tien_giam_gia, so_tien_before_giam_gia, so_tien_after_giam_gia, ghi_chu)
VALUES
    ('VH1',1, '2023-01-05 12:30:00', 10, 100, 90, N'Sử dụng voucher cho đơn hàng A'),
    ('VH2',2, '2023-02-10 14:45:00', 15, 200, 185, N'Sử dụng voucher cho đơn hàng B'),
    ('VH3',3,'2023-03-15 10:00:00', 20, 150, 130, N'Sử dụng voucher cho đơn hàng C'),
	('VH4',4, '2023-04-01 08:00:00', 10, 120, 110, N'Sử dụng voucher cho đơn hàng D'),
    ('VH5',5, '2023-05-12 16:30:00', 25, 180, 155, N'Sử dụng voucher cho đơn hàng E'),
    ('VH6',6, '2023-06-20 11:15:00', 15, 90, 75, N'Sử dụng voucher cho đơn hàng F'),
    ('VH7',7, '2023-07-03 09:45:00', 12, 150, 138, N'Sử dụng voucher cho đơn hàng G'),
    ('VH8',8, '2023-08-18 14:00:00', 18, 200, 182, N'Sử dụng voucher cho đơn hàng H'),
	('VH9',9, '2023-02-10 14:45:00', 15, 200, 185, N'Sử dụng voucher cho đơn hàng K'),
    ('VH10',10, '2023-10-20 18:20:00', 5, 50, 45, N'Sử dụng voucher cho đơn hàng J');

SELECT * FROM voucher_history WHERE id_voucher = 1;
DELETE FROM voucher WHERE id = 1;
insert into voucher_history values ('Ma',1,'2023-01-05 12:30:00',10,100,90,'n1')
SELECT * FROM voucher WHERE trang_thai = N'Hoạt động'
SELECT * FROM voucher_history WHERE ma_voucher_history LIKE 'VH1' OR id_voucher = 1
SELECT * FROM voucher_history ORDER BY id DESC
SELECT * FROM
    (SELECT ROW_NUMBER() OVER (ORDER BY id) AS rownum,  * FROM voucher)
    AS temp
    WHERE rownum BETWEEN 1 AND 10

	-- getAll
	SELECT * FROM trang_thai_voucher ORDER BY id DESC

	-- delete
	DELETE FROM voucher WHERE trang_thai = 1;
	DELETE FROM trang_thai_voucher WHERE id = 1;
