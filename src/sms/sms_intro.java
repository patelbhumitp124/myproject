

package sms;

public class sms_intro extends javax.swing.JFrame implements Runnable{

    int n=0;
    Thread t1;
    public sms_intro() {
        initComponents();
        setSize(500,500);
        setLocation(250,200);

        t1 = new Thread(this);
        t1.start();

    }
    public void run()
        {
            while(true)
            {
            try{

                jProgressBar1.setValue(n);
                n++;
                Thread.sleep(10);
                if(n>=100)
                {
                    loginfrm f2 = new loginfrm();
                    f2.setVisible(true);
                    f2.setSize(400,450);
                    f2.setLocation(250,200);
                    this.setVisible(false);
                    t1.stop();
                    
                }
                
            }catch(Exception e1)
            {
              
            }
            }
            }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("SMS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 50, 250, 30);

        jProgressBar1.setMinimum(1);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(270, 220, 150, 50);

        jLabel2.setText("Develop by -----");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 180, 200, 20);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sms/a1.JPG"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 130, 140, 130);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sms_intro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

}
