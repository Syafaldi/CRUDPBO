package PBO5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class FormMhs extends JFrame {
    JLabel lnim,lnama,lalamat;
    JTextField txnim,txnama,txalamat;
    JButton cetak,read;
    Statement statement;
    public FormMhs (){

        setTitle("From Pengisian Mahasiswa");

        lnim = new JLabel("NIM");
        lnama = new JLabel("Nama");
        lalamat = new JLabel("Alamat");

        txnim = new JTextField("");
        txnama = new JTextField("");
        txalamat = new JTextField("");
        cetak = new JButton("Input");
        read = new JButton("Lihat Data");


        setLayout(null);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(txnim);
        add(txnama);
        add(txalamat);
        add(cetak);
        add(read);

        lnim.setBounds(75, 50, 30, 20);
        lnama.setBounds(75, 75, 50, 20);
        lalamat.setBounds(75, 125, 50, 20);
        cetak.setBounds(75, 230, 75, 20);
        read.setBounds(155, 230, 100, 20);
        txnim.setBounds(150, 50, 150, 20);
        txnama.setBounds(150, 75, 150, 20);
        txalamat.setBounds(150, 125, 150, 100);
        setSize(500,400); //untuk luas jendela
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int a1 =  Integer.parseInt(txnim.getText());
                    String a2 = txnama.getText();
                    String a3 = txalamat.getText();

                    KoneksiDB koneksi = new KoneksiDB();
                    try {
                        statement = koneksi.getKoneksi().createStatement();
                        statement.executeUpdate("INSERT INTO data_mhs VALUE ('" + a1 + "','" + a2 + "','" + a3 + "')" );
                        JOptionPane.showMessageDialog(rootPane,"Data tersimpan");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FormMhs.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FormMhs.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    System.out.println("NIM = "+a1);
                    System.out.println("Nama = "+a2);
                    System.out.println("Alamat = "+a3);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane,"TIPE DATA SALAH");
                } catch (Error ext){
                    JOptionPane.showMessageDialog(rootPane,"SALAH");
                }
            }
        });

        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LihatData();
            }
        });
    }
}
