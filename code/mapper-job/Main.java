import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.*;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.util.Pair; 

// Gson
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.JsonSyntaxException;


// Driver Program
public class Main {

	static HashMap<String,ArrayList<ArrayList<Integer>>> paletteMap;
	static HashMap<Pair<ArrayList<Integer>, ArrayList<Integer>>, Integer> edgeMap; // [ <?> ]
	static int count;

	public static void main(String[] args) throws IOException {
		
		// Load the paletteMap using the palette_data that has been generated by the GAN layer. 
		File[] paletteFiles = new File("./palette_data/").listFiles();
		loadPaletteData(paletteFiles);

		// Operate on all the files.
		File[] files = new File("./data/").listFiles();
		count = 0;
		edgeMap = new HashMap<>();
		operateOnFiles(files);
		System.out.println("Operation done on " + count + " files");

		// Dump in a file 
		PrintWriter writer = new PrintWriter("Edge_Map.txt", "UTF-8");
	
		for (Map.Entry mapElement : edgeMap.entrySet()) { 
            
            Pair pair = (Pair)mapElement.getKey(); 

            //System.out.println(pair.getKey());
            
            ArrayList<Integer> X = (ArrayList<Integer>)pair.getKey();
            ArrayList<Integer> Y = (ArrayList<Integer>)pair.getValue();
            Integer value = (Integer)mapElement.getValue();

            String output = "";
            
            for(int i=0;i<3;i++) {
            	output+=Integer.toString(X.get(i));
            	output+=Constants.DELIM;
            }

            for(int i=0;i<3;i++) {
            	output+=Integer.toString(Y.get(i));
            	output+=Constants.DELIM;
            }

            output+=Integer.toString(value);

            writer.println(output);
        }

        writer.close();

        
        /*
        // Deserialize test 
		HashMap<Pair<ArrayList<Integer>, ArrayList<Integer>>, Integer> cloneMap = new HashMap<>();

        try {

        	String line;

            FileReader fileReader = new FileReader("Edge_Map.txt");
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                //System.out.println(line);

                String[] strArray = line.split("\\|");

				int[] intArray = new int[strArray.length];
				
				for(int i = 0; i < strArray.length; i++) {
					//System.out.print(strArray[i] + " ");
					intArray[i] = Integer.parseInt(strArray[i]);
				}

				ArrayList<Integer> X = new ArrayList<>( Arrays.asList(intArray[0], intArray[1], intArray[2]));
				ArrayList<Integer> Y = new ArrayList<>( Arrays.asList(intArray[3], intArray[4], intArray[5])); 		
				int count = intArray[6];

				System.out.println(X.get(0) + " " + X.get(1) + " " + X.get(2) + " " + 
					               Y.get(0) + " " + Y.get(1) + " " + Y.get(2) + " " + 
					               count + " $");

            }   

            bufferedReader.close();         
        }
        catch(Exception e) {
            System.out.println("Exception in reading file."); 
            e.printStackTrace(System.out);     
        }
        */


		// Serialize 
		/*
		Gson gson = new Gson();
		String jsonString = gson.toJson(edgeMap);

		Type type = new TypeToken< HashMap<Pair<ArrayList<Integer>, ArrayList<Integer>>, Integer> >(){}.getType();
		HashMap<Pair<ArrayList<Integer>, ArrayList<Integer>>, Integer> clonedMap;

		try {
			clonedMap = gson.fromJson(jsonString, type);
		} catch (IllegalStateException | JsonSyntaxException e) {
			//System.out.println("Exception in gson.fromJson");
			//e.printStackTrace(System.out);
		}

		//System.out.println(jsonString);
		*/
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