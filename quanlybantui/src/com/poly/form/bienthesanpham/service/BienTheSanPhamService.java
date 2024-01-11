package com.poly.form.bienthesanpham.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;
import com.poly.form.bienthesanpham.repository.BienTheSanPhamRepository;
import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.sanpham.repository.SanPhamRepository;
import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import com.poly.form.thuoctinh.repository.ThuocTinhMauRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static com.poly.util.ph31848.GetDateTimeCurrent.getTimeNow;
import com.poly.util.ph31848.MaRandom;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author longnvph31848
 */
public class BienTheSanPhamService implements IBienTheSanPhamService {

    private BienTheSanPhamRepository repo;
    private SanPhamRepository repoSP;
    private ThuocTinhMauRepository repoMau;
    private String pathFolder = "D:\\Da1_v7\\quanlybantui\\quanlybantui\\documents\\";

    public BienTheSanPhamService() {    
        repo = new BienTheSanPhamRepository();
        repoSP = new SanPhamRepository();
        repoMau = new ThuocTinhMauRepository();
    }
    @Override
    public List<BienTheSanPhamDTO> findByMaSanPham(String maSanPham) {
        return repo.findByMaSanPham(maSanPham);
    }

    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }

    @Override
    public void insertBienThe(BienTheSanPham bienThe) {
        repo.insertBienThe(bienThe);
    }

    @Override
    public void updateBienThe(BienTheSanPham bienThe) {
        repo.updateBienThe(bienThe);
    }

    @Override
    public void deleteBienThe(Long id) {
        repo.deleteBienThe(id);
    }

    @Override
    public void tatTrangThai(Long id) {
        repo.tatTrangThai(id);
    }

    @Override
    public List<BienTheSanPhamDTO> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean isExistBienThe(BienTheSanPham bienThe) {
        return repo.isExistBienThe(bienThe);
    }

    @Override
    public BienTheSanPhamDTO findBienTheByID(Long id) {
        return repo.findBienTheByID(id);
    }

    @Override
    public Long getIDMauBienTheByIDSanPham(Long idSanPham) {
        return repo.getIDMauBienTheByIDSanPham(idSanPham);
    }

    @Override
    public boolean bienTheIsSale(Long id) {
        return repo.bienTheIsSale(id);
    }

    @Override
    public boolean insertListBienThe(List<BienTheSanPham> list) {
        return repo.insertListBienThe(list);
    }

    @Override
    public String exportListToExcel(List<BienTheSanPhamDTO> list, SanPhamDTO sp) {
        XSSFWorkbook wordkbook = new XSSFWorkbook();
        XSSFSheet sheet = wordkbook.createSheet("DanhSachBienThe");
        XSSFRow row = null;
        Cell cell = null;

        // Styte tiêu đề
        // Tạo CellStyle để tô nền và in đậm
        CellStyle style = wordkbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Mã sản phẩm
        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã SP: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getMaSanPham());
        // Tên sản phẩm
        row = sheet.createRow(2);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Tên SP: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getTenSanPham());
        // Thể loại
        row = sheet.createRow(3);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Thể loại: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getTheLoai());
        // Nhà cung cấp
        row = sheet.createRow(4);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("NCC: ");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(sp.getNhaCungCap());

        // In danh sách
        row = sheet.createRow(6);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã");
        cell.setCellStyle(style);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Màu");
        cell.setCellStyle(style);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Số lượng");
        cell.setCellStyle(style);
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Giá niêm yết");
        cell.setCellStyle(style);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Giá bán");
        cell.setCellStyle(style);
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Trạng thái");
        cell.setCellStyle(style);
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Đã bán");
        cell.setCellStyle(style);

        for (int i = 0; i < list.size(); i++) {
            BienTheSanPhamDTO bt = list.get(i);
            row = sheet.createRow(6 + i);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(i + 1);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(bt.getMa());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(bt.getMauSac());

            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(bt.getSoLuong());

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(bt.getGiaNiemYet());

            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue(bt.getGiaBan());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue((bt.getTrangThai() == 0) ? "Hoạt động" : "Không hoạt động");

            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(bt.getTongDaBan());
        }
        String path = pathFolder + "excel\\" + sp.getMaSanPham() + " " + getTimeNow() + ".xlsx";
        File file = new File(path);
        try {
            FileOutputStream fis = new FileOutputStream(file);
            wordkbook.write(fis);
            fis.close();
            return path;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String importExcel() {
        JFileChooser fileChooser = new JFileChooser(pathFolder);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(selectedFile); XSSFWorkbook workbook = new XSSFWorkbook(fis);) {

                // Lấy sheet đầu tiên từ workbook
                XSSFSheet sheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();

                // Tạo list chứa các biến thể import
                List<BienTheSanPham> list = new ArrayList<>();

                // Tạo Set để kiểm tra có biến thể trùng lặp không
                Set<Long> setMau = new HashSet<>();
                while (iterator.hasNext()) {

                    Row nextRow = iterator.next();
                    if (nextRow.getRowNum() == 0 || nextRow.getRowNum() == 1) {
                        continue;
                    }

                    Iterator<Cell> iteratorCell = nextRow.cellIterator();
                    BienTheSanPham bienThe = new BienTheSanPham();

                    while (iteratorCell.hasNext()) {
                        Cell nextCell = iteratorCell.next();
                        int columnIndex = nextCell.getColumnIndex();
                        switch (columnIndex) {
                            case 0:
                                if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                    return "Mã SP bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                // Case type excel
                                String maSP = "";
                                if (nextCell.getCellType() == CellType.NUMERIC) {
                                    maSP += nextCell.getNumericCellValue();
                                } else if (nextCell.getCellType() == CellType.STRING) {
                                    maSP += nextCell.getStringCellValue();
                                }
                                maSP = maSP.trim();
                                // Case type excel

                                if (maSP.isEmpty()) {
                                    return "Mã SP bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }

                                Long idSP = repoSP.getIDSanPhamByMa(maSP);
                                if (idSP == null) {
                                    return "Mã SP không tồn tại " + maSP + "\nDòng: " + (nextCell.getRowIndex() + 1);
                                }
                                bienThe.setIdSanPham(idSP);
                                break;
                            case 1:
                                if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                    return "Màu bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                // Case type excel
                                String tenMau = "";
                                if (nextCell.getCellType() == CellType.NUMERIC) {
                                    tenMau += nextCell.getNumericCellValue();
                                } else if (nextCell.getCellType() == CellType.STRING) {
                                    tenMau += nextCell.getStringCellValue();
                                }
                                tenMau = tenMau.trim();
                                // Case type excel

                                if (tenMau.isEmpty()) {
                                    return "Màu bị bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }

                                Long idMau = repoMau.getIDMauByTen(tenMau);
                                System.out.println("ID màu: " + idMau);
                                if (idMau == null) {
                                    String maMau = "";
                                    do {
                                        maMau = "MA" + MaRandom.renderMa();
                                    } while (repoMau.isExistMa(maMau));
                                    ThuocTinhMau newMau = new ThuocTinhMau(maMau, tenMau, "Mô tả: " + tenMau, 1);
                                    repoMau.insertMau(newMau);
                                    idMau = repoMau.getIDMauByTen(tenMau);
                                    System.out.println("ID màu: " + idMau);
                                }
                                bienThe.setIdMau(idMau);
                                break;
                            case 2:
                                if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                    return "Số lượng bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                // Case type excel
                                String soLuong = "";
                                if (nextCell.getCellType() == CellType.NUMERIC) {
                                    soLuong += ((int) nextCell.getNumericCellValue());
                                } else if (nextCell.getCellType() == CellType.STRING) {
                                    soLuong += nextCell.getStringCellValue();
                                }
                                soLuong = soLuong.trim();
                                System.out.println(soLuong);
                                // Case type excel
                                if (soLuong.isEmpty()) {
                                    return "Số lượng bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }

                                if (!checkNumber(soLuong)) {
                                    return "Số lượng không hợp lệ tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                bienThe.setSoLuong(Integer.valueOf(soLuong));
                                break;
                            case 3:
                                if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                    return "Giá niêm yết bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                // Case type excel
                                String giaNiemYet = "";
                                if (nextCell.getCellType() == CellType.NUMERIC) {
                                    giaNiemYet += nextCell.getNumericCellValue();
                                } else if (nextCell.getCellType() == CellType.STRING) {
                                    giaNiemYet += nextCell.getStringCellValue();
                                }
                                giaNiemYet = giaNiemYet.trim();
                                // Case type excel
                                if (giaNiemYet.isEmpty()) {
                                    return "Giá niêm yết bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                if (!checkFloat(giaNiemYet)) {
                                    return "Giá niêm yết không hợp lệ tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                bienThe.setGiaNiemYet(Float.valueOf(giaNiemYet));
                                break;

                            case 4:
                                if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                    return "Giá bán bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                // Case type excel
                                String giaBan = "";
                                if (nextCell.getCellType() == CellType.NUMERIC) {
                                    giaBan += nextCell.getNumericCellValue();
                                } else if (nextCell.getCellType() == CellType.STRING) {
                                    giaBan += nextCell.getStringCellValue();
                                }
                                giaBan = giaBan.trim();
                                // Case type excel
                                if (giaBan.isEmpty()) {
                                    return "Giá bán bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                if (!checkFloat(giaBan)) {
                                    return "Giá bán không hợp lệ tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                bienThe.setGiaBan(Float.valueOf(giaBan));
                                break;
                            case 5:
                                if (nextCell.getCellType() == CellType.BLANK || nextCell.getCellType() == CellType._NONE) {
                                    return "Trạng thái bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                // Case type excel
                                String trangThaiStr = "";
                                if (nextCell.getCellType() == CellType.NUMERIC) {
                                    trangThaiStr += nextCell.getNumericCellValue();
                                } else if (nextCell.getCellType() == CellType.STRING) {
                                    trangThaiStr += nextCell.getStringCellValue();
                                }
                                trangThaiStr = trangThaiStr.trim();
                                // Case type excel
                                if (trangThaiStr.isEmpty()) {
                                    return "Trạng thái bỏ trống tại hàng " + (nextCell.getRowIndex() + 1);
                                }
                                Integer trangThai = null;
                                if (trangThaiStr.equalsIgnoreCase("Hoạt động")) {
                                    trangThai = 1;
                                }
                                if (trangThaiStr.equalsIgnoreCase("Không hoạt động")) {
                                    trangThai = 0;
                                }
                                if (trangThai == null) {
                                    return "Vui lòng sửa lại trạng thái tại dòng " + (nextCell.getRowIndex() + 1) + "\nTrạng thái hợp lệ: Hoạt động, Không hoạt động";
                                }
                                bienThe.setTrangThai(trangThai);
                                break;
                            default:
                                break;
                        }
                        String maBienThe = "";
                        do {
                            maBienThe = "BT" + MaRandom.renderMa();
                        } while (repo.isExistMa(maBienThe));
                        String mainImage = pathFolder + "img_sp\\0.png";

                        bienThe.setMa(maBienThe);
                        bienThe.setMainImage(mainImage);

                    }
                    //check biến thể trùng lặp
                    if (repo.isExistBienThe(bienThe)) {
                        return "Đã có biến thể trùng lặp";
                    }

                    setMau.add(bienThe.getIdMau());
                    list.add(bienThe);

                }
                if (list.size() != setMau.size()) {
                    return "Không thể có 2 biến thể cùng màu\nVui lòng kiểm tra lại";
                }
                boolean save = repo.insertListBienThe(list);

                // Đóng FileInputStream và Workbook để giải phóng tài nguyên
                fis.close();
                workbook.close();
                if (!save) {
                    return "Nhập excel thành công";
                } else {
                    return "Nhập thất bại";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "Nhập thất bại";
    }

    @Override
    public String exportMauExcel() {
        XSSFWorkbook wordkbook = new XSSFWorkbook();
        XSSFSheet sheet = wordkbook.createSheet("NhapBienThe");
        XSSFRow row = null;
        Cell cell = null;

        // Styte tiêu đề
        // Tạo CellStyle để tô nền và in đậm
        CellStyle style = wordkbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Tiêu đề
        row = sheet.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Nhập danh sách biến thể");

        // Tạo cột
        row = sheet.createRow(1);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã SP");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Màu");
        cell.setCellStyle(style);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Số lượng");
        cell.setCellStyle(style);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Giá niêm yết");
        cell.setCellStyle(style);
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Giá bán");
        cell.setCellStyle(style);
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Trạng thái");
        cell.setCellStyle(style);

        
        String path = pathFolder +"excel\\MauNhapBienThe\\MauNhapBienThe.xlsx";
        File file = new File(path);
        try {
            FileOutputStream fis = new FileOutputStream(file);
            wordkbook.write(fis);
            fis.close();
            return path;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String renderQRCodeByMaBienThe(String maSP, String maBTSP) {
        String pathString = "";
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix matrix = qrCodeWriter.encode(maBTSP, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Write to file image
            pathString = pathFolder + "qr" + "\\" + maSP + "_" + maBTSP + ".png";
            Path path = FileSystems.getDefault().getPath(pathString);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return pathString;
    }

    @Override
    public void insertTextToImage(String path, String maSP, String maBTSP) {
        try {
            // Đọc ảnh từ đường dẫn
            BufferedImage image = ImageIO.read(new File(path));

            // Tạo đối tượng Graphics để thêm văn bản
            Graphics2D g = image.createGraphics();
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.PLAIN, 20)); // Thiết lập font và kích thước

            // Chèn văn bản vào ảnh
            String data = maSP + "_" + maBTSP;
            g.drawString(data, 20, image.getHeight() - 7);

            // Ghi ảnh mới có văn bản vào đường dẫn đầu ra
            ImageIO.write(image, "png", new File(path));

            // Giải phóng tài nguyên
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}