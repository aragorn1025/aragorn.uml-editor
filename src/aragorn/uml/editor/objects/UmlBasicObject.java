package aragorn.uml.editor.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.util.MathVector2D;

public abstract class UmlBasicObject implements Comparable<UmlBasicObject>, Paintable {

	private static final int MIN_DEPTH = 0;

	private static final int MAX_DEPTH = 99;

	private double x;

	private double y;

	private double width;

	private double height;

	private boolean selected = false;

	private double connection_port_horizontal_gap = 0;

	private double connection_port_vertical_gap = 0;

	private double connection_port_length = 4;

	private int depth;

	private String name = null;

	protected UmlBasicObject() {
		this(0, 0, 0, 0);
	}

	protected UmlBasicObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setDepth(UmlBasicObject.MIN_DEPTH);
	}

	@Override
	public int compareTo(UmlBasicObject compared_uml_basic_object) {
		return this.depth - compared_uml_basic_object.depth;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		drawBody(g, c);
		if (isSelected() && !isUngroupable()) {
			drawConnectPort(g, c);
		}
	}

	public abstract void drawBackground(Graphics g, Coordinate2D c);

	protected abstract void drawBody(Graphics g, Coordinate2D c);

	private void drawConnectPort(Graphics g, Coordinate2D c) {
		Paintable.fillRectangle(g, c, getConnectionPortReferencePoint(UmlConnectionPort.TOP), connection_port_length, connection_port_length);
		Paintable.fillRectangle(g, c, getConnectionPortReferencePoint(UmlConnectionPort.LEFT), connection_port_length, connection_port_length);
		Paintable.fillRectangle(g, c, getConnectionPortReferencePoint(UmlConnectionPort.BOTTOM), connection_port_length, connection_port_length);
		Paintable.fillRectangle(g, c, getConnectionPortReferencePoint(UmlConnectionPort.RIGHT), connection_port_length, connection_port_length);
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}

	Point2D.Double getConnectionPort(UmlConnectionPort connection_port) {
		switch (connection_port) {
			case TOP:
				return new Point2D.Double(x + width / 2.0, y);
			case LEFT:
				return new Point2D.Double(x, y + height / 2.0);
			case BOTTOM:
				return new Point2D.Double(x + width / 2.0, y + height);
			case RIGHT:
				return new Point2D.Double(x + width, y + height / 2.0);
			default:
				throw new InternalError("Unknown error.");
		}
	}

	private Point2D.Double getConnectionPortCenter(UmlConnectionPort connection_port) {
		switch (connection_port) {
			case TOP:
				return MathVector2D.add(getConnectionPort(connection_port), new MathVector2D(0, -connection_port_length / 2.0 + connection_port_vertical_gap));
			case LEFT:
				return MathVector2D.add(getConnectionPort(connection_port), new MathVector2D(-connection_port_length / 2.0 + connection_port_horizontal_gap, 0));
			case BOTTOM:
				return MathVector2D.add(getConnectionPort(connection_port), new MathVector2D(0, connection_port_length / 2.0 - connection_port_vertical_gap));
			case RIGHT:
				return MathVector2D.add(getConnectionPort(connection_port), new MathVector2D(connection_port_length / 2.0 - connection_port_horizontal_gap, 0));
			default:
				throw new InternalError("Unknown error.");
		}
	}

	protected double getConnectionPortLength() {
		return connection_port_length;
	}

	private Point2D.Double getConnectionPortReferencePoint(UmlConnectionPort connection_port) {
		return MathVector2D.add(getConnectionPortCenter(connection_port), MathVector2D.getScalarMultiply(new MathVector2D(1, 1), -connection_port_length / 2.0));
	}

	public UmlConnectionPort getCorrespondingConnectPort(Point point) {
		if (!isSurround(point))
			return null;
		double t = (point.getX() - x) / width;
		if (t <= 0.5) {
			if (point.getY() <= y + t * height)
				return UmlConnectionPort.TOP;
			if (point.getY() > y + (1 - t) * height)
				return UmlConnectionPort.BOTTOM;
			return UmlConnectionPort.LEFT;
		} else {
			if (point.getY() <= y + (1 - t) * height)
				return UmlConnectionPort.TOP;
			if (point.getY() > y + t * height)
				return UmlConnectionPort.BOTTOM;
			return UmlConnectionPort.RIGHT;
		}
	}

	public int getDepth() {
		return depth;
	}

	public Point2D.Double getLocation() {
		return new Point2D.Double(x, y);
	}

	public String getName() {
		return name;
	}

	protected boolean isSelected() {
		return selected;
	}

	public boolean isSurround(Point point) {
		return isSurround(new Point2D.Double(point.getX(), point.getY()));
	}

	protected abstract boolean isSurround(Point2D.Double point);

	public boolean isSurroundedBy(Rectangle2D.Double bounds) {
		return (x >= bounds.getMinX() && x + width <= bounds.getMaxX() && y >= bounds.getMinY() && y + height <= bounds.getMaxY());
	}

	public boolean isUngroupable() {
		return false;
	}

	protected void setConnectionPortIconHorizontalGap(double connection_port_horizontal_gap) {
		this.connection_port_horizontal_gap = connection_port_horizontal_gap;
	}

	protected void setConnectionPortIconVerticalGap(double connection_port_vertical_gap) {
		this.connection_port_vertical_gap = connection_port_vertical_gap;
	}

	public void setDepth(int depth) {
		int diff = UmlBasicObject.MAX_DEPTH - UmlBasicObject.MIN_DEPTH + 1;
		this.depth = ((depth - UmlBasicObject.MIN_DEPTH) % diff + diff) % diff + UmlBasicObject.MIN_DEPTH;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
}