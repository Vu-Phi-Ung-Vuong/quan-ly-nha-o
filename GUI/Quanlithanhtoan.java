/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.duan.DAO.HopdongDao;
import com.duan.DAO.KhachhangDao;
import com.duan.DAO.QLChiPhiDao;
import com.duan.DAO.ThanhToanDAO;
import com.duan.helper.MgsBoxhelper;
import com.duan.helper.XFile;
import com.duan.helper.Xdate;
import com.duan.helper.utilityHelper;
import com.duan.model.ChiPhi;
import com.duan.model.HopDong;
import com.duan.model.Khachhang;
import com.duan.model.ThanhToan;
import static java.awt.Color.white;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author THANH
 */
public class Quanlithanhtoan extends javax.swing.JPanel {

    /**
     * Creates new form Quanlithanhtoan
     */
    public Quanlithanhtoan() {
        initComponents();
        load();
        load2();
        fillComboBoxCp();
        fillComboBoxkh();
    }
    ThanhToanDAO dao = new ThanhToanDAO();
    KhachhangDao khdao = new KhachhangDao();
    QLChiPhiDao cpdao = new QLChiPhiDao();
    int index = 0;

    private void thanhtoan() {
        load();
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tbldathanhtoan.getModel();
        model.setRowCount(0);
        try {
            List<ThanhToan> list = dao.selectAll();
            for (ThanhToan th : list) {
                Object[] row = {
                    th.getMaCP(),
                    th.getMaKH(),
                    th.getDongia(),
                    th.getNgaydongtien(),
                    th.isTrangthai(),};
                model.addRow(row);
            }
            this.load2();
        } catch (Exception e) {
            MgsBoxhelper.alert(this, "Lỗi Truy Vấn!");

        }
    }

    ThanhToan getModel() {
        ThanhToan model = new ThanhToan();
        model.setMaCP(String.valueOf(cbomacp.getSelectedItem()));
        model.setMaKH(String.valueOf(cbomakh.getSelectedItem()));
        model.setDongia(Float.valueOf(txtdongia.getText()));
        model.setNgaydongtien(date.getDate());
        model.setTrangthai(rdoxong.isSelected());
        return model;
    }

    void setModel(ThanhToan model) {
        cbomacp.setSelectedItem(model.getMaCP());
        cbomakh.setSelectedItem((model.getMaKH()));
        txtdongia.setText(String.valueOf(model.getDongia()));
        date.setDate(model.getNgaydongtien());
        rdoxong.setSelected(model.isTrangthai());
        rdochua.setSelected(!model.isTrangthai());
    }

    void getstatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnNew.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tbldathanhtoan.getRowCount() - 1;
        boolean lastt = this.index < tblchuathanhtoan.getRowCount() - 1;
        btnFirst.setEnabled(!insertable && first);
        btnPreview.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && lastt);
        btnNext.setEnabled(!insertable && lastt);
    }

    void load2() {
        DefaultTableModel model = (DefaultTableModel) tblchuathanhtoan.getModel();
        model.setRowCount(0);
        try {
            List<ThanhToan> list = dao.selectAll2();
            for (ThanhToan th : list) {
                Object[] row = {
                    th.getMaCP(),
                    th.getMaKH(),
                    th.getDongia(),
                    th.getNgaydongtien(),
                    th.isTrangthai()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MgsBoxhelper.alert(this, "Lỗi Truy Vấn!");
        }
    }

    void insert() {
        ThanhToan th = getModel();
        try {
            dao.insert(th);
            load();
            clear();
            MgsBoxhelper.alert(this, "Thêm Thành Công");
        } catch (Exception e) {
            MgsBoxhelper.alert(this, "Thêm Thất Bại!");
        }
    }

    void clear() {
        this.setModel(new ThanhToan());
        this.getstatus(true);
    }

    void update() {
        ThanhToan th = getModel();
        try {
            dao.update(th);
            this.load();
            this.clear();
            MgsBoxhelper.alert(this, "Update Thành Công");
        } catch (Exception w) {
            MgsBoxhelper.alert(this, "Update Thất Bại!");
        }
    }

    void delete() {
        if (MgsBoxhelper.confirm(this, "Bạn có muốn xóa?")) {
            String makh = String.valueOf(cbomakh.getSelectedItem());
            String macp = String.valueOf(cbomacp.getSelectedItem());
            try {
                dao.delete1(makh, macp);
                this.load();
                this.clear();
                MgsBoxhelper.alert(this, "Xóa Thành Công");
            } catch (Exception e) {
                MgsBoxhelper.alert(this, "Xóa Thất Bại");
            }
        }
    }

    void edit() {
        try {
            String macd = (String) tbldathanhtoan.getValueAt(this.index, 0);
            ThanhToan model = dao.selectById(macd);
            if (model != null) {
                this.setModel(model);
                this.getstatus(false);
            }
        } catch (Exception e) {
            MgsBoxhelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void edit1() {
        try {
            String macd = (String) tblchuathanhtoan.getValueAt(this.index, 0);
            ThanhToan model = dao.selectById2(macd);
            if (model != null) {
                this.setModel(model);
                this.getstatus(false);
            }
        } catch (Exception e) {
            MgsBoxhelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillComboBoxCp() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbomacp.getModel();
        model.removeAllElements();
        List<ChiPhi> list = cpdao.selectAll();
        for (ChiPhi cp : list) {
            cbomacp.addItem(cp.getMaCP());

        }
    }

    void fillComboBoxkh() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbomakh.getModel();
        model.removeAllElements();
        List<Khachhang> list = khdao.selectAll();
        for (Khachhang kh : list) {
            cbomakh.addItem(kh.getMaKH());

        }
    }

    void frist() {
        this.index = 0;
        this.edit();
    }

    void last() {
        this.index = tbldathanhtoan.getRowCount() - 1;
        this.edit();
    }

    void prev() {
        if (this.index > 0) {
            this.index--;
            this.edit();
        }
    }

    void next() {
        if (this.index < tbldathanhtoan.getRowCount() - 1) {
            this.index++;
            this.edit();
        }
    }

    void xuatFile() {
        String[] header = new String[]{"Mã chi phí", "Mã khách hàng", "Đơn giá", "Bắt đầu thanh toán", "Trạng thái"};
        List<ThanhToan> list = new ThanhToanDAO().selectAll1();
        List<Object[]> listObjs = new ArrayList<>();
        list.forEach((ThanhToan) -> {
            listObjs.add(ThanhToan.toObject());
        });
        String fileName = "ThanhToan.xlsx";
        String title = "Danh sách thanh toán ";
        XFile.xuatFile(this, header, listObjs, fileName, title);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tblNguoiHoc1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbluser = new javax.swing.JLabel();
        lblpasss = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        lblvaitro = new javax.swing.JLabel();
        lblvaitro1 = new javax.swing.JLabel();
        rdoxong = new javax.swing.JRadioButton();
        rdochua = new javax.swing.JRadioButton();
        lblvaitro2 = new javax.swing.JLabel();
        cbomacp = new javax.swing.JComboBox<>();
        cbomakh = new javax.swing.JComboBox<>();
        date = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPreview = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldathanhtoan = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblchuathanhtoan = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        jScrollPane3.setBorder(null);

        tblNguoiHoc1.setAutoCreateRowSorter(true);
        tblNguoiHoc1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNguoiHoc1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NH", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐIỆN THOẠI", "EMAIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiHoc1.setFocusable(false);
        tblNguoiHoc1.setRowHeight(24);
        tblNguoiHoc1.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tblNguoiHoc1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Thanh Toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbluser.setText("MaCP:");

        lblpasss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblpasss.setText("MaKH:");

        lblvaitro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblvaitro.setText("Đơn giá thanh toán:");

        lblvaitro1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblvaitro1.setText("Bắt đầu đóng tiền từ ngày:");

        buttonGroup1.add(rdoxong);
        rdoxong.setSelected(true);
        rdoxong.setText("Đã Thanh Toán");

        buttonGroup1.add(rdochua);
        rdochua.setText("Chưa Thanh Toán");

        lblvaitro2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblvaitro2.setText("Trạng thái:");

        cbomacp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbomakh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblvaitro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblvaitro2)
                                .addGap(140, 140, 140)
                                .addComponent(rdoxong)
                                .addGap(34, 34, 34)
                                .addComponent(rdochua))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblvaitro1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbluser)
                            .addComponent(lblpasss))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbomacp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbomakh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbluser)
                    .addComponent(cbomacp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblpasss)
                    .addComponent(cbomakh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvaitro)
                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblvaitro1))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoxong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdochua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblvaitro2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnFirst.setBackground(new java.awt.Color(204, 255, 204));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/previous (1).png"))); // NOI18N
        btnFirst.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.setDefaultCapable(false);
        btnFirst.setFocusable(false);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPreview.setBackground(new java.awt.Color(204, 255, 204));
        btnPreview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/next.png"))); // NOI18N
        btnPreview.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        btnPreview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreview.setDefaultCapable(false);
        btnPreview.setFocusable(false);
        btnPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 255, 204));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/next (1).png"))); // NOI18N
        btnNext.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setDefaultCapable(false);
        btnNext.setFocusable(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(204, 255, 204));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/previous.png"))); // NOI18N
        btnLast.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.black, java.awt.Color.black));
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.setDefaultCapable(false);
        btnLast.setFocusable(false);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete v1 100.png"))); // NOI18N
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/update v1 100.png"))); // NOI18N
        btnNew.setBorderPainted(false);
        btnNew.setContentAreaFilled(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/save v1 100.png"))); // NOI18N
        btnUpdate.setBorderPainted(false);
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/add v1 100.png"))); // NOI18N
        btnAdd.setBorderPainted(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPreview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane2.setBorder(null);

        tbldathanhtoan.setAutoCreateRowSorter(true);
        tbldathanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbldathanhtoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ CP", "MÃ KH", "ĐƠN GIÁ", "BẮT ĐẦU THANH TOÁN", "TRẠNG THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldathanhtoan.setFocusable(false);
        tbldathanhtoan.setRowHeight(24);
        tbldathanhtoan.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tbldathanhtoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldathanhtoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldathanhtoan);

        jTabbedPane1.addTab("Danh sách đã thanh toán ", jScrollPane2);

        jScrollPane4.setBorder(null);

        tblchuathanhtoan.setAutoCreateRowSorter(true);
        tblchuathanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblchuathanhtoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ CP", "MÃ KH", "ĐƠN GIÁ", "NGÀY BẮT ĐẦU", "TRẠNG THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblchuathanhtoan.setFocusable(false);
        tblchuathanhtoan.setRowHeight(24);
        tblchuathanhtoan.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblchuathanhtoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblchuathanhtoanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblchuathanhtoan);

        jTabbedPane1.addTab("Danh sách chưa thanh toán", jScrollPane4);

        jButton3.setBackground(new java.awt.Color(0, 0, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Xuất File Execl");
        jButton3.setBorderPainted(false);
        jButton3.setDefaultCapable(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        frist();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPreviewActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (utilityHelper.checkNullText(txtdongia)
                && utilityHelper.checkDate(date)) {

            update();

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (utilityHelper.checkNullText(txtdongia)
                && utilityHelper.checkDate(date)) {

            insert();

        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        xuatFile();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbldathanhtoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldathanhtoanMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tbldathanhtoan.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tbldathanhtoanMouseClicked

    private void tblchuathanhtoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblchuathanhtoanMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblchuathanhtoan.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit1();
            }
        }
    }//GEN-LAST:event_tblchuathanhtoanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreview;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbomacp;
    private javax.swing.JComboBox<String> cbomakh;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblpasss;
    private javax.swing.JLabel lbluser;
    private javax.swing.JLabel lblvaitro;
    private javax.swing.JLabel lblvaitro1;
    private javax.swing.JLabel lblvaitro2;
    private javax.swing.JRadioButton rdochua;
    private javax.swing.JRadioButton rdoxong;
    private javax.swing.JTable tblNguoiHoc1;
    private javax.swing.JTable tblchuathanhtoan;
    private javax.swing.JTable tbldathanhtoan;
    private javax.swing.JTextField txtdongia;
    // End of variables declaration//GEN-END:variables
}
