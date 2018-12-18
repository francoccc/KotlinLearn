package com.datamining.algorithm;

import com.datamining.data.BaseRecord;
import com.datamining.data.Records;

import java.lang.reflect.Field;
import java.util.Set;

/**
 *
 * @author Franco chen
 */
public interface AttrSelector {

    Field select(Records records, Set<Field> attrs);
}
