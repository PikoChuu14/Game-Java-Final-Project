package Main;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	
                	KeyHandler keyHandler = new KeyHandler(null);
                	
                	GamePanel gamePanel = new GamePanel(keyHandler);
                	
                	keyHandler.setGamePanel(gamePanel);
                	
                    FirstWindow frame = new FirstWindow(gamePanel);
                    frame.setVisible(true);
              
                	
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
