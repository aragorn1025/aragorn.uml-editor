package aragorn.uml.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JToggleButton;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

@SuppressWarnings("serial")
public class UmlButton extends JToggleButton implements ActionListener {

	private static String encode(Color color) {
		return Integer.toHexString(color.getRGB()).substring(2);
	}

	private Color tool_tip_text_color = Color.DARK_GRAY;

	private String function_name;

	private Paintable icon;

	private UmlCanvas canvas_area;

	private UmlMode mouse_adapter;

	protected UmlButton(UmlCanvas canvas_area, UmlMode mouse_adapter, String function_name, Paintable icon) {
		this.canvas_area = canvas_area;
		this.mouse_adapter = mouse_adapter;
		this.function_name = function_name;
		this.icon = icon;

		setSize(50);
		setToolTipText(this.function_name);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		canvas_area.setCanvasMouseAdapter(mouse_adapter);
	}

	private Coordinate2D getFitCoordinate(int margin) {
		Dimension button_size = getSize();
		double unit = Math.min((button_size.width - 2.0 * margin) / icon.getBounds().getWidth(), (button_size.getHeight() - 2.0 * margin) / icon.getBounds().getHeight());
		double ox = button_size.getWidth() / 2.0 - unit * (icon.getBounds().getWidth() / 2.0 + icon.getBounds().getX());
		double oy = button_size.getHeight() / 2.0 - unit * (icon.getBounds().getHeight() / 2.0 + icon.getBounds().getY());
		return new Coordinate2D(new Point2D.Double(ox, oy), unit, -unit);
	}

	@Override
	protected final void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(isSelected() ? Color.BLACK : Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(isSelected() ? Color.WHITE : Color.BLACK);
		icon.draw(g, getFitCoordinate(Math.min(getWidth(), getHeight()) / 10));
	}

	private void setSize(int length) {
		Dimension size = new Dimension(length, length);
		setMinimumSize(size);
		setPreferredSize(size);
	}

	@Override
	public final void setToolTipText(String text) {
		super.setToolTipText("<html><p><font color=\"#" + encode(tool_tip_text_color) + "\" size=\"4\" face=\"SansSerif\">" + text + "</font></p></html>");
	}
}