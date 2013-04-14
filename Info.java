import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Graphics;
import java.net.URL;

public class Info extends JFrame
{
	public final static int PUNCHCARDPRICE = 200;
	public final static int DAYCARDPRICE = 320;
	public final static int HOURCARDPRICE = 120;
	public final static int SEASONCARDPRICE = 3000;

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JButton button, newsbutton, infobutton;
    private static JPanel newsWindowPnl, infoWindowPnl;
 
    public Info()
    {

        JPanel panel1;
        Listener listener;



        
        JPanel panel;
        JPanel image;
        JLabel label;
        




	setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	if (shouldFill) {
	//natural height, maximum width
	c.fill = GridBagConstraints.BOTH;
	}
	


    // første kolonne /////////////////////////////


	ImageIcon nyheter = new ImageIcon("nyheter.png");
	newsbutton = new JButton(nyheter);

	if (shouldWeightX) {
	c.weightx = 0.5;
	}
	c.fill = GridBagConstraints.BOTH;
	c.gridx = 0;
	c.gridy = 0;
	c.weighty = 1;
	add(newsbutton, c);

	ImageIcon info = new ImageIcon("info.png");
	infobutton = new JButton(info);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		add(infobutton, c);

		ImageIcon tilbud = new ImageIcon("tilbud.png");
		button = new JButton(tilbud);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
	c.weighty = 1;
	add(button, c);

	ImageIcon priser = new ImageIcon("priser.png");
	button = new JButton(priser);
	button.setFocusPainted(false);
	button.setBorderPainted(false);
	button.setContentAreaFilled(false);
	c.fill = GridBagConstraints.BOTH;
	c.weighty = 1;
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 3;
	add(button, c);

	// andre kolonne /////////////////////////////

	panel = new JPanel();
	c.fill = GridBagConstraints.BOTH;
	panel.setBackground(new Color(200, 230, 255));
	panel.setToolTipText("Hei");
	c.weightx = 0.5;
	c.gridheight = 1;
	c.gridwidth = 2;
	c.gridx = 1;
	c.gridy = 0;
	add(panel, c);

	panel1 = new JPanel();
	c.fill = GridBagConstraints.BOTH;
	panel1.setBackground(new Color(220, 240, 255));
	c.weightx = 0.8;
	c.gridheight = 4;
	c.gridwidth = 4;
	c.gridx = 1;
	c.gridy = 1;
	add(panel1, c);

	image = new JPanel(new BorderLayout());
	label = new JLabel(new ImageIcon("offpist_liten.png"));
	panel.add(label);


	// tredje kolonne/////////////

	panel = new JPanel();
	panel.setBackground(Color.CYAN);
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 4;
	c.gridheight = 1;
	c.gridwidth = 1;
	c.gridx = 2;
	c.gridy = 0;
	add(panel, c);

 

	newsWindowPnl = new NewsWindowPanel();
	infoWindowPnl = new InfoWindowPanel();


	panel1.add(newsWindowPnl);
	panel1.add(infoWindowPnl);

	newsWindowPnl.setVisible(true);
	infoWindowPnl.setVisible(false);

	listener = new Listener(); 
	newsbutton.addActionListener( listener );

	infobutton.addActionListener( listener );



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        getContentPane();


        Toolkit verktoykasse = Toolkit.getDefaultToolkit();
  		String bildefil = "offpist_logo.png";
  		Image ikon = verktoykasse.getImage(bildefil);
  		setIconImage(ikon);
        //Display the window.
        pack();
        setSize(1000,1000);
        setVisible(true);



	}

	private class Listener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			newsWindowPnl.setVisible(false);
			infoWindowPnl.setVisible(false);


			if( e.getSource() == newsbutton )
			{
				newsWindowPnl.setVisible(true);
			}
				
			else if( e.getSource() == infobutton)
			{
				newsWindowPnl.setVisible(false);
				infoWindowPnl.setVisible(true);
			}

		}
	}




	// fjerde kolonne/////////////


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */




}/*<datafelt, inkludert statiske priskonstanter som brukes av de forskjellige heiskortklassene>

	<konstruktør som oppretter vindu>

	<grafiske metoder som vi enda ikke har lært (vi ønsker å ha et menybasert system, som endrer 
		hovedinnhold basert på hvilken knapp man trykker)>

	<metode for å hente ut informasjon om åpne heiser>

	<diverse knappelyttere og slikt>*/
