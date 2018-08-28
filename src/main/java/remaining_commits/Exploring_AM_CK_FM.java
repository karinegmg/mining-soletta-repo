package remaining_commits;

import java.text.SimpleDateFormat;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.domain.ModificationType;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class Exploring_AM_CK_FM implements CommitVisitor{

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {

		int typeModifify = 0;
		int typeAdd = 0;
		int typeRename = 0;
		int typeRemoved = 0;
		double part = 0;
		double safe = 0;
		double percentSafe, percentPart;

		for(Modification m: commit.getModifications()){
			if (m.getType().equals(ModificationType.MODIFY)) {
				typeModifify = typeModifify + 1;
				part = part + 1;
			}
			else if (m.getType().equals(ModificationType.ADD)) {
				typeAdd = typeAdd + 1;
				part = part + 1;

			} else if (m.getType().equals(ModificationType.RENAME)) {
				typeRename = typeRename + 1;
				safe = safe + 1;
			}
			else if (m.getType().equals(ModificationType.DELETE)) {
				typeRemoved = typeRemoved + 1;
				part = part + 1;
			}
			/** complete 

				new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()),
				m.getFileName(),
				m.getNewPath().replace(m.getFileName().toString(), ""),
				m.getType());
			 */
		}
		percentPart = part/(part+safe);
		percentSafe = safe/(part+safe);
		//total = Integer.parseInt(""+(part + safe));

		writer.write(commit.getHash(), "Kconfig", 
				new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()),
				/**	complete 
		    "---",
			"---",
			"---",**/
				typeModifify,
				typeAdd,
				typeRename,
				typeRemoved,
				safe,
				part,
				part + safe,
				commit.getModifications().size(),
				safe + "/"+ part,
				percentSafe,
				percentPart);
	}
}

