/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examsandquestions;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author set
 */
public interface ExamsRepository extends JpaRepository<Exam, Long>{
    
}
