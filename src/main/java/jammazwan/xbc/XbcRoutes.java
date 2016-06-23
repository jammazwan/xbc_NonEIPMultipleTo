package jammazwan.xbc;

import org.apache.camel.builder.RouteBuilder;

public class XbcRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:xbc").to("mock:assert1").to("mock:assert2").to("file://./?fileName=file1.txt")
				.to("file://./?fileName=file2.txt").to("file://./?fileName=file3.txt");
	}
}
