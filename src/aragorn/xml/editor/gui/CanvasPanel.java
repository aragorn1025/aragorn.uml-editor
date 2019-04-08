package aragorn.xml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
class CanvasPanel extends GuiPanel {

	private int margin = 0;

	CanvasPanel() {
		super();
		setDefaultMargin(10);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.BLACK);
		addComponent(canvas, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(margin, 0, margin, margin));
	}

	@Override
	public void setDefaultMargin(int margin) {
		super.setDefaultMargin(0);
		this.margin = margin;
	}
}