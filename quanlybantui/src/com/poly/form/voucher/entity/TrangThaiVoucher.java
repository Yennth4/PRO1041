
package com.poly.form.voucher.entity;

public class TrangThaiVoucher {
    private int idTrangThai;
    private String maTrangThai;
    private String tenTrangThai;
    private String moTaTrangThai;

    public TrangThaiVoucher() {
    }

    public TrangThaiVoucher(int idTrangThai, String maTrangThai, String tenTrangThai, String moTaTrangThai) {
        this.idTrangThai = idTrangThai;
        this.maTrangThai = maTrangThai;
        this.tenTrangThai = tenTrangThai;
        this.moTaTrangThai = moTaTrangThai;
    }

    public String getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public String getMoTaTrangThai() {
        return moTaTrangThai;
    }

    public void setMoTaTrangThai(String moTaTrangThai) {
        this.moTaTrangThai = moTaTrangThai;
    }

    public int getIdTrangThai() {
        return idTrangThai;
    }

    public void setIdTrangThai(int idTrangThai) {
        this.idTrangThai = idTrangThai;
    }

    public TrangThaiVoucher(String maTrangThai, String tenTrangThai, String moTaTrangThai) {
        this.maTrangThai = maTrangThai;
        this.tenTrangThai = tenTrangThai;
        this.moTaTrangThai = moTaTrangThai;
    }
   
    public Object[] toDataRow() {
        return new Object[] {
          idTrangThai , maTrangThai , tenTrangThai , moTaTrangThai  
        };
    }
}
