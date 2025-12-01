/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul06;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author zan
 */
public class Latihan1 {
    public static void main(String[] args) {
         // 1. Buat Frame
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // 2. Buat layar (display) di NORTH
        JTextField display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        // 3. Buat panel untuk tombol-tombol di CENTER
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5)); // 4 baris, 4 kolom

        // 4. Buat tombol-tombol kalkulator
        String[] tombol = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // 5. Tambahkan tombol-tombol ke panel
        for (String text : tombol) {
            JButton button = new JButton(text);
            panelTombol.add(button);
        }

        // 6. Tambahkan panel tombol ke frame
        frame.add(panelTombol, BorderLayout.CENTER);

        // 7. Tampilkan frame
        frame.setVisible(true);
    }
}
