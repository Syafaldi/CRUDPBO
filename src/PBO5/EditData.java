package PBO5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditData extends JFrame {
    JLabel lnim,lNama,lAlamat,lJudul;
    JTextField tfnim,tfNama,tfAlamat;
    JButton btnUpdate,btnKembali;
    Statement statement;
    ResultSet resultSet;
    public EditData(){
        lJudul = new JLabel("Masukkan NIM yang akan di update");
        lnim = new JLabel("NIM");
        lNama = new JLabel("Nama");
        lAlamat = new JLabel("Alamat");

        tfnim = new JTextField();
        tfNama = new JTextField();
        tfAlamat = new JTextField();

        btnUpdate = new JButton("Update");
        btnKembali = new JButton("Kembali");

        setTitle("Update Data Pengawas");
        setSize(300,370);
        lJudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(lJudul);
        add(lnim);
        add(lNama);
        add(lAlamat);
        add(tfnim);
        add(tfNama);
        add(tfAlamat);
        add(btnUpdate);
        add(btnKembali);

        lJudul.setBounds(0,10,300,25);
        lnim.setBounds(50,50,100,25);
        tfnim.setBounds(100,50,100,25);
        lNama.setBounds(50,90,100,25);
        tfNama.setBounds(100,90,100,25);
        lAlamat.setBounds(50,210,100,25);
        tfAlamat.setBounds(100,210,100,25);
        btnKembali.setBounds(30,290,100,25);
        btnUpdate.setBounds(140,290,100,25);

        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LihatData();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdateActionListener();
            }
        });
    }

    private void btnUpdateActionListener(){
        KoneksiDB koneksi = new KoneksiDB();
        try{
            statement = koneksi.getKoneksi().createStatement();
            statement.executeUpdate("UPDATE data_mhs SET NAMA='"+ tfNama.getText() + "'," + "ALAMAT ='"+tfAlamat.getText()+ "' WHERE NIM ='" + tfnim.getText() + "'");
            JOptionPane.showMessageDialog(null,"Data berhasil di update","Hasil",JOptionPane.INFORMATION_MESSAGE);
            statement.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Data gagal di update","Hasil",JOptionPane.ERROR_MESSAGE);
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Driver tidak ditemukan","Hasil",JOptionPane.ERROR_MESSAGE);
        }
    }
}
