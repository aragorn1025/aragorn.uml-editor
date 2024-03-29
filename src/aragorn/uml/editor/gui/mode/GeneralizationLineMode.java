package aragorn.uml.editor.gui.mode;

import java.awt.event.MouseEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.line.GeneralizationLine;

public class GeneralizationLineMode extends UmlMode {

	private static final String NAME = "generalization line";

	public GeneralizationLineMode(UmlCanvas parent) {
		super(parent, GeneralizationLineMode.NAME);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);
		if (!isUmlConnectLineShouldBeSet())
			return;
		getParent().addUmlLineObject(new GeneralizationLine(getStartingPort(), getEndingPort()));
	}
}