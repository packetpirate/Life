package com.life.controls;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton next;
	public JButton getNextButton() { return this.next; }
	private JButton startStop;
	public JButton getStartStopButton() { return this.startStop; }
	private JButton clear;
	public JButton getClearButton() { return this.clear; }
	
	private JLabel generationText;
	public String getGenerationText() { return this.generationText.getText(); }
	public void setGenerationText(String text) { this.generationText.setText(text); }

	public ControlPanel() {
		next = new JButton("Next");
		startStop = new JButton("Start");
		clear = new JButton("Clear");
		
		generationText = new JLabel("Generations: 0");
		
		add(next);
		add(startStop);
		add(clear);
		add(generationText);
	}
}
