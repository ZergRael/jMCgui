package net.thetabx.jmcgui.Frames;

import net.thetabx.jmcgui.DataTypes.Block;
import net.thetabx.jmcgui.McConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MinimapPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<MinimapDot> dots = new ArrayList<MinimapDot>();
	private ArrayList<MinimapBlock> blocks = new ArrayList<MinimapBlock>();
	private BufferedImage terrain; //"res/terrain.png"

	private int zoom = McConstants.DEFAULTFPS;
	private final int BLOCK_SIZE = 16;
	
	public void setTexturePack(String location) {
		try {
			terrain = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setZoom(int zoom) {
		if(zoom < 1 || zoom > 128)
			return;
		
		this.zoom = zoom;
	}
	
	public void paintComponent(Graphics g){
		// Background
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN, 9));

		// Blocks
		while(!blocks.isEmpty()) {
			MinimapBlock block = null;
			synchronized(blocks)
			{
				block = blocks.remove(0);
			}
			if(block == null)
				continue;
			
			int x = (int)(this.getWidth()/2.0 + block.x*zoom);
			int y = (int)(this.getHeight()/2.0 + block.z*zoom);
			Block mcBlock = Block.getBlock(block.data);
			if(mcBlock != Block.Unknown) {
				int xImg = mcBlock.getX();
				int yImg = mcBlock.getY();
				g.drawImage(terrain, x, y, x + zoom, y + zoom, xImg, yImg, xImg + BLOCK_SIZE, yImg + BLOCK_SIZE, null);
			}
			else
				g.drawString(block.data + "", x, y + zoom);
			//g.setColor(McBlock.getColor((byte)block[2]));
			//g.fillRect(x, z, ZOOM, ZOOM);
			//g.drawString((byte) block[2] + "", x, z);
		}
		
		// Dots
		while(!dots.isEmpty())
		{
			MinimapDot dot = null;
			synchronized(dots)
			{
				dot = dots.remove(0);
			}
			if(dot == null)
				continue;
			
			int x = (int)(this.getWidth()/2.0 + dot.x*zoom);
			int y = (int)(this.getHeight()/2.0 + dot.z*zoom);
			//int y = ((int) dot[2] + 255)/2;
			
			g.setColor(Color.black);
			g.drawString(dot.name, x, y);
			g.fillRect(x, y, 2, 2);
		}
	}
	
	public void setDot(double x, double z, double y, String name)
	{
		synchronized(dots)
		{
			dots.add(new MinimapDot(x, z, y, name));
		}
	}
	
	public void setBlock(double x, double z, byte data) {
		//System.out.println("Block set at x=" + x + ", z=" + z + ". Id= " + data);
		synchronized(dots)
		{
			blocks.add(new MinimapBlock(x, z, data));
		}
	}

    class MinimapBlock {
        public double x;
        public double z;
        public byte data;

        public MinimapBlock(double x, double z, byte data) {
            this.x = x;
            this.z = z;
            this.data = data;
        }
    }

    class MinimapDot {
        public double x;
        public double z;
        public double y;
        public String name;

        public MinimapDot(double x, double z, double y, String name) {
            this.x = x;
            this.z = z;
            this.y = y;
            this.name = name;
        }
    }
}
