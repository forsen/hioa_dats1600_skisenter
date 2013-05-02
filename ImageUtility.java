import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.nio.channels.FileChannel;



public class ImageUtility
{	
	
	File orgfile;

	private File drawImage( File f)
	{	
		
		orgfile = f;
		try
		{
			BufferedImage img = ImageIO.read(f);

		
		
			int width = 74;
			int height = 120;

			BufferedImage resizedPic = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gr = resizedPic.createGraphics();
			//gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			gr.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, width, height);
			gr.setComposite(AlphaComposite.SrcOver);
			gr.drawImage(img, 0, 0, width, height, null);
			gr.dispose();
		
		
			File nfile = new File(orgfile.getName());
			ImageIO.write(resizedPic, "png", nfile);
			return nfile;
		}
		catch(IOException ie)
		{
			System.out.println("Feil med åpning eller tegning av bildet");
		}
		catch(IllegalArgumentException iae)
		{
			return null;
		}
		return null;
	}

	

	public void saveImage(File f, Person p)
	{
		

		try
		{
			File persPic = new File("persImg/" + p.getCustId()+".jpg");
  			if (!persPic.exists()) 
  			{
       			persPic.createNewFile();
  			}
  			
  			File nf = drawImage(f);
  			


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
			System.out.println("fant ikke mappa");
		}
		catch(IOException ie)
		{

		}
		catch(NullPointerException npe)
		{
			
		}
	}


	
} 

 //  klasse med statiske metoder for tegning og kopiering av bilder,