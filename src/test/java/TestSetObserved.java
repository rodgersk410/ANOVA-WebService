import org.junit.Test;

import anovaApp.AnovaForm;

public class TestSetObserved {

	@Test
	public void testSetObservedValues(){
		AnovaForm form = new AnovaForm();
		form.setObservedValues("C:\\Users\\keith.rodgers\\Downloads\\observedValuesTestFile.txt");
	}
	
}
