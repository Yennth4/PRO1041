 
package com.poly.form.doihang.service.DoiHangService.impl;

import com.poly.form.doihang.entity.DoiHangResponse.LyDoDoi;
import com.poly.form.doihang.repository.DoiHangRepository.LyDoDoiRepository;
import com.poly.form.doihang.service.DoiHangService.ILyDoDoi;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LyDoDoiService implements ILyDoDoi{
    private LyDoDoiRepository repo= new  LyDoDoiRepository();
    public List<LyDoDoi> findAll(){
        return repo.findAll();
    }
    public void createLyDoChiTiet(Long idPD,Long idLDD){
        try {
            repo.createLyDoChiTiet(idPD, idLDD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
