package com.yaroma.valuation.service;

import com.yaroma.valuation.model.Stat;
import java.util.List;

public interface StatService {
    
    void createStat(Stat stat);

    Stat readTask(int statId);

    void updateStat(Stat stat);

    void deleteStat(Stat stat);

    List getAllStats();
    
}
