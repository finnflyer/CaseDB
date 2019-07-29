package com.demo.model.issue;

import javax.persistence.*;

/**
 * Created by Admin on 2016/9/8.
 */
@Entity
@Table(name = "ctdissuephase", schema = "", catalog = "casedb")
public class IssuePhaseBean {
    private int phaseId;
    private String phaseCato;

    @Id
    @Column(name = "PhaseID")
    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }

    @Basic
    @Column(name = "PhaseCato")
    public String getPhaseCato() {
        return phaseCato;
    }

    public void setPhaseCato(String phaseCato) {
        this.phaseCato = phaseCato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssuePhaseBean that = (IssuePhaseBean) o;

        if (phaseId != that.phaseId) return false;
        if (phaseCato != null ? !phaseCato.equals(that.phaseCato) : that.phaseCato != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = phaseId;
        result = 31 * result + (phaseCato != null ? phaseCato.hashCode() : 0);
        return result;
    }
}
