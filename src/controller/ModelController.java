package controller;

import model.Model;
import view.Space;

public class ModelController {
	protected static Model model;
	protected static Space space;
	
	public static Model getModel () {
		return model;
	}
	
	public static void setModel (Model model2) {
		model=model2;
	}
	
	public static Space getSpace() {
		return space;
	}
	
	public static void setSpace(Space space2) {
		space=space2;
	}
}
