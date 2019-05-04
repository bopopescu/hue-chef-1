import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.nio.*;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Driver Program
public class Main {

	public static void main(String[] args) throws IOException {
		
		File[] paletteFiles = new File("./palette_data/").listFiles();
		loadPaletteData(paletteFiles);

		File[] files = new File("./data/").listFiles();
		operateOnFiles(files);
	}


	public static void loadPaletteData(File[] files) {
		
		HashMap<String,ArrayList<ArrayList<Integer>>> newMap = new HashMap<>();

		// For all the palette Json files.
		for (File file : files) {
			//System.out.println("File: " + file.getName());

			JSONParser parser = new JSONParser();
        	
        	try {
            	Object object = parser.parse(new FileReader(file.getPath()));
            
            	//convert Object to JSONObject
            	JSONObject jsonObject = (JSONObject)object;
            	
            	//System.out.println("JSONOBJECT: "+jsonObject);
            	
            	for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
    				String key = (String) iterator.next();
    				
    				//System.out.println(jsonObject.get(key));
					
					JSONArray jsonArr = (JSONArray)jsonObject.get(key);
	            	
	            	ArrayList<ArrayList<Integer>> mapList = new ArrayList<>();
	            	
	            	for (int j = 0; j < 5; j++) {
	            		ArrayList<Integer> innerTriplet = new ArrayList<>();

  						JSONArray innerJsonArr = (JSONArray)jsonArr.get(j);
  						// System.out.println("INNER JSONARRAY: "+innerJsonArr);
  						// ArrayList<Integer> list = new ArrayList<Integer>(); 
  						for (int k = 0; k < 3; k++) {
  							long elem = (Long)innerJsonArr.get(k);
  							innerTriplet.add((int)elem);
						}
						mapList.add(innerTriplet);
					}
					newMap.put(key, mapList);
            	}
			} catch(Exception e) {
				System.out.println("Exception while forming Palette data map." + e);
			}
		}

		//System.out.println("MAP: "+newMap.toString());
	}

	public static void operateOnFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				//System.out.println("Directory: " + file.getName());
				operateOnFiles(file.listFiles()); 
			} else {
				//Utils.ImageBFS(file);
				//System.out.println("File: " + file.getName());
			}
		}
	}

}