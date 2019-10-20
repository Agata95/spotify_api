package spotifyapi.spotify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spotifyapi.spotify_api.model.Artist;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist,Long> {

    Optional<Artist> findByName(String name);

    boolean existsByName(String name);
}
