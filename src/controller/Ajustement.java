package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Ajustement extends ModelController implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		model.adjust(space);
	}
}