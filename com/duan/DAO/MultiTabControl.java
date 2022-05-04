/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import GUI.Quanlichiphi;
import GUI.Quanlihopdong;
import GUI.Quanlikhachhang;
import GUI.Quanlinhanvien;
import GUI.Quanliphongtro;
import GUI.Quanlithanhtoan;
import com.duan.model.BeanMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static ultility.contraints.*;

/**
 *
 * @author Admin
 */
public class MultiTabControl {

    private JPanel root;
    private String NameTabSelected = "";

    private List<BeanMenu> listMenu = null;

    public MultiTabControl(JPanel rootJPanel) {
        this.root = rootJPanel;
    }

    public void setTab(String kind, JPanel panel, JLabel label) {
        NameTabSelected = kind;
        panel.setBackground(COLOR_BACKGROUND_JPN_SELECTED);
        label.setForeground(COLOR_BACKGROUND_JLB_SELECTED);
        JPanel view = null;
        switch (NameTabSelected) {
            case MENUNHANVIEN:
                view = new Quanlinhanvien();
                break;
            case MENUKHACHHANG:
                view = new Quanlikhachhang();
                break;
            case MENUHPHONGTRO:
                view = new Quanliphongtro();
                break;
            case MENUHOPDONG:
                view = new Quanlihopdong();
                break;
            case MENUCHIPHI:
                view = new Quanlichiphi();
                break;
            case MENUTHANHTOAN:
                view = new Quanlithanhtoan();
                break;
//            case MENUTHONGKE:
//                view = new StudentMarksGUI();
//                break;
        }
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(view);
        root.validate();
        root.repaint();
    }

    public void setEvent(List<BeanMenu> listMenu) {
        this.listMenu = listMenu;
        for (BeanMenu itemMenu : listMenu) {
            itemMenu.getJpn().addMouseListener(new LabelEvent(itemMenu.getKind(), itemMenu.getJpn(), itemMenu.getJlb(), itemMenu.getJlb1(), itemMenu.getTitle()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node = null;
        private String tabSelect;
        private JPanel jpnPanel;
        private JLabel jlbLabel;
        private JLabel jlbLabel1;
        private String title;

        public LabelEvent(String tabSelect, JPanel jpnPanel, JLabel jlbLabel) {
            this.tabSelect = tabSelect;
            this.jpnPanel = jpnPanel;
            this.jlbLabel = jlbLabel;
        }

        public LabelEvent(String tabSelect, JPanel jpnPanel, JLabel jlbLabel, JLabel jlbLabel1, String title) {
            this.tabSelect = tabSelect;
            this.jpnPanel = jpnPanel;
            this.jlbLabel = jlbLabel;
            this.jlbLabel1 = jlbLabel1;
            this.title = title;
        }

        @Override
        public void mouseClicked(MouseEvent me) {

            switch (tabSelect) {
                case MENUNHANVIEN:
                    node = new Quanlinhanvien();
                    break;
                case MENUKHACHHANG:
                    node = new Quanlikhachhang();
                    break;
                case MENUHPHONGTRO:
                    node = new Quanliphongtro();
                    break;
                case MENUHOPDONG:
                    node = new Quanlihopdong();
                    break;
                case MENUCHIPHI:
                    node = new Quanlichiphi();
                    break;
                case MENUTHANHTOAN:
                    node = new Quanlithanhtoan();
                    break;

                case EXIT:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            jlbLabel1.setText(title);
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(tabSelect);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            NameTabSelected = tabSelect;
            jpnPanel.setBackground(COLOR_BACKGROUND_JPN_PRESSED);
            jlbLabel.setForeground(COLOR_BACKGROUND_JLB_PRESSED);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
//            jpnPanel.setBackground(Color.yellow);
//            jlbLabel.setForeground(Color.magenta);
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            jpnPanel.setBackground(COLOR_BACKGROUND_JPN_PRESSED);
            jlbLabel.setForeground(COLOR_BACKGROUND_JLB_PRESSED);
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (!NameTabSelected.equalsIgnoreCase(tabSelect)) {
                jpnPanel.setBackground(COLOR_BACKGROUND_JPN_DEFAULT);
                jlbLabel.setForeground(COLOR_BACKGROUND_JLB_DEFAULT);
            } else {
                jpnPanel.setBackground(COLOR_BACKGROUND_JPN_SELECTED);
                jlbLabel.setForeground(COLOR_BACKGROUND_JLB_SELECTED);
            }
        }

    }

    private void setChangeBackground(String kind) {
        for (BeanMenu item : listMenu) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(COLOR_BACKGROUND_JPN_SELECTED);
                item.getJlb().setForeground(COLOR_BACKGROUND_JLB_SELECTED);
            } else {
                item.getJpn().setBackground(COLOR_BACKGROUND_JPN_DEFAULT);
                item.getJlb().setForeground(COLOR_BACKGROUND_JLB_DEFAULT);
            }
        }
    }
}
