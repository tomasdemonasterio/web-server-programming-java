/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author set
 */

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCart shoppingCart;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("items", this.shoppingCart.getItems());
        model.addAttribute("sum", this.shoppingCart.getSum());
        return "cart";
    }
    
    @PostMapping("/cart/items/{id}")
    public String addToCart(@PathVariable Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        optionalItem.ifPresent(i -> shoppingCart.addToCart(i));
        
        return "redirect:/cart";
    }
}
