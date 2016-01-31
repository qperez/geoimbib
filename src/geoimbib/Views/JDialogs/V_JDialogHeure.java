package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ravier on 31/01/2016.
 */
public class V_JDialogHeure extends JDialog {


    private final C_ControlDialogSerie c_controlDialogSerie;
    private final int idCar;
    private JTextField jtextfieldHeure;
    private JButton jbuttonOk;

    public V_JDialogHeure(V_MainWindow v_mainWindow, String s, boolean b, C_ControlDialogSerie c_controlDialogSerie, int i){
        super(v_mainWindow, s, b);

        this.setSize(150, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        this.idCar = i;

        initComposants();

        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jpcomposants = new JPanel(new BorderLayout());
        Border paddingjpcomposants = BorderFactory.createEmptyBorder(10,10,10,10);
        jpcomposants.setBorder(paddingjpcomposants);

        jtextfieldHeure = new JTextField(c_controlDialogSerie.getHeureM_mesure(idCar));

        jpcomposants.add(jtextfieldHeure, BorderLayout.CENTER);

        JPanel jPanelButtons = new JPanel();
        jbuttonOk = new JButton("Ok");
        jPanelButtons.add(jbuttonOk, BorderLayout.CENTER);

        Border paddingjpbutton = BorderFactory.createEmptyBorder(10,40,10,40);
        jPanelButtons.setBorder(paddingjpbutton);

        jbuttonOk.addActionListener(this.c_controlDialogSerie);

        this.getContentPane().add(jpcomposants, BorderLayout.CENTER);
        this.getContentPane().add(jPanelButtons, BorderLayout.SOUTH);
    }

    public int getIdEchant() {
        return idCar;
    }

    public JButton getButtonOk() {
        return jbuttonOk;
    }

    public String getJTextfield() {
        return jtextfieldHeure.getText();
    }
}
