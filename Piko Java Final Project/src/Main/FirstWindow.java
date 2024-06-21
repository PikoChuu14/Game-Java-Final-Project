package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstWindow extends JFrame {
	
	GamePanel gp;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu audioMenuBar;
	private JMenuItem muteMenuItem;
	private boolean isMuted = false;

	public FirstWindow(GamePanel gp) {
		
		this.gp = gp;
		
		setTitle("MyGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setResizable(false);					//supaya org xle resize
		setLocationRelativeTo(null);			//untuk letak window kat tngh" screen
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		audioMenuBar = new JMenu("Audio");
		menuBar.add(audioMenuBar);
		
		muteMenuItem = new JMenuItem("Mute");
		muteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isMuted == false) {
                    gp.gameAudio.stop();
                    muteMenuItem.setText("Unmute");
                    isMuted = true;
                } 
				
				else {
                    gp.gameAudio.play();
                    muteMenuItem.setText("Mute");
                    isMuted = false;
                }
            }
			
		});
		audioMenuBar.add(muteMenuItem);
		
		setContentPane(contentPane);
	}

}
