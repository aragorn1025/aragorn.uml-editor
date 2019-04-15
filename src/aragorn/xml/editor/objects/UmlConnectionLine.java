package aragorn.xml.editor.objects;

import java.awt.Point;

abstract class UmlConnectionLine implements UmlObject {

	@SuppressWarnings("unused")
	private CONNECTION_TYPE connection_type;

	@SuppressWarnings("unused")
	private Point starting_point;

	@SuppressWarnings("unused")
	private Point ending_point;

	static enum CONNECTION_TYPE {
		VERTICAL,
		HORIZONTAL;
	}

	protected UmlConnectionLine(CONNECTION_TYPE connection_type, Point starting_point, Point ending_point) {
		this.connection_type = connection_type;
		this.starting_point = starting_point;
		this.ending_point = ending_point;
	}
}