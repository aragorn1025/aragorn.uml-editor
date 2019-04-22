package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;

public class UmlClass extends UmlBasicObject {

	public static final Paintable BUTTON_ICON = new UmlClass(0, 1, 8, 6);

	public static final String NAME = "class";

	/** The default size of the object. It is strongly recommend to set the height as the multiple of 3. */
	private final static Dimension DEFAULT_SIZE = new Dimension(84, 90);

	private UmlClass(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public UmlClass(Point point) {
		this(point.getX(), point.getY(), UmlClass.DEFAULT_SIZE.getWidth(), UmlClass.DEFAULT_SIZE.getHeight());
	}

	@Override
	public void drawBackground(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		Paintable.fillRectangle(g, c, new Point2D.Double(bounds.getX(), bounds.getY()), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	protected void drawBody(Graphics g, Coordinate2D c) {
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
	}

	@Override
	protected boolean isSurround(Point2D.Double point) {
		Rectangle2D.Double bounds = getBounds();
		return (point.getX() >= bounds.getMinX() && point.getX() <= bounds.getMaxX() && point.getY() >= bounds.getMinY() && point.getY() <= bounds.getMaxY());
	}
}