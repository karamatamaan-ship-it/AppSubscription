package com.example.appsubscription;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // New import
import org.springframework.web.bind.annotation.RequestParam; // New import
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    // Moving the list here makes it "stay alive" while the app is running
    private static List<Subscription> mySubs = new ArrayList<>();

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        double total = 0.0;
        for(Subscription subs:mySubs)
            total = total + subs.getPrice();

        model.addAttribute("subscriptions", mySubs);
        model.addAttribute("totalCost", total);
        return "index";
    }

    @PostMapping("/add")
    public String addSubscription(@RequestParam String name, @RequestParam String price) {
        // 1. Convert the String price into a double
        double priceAsDouble = Double.parseDouble(price);

        // 2. Use that double to create the subscription
        mySubs.add(new Subscription(name, priceAsDouble));
        return "redirect:/dashboard";
    }

    @PostMapping("/remove")
    public String removeSubscription(@RequestParam int index) {
        // Check to make sure the index actually exists before removing
        if (index >= 0 && index < mySubs.size()) {
            mySubs.remove(index);
        }
        return "redirect:/dashboard";
    }
}