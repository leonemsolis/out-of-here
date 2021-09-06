package com.doublew.outofhere.ScreenContent;

import com.doublew.outofhere.Buttons.HelpBallButton;
import com.doublew.outofhere.Support.SRS;


public class HelpMechanism {
	
	private HelpBallButton helpBallButton;
	
	public HelpMechanism() {
		float shadowIndent = 3f;
		helpBallButton = new HelpBallButton(SRS.gameWidth / 2f, 24 + (SRS.gameHeight - 24) / 2, 20f, shadowIndent);
	}
	
	public void update(float delta) {
		
	}
	
	public HelpBallButton getButton() {
		return helpBallButton;
	}
}
