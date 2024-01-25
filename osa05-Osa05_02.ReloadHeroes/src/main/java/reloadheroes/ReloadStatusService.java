/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reloadheroes;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author set
 */

@Service
public class ReloadStatusService {
    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;
    
    @Transactional
    public ReloadStatus getReloadStatus() {
        if (session.getAttribute("ReloadStatus") == null) {
            ReloadStatus rs = new ReloadStatus();
            rs.setName(UUID.randomUUID().toString());
            rs.setReloads(1);
            session.setAttribute("ReloadStatus", rs.getName());
            reloadStatusRepository.save(rs);
            return rs;
        }
        String username = (String) session.getAttribute("ReloadStatus");
        ReloadStatus rs = reloadStatusRepository.findByName(username);
        rs.setReloads(rs.getReloads() + 1);
        reloadStatusRepository.save(rs);
        return rs ;
    }
    
    public Page<ReloadStatus> getTopFive() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        return reloadStatusRepository.findAll(pageable);
    }
}
