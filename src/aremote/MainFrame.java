package aremote;

import com.android.chimpchat.core.PhysicalButton;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
6 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
6 *
 * @author Administrator
 */
public class MainFrame extends javax.swing.JFrame implements ActionListener, MouseListener
{

    private IDevice _device;
    
    private Timer _timer;

    private JButton _homeButton;

    private JButton _backButton;


    private JButton _pwdButton;
    

    private JButton _typeButton;
  
    private JTextArea _txtArea;
    
    /* Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        _device = new AdbDevice();
        _timer = new Timer(1000, this);
        _timer.start();
        
       jLabel1.addMouseListener(this);
       
       _homeButton = new JButton();
       _homeButton.setText("Home");
       _homeButton.setLocation(new Point(550, 70));
       _homeButton.setSize(new Dimension(80, 40));
       _homeButton.addActionListener(this);
       this.add(_homeButton);


       _backButton = new JButton();
       _backButton.setText("Back");
       _backButton.setLocation(new Point(550, 120));
       _backButton.setSize(new Dimension(80, 40));
       _backButton.addActionListener(this);
       this.add(_backButton);


      _pwdButton = new JButton();
 _pwdButton.setText("Password");
       _pwdButton.setLocation(new Point(550, 170 ));
       _pwdButton.setSize(new Dimension(80, 40));
      _pwdButton.addActionListener(this);
       this.add(_pwdButton);
       
       
      _typeButton = new JButton();
        _typeButton.setText("Type");
       _typeButton.setLocation(new Point(550, 220 ));
       _typeButton.setSize(new Dimension(80, 40));
      _typeButton.addActionListener(this);
       this.add(_typeButton);
       
       _txtArea = new JTextArea();
       _txtArea.setLocation(new Point(550, 270 ));
       _txtArea.setSize(new Dimension(100, 220));
       this.add(_txtArea);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ARemote");
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(800, 800));

        jLabel1.setText("jLabel1");
        jLabel1.setName("pictureLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

   public void actionPerformed(ActionEvent e) 
   {
       var source = e.getSource();
       
       if (source == _timer)
       {
        BufferedImage img = _device.getScreenShot();

        if (img == null)
        {
             return;
        }

        int lW = jLabel1.getWidth();
        int lH = jLabel1.getHeight();

        double scaleX = ((double)lW)/(((double)img.getWidth()));
        double scaleY = ((double)lH)/(((double)img.getHeight()));

        Image newImg = img.getScaledInstance(lW, lH, 0);
        ImageIcon icon = new ImageIcon(newImg);

       jLabel1.setIcon(icon);
       }
       else if (source == _homeButton)
       {
           _device.press(PhysicalButton.HOME);
           
       }
              else if (source == _pwdButton)
       {
           try {
               _device.shell("input keyevent 5");
               Thread.sleep(1500);
               
               _device.touch(853, 1481);

               Thread.sleep(1500);               
/*              
               Thread.sleep(500);
               _device.touch(168, 1959);
               Thread.sleep(500);
                _device.touch(550,1557);
               Thread.sleep(500);
               _device.touch(520,1322);
               Thread.sleep(500);
               _device.touch(550, 1709);
               Thread.sleep(500);
               _device.touch(872, 1709);
               Thread.sleep(500);
               _device.touch(853, 1718);
*/              
    _device.type("2128506#");
           } catch (InterruptedException ex) {
               Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
else if (source == _backButton)
       {
           _device.press(PhysicalButton.BACK);
          
       }
        else if (source == _typeButton)
       {
         String s = _txtArea.getText();
       
//           _device.type(s);
           
       }
      
   }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
         if (e.getClickCount() == 2)
         {
            Point p = ScalePoint(e.getPoint());
            _device.hold(p.x, p.y, 5000);
         }
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
           Point p = ScalePoint(e.getPoint());
            _device.touch(p.x, p.y);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    private Point ScalePoint(Point point)
    {
    
      int lW = jLabel1.getWidth();
       int lH = jLabel1.getHeight();
       
       double scaleX = ((double)lW)/(((double)_device.getWidth()));
       double scaleY = ((double)lH)/(((double)_device.getHeight()));
       
       int scaledX = (int)(((double)point.x)/scaleX);
       int scaledY = (int)(((double)point.y)/scaleY);
       
       return new Point(scaledX, scaledY);
}
}

