package com.bookish.engineering.client;

import com.google.gwt.core.client.JavaScriptObject;

public class LightBook extends JavaScriptObject {
	protected LightBook() { } 
	
	public final native String getTitle() /*-{ return this.title; }-*/; 
	public final native String getAuthor() /*-{ return this.author; }-*/;
	public final native String getLink() /*-{ return this.link; }-*/;
	public final native String getUpvote() /*-{ return this.upvote; }-*/;
	public final native String getUser() /*-{ return this.user; }-*/; 
	
    public final String getString() {
        return getUser() + " - " + getUpvote();
    }
}
