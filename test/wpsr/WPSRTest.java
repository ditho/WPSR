package wpsr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author tb006
 */
public class WPSRTest extends TestCase {
    
    public WPSRTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getConstruct method, of class RunDOM.
     */
    public void testProcess() {
        InputStream in = null;
        try {
            File f = new File(WPSRTest.class.getResource("/wpsr/resources/websites/iswas.html").toURI());
            byte[] buffer = new byte[(int) f.length()];
            in = new FileInputStream(f);
            in.read(buffer);
            in.close();
            String s = new String(buffer);

            assertNotNull(WPSR.process(s, "test", "RDFSearch"));
            assertNotNull(WPSR.process(s, "test", "RDFIdent"));
            assertNotNull(WPSR.process(s, "test", "RDFSearch"));
            //assertNotNull(WPSR.process(s, "test", "RDFTab"));
            //assertNotNull(WPSR.process(s, "test", "RDFNavigation"));
            //assertNotNull(WPSR.process(s, "test", "RDFTree"));
        } catch (IOException ex) {
            Logger.getLogger(WPSRTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(WPSRTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(WPSRTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}