package com.poly.form.khuyenmai.khuyenmai_sanpham.service.impl;

import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.KhuyenMaiTheoSanPham;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.SanPhamChiTietForKhuyenMaiResponse;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.SanPhamForKhuyenMaiResponse;
import com.poly.form.khuyenmai.khuyenmai_sanpham.repository.KMKhuyenMaiTheoSanPhamReposirory;
import com.poly.form.khuyenmai.khuyenmai_sanpham.repository.KMSanPhamRepository;
import com.poly.form.khuyenmai.khuyenmai_sanpham.service.SanPhamForKhuyenMaiService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamForKhuyenMaiServiceImpl implements SanPhamForKhuyenMaiService {

    private KMSanPhamRepository repo = new KMSanPhamRepository();
    private KMKhuyenMaiTheoSanPhamReposirory repoKhuyenMai = new KMKhuyenMaiTheoSanPhamReposirory();

    @Override
    public SanPhamChiTietForKhuyenMaiResponse getSanPhamChiTietForKhuyenMaiResponse(List<SanPhamChiTietForKhuyenMaiResponse> list, String ma) {
        for (SanPhamChiTietForKhuyenMaiResponse sanPhamForKhuyenMaiResponse : list) {
            if (sanPhamForKhuyenMaiResponse.getMaSanPham().equalsIgnoreCase(ma)) {
                return sanPhamForKhuyenMaiResponse;
            }
        }
        return null;
    }

    @Override
    public List<SanPhamChiTietForKhuyenMaiResponse> findSanPhamChiTietForKhuyenMaiResponseByMaSanPham(Long id) {
        return repo.findSanPhamChiTietByMaSanPham(id);
    }

    @Override
    public SanPhamForKhuyenMaiResponse getSanPhamForKhuyenMaiResponSe(List<SanPhamForKhuyenMaiResponse> list, String ma) {
        for (SanPhamForKhuyenMaiResponse sanPhamForKhuyenMaiResponse : list) {
            if (sanPhamForKhuyenMaiResponse.getMa().equalsIgnoreCase(ma)) {
                return sanPhamForKhuyenMaiResponse;
            }
        }
        return null;
    }

    @Override
    public List<Long> danhSachIDSanPham(List<SanPhamChiTietForKhuyenMaiResponse> list) {
        List<Long> danhSachId = new ArrayList<>();
        for (SanPhamChiTietForKhuyenMaiResponse sanPhamChiTietForKhuyenMaiResponse : list) {
            danhSachId.add(sanPhamChiTietForKhuyenMaiResponse.getId());
        }
        return danhSachId;
    }

    @Override
    public void themKhuyenMai(KhuyenMaiTheoSanPham khuyenMaiTheoSanPham) throws Exception {
        if (khuyenMaiTheoSanPham != null) {
            repoKhuyenMai.themKhuyenMai(khuyenMaiTheoSanPham);
        } else {
            throw new IllegalArgumentException("KhuyenMaiTheoSanPham cannot be null");
        }
    }

    @Override
    public void suaKhuyenMai(KhuyenMaiTheoSanPham khuyenMaiTheoSanPham) throws Exception {
        repoKhuyenMai.suaKhuyenMai(khuyenMaiTheoSanPham);
    }

    @Override
    public void capNhatTrangThaiKhuyenMaiBangMa(String maKhuyenMai, Boolean trangThai) throws Exception {
        repoKhuyenMai.capNhatTrangThaiKhuyenMaiBangMa(maKhuyenMai, trangThai);
    }

}
