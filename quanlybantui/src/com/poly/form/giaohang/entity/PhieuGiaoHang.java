package com.poly.form.giaohang.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PhieuGiaoHang {

    private String ma;

    private String tenNguoiNhan;

    private String sdtNguoiNhan;

    private String tenNguoiGiao;

    private String sdtNguoiGiao;

    private String diaChi;

    private Long thoiGianGiao;

    private Long thoiGianNhan;

    private String moTa;

    private Integer trangThai;

    private Long thoiGianUocTinh;

    public PhieuGiaoHang() {
    }

    public PhieuGiaoHang(String ma, String tenNguoiNhan, String sdtNguoiNhan, String tenNguoiGiao, String sdtNguoiGiao, String diaChi, Long thoiGianGiao, Long thoiGianNhan, String moTa, Integer trangThai, Long thoiGianUocTinh) {
        this.ma = ma;
        this.tenNguoiNhan = tenNguoiNhan;
        this.sdtNguoiNhan = sdtNguoiNhan;
        this.tenNguoiGiao = tenNguoiGiao;
        this.sdtNguoiGiao = sdtNguoiGiao;
        this.diaChi = diaChi;
        this.thoiGianGiao = thoiGianGiao;
        this.thoiGianNhan = thoiGianNhan;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.thoiGianUocTinh = thoiGianUocTinh;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    public String getTenNguoiGiao() {
        return tenNguoiGiao;
    }

    public void setTenNguoiGiao(String tenNguoiGiao) {
        this.tenNguoiGiao = tenNguoiGiao;
    }

    public String getSdtNguoiGiao() {
        return sdtNguoiGiao;
    }

    public void setSdtNguoiGiao(String sdtNguoiGiao) {
        this.sdtNguoiGiao = sdtNguoiGiao;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Long getThoiGianGiao() {
        return thoiGianGiao;
    }

    public void setThoiGianGiao(Long thoiGianGiao) {
        this.thoiGianGiao = thoiGianGiao;
    }

    public Long getThoiGianNhan() {
        return thoiGianNhan;
    }

    public void setThoiGianNhan(Long thoiGianNhan) {
        this.thoiGianNhan = thoiGianNhan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Long getThoiGianUocTinh() {
        return thoiGianUocTinh;
    }

    public void setThoiGianUocTinh(Long thoiGianUocTinh) {
        this.thoiGianUocTinh = thoiGianUocTinh;
    }

    public Object[] toRowTable() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

        Date ngayNhanConvert;
        String ngayNhanConverted = null;
        if (thoiGianNhan != 0) {
            ngayNhanConvert = new Date(thoiGianNhan * 1000);
            ngayNhanConverted = sdf.format(ngayNhanConvert);
        }

        Date ngayGiaoConvert;
        String ngayGiaoConverted = null;

        if (thoiGianGiao != 0) {
            ngayGiaoConvert = new Date(thoiGianGiao * 1000);
            ngayGiaoConverted = sdf.format(ngayGiaoConvert);
        }

        Date ngayUocTinhConvert;
        String ngayUocTinhConverted = null;
        if (thoiGianUocTinh != 0) {
            ngayUocTinhConvert = new Date(thoiGianUocTinh * 1000);
            ngayUocTinhConverted = sdf.format(ngayUocTinhConvert);
        }

        if(tenNguoiGiao != null){
            System.out.println(tenNguoiGiao.isEmpty());
        }

        if (trangThai == 1) {
            return new Object[]{
                ma, diaChi, tenNguoiNhan, tenNguoiGiao != null ? (tenNguoiGiao.trim().isEmpty() ? "Chưa có người giao" : tenNguoiGiao) : "Chưa có người giao", ngayGiaoConverted != null ? ngayGiaoConverted : "Chưa giao", ngayNhanConverted != null ? ngayNhanConverted : "Chưa giao đến", ngayUocTinhConverted != null ? ngayUocTinhConverted : "Chưa ước tính", "Đang giao"
            };
        } else if (trangThai == 2) {
            return new Object[]{
                ma, diaChi, tenNguoiNhan, tenNguoiGiao != null ? (tenNguoiGiao.trim().isEmpty() ? "Chưa có người giao" : tenNguoiGiao) : "Chưa có người giao", ngayGiaoConverted != null ? ngayGiaoConverted : "Chưa giao", ngayNhanConverted != null ? ngayNhanConverted : "Chưa giao đến", ngayUocTinhConverted != null ? ngayUocTinhConverted : "Chưa ước tính", "Đã giao"
            };
        } else if (trangThai == 3) {
            return new Object[]{
                ma, diaChi, tenNguoiNhan, tenNguoiGiao != null ? (tenNguoiGiao.trim().isEmpty() ? "Chưa có người giao" : tenNguoiGiao) : "Chưa có người giao", ngayGiaoConverted != null ? ngayGiaoConverted : "Chưa giao", ngayNhanConverted != null ? ngayNhanConverted : "Chưa giao đến", ngayUocTinhConverted != null ? ngayUocTinhConverted : "Chưa ước tính", "Hủy giao"
            };
        } else if (trangThai == 4) {
            return new Object[]{
                ma, diaChi, tenNguoiNhan, tenNguoiGiao != null ? (tenNguoiGiao.trim().isEmpty() ? "Chưa có người giao" : tenNguoiGiao) : "Chưa có người giao", ngayGiaoConverted != null ? ngayGiaoConverted : "Chưa giao", ngayNhanConverted != null ? ngayNhanConverted : "Chưa giao đến", ngayUocTinhConverted != null ? ngayUocTinhConverted : "Chưa ước tính", "Hẹn lại"
            };
        } else {
            return new Object[]{
                ma, diaChi, tenNguoiNhan, tenNguoiGiao != null ? (tenNguoiGiao.trim().isEmpty() ? "Chưa có người giao" : tenNguoiGiao) : "Chưa có người giao", ngayGiaoConverted != null ? ngayGiaoConverted : "Chưa giao", ngayNhanConverted != null ? ngayNhanConverted : "Chưa giao đến", ngayUocTinhConverted != null ? ngayUocTinhConverted : "Chưa ước tính", "Chuẩn bị"
            };
        }

    }

}
