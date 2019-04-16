package aragorn.xml.editor.gui;

import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
class ButtonPanel extends GuiPanel {

	ButtonPanel(CanvasArea canvas_area) {
		super();
		setDefaultMargin(10);

		XmlButton[] buttons = new XmlButton[6];
		ButtonGroup group = new ButtonGroup();
		buttons[0] = new XmlButton.Select(canvas_area, new CanvasMouseAdapter.Select(canvas_area));
		buttons[1] = new XmlButton.AssociationLine(canvas_area, new CanvasMouseAdapter.AssociationLine(canvas_area));
		buttons[2] = new XmlButton.GeneralizationLine(canvas_area, new CanvasMouseAdapter.GeneralizationLine(canvas_area));
		buttons[3] = new XmlButton.CompositionLine(canvas_area, new CanvasMouseAdapter.CompositionLine(canvas_area));
		buttons[4] = new XmlButton.Class(canvas_area, new CanvasMouseAdapter.Class(canvas_area));
		buttons[5] = new XmlButton.UseCase(canvas_area, new CanvasMouseAdapter.UseCase(canvas_area));
		for (int i = 0; i < buttons.length; i++) {
			group.add(buttons[i]);
			addComponent(buttons[i], 0, i, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		}
		addComponent(new JPanel(), 0, buttons.length, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
}