import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;



public class ImageUtility
{	
	
	File orgfile;

	public File drawImage(BufferedImage img )
	{
		
		int width = 192;
		int height = 192;

		BufferedImage resizedPic = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gr = resizedPic.createGraphics();
		gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		gr.drawImage(img, 0, 0, width, height, null);
		
		try
		{
			File nfile = new File(orgfile.getName());
			ImageIO.write(resizedPic, "jpg", nfile);
			return nfile;
		}
		catch(IOException ie)
		{
			System.out.println("Fail på tegning");
		}
		return null;
	}

	public BufferedImage openImage(File f)
	{	
		orgfile = f;
		try
		{
			BufferedImage img = ImageIO.read(f);
			return img;
		}
		catch(IOException ie)
		{
			System.out.println("Fail på åpning");
		}
		
		return null;
	}

	static public void saveImage()
	{
		
	}

	
} 

 //  klasse med statiske metoder for tegning og kopiering av bilder,