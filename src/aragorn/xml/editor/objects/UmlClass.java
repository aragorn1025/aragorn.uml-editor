package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;

public class UmlClass extends UmlBasicObject {

	public static final Paintable BUTTON_ICON = new UmlClass(new Point(0, 1), new Dimension(8, 6));

	public static final String NAME = "class";

	/** The default size of the object. It is strongly recommend to set the height as the multiple of 3. */
	private final static Dimension DEFAULT_SIZE = new Dimension(84, 90);

	public UmlClass(Point reference_point) {
		this(reference_point, UmlClass.DEFAULT_SIZE);
	}

	private UmlClass(Point reference_point, Dimension size) {
		super(reference_point, size);
	}

	@Override
	protected Paintable getIcon() {
		double[] x = new double[2];
		double[] y = new double[4];
		for (int i = 0; i < x.length; i++) {
			x[i] = getBounds().getX() + getBounds().getWidth() * i / (x.length - 1);
		}
		for (int i = 0; i < y.length; i++) {
			y[i] = getBounds().getY() + getBounds().getHeight() * i / (y.length - 1);
		}
		Polyline2D val = new Polyline2D();
		val.addPoint(new Point2D.Double(x[0], y[1]));
		val.addPoint(new Point2D.Double(x[1], y[1]));
		val.addPoint(new Point2D.Double(x[1], y[2]));
		val.addPoint(new Point2D.Double(x[0], y[2]));
		val.addPoint(new Point2D.Double(x[0], y[3]));
		val.addPoint(new Point2D.Double(x[1], y[3]));
		val.addPoint(new Point2D.Double(x[1], y[0]));
		val.addPoint(new Point2D.Double(x[0], y[0]));
		val.addPoint(new Point2D.Double(x[0], y[2]));
		return val;
	}

	@Override
	protected boolean isSurround(Point2D.Double point) {
		if (point.getX() < getBounds().getMinX() || point.getX() > getBounds().getMaxX())
			return false;
		if (point.getY() < getBounds().getMinY() || point.getY() > getBounds().getMaxY())
			return false;
		return true;
	}
}