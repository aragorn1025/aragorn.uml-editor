package aragorn.uml.editor.object.line;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;
import aragorn.uml.editor.object.UmlLineObject;
import aragorn.uml.editor.object.UmlPort;
import aragorn.util.MathVector2D;

public class CompositionLine extends UmlLineObject {

	public static final Paintable BUTTON_ICON = new Polyline2D(new Point2D.Double(10, 5), new Point2D.Double(4, 5), new Point2D.Double(2, 3), new Point2D.Double(0, 5),
			new Point2D.Double(2, 7), new Point2D.Double(4, 5));

	public static final String NAME = "composition line";

	public CompositionLine(UmlPort starting_port, UmlPort ending_port) {
		super(starting_port, ending_port);
	}

	@Override
	protected Paintable getArrow(Double ending_point, MathVector2D parallel_vector, MathVector2D normal_vector) {
		MathVector2D negative_parallel_vector = parallel_vector.getNegative();
		MathVector2D negative_parallel_vector_half = negative_parallel_vector.getScalarMultiply(0.5);
		MathVector2D normal_vector_half = normal_vector.getScalarMultiply(0.5);

		Polyline2D val = new Polyline2D();
		val.addPoint(MathVector2D.add(ending_point, negative_parallel_vector));
		val.addPoint(MathVector2D.add(ending_point, negative_parallel_vector_half, normal_vector_half));
		val.addPoint(ending_point);
		val.addPoint(MathVector2D.add(ending_point, negative_parallel_vector_half, normal_vector_half.getNegative()));
		val.addPoint(MathVector2D.add(ending_point, negative_parallel_vector));
		return val;
	}
}