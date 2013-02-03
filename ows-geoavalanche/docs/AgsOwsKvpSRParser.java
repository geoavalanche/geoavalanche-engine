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

		CoordinateReferenceSystem crs = null;
		if (value != null && "".equalsIgnoreCase(value) == false) {
			try {
				// create CoordinateReferenceSystem based on wkid
				crs = CRS.decode("EPSG:" + value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return crs;
	}
}


/*

http://augusttown.blogspot.it/2010/01/extend-geoserver-with-customized-ows_7162.html

*/