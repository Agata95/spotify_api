package spotifyapi.spotify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spotifyapi.spotify_api.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);
}
