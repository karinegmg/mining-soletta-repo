package remaining_commits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class Exploring_AM_CK implements CommitVisitor{


	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		// TODO Auto-generated method stub


		for(Modification m: commit.getModifications()){
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()),
					commit.getAuthor().getName(),
					m.getType(),
					"PATH = "+m.getNewPath(),
					m.getDiff(),
					m.getSourceCode(),
					commit.getModifications().size());
		}




	}

}
