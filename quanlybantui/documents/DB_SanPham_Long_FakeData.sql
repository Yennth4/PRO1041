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
	VALUES	('SPA111',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,1,1),
			('SPA222',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,2,1,1),
			('SPA333',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,3,1,1),
			('SPA444',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,4,1,1),
			('SPA555',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,1,1,1),

			('SPA666',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPA777',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPA888',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPA999',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),
			('SPB111',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',2,2,2,1),

			('SPB222',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,3,1),
			('SPB333',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,3,1),
			('SPB444',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,3,1),
			('SPB555',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,4,1),
			('SPB666',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',3,4,4,1),

			('SPB777',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPB888',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPB999',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPC111',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',4,3,5,1),
			('SPC222',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',1,3,5,1),

			('SPC333',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC444',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC555',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC666',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),
			('SPC777',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',5,1,6,1),

			('SPC888',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',6,4,1,1),
			('SPC999',N'TÚI ĐEO VAI MINI', N'1 Ngăn lớn, 3 ngăn nhỏ',6,4,1,1)



-- SPCT
INSERT INTO SanPhamChiTiet(SoLuong,TrangThai,MainImage,IDSanPham,IDMau,GiaBan,GiaNiemYet, Ma) VALUES
	(100, 1, 'documents\img_sp\27b.png',1 , 4, 350000,350000, 'CTA111'),
	(100, 1, 'documents\img_sp\27b.png',1 , 5, 350000,350000, 'CTA222'),

	(100, 1, 'documents\img_sp\27b.png',2 , 7, 420000,420000, 'CTB111'),
	(100, 1, 'documents\img_sp\27b.png',2 , 4, 420000,420000, 'CTB222'),

	(100, 1, 'documents\img_sp\27b.png',3 , 5, 230000,230000, 'CTC111'),
	(100, 1, 'documents\img_sp\27b.png',3 , 4, 230000,230000, 'CTC222'),
	(100, 1, 'documents\img_sp\27b.png',3 , 3, 230000,230000, 'CTC333'),

	(100, 1, 'documents\img_sp\27b.png',4 , 5, 330000,330000, 'CTD111'),
	(100, 1, 'documents\img_sp\27b.png',4 , 4, 330000,330000, 'CTD222'),

	(100, 1, 'documents\img_sp\27b.png',5 , 5, 410000,410000, 'CTE111'),
	(100, 1, 'documents\img_sp\27b.png',5 , 4, 410000,410000, 'CTE222'),

	(100, 1, 'documents\img_sp\27b.png',6 , 4, 550000,550000, 'CTG111'),
	(100, 1, 'documents\img_sp\27b.png',6 , 5, 550000,550000, 'CTG222'),

	(100, 1, 'documents\img_sp\27b.png',7 , 6, 270000,270000, 'CTH111'),
	(100, 1, 'documents\img_sp\27b.png',7 , 4, 270000,270000, 'CTH222'),
	(100, 1, 'documents\img_sp\27b.png',7 , 5, 270000,270000, 'CTH333'),

	(100, 1, 'documents\img_sp\27b.png',8 , 4, 370000,370000, 'CTF111'),
	(100, 1, 'documents\img_sp\27b.png',8 , 5, 370000,370000, 'CTF222'),
	(100, 1, 'documents\img_sp\27b.png',8 , 2, 370000,370000, 'CTF333'),

	(100, 1, 'documents\img_sp\27b.png',9 , 4, 325000,325000, 'CTI111'),
	(100, 1, 'documents\img_sp\27b.png',9 , 5, 325000,325000, 'CTI222'),

	(100, 1, 'documents\img_sp\27b.png',10 , 6, 190000,190000, 'CTK111'),
	(100, 1, 'documents\img_sp\27b.png',10 , 5, 190000,190000, 'CTK222'),
	(100, 1, 'documents\img_sp\27b.png',10 , 6, 190000,190000, 'CTK333'),

	(100, 1, 'documents\img_sp\27b.png',11 , 4, 790000,790000, 'CTL111'),
	(100, 1, 'documents\img_sp\27b.png',11 , 8, 790000,790000, 'CTL222'),

	(100, 1, 'documents\img_sp\27b.png',12 , 4, 880000,880000, 'CTO111'),
	(100, 1, 'documents\img_sp\27b.png',12 , 7, 880000,880000, 'CTO222'),
	(100, 1, 'documents\img_sp\27b.png',12 , 6, 880000,880000, 'CTO333'),

	(100, 1, 'documents\img_sp\27b.png',13 , 4, 740000,740000, 'CTN111'),
	(100, 1, 'documents\img_sp\27b.png',13 , 1, 740000,740000, 'CTN222'),

	(100, 1, 'documents\img_sp\27b.png',14 , 4, 660000,660000, 'CTM111'),
	(100, 1, 'documents\img_sp\27b.png',14 , 8, 660000,660000, 'CTM222'),

	(100, 1, 'documents\img_sp\27b.png',15 , 4, 690000,690000, 'CTX111'),
	(100, 1, 'documents\img_sp\27b.png',15 , 1, 690000,690000, 'CTX222'),
	(100, 1, 'documents\img_sp\27b.png',15 , 7, 690000,690000, 'CTX333'),

	(100, 1, 'documents\img_sp\27b.png',16 , 4, 880000,880000, 'CTW111'),
	(100, 1, 'documents\img_sp\27b.png',16 , 1, 880000,880000, 'CTW222'),
	(100, 1, 'documents\img_sp\27b.png',16 , 7, 880000,880000, 'CTW333'),
	(100, 1, 'documents\img_sp\27b.png',16 , 3, 880000,880000, 'CTW444'),
	(100, 1, 'documents\img_sp\27b.png',16 , 5, 880000,880000, 'CTW555'),

	(100, 1, 'documents\img_sp\27b.png',17 , 4, 340000,340000, 'CTT111'),
	(100, 1, 'documents\img_sp\27b.png',17 , 1, 340000,340000, 'CTT222'),
	(100, 1, 'documents\img_sp\27b.png',17 , 7, 340000,340000, 'CTT333'),

	(100, 1, 'documents\img_sp\27b.png',18 , 4, 440000,440000, 'CTAA11'),
	(100, 1, 'documents\img_sp\27b.png',18 , 7, 440000,440000, 'CTAA22'),
	(100, 1, 'documents\img_sp\27b.png',18 , 5, 440000,440000, 'CTAA33'),
	(100, 1, 'documents\img_sp\27b.png',18 , 6, 440000,440000, 'CTAA44'),

	(100, 1, 'documents\img_sp\27b.png',19 , 5, 459000,459000, 'CTBB11'),
	(100, 1, 'documents\img_sp\27b.png',19 , 4, 459000,459000, 'CTBB22'),
	(100, 1, 'documents\img_sp\27b.png',19 , 2, 459000,459000, 'CTBB33'),

	(100, 1, 'documents\img_sp\27b.png',20 , 7, 910000,910000, 'CTCC11'),
	(100, 1, 'documents\img_sp\27b.png',20 , 2, 910000,910000, 'CTCC22'),

	(100, 1, 'documents\img_sp\27b.png',21 , 4, 720000,720000, 'CTDD11'),
	(100, 1, 'documents\img_sp\27b.png',21 , 8, 720000,720000, 'CTDD22'),

	(100, 1, 'documents\img_sp\27b.png',22 , 4, 888000,888000, 'CTEE11'),
	(100, 1, 'documents\img_sp\27b.png',22 , 1, 888000,888000, 'CTEE22'),

	(100, 1, 'documents\img_sp\27b.png',23 , 4, 410000,410000, 'CTEE33'),
	(100, 1, 'documents\img_sp\27b.png',23 , 7, 410000,410000, 'CTEE44'),

	(100, 1, 'documents\img_sp\27b.png',24 , 7, 987000,987000, 'CTEE55'),
	(100, 1, 'documents\img_sp\27b.png',24 , 2, 987000,987000, 'CTEE66'),

	(100, 1, 'documents\img_sp\27b.png',25 , 4, 344000,344000, 'CTEE77'),
	(100, 1, 'documents\img_sp\27b.png',25 , 8, 344000,344000, 'CTEE88'),

	(100, 1, 'documents\img_sp\27b.png',26 , 4, 280000,280000, 'CTEE99'),
	(100, 1, 'documents\img_sp\27b.png',26 , 5, 280000,280000, 'CTVV11'),

	(100, 1, 'documents\img_sp\27b.png',27 , 4, 705000,705000, 'CTVV22'),
	(100, 1, 'documents\img_sp\27b.png',27 , 1, 705000,705000, 'CTVV33')