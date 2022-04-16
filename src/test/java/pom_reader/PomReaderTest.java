/**
 * 
 */
package pom_reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PomReaderTest {	
		
	@Test
	void newPomReader() {
		PomReader reader = new PomReader();	
		assertTrue(reader != null);
	}
	
	@Test
	void getVersion_from_DefaultDir() {
		PomReader reader = new PomReader();
		reader.createPomInfo();
		
		assertTrue(reader.getVersion().length() > 0);
	}
	
	@Test
	void getArtifactId_from_externalPom_with_fullyQualifiedPath() {
		PomReader reader = new PomReader();
		reader.createPomInfo("C:\\Users\\Brown\\eclipse-workspace\\2021\\MavenPomReader\\pom.xml");
		
		assertEquals("MavenPomReader", reader.getArtifactId());
	}
	
	@Test
	void getArtifactId_from_externalPom_with_pomXmlMissing() {
		PomReader reader = new PomReader();
		reader.createPomInfo("C:\\Users\\Brown\\eclipse-workspace\\2021\\MavenPomReader\\");
		
		assertEquals("MavenPomReader", reader.getArtifactId());
	}
	
}
