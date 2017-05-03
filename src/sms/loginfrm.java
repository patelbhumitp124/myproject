

package sms;
import java.sql.*;
import javax.swing.JOptionPane;



public class loginfrm extends javax.swing.JFrame {

        Connection cn;
        Statement st;
        ResultSet rs;
        public loginfrm() {
        initComponents();
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

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t12 = new javax.swing.JTextField();
        t2 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel1.setText("LOGIN PAGE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 30, 280, 30);

        jLabel3.setText("Username:-");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 90, 120, 20);

        jLabel4.setText("Passward:-");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 120, 80, 14);

        t12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t12ActionPerformed(evt);
            }
        });
        getContentPane().add(t12);
        t12.setBounds(140, 90, 120, 20);

        t2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ActionPerformed(evt);
            }
        });
        getContentPane().add(t2);
        t2.setBounds(140, 120, 120, 20);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(60, 180, 100, 40);

        jButton2.setText("Cancle");
        getContentPane().add(jButton2);
        jButton2.setBounds(170, 180, 110, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t2ActionPerformed

    private void t12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String s = "select * from login1 where username ='"+t12.getText()+"' and password = '"+t2.getText()+"'";
       //JOptionPane.showMessageDialog(this, s);
        try
        {
            rs = st.executeQuery(s);
            if(rs.next())
            {
                sms_mainpage f3 = new sms_mainpage();
                f3.setVisible(true);
                f3.setSize(500,400);
                f3.setLocation(250,200);

            }



        }catch(Exception e1)
        {
            JOptionPane.showMessageDialog(this,e1.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginfrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField t12;
    private javax.swing.JPasswordField t2;
    // End of variables declaration//GEN-END:variables

}
