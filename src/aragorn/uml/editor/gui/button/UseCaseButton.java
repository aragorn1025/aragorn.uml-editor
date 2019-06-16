package aragorn.uml.editor.gui.button;

import java.awt.geom.Point2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;
import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.UseCaseMode;

@SuppressWarnings("serial")
public class UseCaseButton extends UmlButton {

	private static final Paintable ICON = new Oval(new Point2D.Double(4, 4), 8, 6);

	public UseCaseButton(UmlCanvas canvas_area) {
		super(canvas_area, new UseCaseMode(canvas_area), UseCaseButton.ICON);
	}
}