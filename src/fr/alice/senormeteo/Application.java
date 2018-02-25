package fr.alice.senormeteo;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		/**
		 * Runnable est une interface, 
		 * on cr�e une classe anonyme qui impl�mente Runnable
		 */
		EventQueue.invokeLater(new Runnable() {
		
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame("Senor M�t�o");
				mainFrame.setResizable(false); // on ne doit pas pouvoir redimensionner la fen�tre
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Le run s'arr�te quand on ferme la fen�tre
				mainFrame.pack();
				mainFrame.setLocationRelativeTo(null); // la fen�tre est au centre de l'�cran
				mainFrame.setVisible(true);
			}
			
		});

	}

}
