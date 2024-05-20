package com.pinguela.yourpc.desktop.components;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXMultiThumbSlider;
import org.jdesktop.swingx.color.GradientThumbRenderer;
import org.jdesktop.swingx.color.GradientTrackRenderer;
import org.jdesktop.swingx.multislider.MultiThumbModel;
import org.jdesktop.swingx.multislider.Thumb;
import org.jdesktop.swingx.multislider.ThumbDataEvent;
import org.jdesktop.swingx.multislider.ThumbDataListener;

public class MultithumbSampleWindow {

	private static final int MIN_PRICE = 10;
	private static final int MAX_PRICE = 200;

	private JFrame frame;	
	private JPanel mainPane;
	private JPanel centerPane;
	private JLabel minLabel;
	private JXMultiThumbSlider<Color> thumbSlider = null;
	private JLabel maxLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultithumbSampleWindow window = new MultithumbSampleWindow();
					window.frame.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MultithumbSampleWindow() {
		initialize();
		// Para editar en WindowBuilder, requiere ser comentada:
		postInitialize();


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPane = new JPanel();
		frame.getContentPane().add(mainPane, BorderLayout.CENTER);
		mainPane.setLayout(new BorderLayout(0, 0));

		JPanel northPane = new JPanel();
		mainPane.add(northPane, BorderLayout.NORTH);
		northPane.setLayout(new BorderLayout(0, 0));

		centerPane = new JPanel();
		mainPane.add(centerPane, BorderLayout.CENTER);
		centerPane.setLayout(new BorderLayout(0, 0));

		minLabel = new JLabel("0");
		minLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPane.add(minLabel, BorderLayout.WEST);

		maxLabel = new JLabel("200");
		maxLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centerPane.add(maxLabel, BorderLayout.EAST);

		JPanel southPane = new JPanel();
		mainPane.add(southPane, BorderLayout.SOUTH);
		southPane.setLayout(new BorderLayout(0, 0));

	}

	private void postInitialize() {
		minLabel.setText(String.valueOf(MIN_PRICE));
		maxLabel.setText(String.valueOf(MAX_PRICE));

		thumbSlider = new JXMultiThumbSlider<Color>();

		thumbSlider.getModel().setMinimumValue(0f);
		thumbSlider.getModel().setMaximumValue(1.0f);

		thumbSlider.getModel().addThumb(0.0f, Color.ORANGE);
		thumbSlider.getModel().addThumb(1.0f, Color.BLUE);
		thumbSlider.setThumbRenderer(new GradientThumbRenderer());
		thumbSlider.setTrackRenderer(new GradientTrackRenderer());
		thumbSlider.getModel().addThumbDataListener(new ThumbDataListener() {

			@Override
			public void positionChanged(ThumbDataEvent evt) {
				MultiThumbModel<?> model = (MultiThumbModel<?>) evt.getSource();
				Thumb<?> th = evt.getThumb();
				int nuevaPosicion = Math.round(MIN_PRICE + th.getPosition()*(MAX_PRICE-MIN_PRICE)); 
				if (th==model.getThumbAt(0)) {
					// Cambio en el mínimo
					minLabel.setText(String.valueOf(nuevaPosicion));
				} else if (th==model.getThumbAt(1)) {
					// Cambio en el máximo
					maxLabel.setText(String.valueOf(nuevaPosicion));
				} else {
					throw new IllegalArgumentException("Unknown thumb");
				}
			}

			@Override
			public void thumbAdded(ThumbDataEvent evt) {
			}




			@Override
			public void thumbRemoved(ThumbDataEvent evt) {
			}

			@Override
			public void valueChanged(ThumbDataEvent evt) {
			}

		});
		centerPane.add(thumbSlider, BorderLayout.CENTER);
	}

}
