/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author zan
 */
public class AplikasiFileIO extends JFrame {
    private JTextArea textArea;
    private JTextField txtUsername;
    private JButton btnSimpanText, btnAppendText, btnBukaText;
    private JButton btnSimpanConfig, btnMuatConfig;
    private JButton btnSimpanObjek, btnMuatObjek;
    private JSpinner spinnerFontSize;
    
    public AplikasiFileIO() {
        setTitle("Aplikasi File I/O - Modul 9");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        
        // LATIHAN 2: Auto load last_notes.txt saat aplikasi dibuka
        autoLoadLastNotes();
    }
    
    private void initComponents() {
        // Text Area
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Panel Atas - Username dan Font Size
        JPanel panelAtas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAtas.add(new JLabel("Username:"));
        txtUsername = new JTextField(15);
        panelAtas.add(txtUsername);
        
        panelAtas.add(Box.createHorizontalStrut(20));
        panelAtas.add(new JLabel("Font Size:"));
        SpinnerNumberModel model = new SpinnerNumberModel(14, 8, 72, 1);
        spinnerFontSize = new JSpinner(model);
        spinnerFontSize.addChangeListener(e -> {
            int size = (Integer) spinnerFontSize.getValue();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, size));
        });
        panelAtas.add(spinnerFontSize);
        
        // Panel File Text Operations (Latihan 1 & 4)
        JPanel panelFile = new JPanel();
        panelFile.setBorder(BorderFactory.createTitledBorder("File Text Operations"));
        btnSimpanText = new JButton("Simpan Text");
        btnAppendText = new JButton("Append Text");
        btnBukaText = new JButton("Buka Text");
        
        btnSimpanText.addActionListener(e -> simpanText());
        btnAppendText.addActionListener(e -> appendText());
        btnBukaText.addActionListener(e -> bacaText());
        
        panelFile.add(btnSimpanText);
        panelFile.add(btnAppendText);
        panelFile.add(btnBukaText);
        
        // Panel Config Binary (Latihan 1)
        JPanel panelConfig = new JPanel();
        panelConfig.setBorder(BorderFactory.createTitledBorder("Config Binary"));
        btnSimpanConfig = new JButton("Simpan Config");
        btnMuatConfig = new JButton("Muat Config");
        
        btnSimpanConfig.addActionListener(e -> simpanConfig());
        btnMuatConfig.addActionListener(e -> muatConfig());
        
        panelConfig.add(btnSimpanConfig);
        panelConfig.add(btnMuatConfig);
        
        // Panel Object Serialization (Latihan 3)
        JPanel panelObjek = new JPanel();
        panelObjek.setBorder(BorderFactory.createTitledBorder("Object Serialization"));
        btnSimpanObjek = new JButton("Simpan Objek");
        btnMuatObjek = new JButton("Muat Objek");
        
        btnSimpanObjek.addActionListener(e -> simpanObjek());
        btnMuatObjek.addActionListener(e -> muatObjek());
        
        panelObjek.add(btnSimpanObjek);
        panelObjek.add(btnMuatObjek);
        
        // Panel Bawah
        JPanel panelBawah = new JPanel(new GridLayout(3, 1, 5, 5));
        panelBawah.add(panelFile);
        panelBawah.add(panelConfig);
        panelBawah.add(panelObjek);
        
        // Layout Utama
        setLayout(new BorderLayout(5, 5));
        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);
    }
    
    // ========== LATIHAN 1: BACA TEXT dengan Try-Catch-Finally ==========
    private void bacaText() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null;
            
            try {
                reader = new BufferedReader(new FileReader(file));
                textArea.setText("");
                
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                
                JOptionPane.showMessageDialog(this, "File berhasil dibuka!");
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, 
                    "File tidak ditemukan: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error membaca file: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } finally {
                // Cleanup - pastikan reader ditutup
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    // ========== LATIHAN 1: SIMPAN TEXT dengan Try-with-Resources ==========
    private void simpanText() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Try-with-Resources - otomatis close()
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error menyimpan file: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== LATIHAN 4: APPEND TEXT ==========
    // Menggunakan FileWriter(File file, boolean append)
    private void appendText() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Parameter kedua = true untuk append mode
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true))) {
                
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, 
                    "Text berhasil ditambahkan ke file (append)!");
                    
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error menambahkan text: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ========== LATIHAN 1: SIMPAN CONFIG dengan DataOutputStream ==========
    private void simpanConfig() {
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("config.bin"))) {
            
            int fontSize = (Integer) spinnerFontSize.getValue();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, 
                "Config berhasil disimpan!\nFont Size: " + fontSize);
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== LATIHAN 1: MUAT CONFIG dengan DataInputStream ==========
    private void muatConfig() {
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("config.bin"))) {
            
            int fontSize = dis.readInt();
            spinnerFontSize.setValue(fontSize);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            
            JOptionPane.showMessageDialog(this, 
                "Config berhasil dimuat!\nFont Size: " + fontSize);
                
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, 
                "File config.bin tidak ditemukan!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== LATIHAN 3: SIMPAN OBJEK dengan ObjectOutputStream ==========
    private void simpanObjek() {
        String username = txtUsername.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Username tidak boleh kosong!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int fontSize = (Integer) spinnerFontSize.getValue();
        UserConfig config = new UserConfig(username, fontSize);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("userconfig.dat"))) {
            
            oos.writeObject(config);
            
            JOptionPane.showMessageDialog(this, 
                "Objek UserConfig berhasil disimpan!\n" + config, 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== LATIHAN 3: MUAT OBJEK dengan ObjectInputStream ==========
    private void muatObjek() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("userconfig.dat"))) {
            
            // Baca objek dan lakukan CASTING
            UserConfig config = (UserConfig) ois.readObject();
            
            // Set nilai ke UI
            txtUsername.setText(config.getUsername());
            spinnerFontSize.setValue(config.getFontSize());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
            
            JOptionPane.showMessageDialog(this, 
                "Objek UserConfig berhasil dimuat!\n" + config, 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, 
                "File userconfig.dat tidak ditemukan!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========== LATIHAN 2: AUTO LOAD last_notes.txt ==========
    // Dipanggil di Constructor, gunakan try-catch agar tidak error jika file tidak ada
    private void autoLoadLastNotes() {
        File file = new File("last_notes.txt");
        
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                
                System.out.println("last_notes.txt berhasil dimuat otomatis.");
                
            } catch (IOException ex) {
                // Diam saja jika error (tidak menampilkan pesan)
                System.err.println("Gagal memuat last_notes.txt");
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiFileIO app = new AplikasiFileIO();
            app.setVisible(true);
        });
    }
}