package com.pinguela.yourpc.desktop.view;

import java.awt.Dimension;

import com.pinguela.yourpc.desktop.actions.ProductSearchAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;
import com.pinguela.yourpc.desktop.components.CriteriaPanel;
import com.pinguela.yourpc.desktop.components.ProductCriteriaPanel;
import com.pinguela.yourpc.desktop.renderer.ProductTableCellRenderer;
import com.pinguela.yourpc.model.dto.LocalizedProductDTO;

@SuppressWarnings("serial")
public class ProductSearchView 
extends AbstractPaginatedSearchView<LocalizedProductDTO> {

	public ProductSearchView() {
		this(new SearchActionBuilder<>(ProductSearchAction.class));
	}

	private ProductSearchView(SearchActionBuilder<LocalizedProductDTO, ? extends SearchAction<LocalizedProductDTO>> builder) {
		super(builder);
		setPreferredSize(new Dimension(960, 720));
		setTableCellRenderer(Object.class, new ProductTableCellRenderer());
	}
	
	@Override
	protected CriteriaPanel<LocalizedProductDTO> initializeCriteriaPanel() {
		return new ProductCriteriaPanel();
	}

}
