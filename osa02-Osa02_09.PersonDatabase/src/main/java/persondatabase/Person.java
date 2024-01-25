/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persondatabase;

/**
 *
 * @author set
 */
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person extends AbstractPersistable<Long> {
    private String name;
}
