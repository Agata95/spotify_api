package spotifyapi.spotify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spotifyapi.spotify_api.model.AccountRole;

import java.util.Optional;

public interface AccountRoleRepository extends JpaRepository<AccountRole,Long> {

    Optional<AccountRole> findByName(String name);

    boolean existsByName(String name);
}
