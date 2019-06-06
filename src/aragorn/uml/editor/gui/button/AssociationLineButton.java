package aragorn.uml.editor.gui.button;

import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.AssociationLineMode;
import aragorn.uml.editor.object.line.AssociationLine;

@SuppressWarnings("serial")
public class AssociationLineButton extends UmlButton {

	public AssociationLineButton(UmlCanvas canvas_area) {
		super(canvas_area, new AssociationLineMode(canvas_area), AssociationLine.NAME, AssociationLine.BUTTON_ICON);
	}
}