package spotifyapi.spotify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spotifyapi.spotify_api.model.AccountRole;
import spotifyapi.spotify_api.model.Artist;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist,Long> {

    Optional<AccountRole> findByName(String name);

}
