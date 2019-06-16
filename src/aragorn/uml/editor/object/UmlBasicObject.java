package aragorn.uml.editor.object;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;

public abstract class UmlBasicObject extends UmlObject implements Comparable<UmlBasicObject> {

	private static final int MIN_DEPTH = 0;

	private static final int MAX_DEPTH = 99;

	private double x;

	private double y;

	private double width;

	private double height;

	private boolean selected = false;

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
	protected void drawForeground(Graphics g, Coordinate2D c) {
		if (isSelected() && !isUngroupable()) {
			drawConnectPort(g, c);
		}
	}

	private void drawConnectPort(Graphics g, Coordinate2D c) {
		getConnectionPort(UmlPortDirection.TOP).draw(g, c);
		getConnectionPort(UmlPortDirection.LEFT).draw(g, c);
		getConnectionPort(UmlPortDirection.BOTTOM).draw(g, c);
		getConnectionPort(UmlPortDirection.RIGHT).draw(g, c);
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}

	UmlPort getConnectionPort(UmlPortDirection port_direction) {
		switch (port_direction) {
			case TOP:
				return new UmlPort(x + width / 2.0, y);
			case LEFT:
				return new UmlPort(x, y + height / 2.0);
			case BOTTOM:
				return new UmlPort(x + width / 2.0, y + height);
			case RIGHT:
				return new UmlPort(x + width, y + height / 2.0);
			default:
				throw new InternalError("Unknown error.");
		}
	}

	public UmlPortDirection getCorrespondingConnectPort(Point point) {
		if (!isSurround(point))
			return null;
		double t = (point.getX() - x) / width;
		if (t <= 0.5) {
			if (point.getY() <= y + t * height)
				return UmlPortDirection.TOP;
			if (point.getY() > y + (1 - t) * height)
				return UmlPortDirection.BOTTOM;
			return UmlPortDirection.LEFT;
		} else {
			if (point.getY() <= y + (1 - t) * height)
				return UmlPortDirection.TOP;
			if (point.getY() > y + t * height)
				return UmlPortDirection.BOTTOM;
			return UmlPortDirection.RIGHT;
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

	public boolean isSelected() {
		return selected;
	}

	public boolean isSurround(Point point) {
		return isSurround(new Point2D.Double(point.getX(), point.getY()));
	}

	public abstract boolean isSurround(Point2D.Double point);

	public boolean isSurroundedBy(Rectangle2D.Double bounds) {
		return (x >= bounds.getMinX() && x + width <= bounds.getMaxX() && y >= bounds.getMinY() && y + height <= bounds.getMaxY());
	}

	public boolean isUngroupable() {
		return false;
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

	protected void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
}