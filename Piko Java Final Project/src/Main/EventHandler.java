package Main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	EventRectangle eventRectangle[][];
	
	//nak buat event repeatable tpi after gerak 1 tile away
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler (GamePanel gp) {
		this.gp = gp;
		eventRectangle = new EventRectangle[gp.maxWorldColumn][gp.maxWorldRow];
		
		int column = 0;
		int row = 0;
		
		while (column < gp.maxWorldColumn && row < gp.maxWorldRow) {
			
			eventRectangle[column][row] = new EventRectangle();
			eventRectangle[column][row].x = 23;
			eventRectangle[column][row].y = 23;
			eventRectangle[column][row].width =2;
			eventRectangle[column][row].height =2;
			eventRectangle[column][row].eventRectDefaultX = eventRectangle[column][row].x;
			eventRectangle[column][row].eventRectDefaultY = eventRectangle[column][row].y;
			
			column++;
			if (column == gp.maxWorldColumn) {
				column = 0;
				row++;
			}
			
		}

		
	}
	
	public void checkEvent() {
		
		//check if player more than 1 tile away from last eevent
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if (canTouchEvent == true) {
			
			if (hitEvent(24, 20, "right") == true) { damagePit(24,20,gp.dialogState); }
			if (hitEvent(22, 22, "any") == true) { damagePit(22,22,gp.dialogState); }
			if (hitEvent(23,7,"up") == true) { healingPool(23,7,gp.dialogState); }
			
		}
		
	}
	
	public void damagePit(int column , int row, int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "bodo jatuh longkang";
		gp.player.life -= 1;
		//eventRectangle[column][row].eventDone = true;
		canTouchEvent = false;
		
	}
	
	public void healingPool(int column, int row, int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You drank Holy Water! Now you feel refreshed!";
			gp.player.life = gp.player.maxLife;
		}

	}

	public boolean hitEvent(int column, int row, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.playerBodyArea.x = gp.player.worldX + gp.player.playerBodyArea.x;
		gp.player.playerBodyArea.y = gp.player.worldY + gp.player.playerBodyArea.y;
		
		eventRectangle[column][row].x = column*gp.tileSize + eventRectangle[column][row].x;
		eventRectangle[column][row].y = row*gp.tileSize + eventRectangle[column][row].y;
		
		//checking player collisoion with event
		if (gp.player.playerBodyArea.intersects(eventRectangle[column][row]) && eventRectangle[column][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.playerBodyArea.x = gp.player.solidDefaultAreaX;
		gp.player.playerBodyArea.y = gp.player.solidDefaultAreaY;
		eventRectangle[column][row].x = eventRectangle[column][row].eventRectDefaultX;
		eventRectangle[column][row].y = eventRectangle[column][row].eventRectDefaultY;
		
		
		return hit;
		
	}
	
}
