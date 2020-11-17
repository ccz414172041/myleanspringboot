package com.ccz.canaldemo.config;

/**
 * @PackageName: com.meetyou.idx.config
 * @ClassName: SortEnum
 * @Author: eddia
 * @date: 2020/1/7 13:53
 * @Description:
 * @Corporation: meetyou
 */
public enum SortEnum {

    DESC("升序",0),ASC("降序", 1);


    private String label;
    private Integer value;

    private SortEnum(String _label, Integer _value){
        this.label = _label;
        this.value = _value;
    }

    public Integer getValue(){
        return this.value;
    }
    public String getLabel(){
        return this.label;
    }
}
