package com.demo.model.issue;

import javax.persistence.*;

/**
 * Created by finnf on 2019/1/18.
 */
@Entity
@Table(name = "ctdcomponet", schema = "", catalog = "casedb")
public class ComponetBean {
    private String componet;
    private String componentName;

    @Id
    @Basic
    @Column(name = "Componet")
    public String getComponet() {
        return componet;
    }

    public void setComponet(String componet) {
        this.componet = componet;
    }

    @Basic
    @Column(name = "ComponentName")
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

}
