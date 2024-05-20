package com.pinguela.yourpc.desktop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.pinguela.yourpc.desktop.actions.FirstPageAction;
import com.pinguela.yourpc.desktop.actions.LastPageAction;
import com.pinguela.yourpc.desktop.actions.NextPageAction;
import com.pinguela.yourpc.desktop.actions.PaginationAction;
import com.pinguela.yourpc.desktop.actions.PreviousPageAction;
import com.pinguela.yourpc.desktop.actions.SearchAction;
import com.pinguela.yourpc.desktop.actions.SearchActionBuilder;

public abstract class AbstractPaginatedSearchView<T> 
extends AbstractSearchView<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 217909795728003216L;

	protected static final String POS_PROPERTY = "pos";
	protected static final String PAGE_SIZE_PROPERTY = "pageSize";
	protected static final String RESULT_COUNT_PROPERTY = "resultCount";
	
	private Integer pos = 1;
	private Integer pageSize = 16;
	private Integer resultCount = 0;
	
	private final PropertyChangeListener clampPos = (evt) -> {
		if (pos > resultCount) {
			setPos(resultCount - pageSize +1);
		} else if (pos <= pageSize || resultCount <= pageSize) {
			setPos(1);
		}
	};

	private JPanel paginationPanel;
	private JButton firstPageButton;
	private JButton previousPageButton;
	private JButton nextPageButton;
	private JButton lastPageButton;
	private JLabel pageLabel;

	public AbstractPaginatedSearchView(SearchActionBuilder<T, ? extends SearchAction<T>> builder) {
		super(builder);
		initialize();
		postInitialize(builder.getAfterBuild());
	}
	
	private void initialize() {
		
		paginationPanel = new JPanel();
		paginationPanel.setVisible(true);
		add(paginationPanel, BorderLayout.SOUTH);

		firstPageButton = new JButton("");
		firstPageButton.setEnabled(false);
		firstPageButton.setPreferredSize(new Dimension(32, 32));
		firstPageButton.setIcon(new ImageIcon(AbstractPaginatedSearchView.class.getResource("/nuvola/22x22/1827_rew_player_rew_player.png")));
		paginationPanel.add(firstPageButton);

		previousPageButton = new JButton("");
		previousPageButton.setEnabled(false);
		previousPageButton.setPreferredSize(new Dimension(32, 32));
		previousPageButton.setIcon(new ImageIcon(AbstractPaginatedSearchView.class.getResource("/nuvola/22x22/1897_noatunback_noatunback.png")));
		paginationPanel.add(previousPageButton);

		pageLabel = new JLabel("0-0 of 0");
		pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel.setPreferredSize(new Dimension(100, 14));
		paginationPanel.add(pageLabel);

		nextPageButton = new JButton("");
		nextPageButton.setEnabled(false);
		nextPageButton.setPreferredSize(new Dimension(32, 32));
		nextPageButton.setIcon(new ImageIcon(AbstractPaginatedSearchView.class.getResource("/nuvola/22x22/1822_end_player_player_end.png")));
		paginationPanel.add(nextPageButton);

		lastPageButton = new JButton("");
		lastPageButton.setEnabled(false);
		lastPageButton.setPreferredSize(new Dimension(32, 32));
		lastPageButton.setIcon(new ImageIcon(AbstractPaginatedSearchView.class.getResource("/nuvola/22x22/1823_fwd_fwd_player_player.png")));
		paginationPanel.add(lastPageButton);
	}

	private void postInitialize(SearchAction<T> searchAction) {
		
		addPropertyChangeListener(POS_PROPERTY, searchAction);
		addPropertyChangeListener(PAGE_SIZE_PROPERTY, searchAction);
		addPropertyChangeListener(TABLE_MODEL_PROPERTY, (evt) -> {
				updatePageLabel();
				updatePaginationButtonState();
		});

		PaginationAction firstPageAction = new FirstPageAction(this);
		addPropertyChangeListener(CRITERIA_PROPERTY, firstPageAction);
		
		addListenerToSearchButton(firstPageAction);
		firstPageButton.addActionListener(firstPageAction);
		previousPageButton.addActionListener(new PreviousPageAction(this));
		nextPageButton.addActionListener(new NextPageAction(this));
		lastPageButton.addActionListener(new LastPageAction(this));
		
		addPropertyChangeListener(RESULT_COUNT_PROPERTY, clampPos);
	}

	private void updatePageLabel() {

		int pageStart = resultCount == 0 ? 0 : pos;
		int pageEnd = pos + pageSize -1;

		if (pageEnd >= resultCount) {
			pageEnd = resultCount;
		}

		pageLabel.setText(String.format("%d-%d of %d", pageStart, pageEnd, resultCount));
	}

	private void updatePaginationButtonState() {

		boolean isFirstPage = pos <= pageSize;
		boolean isLastPage = pos + pageSize - 1 >= resultCount;

		firstPageButton.setEnabled(!isFirstPage);
		previousPageButton.setEnabled(!isFirstPage);

		nextPageButton.setEnabled(!isLastPage);
		lastPageButton.setEnabled(!isLastPage);

	}

	public int getPos() {
		return pos;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public Integer getResultCount() {
		return resultCount;
	}

	public final void setPos(int pos) {
		int old = this.pos;
		this.pos = pos;
		firePropertyChange(POS_PROPERTY, old, pos);
	}

	public void setPageSize(int pageSize) {
		int old = this.pageSize;
		this.pageSize = pageSize;
		firePropertyChange(PAGE_SIZE_PROPERTY, old, pageSize);
	}

	public void setResultCount(Integer resultCount) {
		Integer old = this.resultCount;
		this.resultCount = resultCount;
		firePropertyChange(RESULT_COUNT_PROPERTY, old, resultCount);
	}

}
