package templates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class GetFeatures implements CommitVisitor{

	int countFeatures = 0;
	int countCommit = 0;
	ArrayList<String> features = new ArrayList<String>();
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		String feature = "";
		//long releaseGabi = 1460775599000l;
		//from the first commit to the commit in the date: 2016/04/15 - 23:59

		//if(commit.getDate().getTimeInMillis() < releaseGabi){



		countCommit = countCommit + 1;
		for (Modification m: commit.getModifications()){

			String diff = m.getDiff();
			String[] lines = diff.split("\n");

			if(m.getOldPath().contains("Kconfig")){
				for(String line : lines){
					//	System.out.println(line);
					if(line.contains("+config ")){
						feature = line.substring(8);
						if(!existentFeature(feature)){
							//addFeature(feature);
							countFeatures = countFeatures + 1;
							writer.write("Commit " +countCommit,
									"feature "+ countFeatures,
									commit.getHash(), 
									new SimpleDateFormat("yyyy-MM-dd").format(commit.getDate().getTime()),
									feature);
						}
					}
				}
			}

		}


	}
	//}

	public void addFeature(String feature){

		String feat = feature;
		System.out.println(feat);
		getFeatures().add(feat);
	}

	public boolean existentFeature(String feature){

		boolean existent = false;
		for(int i  = 0; i < getFeatures().size(); i++){
			if(feature.equals(getFeatures().get(i))){
				existent = true;
			}

		}
		if(!existent){
			addFeature(feature);
		}

		return existent;
	}

	public ArrayList<String> getFeatures(){

		return features;
	}
}



