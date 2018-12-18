package com.datamining.algorithm;

import com.datamining.data.BaseRecord;
import com.datamining.data.Records;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * ID3
 *
 * @author Franco chen
 */
public class BaseSelector implements AttrSelector {

    @Override
    public Field select(Records records, Set<Field> attrs) {
        double entropyD = records.entropy();
        double maxGain = 0D;
        Field best = null;
        for(Field attr : attrs) {
            double entropyAttr = records.entropy(attr);
            double gain = entropyD - entropyAttr;
            if(gain > maxGain) {
                maxGain = gain;
                best = attr;
            }
        }
        return best;
    }
}
