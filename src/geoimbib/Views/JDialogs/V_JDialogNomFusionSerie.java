package geoimbib.Views.JDialogs;

import geoimbib.Models.M_armoFile;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

/**
 * Created by ravier on 28/02/2016.
 */
public class V_JDialogNomFusionSerie extends JDialog {

    private JTextField jTextField = null;
    private JButton jbuttonOk = null;
    private java.util.List<String> listNomSerie;

    private V_MainWindow v_mainWindow = null;

    public V_JDialogNomFusionSerie(V_MainWindow v_mainWindow, String s, boolean b, java.util.List<String> listNomSerie){
        super(v_mainWindow, s, b);
        this.listNomSerie = listNomSerie;
        this.v_mainWindow = v_mainWindow;

        this.setSize(150, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        initComposants();

        this.setVisible(true);
    }


    private void initComposants() {
        JPanel jpcomposants = new JPanel(new BorderLayout());
        Border paddingjpcomposants = BorderFactory.createEmptyBorder(10,10,10,10);
        jpcomposants.setBorder(paddingjpcomposants);

        jTextField = new JTextField("", 50);

        jpcomposants.add(jTextField, BorderLayout.CENTER);

        JPanel jPanelButtons = new JPanel();
        jbuttonOk = new JButton("Ok");
        jPanelButtons.add(jbuttonOk, BorderLayout.CENTER);

        Border paddingjpbutton = BorderFactory.createEmptyBorder(10,40,10,40);
        jPanelButtons.setBorder(paddingjpbutton);

        jbuttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    M_armoFile.getINSTANCE().fusionSet(listNomSerie, v_mainWindow.getJPanelMainLeft().getJtextfieldFolder().getText(), "Serie_"+jTextField.getText());
                    v_mainWindow.getJPanelMainLeft().updateJList();
                    dispose();
                }catch(IOException e1){
                    v_mainWindow.getJPanelMainRight().displayErrorInput();
                    System.out.println(e1);
                }
            }
        });

        this.getContentPane().add(jpcomposants, BorderLayout.CENTER);
        this.getContentPane().add(jPanelButtons, BorderLayout.SOUTH);
    }

}
