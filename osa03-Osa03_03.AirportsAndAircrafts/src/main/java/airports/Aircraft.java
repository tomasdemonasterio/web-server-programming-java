package airports;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft extends AbstractPersistable<Long> {
    private String name;
    
    @ManyToOne
    //@JoinColumn(name = "id")
    private Airport airport;
}
