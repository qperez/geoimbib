package geoimbib.Views.JDialogs;

import geoimbib.IO.IO_SyncBulkTransfer;

import javax.swing.*;
import java.text.DecimalFormat;

/**
 * Created by quentin on 07/03/16.
 */
public class V_jlabelDoubleValeurBalanceThread extends Thread{
    JLabel jlabelDoubleValeurBalance;
    private boolean start;

    V_jlabelDoubleValeurBalanceThread(JLabel jlabelDoubleValeurBalance){
        this.jlabelDoubleValeurBalance=jlabelDoubleValeurBalance;

    }

    public void run(){
        start = true;
        while(start){
            try{
                //Création de l'objet servant au transfert
                //création du thread servatn au transfert
                //Démarrage du Thread
                //Attente de la fin thread
                //Récupération de la valeur
                IO_SyncBulkTransfer bulkTransfer = new IO_SyncBulkTransfer();
                Thread threadTransfert = new Thread(bulkTransfer);
                threadTransfert.start();
                try {
                    threadTransfert.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double valMasse = bulkTransfer.getMasse();
                jlabelDoubleValeurBalance.setText(String.valueOf(valMasse));
                this.sleep(100);
            }catch(InterruptedException e){

            }
        }
    }

    public void stopThread(){
        start=false;
    }

}
