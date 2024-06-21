package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean enterPressed;
	//debug
	boolean drawTimeChecker = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setGamePanel(GamePanel gp) {
        this.gp = gp;
    }

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//title state
		if (gp.gameState == gp.titleState) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				//start game
				if (gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playAudio(0);
				}
				//load game
				if (gp.ui.commandNum == 1) {
					
				}
				//quit
				if (gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
			
		}
		
		//play state
		if (gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
	
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
				//System.out.println("entered");
			}
			
		}
		
		//pause state
		else if (gp.gameState == gp.pauseState) {
			
				if(code == KeyEvent.VK_P) {
					gp.gameState = gp.playState;
				}
		}
		
		//in dialogue state
		else if (gp.gameState == gp.dialogState) {
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
		//debug
		else if(code == KeyEvent.VK_T) {
			if(drawTimeChecker == false) {
				drawTimeChecker = true;
			}
			
			else if (drawTimeChecker == true) {
				drawTimeChecker = false;
			}
		}
				
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
			if(code == KeyEvent.VK_W) {
				upPressed = false;
			}

			if(code == KeyEvent.VK_A) {
				leftPressed = false;
			}
				
			if(code == KeyEvent.VK_S) {
				downPressed = false;
			}
				
			if(code == KeyEvent.VK_D) {
				rightPressed = false;
			}
	}

}
