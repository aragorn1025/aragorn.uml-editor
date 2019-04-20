package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public abstract class UmlConnectionLine implements UmlObject {

	/** The minimum length of the segment. It is strongly recommend to set it as the multiple of 2. */
	private static final int MIN_SEGMENT_LENGTH = 4;

	private static MathVector2D getNormalVector(UmlConnectionPort connection_port) {
		switch (connection_port) {
			case TOP:
				return new MathVector2D(-UmlConnectionLine.MIN_SEGMENT_LENGTH, 0);
			case BOTTOM:
				return getNormalVector(UmlConnectionPort.TOP).getNegative();
			case RIGHT:
				return getNormalVector(UmlConnectionPort.LEFT).getNegative();
			case LEFT:
				return new MathVector2D(0, UmlConnectionLine.MIN_SEGMENT_LENGTH);
			default:
				throw new InternalError("Unknown error.");
		}
	}

	private static MathVector2D getParallelVector(UmlConnectionPort connection_port) {
		switch (connection_port) {
			case TOP:
				return new MathVector2D(0, -2 * UmlConnectionLine.MIN_SEGMENT_LENGTH);
			case BOTTOM:
				return getParallelVector(UmlConnectionPort.TOP).getNegative();
			case RIGHT:
				return getParallelVector(UmlConnectionPort.LEFT).getNegative();
			case LEFT:
				return new MathVector2D(-2 * UmlConnectionLine.MIN_SEGMENT_LENGTH, 0);
			default:
				throw new InternalError("Unknown error.");
		}
	}

	private UmlConnectionPort starting_connection_port;

	private UmlConnectionPort ending_connection_port;

	private UmlBasicObject starting_object;

	private UmlBasicObject ending_object;

	protected UmlConnectionLine(UmlBasicObject starting_object, UmlConnectionPort starting_connection_port, UmlBasicObject ending_object,
			UmlConnectionPort ending_connection_port) {
		this.starting_object = starting_object;
		this.ending_object = ending_object;
		this.starting_connection_port = starting_connection_port;
		this.ending_connection_port = ending_connection_port;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		getStartingArrow().draw(g, c);
		// getConnectionPart().draw(g, c); // TODO
		getEndingArrow().draw(g, c);
	}

	@Override
	public Rectangle2D.Double getBounds() {
		Rectangle2D.Double[] bounds = new Rectangle2D.Double[] { getStartingArrow().getBounds(), getConnectionPart().getBounds(), getEndingArrow().getBounds() };
		double x_min = bounds[0].getMinX();
		double x_max = bounds[0].getMaxX();
		double y_min = bounds[0].getMinY();
		double y_max = bounds[0].getMaxY();
		for (int i = 1; i < bounds.length; i++) {
			x_min = Math.min(x_min, bounds[0].getMinX());
			x_max = Math.max(x_max, bounds[0].getMaxX());
			y_min = Math.min(y_min, bounds[0].getMinY());
			y_max = Math.max(y_max, bounds[0].getMaxY());
		}
		return new Rectangle2D.Double(x_min, y_min, x_max - x_min, y_max - y_min);
	}

	private Paintable getConnectionPart() {
		return null; // TODO connection part should be implement
	}

	private Paintable getEndingArrow() {
		return getEndingArrow(getParallelVector(ending_connection_port).getNegative(), getNormalVector(ending_connection_port).getNegative());
	}

	protected abstract Paintable getEndingArrow(MathVector2D parallel_vector, MathVector2D normal_vector);

	protected Point2D.Double getEndingPoint() {
		return ending_object.getConnectPort(ending_connection_port);
	}

	private Paintable getStartingArrow() {
		return getStartingArrow(getParallelVector(starting_connection_port), getNormalVector(starting_connection_port));
	}

	protected abstract Paintable getStartingArrow(MathVector2D parallel_vector, MathVector2D normal_vector);

	protected Point2D.Double getStartingPoint() {
		return starting_object.getConnectPort(starting_connection_port);
	}
}