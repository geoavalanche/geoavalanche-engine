import java.util.ArrayList;
import java.util.List;

import org.geoserver.catalog.LayerInfo;
import org.geoserver.config.GeoServer;
import org.geoserver.ows.KvpParser;
import org.geoserver.ows.util.KvpUtils;
import org.geoserver.ows.util.KvpUtils.Tokenizer;
import org.geotools.util.Version;

public class AgsOwsKvpLayersParser extends KvpParser {

	private GeoServer geoServer;

	public AgsOwsKvpLayersParser(String service, String version,
			GeoServer geoServer) {
		super("layers", List.class);
		setService(service);
		setVersion(new Version(version));
		this.geoServer = geoServer;
	}

	@Override
	public Object parse(String value) throws Exception {
		
		/*
		 * Syntax: [show | hide | include | exclude]:layerId1,layerId2
		 */
		Tokenizer outter_delimeter = new Tokenizer(":");
		List unparsed = KvpUtils.readFlat(value, outter_delimeter);
		boolean isInclude = true;
		if (unparsed.size() == 2) {
			if (((String) unparsed.get(0)).equalsIgnoreCase("show") == true) {
				isInclude = true;
			} else if (((String) unparsed.get(0)).equalsIgnoreCase("hide") == true) {
				isInclude = false;
			} else if (((String) unparsed.get(0)).equalsIgnoreCase("include") == true) {
				// TODO: implement later
			} else if (((String) unparsed.get(0)).equalsIgnoreCase("exclude") == true) {
				// TODO: implement later
			}
		}
		List<LayerInfo> layers = null;
		List requestedLayers = KvpUtils.readFlat((String) unparsed.get(1),
				KvpUtils.INNER_DELIMETER);
		if (isInclude == true) {
			layers = new ArrayList<LayerInfo>();
		} else {
			layers = this.geoServer.getCatalog().getLayers();
		}
		for (int i = 0; i < requestedLayers.size(); i++) {
			String layerName = (String) requestedLayers.get(i);
			if (layerName != null) {
				LayerInfo layerInfo = this.geoServer.getCatalog()
						.getLayerByName(layerName);
				if (layerInfo != null) {
					if (isInclude == true) {
						// include layers being requested
						layers.add(layerInfo);
					} else {
						// exclude layers being requested
						layers.remove(layerInfo);
					}
				}
			}
		}
		return layers;
	}
}