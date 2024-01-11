/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.util.ph42118;

import com.poly.form.hoadon.entityHung.HoaDonChiTietHung;
import com.poly.form.hoadon.entityHung.HoaDonHung;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.BaseFont;
import com.poly.form.khachhang.entity.KhachHangHung;
import com.poly.form.khachhang.service.KhachHangService;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XuatHoaDon {

    public static final String pathUnicode = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\DuAn1\\DuAn1\\quanlybantui\\quanlybantui\\src\\com\\poly\\font\\unicode.ttf";
    KhachHangService khachHangService = new KhachHangService();

    public void exportBill(HoaDonHung hoaDon, List<HoaDonChiTietHung> listHoaDonChiTiet, String pathFile) {
        try {
            String path = pathFile + "\\" + "hoa_don" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            File file = new File(path);
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};
//  PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Table table = new Table(columWidth);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.setFont(font);

            table.addCell(new Cell().add("Túi sách Velisa").setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("Mã hóa đơn: " + hoaDon.getMaHoaDon() + "\n Velisa").setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));

            float colWidth[] = {80, 250, 200, 200};
            Table customerInforTable = new Table(colWidth);
            customerInforTable.setFont(font);
            customerInforTable.addCell(new Cell(0, 4)
                    .add("Thông tin khách hàng").setBold().setBorder(Border.NO_BORDER));

            KhachHangHung khachHang = khachHangService.getKhachHangById(hoaDon.getMaKhachHang());

            if (khachHang == null) {
                customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Khách bán lẻ").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("No").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("No").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-yyyy");
                String date = sdf.format(hoaDon.getTHoiGianTao());
                customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            } else {
                customerInforTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add(khachHang.getHoTen()).setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Số điện thoại:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add(khachHang.getSdt()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                customerInforTable.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add(khachHang.getDiaChi()).setBorder(Border.NO_BORDER));
                customerInforTable.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa dd-MM-yyyy");
                String date = sdf.format(hoaDon.getTHoiGianTao());
                customerInforTable.addCell(new Cell().add(date).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            }

            float itemColWidth[] = {15, 110, 170, 50, 110, 110};
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thông tin SP").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            int index = 1;
            DecimalFormat df = new DecimalFormat("#,###");
            for (HoaDonChiTietHung xx : listHoaDonChiTiet) {
                itemTable.addCell(new Cell().add(index++ + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenSanPham()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenMau()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuong() + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(xx.getGiaTien()) + " Vnđ").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(df.format(new BigDecimal(xx.getSoLuong()).multiply(xx.getGiaTien())) + " Vnđ").setBorder(Border.NO_BORDER));
            }
//            if (!hoaDon.getKhachHang().getMa().equals("KH000")) {
//                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//                itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
//                itemTable.addCell(new Cell().add("% giảm giá").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//               // itemTable.addCell(new Cell().add(hoaDon.getKhachHang().getCapBac() == 0 ? "0 %" : (hoaDon.getKhachHang().getCapBac() == 1 ? "3 %" : (hoaDon.getKhachHang().getCapBac() == 2 ? "5 %" : "10 %"))).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//            }
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền hóa đơn").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTienHoaDon()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

             itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giảm từ khuyến mại").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTienSauKhuyenMai()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Số tiền đã giảm").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getSoTienDaGiam()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền phải trả").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTienPhaiTra()) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền khách trả").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            BigDecimal tienKhachTra = hoaDon.getTienMatKhachTra() == null ? new BigDecimal(0) : hoaDon.getTienMatKhachTra();
            itemTable.addCell(new Cell().add(df.format(tienKhachTra) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Chuyển khoản").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            BigDecimal tienKhachCK = hoaDon.getTienKhachChuyenKhoan() == null ? new BigDecimal(0) : hoaDon.getTienKhachChuyenKhoan();
            itemTable.addCell(new Cell().add(df.format(tienKhachCK) + " Vnđ").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4)
                    .add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4)
                    .add("958/14 Âu Cơ, Phường 14, Quận Tân Bình, Tp HCM").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0924 808 789").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTable);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);

            document.close();

            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
