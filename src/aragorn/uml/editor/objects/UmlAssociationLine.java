package aragorn.uml.editor.objects;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public class UmlAssociationLine extends UmlConnectionLine {

	public static final Paintable BUTTON_ICON = new LineSegment2D(new Point2D.Double(0, 5), new Point2D.Double(10, 5));

	public static final String NAME = "association line";

	public UmlAssociationLine(UmlBasicObject starting_object, UmlConnectionPort starting_connection_port, UmlBasicObject ending_object,
			UmlConnectionPort ending_connection_port) {
		super(starting_object, starting_connection_port, ending_object, ending_connection_port);
	}

	@Override
	protected Paintable getArrow(Double ending_point, MathVector2D parallel_vector, MathVector2D normal_vector) {
		return new LineSegment2D(MathVector2D.add(ending_point, parallel_vector.getNegative()), ending_point);
	}
}