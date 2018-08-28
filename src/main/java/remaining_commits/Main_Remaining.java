package remaining_commits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.domain.Commit;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRemoteRepository;


public class Main_Remaining implements Study{
	
	public static void main (String [] args){
		new RepoDriller().start(new Main_Remaining());
	}

	public void execute() {
	
		ArrayList<String> exploring_commits = getCommits();
		new RepositoryMining()
		.in(GitRemoteRepository.singleProject("https://github.com/solettaproject/soletta"))
		.through(Commits.list(exploring_commits))
		//.through(Commits.all())
		.process(new Exploring_All(), new CSVFile("/home/karine/saidasRepodriller/questoesPesquisa/soletta/remaining/new/three_spaces_rc.csv"))
		.mine();
	}
	
	public ArrayList<String> getCommits(){
		ArrayList<String> commits = new ArrayList<String>();
		try{

			File file = new File("OFICIAL_RC_FH.csv");

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = "am";

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
