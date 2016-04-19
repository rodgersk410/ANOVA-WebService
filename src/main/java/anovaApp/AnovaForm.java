package anovaApp;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AnovaForm {
	
    @NotNull
    private String observedValuesFileName;
    
    @NotNull
    private Double[][] observedValues;

    @NotNull
    private Integer numGenes;
    
    @NotNull
    private Integer numSelectedGroups;

    @NotNull
    private Double pvalueth;
    
    @NotNull
    private Integer pValueEstimation;
    
    @NotNull
    private Integer permutationsNumber;
    
    @NotNull
    private Integer falseDiscoveryRateControl;

    @NotNull
    private Float falseSignificantGenesLimit;

    public String getObservedValuesFileName() {
        return observedValuesFileName;
    }

    public void setObservedValuesFileName(String observedValuesFileName) {
        this.observedValuesFileName = observedValuesFileName;
    }

    public Double[][] getObservedValues() {
    	File f = new File(observedValuesFileName);
    	List<String> lines = Files.readAllLines(f);
        return observedValues;
    }

    public void setObservedValues(Double[][] observedValues) {
        this.observedValues = observedValues;
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
    public Float getFalseSignificantGenesLimit() {
        return falseSignificantGenesLimit;
    }

    public void setFalseSignificantGenesLimit(Float falseSignificantGenesLimit) {
        this.falseSignificantGenesLimit = falseSignificantGenesLimit;
    }
    
}