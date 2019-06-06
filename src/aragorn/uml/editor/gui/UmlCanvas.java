package aragorn.uml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import aragorn.math.geometry.Paintable;
import aragorn.uml.editor.object.UmlBasicObject;
import aragorn.uml.editor.object.UmlLineObject;
import aragorn.uml.editor.object.basic.CompositeObject;

@SuppressWarnings("serial")
public
class UmlCanvas extends Canvas {

	private ArrayList<UmlLineObject> uml_connection_lines = new ArrayList<>();

	private ArrayList<UmlBasicObject> uml_basic_objects = new ArrayList<>();

	private ArrayList<UmlBasicObject> selected_uml_basic_objects = new ArrayList<>();

	private UmlMode mouse_adapter = null;

	private Rectangle2D.Double dragged_block = null;

	private int depth_counter = -1;

	private UmlFrame parent;

	UmlCanvas(UmlFrame parent) {
		super();
		this.parent = parent;
		setBackground(Color.BLACK);
	}

	public void addSelectedUmlBasicObjects(UmlBasicObject uml_basic_object) {
		uml_basic_object.setSelected(true);
		selected_uml_basic_objects.add(uml_basic_object);
	}

	public void addUmlBasicObject(UmlBasicObject uml_basic_object) {
		if (uml_basic_object != null) {
			uml_basic_object.setDepth(depth_counter);
			depth_counter = uml_basic_object.getDepth() - 1;
			uml_basic_objects.add(uml_basic_object);
			repaint();
		}
	}

	public void addUmlConnectionLine(UmlLineObject uml_connection_line) {
		if (uml_connection_line != null) {
			uml_connection_lines.add(uml_connection_line);
			repaint();
		}
	}

	public void clearSelectedUmlBasicObjects() {
		for (int i = 0; i < selected_uml_basic_objects.size(); i++) {
			selected_uml_basic_objects.get(i).setSelected(false);
		}
		selected_uml_basic_objects.clear();
	}

	public UmlFrame getParent() {
		return parent;
	}

	public UmlBasicObject getSelectedUmlBasicObject(int index) {
		return selected_uml_basic_objects.get(index);
	}

	public int getSelectedUmlObjectsNumber() {
		return selected_uml_basic_objects.size();
	}

	public UmlBasicObject getUmlBasicObject(int index) {
		return uml_basic_objects.get(index);
	}

	public int getUmlObjectsNumber() {
		return uml_basic_objects.size();
	}

	public void group() {
		if (selected_uml_basic_objects.size() == 0)
			return;
		CompositeObject composite_object = new CompositeObject(selected_uml_basic_objects);
		for (int i = 0; i < selected_uml_basic_objects.size(); i++) {
			uml_basic_objects.remove(selected_uml_basic_objects.get(i));
		}
		uml_basic_objects.add(composite_object);
		addSelectedUmlBasicObjects(composite_object);
		repaint();
	}

	@Override
	public final void paint(Graphics g) {
		Collections.sort(uml_basic_objects, Collections.reverseOrder());
		for (UmlBasicObject uml_object : uml_basic_objects) {
			g.setColor(Color.BLACK);
			uml_object.drawBackground(g, null);
			g.setColor(Color.WHITE);
			uml_object.draw(g, null);
		}
		g.setColor(Color.WHITE);
		for (UmlLineObject uml_object : uml_connection_lines) {
			uml_object.draw(g, null);
		}
		if (dragged_block == null)
			return;
		g.setColor(Color.GREEN);
		Paintable.drawRectangle(g, null, new Point2D.Double(dragged_block.getX(), dragged_block.getY()), dragged_block.getWidth(), dragged_block.getHeight());
	}

	void setCanvasMouseAdapter(UmlMode mouse_adapter) {
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
		clearSelectedUmlBasicObjects();
		repaint();
	}

	public void setDraggedBlock(Rectangle2D.Double dragged_block) {
		this.dragged_block = dragged_block;
	}

	public void ungroup() {
		if (selected_uml_basic_objects.size() != 1 || !selected_uml_basic_objects.get(0).isUngroupable())
			return;
		CompositeObject group = (CompositeObject) selected_uml_basic_objects.get(0);
		uml_basic_objects.remove(group);
		selected_uml_basic_objects.clear();
		for (UmlBasicObject sub_object : group.getSubObjects()) {
			uml_basic_objects.add(sub_object);
			selected_uml_basic_objects.add(sub_object);
		}
		repaint();
	}
}