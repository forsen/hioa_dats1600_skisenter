import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.nio.channels.FileChannel;

/**
  * This class is a helping class for copying, moving and saving an personal image.    
  * 
  * @author Erik Haider Forsén
  * @author Ole Hansen
  * @author Julie Hill Roa
  * @version 0.9
  */

public class ImageUtility
{	

// CONSTANTS
	private final int IMAGEWIDTH = 74;
	private final int IMAGEHEIGHT = 120;
// END CONSTANTS
	
	File orgfile;

/** 
  *This methode draws a buffred image like the original image, but with a different size and with JPG as last name.
  * 
  * @param f a File object for the image you want to resize
  * @return Returns a file object for the new image
  * @see CustWindowPanel
  */

	private File drawImage( File f)
	{	
		
		orgfile = f;
		try
		{
			BufferedImage img = ImageIO.read(f); //Draws the file f

			
			BufferedImage resizedPic = new BufferedImage(IMAGEWIDTH, IMAGEHEIGHT, BufferedImage.TYPE_INT_RGB); 
			//Draws an empty image with the specific size
			Graphics2D gr = resizedPic.createGraphics();
			gr.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, IMAGEWIDTH, IMAGEHEIGHT);
			gr.setComposite(AlphaComposite.SrcOver);
			gr.drawImage(img, 0, 0, IMAGEWIDTH, IMAGEHEIGHT, null); //Puts the drws file onto to the Graphics object wth the new size
			gr.dispose();
		
			//Converts it to a File and let it have the same name as the old image.
			File nfile = new File(orgfile.getName());
			ImageIO.write(resizedPic, "jpg", nfile);
			return nfile;
		}
		catch(IOException ie)
		{
			System.out.println("error : opening or loading image");
		}
		catch(IllegalArgumentException iae)
		{
			return null;
		}
		return null;
	}

	
/** 
  *This methode makes a new file with the name of the persons custId, and puts the image drawn by the methode drawImage() 
  * onto the new file.
  *
  * @param f File object for the chosen personal image. 
  * @param p Witch person the image belongs to. 
  * @see CustWindowPanel
  * @see #drawImage( File f)
  */
	public void saveImage(File f, Person p)
	{
		

		try
		{
			//Creates a new file with the name of the persons custId
			File persPic = new File("persImg/" + p.getCustId()+".jpg");
  			if (!persPic.exists()) 
  			{
       			persPic.createNewFile();
  			}
  			
  			//Gets the file (image) back resized
  			File nf = drawImage(f);
  			
  			//Opens the connection to the file so it can be be moved and renamed
  			FileChannel fsource = new FileInputStream(nf).getChannel();
   			FileChannel target = new FileOutputStream(persPic).getChannel();
   			target.transferFrom(fsource, 0, fsource.size());


   			if (fsource != null) 
   			{
       			fsource.close();
   			}
  			if (target != null) 
  			{
       			target.close();
       		}
       		p.setImage(persPic);
       		nf.delete();

       	}
       	catch(FileNotFoundException fne)
		{
			System.out.println("Couldn't find the directory");
		}
		catch(IOException ie)
		{
			System.out.println("Something went wrong..");
		}
		catch(NullPointerException npe)
		{
			System.out.println("Didn't get a image");
			npe.printStackTrace( System.out ); 
		}
	}


	
} // end of class ImageUtility