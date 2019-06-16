package aragorn.uml.editor.gui.mode;

import java.awt.event.MouseEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.basic.Clazz;

public class ClazzMode extends UmlMode {

	private static final String NAME = "class";

	public ClazzMode(UmlCanvas parent) {
		super(parent, ClazzMode.NAME);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		addUmlBasicObject(new Clazz(event.getPoint()));
	}
}