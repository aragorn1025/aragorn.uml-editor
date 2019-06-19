package aragorn.uml.editor.object;

import java.awt.Color;
import java.awt.Graphics;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

public interface Drawable extends Paintable {

	public static final Color BACKGROUND_COLOR = Color.BLACK;

	public static final Color FOREGROUND_COLOR = Color.WHITE;

	@Override
	public default void draw(Graphics g, Coordinate2D c) {
		g.setColor(BACKGROUND_COLOR);
		drawBackground(g, c);
		g.setColor(FOREGROUND_COLOR);
		drawForeground(g, c);
	}

	public void drawBackground(Graphics g, Coordinate2D c);

	public void drawForeground(Graphics g, Coordinate2D c);
}