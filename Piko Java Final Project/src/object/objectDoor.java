package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectDoor extends superObject {

	public objectDoor() {
			
			name = "Door";
			
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			}
			
			catch (IOException e) {
				e.printStackTrace();
			}
			
			collision = true;
			
	}
	
}
