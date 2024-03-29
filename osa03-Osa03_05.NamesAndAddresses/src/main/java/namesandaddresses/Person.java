package namesandaddresses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NamedEntityGraph(name = "Person.address",
        attributeNodes = @NamedAttributeNode("address"))
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person extends AbstractPersistable<Long> {

    private String firstName;
    private String lastName;
    @ManyToOne
    private Address address;

}
