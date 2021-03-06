package client.utils;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jvnet.substance.SubstanceLookAndFeel;

/**
 * CLIENT SIDED JFrame Utilities
 * 
 * @author Karimshan
 *
 */
public class JFrameUtils {
	
	/**
	 * Displays a jinput dialog.
	 * @param title
	 * @param message
	 * @return
	 */
	public static Object inputDialog(String title, Object message) {
		return JOptionPane.showInputDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Returns true if the user confirms.
	 * @param title
	 * @param message
	 * @return
	 */
	public static boolean confirmDialog(String title, Object message) {
		int result = JOptionPane.showConfirmDialog(new JFrame(), message, title, JOptionPane.WARNING_MESSAGE);
		return result == 0 ? true : false;
	}
	
	/**
	 * Shows a message.
	 * @param title
	 * @param message
	 */
	public static void showMessage(String title, Object message) {
		JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Gives the option to select the file.
	 * @param directory
	 * @return
	 */
	public static File selectFile(boolean directory) {
		JFileChooser chooser = new JFileChooser();
		File file = null;
		chooser.setFileSelectionMode(directory ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_ONLY);
		if(chooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			try {
				file = chooser.getSelectedFile();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Error occured while selecting path, make sure it's valid and exists.");
			}
		}
		return file;
	}

	/**
	 * Sets the theme
	 * @param theme
	 */
	public static void setSubstanceSkin(String theme) {
		theme = theme.replace(" ", "");
		try {
			SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin."+theme+"Skin");
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {
			System.err.println("Substance error: " + e.getMessage());
		}
	}

	/**
	 * Sets the icon.
	 * @param frame
	 * @param path
	 */
	public static void setFrameIcon(JFrame frame, String path) {
		try {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(path));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
