package geoimbib.Views.JPanels;

import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by ravier on 10/01/2016.
 */
public class V_JPanelMainLeft extends JPanel{

    private V_MainWindow v_mainWindow;

    //JList
    JList jList = null;

    public V_JPanelMainLeft(V_MainWindow v_mainWindow){
        this.v_mainWindow = v_mainWindow;
        initView();
    }

    public void initView(){
        //this.setBackground(Color.cyan);
        this.setLayout(null);
        this.setSize(new Dimension(v_mainWindow.getWidth()/2, v_mainWindow.getHeight()));
        this.setLocation(0,0);

        this.setBorder(new BevelBorder(BevelBorder.RAISED));

        //tableau de Strings pour exemple jlist
        String [] tabStringExemple = new String[50];
        for (int i = 0; i<tabStringExemple.length; ++i){
            tabStringExemple[i] = "String "+i;
        }

        jList = new JList(tabStringExemple); //data has type Object[]
        jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(jList);
        listScroller.setPreferredSize(new Dimension(150, 250));

        this.add(listScroller);

        listScroller.setBounds(getWidth()/2 - (int)listScroller.getPreferredSize().getWidth()/2,
                getHeight()/2 - (int)listScroller.getPreferredSize().getHeight()/2,
                150,
                250);
    }

}
