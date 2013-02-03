package org.geoserver.ows.arcgisserver;
import java.util.Map;

import org.geoserver.ows.KvpRequestReader;

public class AgsOwsExportRequestKvpReader extends KvpRequestReader {

	public AgsOwsExportRequestKvpReader() {
		super(AgsOwsExportRequest.class);
		System.out.println("\n\nAgsOwsExportRequestKvpReader ----");
	}

	@Override
	public Object createRequest() throws Exception {
		System.out.println("\n\nAgsOwsExportRequestKvpReader.createRequest() ----");
		AgsOwsExportRequest requestBean = new AgsOwsExportRequest();
		return requestBean;
	}

	@Override
	public Object read(Object request, Map kvp, Map rawKvp) throws Exception {
		System.out.println("\n\nAgsOwsExportRequestKvpReader.read ----");
		AgsOwsExportRequest exportRequest = (AgsOwsExportRequest) super.read(
				request, kvp, rawKvp);
		/*
		 * do any specific setting for AgsOwsExportRequest request bean instance
		 * - - based on kvp and rawKvp
		 */
		// TODO: here shall I set default value for certain parameters -
		// - if they are missing from kvp?
		return exportRequest;
	}
}

/*
 * “filter” is an optional attribute (through setFilter(Set<String> filter)) you
 * can set so that certain keys in kvp can be filtered out without setting the
 * its value to request bean instance.
 */