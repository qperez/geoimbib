package geoimbib.Models.ModelsJPanelMainLeft.Threads;

import geoimbib.Views.JPanels.V_JPanelMainLeft;

/**
 * Classe &eacute;tendue de thread pour l'affichage du message d'erreur lorsqu'il n'y a pas de dossier s&eacute;l&eacute;ctionn&eacute;
 * Permet de ne pas bloquer l'application.
 */
public class M_ThreadWarningNoPath extends Thread {
    V_JPanelMainLeft v_jPanelMainLeft = null;

    public M_ThreadWarningNoPath(V_JPanelMainLeft v_jPanelMainLeft) {
        super();
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    @Override
    public void run() {
        super.run();
    }
}
