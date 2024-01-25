/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapplication;

/**
 *
 * @author set
 */
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Todo extends AbstractPersistable<Long>{
    private String name;
    private int checked;
    
    public Todo(String name) {
        this(name, 0);
    }
    
    public void incrementChecked() {
        this.checked++;
    }
}
