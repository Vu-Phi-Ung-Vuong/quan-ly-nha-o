/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.helper;

import com.duan.model.HopDong;
import com.duan.model.Khachhang;
import com.duan.model.Nhanvien;
import com.duan.model.phongtro;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class XFile {

    public static void xuatFile(Component parent, String[] header, List<Object[]> row, String fileName,String title) {
        JFileChooser fileChooser = new JFileChooser(); 
        FileNameExtensionFilter Findxlsx = new FileNameExtensionFilter("Excel(.xlsx)", "xlsx", "xlsx");
        fileChooser.setFileFilter(Findxlsx);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Admin\\Documents"));
        fileChooser.setDialogTitle("Chọn nơi lưu");
        fileChooser.setSelectedFile(new File(fileName));
        int x = fileChooser.showDialog(parent, "Chọn thư mục");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith("xlsx")) {
                    path += ".xlsx";
                }
                XExcel.clear();
                XExcel.setHeader(header);
                XExcel.setTitle(title);
                XExcel.setObjects(row);

                XExcel.create(path);
                if (MgsBoxhelper.confirm(parent, "Lưu thành công \n Bạn có muốn mở file không")) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(new File(path));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                MgsBoxhelper.alert(parent, "Lưu thất bại");
            }
        }
    }
    public static void saveQRCode(Component parent, BufferedImage icon) {
        JFileChooser fileChooser = new JFileChooser(); 
        FileNameExtensionFilter Findxlsx = new FileNameExtensionFilter("PNG", "png", "png");
        fileChooser.setFileFilter(Findxlsx);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Admin\\Documents"));
        fileChooser.setDialogTitle("Chọn nơi lưu");
        fileChooser.setSelectedFile(new File("QRcode.png"));
        int x = fileChooser.showDialog(parent, "Chọn thư mục");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith("png")) {
                    path += ".png";
                }
                ImageIO.write(icon, "PNG", fileChooser.getSelectedFile());
                if (MgsBoxhelper.confirm(parent, "Lưu thành công \n Bạn có muốn mở file không")) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(new File(path));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                MgsBoxhelper.alert(parent, "Lưu thất bại");
            }
        }
    }
     public static void exportHopDong(Component parent,Nhanvien nv , Khachhang kh,phongtro pt,HopDong hd) {

        String s = "                    CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM\n"
                + "\n"
                + "                         Độc Lập-Tự Do-Hạnh Phúc\n"
                + "\n"
                + "                                 —oOo—\n"
                + "\n"
                + "                        HỢP ĐỒNG THUÊ PHÒNG TRỌ\n"
                + "\n"
                + "                             (HĐ số - "+hd.getMaHD()+".)\n"
                + "\n"
                + "BÊN A: BÊN CHO THUÊ\n"
                + "\n"
                + "Họ và Tên: "+nv.getHoten()+"\n"
                + "\n"
                + "Năm sinh: "+Xdate.toString(nv.getNgaysinh(),"dd/MM/yyyy")+"\n"
                + "\n"
                + "BÊN B: BÊN THUÊ\n"
                + "\n"
                + "Họ và Tên: "+kh.getHoTen()+"\n"
                + "\n"
                + "Năm sinh: "+Xdate.toString(nv.getNgaysinh(),"dd/MM/yyyy")+"\n"
                + "\n"
                + "CMND:"+kh.getMaKH()+"\n"
                + "\n"
                + "Hai bên cùng thỏa thuận và đồng ý với nội dung sau:\n"
                + "\n"
                + "ĐIỀU 1: THOẢ THUẬN THUÊ.\n"
                + "\n"
                + "Bên A đồng ý cho bên B thuê và bên B đồng ý thuê phòng trọ số "+kh.getMaPT()+"\n"
                + "\n"
                + "Thời hạn hợp đồng thuê nhà trọ đến khi 1 bên đơn phương chấm dứt hộp đồng tháng kể bắt đầu từ ngày "+Xdate.toString(hd.getNgayBD(),"dd/MM/yyyy")+"\n"
                + "\n"
                + "ĐIỀU 2: TIỀN THUÊ NHÀ TRỌ.\n"
                + "\n"
                + "- Giá tiền thuê nhà là "+pt.getGiaPhong()+" đồng/tháng \n"
                + "\n"
                + "- Tiền thuê nhà bên B thanh toán cho bên A từ ngày 1 mỗi tháng.\n"
                + "\n"
                + "- Bên B ngưng hợp đồng trước thời hạn sẽ bị mất tiền cọc. Ngược lại nếu Bên A ngưng hợp đồng (lấy lại nhà) trước thời hạn thì bồi thường gấp đôi số tiền bên B đã đặt cọc.\n"
                + "\n"
                + "ĐIỀU 3: TRÁCH NHIỆM BÊN A.\n"
                + "\n"
                + "- Giao nhà, trang thiết bị trong nhà cho bên B đúng ngày ký hợp đồng.\n"
                + "\n"
                + "- Hướng dẫn bên B chấp hành đúng các quy định của địa phương, hoàn tất mọi thủ tục giấy tờ đăng ký tạm trú cho bên B.\n"
                + "\n"
                + "ĐIỀU 4: TRÁCH NHIỆM BÊN B.\n"
                + "\n"
                + "- Trả tiền thuê nhà hàng tháng theo hợp đồng.\n"
                + "\n"
                + "- Sử dụng đúng mục đích thuê nhà, khi cần sữa chữa, cải tạo theo yêu cầu sử dụng riêng phải được sự đồng ý của bên A.\n"
                + "\n"
                + "- Đồ đạc trang thiết bị trong nhà phải có trách nhiệm bảo quản cẩn thận không làm hư hỏng mất mát.\n"
                + "\n"
                + "ĐIỀU 5: ĐIỀU KHOẢN CHUNG.\n"
                + "\n"
                + "Bên A và bên B thực hiện đúng các điều khoản ghi trong hợp đồng.\n"
                + "\n"
                + "Trường hợp có tranh chấp hoặc một bên vi phạm hợp đồng thì hai bên cùng nhau bàn bạc giải quyết, nếu không giải quyết được thì yêu cầu cơ quan có thẩm quyền giải quyết.\n"
                + "\n"
                + "Hợp đồng này sẽ được thanh lý khi hết hạn thuê trong điều 1, trong trường hợp bên B muốn gia hạn hoặc thanh lý hợp đồng phải báo bên A biết trước 30 ngày kết thúc hợp đồng\n"
                + "\n"
                + "Hợp đồng được lập thành 02 bản mỗi bản có 02 trang có giá trị ngang nhau, mỗi bên giữ 01 bản, hai bên đã đọc cùng đồng ý các điều khoản trên và ký tên dưới đây.\n"
                + "\n"
                + "                               "+Xdate.toString(hd.getNgayBD(),"dd/MM/yyyy")+"\n"
                + "\n"
                + "\n"
                + "BÊN A (BÊN CHO THUÊ)                                 BÊN B (BÊN THUÊ)               \n"
                + "     …………………                                             ……………………";

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter Findxlsx = new FileNameExtensionFilter("DOC", "doc", "doc");
        fileChooser.setFileFilter(Findxlsx);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Admin\\Documents"));
        fileChooser.setDialogTitle("Chọn nơi lưu");
        fileChooser.setSelectedFile(new File("HopDong.doc"));
        int x = fileChooser.showDialog(parent, "Chọn thư mục");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fr = new FileWriter(fileChooser.getSelectedFile());
                BufferedWriter bw = new BufferedWriter(fr);
                String tamp = s;
                bw.write(tamp);
                bw.flush();
                bw.close();

                if (!Desktop.isDesktopSupported()) {
                    System.out.println("not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (fileChooser.getSelectedFile().exists()) {
                    desktop.open(fileChooser.getSelectedFile());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
