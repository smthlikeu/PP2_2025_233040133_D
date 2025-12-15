package id.ac.unpas.pp2.modul10.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class mahasiswaView extends JFrame {
    private JTextField txtNama, txtNIM, txtJurusan;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public mahasiswaView() {
        setTitle("MVC Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Form
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        
        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);
        
        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // Tabel
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

    public JTable getTable() { return tableMahasiswa; }
    public DefaultTableModel getTableModel() { return model; }
    
    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
    }

    // --- Listener ---
    public void addSimpanListener(ActionListener e) { btnSimpan.addActionListener(e); }
    public void addEditListener(ActionListener e) { btnEdit.addActionListener(e); }
    public void addHapusListener(ActionListener e) { btnHapus.addActionListener(e); }
    public void addClearListener(ActionListener e) { btnClear.addActionListener(e); }
    public void addTableMouseListener(MouseAdapter e) { tableMahasiswa.addMouseListener(e); }
}