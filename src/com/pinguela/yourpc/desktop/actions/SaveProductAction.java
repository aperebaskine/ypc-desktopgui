package com.pinguela.yourpc.desktop.actions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.Action;

import com.pinguela.YPCException;
import com.pinguela.yourpc.desktop.constants.Icons;
import com.pinguela.yourpc.desktop.util.LocaleUtils;
import com.pinguela.yourpc.desktop.view.ProductView;
import com.pinguela.yourpc.model.dto.LocalizedProductDTO;
import com.pinguela.yourpc.model.dto.ProductDTO;
import com.pinguela.yourpc.service.ImageFileService;
import com.pinguela.yourpc.service.ProductService;
import com.pinguela.yourpc.service.impl.ImageFileServiceImpl;
import com.pinguela.yourpc.service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
public class SaveProductAction 
extends SaveItemAction<LocalizedProductDTO> {

	private ProductService productService;
	private ImageFileService imageFileService;

	public SaveProductAction(ProductView view) {
		super(view, "Save", Icons.OK_ICON);
		this.productService = new ProductServiceImpl();
		this.imageFileService = new ImageFileServiceImpl();
	}
	
	@Override
	protected void doCreate(LocalizedProductDTO item) throws YPCException {
		
		ProductDTO dto = new ProductDTO();
		
		Map<Locale, String> nameI18n = new HashMap<>();
		nameI18n.put(LocaleUtils.getLocale(), item.getName());
		dto.setNameI18n(nameI18n);
		
		Map<Locale, String> descrI18n = new HashMap<>();
		descrI18n.put(LocaleUtils.getLocale(), item.getDescription());
		dto.setDescriptionI18n(descrI18n);
		
		dto.setCategoryId(item.getCategoryId());
		dto.setLaunchDate(item.getLaunchDate());
		dto.setDiscontinuationDate(item.getDiscontinuationDate());
		dto.setStock(item.getStock());
		dto.setPurchasePrice(item.getPurchasePrice());
		dto.setSalePrice(item.getSalePrice());
		dto.setReplacementId(item.getReplacementId());
		
		dto.setAttributes(item.getAttributes());
		
		item.setId(productService.create(dto));
		
		getView().setEntity(productService.findByIdLocalized(item.getId(), LocaleUtils.getLocale()));
//		saveImages(item);
	}
	
	@Override
	protected void doUpdate(LocalizedProductDTO item) throws YPCException {
		
		ProductDTO dto = new ProductDTO();

		Map<Locale, String> nameI18n = new HashMap<>();
		nameI18n.put(LocaleUtils.getLocale(), item.getName());
		dto.setNameI18n(nameI18n);
		
		Map<Locale, String> descrI18n = new HashMap<>();
		descrI18n.put(LocaleUtils.getLocale(), item.getDescription());
		dto.setDescriptionI18n(descrI18n);
		
		dto.setId(item.getId());
		dto.setCategoryId(item.getCategoryId());
		dto.setLaunchDate(item.getLaunchDate());
		dto.setDiscontinuationDate(item.getDiscontinuationDate());
		dto.setStock(item.getStock());
		dto.setPurchasePrice(item.getPurchasePrice());
		dto.setSalePrice(item.getSalePrice());
		dto.setReplacementId(item.getReplacementId());
		
		productService.update(dto);
		
		saveImages(item);
		getView().setEntity(productService.findByIdLocalized(item.getId(), LocaleUtils.getLocale()));
	}
	
	private void saveImages(LocalizedProductDTO item) throws YPCException {
		imageFileService.update(ImageFileService.PRODUCT_TYPE,
				getView().getCurrentEntity().getId(), ((ProductView) getView()).getModifiedImages());
	}
	
	@Override
	protected Action[] getViewerActions() {
		return new Action[]{new DeleteProductAction(getView()), new EditItemAction<LocalizedProductDTO>(getView())};
	}

}
