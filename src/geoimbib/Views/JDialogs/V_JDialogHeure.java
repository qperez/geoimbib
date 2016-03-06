package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Controlers.C_ControlDialogTouch;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * Created by ravier on 31/01/2016.
 * Classe &eacute;tendue de JDialog, confirmation ou modification de l'heure
 */
public class V_JDialogHeure extends JDialog {


    private C_ControlDialogSerie c_controlDialogSerie;
    private C_ControlDialogTouch c_controlDialogTouch;
    private ActionListener aL;
    //private final int idCar;
    private JTextField jtextfieldHeure;
    private JButton jbuttonOk;

    /**
     * Constructeur de la classe V_JDialogHeure
     * @param v_mainWindow
     * @param s
     * @param b
     * @param aL
     */
    public V_JDialogHeure(V_MainWindow v_mainWindow, String s, boolean b, ActionListener aL){
        super(v_mainWindow, s, b);

        this.setSize(150, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.aL = aL;

        if (aL instanceof C_ControlDialogSerie){
            this.c_controlDialogSerie = (C_ControlDialogSerie)aL;
            this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);
        }else{
            this.c_controlDialogTouch = (C_ControlDialogTouch) aL;
            this.c_controlDialogTouch.setV_jDialogHour(this);
        }


        initComposants();

        this.setVisible(true);
    }

    private void initComposants() {

        JPanel jpcomposants = new JPanel(new BorderLayout());
        Border paddingjpcomposants = BorderFactory.createEmptyBorder(10,10,10,10);
        jpcomposants.setBorder(paddingjpcomposants);

        if (aL instanceof C_ControlDialogSerie)
            jtextfieldHeure = new JTextField( new SimpleDateFormat("HH:mm").format(c_controlDialogSerie.getNewCalendarSerie().getTime()));
        else
            jtextfieldHeure = new JTextField( new SimpleDateFormat("HH:mm").format(c_controlDialogTouch.getNewCalendarSerie().getTime()));

        jpcomposants.add(jtextfieldHeure, BorderLayout.CENTER);

        JPanel jPanelButtons = new JPanel();
        jbuttonOk = new JButton("Ok");
        jPanelButtons.add(jbuttonOk, BorderLayout.CENTER);

        Border paddingjpbutton = BorderFactory.createEmptyBorder(10,40,10,40);
        jPanelButtons.setBorder(paddingjpbutton);

        jbuttonOk.addActionListener(aL);

        this.getContentPane().add(jpcomposants, BorderLayout.CENTER);
        this.getContentPane().add(jPanelButtons, BorderLayout.SOUTH);
    }


    public JButton getButtonOk() {
        return jbuttonOk;
    }

    public String getJTextfield() {
        return jtextfieldHeure.getText();
    }
}
