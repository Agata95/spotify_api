package spotifyapi.spotify_api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spotifyapi.spotify_api.model.AccountRole;
import spotifyapi.spotify_api.repository.AccountRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountRoleService {
    @Value("${account.default.roles:USER}")
    private String[] defaultRoles;

    private AccountRoleRepository accountRoleRepository;

    @Autowired
    public AccountRoleService(AccountRoleRepository accountRoleRepository) {
        this.accountRoleRepository = accountRoleRepository;
    }

    public Set<AccountRole> getDefaultRoles(){
        Set<AccountRole> accountRoles = new HashSet<>();
        for (String role : defaultRoles) {
//            todo: znajdz w bazie
            Optional<AccountRole> accountRoleOptional = accountRoleRepository.findByName(role);
//            todo: dodaj do kolekcji
            accountRoleOptional.ifPresent(accountRoles::add);
        }

        return accountRoles;
    }

    public List<AccountRole> getAll() {
        return accountRoleRepository.findAll();
    }
}
