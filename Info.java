import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Graphics;

public class Info extends JFrame
{
	public final static int PUNCHCARDPRICE = 200;
	public final static int DAYCARDPRICE = 320;
	public final static int HOURCARDPRICE = 120;

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

 public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        JTextArea area;
        
        JPanel panel;



	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	if (shouldFill) {
	//natural height, maximum width
	c.fill = GridBagConstraints.BOTH;
	}


    // første kolonne /////////////////////////////


	ImageIcon nyheter = new ImageIcon("nyheter.png");
	button = new JButton(nyheter);
	button.setFocusPainted(false);
	button.setContentAreaFilled(false);
	if (shouldWeightX) {
	c.weightx = 0.5;
	}
	c.fill = GridBagConstraints.BOTH;
	c.gridx = 0;
	c.gridy = 0;
	c.weighty = 1;
	pane.add(button, c);

	ImageIcon info = new ImageIcon("info.png");
	button = new JButton(info);
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 1;
	c.weighty = 1;
	pane.add(button, c);

	ImageIcon tilbud = new ImageIcon("tilbud.png");
	button = new JButton(tilbud);
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 2;
	c.weighty = 1;
	pane.add(button, c);

	ImageIcon priser = new ImageIcon("priser.png");
	button = new JButton(priser);
	c.fill = GridBagConstraints.BOTH;
	c.weighty = 1;
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 3;
	pane.add(button, c);

	// andre kolonne /////////////////////////////

	panel = new JPanel();
	c.fill = GridBagConstraints.BOTH;
	panel.setBackground(new Color(200, 230, 255));
	c.weightx = 0.5;
	c.gridheight = 1;
	c.gridwidth = 2;
	c.gridx = 1;
	c.gridy = 0;
	pane.add(panel, c);

	area = new JTextArea();
	c.fill = GridBagConstraints.BOTH;
	area.setBackground(new Color(220, 240, 255));
	c.weightx = 0.8;
	c.gridheight = 4;
	c.gridwidth = 4;
	c.gridx = 1;
	c.gridy = 1;
	pane.add(area, c);

	// tredje kolonne/////////////

	panel = new JPanel();
	panel.setBackground(Color.CYAN);
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 4;
	c.gridheight = 1;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 0;
	pane.add(panel, c);

	// fjerde kolonne/////////////

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();

            }
        });
    }


}/*<datafelt, inkludert statiske priskonstanter som brukes av de forskjellige heiskortklassene>

	<konstruktør som oppretter vindu>

	<grafiske metoder som vi enda ikke har lært (vi ønsker å ha et menybasert system, som endrer 
		hovedinnhold basert på hvilken knapp man trykker)>

	<metode for å hente ut informasjon om åpne heiser>

	<diverse knappelyttere og slikt>*/
