package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistDao;
import model.Artist;


@WebServlet("/")
public class ArtistListServlet extends HttpServlet {
	
	private ArtistDao dao = new ArtistDao();
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Artist> artists = this.dao.getAllArtists();
		
		req.setAttribute("artists", artists);
		req.getRequestDispatcher("/WEB-INF/artistList.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		Artist newArtist = new Artist(name);
		this.dao.addArtist(newArtist);
		resp.sendRedirect("/artistList");
	}
	
	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
		long id = Long.parseLong(req.getParameter("id"));
		Artist itemToRemove = this.dao.getArtist(id);
		
		if (itemToRemove != null) {
			this.dao.removeArtist(itemToRemove);
		} else {
			resp.setStatus(404);
		}
	}


}
