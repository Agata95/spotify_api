package spotifyapi.spotify_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spotifyapi.spotify_api.model.Account;
import spotifyapi.spotify_api.model.Artist;
import spotifyapi.spotify_api.repository.AccountRepository;
import spotifyapi.spotify_api.repository.ArtistRepository;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void addArtist(Artist artist, Principal principal) {
        Optional<Account> account = accountRepository.findByUsername(principal.getName());
        if (account.isPresent()) {
            Account userAccount = account.get();

            artist.setAccount(userAccount);

            artistRepository.save(artist);
        }
    }

    public Set<Artist> getAll(String username) {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()) {
            Account userAccount = account.get();
            return new HashSet<>(userAccount
                    .getArtists());
        }
        return new HashSet<>();
    }

    public Optional<Artist> findByArtistId(Long id) {
        return artistRepository.findById(id);
    }

    public void removeArtistById(Long id) {
        artistRepository.deleteById(id);
    }
}
