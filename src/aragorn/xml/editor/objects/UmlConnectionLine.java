package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public abstract class UmlConnectionLine implements UmlObject {

	/** The minimum length of the segment. It is strongly recommend to set it as the multiple of 2. */
	private static final int MIN_SEGMENT_LENGTH = 4;

	private Point starting_point;

	private Point ending_point;

	@SuppressWarnings("unused")
	private UmlConnectionPort starting_connection_port;

	private UmlConnectionPort ending_connection_port;

	protected UmlConnectionLine(Point starting_point, Point ending_point, UmlConnectionPort starting_connection_port, UmlConnectionPort ending_connection_port) {
		this.starting_point = starting_point;
		this.ending_point = ending_point;
		this.starting_connection_port = starting_connection_port;
		this.ending_connection_port = ending_connection_port;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		// TODO
	}

	@Override
	public Rectangle2D.Double getBounds() {
		double x = Math.min(starting_point.getX(), ending_point.getX());
		double y = Math.min(starting_point.getY(), ending_point.getY());
		double w = Math.max(starting_point.getX(), ending_point.getX()) - x;
		double h = Math.max(starting_point.getY(), ending_point.getY()) - y;
		return new Rectangle2D.Double(x, y, w, h);
	}

	@SuppressWarnings("unused")
	private Paintable getEndArrow() {
		switch (ending_connection_port) {
			case TOP:
				return getEndArrow(new MathVector2D(0, 2 * UmlConnectionLine.MIN_SEGMENT_LENGTH), new MathVector2D(UmlConnectionLine.MIN_SEGMENT_LENGTH, 0));
			case BOTTOM:
				return getEndArrow(new MathVector2D(0, -2 * UmlConnectionLine.MIN_SEGMENT_LENGTH), new MathVector2D(-UmlConnectionLine.MIN_SEGMENT_LENGTH, 0));
			case RIGHT:
				return getEndArrow(new MathVector2D(-2 * UmlConnectionLine.MIN_SEGMENT_LENGTH, 0), new MathVector2D(0, UmlConnectionLine.MIN_SEGMENT_LENGTH));
			case LEFT:
				return getEndArrow(new MathVector2D(2 * UmlConnectionLine.MIN_SEGMENT_LENGTH, 0), new MathVector2D(0, -UmlConnectionLine.MIN_SEGMENT_LENGTH));
			default:
				throw new InternalError("Unknown error.");
		}
	}

	protected abstract Paintable getEndArrow(MathVector2D parallel_vector, MathVector2D normal_vector);

	protected Point2D.Double getEndingPoint() {
		return new Point2D.Double(ending_point.getX(), ending_point.getY());
	}
}