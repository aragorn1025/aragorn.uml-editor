package aragorn.uml.editor.object;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public abstract class UmlLineObject implements Drawable {

	/** The minimum length of the segment. It is strongly recommend to set it as the multiple of 2. */
	private static final int MIN_SEGMENT_LENGTH = 16;

	private UmlPort starting_port;

	private UmlPort ending_port;

	protected UmlLineObject(UmlPort starting_port, UmlPort ending_port) {
		this.starting_port = starting_port;
		this.ending_port = ending_port;
	}

	@Override
	public final void drawBackground(Graphics g, Coordinate2D c) {
	}

	@Override
	public final void drawForeground(Graphics g, Coordinate2D c) {
		getArrow().draw(g, c);
		getBody().draw(g, c);
	}

	private Paintable getArrow() {
		Point2D.Double starting_point = starting_port.getLocation();
		Point2D.Double ending_point = ending_port.getLocation();
		MathVector2D parallel_vector = new MathVector2D(starting_point, ending_point);
		MathVector2D parallel_unit_vector = parallel_vector.getScalarMultiply(1 / parallel_vector.getLength());
		MathVector2D normal_unit_vector = new MathVector2D(-parallel_unit_vector.getY(), parallel_unit_vector.getX());
		return getArrow(ending_point, parallel_unit_vector.getScalarMultiply(MIN_SEGMENT_LENGTH), normal_unit_vector.getScalarMultiply(MIN_SEGMENT_LENGTH));
	}

	protected abstract Paintable getArrow(Point2D.Double ending_point, MathVector2D parallel_vector, MathVector2D normal_vector);

	private Paintable getBody() {
		Point2D.Double starting_point = starting_port.getLocation();
		Point2D.Double ending_point = ending_port.getLocation();
		MathVector2D parallel_vector = new MathVector2D(starting_point, ending_point);
		MathVector2D parallel_unit_vector = parallel_vector.getScalarMultiply(1 / parallel_vector.getLength());
		return new LineSegment2D(starting_point, MathVector2D.add(parallel_vector, parallel_unit_vector.getScalarMultiply(MIN_SEGMENT_LENGTH).getNegative()));
	}

	@Override
	public Rectangle2D.Double getBounds() {
		Rectangle2D.Double arrow_bounds = getArrow().getBounds();
		Rectangle2D.Double tail_bounds = getBody().getBounds();
		double x_min = Math.min(arrow_bounds.getMinX(), tail_bounds.getMinX());
		double x_max = Math.max(arrow_bounds.getMaxX(), tail_bounds.getMaxX());
		double y_min = Math.min(arrow_bounds.getMinY(), tail_bounds.getMinY());
		double y_max = Math.max(arrow_bounds.getMaxY(), tail_bounds.getMaxY());
		return new Rectangle2D.Double(x_min, y_min, x_max - x_min, y_max - y_min);
	}
}