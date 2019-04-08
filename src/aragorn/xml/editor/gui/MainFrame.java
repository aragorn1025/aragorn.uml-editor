package aragorn.xml.editor.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
public class MainFrame extends GuiFrame {

	public MainFrame() {
		super(new Dimension(800, 450), false);

		setTitle("XML Editor");
		setJMenuBar(new MainMenuBar(this));

		GuiPanel content_pane = new GuiPanel();
		content_pane.addComponent(new EditPanel(), 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		content_pane.addComponent(new CanvasPanel(), 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		setContentPane(content_pane);
	}
}