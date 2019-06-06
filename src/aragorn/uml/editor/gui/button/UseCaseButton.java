package aragorn.uml.editor.gui.button;

import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.UseCaseMode;
import aragorn.uml.editor.object.basic.UseCase;

@SuppressWarnings("serial")
public class UseCaseButton extends UmlButton {

	public UseCaseButton(UmlCanvas canvas_area) {
		super(canvas_area, new UseCaseMode(canvas_area), UseCase.NAME, UseCase.BUTTON_ICON);
	}
}