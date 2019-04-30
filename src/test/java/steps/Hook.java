package steps;

import base.DriverFactory;
import base.ThreadLocalDriver;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.log4j.Logger;
import util.ConstantHelper;

public class Hook {

    static Logger LOGGER = Logger.getLogger(Hook.class);

    @BeforeScenario
    public void setUp() throws Exception {
        LOGGER.info("setUpTest");
        DriverFactory.createDriver();
        ConstantHelper.getAndSetConstantsClasses();
    }

    @AfterScenario
    public void tearDown() {
        LOGGER.info("tearDownTest");

        if (ThreadLocalDriver.getDriver() != null){
            ThreadLocalDriver.getDriver().quit();
            ThreadLocalDriver.setDriver(null);
        }
    }
}
