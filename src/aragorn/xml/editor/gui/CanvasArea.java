package aragorn.xml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import aragorn.math.geometry.Paintable;
import aragorn.xml.editor.objects.UmlBasicObject;
import aragorn.xml.editor.objects.UmlConnectionLine;
import aragorn.xml.editor.objects.UmlObject;

@SuppressWarnings("serial")
class CanvasArea extends Canvas {

	private ArrayList<UmlConnectionLine> uml_connection_lines = new ArrayList<>();

	private ArrayList<UmlBasicObject> uml_basic_objects = new ArrayList<>();

	private ArrayList<UmlBasicObject> selected_uml_basic_objects = new ArrayList<>();

	private CanvasMouseAdapter mouse_adapter = null;

	private Rectangle2D.Double dragged_block = null;

	CanvasArea() {
		super();
		setBackground(Color.BLACK);
	}

	void addUmlBasicObject(UmlBasicObject uml_basic_object) {
		if (uml_basic_object != null) {
			uml_basic_objects.add(uml_basic_object);
			repaint();
		}
	}

	UmlBasicObject getUmlBasicObject(int index) {
		return uml_basic_objects.get(index);
	}

	int getUmlObjectsNumber() {
		return uml_basic_objects.size();
	}

	@Override
	public final void paint(Graphics g) {
		g.setColor(Color.WHITE);
		for (UmlObject uml_object : uml_basic_objects) {
			uml_object.draw(g, null);
		}
		for (UmlObject uml_object : uml_connection_lines) {
			uml_object.draw(g, null);
		}
		if (dragged_block == null)
			return;
		g.setColor(Color.GREEN);
		Paintable.drawRectangle(g, null, new Point2D.Double(dragged_block.getX(), dragged_block.getY()), dragged_block.getWidth(), dragged_block.getHeight());
	}

	public void setDraggedBlock(Rectangle2D.Double dragged_block) {
		this.dragged_block = dragged_block;
	}

	void setCanvasMouseAdapter(CanvasMouseAdapter mouse_adapter) {
		if (mouse_adapter == null)
			return;
		if (this.mouse_adapter == mouse_adapter)
			return;
		if (this.mouse_adapter != null) {
			removeMouseListener(this.mouse_adapter);
			removeMouseMotionListener(this.mouse_adapter);
			this.mouse_adapter = null;
		}
		this.mouse_adapter = mouse_adapter;
		addMouseListener(this.mouse_adapter);
		addMouseMotionListener(this.mouse_adapter);
	}

	public void clearSelectedUmlBasicObjects() {
		for (int i = 0; i < selected_uml_basic_objects.size(); i++) {
			selected_uml_basic_objects.get(i).setSelected(false);
		}
		selected_uml_basic_objects.clear();
	}

	public void addSelectedUmlBasicObjects(UmlBasicObject uml_basic_object) {
		uml_basic_object.setSelected(true);
		selected_uml_basic_objects.add(uml_basic_object);
	}
}