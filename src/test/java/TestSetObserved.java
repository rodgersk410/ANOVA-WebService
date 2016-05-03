import org.junit.Test;

import anovaApp.AnovaForm;

public class TestSetObserved {

	@Test
	public void testSetObservedValues() throws InterruptedException{
		AnovaForm form = new AnovaForm();
		form.setObservedValues("C:\\Users\\keith.rodgers\\Downloads\\observedValuesTestFile.txt");
	}
	
	@Test
	public void testGetObservedValues() throws InterruptedException{
		AnovaForm form = new AnovaForm();
		form.setObservedValues("C:\\Users\\keith.rodgers\\Downloads\\observedValuesTestFile.txt");
		form.getObservedValuesArray();
	}
	
}
