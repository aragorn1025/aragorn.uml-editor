package aragorn.uml.editor.gui.button;

import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.ClazzMode;
import aragorn.uml.editor.object.basic.Clazz;

@SuppressWarnings("serial")
public class ClazzButton extends UmlButton {

	public ClazzButton(UmlCanvas canvas_area) {
		super(canvas_area, new ClazzMode(canvas_area), Clazz.BUTTON_ICON);
	}
}