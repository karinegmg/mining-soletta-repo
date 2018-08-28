package msc.research;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main_Get_Remaining_Commits{

	public static void main(String [] args) throws IOException{

		ArrayList<String> templates = new ArrayList<String>();
		ArrayList<String> dataset = new ArrayList<String>();

		BufferedWriter bw = new BufferedWriter(new FileWriter("output_ok/remaining_commits_final.csv"));

		File file = new File("input_test/new_templates.csv");
		FileReader fr = new FileReader(file);

		File fileData = new File("input_test/2300.csv");
		FileReader fReader = new FileReader(fileData);

		BufferedReader br = new BufferedReader(fr);
		BufferedReader bReader = new BufferedReader(fReader);

		try {
			//String line = "templates";
			String line = br.readLine();
			while(line != null){
				line = br.readLine();
				templates.add(line);
			}

			//line = "2300-commits";
			line = bReader.readLine();
			while(line != null){
				line = bReader.readLine();
				System.out.println(line);
				dataset.add(line);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dataset.removeAll(templates);

		for(int i = 0; i < dataset.size(); i++){
			//System.out.println(dataset.get(i));
			bw.write(dataset.get(i)+"\n");
		}
		
		br.close();
		bReader.close();
		bw.close();
	}
}
