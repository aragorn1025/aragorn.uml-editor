package aragorn.uml.editor.gui;

import java.awt.event.ActionListener;

public abstract class UmlSubmode implements ActionListener {

	private UmlCanvas parent;

	protected UmlSubmode(UmlCanvas parent) {
		this.parent = parent;
	}

	protected UmlCanvas getParent() {
		return parent;
	}
}