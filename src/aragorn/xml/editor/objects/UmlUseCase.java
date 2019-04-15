package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;

public class UmlUseCase extends UmlBasicObject {

	protected UmlUseCase(Point reference_point) {
		super(reference_point);
	}

	public static final Paintable BUTTON_ICON = new Oval(new Point2D.Double(4, 4), 8, 6);

	public static final String NAME = "use case";

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		// TODO Auto-generated method stub
	}

	@Override
	public Double getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}