/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author set
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AirportServiceTest {
    @Autowired
    private AirportService airportService;
    @Autowired
    private AirportRepository airportRepository;
    
    @Test
    public void testAirportCreate() throws Exception {
        this.airportService.create("testIdentifier", "testName");
        Airport a = new Airport();
        a.setIdentifier("testIdentifier");
        a.setName("testName");
        
        Assert.assertTrue(airportService.list().contains(a));
    }
    
    @Test
    public void testAirportList() throws Exception {
        this.airportService.create("AB", "test1");
        this.airportService.create("AC", "test2");
        this.airportService.create("AD", "test3");

        Assert.assertEquals(3, this.airportService.list().size());
    }
    
    @Test
    public void testAiportCreateDuplicated() throws Exception {
        this.airportService.create("AB", "test1");
        this.airportService.create("AB", "test1");
        Assert.assertEquals(1, this.airportService.list().size());
    }
}
