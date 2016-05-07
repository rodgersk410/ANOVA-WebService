package anovaApp;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AnovaForm {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	private String observedValuesFileName;

	private List<String> observedValues;
	private float[][] observedValuesArray;
	
	public int[] groupAssignments;

//	@NotNull
	private int numGenes;

//	@NotNull
	private int numSelectedGroups;

//	@NotNull
	private double pvalueth;

//	@NotNull
	private int pValueEstimation;

//	@NotNull
	private int permutationsNumber;

//	@NotNull
	private int falseDiscoveryRateControl;

//	@NotNull
	private float falseSignificantGenesLimit;

	public String getObservedValuesFileName() {
		return observedValuesFileName;
	}

	public void setObservedValuesFileName(String observedValuesFileName) {
		this.observedValuesFileName = observedValuesFileName;
	}

	public List<String> getObservedValues() {
		return observedValues;
	}
	
	public float[][] getObservedValuesArray() {
		return observedValuesArray;
	}

	public void setObservedValues(String observedValuesFileName) throws InterruptedException {
		try {
			log.info("Filename is: " + observedValuesFileName);
			observedValues = Files.readAllLines(Paths.get(observedValuesFileName));
			String stringObservedValues = "";
			for (String s : observedValues) {
				stringObservedValues += s + "\t";
			}
			
			// Split on this delimiter
			String[] rows = stringObservedValues.split("},\\{");
			for (int i = 0; i < rows.length; i++) {
				// Remove any beginning and ending braces and any white spaces
				rows[i] = rows[i].replace("{{", "").replace("}}", "").replaceAll(" ", "").replaceAll("\t", "");
			}

			// Get the number of columns in a row
			int numberOfColumns = rows[0].split(",").length;

			// Setup matrix
			String[][] matrix = new String[rows.length][numberOfColumns];
			float[][] observedValues = new float[rows.length][numberOfColumns];

			// Populate matrix
			for (int i = 0; i < rows.length; i++) {
				matrix[i] = rows[i].split(",");
				for (int j = 0; j < matrix[i].length; j++) {
					observedValues[i][j] = Float.parseFloat(matrix[i][j]);
					
				}
			}
			
			observedValuesArray=observedValues;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
	}
	
	public int[] getGroupAssignments() {
		return groupAssignments;
	}

	public void setGroupAssignments(int[] groupAssignments) {
		this.groupAssignments = groupAssignments;
	}

	public Integer getNumGenes() {
		return numGenes;
	}

	public void setNumGenes(Integer numGenes) {
		this.numGenes = numGenes;
	}

	public Integer getNumSelectedGroups() {
		return numSelectedGroups;
	}

	public void setNumSelectedGroups(Integer numSelectedGroups) {
		this.numSelectedGroups = numSelectedGroups;
	}

	public Double getPvalueth() {
		return pvalueth;
	}

	public void setPvalueth(Double pvalueth) {
		this.pvalueth = pvalueth;
	}

	public Integer getPValueEstimation() {
		return pValueEstimation;
	}

	public void setPValueEstimation(Integer pValueEstimation) {
		this.pValueEstimation = pValueEstimation;
	}

	public Integer getPermutationsNumber() {
		return permutationsNumber;
	}

	public void setPermutationsNumber(Integer permutationsNumber) {
		this.permutationsNumber = permutationsNumber;
	}

	public Integer getFalseDiscoveryRateControl() {
		return falseDiscoveryRateControl;
	}

	public void setFalseDiscoveryRateControl(Integer falseDiscoveryRateControl) {
		this.falseDiscoveryRateControl = falseDiscoveryRateControl;
	}

	public float getFalseSignificantGenesLimit() {
		return falseSignificantGenesLimit;
	}

	public void setFalseSignificantGenesLimit(float falseSignificantGenesLimit) {
		this.falseSignificantGenesLimit = falseSignificantGenesLimit;
	}

}