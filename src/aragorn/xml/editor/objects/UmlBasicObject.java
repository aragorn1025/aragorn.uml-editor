package aragorn.xml.editor.objects;

import java.awt.Point;

abstract class UmlBasicObject implements UmlObject {

	@SuppressWarnings("unused")
	private Point reference_point;

	protected UmlBasicObject(Point reference_point) {
		this.reference_point = reference_point;
	}
}