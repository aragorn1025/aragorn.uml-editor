package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public abstract class UmlBasicObject implements UmlObject {

	private Rectangle2D.Double bounds;

	private boolean selected = false;

	private double connection_port_icon_horizontal_gap = 0;

	private double connection_port_icon_vertical_gap = 0;

	private double connection_port_icon_length = 4;

	protected UmlBasicObject(Point reference_point, Dimension size) {
		this(new Rectangle2D.Double(reference_point.getX(), reference_point.getY(), size.getWidth(), size.getHeight()));
	}

	protected UmlBasicObject(Rectangle2D.Double bounds) {
		this.bounds = bounds;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		getIcon().draw(g, c);
		if (selected) {
			Paintable.fillRectangle(g, c, getConnectPortReferencePoint(UmlConnectionPort.TOP), connection_port_icon_length, connection_port_icon_length);
			Paintable.fillRectangle(g, c, getConnectPortReferencePoint(UmlConnectionPort.LEFT), connection_port_icon_length, connection_port_icon_length);
			Paintable.fillRectangle(g, c, getConnectPortReferencePoint(UmlConnectionPort.BOTTOM), connection_port_icon_length, connection_port_icon_length);
			Paintable.fillRectangle(g, c, getConnectPortReferencePoint(UmlConnectionPort.RIGHT), connection_port_icon_length, connection_port_icon_length);
		}
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return bounds;
	}

	protected double getConnectionPortIconLength() {
		return connection_port_icon_length;
	}

	public Point2D.Double getConnectPort(UmlConnectionPort connection_port) {
		switch (connection_port) {
			case TOP:
				return new Point2D.Double(bounds.getX() + bounds.getWidth() / 2.0, bounds.getY());
			case LEFT:
				return new Point2D.Double(bounds.getX(), bounds.getY() + bounds.getHeight() / 2.0);
			case BOTTOM:
				return new Point2D.Double(bounds.getX() + bounds.getWidth() / 2.0, bounds.getY() + bounds.getHeight());
			case RIGHT:
				return new Point2D.Double(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds.getHeight() / 2.0);
			default:
				throw new InternalError("Unknown error.");
		}
	}

	private Point2D.Double getConnectPortReferencePoint(UmlConnectionPort connection_port) {
		double length = connection_port_icon_length;
		MathVector2D gap_v_vector = new MathVector2D(0, connection_port_icon_vertical_gap);
		MathVector2D gap_h_vector = new MathVector2D(connection_port_icon_horizontal_gap, 0);
		switch (connection_port) {
			case TOP:
				return MathVector2D.add(getConnectPort(UmlConnectionPort.TOP), gap_v_vector, new MathVector2D(-0.5 * length, -length));
			case LEFT:
				return MathVector2D.add(getConnectPort(UmlConnectionPort.LEFT), gap_h_vector, new MathVector2D(-length, -length / 2.0));
			case BOTTOM:
				return MathVector2D.add(getConnectPort(UmlConnectionPort.BOTTOM), gap_v_vector.getNegative(), new MathVector2D(-length / 2.0, 0));
			case RIGHT:
				return MathVector2D.add(getConnectPort(UmlConnectionPort.RIGHT), gap_h_vector.getNegative(), new MathVector2D(0, -length / 2.0));
			default:
				throw new InternalError("Unknown error.");
		}
	}

	protected abstract Paintable getIcon();

	public UmlConnectionPort getReferenceConnectPort(Point point) {
		if (!isSurround(point))
			return null;
		double t = (point.getX() - bounds.getMinX()) / bounds.getWidth();
		if (t <= 0.5) {
			if (point.getY() <= bounds.getMinY() + t * bounds.getHeight())
				return UmlConnectionPort.TOP;
			if (point.getY() > bounds.getMinY() + (1 - t) * bounds.getHeight())
				return UmlConnectionPort.BOTTOM;
			return UmlConnectionPort.LEFT;
		} else {
			if (point.getY() <= bounds.getMinY() + (1 - t) * bounds.getHeight())
				return UmlConnectionPort.TOP;
			if (point.getY() > bounds.getMinY() + t * bounds.getHeight())
				return UmlConnectionPort.BOTTOM;
			return UmlConnectionPort.RIGHT;
		}
	}

	public boolean isIn(Rectangle2D.Double bounds) {
		return (this.bounds.getMinX() >= bounds.getMinX() && this.bounds.getMaxX() <= bounds.getMaxX() && this.getBounds().getMinY() >= bounds.getMinY()
				&& this.getBounds().getMaxY() <= bounds.getMaxY());
	}

	protected boolean isSelected() {
		return selected;
	}

	public boolean isSurround(Point point) {
		return isSurround(new Point2D.Double(point.getX(), point.getY()));
	}

	protected abstract boolean isSurround(Point2D.Double point);

	public boolean isUngroupable() {
		return false;
	}

	protected void setConnectionPortIconHorizontalGap(double connection_port_icon_horizontal_gap) {
		this.connection_port_icon_horizontal_gap = connection_port_icon_horizontal_gap;
	}

	protected void setConnectionPortIconVerticalGap(double connection_port_icon_vertical_gap) {
		this.connection_port_icon_vertical_gap = connection_port_icon_vertical_gap;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}