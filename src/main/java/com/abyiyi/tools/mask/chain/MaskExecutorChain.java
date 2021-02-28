package com.abyiyi.tools.mask.chain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dongqingsong on 2020/2/12.
 */
public class MaskExecutorChain implements IMaskExecutorChain {

    public List<IMaskExector> maskExectorList = new LinkedList<IMaskExector>();

    public int position;

    private String report;

    public List<IMaskExector> getMaskExectorList() {
        return maskExectorList;
    }

    public void setMaskExectorList(List<IMaskExector> maskExectorList) {
        this.maskExectorList = maskExectorList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void execute() {
        this.maskExectorList.add(new MaskExecutor());
        this.maskExectorList.add(new MaskExecutor1());
        while (position< this.maskExectorList.size()){
            IMaskExector exec = this.getMaskExectorList().get(position);
            position =position +1;
            report = exec.execute(report);
        }
        //写文件

    }

    @Override
    public String getReport() {
        return report;
    }

    @Override
    public void setReport(String report) {
        this.report = report;

    }

    @Override
    public void setMaskExecutorList(List list) {
        this.maskExectorList = list;
    }


    public static void main(String[] args) {
        MaskExecutorChain maskExecutorChain = new MaskExecutorChain();
        maskExecutorChain.setReport(
                "<PB>1234567899876543212346</PB>\n" +
                "<PC>2222234567899876543212346</PC>\n" +
                "<PB>1234567899876543212346</PB>\n"
        );
        maskExecutorChain.execute();
        System.out.println(maskExecutorChain.getReport());
    }




}
