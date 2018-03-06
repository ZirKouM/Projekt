package uebung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Uebung_fur_SA extends JFrame {

	/**
	 * TODO: Aufgabe 7: Entry-Objekt (2 Attribute - eine Zeile in Liste
	 */
	private class Entry {

	}

	private static final String ENCODING = "UTF8";

	/** Die Datei */
	private static final File FILE = new File("todo.txt");

	/** The font for the list */
	private static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);

	/** main */
	public static void main(final String[] args) {
		new Uebung_fur_SA();
	}

	/** Create a new object */
	public Uebung_fur_SA() {

		super();

		// title
		setTitle("Uebung f�r SA");

		// window listener
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(final WindowEvent e) {
			}
		});

		// container for components and so on.
		final Container con = getContentPane();

		// layout
		con.setLayout(new BorderLayout());

		/**
		 * TODO: Aufgabe 1: Label (lbl) mit Text "Hallo" - Position: NORTH
		 */

con.setLayout(new BorderLayout());
JLabel norden = new JLabel("Hallo");
con.add(norden, BorderLayout.NORTH);


		/**
		 * TODO: Aufgabe 2: Textfeld (txf)- Position: CENTER
		 */
JTextField tTextfeld;

con.setLayout(new BorderLayout());
tTextfeld = new JTextField();
con.add(tTextfeld, BorderLayout.CENTER);

		/**
		 * TODO: Aufgabe 3: 2 Buttons (bt1, bt2) incl. Panel - Position: SOUTH
		 */

JPanel pButton = new JPanel();
pButton.setLayout(new GridLayout(2,1));

JButton bBT1 = new JButton("bt1");
JButton bBT2 = new JButton("bt2");

pButton.add(bBT1);
pButton.add(bBT2);
con.add(pButton, BorderLayout.SOUTH);

		/**
		 * TODO: Aufgabe 4: Action Listener f�r bt1) -> Setzt lbl-Text auf "Servus"
		 */
		bBT1.addActionListener((e) -> {System.out.println("Servus");

		});

		/**
		 * TODO: Aufgabe 5: Liste scrollbar(Default-List-Model) Position: EAST
		 */
DefaultListModel lm;
lm = new DefaultListModel<Vector<String>>();
JList Uebung_fur_SA = new JList();
JScrollPane sScrollPane = new JScrollPane(Uebung_fur_SA);
con.add(sScrollPane, BorderLayout.EAST);
		/**
		 * TODO: Aufgabe 6: Methode (bt2: eine selektierte Zeile aus Liste entfernen
		 */
int [] auswahl = Uebung_fur_SA.getSelectedIndices();
		bBT2.addActionListener((e) -> {
			for(int i = auswahl.length -1; i>=0; i--) {
			lm.removeElementAt(auswahl[i]);
			}
		});

		// set the size for the window
		setSize(460, 200);

		// make it visible
		setVisible(true);
	}

}
}
