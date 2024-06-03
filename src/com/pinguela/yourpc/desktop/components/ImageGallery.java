package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.pinguela.yourpc.desktop.actions.ChooseImageFileAction;
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
	private JPanel galleryPanel;

	private JButton previousImageButton;
	private JButton nextImageButton;

	private final PropertyChangeListener editableListener = (evt) -> {
		if (galleryPanel.getComponentCount() == 0) {
			return;
		}
		galleryPanel.getComponent(galleryPanel.getComponentCount() -1).setVisible(isEditable);
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

		galleryPanel = new JPanel();
		galleryPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		galleryPanel.setPreferredSize(new Dimension(300, 150));
		galleryPanel.setMinimumSize(new Dimension(10, 150));
		galleryPanel.setMaximumSize(new Dimension(32767, 150));
		galleryScrollPane.setViewportView(galleryPanel);

		JPanel panel = new JPanel();
		panel.setVisible(false);
		galleryPanel.setLayout(new BoxLayout(galleryPanel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(24, 148));
		galleryPanel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JButton btnNewButton = new JButton(new ChooseImageFileAction(this));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setPreferredSize(new Dimension(24, 24));
		btnNewButton.setMinimumSize(new Dimension(24, 24));
		btnNewButton.setMaximumSize(new Dimension(24, 24));
		panel.add(btnNewButton);

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
		galleryPanel.addContainerListener(new AutoResizer());

		// TODO: Extract actions into classes
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
			BufferedImage resizedImage = SwingUtils.resize(imageEntry.getImage(), galleryPanel);

			if (resizedImage == null) {
				return;
			}

			imageEntries.add(imageEntry);

			JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
			imageLabel.addMouseListener(imageMouseListener);
			imageLabel.setVisible(true);

			galleryPanel.add(imageLabel, galleryPanel.getComponentCount() -1);

			galleryPanel.revalidate();
			galleryPanel.repaint();
		});
	}

	public void addImages(List<ImageEntry> imageEntries) {
		for (ImageEntry entry : imageEntries) {
			addImage(entry);
		}
	}

	public void removeImageAt(int index) {

		if (index == galleryPanel.getComponentCount()-1) {
			throw new IllegalArgumentException("Cannot remove the component at the gallery panel's last index.");
		}

		imageEntries.remove(index);
		galleryPanel.remove(index);

		if (selectionIndex == index) {
			setSelection(index-1 < 0 ? index : index-1); 
		}
	}

	public void clearImages() {
		imageEntries.removeAll(imageEntries);
		for (int i = 0; i < galleryPanel.getComponentCount() -1; i++) {
			galleryPanel.remove(i);
		}
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
			int hGap = 10;
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
		
	}

}
