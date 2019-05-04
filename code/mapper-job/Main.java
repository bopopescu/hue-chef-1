import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.nio.*;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.util.Pair; 

// Driver Program
public class Main {

	static Map<String,ArrayList<ArrayList<Integer>>> paletteMap;
	static Map<Pair, Integer> edgeMap; // [ <?> ]
	static int count;

	public static void main(String[] args) throws IOException {
		
		File[] paletteFiles = new File("./palette_data/").listFiles();
		loadPaletteData(paletteFiles);


		File[] files = new File("./data/").listFiles();
		count =0;
		edgeMap = new HashMap<>();
		operateOnFiles(files);
		System.out.println(count);
	}


	public static void loadPaletteData(File[] files) {
		
		paletteMap = new HashMap<>();

		int ctr = 0;

		// For all the palette Json files.
		for (File file : files) {
			
			//System.out.println("File: " + file.getName());

			JSONParser parser = new JSONParser();
        	
        	try {
            	Object object = parser.parse(new FileReader(file.getPath()));
            
            	//convert Object to JSONObject
            	JSONObject jsonObject = (JSONObject)object;
            	            	
            	for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
    				String key = (String) iterator.next();
    				ctr++;

    				//System.out.println(key);
					
					JSONArray jsonArr = (JSONArray)jsonObject.get(key);
	            	
	            	ArrayList<ArrayList<Integer>> rgbList = new ArrayList<>();
	            	
	            	for (int j = 0; j < 5; j++) {
	            		ArrayList<Integer> triplet = new ArrayList<>();

  						JSONArray innerJsonArr = (JSONArray)jsonArr.get(j);

  						for (int k = 0; k < 3; k++) {
  							long elem = (Long)innerJsonArr.get(k);
  							triplet.add((int)elem);
						}
						rgbList.add(triplet);
					}
					paletteMap.put(key + ".jpg", rgbList); // Key -> FileName with extension.
            	}
			} catch(Exception e) {
				System.out.println("Exception while forming Palette data map." + e);
			}
		}

		//System.out.println("MAP: " + paletteMap.toString());
		//System.out.println(ctr);
	}

	public static void operateOnFiles(File[] files) throws IOException {

		for (File file : files) {
			if (file.isDirectory()) {
				//System.out.println("Directory: " + file.getName());
				operateOnFiles(file.listFiles()); 
			} else {
				// Operate on the file
				ArrayList<ArrayList<Integer>> rgbList = null;
				try{
					rgbList = paletteMap.getOrDefault(file.getName(),null);
					//System.out.println("###" + rgbList);
				} catch(Exception e) {
					//System.out.println(file.getName());
				}

				if(rgbList!=null) {
					count++;
					Utils.ImageBFS(file, rgbList, edgeMap);	
				} else {
					//System.out.println("# " + file.getName());
				}

				//System.out.println("File: " + file.getName());
			}
		}
	}

}