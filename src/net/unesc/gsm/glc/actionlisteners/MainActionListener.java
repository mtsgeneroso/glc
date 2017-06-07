package net.unesc.gsm.glc.actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import net.unesc.gsm.glc.controllers.Main;
import net.unesc.gsm.glc.frames.MainFrame;

public class MainActionListener implements ActionListener {

    private Main main;
    private MainFrame MainFrame;

    public MainActionListener(Main main, MainFrame tela) {

        this.main = main;
        this.MainFrame = tela;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sair")) {
            this.MainFrame.dispose();
        }
    }
}
