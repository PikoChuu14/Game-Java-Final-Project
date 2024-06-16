package Main;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstWindow frame = new FirstWindow();
					frame.setVisible(true);
					
					KeyHandler keyHandler = new KeyHandler();
					
					GamePanel gamePanel = new GamePanel(keyHandler);
					frame.add(gamePanel);
					frame.pack();
					
					gamePanel.gameSetup();
					
					Threading threading = new Threading(keyHandler, gamePanel);
					threading.startGameThread();
					
					frame.addKeyListener(keyHandler);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
