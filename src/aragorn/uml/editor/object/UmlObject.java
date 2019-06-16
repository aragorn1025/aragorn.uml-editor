package aragorn.uml.editor.object;

import java.awt.Color;
import java.awt.Graphics;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

public abstract class UmlObject implements Paintable {

	private static final Color BACKGROUND_COLOR = Color.BLACK;

	private static final Color FOREGROUND_COLOR = Color.WHITE;

	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}

	public static Color getForegroundColor() {
		return FOREGROUND_COLOR;
	}

	@Override
	public final void draw(Graphics g, Coordinate2D c) {
		g.setColor(BACKGROUND_COLOR);
		drawBackground(g, c);
		g.setColor(FOREGROUND_COLOR);
		drawForeground(g, c);
	}

	protected abstract void drawBackground(Graphics g, Coordinate2D c);

	protected abstract void drawForeground(Graphics g, Coordinate2D c);
}