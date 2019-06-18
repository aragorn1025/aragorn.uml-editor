package aragorn.uml.editor.object.line;

import java.awt.geom.Point2D.Double;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Paintable;
import aragorn.uml.editor.object.UmlLineObject;
import aragorn.uml.editor.object.UmlPort;
import aragorn.util.MathVector2D;

public class AssociationLine extends UmlLineObject {

	public AssociationLine(UmlPort starting_port, UmlPort ending_port) {
		super(starting_port, ending_port);
	}

	@Override
	protected Paintable getArrow(Double ending_point, MathVector2D parallel_vector, MathVector2D normal_vector) {
		return new LineSegment2D(MathVector2D.add(ending_point, parallel_vector.getNegative()), ending_point);
	}
}