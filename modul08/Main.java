/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul08;

/**
 *
 * @author zan
 */
import id.ac.unpas.pp2.modul08.controller.PersegiPanjangController;
import id.ac.unpas.pp2.modul08.view.PersegiPanjangView;
import id.ac.unpas.pp2.modul08.model.PersegiPanjangModel;

public class Main {
    public static void main(String[] args) {
        //1. Instansiasi Model
        PersegiPanjangModel model = new PersegiPanjangModel();
        
        //2. Instansiasi View
        PersegiPanjangView view = new PersegiPanjangView();
        
        //3 Instansiasi Controller (Hubungkan Model & View)
        PersegiPanjangController controller = new PersegiPanjangController(model, view);
        
        //4. Tampilkan View
        view.setVisible(true);
    }
}
