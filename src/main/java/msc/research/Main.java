package msc.research;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRemoteRepository;

import templates.Informations_Commits;


public class Main implements Study{
    
	public static void main (String [] args){
		new RepoDriller().start(new Main());
	}

	public void execute() {
		// TODO Auto-generated method stub
		ArrayList<String> listCommits = getCommits();
		String outPutFile = "/home/karine/repodriller/";

		new RepositoryMining()
		.in(GitRemoteRepository.singleProject("https://github.com/solettaproject/soletta"))
		//.through(Commits.all())
		.through(Commits.list(listCommits))
		.process(new Informations_Commits(), new CSVFile(outPutFile))
		.mine();
	}

	public ArrayList<String> getCommits(){
		ArrayList<String> commits = new ArrayList<String>();
		try{

			String fileName = "d_remove unused assets.csv";
			File file = new File(fileName);

			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			String line = "line";
			//String line = br.readLine();
			while(line != null){
				line = br.readLine();
				commits.add(line);
			}

		} catch (Exception e) {
			// TODO: handle exception			
			e.getMessage();
		}
		return commits;
	}
}
