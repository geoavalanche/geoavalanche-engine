package org.geoserver.ows.arcgisserver;
import org.geoserver.ows.KvpParser;
import org.geotools.referencing.CRS;
import org.geotools.util.Version;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class AgsOwsKvpSRParser extends KvpParser {

	public AgsOwsKvpSRParser(String key, String service, String version) {
		super(key, CoordinateReferenceSystem.class);
		setService(service);
		setVersion(new Version(version));
	}

	@Override
	public Object parse(String value) throws Exception {
		LOGGER.info(this.getKey() + " parsed by AgsOwsKvpSRParser");
		CoordinateReferenceSystem crs = null;
		if (value != null && "".equalsIgnoreCase(value) == false) {
			try {
				// create CoordinateReferenceSystem based on wkid
				crs = CRS.decode("EPSG:" + value);
			} catch (Exception e) {
				LOGGER.warning("invalid parameter");
				e.printStackTrace();
			}
		}
		return crs;
	}
}