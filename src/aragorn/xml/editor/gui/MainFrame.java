package aragorn.xml.editor.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiPanel;
import aragorn.xml.editor.objects.UmlObject;

@SuppressWarnings("serial")
public class MainFrame extends GuiFrame {

	private ButtonPanel button_panel = new ButtonPanel();

	private CanvasArea canvas_area = new CanvasArea(this);

	public MainFrame() {
		super(new Dimension(800, 450), false);

		setTitle("XML Editor");
		setJMenuBar(new MainMenuBar(this));

		int margin = 10;
		GuiPanel content_pane = new GuiPanel();
		content_pane.setDefaultMargin(0);
		content_pane.addComponent(button_panel, 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		content_pane.addComponent(canvas_area, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(margin, 0, margin, margin));
		setContentPane(content_pane);
	}

	void addUmlObject(UmlObject uml_object) {
		canvas_area.addUmlObject(uml_object);
	}

	XmlButton getSelectedButton() {
		return button_panel.getSelectedButton();
	}
}