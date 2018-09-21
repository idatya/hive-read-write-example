package sh.samar;

import java.io.IOException;

import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

public class TestMain {

    @Mocked
    Main main;
    @Before
    public void setup(){
        
    }
    
    @Test
    public void test() throws IOException{
        main.main(null);
    }
}
