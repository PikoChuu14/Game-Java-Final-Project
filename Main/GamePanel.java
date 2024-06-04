package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//SCREEN SETTINGS
	final int originalTileSize = 16;	//16x16 tile
	final int scale = 3;		//upscale
	public final int tileSize = originalTileSize * scale; //applying the scale = 48x48
	final int maxScreenColumn = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenColumn;		//768 pixels
	final int screenHeight = tileSize * maxScreenRow;		//576 pixels
	
	KeyHandler keyH = new KeyHandler();
	Player player = new Player(this, keyH);
		
	public GamePanel(KeyHandler keyHandler) {
		this.keyH = keyHandler;
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	//Java upper left corner is X:0 Y:0
	//going right X increase
	//going down Y increase
	public void update() {
		//System.out.println("check");
		player.update();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		g2.dispose();
		
	}
	
}
