package aragorn.uml.editor.object;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;

public abstract class UmlBasicObject implements Comparable<UmlBasicObject>, Drawable, Selectable {

	private static final int MIN_DEPTH = 0;

	private static final int MAX_DEPTH = 99;

	private static final int RIGHT = 0;

	private static final int TOP = 1;

	private static final int LEFT = 2;

	private static final int BOTTOM = 3;

	private double x;

	private double y;

	private double w;

	private double h;

	private int depth;

	private String name = null;

	private boolean selected = false;

	private UmlPort[] ports = new UmlPort[4];

	protected UmlBasicObject() {
		this(0, 0, 0, 0);
	}

	protected UmlBasicObject(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		setDepth(UmlBasicObject.MIN_DEPTH);
		setSize(w, h);
		for (int i = 0; i < ports.length; i++) {
			ports[i] = new UmlPort();
		}
		resetPorts();
	}

	@Override
	public int compareTo(UmlBasicObject compared_uml_basic_object) {
		return this.depth - compared_uml_basic_object.depth;
	}

	@Override
	public void drawForeground(Graphics g, Coordinate2D c) {
		if (isSelected() && !isUngroupable()) {
			drawPorts(g, c);
		}
	}

	private void drawPorts(Graphics g, Coordinate2D c) {
		for (UmlPort port : ports) {
			port.draw(g, c);
		}
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(x, y, w, h);
	}

	public UmlPort getCorrespondingPort(Point point) {
		if (!isSurround(point))
			return null;
		double t = (point.getX() - x) / w;
		if (t <= 0.5) {
			if (point.getY() <= y + t * h)
				return ports[UmlBasicObject.TOP];
			if (point.getY() > y + (1 - t) * h)
				return ports[UmlBasicObject.BOTTOM];
			return ports[UmlBasicObject.LEFT];
		} else {
			if (point.getY() <= y + (1 - t) * h)
				return ports[UmlBasicObject.TOP];
			if (point.getY() > y + t * h)
				return ports[UmlBasicObject.BOTTOM];
			return ports[UmlBasicObject.RIGHT];
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

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean isSurroundedBy(UmlDraggedBox dragged_box) {
		Rectangle2D.Double bounds = dragged_box.getBounds();
		return (x >= bounds.getMinX() && x + w <= bounds.getMaxX() && y >= bounds.getMinY() && y + h <= bounds.getMaxY());
	}

	public boolean isUngroupable() {
		return false;
	}

	private void resetPorts() {
		ports[UmlBasicObject.RIGHT].setLocation(x + w, y + h / 2.0);
		ports[UmlBasicObject.TOP].setLocation(x + w / 2.0, y);
		ports[UmlBasicObject.LEFT].setLocation(x, y + h / 2.0);
		ports[UmlBasicObject.BOTTOM].setLocation(x + w / 2.0, y + h);
	}

	public void setDepth(int depth) {
		int diff = UmlBasicObject.MAX_DEPTH - UmlBasicObject.MIN_DEPTH + 1;
		this.depth = ((depth - UmlBasicObject.MIN_DEPTH) % diff + diff) % diff + UmlBasicObject.MIN_DEPTH;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
		resetPorts();
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	protected void setSize(double width, double height) {
		this.w = width;
		this.h = height;
	}
}