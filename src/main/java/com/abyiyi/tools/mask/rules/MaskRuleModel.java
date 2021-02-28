package com.abyiyi.tools.mask.rules;

/**
 * Created by dongqingsong on 2020/2/13.
 */
public class MaskRuleModel {

    private String name;

    private String identifier;

    private String startindex;

    private String endindex;

    private String regex;

    private String executor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStartindex() {
        return startindex;
    }

    public void setStartindex(String startindex) {
        this.startindex = startindex;
    }

    public String getEndindex() {
        return endindex;
    }

    public void setEndindex(String endindex) {
        this.endindex = endindex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
