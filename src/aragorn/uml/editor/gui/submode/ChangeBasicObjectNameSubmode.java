package aragorn.uml.editor.gui.submode;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlSubmode;

public class ChangeBasicObjectNameSubmode extends UmlSubmode {

	public ChangeBasicObjectNameSubmode(UmlCanvas parent) {
		super(parent);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (getSelectedUmlObjectsNumber()) {
			case 0:
				echo("No basic object is selected.", JOptionPane.WARNING_MESSAGE);
				break;
			case 1:
				String name = showInputDialog(getMessage(), "Change Name", JOptionPane.QUESTION_MESSAGE);
				if (name != null) {
					getSelectedUmlBasicObject(0).setName(name);
				}
				break;
			default:
				echo("Too many basic objects are selected.", JOptionPane.WARNING_MESSAGE);
				break;
		}
	}

	private String getMessage() {
		String name = getSelectedUmlBasicObject(0).getName();
		if (name == null) {
			return new String("Please enter the new name:");
		} else {
			return String.format("The name of the selected is %s.%nPlease enter the new name:", name);
		}
	}
}