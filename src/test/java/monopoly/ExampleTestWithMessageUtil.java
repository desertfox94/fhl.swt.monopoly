package monopoly;

import static org.junit.Assert.fail;

import org.junit.Test;

import monopoly.core.MessageUtil;

public class ExampleTestWithMessageUtil {

    @Test
    public void testExecute() {
    	// do something... messageUtil will not open any alert.
    	MessageUtil.inform("Test", "By default this would cause an execption");
    }
    
    @Test(expected=ExceptionInInitializerError.class)
    public void testExecuteWithDialog() {
    	// maybe there will be a situatuion where its neccessary to open an alert.
    	MessageUtil.SUPPRESS_IN_TESTEXECUTION = false;
    	try {
    		MessageUtil.inform("Test", "This should cause an execption!");
    		fail();
		} finally {
			// Make sure to set the boolean flag in the finally block, otherwise it will not be executed.
			MessageUtil.SUPPRESS_IN_TESTEXECUTION = true;
		}
    }

}
