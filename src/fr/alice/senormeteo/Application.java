package fr.alice.senormeteo;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		/**
		 * Runnable est une interface, 
		 * on crée une classe anonyme qui implémente Runnable
		 */
		EventQueue.invokeLater(new Runnable() {
		
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame("Senor Météo");
				mainFrame.setResizable(false); // on ne doit pas pouvoir redimensionner la fenêtre
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Le run s'arrête quand on ferme la fenêtre
				mainFrame.pack();
				mainFrame.setLocationRelativeTo(null); // la fenêtre est au centre de l'écran
				mainFrame.setVisible(true);
			}
			
		});

	}

}
