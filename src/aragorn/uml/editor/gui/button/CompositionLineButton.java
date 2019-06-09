package aragorn.uml.editor.gui.button;

import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.CompositionLineMode;
import aragorn.uml.editor.object.line.CompositionLine;

@SuppressWarnings("serial")
public class CompositionLineButton extends UmlButton {

	public CompositionLineButton(UmlCanvas canvas_area) {
		super(canvas_area, new CompositionLineMode(canvas_area), CompositionLine.BUTTON_ICON);
	}
}