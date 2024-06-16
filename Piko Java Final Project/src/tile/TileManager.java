package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	
	public int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		//setting total jenis tile yg nak guna
		tile = new Tile[10];
		
		//map size
		mapTileNumber = new int [gp.maxWorldColumn][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/testWorld01.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int column = 0;
			int row = 0;
			
			while (column < gp.maxWorldColumn && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while (column < gp.maxWorldColumn) {
					
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[column]);
					
					mapTileNumber [column] [row] = num;
					column++;
					
				}
				
				if (column == gp.maxWorldColumn) {
					column = 0;
					row++;
				}
			}
			
			br.close();
		}
		
		catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		//bucu kiri is x:0 y:0
		//guna scale 48
		/*g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image,48, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);*/
		
		int worldColumn = 0;
		int worldRow = 0;
		//int x = 0;
		//int y = 0;
		
		while (worldColumn < gp.maxWorldColumn && worldRow < gp.maxWorldRow) {
			
			int tileNumber = mapTileNumber [worldColumn] [worldRow];
			
			int worldX = worldColumn * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//(...... 1.tile image 2.x-coordinate 3.y-coordinate)
			//line to improve rendering
			if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldColumn++;
			//x += gp.tileSize;
			 
				if (worldColumn == gp.maxWorldColumn) {
					worldColumn = 0;
					//x = 0;
					worldRow++;
					//y += gp.tileSize;
				}
		   
		}
	}
}
