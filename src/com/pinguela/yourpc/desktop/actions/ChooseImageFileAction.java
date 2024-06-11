package com.pinguela.yourpc.desktop.actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.yourpc.desktop.components.ImageGalleryPanel;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.model.ImageEntry;

public class ChooseImageFileAction 
extends ImageGalleryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1582903302123613515L;
	
	private static Logger logger = LogManager.getLogger(ChooseImageFileAction.class);
	
	public ChooseImageFileAction(ImageGalleryPanel panel) {
		super(Icons.ADD_ICON, panel);
	}

	@Override
	protected void doAction() {
		ImageGalleryPanel panel = getPanel();
		
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		int option = fileChooser.showSaveDialog(panel);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			try {
				BufferedImage image = ImageIO.read(f);
				panel.addImage(new ImageEntry(image, null));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(panel, "Error while loading image.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
