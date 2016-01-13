package geoimbib.Views.JPanels;

import geoimbib.Controlers.C_ControlButtonMainPanelRight;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by ravier on 10/01/2016.
 */
public class V_JPanelMainRight extends JPanel {

    private V_MainWindow v_mainWindow;

    //JPanel des boutons
    private JPanel jpanelButtons = null;
    private JPanel jpanelLeave = null;

    //Boutons
    JButton jbutton1 = null;
    JButton jbutton2 = null;
    JButton jbutton3 = null;
    JButton jbutton4 = null;
    JButton jbutton5 = null;

    JButton jbuttonLeave = null;


    public V_JPanelMainRight(V_MainWindow v_mainWindow){
        this.v_mainWindow = v_mainWindow;
        initView();
        initControlers();
    }

    private void initControlers() {
        jbuttonLeave.addActionListener(new C_ControlButtonMainPanelRight(this));
    }

    public void initView(){
        //this.setBackground(Color.ORANGE);
        this.setLayout(null);
        this.setSize(new Dimension(v_mainWindow.getWidth()/2, v_mainWindow.getHeight()));
        this.setLocation(v_mainWindow.getJPanelMainLeft().getWidth(), 0);

        this.setBorder(new BevelBorder(BevelBorder.RAISED));

        jpanelButtons = new JPanel(new GridLayout(5,1));
        this.add(jpanelButtons);

        jbutton1 = new JButton("Nouvelle série");
        jpanelButtons.add(jbutton1);
        jbutton2 = new JButton("Fusion de séries");
        jpanelButtons.add(jbutton2);
        jbutton3 = new JButton("Retirer échantillon");
        jpanelButtons.add(jbutton3);
        jbutton4 = new JButton("Graphique");
        jpanelButtons.add(jbutton4);
        jbutton5 = new JButton("Supprimer");
        jpanelButtons.add(jbutton5);


        jpanelButtons.setBounds(getWidth()/2 - (int)jpanelButtons.getPreferredSize().getWidth()/2,
                getHeight()/2 - (int)jpanelButtons.getPreferredSize().getHeight(),
                200,
                250);

        jpanelLeave = new JPanel(new GridLayout(1,1));
        jbuttonLeave = new JButton("Quitter");
        jpanelLeave.add(jbuttonLeave);


        jpanelLeave.setBounds(getWidth()/2 - (int)jpanelLeave.getPreferredSize().getWidth(),
                getHeight() - (int)jpanelLeave.getPreferredSize().getHeight()*3,
                200,
                50);
        this.add(jpanelLeave);

    }

    public void leave() {
        v_mainWindow.dispose();
    }


    /*
    * Getters and Setters
    * */
    public JButton getButtonLeave() {
        return jbuttonLeave;
    }
}
