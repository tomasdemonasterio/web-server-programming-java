/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtables;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author set
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course extends AbstractPersistable<Long>{
    private String name;
    @ManyToMany (mappedBy = "enrollments")
    private List<Student> students;
}
