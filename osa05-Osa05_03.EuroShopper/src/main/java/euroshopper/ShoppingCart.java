/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author set
 */

@Data @NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<Item, Long> cart = new HashMap<>();
    
    public Map<Item, Long> getItems() {
        return this.cart;
    }
    
    public void addToCart(Item item) {
        this.cart.put(item, this.cart.getOrDefault(item, Long.valueOf(0)) + 1);
    }
    
    public Double getSum() {
        Double sum = 0.0;
        for (Item item : this.cart.keySet()) {
            sum += item.getPrice() * this.cart.get(item);
        }
        return sum;
    }
}
