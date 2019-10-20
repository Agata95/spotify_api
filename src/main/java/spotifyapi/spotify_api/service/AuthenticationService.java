package spotifyapi.spotify_api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spotifyapi.spotify_api.model.Account;
import spotifyapi.spotify_api.model.AccountRole;
import spotifyapi.spotify_api.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    //    interface zgłasza nam, że klasa jest servicem który będzie przyjmował i sprawdzał uzytkownika w bazie danych
//    spring musi załadować użytkownika, wynikiem jest UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            String[] roles = account.getAccountRoles()
                    .stream()
                    .map(AccountRole::getName).toArray(String[]::new);

            return User.builder()
                    .username(account.getUsername())
                    .password(account.getPassword())
                    .roles(roles)
                    .accountLocked(account.isLocked())
                    .build();
        }
        throw new UsernameNotFoundException("Username not found.");
    }
}
