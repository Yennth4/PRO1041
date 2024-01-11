/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entity;

/**
 *
 * @author longnvph31848
 */
public class HoaDonDTO {

    private Long idHoaDon;
    private String ghiChu;
    private Float tongTienHoaDon;
    private Float tongTienSauKhuyenMai;
    private String tenNhanVien;
    private String tenKhachHang;
    private Float soTienDaGiam;
    private Float tongTienPhaiTra;
    private String maHoaDon;
    private Integer trangThai;
    private Float tienMatKhachTra;
    private Integer hinhThucThanhToan;
    private Integer hinhThucMua;
    private Integer tongSanPham;
    private String maKhachHang;
    private String hoTenKhachHang;

    public HoaDonDTO() {
    }

    public HoaDonDTO(Long idHoaDon, String ghiChu, Float tongTienHoaDon, Float tongTienSauKhuyenMai, String tenNhanVien, String tenKhachHang, Float soTienDaGiam, Float tongTienPhaiTra, String maHoaDon, Integer trangThai, Float tienMatKhachTra, Integer hinhThucThanhToan, Integer hinhThucMua, Integer tongSanPham, String maKhachHang, String hoTenKhachHang) {
        this.idHoaDon = idHoaDon;
        this.ghiChu = ghiChu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.soTienDaGiam = soTienDaGiam;
        this.tongTienPhaiTra = tongTienPhaiTra;
        this.maHoaDon = maHoaDon;
        this.trangThai = trangThai;
        this.tienMatKhachTra = tienMatKhachTra;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.hinhThucMua = hinhThucMua;
        this.tongSanPham = tongSanPham;
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public Object[] toDataRow() {
        return new Object[]{
            maHoaDon,
            tenNhanVien,
            tenKhachHang,
            getHinhThucMuaString(hinhThucMua),
            getTrangThaiString(trangThai),
            tongSanPham
        };
    }

    public String getTrangThaiString(Integer number) {
        String result = "";
        switch (number) {
            case 0:
                result = "Chờ TT";
                break;
            case 1:
                result = "Đã TT";
                break;
            case 2:
                result = "Đã hủy";
                break;
            case 3:
                result = "Chờ giao";
                break;
            case 4:
                result = "Đang giao";
                break;
            case 5:
                result = "Đã giao";
                break;
            case 6:
                result = "Khách hẹn lại";
                break;

            default:
                break;
        }
        return result;
    }

    public String getHinhThucMuaString(Integer number) {
        String result = "";
        switch (number) {
            case 1:
                result = "Tại quầy";
                break;
            case 2:
                result = "Đặt hàng";
                break;

            default:
                break;
        }
        return result;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKhachHang() {
        return hoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public Long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Float getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(Float tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public Float getTongTienSauKhuyenMai() {
        return tongTienSauKhuyenMai;
    }

    public void setTongTienSauKhuyenMai(Float tongTienSauKhuyenMai) {
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Float getSoTienDaGiam() {
        return soTienDaGiam;
    }

    public void setSoTienDaGiam(Float soTienDaGiam) {
        this.soTienDaGiam = soTienDaGiam;
    }

    public Float getTongTienPhaiTra() {
        return tongTienPhaiTra;
    }

    public void setTongTienPhaiTra(Float tongTienPhaiTra) {
        this.tongTienPhaiTra = tongTienPhaiTra;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Float getTienMatKhachTra() {
        return tienMatKhachTra;
    }

    public void setTienMatKhachTra(Float tienMatKhachTra) {
        this.tienMatKhachTra = tienMatKhachTra;
    }

    public Integer getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(Integer hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public Integer getHinhThucMua() {
        return hinhThucMua;
    }

    public void setHinhThucMua(Integer hinhThucMua) {
        this.hinhThucMua = hinhThucMua;
    }

    public Integer getTongSanPham() {
        return tongSanPham;
    }

    public void setTongSanPham(Integer tongSanPham) {
        this.tongSanPham = tongSanPham;
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" + "idHoaDon=" + idHoaDon + ", ghiChu=" + ghiChu + ", tongTienHoaDon=" + tongTienHoaDon + ", tongTienSauKhuyenMai=" + tongTienSauKhuyenMai + ", tenNhanVien=" + tenNhanVien + ", tenKhachHang=" + tenKhachHang + ", soTienDaGiam=" + soTienDaGiam + ", tongTienPhaiTra=" + tongTienPhaiTra + ", maHoaDon=" + maHoaDon + ", trangThai=" + trangThai + ", tienMatKhachTra=" + tienMatKhachTra + ", hinhThucThanhToan=" + hinhThucThanhToan + ", hinhThucMua=" + hinhThucMua + ", tongSanPham=" + tongSanPham + ", maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + '}';
    }

}
