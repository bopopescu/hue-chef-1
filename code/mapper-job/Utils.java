import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.util.Pair; 

public class Utils {

    static boolean inRange(int val, int L, int R) {
        if(val>=L && val<=R) {
            return true;
        }
        return false;
    }

    // return a Tuple or NULL if it doesn't matches to any of the palette colors.
    static ArrayList<Integer> getPaletteColor(int pixel, ArrayList<ArrayList<Integer>> rgbList) {

        int R = (pixel >> 16) & 0xff;
        int G = (pixel >> 8) & 0xff;
        int B = (pixel) & 0xff;
        //System.out.println("R: " + R + ", G: " + G + ", B: " + B);


        for(int i=0;i<5;i++) {
            ArrayList<Integer> curr = rgbList.get(i);

            if(inRange(R, curr.get(0) - Constants.DELTA, curr.get(0) + Constants.DELTA) &&
               inRange(G, curr.get(1) - Constants.DELTA, curr.get(1) + Constants.DELTA) &&
               inRange(B, curr.get(2) - Constants.DELTA, curr.get(2) + Constants.DELTA)) {
                
                //System.out.println("Bucket match!");
                return curr;
            }       
        }

        return null;
    }

    public static void ImageBFS(File file, ArrayList<ArrayList<Integer>> rgbList, Map<Pair, Integer> edgeMap) throws IOException {
        
        //System.out.println("RGB List size: " + rgbList.size());
        //System.out.println("HERE!");

        int dx[] = {1,1,1,0,0,-1,-1,-1};
        int dy[] = {1,0,-1,1,-1,1,0,-1};

        Map<Pair, Integer> localEdgeMap = new HashMap<>();

        //BufferedImage bi = ImageIO.read(new File(filePath));    
        BufferedImage bi = ImageIO.read(file);
        
        int h = bi.getHeight();
        int w = bi.getWidth();
        //System.out.println(h + " " + w);

        int ctr = 0;

        for(int x=0;x<w;x++) {
            for(int y=0;y<h;y++) {
                
                int pixel1 = bi.getRGB(x, y);
                
                for(int dir = 0; dir < 8; dir++) {
                    
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    
                    if(nx>=0 && nx<w && ny>=0 && ny<h) {
                        
                        int pixel2 = bi.getRGB(nx, ny);
                
                        ArrayList<Integer> X = getPaletteColor(pixel1, rgbList);
                        
                        /*if(X!=null) {
                            System.out.println("HERE 2!");
                        }*/

                        ArrayList<Integer> Y = getPaletteColor(pixel2, rgbList);
                        
                        /*if(Y!=null) {
                            System.out.println("HERE 2!"); 
                        }*/

                        if(X!=null && Y!=null) {
                            ctr++;
                            //System.out.println("HERE 2!");

                            Pair P = new Pair(X,Y);
                            if(localEdgeMap.containsKey(P)) {
                                int count = edgeMap.get(P);
                                count++;
                                localEdgeMap.put(P,count);
                                //System.out.println("OK " + count);
                            } else {
                                localEdgeMap.put(P,1);
                                //System.out.println("New!");
                            }

                        } // EDGE

                    } // IF

                } // direction

            } // Y

        } // X

        // 255 * 255 = 65025
        System.out.println("Pixels operated on: " + ctr);

        // Use MIN_THRESHOLD value to put Edges from localEdgeMap to the edgeMap

        // Using for-each loop 
        for (Map.Entry mapElement : localEdgeMap.entrySet()) { 
            
            Pair key = (Pair)mapElement.getKey(); 
            Integer value = (Integer)mapElement.getValue();

            if(value < Constants.MIN_THRESHOLD) {
                continue;
            }

            if(edgeMap.containsKey(key)) {
                int count = edgeMap.get(key);
                count+=localEdgeMap.get(key);
                edgeMap.put(key, count);
            } else {
                int count = localEdgeMap.get(key);
                edgeMap.put(key, count);
            }
        }
        // [Done] ~ Updating the global edge map.
    
    }
}

