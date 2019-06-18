package aragorn.uml.editor.gui.mode;

import java.awt.event.MouseEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.line.CompositionLine;

public class CompositionLineMode extends UmlMode {

	private static final String NAME = "composition line";

	public CompositionLineMode(UmlCanvas parent) {
		super(parent, CompositionLineMode.NAME);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		super.mouseReleased(event);
		if (!isUmlConnectLineShouldBeSet())
			return;
		addUmlLineObject(new CompositionLine(getStartingPort(), getEndingPort()));
	}
}