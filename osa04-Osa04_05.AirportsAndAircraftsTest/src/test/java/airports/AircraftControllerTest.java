/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author set
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    public void statusOK() throws Exception {
        mockMvc.perform(get("/aircrafts")).andExpect(status().isOk());
    }

    @Test
    public void responseContainsAircrafts() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attributeExists("aircrafts"));
    }

    @Test
    public void responseContainsAirpots() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attributeExists("airports"));
    }
    
    @Test
    public void postAircraft() throws Exception {
        mockMvc.perform(post("/aircrafts?name=HA-LOL")).andExpect(redirectedUrl("/aircrafts")).andExpect(status().isFound());
        Aircraft a = new Aircraft();
        a.setName("HA-LOL");
        
        Assert.assertTrue(this.aircraftRepository.findAll().contains(a));
    }
    
    @Test
    public void testPostAircraftAndIsInModel() throws Exception {
        mockMvc.perform(post("/aircrafts?name=XP-55")).andExpect(redirectedUrl("/aircrafts")).andExpect(status().isFound());
        List<Aircraft> aircrafts = (List) mockMvc.perform(get("/aircrafts")).andReturn().getModelAndView().getModel().get("aircrafts");
        Aircraft a = new Aircraft();
        a.setName("XP-55");
        
        Assert.assertTrue(aircrafts.contains(a));
    }
   
}
