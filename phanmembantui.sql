CREATE DATABASE V1
USE V1
-- T?o b?ng id_sanphamchitiet_khuyenmai
CREATE TABLE id_khuyen_mai (
    id BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    ten_khuyen_mai NVARCHAR(100) NOT NULL,
    phan_tram_giam_gia INT NOT NULL,
    thoi_gian_bat_dau DATE NOT NULL,
    thoi_gian_ket_thuc DATE NOT NULL,
    thoi_gian_sua DATETIME NOT NULL DEFAULT GETDATE(),
    thoi_gian_tao DATETIME NOT NULL DEFAULT GETDATE()
);

-- Thêm 10 b?n ghi vào b?ng id_khuyen_mai
INSERT INTO id_khuyen_mai (ten_khuyen_mai, phan_tram_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc)
VALUES
    (N'Khuyến mãi 1', 10, '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 2', 20,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 3', 30,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 4', 40,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 5', 50,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 6', 10,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 7', 20,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 8', 30,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 9', 40,  '2023-12-01', '2023-12-23'),
    (N'Khuyến mãi 10', 50,  '2023-12-01', '2023-12-23');

	select * from id_khuyen_mai