import java.util.*;


public class ImageBFS {

	public static void main() {
		BufferedImage bi = ImageIO.read(new File("C:\\images\\Sunset.jpg"));

		for (int x = 0; x < bi.getWidth(); x++) {
    		for (int y = 0; y < bi.getHeight(); y++) {
        		int pixel = bi.getRGB(x, y);
        		int red = (pixel >> 16) & 0xff;
        		int green = (pixel >> 8) & 0xff;
        		int blue = (pixel) & 0xff;
        		System.out.println("red: " + red + ", green: " + green + ", blue: " + blue);                                                                                                                                                  
    		}
		}
	}
}


