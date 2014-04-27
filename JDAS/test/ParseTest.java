/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ParseTest {
    
    public ParseTest() {
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
    public void testParseCSV(){
        try {
            ArrayList<ArrayList<String> > res;
            String str="";
            Controller c = new Controller();
            
            res=c.parseCSV(new File("parseCSV-testcase/test1.csv"));
            for(ArrayList<String> l:res){
                for(String s:l){
                    str+=s+"\n";
                }
                str+="\n";
            }
            assertEquals(str,"Fly\n\n");
            
            res=c.parseCSV(new File("parseCSV-testcase/test2.csv"));
            str="";
            for(ArrayList<String> l:res){
                for(String s:l){
                    str+=s+"\n";
                }
                str+="\n";
            }
            assertEquals(str,",\n\n");
            
            res=c.parseCSV(new File("parseCSV-testcase/test3.csv"));
            str="";
            for(ArrayList<String> l:res){
                for(String s:l){
                    str+=s+"\n";
                }
                str+="\n";
            }
            assertEquals(str,"Row 1\nColumn 2\n\nRow 2\nColumn 3\n\n");
            
            res=c.parseCSV(new File("parseCSV-testcase/test4.csv"));
            str="";
            for(ArrayList<String> l:res){
                for(String s:l){
                    str+=s+"\n";
                }
                str+="\n";
            }
            assertEquals(str,"Enclosed\nwithout comma\nmultiple columns\n\nCSV\nFILE\nCS\n\n");
            
            res=c.parseCSV(new File("parseCSV-testcase/test5.csv"));
            str="";
            for(ArrayList<String> l:res){
                for(String s:l){
                    str+=s+"\n";
                }
                str+="\n";
            }
            assertEquals(str,"19880\n\n55042232,3213\n\n"
                    + "2\n\n109321,2,3213,2321,2312\n\n"
                    + ",,,,,,@13123\n\n");
            
            res=c.parseCSV(new File("parseCSV-testcase/test6.csv"));
            str="";
            for(ArrayList<String> l:res){
                for(String s:l){
                    str+=s+"\n";
                }
                str+="\n";
            }
            assertEquals(str,"\n\nyes19983,789\n\n"
                    + "123\n\nokay,2920,321\n\n"
                    + "12983\n232\nsixty\n\n"
                    + "fine,haha\n1231\n\n\n");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}
