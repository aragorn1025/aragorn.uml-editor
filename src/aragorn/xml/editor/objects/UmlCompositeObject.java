package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import aragorn.math.geometry.Paintable;

public class UmlCompositeObject extends UmlBasicObject {

	protected UmlCompositeObject(Point reference_point, Dimension size) {
		super(reference_point, size);
	}

	@Override
	protected Paintable getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isSurround(Double point) {
		// TODO Auto-generated method stub
		return false;
	}
}