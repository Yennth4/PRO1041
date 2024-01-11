/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.service.DoiHangService.impl;

import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.repository.DoiHangRepository.HoaDonRepository;
import com.poly.form.doihang.repository.DoiHangRepository.PhieuDoiRepository;
import com.poly.form.doihang.service.DoiHangService.IPhieuDoiService;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuDoiService implements IPhieuDoiService {

    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private PhieuDoiRepository PhieuDoiRepository = new PhieuDoiRepository();

   
    public List<HoaDon> getAllDaDoi(String keySearch,Integer trangThai) {
        return hoaDonRepository.findAllDaDoi(  keySearch , trangThai);
    }

    public  List<HoaDon>  lichSuDoi(String keySearch,Integer trangThai){
        return hoaDonRepository.findAllDaDoi( keySearch, trangThai);        
    }
    public List<HoaDon> getAll3Day(String keySearch,Integer trangThai) {
        List<HoaDon> listHD=hoaDonRepository.findAllChuaDoi(keySearch,trangThai);
        List<HoaDon> list3day= new ArrayList<>(); 
          long currentTime = System.currentTimeMillis();
          for (HoaDon hoaDon : listHD) {
               if (currentTime - hoaDon.getThoiGianTao().getTime() <= 3 * 24 * 60 * 60 * 1000) {
               list3day.add(hoaDon);
            }
        }
        return list3day;
    }

    public void createPhieuDoi(Long idHoaDon) {
        try {
            PhieuDoiRepository.createPhieuDoi(idHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getIdPhieuDoibyIdHoaDon(Long idHoaDon) {
        try {
            return PhieuDoiRepository.getIdPhieuDoibyIdHoaDon(idHoaDon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
