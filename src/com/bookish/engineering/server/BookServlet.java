package com.bookish.engineering.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.Query;

import com.bookish.engineering.server.model.Book;
import com.bookish.engineering.server.model.BookState;
import com.bookish.engineering.server.model.BookStateEnum;
import com.bookish.engineering.server.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;
import com.google.gwt.http.client.Request;

public class BookServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(BookServlet.class.getName());
	private static final PersistenceManagerFactory PMF =
			JDOHelper.getPersistenceManagerFactory("transactions-optional");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		PersistenceManager pm = getPersistenceManager();
		String returnJson = "";
		try {
			Query q = pm.newQuery(Book.class);
			q.setOrdering("createDate");
			List<Book> books = (List<Book>) q.execute();
			Gson gson = new Gson();
			returnJson = gson.toJson(books);
		} finally {
			pm.close();
		}
		out.println(returnJson);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManager pm = getPersistenceManager();
		String action = request.getParameter(ServerConstants.ACTION);
		LOG.log(Level.WARNING, "BookServlet: Inside post. Action: "+ action);
		Book newBook = new Book();
		newBook.setTitle(request.getParameter("title"));
		newBook.setAuthor(request.getParameter("author"));

		if(action.equalsIgnoreCase(ServerConstants.ADD)) {
			newBook.setLink(request.getParameter("link"));
			User requestor = new User("Jayita");
			BookState bookRequest = new BookState(requestor, BookStateEnum.REQUEST);
			newBook.setBookstate(bookRequest);		  
			try {
				pm.makePersistent(newBook);
			} finally {
				pm.close();
			}
		}

		if(action.equalsIgnoreCase(ServerConstants.DELETE)) {
			try {
				Query query = pm.newQuery(Book.class);
				query.setFilter("title == :title && author == :author");
				query.setUnique(true);
				Book deleteBook = (Book) query.execute(newBook.getTitle(), newBook.getAuthor());
				pm.deletePersistent(deleteBook);
				LOG.log(Level.WARNING, "book deleted "+newBook.getAuthor());
			} finally {
				pm.close();
			}
		}

	}

	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}

}
