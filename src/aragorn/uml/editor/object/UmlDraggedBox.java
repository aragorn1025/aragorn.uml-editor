package aragorn.uml.editor.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

public class UmlDraggedBox extends UmlObject {

	private static Color DRAGGED_BOX_COLOR = Color.GREEN;

	private Point pressed_point;

	private Point current_point;

	public UmlDraggedBox() {
		reset();
	}

	@Override
	protected void drawBackground(Graphics g, Coordinate2D c) {
	}

	@Override
	protected void drawForeground(Graphics g, Coordinate2D c) {
		if (isNull())
			return;
		Rectangle.Double bounds = getBounds();
		g.setColor(UmlDraggedBox.DRAGGED_BOX_COLOR);
		Paintable.drawRectangle(g, c, new Point.Double(bounds.getX(), bounds.getY()), bounds.getWidth(), bounds.getHeight());
	}

	@Override
	public Rectangle.Double getBounds() {
		if (isNull())
			return new Rectangle.Double(0, 0, 0, 0);
		double x = Math.min(pressed_point.getX(), current_point.getX());
		double y = Math.min(pressed_point.getY(), current_point.getY());
		double w = pressed_point.getX() + current_point.getX() - x * 2;
		double h = pressed_point.getY() + current_point.getY() - y * 2;
		return new Rectangle.Double(x, y, w, h);
	}

	private boolean isNull() {
		return pressed_point == null || current_point == null || pressed_point.equals(current_point);
	}

	private void reset() {
		setPressedPoint(null);
		setCurrentPoint(null);
	}

	public void setCurrentPoint(Point current_point) {
		this.current_point = current_point;
	}

	public void setPressedPoint(Point pressed_point) {
		this.pressed_point = pressed_point;
	}
}