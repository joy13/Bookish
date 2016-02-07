package com.bookish.engineering.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Bookish implements EntryPoint {
	private TabLayoutPanel tabLayoutPanel = new TabLayoutPanel(10, Unit.EM);
	private LayoutPanel layoutPanel = new LayoutPanel();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Widget header = new HTML("<h1>Bookish Dashboard</h1>");
		layoutPanel.add(header);
		tabLayoutPanel.add(new HTML("this"), "Wishlist");
		tabLayoutPanel.add(new HTML("that"), "Favourites");
		tabLayoutPanel.add(new HTML("and this"), "Library");
		tabLayoutPanel.selectTab(0);
		tabLayoutPanel.setWidth("50%");
		RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		rootLayoutPanel.add(layoutPanel);
		rootLayoutPanel.add(tabLayoutPanel);
	}
}
