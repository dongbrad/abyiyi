package com.abyiyi.tools.mask;

import com.abyiyi.tools.mask.chain.IMaskExecutorChain;
import com.abyiyi.tools.mask.convert.ConvertMaskChain;
import com.abyiyi.tools.mask.convert.ConvertMaskExecutor;

import java.util.List;

/**
 * Created by dongqingsong  2020/2/13.
 */
public class ConvertService {

    public static void main(String[] args) {
        ConvertMaskExecutor cme = new ConvertMaskExecutor();
        List list =cme.buildMaskExecutor("CRCC");
        ConvertMaskChain cc = new ConvertMaskChain();
        IMaskExecutorChain mec = cc.getMaskExectorInstance("CRCC");
        mec.setMaskExecutorList(list);
        mec.setReport("<PB>12233345t563456765745</PB><PDDD>2222223332222222</PDDD><PB>12233345t563456765746</PB>");
        mec.execute();
        System.out.println(mec.getReport());
    }

}
