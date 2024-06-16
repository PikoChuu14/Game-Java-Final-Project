package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectBoots extends superObject {

	public objectBoots() {
		
		name = "Boots";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

