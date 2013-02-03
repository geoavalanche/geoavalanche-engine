package org.geoserver.ows.arcgisserver;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.geoserver.ows.Response;
import org.geoserver.platform.Operation;
import org.geoserver.platform.ServiceException;

public class AgsOwsExportResponse extends Response {

	public AgsOwsExportResponse() {
		super(AgsOwsExportResponse.class);
	}

	@Override
	public String getMimeType(Object value, Operation operation)
			throws ServiceException {
		// to simplify the problem, always return image/png
		// but in your own ows service, you can decided based on what you
		// support
		// and what clients request
		return "image/png";
	}

	@Override
	public void write(Object value, OutputStream output, Operation operation)
			throws IOException, ServiceException {
		String responseStr = "Static content";
		OutputStreamWriter writer = new OutputStreamWriter(output);
		writer.write(responseStr);
		writer.flush();
	}
}

/*
private void write(Object value, OutputStream output, Operation operation)
     throws IOException, ServiceException {    
   
   AgsOwsExportResponse exportResponse = (AgsOwsExportResponse)value;
   AgsOwsExportRequest exportRequest 
       = (AgsOwsExportRequest)OwsUtils.parameter(operation.getParameters(), AgsOwsExportRequest.class);
   
   	// the main purpose here is to create mapContext {GraphicEnhancedMapContext} 
	// and pass it to map producer to generate the map
    // borrow the idea from GetMapResponse but omitted a lot details
        
     // requested image crs
     final CoordinateReferenceSystem imageSR = exportRequest.getImageSR();
     // requested layers
     final LayerInfo[] layers = exportRequest.getLayers();          
     // initialize mapContext
     this.mapContext = new GraphicEnhancedMapContext();    
     try {
       this.mapContext.setCoordinateReferenceSystem(imageSR);
     } catch(FactoryException e) {
       e.printStackTrace();
     } catch(TransformException e) {
       e.printStackTrace();
     }        
     // bbox and bbox crs
     final CoordinateReferenceSystem bboxSR = exportRequest.getBboxSR();
     final Envelope bbox = exportRequest.getBbox();
     if(bboxSR != null) {
       this.mapContext.setAreaOfInterest(bbox, bboxSR);
     } else {
       // TODO: should throw exception, no?
       this.mapContext.setAreaOfInterest(bbox, DefaultGeographicCRS.WGS84);
     }
     
     this.mapContext.setMapWidth(exportRequest.getWidth());
     this.mapContext.setMapHeight(exportRequest.getHeight());
     this.mapContext.setBgColor(exportRequest.getBgColor());
     this.mapContext.setTransparent(exportRequest.isTransparent());
     
     try {
       for (int i=0; i<layers.length; i++) {      
         final Style layerStyle = layers[i].getDefaultStyle().getStyle();        
         final MapLayer layer;                
         if(layers[i].getType().getCode() == LayerInfo.Type.VECTOR.getCode()) {
           FeatureSource<? extends FeatureType, ? extends Feature> featureSource;
           FeatureTypeInfo resource = (FeatureTypeInfo)layers[i].getResource();
           if(resource.getStore() == null || resource.getStore().getDataStore(null) == null) {
                   throw new IOException("");
               }
           Hints hints = new Hints(ResourcePool.REPROJECT, Boolean.valueOf(false));
           featureSource = resource.getFeatureSource(null, hints);
           
           layer = new FeatureSourceMapLayer(featureSource, layerStyle);
                     layer.setTitle(layers[i].getResource().getName());                    
                     // use default filter and version in DefaultQuery
                     final DefaultQuery definitionQuery = new DefaultQuery(featureSource.getSchema().getName().getLocalPart());                                      
                     layer.setQuery(definitionQuery);
                     mapContext.addLayer(layer);                    
         } else if(layers[i].getType().getCode() == LayerInfo.Type.RASTER.getCode()) {
           //
         }
         
         // default outputFormat to 'png' and mimeType to 'image/png'
         this.mapProducer = new AgsOwsMapProducer();
         this.mapProducer.setMapContext(this.mapContext);
         
         this.mapProducer.produceMap();
         this.mapProducer.writeTo(output);
       }
     } catch(Exception e) {
       e.printStackTrace();
     }
   }

*/