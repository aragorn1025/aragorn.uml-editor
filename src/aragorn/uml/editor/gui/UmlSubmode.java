package aragorn.uml.editor.gui;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import aragorn.uml.editor.object.UmlBasicObject;

public abstract class UmlSubmode implements ActionListener {

	private UmlCanvas parent;

	protected UmlSubmode(UmlCanvas parent) {
		this.parent = parent;
	}

	protected void echo(String message, int message_type) {
		parent.getParent().echo(message, message_type);
	}

	protected UmlBasicObject getSelectedUmlBasicObject(int index) {
		return parent.getSelectedUmlBasicObject(index);
	}

	protected int getSelectedUmlObjectsNumber() {
		return parent.getSelectedUmlObjectsNumber();
	}

	protected void group() {
		parent.group();
	}

	protected String showInputDialog(String message, String title, int message_type) {
		return JOptionPane.showInputDialog(parent.getParent(), message, title, message_type);
	}

	protected void ungroup() {
		parent.ungroup();
	}
}