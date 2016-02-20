package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ravier on 31/01/2016.
 */
public class V_JDialogFrange extends JDialog {

    private final C_ControlDialogSerie c_controlDialogSerie;
    private final int idCar;
    private JLabel jLabel;
    private ArrayList<JTextField> arrayJTextfields;
    private JButton jbuttonok;
    private int nbFrange = 1;

    private JTextField jTextfieldMoyenne;

    public V_JDialogFrange(V_MainWindow v_mainWindow, String s, boolean b, C_ControlDialogSerie c_controlDialogSerie, int i){
        super(v_mainWindow, s, b);

        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);
        arrayJTextfields=new ArrayList<>();

        this.idCar = i;

        initComposants();

        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jpcomposants = new JPanel(new GridLayout(nbFrange+1,2));


        int paddingJPanel = 100;
        for (int i = 0; i<nbFrange; ++i) {
            paddingJPanel-=15;
            if (paddingJPanel<=0)
                break;
        }

        Border padding = BorderFactory.createEmptyBorder(paddingJPanel, 20, paddingJPanel,20);
        jpcomposants.setBorder(padding);

        for (int i =0; i<nbFrange; ++i){
            arrayJTextfields.add(new JTextField("0", 10));
            arrayJTextfields.get(i).getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    DecimalFormat df = new DecimalFormat("######00.000");
                    jTextfieldMoyenne.setText(df.format(getAverageFrangeH())+"");
                }

                @Override
                public void removeUpdate(DocumentEvent e) {

                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }

            });
            jLabel = new JLabel("Hauteur frange humide "+i+" :");
            jpcomposants.add(jLabel);
            jpcomposants.add(arrayJTextfields.get(i));
        }



        JButton jButtonPlus = new JButton("+");
        jButtonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nbFrange+=1;
                cleanWindow();
                System.out.println("clic : "+nbFrange);
            }
        });
        jpcomposants.add(jButtonPlus);

        JPanel jpanelButtons = new JPanel(new GridLayout(1,3));
        jbuttonok = new JButton("Suiv");
        jpanelButtons.add(new JLabel("Moyenne : "));
        DecimalFormat df = new DecimalFormat("######00.000");
        jTextfieldMoyenne = new JTextField(df.format(getAverageFrangeH())+"");
        jpanelButtons.add(jTextfieldMoyenne);
        jpanelButtons.add(jbuttonok);


        Border paddingjbutton = BorderFactory.createEmptyBorder(0,20,20,20);
        jpanelButtons.setBorder(paddingjbutton);

        jbuttonok.addActionListener(this.c_controlDialogSerie);

        //Scroller
        JScrollPane listScroller = new JScrollPane(jpcomposants);
        listScroller.setPreferredSize(new Dimension(400, 300));

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(listScroller, BorderLayout.CENTER);
    }

    public JButton getbuttonOk() {
        return jbuttonok;
    }

    public int getIdEchant() {
        return idCar;
    }

    public String getFrangeHu() {
        return jTextfieldMoyenne.getText();
    }

    private void cleanWindow() {
        getContentPane().removeAll();
        initComposants();
        validate();
    }

    public double getAverageFrangeH() {
        double average = 0;
        for (int i = 0; i<arrayJTextfields.size(); ++i){
            average+=Double.parseDouble(arrayJTextfields.get(i).getText());
        }
        average = average/arrayJTextfields.size();
        return average;
    }
}
