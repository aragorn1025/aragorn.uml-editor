package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

abstract class UmlBasicObject implements UmlObject {

	private Rectangle2D.Double bounds;

	protected UmlBasicObject(Point reference_point, Dimension size) {
		this.bounds = new Rectangle2D.Double(reference_point.getX(), reference_point.getY(), size.getWidth(), size.getHeight());
	}

	protected abstract Paintable getIcon();

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		getIcon().draw(g, c);
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return bounds;
	}
}