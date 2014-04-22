/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testParseCSV() throws FileNotFoundException{
        Controller c= new Controller();
        ArrayList<ArrayList<String> > res =c.parseCSV(new File("parseTest.csv"));
        for(ArrayList<String> l:res){
            for(String s:l){
                System.out.print(s+" ");
            }
            System.out.println("");
        }
    }
    
    @Test
    public void tryUploadPublication() throws Exception{
        Controller c= new Controller();
        c.uploadPublication(new File("parseTest.csv"));
    }
    
    @Test
    public void tryModelFile() throws Exception{
        Model m= new Model();
        m.updatePublication(new File("parseTest2.csv"));
    }
}
