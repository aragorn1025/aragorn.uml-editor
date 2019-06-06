package aragorn.uml.editor.gui.button;

import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.GeneralizationLineMode;
import aragorn.uml.editor.object.line.GeneralizationLine;

@SuppressWarnings("serial")
public class GeneralizationLineButton extends UmlButton {

	public GeneralizationLineButton(UmlCanvas canvas_area) {
		super(canvas_area, new GeneralizationLineMode(canvas_area), GeneralizationLine.NAME, GeneralizationLine.BUTTON_ICON);
	}
}