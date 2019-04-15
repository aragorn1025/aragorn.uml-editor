package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;

public class UmlGeneralizationLine extends UmlConnectionLine {

	protected UmlGeneralizationLine(CONNECTION_TYPE connection_type, Point starting_point, Point ending_point) {
		super(connection_type, starting_point, ending_point);
	}

	public static final Paintable BUTTON_ICON = new Polyline2D(new Point2D.Double(8, 4), new Point2D.Double(2, 4), new Point2D.Double(2, 2), new Point2D.Double(0, 4),
			new Point2D.Double(2, 6), new Point2D.Double(2, 4));

	public static final String NAME = "generalization line";

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