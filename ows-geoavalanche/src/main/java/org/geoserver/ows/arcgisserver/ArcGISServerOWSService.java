package org.geoserver.ows.arcgisserver;

import org.geoserver.config.GeoServer;


public class ArcGISServerOWSService {
	
	
	private GeoServer geoServer;

	public ArcGISServerOWSService(final GeoServer geoServer) {
		this.geoServer = geoServer;
		System.out.println("\n\n\nArcGISServerOWSService------------\n");
		
	}

	public AgsOwsExportResponse export(AgsOwsExportRequest request) {
		System.out.println("\n\n\nAgsOwsExportResponse------------\n");
		return new AgsOwsExportResponse();
	}

}
