package com.yaroma.valuation.service;

import com.yaroma.valuation.model.Software;
import java.util.List;

public interface SoftwareService {
    
    void createSoftware(Software software);

    Software readSoftware(int softwareId);

    void updateSoftware(Software software);

    void deleteSoftware(Software software);

    List getAllSoftwares();
    
}
