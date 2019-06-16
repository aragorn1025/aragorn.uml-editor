package aragorn.uml.editor.gui.button;

import java.awt.Point;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;
import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.ClazzMode;

@SuppressWarnings("serial")
public class ClazzButton extends UmlButton {

	private static final Paintable ICON = new Polyline2D(new Point.Double(0, 3), new Point.Double(0, 1), new Point.Double(8, 1), new Point.Double(8, 3),
			new Point.Double(0, 3), new Point.Double(0, 7), new Point.Double(8, 7), new Point.Double(8, 3), new Point.Double(8, 5), new Point.Double(0, 5));

	public ClazzButton(UmlCanvas canvas_area) {
		super(canvas_area, new ClazzMode(canvas_area), ClazzButton.ICON);
	}
}