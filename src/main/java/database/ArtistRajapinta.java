package database;

import java.util.List;

import model.Artist;

public interface ArtistRajapinta {
	
	public List<Artist> getAllArtists();

    public Artist getArtist(long id);

    public boolean addArtist(Artist newArtist);

    public boolean removeArtist(Artist artist);


}
