package com.bookish.engineering.client;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bookish.engineering.client.model.BookDetail;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
=======
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
>>>>>>> b399e35b0a978fa068798ad4bc383d2787964b10
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Bookish implements EntryPoint {
	private static final Logger LOG = Logger.getLogger(Bookish.class.getName());
	private TabLayoutPanel tabLayoutPanel = new TabLayoutPanel(10, Unit.EM);
	private LayoutPanel layoutPanel = new LayoutPanel();
	private FlexTable wishlistTable = new FlexTable();
	private FlexTable favoriteTable = new FlexTable();
	private FlexTable libraryTable = new FlexTable();
	private VerticalPanel addPanel = new VerticalPanel();
	private Button addToWishlistButton = new Button();
	private Label errorMsgLabel = new Label();

	private ArrayList<String> books = new ArrayList<String>();

	private static final String BOOK_BASE_URL = GWT.getModuleBaseURL() + "books?";

	public Button getAddToWishlistButton() {
		return addToWishlistButton;
	}
	public void setAddToWishlistButton(Button addToWishlistButton) {
		this.addToWishlistButton = addToWishlistButton;
	}
	private TabLayoutPanel tabLayoutPanel = new TabLayoutPanel(10, Unit.EM);
	private LayoutPanel layoutPanel = new LayoutPanel();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Widget header = new HTML("<h1>Bookish Dashboard</h1>");
		layoutPanel.add(header);
		wishlistTable.setCellSpacing(10);
		wishlistTable.setStylePrimaryName("WishList");
		addPanel.setStylePrimaryName("AddPanel");
		addPanel.setSpacing(20);
		addToWishlistButton.setStylePrimaryName("AddButton");
		addToWishlistButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AddToListPopup addPopup = new AddToListPopup(Bookish.this);
				addPopup.center();
				addPopup.show();
			}
		});

		addPanel.add(wishlistTable);	
		addPanel.add(addToWishlistButton);
		addPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		addPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		addPanel.add(errorMsgLabel);

		tabLayoutPanel.add(addPanel, "Wishlist");
		tabLayoutPanel.add(favoriteTable, "Favourites");
		tabLayoutPanel.add(libraryTable, "Library");
		tabLayoutPanel.selectTab(0);
		tabLayoutPanel.setWidth("45%");
		tabLayoutPanel.setHeight("80%");
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);

		RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		rootLayoutPanel.add(layoutPanel);
		rootLayoutPanel.add(tabLayoutPanel);

		Image addImage = new Image();
		addImage.setUrl(GWT.getModuleBaseURL()+"images/add.png");
		addImage.setSize("50px","50px");
		addToWishlistButton.getElement().appendChild(addImage.getElement());

		displayBooks();
	}

	private void displayBooks() {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, BOOK_BASE_URL);
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					displayError("Couldn't retrieve JSON");
				}		    	
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						JsArray<LightBook> lightBooks = JsonUtils.<JsArray<LightBook>>safeEval(response.getText());
						updateList(lightBooks);		    	  
					} else {
						displayError("Couldn't retrieve JSON (" + response.getStatusText()
								+ ")");
					}
				}
			});
		} catch (RequestException e) {
			displayError("Couldn't retrieve JSON");
		}
	}

	public void updateList(JsArray<LightBook> books) {
		for (int i=0; i < books.length(); i++) {
			LightBook lightBook = books.get(i);
			BookDetail bookDetails = new BookDetail();
			bookDetails.setTitle(lightBook.getTitle());
			bookDetails.setAuthor(lightBook.getAuthor());
			bookDetails.setUpvote(lightBook.getUpvote());
			updateTable(bookDetails);
		}					
	}
	private void updateTable(final BookDetail bookDetail) {
		int row = wishlistTable.getRowCount();
		String title = bookDetail.getTitle();
		String upvote = bookDetail.getUpvote();
		books.add(title);

		wishlistTable.getColumnFormatter().setStylePrimaryName(1, "TitleColumn");
		wishlistTable.getColumnFormatter().setStylePrimaryName(2, "ListColumn");
		wishlistTable.getColumnFormatter().setStylePrimaryName(3, "ListColumn");

		Label titleLabel = new Label();
		titleLabel.setText(title);
		titleLabel.setStylePrimaryName("titles");
		wishlistTable.setWidget(row, 1, titleLabel);

		HorizontalPanel heartPanel = new HorizontalPanel();
		heartPanel.setSpacing(5);
		heartPanel.setStylePrimaryName("HeartPanel");
		int hearts = Integer.valueOf(upvote);
		for (int i = 0; i<hearts; i++) {
			Image heart = new Image();
			heart.setUrl(GWT.getModuleBaseURL()+"images/heart.png");
			heart.setSize("30px","30px");
			heartPanel.add(heart);
		}
		wishlistTable.setWidget(row, 2, heartPanel);
		Image image = new Image();
		image.setUrl(GWT.getModuleBaseURL()+"images/trash.png");
		image.setSize("20px","20px");
		Button deleteBookButton = new Button();
		deleteBookButton.getElement().appendChild(image.getElement());
		deleteBookButton.setStylePrimaryName("DeleteButton");
		deleteBookButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteBook(bookDetail);
			}
		});
		wishlistTable.setWidget(row, 3, deleteBookButton);
	}

	public void addBook(final BookDetail bookDetail) {
		String ADD_BOOK_URL = BOOK_BASE_URL;
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, ADD_BOOK_URL);
		builder.setHeader("Content-type", "application/x-www-form-urlencoded");
		StringBuilder sb = new StringBuilder();
		sb.append("ACTION=add");
		sb.append("&title="+ bookDetail.getTitle());
		sb.append("&author="+ bookDetail.getAuthor());
		sb.append("&link="+ bookDetail.getLink());
		try {
			Request request = builder.sendRequest(sb.toString(), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					displayError("Couldn't retrieve JSON");
				}		    	
				public void onResponseReceived(Request request, Response response) {
					displayError("ADDED!! addBook response: "+response.getStatusCode());
					if (200 == response.getStatusCode()) {
						updateTable(bookDetail);		    	  
					} else {
						displayError("Couldn't save book (" + response.getStatusText()
								+ ")");
					}
				}
			});
		} catch (RequestException e) {
			displayError("Couldn't retrieve Response");
		}

	}
	
	public void deleteBook(final BookDetail bookDetail) {
		String DELETE_BOOK_URL = BOOK_BASE_URL + "ACTION=" + "delete" + "&title=" + bookDetail.getTitle() + 
				"&author=" + bookDetail.getAuthor();
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, DELETE_BOOK_URL);
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					displayError("Couldn't delete book");
				}		    	
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						int removedIndex = books.indexOf(bookDetail.getTitle());
						books.remove(removedIndex);
						wishlistTable.removeRow(removedIndex);		    	  
					} else {
						displayError("Couldn't delete book (" + response.getStatusText()
								+ ")");
					}
				}
			});
		} catch (RequestException e) {
			displayError("Couldn't retrieve Response");
		}
	}

	private void displayError(String error) {
		errorMsgLabel.setText("Error: " + error);
		errorMsgLabel.setVisible(true);
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
