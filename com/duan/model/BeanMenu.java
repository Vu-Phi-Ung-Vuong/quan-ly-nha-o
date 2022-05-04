/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class BeanMenu {
    private String kind;
    private JPanel jpn;
    private JLabel jlb,jlb1;
    private String title;

    public BeanMenu() {
    }

    public BeanMenu(String kind, JPanel jpn, JLabel jlb) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public BeanMenu(String kind, JPanel jpn, JLabel jlb,JLabel jlb1, String title) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
        this.jlb1 = jlb1;
        this.title = title;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }

    public JLabel getJlb1() {
        return jlb1;
    }

    public void setJlb1(JLabel jlb1) {
        this.jlb1 = jlb1;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
