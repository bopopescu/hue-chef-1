import java.util.*;
import java.io.File;
import java.io.IOException;

// Driver Program
public class Main {
	public static void main(String[] args) throws IOException {
		//System.out.println("OK!");
		File[] files = new File("./data/").listFiles();
		operateOnFiles(files);
		//String filePath = "./P1.jpg";
		//Utils.ImageBFS(filePath);
    }

	// Recursive
	public static void operateOnFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				operateOnFiles(file.listFiles()); 
			} else {
				//System.out.println("File: " + file.getName());
			}
		}
	}
}