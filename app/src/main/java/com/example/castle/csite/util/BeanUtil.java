package com.example.castle.csite.util;

import android.util.Property;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by castle on 16-9-10.
 * 用于得到属性的工具类
 * 注意，这里的类只能包含属性成员变量，否则就会抛出异常
 */
public class BeanUtil<Type> {
    private final Field[] mFields;
    private Class<Type> mTypeClass;
    private ArrayList<Property> mProperties=new ArrayList<>();

    public BeanUtil(Class<Type> typeClass) {
        mTypeClass = typeClass;
        mFields = mTypeClass.getDeclaredFields();
        initPropertyList();
    }

    /**
     * 初始化属性List
     */
    public void initPropertyList(){
        for (Field field : mFields) {
            field.setAccessible(true);
            Property property = Property.of(mTypeClass, field.getType(), field.getName());
            mProperties.add(property);
        }
    }

    public ArrayList<Property> getProperties() {
        return mProperties;
    }
}
