/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.service.DoiHangService.impl;

import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import com.poly.form.doihang.repository.DoiHangRepository.ProductDetailRepository;
import com.poly.form.doihang.service.DoiHangService.IProductDetailService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductDetailService implements IProductDetailService {

    private ProductDetailRepository detailRepository = new ProductDetailRepository();

    public List<ProductDetailResponse> findProductsById(Long id, String keySearch,   String tenMauSac) {
        return detailRepository.findById(id  ,keySearch ,tenMauSac);
    }

    public List<ProductDetailResponse> findProductsById(Long id ) {
        return detailRepository.findById(id);
    }

    public List<ProductDetailResponse> sanPhamDaDoi(Long id) {
        return detailRepository.sanPhamDaDoi(id);
    }

    public List<ProductDetailResponse> sanPhamChuaDoi(Long id) {
        return detailRepository.sanPhamChuaDoi(id);
    }
}
