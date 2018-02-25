package fr.alice.senormeteo.utilities;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Refactoring
 * Les erreurs sont gérées dans un autre package (on aurait pu faire sans)
 */

public class Alert {

	public static void error(Component parentComponent, String title, String message) {
		/**
		 * JOptionPane = boites de dialogue d'erreur
		 */
		JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void error(Component parentComponent, String message) {
		error(parentComponent, "Erreur", message);
		//JOptionPane.showMessageDialog(parentComponent, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void info(Component parentComponent, String title, String message) {
		JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void info(Component parentComponent, String message) {
		info(parentComponent, "Information", message);
		//JOptionPane.showMessageDialog(parentComponent, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
