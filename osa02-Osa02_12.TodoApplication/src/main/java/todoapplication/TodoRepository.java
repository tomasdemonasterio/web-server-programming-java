/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapplication;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author set
 */
public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
