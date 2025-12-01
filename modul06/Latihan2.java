/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul06;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author zan
 */
public class Latihan2 {
     public static void main(String[] args) {
        // 1. Buat Frame
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // 2. Buat komponen-komponen
        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField textFieldCelcius = new JTextField(10);
        JButton buttonKonversi = new JButton("Konversi");
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel labelHasil = new JLabel("0.0");

        // 3. Buat Event Listener untuk tombol Konversi
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ambil teks dari JTextField
                    String textCelcius = textFieldCelcius.getText();
                    
                    // Ubah teks ke double
                    double celcius = Double.parseDouble(textCelcius);
                    
                    // Hitung Fahrenheit
                    double fahrenheit = (celcius * 9/5) + 32;
                    
                    // Atur teks JLabel hasil
                    labelHasil.setText(String.format("%.2f", fahrenheit));
                    
                } catch (NumberFormatException ex) {
                    // Penanganan jika input bukan angka
                    JOptionPane.showMessageDialog(frame, 
                        "Masukkan angka yang valid!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    textFieldCelcius.setText("");
                    labelHasil.setText("0.0");
                }
            }
        };

        // 4. Daftarkan listener ke tombol
        buttonKonversi.addActionListener(listener);

        // 5. Tambahkan komponen ke frame
        frame.add(labelCelcius);
        frame.add(textFieldCelcius);
        frame.add(buttonKonversi);
        frame.add(labelFahrenheit);
        frame.add(labelHasil);

        // 6. Tampilkan frame
        frame.setVisible(true);
    }
}
