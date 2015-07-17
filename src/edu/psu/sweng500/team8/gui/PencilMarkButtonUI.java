package edu.psu.sweng500.team8.gui;

import java.awt.Color;

import javax.swing.plaf.metal.MetalToggleButtonUI;

public class PencilMarkButtonUI extends MetalToggleButtonUI {

	@Override
	protected Color getSelectColor() {
		return Color.LIGHT_GRAY;
	}
}
