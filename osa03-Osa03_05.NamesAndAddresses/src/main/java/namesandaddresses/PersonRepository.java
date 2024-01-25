package namesandaddresses;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @EntityGraph(value = "Person.address")
    List<Person> findByIdNotNull();
}
