package org.geoserver.ows.arcgisserver;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.geoserver.config.GeoServer;
import org.geoserver.ows.Response;
import org.geoserver.platform.Operation;
import org.geoserver.platform.ServiceException;

public class AgsOwsCapabilitiesResponse extends Response {

	private GeoServer geoServer;	
	
	public AgsOwsCapabilitiesResponse(final GeoServer geoServer) {
		super(AgsOwsCapabilitiesResponse.class);
		this.geoServer = geoServer;
		System.out.println("\n\nAgsOwsCapabilitiesResponse ----");
	}

	@Override
	public String getMimeType(Object value, Operation operation) throws ServiceException {
		System.out.println("\n\nAgsOwsCapabilitiesResponse.getMimeType() ----");
		return "application/xml";
	}

	@Override
	public void write(Object value, OutputStream output, Operation operation) throws IOException, ServiceException {
		System.out.println("\n\nAgsOwsCapabilitiesResponse.write() ----");
		String responseStr = "Static content";
		OutputStreamWriter writer = new OutputStreamWriter(output);
		writer.write(responseStr);
		writer.flush();
	}
}
