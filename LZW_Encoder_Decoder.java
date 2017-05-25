/*
	
	Name				:	Bhumit Patel
	LID					:	L20382154
	Course				:	CPSC 5330_01_1 Adv. Multimedia Processing
	Instructor			: 	Prof. JIANGJIANG (JANE) LIU
	Assignment			:	Implement encoder and decoder for LZW compression algorithms using JAVA or C++.
	File Name			: 	LZW_Encoder_Decoder.java
	
*/
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import L_Z_W.LZW_Implementation;
import java.awt.*;
import java.awt.event.*;

public class LZW_Encoder_Decoder extends JFrame implements ActionListener {

    private JLabel lblCompression;
    private JLabel lblDecompress;
    private JPanel pnlMain;
    private JPanel pnlSub;
    private JFrame frmMain;
    private JLabel lblHeader;
    private JButton btnClose;
    private JButton btnCompress;
    private JButton btnDecompress;
    private JRadioButton radWord;
    private JRadioButton radImg;
    private JRadioButton radAudio;
    private JRadioButton radDLL;
    private JRadioButton radExe;

    public LZW_Encoder_Decoder() {
        prepareGUI();
    }

    public void prepareGUI() {
        frmMain = new JFrame("LZW_Encoder_Decoder");
        frmMain.setSize(800, 600);
        frmMain.getContentPane().setLayout(new GridLayout(2, 1));
        frmMain.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        pnlMain = new JPanel();
        pnlMain.setLayout(new GridLayout(6, 1));
        pnlMain.setSize(400, 250);
        frmMain.getContentPane().add(pnlMain);

        pnlSub = new JPanel();
        pnlSub.setLayout(new FlowLayout());
        pnlSub.setSize(400, 150);

        frmMain.getContentPane().add(pnlSub);

        lblHeader = new JLabel("Select File Type for compression or decompression: ", JLabel.LEFT);
        pnlMain.add(lblHeader);

        radImg = new JRadioButton("IMAGE File",true);
        radAudio = new JRadioButton("AUDIO File");
        radWord = new JRadioButton("WORD File");
        radDLL = new JRadioButton("DLL File");
        radExe = new JRadioButton("EXECUTABLE File");

        ButtonGroup group = new ButtonGroup();
        group.add(radImg);
        group.add(radAudio);
        group.add(radWord);
        group.add(radDLL);
        group.add(radExe);
        
        
        
        pnlMain.add(radImg);
        pnlMain.add(radAudio);
        pnlMain.add(radWord);
        pnlMain.add(radDLL);
        pnlMain.add(radExe);

        btnClose = new JButton("Close");
        btnCompress = new JButton("Compress File");

        btnClose.setSize(50, 60);
        btnCompress.setSize(50, 60);
        btnClose.addActionListener(this);
        btnCompress.addActionListener(this);

        pnlSub.add(btnCompress);
        btnDecompress = new JButton("Decompress File");
        btnDecompress.setSize(50, 60);
        btnDecompress.addActionListener(this);
        pnlSub.add(btnDecompress);
        pnlSub.add(btnClose);

        frmMain.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        FileInputStream fsInp;
        FileOutputStream fsOut;
        LZW_Implementation lzw = new LZW_Implementation();

        if (ae.getSource() == btnClose) {
            System.exit(0);
        } else if (ae.getSource() == btnCompress) {
            try {
                if (radWord.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample word file.doc"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample word file.doc.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Word file is compressed successfully.");
                } else if (radImg.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample image file.jpg"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample image file.jpg.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Image File is compressed successfully.");
                } else if (radAudio.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample music file.mp3"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample music file.mp3.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Audio File is compressed successfully.");
                } else if (radExe.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample exe file.exe"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample exe file.exe.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Executable File is compressed successfully.");
                } else if (radDLL.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample dll file.dll"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample dll file.dll.lzw"));
                    lzw.compress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "DLL file is compressed successfully.");
                }
            } catch (FileNotFoundException fe) {
                JOptionPane.showMessageDialog(null, "File is not in folder.....");
            } catch (IOException ie) {
            }
        } else if (ae.getSource() == btnDecompress) {
            try {
                if (radWord.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample word file.doc.lzw"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample word file after decoded.doc"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Word file is decompressed successfully");
                } else if (radImg.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample image file.jpg.lzw"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample image file after decoded.jpg"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Image file is decompressed successfully");
                } else if (radAudio.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample music file.mp3.lzw"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample music file after decoded.mp3"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Audio file is decompressed successfully");
                } else if (radExe.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample exe file.exe.lzw"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample exe file after decoded.exe"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "Executable file is decompressed successfully");
                } else if (radDLL.isSelected()) {
                    fsInp = new FileInputStream(new File("./Inputs/Sample dll file.dll.lzw"));
                    fsOut = new FileOutputStream(new File("./Inputs/Sample dll file after decoded.dll"));
                    lzw.decompress(fsInp, fsOut);
                    JOptionPane.showMessageDialog(null, "DLL file is decompressed successfully");
                }
            } catch (FileNotFoundException fe) {
                JOptionPane.showMessageDialog(null, "Compression is unsuccessful. Please perform compression again.");
            } catch (IOException ie) {
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        LZW_Encoder_Decoder lzw = new LZW_Encoder_Decoder();
        
    }
}
