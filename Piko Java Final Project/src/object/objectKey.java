package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class objectKey extends superObject {
	
	public objectKey() {
		
		name = "Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//ni kalau nak setting specific collision area tuk object katakan
		//solidArea.x = 5;
	}
}
