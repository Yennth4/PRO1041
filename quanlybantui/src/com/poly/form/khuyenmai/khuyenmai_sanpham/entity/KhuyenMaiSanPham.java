package com.poly.form.khuyenmai.khuyenmai_sanpham.entity;

public class KhuyenMaiSanPham {
    
    private Long id;
    
    private Long idSanPhamChiTiet;
    
    private Long idKhuyenMaiTheoSanPham;

    public KhuyenMaiSanPham() {
    }

    public KhuyenMaiSanPham(Long id, Long idSanPhamChiTiet, Long idKhuyenMaiTheoSanPham) {
        this.id = id;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.idKhuyenMaiTheoSanPham = idKhuyenMaiTheoSanPham;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(Long idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public Long getIdKhuyenMaiTheoSanPham() {
        return idKhuyenMaiTheoSanPham;
    }

    public void setIdKhuyenMaiTheoSanPham(Long idKhuyenMaiTheoSanPham) {
        this.idKhuyenMaiTheoSanPham = idKhuyenMaiTheoSanPham;
    }
    
    
    
}
