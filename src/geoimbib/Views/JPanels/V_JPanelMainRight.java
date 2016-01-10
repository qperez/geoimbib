package geoimbib.Views.JPanels;

import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ravier on 10/01/2016.
 */
public class V_JPanelMainRight extends JPanel {

    private V_MainWindow v_mainWindow;

    //JPanel des boutons
    JPanel jpanelButtons = null;

    //Boutons
    JButton jbutton1 = null;
    JButton jbutton2 = null;
    JButton jbutton3 = null;
    JButton jbutton4 = null;

    public V_JPanelMainRight(V_MainWindow v_mainWindow){
        this.v_mainWindow = v_mainWindow;
        initView();
    }

    public void initView(){
        //this.setBackground(Color.ORANGE);
        this.setLayout(null);
        this.setSize(new Dimension(v_mainWindow.getWidth()/2, v_mainWindow.getHeight()));
        this.setLocation(v_mainWindow.getJPanelMainLeft().getWidth(), 0);

        this.setBorder(new BevelBorder(BevelBorder.RAISED));

        jpanelButtons = new JPanel(new GridLayout(4,1));
        this.add(jpanelButtons);

        jbutton1 = new JButton("JButton 1");
        jpanelButtons.add(jbutton1);
        jbutton2 = new JButton("JButton 2");
        jpanelButtons.add(jbutton2);
        jbutton3 = new JButton("JButton 3");
        jpanelButtons.add(jbutton3);
        jbutton4 = new JButton("JButton 4");
        jpanelButtons.add(jbutton4);


        jpanelButtons.setBounds(getWidth()/2 - (int)jpanelButtons.getPreferredSize().getWidth(),
                getHeight()/2 - (int)jpanelButtons.getPreferredSize().getHeight(),
                200,
                200);
    }

}
