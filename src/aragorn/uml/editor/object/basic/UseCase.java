package aragorn.uml.editor.object.basic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;
import aragorn.uml.editor.object.UmlBasicObject;

public class UseCase extends UmlBasicObject {

	public static final Paintable BUTTON_ICON = new Oval(new Point2D.Double(4, 4), 8, 6);

	public static final String NAME = "use case";

	/** The default size of the object. It is strongly recommend to set the width and height as the multiple of 2. */
	private final static Dimension DEFAULT_SIZE = new Dimension(84, 48);

	private UseCase(double x, double y, double width, double height) {
		super(x, y, width, height);
		setConnectionPortIconHorizontalGap(width / 2 * (1 - Math.sqrt(1 - Math.pow(getConnectionPortLength() / height, 2))));
		setConnectionPortIconVerticalGap(height / 2 * (1 - Math.sqrt(1 - Math.pow(getConnectionPortLength() / width, 2))));
	}

	public UseCase(Point point) {
		this(point.getX(), point.getY(), UseCase.DEFAULT_SIZE.getWidth(), UseCase.DEFAULT_SIZE.getHeight());
	}

	@Override
	public void drawBackground(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		Paintable.fillOval(g, c, new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	protected void drawForeground(Graphics g, Coordinate2D c) {
		Rectangle2D.Double bounds = getBounds();
		Paintable.drawOval(g, c, new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()), bounds.getWidth(), bounds.getHeight());
		super.drawForeground(g, c);
	}

	@Override
	public boolean isSurround(Point2D.Double point) {
		Rectangle2D.Double bounds = getBounds();
		Oval icon = new Oval(new Point2D.Double(bounds.getCenterX(), bounds.getCenterY()), bounds.getWidth(), bounds.getHeight());
		return icon.isSurround(point);
	}
}