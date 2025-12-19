package id.ac.unpas.pp2.modul10.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class mahasiswaView extends JFrame {
    // Komponen Form
    private JTextField txtNama, txtNIM, txtJurusan;
    
    // Komponen Tombol CRUD
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    
    // Komponen Pencarian
    private JTextField txtCari; 
    private JButton btnCari; 
    
    // Tabel
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public mahasiswaView() {
        setTitle("MVC Mahasiswa");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. BAGIAN ATAS (Form Input) ---
        JPanel panelAtas = new JPanel(new BorderLayout());
        
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        
        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);
        
        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        panelAtas.add(panelForm, BorderLayout.CENTER);


        // --- 2. BAGIAN TENGAH (Tombol & Pencarian seperti Gambar) ---
        // Kita buat container untuk menampung 2 baris tombol
        JPanel panelGabungan = new JPanel(new GridLayout(2, 1)); 

        // Baris 1: Tombol CRUD
        JPanel panelCRUD = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        panelCRUD.add(btnSimpan);
        panelCRUD.add(btnEdit);
        panelCRUD.add(btnHapus);
        panelCRUD.add(btnClear);

        // Baris 2: Pencarian
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        
        panelCari.add(new JLabel("Cari Nama:")); // Label seperti di gambar
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Masukkan kedua panel baris tadi ke panel gabungan
        panelGabungan.add(panelCRUD);
        panelGabungan.add(panelCari);
        
        // Letakkan Panel Gabungan di Bawah Form Input
        panelAtas.add(panelGabungan, BorderLayout.SOUTH);
        
        // Tambahkan panelAtas ke Frame Utama
        add(panelAtas, BorderLayout.NORTH);


        // --- 3. BAGIAN TABEL ---
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);
    }

    // --- Getter & Setter ---
    public String getNama() { return txtNama.getText(); }
    public String getNim() { return txtNIM.getText(); }
    public String getJurusan() { return txtJurusan.getText(); }

    public void setNama(String nama) { txtNama.setText(nama); }
    public void setNim(String nim) { txtNIM.setText(nim); }
    public void setJurusan(String jurusan) { txtJurusan.setText(jurusan); }

    // Getter untuk Pencarian
    public String getCari() { return txtCari.getText(); }

    public JTable getTable() { return tableMahasiswa; }
    public DefaultTableModel getTableModel() { return model; }
    
    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        txtCari.setText("");
    }

    // --- Listener ---
    public void addSimpanListener(ActionListener e) { btnSimpan.addActionListener(e); }
    public void addEditListener(ActionListener e) { btnEdit.addActionListener(e); }
    public void addHapusListener(ActionListener e) { btnHapus.addActionListener(e); }
    public void addClearListener(ActionListener e) { btnClear.addActionListener(e); }
    public void addCariListener(ActionListener e) { btnCari.addActionListener(e); }
    
    public void addTableMouseListener(MouseAdapter e) { tableMahasiswa.addMouseListener(e); }
}