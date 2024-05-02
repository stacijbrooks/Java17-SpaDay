package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class SpaDayController {

    // Your existing methods...

    @GetMapping(value="")
    public String customerForm () {
        return "form"; // Assuming you have a template named "form.html"
    }

    @PostMapping(value="")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String manipedi, Model model) {

        ArrayList<String> facials = new ArrayList<>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        ArrayList<String> appropriateFacials = new ArrayList<>();
        for (String facial : facials) {
            if (checkSkinType(skintype, facial)) {
                appropriateFacials.add(facial);
            }
        }

        model.addAttribute("appropriateFacials", appropriateFacials);
        model.addAttribute("name", name);
        model.addAttribute("skintype", skintype);
        model.addAttribute("manipedi", manipedi);

        return "menu"; // Assuming you have a template named "menu.html"
    }
}
