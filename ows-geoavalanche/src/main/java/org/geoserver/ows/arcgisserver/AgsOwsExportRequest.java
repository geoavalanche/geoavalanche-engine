package org.geoserver.ows.arcgisserver;
import java.awt.Color;

import org.geoserver.catalog.LayerInfo;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Envelope;

public class AgsOwsExportRequest {

	private LayerInfo[] layers;
	private Envelope bbox;
	private AgsOwsExportSize size;
	private CoordinateReferenceSystem imageSR;
	private CoordinateReferenceSystem bboxSR;
	private boolean transparent;
	private Color bgColor = Color.WHITE;

	public AgsOwsExportRequest() {}

	public LayerInfo[] getLayers() {
		return layers;
	}

	public void setLayers(LayerInfo[] layers) {
		this.layers = layers;
	}

	public Envelope getBbox() {
		return bbox;
	}

	public void setBbox(Envelope bbox) {
		this.bbox = bbox;
	}

	public AgsOwsExportSize getSize() {
		return size;
	}

	public void setSize(AgsOwsExportSize size) {
		this.size = size;
	}

	public CoordinateReferenceSystem getImageSR() {
		return imageSR;
	}

	public void setImageSR(CoordinateReferenceSystem imageSR) {
		this.imageSR = imageSR;
	}

	public CoordinateReferenceSystem getBboxSR() {
		return bboxSR;
	}

	public void setBboxSR(CoordinateReferenceSystem bboxSR) {
		this.bboxSR = bboxSR;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
}