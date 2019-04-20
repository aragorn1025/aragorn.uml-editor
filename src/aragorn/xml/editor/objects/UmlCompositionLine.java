package aragorn.xml.editor.objects;

import java.awt.geom.Point2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;
import aragorn.util.MathVector2D;

public class UmlCompositionLine extends UmlConnectionLine {

	public static final Paintable BUTTON_ICON = new Polyline2D(new Point2D.Double(10, 5), new Point2D.Double(4, 5), new Point2D.Double(2, 3), new Point2D.Double(0, 5),
			new Point2D.Double(2, 7), new Point2D.Double(4, 5));

	public static final String NAME = "composition line";

	protected UmlCompositionLine(UmlBasicObject starting_object, UmlConnectionPort starting_connection_port, UmlBasicObject ending_object,
			UmlConnectionPort ending_connection_port) {
		super(starting_object, starting_connection_port, ending_object, ending_connection_port);
	}

	@Override
	protected Paintable getEndingArrow(MathVector2D parallel_vector, MathVector2D normal_vector) {
		MathVector2D negative_parallel_vector = parallel_vector.getNegative();
		MathVector2D negative_parallel_vector_half = negative_parallel_vector.getScalarMultiply(0.5);
		MathVector2D negative_parallel_vector_quarter = negative_parallel_vector.getScalarMultiply(0.25);
		MathVector2D normal_vector_half = normal_vector.getScalarMultiply(0.5);

		Polyline2D val = new Polyline2D();
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector));
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_half));
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_quarter, normal_vector_half));
		val.addPoint(getEndingPoint());
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_quarter, normal_vector_half.getNegative()));
		val.addPoint(MathVector2D.add(getEndingPoint(), negative_parallel_vector_half));
		return val;
	}

	@Override
	protected Paintable getStartingArrow(MathVector2D parallel_vector, MathVector2D normal_vector) {
		return null; // TODO should be implements
	}
}