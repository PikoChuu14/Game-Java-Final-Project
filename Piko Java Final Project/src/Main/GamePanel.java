package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
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
	public KeyHandler keyH = new KeyHandler(this);
	GameAudio gameAudio = new GameAudio();
	GameAudio seAudio = new GameAudio();
	
	public CollisionDetector collisionDetector = new CollisionDetector(this);
	public AssetManager assetManager = new AssetManager(this);
	
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	//ENTITY AND OBJECT SETTINGS
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//Game status
	public int gameState;
	public final int titleState = 0;
	public final int playState = 2;
	public final int pauseState = 1;
	public final int dialogState = 3;
		
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
		assetManager.setNPC();
		assetManager.setMonster();
		//playAudio(0);
		gameState = titleState;
	}
	
	//Java upper left corner is X:0 Y:0
	//going right X increase
	//going down Y increase
	public void update() {
		//System.out.println("check");
		
		if (gameState == playState) {
			//player
			player.update();
			//npc
			for (int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update(); 
				}
			}
			
			for (int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					monster[i].update(); 
				}
			}
		}
		
		if (gameState == pauseState) {
			
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		else {
			//TILE DRAW
			tileManage.draw(g2);
			
			//add entity to list
			entityList.add(player);
			
			for (int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for (int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for (int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			//sorting entity by worldY
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			
			//draw entities
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			//empty entity list for better performance
			for (int i = 0; i < entityList.size(); i++) {
				entityList.remove(i);
			}
			
			//draw on scree ui
			ui.draw(g2);
			
		}
		
		
		
		//debug
		long drawStart = 0;
		drawStart = System.nanoTime();
		
		if(keyH.drawTimeChecker == true) {
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
		
		}
		
		g2.dispose();
		
	}
	
	public void playAudio(int i) {
		
		gameAudio.setFile(i);
		gameAudio.play();
		gameAudio.loop();
	}
	
	public void stopAudio() {
		
		gameAudio.stop();
		
	}
	
	public void playSoundEffectAudio(int i) {
		
		seAudio.setFile(i);
		seAudio.play();
		
	}
	
}
