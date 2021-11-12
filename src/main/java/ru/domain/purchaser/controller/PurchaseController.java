package ru.domain.purchaser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.domain.purchaser.model.Purchase;
import ru.domain.purchaser.model.User;
import ru.domain.purchaser.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    @Autowired
    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping("/purchase")
    public String purchaseMain(Model model) {
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);
        return "purchase/purchaseMain";
    }

    @GetMapping("/purchase/add")
    public String purchaseAddPage(Model model) {
        return "purchase/purchaseAdd";
    }

    @PostMapping("/purchase/add")
    public String purchaseAdd (@AuthenticationPrincipal User user,
                               @RequestParam String inNumber,
                               @RequestParam String outNumber,
                               @RequestParam String link,
                               @RequestParam String price,
                               @RequestParam String topic,
                               @RequestParam String status,
                               @RequestParam String stopPrice,
                               @RequestParam String comment
    ) {
        Purchase purchase = new Purchase(inNumber, outNumber, link, price, topic, status, stopPrice, comment, user);
        purchaseRepository.save(purchase);
        return "redirect:/purchase";
    }

    @GetMapping("/purchase/{id}/edit")
    public String purchaseEdit(@AuthenticationPrincipal User user,
                               @PathVariable(value = "id") Long id, Model model) {
        if (!purchaseRepository.existsById(id)) {
            return "redirect:/purchase";
        }
        Optional<Purchase> purchases = purchaseRepository.findById(id);
        ArrayList<Purchase> res = new ArrayList<>();
        purchases.ifPresent(res::add);
        model.addAttribute("purchases", res);
        model.addAttribute("user", user);
        return "purchase/purchaseEdit";
    }

    @PostMapping("/purchase/{id}/edit")
    public String purchaseUpdate(@PathVariable(value = "id") Long id,
                                 @RequestParam String inNumber,
                                 @RequestParam String outNumber,
                                 @RequestParam String link,
                                 @RequestParam String price,
                                 @RequestParam String topic,
                                 @RequestParam String status,
                                 @RequestParam String stopPrice,
                                 @RequestParam String comment
    ) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        purchase.setInNumber(inNumber);
        purchase.setOutNumber(outNumber);
        purchase.setLink(link);
        purchase.setPrice(price);
        purchase.setTopic(topic);
        purchase.setStatus(status);
        purchase.setStopPrice(stopPrice);
        purchase.setComment(comment);
        purchaseRepository.save(purchase);
        return "redirect:/purchase";
    }

    @PostMapping("/purchase/{id}/remove")
    public String purchaseDelete(@PathVariable(value = "id") Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        purchaseRepository.delete(purchase);
        return "redirect:/purchase";
    }

}
