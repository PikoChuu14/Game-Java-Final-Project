package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.superObject;
import tile.TileManager;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//SCREEN SETTINGS
	final int originalTileSize = 16;	//16x16 tile
	final int scale = 3;		//upscale
	public final int tileSize = originalTileSize * scale; //applying the scale = 48x48
	public final int maxScreenColumn = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenColumn;		//768 pixels
	public final int screenHeight = tileSize * maxScreenRow;		//576 pixels
	
	//WORLD MAP SETTINGS
	//50 x 50 map
	public final int maxWorldColumn = 50;
	public final int maxWorldRow = 50;
	//public final int worldWidth = tileSize * maxWorldColumn;
	//public final int worldHeight = tileSize * maxWorldRow;
	
	TileManager tileManage = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	
	GameAudio gameAudio = new GameAudio();
	GameAudio seAudio = new GameAudio();
	
	public CollisionDetector collisionDetector = new CollisionDetector(this);
	public AssetManager assetManager = new AssetManager(this);
	
	public UI ui = new UI(this);
	
	//ENTITY AND OBJECT SETTINGS
	public Player player = new Player(this, keyH);
	
	
	//prepare 10 slot for object to display at the same time
	//xnk bnyk" takut masalah rendering
	public superObject obj[] = new superObject[10];
		
	public GamePanel(KeyHandler keyHandler) {
		this.player = new Player(this, keyH);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void gameSetup() {
		
		assetManager.setObject();
		playAudio(0);
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
		
		//TILE DRAW
		tileManage.draw(g2);
		
		//ASSET DRAW
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		
		//PLAYER DRAW
		player.draw(g2);
		
		//draw on scree ui
		ui.draw(g2);
		
		
		g2.dispose();
		
	}
	
	public void playAudio(int i) {
		
		gameAudio.setFile(i);
		gameAudio.play();
		gameAudio.loop();
	}
	
	public void stopAudio(int i) {
		
		gameAudio.stop();
		
	}
	
	public void playSoundEffectAudio(int i) {
		
		seAudio.setFile(i);
		seAudio.play();
		
	}
	
}
