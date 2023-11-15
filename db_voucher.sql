CREATE DATABASE V2
USE V2 

CREATE TABLE voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_voucher NVARCHAR(100) NOT NULL,
    ten_voucher NVARCHAR(100) NOT NULL,
    phan_tram_giam_gia INT NOT NULL,
    thoi_gian_bat_dau DATE NOT NULL,
    thoi_gian_ket_thuc DATE NOT NULL,
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE(),
	so_luong INT NOT NULL,
	trang_thai NVARCHAR(100) NOT NULL
);

-- Them 10 ban ghi bang voucher
INSERT INTO voucher (ma_voucher,ten_voucher, phan_tram_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai)
VALUES
    (N'V1',N'Voucher01', 10, '2023-01-01', '2023-01-10', 100, N'Hoạt động'),
    (N'V2',N'Voucher02', 15, '2023-02-01', '2023-02-15', 50, N'Hoạt động'),
    (N'V3',N'Voucher03', 20, '2023-03-01', '2023-03-20', 200, N'Hoạt động'),
    (N'V4',N'Voucher04', 5, '2023-10-01', '2023-10-10', 75, N'Hết hạn'),
	(N'V5',N'Voucher05', 5, '2023-10-01', '2023-10-10', 75, N'Hết hạn'),
    (N'V6',N'Voucher06', 25, '2023-06-01', '2023-06-15', 150, N'Hoạt động'),
    (N'V7',N'Voucher07', 30, '2023-07-01', '2023-07-10', 80, N'Hoạt động'),
    (N'V8',N'Voucher08', 15, '2023-08-01', '2023-08-20', 120, N'Hoạt động'),
    (N'V9',N'Voucher09', 10, '2023-09-01', '2023-09-15', 50, N'Hết hạn'),
    (N'V10',N'Voucher10', 5, '2023-10-01', '2023-10-10', 75, N'Hoạt động');

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
