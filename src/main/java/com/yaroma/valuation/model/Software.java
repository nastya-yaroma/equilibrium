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
    private String license;
    private int licensePrice;
    private int copyPrice;
    private int supportPrice;
    private int total;
    private Date date;
    private int userId;
    
    public Software(){
        id = 0;
        name = DEFAULT_NAME;
        license  = DEFAULT_LICENCE;
        licensePrice = 0;
        copyPrice = 0;
        supportPrice = 0;
        total = 0;
        date = new Date();
        userId = 0;
    }
    
    public Software(String name, String licence, int licencePrice, int copyPrice, int supportPrice, int total, int userId){
        id = 0;
        this.name = name;
        this.license  = licence;
        this.licensePrice = licencePrice;
        this.copyPrice = copyPrice;
        this.supportPrice = supportPrice;
        this.total = total;
        date = new Date();
        this.userId = userId;
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

    @Column (name="LICENSE")    
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Column (name="LICENSE_PRICE")     
    public int getLicensePrice() {
        return licensePrice;
    }

    public void setLicensePrice(int licencePrice) {
        this.licensePrice = licencePrice;
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

    @Column (name="USER_ID") 
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
