CREATE DATABASE PHAN_MEM_BAN_TUI_HAIYEN
USE PHAN_MEM_BAN_TUI_HAIYEN
-- T?o b?ng id_sanphamchitiet_khuyenmai
CREATE TABLE voucher (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    ten_voucher NVARCHAR(100) NOT NULL,
    phan_tram_giam_gia INT NOT NULL,
    thoi_gian_bat_dau DATE NOT NULL,
    thoi_gian_ket_thuc DATE NOT NULL,
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE()
);

-- Thêm 10 b?n ghi vào b?ng id_khuyen_mai
INSERT INTO voucher (ten_voucher, phan_tram_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc)
VALUES
    (N'Voucher 1', 10, '2023-12-01', '2023-12-23'),
    (N'Voucher 2', 20,  '2023-12-02', '2023-12-13'),
    (N'Voucher 3', 30,  '2023-12-03', '2023-12-22'),
    (N'Voucher 4', 40,  '2023-12-04', '2023-12-23'),
    (N'Voucher 5', 50,  '2023-12-05', '2023-12-24'),
    (N'Voucher 6', 10,  '2023-12-06', '2023-12-25'),
    (N'Voucher 7', 20,  '2023-12-07', '2023-12-26'),
    (N'Voucher 8', 30,  '2023-12-08', '2023-12-27'),
    (N'Voucher 9', 40,  '2023-12-09', '2023-12-28'),
    (N'Voucher 10', 50,  '2023-12-10', '2023-12-29');

	select * from voucher
	INSERT INTO voucher (ten_voucher, phan_tram_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc)
	VALUES
    (N'Voucher 11', 100, '2023-12-01', '2023-12-01')
