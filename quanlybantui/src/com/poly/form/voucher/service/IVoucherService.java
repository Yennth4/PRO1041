package com.poly.form.voucher.service;

import com.poly.form.voucher.entity.Voucher;
import java.util.List;

public interface IVoucherService {

    public List<Voucher> getAll();

    public void them(Voucher v, String tenLoaiVoucher, String tenTrangThai) throws Exception;

    public void capNhat(Voucher v, String tenLoaiVoucher, String tenTrangThai, int id) throws Exception;

    public void xoa(int index) throws Exception;

    public List<Voucher> locTimKiemTheoTrangThai(String trangThai);

    public List<Voucher> locTimKiemTheoLoaiVoucher(String loai);

    public List<Voucher> locTimKiemTheoMa(String ma);

    public List<Voucher> sortLamMoi();
    
    public List<Voucher> phanTrang(int min, int max);
}
