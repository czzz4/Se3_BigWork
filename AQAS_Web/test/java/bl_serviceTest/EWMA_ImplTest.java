
import bl.managementServiceImpl.EWMA_Impl;
import bl_service.EWMA_service;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ryysuke on 16/4/14.
 */
public class EWMA_ImplTest extends TestCase {

    private EWMA_service test;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        test = new EWMA_Impl("sz000908", "2015-08-09", "2015-08-14");
    }

    @Test
    public void testGetEWMA() throws Exception {
        assertTrue(!((Double)test.getEWMA()).equals((Double) 0.0 ));
    }
}
