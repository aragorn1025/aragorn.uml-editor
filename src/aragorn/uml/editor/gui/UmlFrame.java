package aragorn.uml.editor.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
public class UmlFrame extends GuiFrame {

	public UmlFrame() {
		this(10);
	}

	private UmlFrame(int margin) {
		super(new Dimension(800, 450), false);

		UmlCanvas canvas_area = new UmlCanvas(this);
		GuiPanel button_panel = new ButtonPanel(canvas_area);

		GuiPanel content_pane = new GuiPanel();
		content_pane.setDefaultMargin(0);
		content_pane.addComponent(button_panel, 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		content_pane.addComponent(canvas_area, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(margin, 0, margin, margin));

		setContentPane(content_pane);
		setTitle("UML Editor");
		setJMenuBar(new UmlMenuBar(this, canvas_area));
	}
}