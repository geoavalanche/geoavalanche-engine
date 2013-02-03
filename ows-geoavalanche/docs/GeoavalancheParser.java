import java.util.List;

import org.geoserver.config.GeoServer;
import org.geoserver.ows.KvpParser;
import org.geotools.util.Version;


public class GeoavalancheParser extends KvpParser {

	private GeoServer geoServer;
		
	public GeoavalancheParser(String service, String version) {
		super("layers", List.class);
		this.setService(service);
		this.setVersion(new Version(version));

	}
	
	public GeoavalancheParser(String key, Class binding) {
		super(key, binding);

	}

	@Override
	public Object parse(String value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
