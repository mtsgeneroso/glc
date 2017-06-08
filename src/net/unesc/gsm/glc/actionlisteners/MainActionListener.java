package net.unesc.gsm.glc.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import net.unesc.gsm.glc.controllers.Producao;
import net.unesc.gsm.glc.frames.MainFrame;

public class MainActionListener implements ActionListener {

    private ArrayList<Producao> gramatica;
    private MainFrame MainFrame;

    public MainActionListener(ArrayList<Producao> gramatica, MainFrame tela) {

        this.gramatica = gramatica;
        this.MainFrame = tela;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sair")) {
            this.MainFrame.dispose();
        }
    }
}
