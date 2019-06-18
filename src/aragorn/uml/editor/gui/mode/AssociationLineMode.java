package aragorn.uml.editor.gui.mode;

import java.awt.event.MouseEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.line.AssociationLine;

public class AssociationLineMode extends UmlMode {

	private static final String NAME = "association line";

	public AssociationLineMode(UmlCanvas parent) {
		super(parent, AssociationLineMode.NAME);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);
		if (!isUmlConnectLineShouldBeSet())
			return;
		addUmlLineObject(new AssociationLine(getStartingPort(), getEndingPort()));
	}
}