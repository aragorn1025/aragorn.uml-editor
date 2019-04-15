package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Paintable;

public class UmlAssociationLine extends UmlConnectionLine {

	protected UmlAssociationLine(CONNECTION_TYPE connection_type, Point starting_point, Point ending_point) {
		super(connection_type, starting_point, ending_point);
	}

	public static final Paintable BUTTON_ICON = new LineSegment2D(new Point2D.Double(0, 4), new Point2D.Double(8, 4));

	public static final String NAME = "association line";

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