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
    private JButton btnSimpan, btnBuka, btnAppend;
    private JButton btnSimpanConfig, btnMuatConfig;
    private JButton btnSimpanObjek, btnMuatObjek;
    private JSpinner spinnerFontSize;
    private JTextField txtUsername;
    
    public AplikasiFileIO() {
        setTitle("Aplikasi File I/O ");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        autoLoadLastNotes();
        autoLoadUserConfig();
    }
    
    private void initComponents() {
        // Text Area dengan ScrollPane
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Panel atas untuk username dan font size
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
        
        // Panel bawah untuk tombol File Operations
        JPanel panelFile = new JPanel();
        panelFile.setBorder(BorderFactory.createTitledBorder("File Text Operations"));
        btnSimpan = new JButton("Simpan Text");
        btnAppend = new JButton("Append Text");
        btnBuka = new JButton("Buka Text");
        
        btnSimpan.addActionListener(e -> simpanText(false));
        btnAppend.addActionListener(e -> simpanText(true));
        btnBuka.addActionListener(e -> bacaText());
        
        panelFile.add(btnSimpan);
        panelFile.add(btnAppend);
        panelFile.add(btnBuka);
        
        // Panel untuk Config Operations
        JPanel panelConfig = new JPanel();
        panelConfig.setBorder(BorderFactory.createTitledBorder("Config Operations"));
        btnSimpanConfig = new JButton("Simpan Config (Binary)");
        btnMuatConfig = new JButton("Muat Config (Binary)");
        
        btnSimpanConfig.addActionListener(e -> simpanConfig());
        btnMuatConfig.addActionListener(e -> muatConfig());
        
        panelConfig.add(btnSimpanConfig);
        panelConfig.add(btnMuatConfig);
        
        // Panel untuk Object Serialization
        JPanel panelObjek = new JPanel();
        panelObjek.setBorder(BorderFactory.createTitledBorder("Object Serialization"));
        btnSimpanObjek = new JButton("Simpan Objek");
        btnMuatObjek = new JButton("Muat Objek");
        
        btnSimpanObjek.addActionListener(e -> simpanObjek());
        btnMuatObjek.addActionListener(e -> muatObjek());
        
        panelObjek.add(btnSimpanObjek);
        panelObjek.add(btnMuatObjek);
        
        // Panel bawah gabungan
        JPanel panelBawah = new JPanel(new GridLayout(3, 1, 5, 5));
        panelBawah.add(panelFile);
        panelBawah.add(panelConfig);
        panelBawah.add(panelObjek);
        
        // Layout utama
        setLayout(new BorderLayout(5, 5));
        add(panelAtas, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);
    }
    
    // Latihan 1 & 4: Simpan text dengan opsi append
    private void simpanText(boolean append) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Latihan 4: Gunakan append parameter
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, append))) {
                
                if (append) {
                    writer.newLine(); // Tambah baris baru sebelum append
                }
                writer.write(textArea.getText());
                
                String mode = append ? "ditambahkan ke" : "disimpan di";
                JOptionPane.showMessageDialog(this, 
                    "Text berhasil " + mode + " file!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Latihan 1: Baca text dengan Try-Catch-Finally
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
                
                JOptionPane.showMessageDialog(this, 
                    "File berhasil dibuka!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, 
                    "File tidak ditemukan: " + file.getName(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error saat membaca file: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } finally {
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
    
    // Latihan 1: Simpan config dengan DataOutputStream
    private void simpanConfig() {
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("config.bin"))) {
            
            int fontSize = (Integer) spinnerFontSize.getValue();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, 
                "Config disimpan!\nFont Size: " + fontSize, 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Latihan 1: Muat config dengan DataInputStream
    private void muatConfig() {
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("config.bin"))) {
            
            int fontSize = dis.readInt();
            spinnerFontSize.setValue(fontSize);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            
            JOptionPane.showMessageDialog(this, 
                "Config dimuat!\nFont Size: " + fontSize, 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
                
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
    
    // Latihan 3: Simpan objek UserConfig dengan ObjectOutputStream
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
    
    // Latihan 3: Muat objek UserConfig dengan ObjectInputStream
    private void muatObjek() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("userconfig.dat"))) {
            
            // Casting objek yang dibaca
            UserConfig config = (UserConfig) ois.readObject();
            
            // Set nilai ke komponen UI
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
    
    // Latihan 2: Auto load last_notes.txt saat aplikasi dibuka
    private void autoLoadLastNotes() {
        File file = new File("last_notes.txt");
        
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                System.out.println("last_notes.txt berhasil dimuat otomatis");
            } catch (IOException ex) {
                // Diam saja jika error
                System.err.println("Tidak dapat memuat last_notes.txt");
            }
        }
    }
    
    // Auto load userconfig.dat saat aplikasi dibuka
    private void autoLoadUserConfig() {
        File file = new File("userconfig.dat");
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                
                UserConfig config = (UserConfig) ois.readObject();
                txtUsername.setText(config.getUsername());
                spinnerFontSize.setValue(config.getFontSize());
                textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
                
                System.out.println("UserConfig berhasil dimuat otomatis: " + config);
            } catch (IOException | ClassNotFoundException ex) {
                // Diam saja jika error
                System.err.println("Tidak dapat memuat userconfig.dat");
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