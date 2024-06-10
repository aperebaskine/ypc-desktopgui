package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.OverlayLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.pinguela.yourpc.desktop.actions.ChooseImageFileAction;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.desktop.view.AbstractItemView;
import com.pinguela.yourpc.model.ImageEntry;

/**
 * TODO: Optimize gallery panel layout implementation
 */
public class ImageGallery 
extends JPanel 
implements YPCComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8552832685952898146L;

	protected static final String SELECTION_INDEX_PROPERTY = "selectionIndex";
	private Integer selectionIndex;

	protected static final String IS_EDITABLE_PROPERTY = "isEditable";
	private boolean isEditable = false;

	private ImageMouseListener imageMouseListener;

	private List<ImageEntry> imageEntries;

	private ImagePanel imagePanel;
	private JPanel galleryCenterPanel;
	private JPanel addButtonPanel;

	private JButton previousImageButton;
	private JButton nextImageButton;

	private final PropertyChangeListener editableListener = (evt) -> {
		addButtonPanel.setVisible(isEditable);
	};

	private final PropertyChangeListener selectionListener = (evt) -> {
		displaySelection((Integer) evt.getNewValue());
	};

	public ImageGallery() {
		initialize();
		postInitialize();
	}

	private void initialize() {

		setLayout(new BorderLayout(0, 0));

		imagePanel = new ImagePanel();
		add(imagePanel, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane galleryScrollPane = new JScrollPane();
		galleryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		galleryScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		southPanel.add(galleryScrollPane, BorderLayout.CENTER);
		
		JPanel galleryPanel = new JPanel();
		galleryScrollPane.setViewportView(galleryPanel);
		galleryPanel.setLayout(new BorderLayout(0, 0));
		
		galleryCenterPanel = new JPanel();
		galleryCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));
		galleryCenterPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		galleryCenterPanel.setPreferredSize(new Dimension(300, 150));
		galleryCenterPanel.setMinimumSize(new Dimension(10, 150));
		galleryCenterPanel.setMaximumSize(new Dimension(32767, 150));
		galleryPanel.add(galleryCenterPanel);

		addButtonPanel = new JPanel();
		addButtonPanel.setLayout(new BoxLayout(addButtonPanel, BoxLayout.X_AXIS));
		addButtonPanel.setVisible(false);
		addButtonPanel.setPreferredSize(new Dimension(32, 148));
		galleryPanel.add(addButtonPanel, BorderLayout.EAST);

		JButton addImageButton = new JButton(new ChooseImageFileAction(this));
		addImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addImageButton.setPreferredSize(new Dimension(24, 24));
		addImageButton.setMinimumSize(new Dimension(24, 24));
		addImageButton.setMaximumSize(new Dimension(24, 24));
		addButtonPanel.add(addImageButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(8);
		addButtonPanel.add(horizontalStrut);

		JPanel previousImageButtonPanel = new JPanel();
		previousImageButtonPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		southPanel.add(previousImageButtonPanel, BorderLayout.WEST);
		previousImageButtonPanel.setLayout(new BoxLayout(previousImageButtonPanel, BoxLayout.X_AXIS));

		previousImageButton = new JButton("");
		previousImageButton.setPreferredSize(new Dimension(24, 24));
		previousImageButton.setIcon(new ImageIcon(AbstractItemView.class.getResource("/nuvola/16x16/1700_back_next_back_next.png")));
		previousImageButtonPanel.add(previousImageButton);

		JPanel nextImageButtonPanel = new JPanel();
		nextImageButtonPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		southPanel.add(nextImageButtonPanel, BorderLayout.EAST);
		nextImageButtonPanel.setLayout(new BoxLayout(nextImageButtonPanel, BoxLayout.X_AXIS));

		nextImageButton = new JButton("");
		nextImageButton.setIcon(new ImageIcon(AbstractItemView.class.getResource("/nuvola/16x16/1749_forward_forward.png")));
		nextImageButton.setPreferredSize(new Dimension(24, 24));
		nextImageButtonPanel.add(nextImageButton);
	}

	private void postInitialize() {

		addPropertyChangeListener(IS_EDITABLE_PROPERTY, editableListener);
		addComponentListener(new FirstImageAutoDisplayer());

		imageMouseListener = new ImageMouseListener();
		imageEntries = new ArrayList<ImageEntry>();

		addPropertyChangeListener(SELECTION_INDEX_PROPERTY, selectionListener);
		galleryCenterPanel.addContainerListener(new AutoResizer());

		previousImageButton.addActionListener((e) -> {
			setSelection(selectionIndex-1);
		});

		nextImageButton.addActionListener((e) -> {
			setSelection(selectionIndex+1);
		});
	}

	public List<ImageEntry> getImages() {
		return imageEntries;
	}

	public void addImage(ImageEntry imageEntry) {

		EventQueue.invokeLater(() -> {
			imageEntries.add(imageEntry);
			galleryCenterPanel.add(new ImageLabelPanel(imageEntry));

			galleryCenterPanel.revalidate();
			galleryCenterPanel.repaint();
		});
	}

	public void addImages(List<ImageEntry> imageEntries) {
		for (ImageEntry entry : imageEntries) {
			addImage(entry);
		}
	}

	public void removeImageAt(int index) {

		imageEntries.remove(index);
		galleryCenterPanel.remove(index);

		if (selectionIndex == index) {
			setSelection(index-1 < 0 ? index : index-1); 
		}
	}

	public void clearImages() {
		imageEntries.removeAll(imageEntries);
		galleryCenterPanel.removeAll();
	}

	private void setSelection(Integer index) {

		if (index < 0 || index >= imageEntries.size()) {
			return;
		}

		Integer oldIndex = selectionIndex;
		selectionIndex = index;
		firePropertyChange(SELECTION_INDEX_PROPERTY, oldIndex, index);
	}

	private void displaySelection(int index) {
		imagePanel.setImage(imageEntries.get(index).getImage());
	}

	public void setEditable(boolean isEditable) {
		boolean old = this.isEditable;
		this.isEditable = isEditable;
		firePropertyChange(IS_EDITABLE_PROPERTY, old, isEditable);
	}

	private class ImageMouseListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {

			Component comp = (Component) e.getSource();

			if (comp.getComponentAt(e.getPoint()) == null) {
				return;
			}

			Container container = comp.getParent();

			int index = container.getComponentZOrder(comp);
			setSelection(index);
		}
	}

	private class FirstImageAutoDisplayer extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			if (imageEntries.size() > 0) {
				setSelection(0);
				removeComponentListener(this);
			}
		}
	}

	private static class AutoResizer implements ContainerListener {

		private void setSize(Container container) {
			int componentCount = container.getComponentCount();
			int hGap = ((FlowLayout) container.getLayout()).getHgap();
			Insets insets = container.getInsets();

			int width = insets.left + insets.right + (hGap * (componentCount + 1));
			for (Component component : container.getComponents()) {
				width += component.getWidth();
			}
			container.setPreferredSize(new Dimension(width, container.getHeight()));
			container.revalidate();
			container.repaint();
		}

		@Override
		public void componentRemoved(ContainerEvent e) {
			setSize(e.getContainer());
		}

		@Override
		public void componentAdded(ContainerEvent e) {
			e.getChild().addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e1) {
					setSize(e.getContainer());
				}
			});
		}
	};
	
	private class ImageLabelPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2195789467862228246L;
		
		private JButton deleteButton;
		private final PropertyChangeListener editableListener = (evt) -> {
			deleteButton.setVisible(isEditable);
		};
		
		private ImageLabelPanel(ImageEntry imageEntry) {
			
			BufferedImage resizedImage = SwingUtils.resize(imageEntry.getImage(), galleryCenterPanel);
			
			if (resizedImage == null) {
				throw new IllegalStateException("Cannot retrieve image.");
			}
			
			ImageGallery.this.addPropertyChangeListener(editableListener);
			addMouseListener(imageMouseListener);
			
			setLayout(new OverlayLayout(this));
			
			deleteButton = new JButton(Icons.DELETE_ICON);
			deleteButton.setAlignmentX(1.0f);
			deleteButton.setAlignmentY(0.0f);
			add(deleteButton);

			JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
			imageLabel.setAlignmentX(1.0f);
			imageLabel.setAlignmentY(0.0f);
			add(imageLabel);
		}
		
	}

}
