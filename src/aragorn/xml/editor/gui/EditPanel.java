package aragorn.xml.editor.gui;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
class EditPanel extends GuiPanel {

	EditPanel() {
		super();
		setDefaultMargin(10);

		addComponent(new XmlButton.Select(), 0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(new XmlButton.AssociationLine(), 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(new XmlButton.GeneralizationLine(), 0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(new XmlButton.CompositionLine(), 0, 3, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(new XmlButton.Class(), 0, 4, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(new XmlButton.UseCase(), 0, 5, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		addComponent(new JPanel(), 0, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
}