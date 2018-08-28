package templates;

import java.text.SimpleDateFormat;
import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class Informations_Commits implements CommitVisitor{
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		// TODO Auto-generated method stub

		String template_name, abv, evolution;
		int fm, ck, am;
		fm = 0;
		ck = 0;
		am = 0;
		boolean assets, kc, mf;
		assets = false;
		kc = false;
		mf = false;
		template_name = "Remove unused Assets";
		abv = "remua";
		evolution = "safe";

		for(Modification m: commit.getModifications()){

			//			if((m.getFileName().contains(".c") || m.getFileName().contains(".") || m.getFileName().contains("READ")) && (!m.getFileName().contains("Kconfing") || !m.getFileName().contains("Makefile")) ){
			//				assets = true;
			//			} 

			if(!m.getFileName().contains("Kconfig") && !m.getFileName().contains("Makefile") ){
				assets = true;
				am = am + 1;
			}

			if(m.getFileName().contains("Kconfig")){
				kc = true;
				fm = fm + 1;
			}
			if(m.getFileName().contains("Makefile")){
				mf = true;
				ck = ck + 1;
			}
		}
		if(assets && mf && kc){
			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"yes", "yes", "yes",
					fm, ck, am);
		}
		else if(assets && mf && !kc){
			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"no", "yes", "yes",
					fm, ck, am);
		}
		else if(assets && !mf && !kc){

			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"no", "no", "yes",
					fm, ck, am);
		}
		else if(!assets && mf && kc){
			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"yes", "yes", "no",
					fm, ck, am);

		}
		else if(!assets && mf && !kc){

			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"no", "yes", "no",
					fm, ck, am);
		}
		else if (!assets && !mf && kc){
			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"yes", "no", "no",
					fm, ck, am);
		}
		else if(assets && !mf && kc){
			writer.write(template_name, abv, evolution,
					commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					"yes", "no", "yes",
					fm, ck, am);

		}
	}

}
