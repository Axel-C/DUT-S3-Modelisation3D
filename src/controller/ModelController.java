package controller;

import view.Space;

public class ModelController {
	protected static Space space;
	
	public static Space getSpace() {
		return space;
	}
	
	public static void setSpace(Space space2) {
		space=space2;
	}
}
