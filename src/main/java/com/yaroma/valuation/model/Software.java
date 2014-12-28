package com.yaroma.valuation.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity 
@Table (name="SOFTWARE")
public class Software {
    
    public static final String DEFAULT_NAME = "default name";
    public static final String DEFAULT_LICENCE = "default licence";
    
    private int id;
    private String name;
    private String licence;
    private int licencePrice;
    private int copyPrice;
    private int supportPrice;
    private int total;
    private Date date;
    
    public Software(){
        id = 0;
        name = DEFAULT_NAME;
        licence  = DEFAULT_LICENCE;
        licencePrice = 0;
        copyPrice = 0;
        supportPrice = 0;
        total = 0;
        date = new Date();        
    }
    
    public Software(String name, String licence, int licencePrice, int copyPrice, int supportPrice, int total){
        id = 0;
        this.name = name;
        this.licence  = licence;
        this.licencePrice = licencePrice;
        this.copyPrice = copyPrice;
        this.supportPrice = supportPrice;
        this.total = total;
        date = new Date();
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name="NAME")    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name="LICENCE")    
    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    @Column (name="LICENCE_PRICE")     
    public int getLicencePrice() {
        return licencePrice;
    }

    public void setLicencePrice(int licencePrice) {
        this.licencePrice = licencePrice;
    }

    @Column (name="COPY_PRICE")     
    public int getCopyPrice() {
        return copyPrice;
    }

    public void setCopyPrice(int copyPrice) {
        this.copyPrice = copyPrice;
    }

    @Column (name="SUPPORT_PRICE")     
    public int getSupportPrice() {
        return supportPrice;
    }

    public void setSupportPrice(int supportPrice) {
        this.supportPrice = supportPrice;
    }

    @Column (name="TOTAL")     
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Column (name="DATE")     
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
