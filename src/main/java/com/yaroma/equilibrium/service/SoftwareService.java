package com.yaroma.equilibrium.service;

import com.yaroma.equilibrium.model.Software;
import java.util.List;

public interface SoftwareService {
    
    void createSoftware(Software software);

    Software readSoftware(int softwareId);

    void updateSoftware(Software software);

    void deleteSoftware(Software software);

    List getAllSoftwares();
    
    Software getSoftwareByName(String name);
}
