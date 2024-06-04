package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class FirstWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public FirstWindow() {
		setTitle("MyGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setResizable(false);					//supaya org xle resize
		setLocationRelativeTo(null);			//untuk letak window kat tngh" screen
		setContentPane(contentPane);
	}

}
