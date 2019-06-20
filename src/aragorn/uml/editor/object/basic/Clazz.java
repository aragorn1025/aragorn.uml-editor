package aragorn.uml.editor.object.basic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;
import aragorn.uml.editor.object.UmlBasicObject;

public class Clazz extends UmlBasicObject {

	/** The default size of the object. It is strongly recommend to set the height as the multiple of 3. */
	private final static Dimension DEFAULT_SIZE = new Dimension(84, 90);

	private final static Point DEFAULT_NAME_LOCATION = new Point(30, 20);

	private Clazz(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public Clazz(Point point) {
		this(point.getX(), point.getY(), Clazz.DEFAULT_SIZE.getWidth(), Clazz.DEFAULT_SIZE.getHeight());
	}

	@Override
	public void drawBackground(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		Paintable.fillRectangle(g, c, new Point2D.Double(bounds.getX(), bounds.getY()), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	public void drawForeground(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		double[] x = new double[2];
		double[] y = new double[4];
		for (int i = 0; i < x.length; i++) {
			x[i] = bounds.getX() + bounds.getWidth() * i / (x.length - 1);
		}
		for (int i = 0; i < y.length; i++) {
			y[i] = bounds.getY() + bounds.getHeight() * i / (y.length - 1);
		}
		Polyline2D icon = new Polyline2D();
		icon.addPoint(new Point2D.Double(x[0], y[1]));
		icon.addPoint(new Point2D.Double(x[1], y[1]));
		icon.addPoint(new Point2D.Double(x[1], y[2]));
		icon.addPoint(new Point2D.Double(x[0], y[2]));
		icon.addPoint(new Point2D.Double(x[0], y[3]));
		icon.addPoint(new Point2D.Double(x[1], y[3]));
		icon.addPoint(new Point2D.Double(x[1], y[0]));
		icon.addPoint(new Point2D.Double(x[0], y[0]));
		icon.addPoint(new Point2D.Double(x[0], y[2]));
		icon.draw(g, c);
		g.drawString(getName(), (int) (getLocation().getX() + DEFAULT_NAME_LOCATION.getX()), (int) (getLocation().getY() + DEFAULT_NAME_LOCATION.getY()));
		super.drawForeground(g, c);
	}

	@Override
	public boolean isSurround(Point2D.Double point) {
		Rectangle2D.Double bounds = getBounds();
		return (point.getX() >= bounds.getMinX() && point.getX() <= bounds.getMaxX() && point.getY() >= bounds.getMinY() && point.getY() <= bounds.getMaxY());
	}
}