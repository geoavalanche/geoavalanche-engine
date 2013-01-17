package org.geoserver.hellorest;

import java.util.ArrayList;
import java.util.List;
import org.geoserver.rest.AbstractResource;
import org.geoserver.rest.format.DataFormat;
import org.geoserver.rest.format.StringFormat;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;

public class HelloResource extends AbstractResource {

	@Override
	protected List<DataFormat> createSupportedFormats(Request request, Response response) {

		List<DataFormat> formats = new ArrayList<DataFormat>();
		formats.add(new StringFormat(MediaType.TEXT_PLAIN));

		return formats;
	}

	@Override
	public void handleGet() {
		// get the appropriate format
		DataFormat format = getFormatGet();

		// transform the string "Hello World" to the appropriate response
		getResponse().setEntity(format.toRepresentation("Hello World"));
	}
}