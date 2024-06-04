package Main;

import javax.swing.JPanel;

public class Threading extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private Thread gameThread;
	private GamePanel gamePanel;
	
	//setting game FPS
	int FPS = 60;
	
	public Threading(KeyHandler keyHandler, GamePanel gamePanel) {
		this.gamePanel = gamePanel;
        gameThread = new Thread(this); 
        
    }

	public void startGameThread() {
		gameThread.start();
	}
	

	@Override
	public void run() {
		
		double interval = 1000000000 / FPS;					//in nanoseconds
		double nextDraw = System.nanoTime() + interval;
		
		while(gameThread != null) {
			
			//long currentTime = System.nanoTime();
			//System.out.println("current time : " + currentTime);  //debugging
			
			//updating player position
			gamePanel.update();
			
			//draw the updated information
			gamePanel.repaint();
				
			try {
				
				double remainingTime = nextDraw - System.nanoTime();
				remainingTime = remainingTime / 1000000; //convert to miliseconds
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDraw += interval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
