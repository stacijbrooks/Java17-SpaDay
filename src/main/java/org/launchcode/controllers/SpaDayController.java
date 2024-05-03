package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;



    @Controller
    public class SpaDayController {

        public boolean checkSkinType(String skinType, String facialType) {
            if (skinType.equals("oily")) {
                return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating");
            } else if (skinType.equals("combination")) {
                return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel");
            } else if (skinType.equals("dry")) {
                return facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial");
            } else {
                return true;
            }
        }

        @GetMapping(value = "")
        @ResponseBody
        public String customerForm() {
            String html = "<form method = 'post'>" +
                    "Name: <br>" +
                    "<input type = 'text' name = 'name'>" +
                    "<br>Skin type: <br>" +
                    "<select name = 'skintype'>" +
                    "<option value = 'oily'>Oily</option>" +
                    "<option value = 'combination'>Combination</option>" +
                    "<option value = 'normal'>Normal</option>" +
                    "<option value = 'dry'>Dry</option>" +
                    "</select><br>" +
                    "Manicure or Pedicure? <br>" +
                    "<select name = 'manipedi'>" +
                    "<option value = 'manicure'>Manicure</option>" +
                    "<option value = 'pedicure'>Pedicure</option>" +
                    "</select><br>" +
                    "<input type = 'submit' value = 'Submit'>" +
                    "</form>";
            return html;
        }

        @PostMapping(value = "")
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

            ArrayList<String> polishChoices = new ArrayList<>();
            polishChoices.add("#ffd700"); // Gold
            polishChoices.add("#ff6347"); // Tomato
            polishChoices.add("#4682b4"); // Steel Blue
            polishChoices.add("#7fff00"); // Chartreuse
            polishChoices.add("#ff69b4"); // Hot Pink
            polishChoices.add("#ff4500"); // Orange Red

            model.addAttribute("polishChoices", polishChoices);


            return "menu";
        }
    }

