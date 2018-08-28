package remaining_commits;

import java.text.SimpleDateFormat;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.domain.ModificationType;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class Exploring_All implements CommitVisitor{

	int spaceAM, spacesAM_CK, spacesAM_FM, spacesCK_FM, spaceCK, spaceFM, spacesAM_CK_FM = 0;

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		// TODO Auto-generated method stub

		//hash;date;author_name;author_email;amount_files;FM;CK;AM;kconfig_files;makefile_files;assets_files;spaces
		//count_add_file; count_mod_file; count_rem_file; count_rename_file;

		int assets, kconf, makef;
		int add_file, mod_file, rem_file, rename_file;
		int addLines, remLines;
		assets = 0;
		kconf = 0;
		makef = 0;
		boolean ass, kc, mf;
		ass = false;
		kc = false;
		mf = false;

		add_file = 0;
		mod_file = 0;
		rem_file = 0;
		rename_file = 0;
		addLines = 0;
		remLines = 0;
		/*
		spaceAM = 0;
		spaceCK = 0;
		spaceFM = 0;
		spacesAM_CK = 0;
		spacesAM_FM = 0;
		spacesCK_FM = 0;
		spacesAM_CK_FM = 0;
		 */

		for(Modification m: commit.getModifications()){

			if(!m.getFileName().contains("Kconfig") && !m.getFileName().contains("Makefile")){
				ass = true;
				assets = assets + 1;

			} 
			if(m.getFileName().contains("Kconfig")){
				kc = true;
				kconf = kconf + 1;
			}
			if(m.getFileName().contains("Makefile")){
				mf = true;
				makef = makef + 1;
			}
			if(m.getType().equals(ModificationType.ADD) || m.getType().equals(ModificationType.COPY)){
				add_file = add_file + 1;
			}
			if(m.getType().equals(ModificationType.MODIFY)){
				mod_file = mod_file + 1;
			}
			if(m.getType().equals(ModificationType.DELETE)){
				rem_file = rem_file + 1;
			}
			if(m.getType().equals(ModificationType.RENAME)){
				rename_file = rename_file + 1;
			}
			addLines = addLines + m.getAdded();
			remLines = remLines + m.getRemoved();
		}
		if(ass && mf && kc){
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"yes", "yes", "yes", 
					kconf, makef, assets,
					"FM && CK && AM",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spacesAM_CK_FM = spacesAM_CK_FM + 1;
			sum_spaces_affect();
		}
		else if(ass && mf && !kc){
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"no", "yes", "yes", 
					kconf, makef, assets,
					"CK && AM",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spacesAM_CK = spacesAM_CK + 1;
			sum_spaces_affect();
		}
		else if(ass && !mf && !kc){

			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"no", "no", "yes", 
					kconf, makef, assets,
					"AM",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spaceAM = spaceAM + 1;
			sum_spaces_affect();
		}
		else if(!ass && mf && kc){
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"yes", "yes", "no", 
					kconf, makef, assets,
					"FM && CK",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spacesCK_FM = spacesCK_FM + 1;
			sum_spaces_affect();
		}
		else if(!ass && mf && !kc){

			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"no", "yes", "no", 
					kconf, makef, assets,
					"CK",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spaceCK = spaceCK + 1;
			sum_spaces_affect();
		}
		else if (!ass && !mf && kc){
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"yes", "no", "no", 
					kconf, makef, assets,
					"FM",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spaceFM = spaceFM + 1;
			sum_spaces_affect();
		}
		else if(ass && !mf && kc){
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"yes", "no", "yes", 
					kconf, makef, assets,
					"FM && AM",
					add_file, mod_file, rem_file, rename_file,
					addLines, remLines);
			spacesAM_FM = spacesAM_FM +1;
			sum_spaces_affect();
		}
		else {
			writer.write(commit.getHash(), 
					new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()), 
					commit.getAuthor().getName(), 
					commit.getAuthor().getEmail(),
					commit.getModifications().size(),
					"no", "no", "no", 
					kconf, makef, assets,
					"WHAT");
		}

	}

	public void sum_spaces_affect(){

		System.out.println(spaceAM +","+ spacesAM_CK +","+ spacesAM_FM +","+spacesCK_FM +","+ spaceCK+","+ spaceFM+","+ spacesAM_CK_FM);

	}

}


