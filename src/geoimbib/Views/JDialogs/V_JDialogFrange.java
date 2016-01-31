package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ravier on 31/01/2016.
 */
public class V_JDialogFrange extends JDialog {

    private final C_ControlDialogSerie c_controlDialogSerie;
    private final int idCar;
    private JLabel jLabel;
    private JTextField jtextfield;
    private JButton jbuttonok;

    public V_JDialogFrange(V_MainWindow v_mainWindow, String s, boolean b, C_ControlDialogSerie c_controlDialogSerie, int i){
        super(v_mainWindow, s, b);

        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        this.idCar = i;

        initComposants();

        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jpcomposants = new JPanel(new GridLayout(1,2));
        Border paddingjpcomposants = BorderFactory.createEmptyBorder(50,45,50,45);
        jpcomposants.setBorder(paddingjpcomposants);

        jLabel = new JLabel("Hauteur frange humide :");
        jtextfield = new JTextField("0");
        jpcomposants.add(jLabel);
        jpcomposants.add(jtextfield);

        JPanel jpanelButtons = new JPanel(new BorderLayout());
        jbuttonok = new JButton("Ok");
        jpanelButtons.add(jbuttonok, BorderLayout.CENTER);
        Border paddingjbutton = BorderFactory.createEmptyBorder(0,175,20,175);
        jpanelButtons.setBorder(paddingjbutton);

        jbuttonok.addActionListener(this.c_controlDialogSerie);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jpcomposants, BorderLayout.CENTER);
    }

    public JButton getbuttonOk() {
        return jbuttonok;
    }

    public int getIdEchant() {
        return idCar;
    }

    public String getFrangeHu() {
        return jtextfield.getText();
    }
}
