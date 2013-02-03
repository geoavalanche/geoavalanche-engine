import java.util.List;

import org.geoserver.ows.KvpParser;
import org.geoserver.ows.util.KvpUtils;
import org.geotools.util.Version;

public class AgsOwsKvpSizeParser extends KvpParser {
	public AgsOwsKvpSizeParser(String service, String version) {
		super("size", AgsOwsExportSize.class);
		setService(service);
		setVersion(new Version(version));
	}

	@Override
	public Object parse(String value) throws Exception {
		List unparsed = KvpUtils.readFlat(value, KvpUtils.INNER_DELIMETER);
		if (unparsed.size() != 2) {
			return new AgsOwsExportSize(1024, 768);
		} else {
			int width = Integer.parseInt((String) unparsed.get(0));
			int height = Integer.parseInt((String) unparsed.get(1));
			return new AgsOwsExportSize(width, height);
		}
	}
}
