package aragorn.xml.editor.objects;

import java.awt.Point;
import java.awt.geom.Point2D;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public class UmlAssociationLine extends UmlConnectionLine {

	public static final Paintable BUTTON_ICON = new LineSegment2D(new Point2D.Double(0, 4), new Point2D.Double(8, 4));

	public static final String NAME = "association line";

	protected UmlAssociationLine(Point starting_point, Point ending_point, UmlConnectionPort starting_connection_port, UmlConnectionPort ending_connection_port) {
		super(starting_point, ending_point, starting_connection_port, ending_connection_port);
	}

	protected Paintable getEndArrow(MathVector2D parallel_vector, MathVector2D normal_vector) {
		return new LineSegment2D(MathVector2D.add(getEndingPoint(), parallel_vector.getNegative()), getEndingPoint());
	}
}