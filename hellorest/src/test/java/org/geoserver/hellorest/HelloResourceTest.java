package org.geoserver.hellorest;

import org.geoserver.test.GeoServerTestSupport;

public class HelloResourceTest extends GeoServerTestSupport {

	public void test() throws Exception {
		assertEquals("Hello World", getAsString("/rest/hello.txt"));
	}
}