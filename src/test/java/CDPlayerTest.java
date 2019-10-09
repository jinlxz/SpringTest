import com.xzl.configuration.AppConfig;
import com.xzl.test.CDPlayer;
import com.xzl.test.SgtPeppers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CDPlayerTest {
    @Autowired
    private SgtPeppers sgtPepper;
    @Autowired
    private CDPlayer cd;
    @Test
    public void sgtPepperShouldNotNull(){
        assertNotNull(sgtPepper);
    }
    @Test
    public void cdPlayerShouldNotNull(){
        assertNotNull(cd);
    }
}
