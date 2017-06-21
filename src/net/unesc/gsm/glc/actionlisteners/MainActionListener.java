package net.unesc.gsm.glc.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import net.unesc.gsm.glc.controllers.Producao;
import net.unesc.gsm.glc.frames.MainFrame;

public class MainActionListener implements ActionListener {

    private ArrayList<Producao> gramatica;
    private MainFrame mainFrame;

    public MainActionListener(ArrayList<Producao> gramatica, MainFrame tela) {

        this.gramatica = gramatica;
        this.mainFrame = tela;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("+  Produção")) {
            
        }
    }
}
