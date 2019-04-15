package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;

public class UmlClass extends UmlBasicObject {

	protected UmlClass(Point reference_point) {
		super(reference_point);
	}

	public static final Paintable BUTTON_ICON = new Polyline2D(new Point2D.Double(0, 5), new Point2D.Double(0, 1), new Point2D.Double(8, 1), new Point2D.Double(8, 7),
			new Point2D.Double(0, 7), new Point2D.Double(0, 5), new Point2D.Double(8, 5), new Point2D.Double(8, 3), new Point2D.Double(0, 3));

	public static final String NAME = "class";

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		// TODO Auto-generated method stub
	}

	@Override
	public Double getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}