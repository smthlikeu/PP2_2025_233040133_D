package id.ac.unpas.pp2.modul10.model;

import id.ac.unpas.pp2.modul10.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mahasiswaModel {

    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            while (res.next()) {
                list.add(new Mahasiswa(
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertMahasiswa(String nama, String nim, String jurusan) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, jurusan);
        pst.execute();
    }

    public void updateMahasiswa(String nama, String nim, String jurusan) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, jurusan);
        pst.setString(3, nim);
        pst.executeUpdate();
    }

    public void deleteMahasiswa(String nim) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }
    
    public boolean cekNIM(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return res.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}