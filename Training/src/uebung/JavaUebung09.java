package Übung;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class JavaUebung09 extends javax.swing.JFrame {

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JavaUebung09 inst = new JavaUebung09();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public JavaUebung09() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
