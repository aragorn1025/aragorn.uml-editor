package aragorn.uml.editor.gui.submode;

import java.awt.event.ActionEvent;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlSubmode;

public class GroupSubmode extends UmlSubmode {

	public GroupSubmode(UmlCanvas parent) {
		super(parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getParent().group();
	}
}