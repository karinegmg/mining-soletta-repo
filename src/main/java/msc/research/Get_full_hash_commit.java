package msc.research;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class Get_full_hash_commit implements CommitVisitor{

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		// TODO Auto-generated method stub
		try{
			File file = new File("rem_unused_assets.csv");

			FileReader fReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fReader);

			//String line = "";
			String line = br.readLine();
			
			while(line != null){
				line = br.readLine();

				if(commit.getHash().contains(line)){
					writer.write(commit.getHash());
				}
			}

			br.close();
		}catch (IOException e) {
			e.getMessage();
		}
	}

}
