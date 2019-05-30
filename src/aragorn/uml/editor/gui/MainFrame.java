package aragorn.uml.editor.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
public class MainFrame extends GuiFrame {

	private static GuiPanel getButtonPanel(CanvasArea canvas_area) {
		GuiPanel button_panel = new GuiPanel();
		button_panel.setDefaultMargin(10);

		UmlButton[] buttons = new UmlButton[6];
		ButtonGroup group = new ButtonGroup();
		buttons[0] = new UmlButton.Select(canvas_area);
		buttons[1] = new UmlButton.AssociationLine(canvas_area);
		buttons[2] = new UmlButton.GeneralizationLine(canvas_area);
		buttons[3] = new UmlButton.CompositionLine(canvas_area);
		buttons[4] = new UmlButton.Class(canvas_area);
		buttons[5] = new UmlButton.UseCase(canvas_area);
		for (int i = 0; i < buttons.length; i++) {
			group.add(buttons[i]);
			button_panel.addComponent(buttons[i], 0, i, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		}
		button_panel.addComponent(new JPanel(), 0, buttons.length, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		return button_panel;
	}

	public MainFrame() {
		this(10);
	}

	private MainFrame(int margin) {
		super(new Dimension(800, 450), false);

		CanvasArea canvas_area = new CanvasArea(this);
		GuiPanel button_panel = MainFrame.getButtonPanel(canvas_area);

		GuiPanel content_pane = new GuiPanel();
		content_pane.setDefaultMargin(0);
		content_pane.addComponent(button_panel, 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		content_pane.addComponent(canvas_area, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(margin, 0, margin, margin));

		setContentPane(content_pane);
		setTitle("XML Editor");
		setJMenuBar(new MainMenuBar(this, canvas_area));
	}
}