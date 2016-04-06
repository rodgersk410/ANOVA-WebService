package hello;

import javax.validation.constraints.NotNull;

public class PersonForm {

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