package id.ac.unpas.pp2.modul10;

import id.ac.unpas.pp2.modul10.controller.mahasiswaController;
import id.ac.unpas.pp2.modul10.model.mahasiswaModel;
import id.ac.unpas.pp2.modul10.view.mahasiswaView;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mahasiswaView view = new mahasiswaView();
            mahasiswaModel model = new mahasiswaModel();
            
            // Controller menghubungkan View dan Model
            new mahasiswaController(view, model);
            
            view.setVisible(true);
        });
    }
}