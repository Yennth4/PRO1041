package com.poly.form.voucher.service;

import com.poly.form.voucher.entity.Voucher;
import com.poly.form.voucher.repository.VoucherRepository;
import java.util.List;

public class VoucherService implements IVoucherService {

    private VoucherRepository repo;

    public VoucherService() {
        this.repo = new VoucherRepository();
    }

    @Override
    public List<Voucher> getAll() {
        return repo.getAll();
    }

    @Override
    public void them(Voucher v, String tenLoaiVoucher, String tenTrangThai) throws Exception {
        repo.them(v, tenLoaiVoucher, tenTrangThai);
    }

    @Override
    public void capNhat(Voucher v, String tenLoaiVoucher, String tenTrangThai, int id) throws Exception {
        repo.capNhat(v, tenLoaiVoucher, tenTrangThai, id);
    }

    @Override
    public void xoa(int index) throws Exception {
        repo.xoa(index);
    }

    @Override
    public List<Voucher> locTimKiemTheoTrangThai(String trangThai) {
        return repo.locTimKiemTheoTrangThai(trangThai);
    }

    @Override
    public List<Voucher> locTimKiemTheoLoaiVoucher(String loai) {
        return repo.locTimKiemTheoLoaiVoucher(loai);
    }

    @Override
    public List<Voucher> locTimKiemTheoMa(String ma) {
        return repo.locTimKiemTheoMa(ma);
    }

    @Override
    public List<Voucher> sortLamMoi() {
        return repo.sortLamMoi();
    }
    
    @Override
    public List<Voucher> phanTrang(int min, int max){
        return repo.phanTrang(min, max);
    }
}
