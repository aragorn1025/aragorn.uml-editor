package aragorn.uml.editor.object;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

public class UmlPort extends UmlObject {

	public static Dimension DEFAULT_SIZE = new Dimension(4, 4);

	/* The x-axis value of the location of the port. */
	private double x;

	/* The y-axis value of the location of the port. */
	private double y;

	private double w;

	private double h;

	UmlPort() {
		this(0, 0, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
	}

	private UmlPort(double x, double y, double w, double h) {
		setLocation(x, y);
		this.w = w;
		this.h = h;
	}

	@Override
	protected void drawBackground(Graphics g, Coordinate2D c) {
	}

	@Override
	protected void drawForeground(Graphics g, Coordinate2D c) {
		Paintable.fillRectangle(g, c, new Point2D.Double(x - w / 2, y - h / 2), w, h);
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(x, y, w, h);
	}

	Point2D.Double getCenter() {
		return new Point2D.Double(x, y);
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
}