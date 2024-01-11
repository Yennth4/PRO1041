/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.service.DoiHangService.impl;

import com.poly.form.doihang.entity.DoiHangResponse.PhieuDoiChiTiet;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import com.poly.form.doihang.repository.DoiHangRepository.PhieuDoiChiTietRepository;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuDoiChiTietService {

    private PhieuDoiChiTietRepository phieuDoiChiTietRepository = new PhieuDoiChiTietRepository();

    public void createPhieuDoiChiTiet(ProductDetailResponse detailResponse, Long idPD, Long idLyDoDoi, String mota, Integer soLuongDoi) {
        try {
            phieuDoiChiTietRepository.createPhieuDoiChiTiet(detailResponse, idPD, idLyDoDoi, mota, soLuongDoi);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lỗi phieudoichitietservice dòng 26");
        }
    }

    public void createPhieuDoiChiTietTienMat(ProductDetailResponse detailResponse, Long idPD, Long idLyDoDoi, String mota, Integer soLuongDoi) {
        try {
            phieuDoiChiTietRepository.createPhieuDoiChiTietTienMat(detailResponse, idPD, idLyDoDoi, mota, soLuongDoi);
        } catch (Exception e) {
            System.out.println("lỗi đổi tiền mặt");
        }
    }

    public Boolean checkDoiHang(Long idSPCT, Long idPD, Long idLyDo) {
        try {
            return phieuDoiChiTietRepository.checkDoiHang(idSPCT, idPD, idLyDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

 public void addLyDoChiTiet(Integer soluong ,Long idSPCT,Long idPhieuDoi,Long idLyDo){
             try {
             phieuDoiChiTietRepository.addLyDoChiTiet(soluong, idSPCT, idPhieuDoi,idLyDo);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public List<PhieuDoiChiTiet> findAll() {
        return phieuDoiChiTietRepository.findAll();
    }

    public void deletePDCT(Long id) {
        phieuDoiChiTietRepository.deletePDCT(id);
    }

    public void deletePDCT(Long id, Integer soLuongNew) {
        phieuDoiChiTietRepository.deletePDCT(id, soLuongNew);
    }
}
