package spotifyapi.spotify_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spotifyapi.spotify_api.model.Artist;
import spotifyapi.spotify_api.service.ArtistService;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(path = "/admin/artist/")
@PreAuthorize(value = "hasRole('ADMIN')")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/add")
    public String addForm(Model model, Artist artist) {
        model.addAttribute("artistobject", artist);

        return "artist-form";
    }

    @PostMapping("/add")
    public String addForm(Artist artist, Principal principal) {
        artistService.addArtist(artist, principal);

        return "redirect:/admin/artist/list";
    }

    @GetMapping("/list")
    public String artistList(Model model, Principal principal) {
        Set<Artist> artistSet = artistService.getAll(principal.getName());
        model.addAttribute("artists", artistSet);

        return "artist-list";
    }

    @GetMapping("/edit")
    public String artistEdit(Model model,
                              @RequestParam(name = "id") Long id) {
        Optional<Artist> artistOptional = artistService.findByArtistId(id);
        if (artistOptional.isPresent()) {
            model.addAttribute("artist", artistOptional.get());
            return "artist-form";
        }
        return "redirect:/admin/artist/list";
    }

    @GetMapping("/remove")
    public String artistRemove(@RequestParam(name = "id") Long id) {

        artistService.removeArtistById(id);

        return "redirect:/admin/artist/list";
    }

//    @GetMapping("/list/archived")
//    public String taskListArchived(Model model, Principal principal) {
//        Set<TodoTask> taskSet = todoTaskService.getAllArchived(principal.getName());
//        model.addAttribute("tasks", taskSet);
//
//        return "task-list";
//    }



}
