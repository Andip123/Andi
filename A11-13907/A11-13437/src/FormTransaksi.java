
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Locale;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author D2A_44
 */
public class FormTransaksi extends javax.swing.JFrame {

    public static Connection conn;
    public static Statement stm;
    public static ResultSet rs;
    DefaultTableModel tabmodel;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public FormTransaksi() {
        initComponents();
        conn = KoneksiDB.getConn();
        disabledText(false);
        notransaksi();
        listPelanggan();
        listBarang();
        setTable();
    }

    private void disabledText(boolean b) {
        tnama_pelanggan.setEnabled(b);
        talamat_pelanggan.setEnabled(b);
        tusia.setEnabled(b);
        tjenis_kelamin.setEnabled(b);
        txtNoTrk.setEnabled(b);
        txtTanggal.setEnabled(b);
        txtNamaBarang.setEnabled(b);
        txtSatuan.setEnabled(b);
        txtHarga.setEnabled(b);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Customize the date format as needed
        String formattedDate = dateFormat.format(currentDate);
        txtTanggal.setText(formattedDate);
        txtTotalBayar.setEnabled(b);
        txtTotal.setEnabled(b);
        txtKembali.setEnabled(b);
    }

    private void notransaksi() {
        try {
            String query = "SELECT * FROM penjualan ORDER BY no_faktur DESC";
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            if (rs.next()) {
                String NoTransaksi = rs.getString("no_faktur").substring(2);
                String TR = "" + (Integer.parseInt(NoTransaksi) + 1);
                String Nol = "";

                if (TR.length() == 1) {
                    Nol = "000";
                } else if (TR.length() == 2) {
                    Nol = "00";
                } else if (TR.length() == 3) {
                    Nol = "0";
                } else if (TR.length() == 4) {
                    Nol = "";
                }
                txtNoTrk.setText("TR" + Nol + TR);
            } else {
                txtNoTrk.setText("TR0001");
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listPelanggan() {
        String query = "select * from konsumen";
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String kodePelanggan = rs.getString("kode_kons");
                String namaPelanggan = rs.getString("nama_kons");
                String item = kodePelanggan + " - " + namaPelanggan;
                comboKodePelanggan.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listBarang() {
        String query = "select * from obat";
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String kodeBarang = rs.getString("kode_obat");
                String namaBarang = rs.getString("nama_obat");
                String item = kodeBarang + " - " + namaBarang;
                comboKodeBarang.addItem(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTable() {
        tabmodel = new DefaultTableModel();
        tabmodel.addColumn("No Transaksi");
        tabmodel.addColumn("Kode Barang");
        tabmodel.addColumn("Nama Barang");
        tabmodel.addColumn("Harga");
        tabmodel.addColumn("Jumlah");
        tabmodel.addColumn("Total");

        jTable2.setModel(tabmodel);
        String query = "SELECT * FROM temporary";
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String kode = rs.getString("kode_obat");
                String nama = rs.getString("nama_obat");
                String satuan = rs.getString("satuan_obat");
                String jumlah = rs.getString("jumlah");
                String harga = rs.getString("harga");
                String total = rs.getString("total_jual");

                tabmodel.addRow(new Object[]{kode, nama, satuan, jumlah, harga, total});
            }

            jTable2.setModel(tabmodel);
            hitungTotal();
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNoTrk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tnama_pelanggan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        talamat_pelanggan = new javax.swing.JTextField();
        comboKodePelanggan = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tusia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tjenis_kelamin = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSatuan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        comboKodeBarang = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTotalBayar = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtKembali = new javax.swing.JTextField();
        btnInput1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Faktur Penjualan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        jLabel2.setText("No Transaksi");

        jLabel3.setText("Kode Pelanggan");

        jLabel4.setText("Nama Pelanggan");

        jLabel5.setText("Alamat Pelanggan");

        comboKodePelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboKodePelangganActionPerformed(evt);
            }
        });

        jLabel15.setText("Tanggal");

        jLabel16.setText("Usia");

        jLabel17.setText("Jenis Kelamin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNoTrk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(talamat_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboKodePelanggan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tnama_pelanggan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tusia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tjenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(200, 200, 200)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoTrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(comboKodePelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(tnama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tusia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tjenis_kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(talamat_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));

        jLabel6.setText("Kode Obat");

        jLabel7.setText("Nama Obat");

        jLabel8.setText("Satuan");

        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
        });

        jLabel9.setText("Jumlah");

        jLabel10.setText("Harga");

        jLabel11.setText("Total");

        comboKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboKodeBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(94, 94, 94)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jLabel12.setText("Total Bayar");

        txtTotalBayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalBayar.setText("0");

        txtBayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBayar.setText("0");
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarKeyReleased(evt);
            }
        });

        jLabel13.setText("Bayar");

        jLabel14.setText("Kembali");

        txtKembali.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKembali.setText("0");

        btnInput1.setText("Simpan");
        btnInput1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInput1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBayar)
                    .addComponent(txtKembali)
                    .addComponent(txtTotalBayar))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(btnInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        btnTambah.setText("Tambah");
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 184, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboKodePelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboKodePelangganActionPerformed
        String selectedValue = comboKodePelanggan.getSelectedItem().toString();
        String kodePelanggan = selectedValue.substring(0, selectedValue.indexOf(" - "));

        try {
            String query = "select * from konsumen where kode_kons='" + kodePelanggan + "'";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                tnama_pelanggan.setText(rs.getString("nama_kons"));
                talamat_pelanggan.setText(rs.getString("alamat"));
                tusia.setText(rs.getString("usia"));
                tjenis_kelamin.setText(rs.getString("jns_kel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboKodePelangganActionPerformed

    private void comboKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboKodeBarangActionPerformed
        String selectedValue = comboKodeBarang.getSelectedItem().toString();
        String kodeBarang = selectedValue.substring(0, selectedValue.indexOf(" - "));

        try {
            String query = "select * from obat where kode_obat='" + kodeBarang + "'";
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                txtNamaBarang.setText(rs.getString("nama_obat"));
                txtSatuan.setText(rs.getString("satuan"));
                txtHarga.setText(rs.getString("harga"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboKodeBarangActionPerformed

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        int harga = Integer.parseInt(txtHarga.getText());
        if (!txtJumlah.getText().isEmpty()) {
            int jumlah = Integer.parseInt(txtJumlah.getText());
            int total = harga * jumlah;

            txtTotal.setText(String.valueOf(total));
        }
    }//GEN-LAST:event_txtJumlahKeyReleased

    int grandTotal = 0;
    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        String NoTransaksi = txtNoTrk.getText();
        String tanggal = txtTanggal.getText();
        String selectedValue1 = comboKodeBarang.getSelectedItem().toString();
        String kodeobat = selectedValue1.substring(0, selectedValue1.indexOf(" - "));
        String namaObat = txtNamaBarang.getText();
        String satuanObat = txtSatuan.getText();
        String jumlahObat = txtJumlah.getText();
        String hargaObat = txtHarga.getText();
        String totalJual = txtTotal.getText();
        String selectedValue2 = comboKodePelanggan.getSelectedItem().toString();
        String kodePelanggan = selectedValue2.substring(0, selectedValue2.indexOf(" - "));
        String namaPelanggan = tnama_pelanggan.getText();
        String usiaPelanggan = tusia.getText();
        String alamatPelanggan = talamat_pelanggan.getText();
        String querySave = "insert into temporary values ('" + tanggal + "', '" + NoTransaksi + "', '" + kodeobat + "', '" + namaObat + "', '" + satuanObat + "', '" + jumlahObat + "', '" + hargaObat + "', '" + totalJual + "', '" + kodePelanggan + "', '" + namaPelanggan + "', '" + usiaPelanggan + "', '" + alamatPelanggan + "')";
        System.out.println(querySave);
        try {
            stm = conn.createStatement();
            stm.executeUpdate(querySave);
            setTable();
            hitungTotal();
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

            int totalToDelete = Integer.parseInt(model.getValueAt(selectedRow, 4).toString());

            model.removeRow(selectedRow);

//            int grandTotal = Integer.parseInt(tgrand_total.getText());
            grandTotal -= totalToDelete;
            txtTotalBayar.setText(formatRupiah.format(grandTotal));
            // Reset the value of "no" for subsequent rows
            for (int i = selectedRow; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Pilih data!");
        }

    }//GEN-LAST:event_btnHapusMouseClicked

    private void txtBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyReleased
        if (grandTotal != 0 && !txtBayar.getText().isEmpty()) {
            int bayar = Integer.parseInt(txtBayar.getText());
            int kembali = bayar - grandTotal;
            txtKembali.setText(formatRupiah.format(kembali));
        }
    }//GEN-LAST:event_txtBayarKeyReleased

    private void btnInput1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInput1MouseClicked
        if (jTable2.getRowCount() != 0) {
            String NoTransaksi = txtNoTrk.getText();
            String tanggal = txtTanggal.getText();
            String selectedValue = comboKodePelanggan.getSelectedItem().toString();
            String kodePelanggan = selectedValue.substring(0, selectedValue.indexOf(" - "));
            int bayar = Integer.parseInt(txtBayar.getText());
            int kembali = bayar - grandTotal;
            int total = grandTotal;
            if (kembali >= 0) {
                try {
                    String querySave = "insert into penjualan values ('" + NoTransaksi + "', '" + tanggal + "', '" + kodePelanggan + "', '" + total + "')";
                    stm = conn.createStatement();
                    stm.executeUpdate(querySave);
                } catch (SQLException ex) {
                    Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);

                }

                try {
                    int baris = jTable2.getRowCount();
                    for (int i = 0; i < baris; i++) {
                        String sql = "INSERT INTO penjualanrinci(no_transaksi, kode_barang, nama_barang, harga, jumlah, total) VALUES('"
                                + jTable2.getValueAt(i, 0) + "','" + jTable2.getValueAt(i, 1) + "','" + jTable2.getValueAt(i, 2)
                                + "','" + jTable2.getValueAt(i, 3) + "','" + jTable2.getValueAt(i, 4) + "','" + jTable2.getValueAt(i, 5)
                                + "')";
                        stm = conn.createStatement();
                        stm.executeUpdate(sql);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(rootPane, "Berhasil Simpan Transaksi");
                reset();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Uang bayar kurang!");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi Kosong!");
        }
    }//GEN-LAST:event_btnInput1MouseClicked

    private void reset() {
        notransaksi();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnInput1;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> comboKodeBarang;
    private javax.swing.JComboBox<String> comboKodePelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField talamat_pelanggan;
    private javax.swing.JTextField tjenis_kelamin;
    private javax.swing.JTextField tnama_pelanggan;
    private javax.swing.JTextField tusia;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNoTrk;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalBayar;
    // End of variables declaration//GEN-END:variables

    private void hitungTotal() {
        String query = "SELECT sum(total_jual) as totalnya from temporary";
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                txtTotalBayar.setText(rs.getString("totalnya"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
