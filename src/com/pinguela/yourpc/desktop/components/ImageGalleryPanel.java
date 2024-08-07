package com.pinguela.yourpc.desktop.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
import com.pinguela.yourpc.desktop.actions.NextImageAction;
import com.pinguela.yourpc.desktop.actions.PreviousImageAction;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.util.SwingUtils;
import com.pinguela.yourpc.model.ImageEntry;

@SuppressWarnings("serial")
public class ImageGalleryPanel 
extends JPanel 
implements YPCComponent {

	protected static final String SELECTION_INDEX_PROPERTY = "selectionIndex";
	private Integer selectionIndex;

	protected static final String IS_EDITABLE_PROPERTY = "isEditable";
	private boolean isEditable = false;

	private ThumbnailMouseListener imageMouseListener;

	private List<ImageEntry> imageEntries;
	private List<ThumbnailPanel> panels;

	private ImagePanel imagePanel;
	private JPanel galleryPanel;
	private JPanel addPanel;

	private JButton previousImageButton;
	private JButton nextImageButton;

	public ImageGalleryPanel() {
		initialize();
		postInitialize();
	}

	private void initialize() {

		setLayout(new BorderLayout(0, 0));

		imagePanel = new ImagePanel(new Dimension(640, 480));
		add(imagePanel, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel previousImageButtonPanel = new JPanel();
		previousImageButtonPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		southPanel.add(previousImageButtonPanel);
		previousImageButtonPanel.setLayout(new BoxLayout(previousImageButtonPanel, BoxLayout.X_AXIS));

		JScrollPane galleryScrollPane = new JScrollPane();
		galleryScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		southPanel.add(galleryScrollPane);

		galleryPanel = new JPanel();
		galleryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));
		galleryPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		galleryPanel.setPreferredSize(new Dimension(300, 150));
		galleryPanel.setMinimumSize(new Dimension(10, 150));
		galleryPanel.setMaximumSize(new Dimension(32767, 150));
		galleryScrollPane.setViewportView(galleryPanel);

		addPanel = new JPanel();
		addPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
		addPanel.setVisible(false);
		southPanel.add(addPanel);

		JButton addImageButton = new JButton(new ChooseImageFileAction(this));
		addImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addImageButton.setPreferredSize(new Dimension(24, 24));
		addPanel.add(addImageButton);

		previousImageButton = new JButton(new PreviousImageAction(this));
		previousImageButton.setPreferredSize(new Dimension(24, 24));
		previousImageButtonPanel.add(previousImageButton);

		JPanel nextImageButtonPanel = new JPanel();
		nextImageButtonPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		southPanel.add(nextImageButtonPanel);
		nextImageButtonPanel.setLayout(new BoxLayout(nextImageButtonPanel, BoxLayout.X_AXIS));

		nextImageButton = new JButton(new NextImageAction(this));
		nextImageButton.setPreferredSize(new Dimension(24, 24));
		nextImageButtonPanel.add(nextImageButton);
	}

	private void postInitialize() {

		galleryPanel.addContainerListener(new ContainerListener() {

			@Override
			public void componentRemoved(ContainerEvent e) {

				if (galleryPanel.getComponentCount() == 0) {
					imagePanel.removeImage();
				}

				int index = panels.indexOf(e.getChild());

				panels.remove(index);
				imageEntries.remove(index);

				if (index == selectionIndex) {
					setSelectionIndex(index-1 < 0 ? index : index-1); 
				}
			}

			@Override
			public void componentAdded(ContainerEvent e) {
				if (imageEntries.size() == 1) {
					setSelectionIndex(0);
				}
			}
		});

		addPropertyChangeListener(IS_EDITABLE_PROPERTY, (evt) -> {
			addPanel.setVisible(isEditable);
		});

		imageMouseListener = new ThumbnailMouseListener();
		imageEntries = new ArrayList<ImageEntry>();
		panels = new ArrayList<ThumbnailPanel>();

		addPropertyChangeListener(SELECTION_INDEX_PROPERTY, (evt) -> {
			displaySelection((Integer) evt.getNewValue());
		});
		galleryPanel.addContainerListener(new AutoGalleryPanelResizer());
	}

	public List<ImageEntry> getImages() {
		return imageEntries;
	}

	public void addImage(ImageEntry imageEntry) {

		EventQueue.invokeLater(() -> {
			imageEntries.add(imageEntry);

			ThumbnailPanel panel = new ThumbnailPanel(imageEntry);
			panels.add(panel);
			galleryPanel.add(panel);

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
		galleryPanel.remove(index);
	}

	public void clearImages() {
		galleryPanel.removeAll();
	}

	public Integer getSelectionIndex() {
		return selectionIndex;
	}

	public int indexOf(ThumbnailPanel thumbnail) {
		return galleryPanel.getComponentZOrder(thumbnail);
	}

	public void setSelectionIndex(Integer index) {

		if (index < 0 || index >= imageEntries.size()) {
			return;
		}

		selectionIndex = index;
		firePropertyChange(SELECTION_INDEX_PROPERTY, null, index);
	}

	private void displaySelection(int index) {
		imagePanel.setImage(imageEntries.get(index).getImage());
	}

	public void setEditable(boolean isEditable) {
		boolean old = this.isEditable;
		this.isEditable = isEditable;
		firePropertyChange(IS_EDITABLE_PROPERTY, old, isEditable);
	}

	private class ThumbnailPanel extends JPanel {

		private JButton deleteButton;

		private ThumbnailPanel(ImageEntry imageEntry) {

			BufferedImage resizedImage = SwingUtils.resizeImage(imageEntry.getImage(), galleryPanel);

			if (resizedImage == null) {
				throw new IllegalStateException("Cannot retrieve image.");
			}

			setLayout(new OverlayLayout(this));

			deleteButton = new JButton(Icons.DELETE_ICON);
			deleteButton.addActionListener((e) -> {
				int index = galleryPanel.getComponentZOrder(ThumbnailPanel.this);
				removeImageAt(index);
			});
			deleteButton.setVisible(isEditable);
			deleteButton.setAlignmentX(1.0f);
			deleteButton.setAlignmentY(0.0f);
			add(deleteButton);

			JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
			imageLabel.setAlignmentX(1.0f);
			imageLabel.setAlignmentY(0.0f);
			add(imageLabel);

			// Add property change listener to outer class instance
			ImageGalleryPanel.this.addPropertyChangeListener((evt) -> {
				deleteButton.setVisible(isEditable);
			});
			addMouseListener(imageMouseListener);
		}

	}

	private class ThumbnailMouseListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {

			Component comp = (Component) e.getSource();

			if (comp.getComponentAt(e.getPoint()) == null) {
				return;
			}

			Container container = comp.getParent();

			int index = container.getComponentZOrder(comp);
			setSelectionIndex(index);
		}
	}

	private static class AutoGalleryPanelResizer implements ContainerListener {

		private void setSize(Container container) {
			EventQueue.invokeLater(() -> {
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
			});
		}

		@Override
		public void componentRemoved(ContainerEvent e) {
			setSize(e.getContainer());
		}

		@Override
		public void componentAdded(ContainerEvent e) {
			setSize(e.getContainer());
		}
	};

}
