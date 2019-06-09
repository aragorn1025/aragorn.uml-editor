package aragorn.uml.editor.gui;

import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import aragorn.gui.GuiPanel;
import aragorn.uml.editor.gui.button.AssociationLineButton;
import aragorn.uml.editor.gui.button.ClazzButton;
import aragorn.uml.editor.gui.button.CompositionLineButton;
import aragorn.uml.editor.gui.button.GeneralizationLineButton;
import aragorn.uml.editor.gui.button.SelectButton;
import aragorn.uml.editor.gui.button.UseCaseButton;

@SuppressWarnings("serial")
class ButtonPanel extends GuiPanel {

	ButtonPanel(UmlCanvas canvas_area) {
		setDefaultMargin(10);

		UmlButton[] buttons = new UmlButton[6];
		ButtonGroup group = new ButtonGroup();
		buttons[0] = new SelectButton(canvas_area);
		buttons[1] = new AssociationLineButton(canvas_area);
		buttons[2] = new GeneralizationLineButton(canvas_area);
		buttons[3] = new CompositionLineButton(canvas_area);
		buttons[4] = new ClazzButton(canvas_area);
		buttons[5] = new UseCaseButton(canvas_area);
		for (int i = 0; i < buttons.length; i++) {
			group.add(buttons[i]);
			addComponent(buttons[i], 0, i, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		}
		addComponent(new JPanel(), 0, buttons.length, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}
}