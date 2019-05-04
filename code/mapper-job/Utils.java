import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.util.Pair; 

public class Utils {

    // return a Tuple or NULL if it doesn't matches to any of the palette colors.
    public static ArrayList<Integer> getPaletteColor(int pixel, ArrayList<ArrayList<Integer>> rgbList) {

        int R = (pixel >> 16) & 0xff;
        int G = (pixel >> 8) & 0xff;
        int B = (pixel) & 0xff;
        //System.out.println("R: " + R + ", G: " + G + ", B: " + B);

        return null;
    }

	public static void ImageBFS(File file, ArrayList<ArrayList<Integer>> rgbList) throws IOException {

        int dx[] = {1,1,1,0,0,-1,-1,-1};
        int dy[] = {1,0,-1,1,-1,1,0,-1};

        Map<Pair, Integer> edgeMap = new HashMap<>();

        //BufferedImage bi = ImageIO.read(new File(filePath));    
        BufferedImage bi = ImageIO.read(file);
        
        int h = bi.getHeight();
        int w = bi.getWidth();
        //System.out.println(h + " " + w);

        for(int x=0;x<w;x++) {
            for(int y=0;y<h;y++) {
                
                int pixel1 = bi.getRGB(x, y);
                
                for(int dir = 0; dir < 8; dir++) {
                    
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    
                    if(nx>=0 && nx<w && ny>=0 && ny<h) {
                        
                        int pixel2 = bi.getRGB(nx, ny);
                
                        ArrayList<Integer> X = getPaletteColor(pixel1, rgbList);
                        ArrayList<Integer> Y = getPaletteColor(pixel2, rgbList);


                        if(X!=null && Y!=null) {
                            
                        }

                    }
                }

            }
        }
    }
}

