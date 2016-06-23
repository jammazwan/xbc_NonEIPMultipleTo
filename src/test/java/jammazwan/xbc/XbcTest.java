package jammazwan.xbc;

import java.io.File;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XbcTest extends CamelSpringTestSupport {

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

	@Test
	public void testXbc() throws Exception {
		// setup
		File file1 = new File("file1.txt");
		if (file1.exists()) {
			file1.delete();
		}
		File file2 = new File("file2.txt");
		if (file2.exists()) {
			file2.delete();
		}
		File file3 = new File("file3.txt");
		if (file3.exists()) {
			file3.delete();
		}
		Thread.sleep(1000);// time for deletes
		MockEndpoint mock1 = getMockEndpoint("mock:assert1");
		MockEndpoint mock2 = getMockEndpoint("mock:assert2");
		MockEndpoint mock3 = getMockEndpoint("mock:assert3");
		MockEndpoint mock4 = getMockEndpoint("mock:assert4");
		MockEndpoint mock5 = getMockEndpoint("mock:assert5");
		mock1.expectedMessageCount(1);
		mock2.expectedBodiesReceived("input");
		mock3.expectedFileExists("file1.txt");
		mock4.expectedFileExists("file2.txt");
		mock5.expectedFileExists("file3.txt");
		// run
		String reply = template.requestBody("direct:xbc", "input", String.class);
		// validate
		assertEquals("input", reply);
		mock1.assertIsSatisfied();
		mock2.assertIsSatisfied();
		mock3.assertIsSatisfied();
		mock4.assertIsSatisfied();
		mock5.assertIsSatisfied();
	}

}
