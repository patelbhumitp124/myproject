
package sms;
import java.sql.*;
import javax.swing.JOptionPane;

public class frm_registration extends javax.swing.JInternalFrame {

    
         Connection cn;
        Statement st;
        ResultSet rs;
        PreparedStatement pst;
    public frm_registration() {
        initComponents();
        setSize(500,500);
        setLocation(200,250);

        setVisible(true);
        try
        {

         Class.forName("com.mysql.jdbc.Driver");
         cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
         st = cn.createStatement();

        }catch(Exception e1)
        {
            JOptionPane.showMessageDialog(this,e1.getMessage());
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        t2 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        getContentPane().setLayout(null);

        jLabel1.setText("New Registration ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 20, 160, 40);

        jLabel2.setText("Username:-");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 80, 70, 30);
        getContentPane().add(t1);
        t1.setBounds(120, 90, 130, 20);

        jLabel3.setText("Password:-");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 130, 60, 14);
        getContentPane().add(t2);
        t2.setBounds(120, 130, 130, 20);

        jButton1.setText("Create ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(40, 220, 180, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
            pst = cn.prepareStatement("insert into login1(username,password) values (?,?)");
            pst.setObject(1, t1.getText());
            pst.setObject(2, t2.getText());
          
            pst.executeUpdate();

        }catch(Exception e1)
        {
            JOptionPane.showMessageDialog(this,e1.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField t1;
    private javax.swing.JPasswordField t2;
    // End of variables declaration//GEN-END:variables

}
