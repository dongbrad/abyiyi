package com.abyiyi.tools.mask.chain;

import java.util.List;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public interface IMaskExecutorChain {

    void execute();

    String getReport();

    void setReport(String report);

    void setMaskExecutorList(List list);



}
