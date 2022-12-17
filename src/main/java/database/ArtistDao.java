package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

public class ArtistDao {
	
	private Database db = new Database();
	
	public List<Artist> getAllArtists() {
		String selectAll = "SELECT ArtistId, Name FROM Artist ORDER BY Name ASC;";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		List<Artist> allArtists = new ArrayList<>();
		try {
			connection = db.connect();
			statement = connection.prepareStatement(selectAll);
			results = statement.executeQuery();
			while (results.next()) {
				long id = results.getLong("ArtistId");
				String name = results.getString("Name");
				
				Artist a = new Artist(id, name);
				allArtists.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.db.close(connection, statement, results);
		}
		return allArtists;
	}

	public Artist getArtist(long id) { //vaihdettu long id
        List<Artist> allArtists = this.getAllArtists();
        for (Artist artist : allArtists) {
        	if (id == artist.getId()) {
        		return artist;
        	}
        }
        return null;
    }

    public boolean addArtist(Artist newArtist) {
        String sql = "INSERT INTO Artist (name) VALUES (?)"; //lisää ORDER BY Name ASC
        
        Connection connection = null;
        PreparedStatement statement = null;
    	ResultSet ids = null;
    	
    	try {
    		connection = this.db.connect();
    		statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newArtist.getName());
    		int rows = statement.executeUpdate();
    		if (rows == 1) {
    			ids = statement.getGeneratedKeys();
    			ids.next();
    			long generatedId = ids.getLong(1);
    			newArtist.setId(generatedId);
    			return true;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		this.db.close(connection, statement, ids);
    	}
    	
        return false;
    }

    public boolean removeArtist(Artist artist) {
    	Connection connection = null;
        PreparedStatement statement = null;
        
        try {
    		connection = this.db.connect();
    		statement = connection.prepareStatement("DELETE FROM Artist WHERE id = ?");
    		statement.setLong(1, artist.getId());
    		int rows = statement.executeUpdate();
    		if (rows == 1) {
    			return true;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		this.db.close(connection, statement, null);
    	}
    	
        
        return false;
    }


}
