package com.life;

import java.applet.Applet;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import com.life.canvas.CellCanvas;
import com.life.controls.ControlPanel;

public class Life extends Applet implements ActionListener,Runnable {
	private static final long serialVersionUID = 1L;
	
	// The thread used when the user clicks the start/stop button.
	private static Thread gameThread;
	// How many milliseconds to sleep in between generations.
	private static final int sleepTime = 200;

	private CellCanvas canvas;
	private ControlPanel controls;
	
	public void init() {
		// Set the background color to gray.
		setBackground(new Color(0x999999));
		
		// Create the canvas and add a border.
		canvas = new CellCanvas();
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// Create the control panel and add a border.
		controls = new ControlPanel();
		controls.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// Create the layout.
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		
		// Create the constraints for the canvas.
		GridBagConstraints canvasConstraints = new GridBagConstraints();
		canvasConstraints.fill = GridBagConstraints.BOTH;
		canvasConstraints.gridx = GridBagConstraints.HORIZONTAL;
		canvasConstraints.gridy = 0;
		canvasConstraints.weightx = 1.0;
		canvasConstraints.weighty = 5.0;
		canvasConstraints.insets = new Insets(0,0,0,0);
		gridbag.setConstraints(canvas, canvasConstraints);
		add(canvas);
		
		// Create the constraints for the control panel.
		GridBagConstraints controlConstraints = new GridBagConstraints();
		controlConstraints.fill = GridBagConstraints.BOTH;
		controlConstraints.gridx = GridBagConstraints.HORIZONTAL;
		controlConstraints.gridy = 1;
		controlConstraints.weightx = 1.0;
		controlConstraints.weighty = 0.1;
		controlConstraints.insets = new Insets(6,0,0,0);
		gridbag.setConstraints(controls, controlConstraints);
		add(controls);
		
		// Set the main frame's properties.
		setSize(400,300);
		setVisible(true);
		
		// Add the action listener to each button in the control panel.
		controls.getNextButton().addActionListener(this);
		controls.getStartStopButton().addActionListener(this);
		controls.getClearButton().addActionListener(this);
	}
	
	public void nextGeneration() {
		canvas.nextGeneration();
		controls.setGenerationText("Generations: " + canvas.getGenerations());
		canvas.repaint();
	}
	
	public void clearCanvas() {
		canvas.clearCanvas();
		controls.setGenerationText("Generations: " + canvas.getGenerations());
		canvas.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Next")) {
			nextGeneration();
		}
		if(e.getActionCommand().equals("Start")) {
			controls.getStartStopButton().setText("Stop");
			controls.getNextButton().setEnabled(false);
			controls.getClearButton().setEnabled(false);
			start2();
		}
		if(e.getActionCommand().equals("Stop")) {
			controls.getStartStopButton().setText("Start");
			controls.getNextButton().setEnabled(true);
			controls.getClearButton().setEnabled(true);
			stop();
		}
		if(e.getActionCommand().equals("Clear")) {
			clearCanvas();
		}
	}
	
	public void start2() {
		if(gameThread == null) {
			gameThread = new Thread(this);
			gameThread.start();
		}
	}

	@Override
	public void run() {
		while(gameThread != null) {
			nextGeneration();
			try {
				Thread.sleep(sleepTime);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void stop() {
		gameThread = null;
	}
}
