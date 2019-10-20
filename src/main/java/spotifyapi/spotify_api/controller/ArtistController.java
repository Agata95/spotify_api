package spotifyapi.spotify_api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spotifyapi.spotify_api.repository.ArtistRepository;
import spotifyapi.spotify_api.service.ArtistService;

@Controller
@RequestMapping(path = "/admin/artist/")
@PreAuthorize(value = "hasRole('ADMIN')")
public class ArtistController {

    private ArtistService artistService;
    private ArtistRepository artistRepository;
}
