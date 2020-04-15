package PBO5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LihatData extends JFrame {
    String[][] data = new String[480][3];
    String[] kolom = {"NIM","NAMA","ALAMAT"};
    JTable tabel;
    JButton btnBack,btnUpdate,btnDelete;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;

    public LihatData(){
        setTitle("Data Mahasiswa");
        KoneksiDB koneksi = new KoneksiDB();
        try{
            statement = koneksi.getKoneksi().createStatement();
            String sql = "select * from data_mhs";
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while (resultSet.next()){
                data[p][0] = resultSet.getString("NIM");
                data[p][1] = resultSet.getString("NAMA");
                data[p][2] = resultSet.getString("ALAMAT");
                p++;
            }
            statement.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Ditampilkan","Hasil",JOptionPane.ERROR_MESSAGE);
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Data Tidak ditemukan","Hasil",JOptionPane.ERROR_MESSAGE);
        }

        btnBack = new JButton("Input");
        tabel = new JTable(data,kolom);
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        scrollPane = new JScrollPane(tabel);
        setLayout(new FlowLayout());
        add(scrollPane);
        add(btnBack);
        add(btnUpdate);
        add(btnDelete);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new FormMhs();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EditData();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new HapusData();
            }
        });
    }
}
