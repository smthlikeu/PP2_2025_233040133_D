package id.ac.unpas.pp2.modul10.controller;

import id.ac.unpas.pp2.modul10.model.Mahasiswa;
import id.ac.unpas.pp2.modul10.model.mahasiswaModel;
import id.ac.unpas.pp2.modul10.view.mahasiswaView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class mahasiswaController {
    private mahasiswaView view;
    private mahasiswaModel model;

    public mahasiswaController(mahasiswaView view, mahasiswaModel model) {
        this.view = view;
        this.model = model;

        this.view.addSimpanListener(e -> simpanData());
        this.view.addEditListener(e -> editData());
        this.view.addHapusListener(e -> hapusData());
        this.view.addClearListener(e -> view.clearForm());
        this.view.addCariListener(e -> cariData());
        
        this.view.addTableMouseListener(new MouseAdapter() {
            @Override
            // PERBAIKAN: Hapus "be" yang tidak sengaja terketik
            public void mouseClicked(MouseEvent e) { 
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.setNama(view.getTableModel().getValueAt(row, 1).toString());
                    view.setNim(view.getTableModel().getValueAt(row, 2).toString());
                    view.setJurusan(view.getTableModel().getValueAt(row, 3).toString());
                }
            }
        });

        loadData();
    }

    private void loadData() {
        view.getTableModel().setRowCount(0);
        List<Mahasiswa> list = model.getAllMahasiswa();
        int no = 1;
        for (Mahasiswa m : list) {
            view.getTableModel().addRow(new Object[]{
                no++, m.getNama(), m.getNim(), m.getJurusan()
            });
        }
    }
    
    private void cariData() {
        // Karena view.getCari() sekarang sudah return String, .trim() akan berhasil
        String keyword = view.getCari().trim(); 
        
        if (keyword.isEmpty()) {
            loadData();
            return;
        }
        
        view.getTableModel().setRowCount(0);
        List<Mahasiswa> list = model.cariMahasiswaByNama(keyword);
        int no = 1;
        for (Mahasiswa m : list) {
            view.getTableModel().addRow(new Object[]{
                no++, m.getNama(), m.getNim(), m.getJurusan()
            });
        }
        
        if (view.getTableModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Data tidak ditemukan!");
        }
    }

    private void simpanData() {
        if (view.getNama().isEmpty() || view.getNim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data Kosong!");
            return;
        }
        if (model.cekNIM(view.getNim())) {
            JOptionPane.showMessageDialog(view, "NIM Sudah Ada!");
            return;
        }
        try {
            model.insertMahasiswa(view.getNama(), view.getNim(), view.getJurusan());
            JOptionPane.showMessageDialog(view, "Berhasil Simpan");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal: " + e.getMessage());
        }
    }

    private void editData() {
        try {
            model.updateMahasiswa(view.getNama(), view.getNim(), view.getJurusan());
            JOptionPane.showMessageDialog(view, "Berhasil Edit");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() {
        try {
            model.deleteMahasiswa(view.getNim());
            JOptionPane.showMessageDialog(view, "Berhasil Hapus");
            loadData();
            view.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Hapus: " + e.getMessage());
        }
    }
}