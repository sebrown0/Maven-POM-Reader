package pom_reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class PomReader implements Version {
	private MavenXpp3Reader reader = new MavenXpp3Reader();
	private Model model;
	
	private String id;
	private String groupId;
	private String artifactId;
	private String version;

	//Get the info from pom.xml in the app's dir.
	public void createPomInfo() {
		createModel(getFileReader("pom.xml"));
		mapInfo();
	}
	
	public void createPomInfo(String pathToPom) {	
		createModel(getFileReader(getSanitisedPath(pathToPom)));
		mapInfo();
	}
	
	private String getSanitisedPath(String pathToPom) {
		String res = null;
		if(pathToPom != null && pathToPom.length() > 7) {
			var pathLen = pathToPom.length();
			if(pathToPom.substring(pathLen-7).equals("pom.xml")) {
				res = pathToPom;
			}else {
				if(pathToPom.substring(pathLen-1).equals("\\")) {					
					res = pathToPom+="pom.xml"; 
				}
			}
		}
		return res;
	}
	
	private Optional<FileReader> getFileReader(String pathToPom) {
		Optional<FileReader> fileReader = Optional.empty();
		try {
			fileReader = Optional.ofNullable(new FileReader(pathToPom));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileReader;
	}

	private void createModel(Optional<FileReader> fileReader) {
		fileReader.ifPresent(fr -> {
			try {
				model = reader.read(fr);
			} catch (IOException | XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});		
	}

	private void mapInfo() {
		if(model != null) {
			id = model.getId();			
			groupId =model.getGroupId();
			artifactId = model.getArtifactId();
			version = model.getVersion();
		}
	}
	
	@Override
	public String getVersion() {		
		return version;
	}
	

	public String getArtifactId() {		
		return artifactId;
	}

	@Override
	public String toString() {
		return "MavenReader [id=" + id + ", groupId=" + groupId + ", artifactId=" + artifactId + ", version=" + version
				+ "]";
	}

}
