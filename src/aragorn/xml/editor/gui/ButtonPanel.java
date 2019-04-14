package aragorn.xml.editor.gui;

import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
class ButtonPanel extends GuiPanel {

	private ButtonGroup group = new ButtonGroup();

	private XmlButton[] buttons = new XmlButton[] {	new XmlButton.Select(), new XmlButton.AssociationLine(), new XmlButton.GeneralizationLine(),
													new XmlButton.CompositionLine(), new XmlButton.Class(), new XmlButton.UseCase() };

	ButtonPanel() {
		super();
		setDefaultMargin(10);
		for (int i = 0; i < buttons.length; i++) {
			group.add(buttons[i]);
			addComponent(buttons[i], 0, i, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		}
		addComponent(new JPanel(), 0, buttons.length, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}

	XmlButton getSelectedButton() {
		for (XmlButton button : buttons) {
			if (button.isSelected()) {
				return button;
			}
		}
		return null;
	}
}