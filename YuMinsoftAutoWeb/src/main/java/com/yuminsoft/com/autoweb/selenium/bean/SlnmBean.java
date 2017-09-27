package com.yuminsoft.com.autoweb.selenium.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "slnm")
@XmlType(propOrder = {   
  "slnmname",
  "casebeanlist"
})
public class SlnmBean {
    //
    private String slnmname;
    
    @XmlElementWrapper(name = "casebeanlist")
    @XmlElement(name = "slnmcase")
    List<SlnmCaseBean> casebeanlist;
    
    public String getSlnmname() {
        return slnmname;
    }

    public void setSlnmname(String slnmname) {
        this.slnmname = slnmname;
    }

    public List<SlnmCaseBean> getCasebeanlist() {
        return casebeanlist;
    }

    public void setCasebeanlist(List<SlnmCaseBean> casebeanlist) {
        this.casebeanlist = casebeanlist;
    }
    
}
