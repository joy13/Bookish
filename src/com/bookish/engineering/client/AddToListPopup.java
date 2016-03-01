package com.bookish.engineering.client;

import com.bookish.engineering.client.model.BookDetail;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AddToListPopup extends DialogBox {
	private VerticalPanel mainPanel = new VerticalPanel(); 
	private TextBox titleBox = new TextBox();
	private TextBox authorBox = new TextBox();
	private TextBox linkBox = new TextBox();
	private Button submitButton = new Button();
	private Button cancelButton = new Button();
	private HorizontalPanel titlePanel = new HorizontalPanel();
	private HorizontalPanel authorPanel = new HorizontalPanel();
	private HorizontalPanel linkPanel = new HorizontalPanel();
	private Label titleLabel = new Label("Title");
	private Label authorLabel = new Label("Author");
	private Label linkLabel = new Label("Link");
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Bookish bookish;

	private static String BOOK_INFO_URL = GWT.getModuleBaseURL() + "book?";

	public AddToListPopup(Bookish bookish) {
		super(true, true);
		setText("Tell Us More!");
		this.setStyleName("Popup");
		setGlassEnabled(true);
		titlePanel.setSpacing(16);
		authorPanel.setSpacing(8);
		linkPanel.setSpacing(16);
		titlePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		authorPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		linkPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		titleLabel.setStylePrimaryName("Label");
		linkLabel.setStylePrimaryName("Label");
		authorLabel.setStylePrimaryName("Label");
		
		titlePanel.add(titleLabel);
		titlePanel.add(titleBox);
		authorPanel.add(authorLabel);
		authorPanel.add(authorBox);
		linkPanel.add(linkLabel);
		linkPanel.add(linkBox);
		mainPanel.setSpacing(15);

		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				submitBook();
			}
		});
		Image submitImage = new Image();
		submitImage.setUrl(GWT.getModuleBaseURL()+"images/submit.png");
		submitImage.setSize("40px","40px");
		submitButton.getElement().appendChild(submitImage.getElement());
		
		Image cancelImage = new Image();
		cancelImage.setUrl(GWT.getModuleBaseURL()+"images/cancel.png");
		cancelImage.setSize("25px","25px");
		cancelButton.getElement().appendChild(cancelImage.getElement());
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AddToListPopup.this.hide();
			}
		});
		cancelButton.setStyleName("CancelButton");
		final HTML caption = (HTML)getCaption();		
		caption.setStylePrimaryName("Caption");
		mainPanel.add(cancelButton);
		mainPanel.add(titlePanel);
		mainPanel.add(authorPanel);
		mainPanel.add(linkPanel);
		submitButton.setStylePrimaryName("SubmitButton");
		buttonPanel.setWidth("100%");
		buttonPanel.add(cancelButton);
		buttonPanel.add(submitButton); 
		buttonPanel.setSpacing(20);
		mainPanel.add(buttonPanel);
		setWidget(mainPanel);
	}

	public void submitBook() {
		validate(titleBox);
		validate(authorBox);
		validate(linkBox);
		BookDetail bookDetails = new BookDetail();
		bookDetails.setTitle(titleBox.getText().trim());
		bookDetails.setAuthor(authorBox.getText().trim());
		bookDetails.setLink(linkBox.getText().trim());
		bookish.addBook(bookDetails);
		this.hide();
	}

	private void validate(TextBox textBox) {
		String text = textBox.getText().trim();
		if (textBox.equals(this.titleBox)) {
			if (text.isEmpty() | text == null) {
				Window.alert("Please enter title");
			}
		}

		if (textBox.equals(this.linkBox)) {
			if (text.isEmpty() | text == null) {
				Window.alert("Please enter a valid Goodreads/Amazon link");
			}
		}

		if (textBox.equals(this.authorBox)) {
			if (text.isEmpty() | text == null) {
				Window.alert("Please enter author");
			}
		}		
	}

	private void displayBooks() {

	}

	private void displayError(String error) {
		//	    errorMsgLabel.setText("Error: " + error);
		//	    errorMsgLabel.setVisible(true);
	}

}
