package com.example.castle.csite.util;

import android.util.Property;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * 对于json可能为空的对象进行手动转换,根据jsonElement的类型进行解析
 * @param <T> 要转换的类型
 */
public  class CommonBeanConverter<T> implements JsonSerializer<T>,
        JsonDeserializer<T> {
    Class<T> mTClass;

    public CommonBeanConverter(Class<T> TClass) {
        mTClass = TClass;
    }

    public JsonElement serialize(T src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        if (src == null) {
            return new JsonPrimitive("");
        } else {
            return new JsonPrimitive(src.toString());
        }
    }

    /**
     * 反序列化json字符串
     * @param json json元素
     * @param typeOfT 要反序列化的类型
     * @param context 上下文
     * @return 反序列化完成的类型，如果出现异常返回null
     * @throws JsonParseException json解析出错时抛出
     */
    public T deserialize(JsonElement json, Type typeOfT,
                         JsonDeserializationContext context)
            throws JsonParseException {
        try {
            T t = mTClass.newInstance();
            BeanUtil<T> beanUtil = new BeanUtil<>(mTClass);
            ArrayList<Property> properties = beanUtil.getProperties();
            //如果整个json是null或者为空，直接返回null
            if (json.isJsonNull()) return null;
            for (Map.Entry<String, JsonElement> entry : ((JsonObject) json).entrySet()) {
                for (Property property : properties) {
                    setUpProperty(t, entry, property,context);
                }
            }
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 填充属性
     * @param t 类型
     * @param entry json条目
     * @param property 属性
     */
    private void setUpProperty(T t, Map.Entry<String, JsonElement> entry, Property property,JsonDeserializationContext context) {
        if (property.getName().equals(entry.getKey())) {
            //是基本类型，在这里处理逻辑
            JsonElement element = entry.getValue();
            //如果碰到null，设置相应属性为null
            if(element.isJsonNull()) {
                property.set(t,null);
            //如果是基本类型，直接转换
            } else if (element.isJsonPrimitive()) {
                setPropertyPrimitive(t, property, element);
            } else  {
                //是对象或者数组，由上下文解析
                Object deserialize = context.deserialize(element, property.getType());
                property.set(t,deserialize);
            }
        }
    }

    /**
     * 用于设置属性基础类型的值
     * @param t 类型
     * @param property 属性
     * @param element 元素
     */
    private void setPropertyPrimitive(T t, Property property, JsonElement element) {
        if (property.getType().getSimpleName().equals("String")) {
            property.set(t, element.getAsString());
        } else if (element.getAsJsonPrimitive().isBoolean()) {
            property.set(t, element.getAsBoolean());
        } else {
            setPropertyNum(t, property, element);
        }
    }

    /**
     * 用于设置属性数字
     * @param t 类型
     * @param property 属性
     * @param element 元素
     */
    private void setPropertyNum(T t, Property property, JsonElement element) {
        String numType = property.getType().getSimpleName();
        switch (numType) {
            case "int":
                property.set(t, element.getAsInt());
                break;
            case "long":
                property.set(t, element.getAsLong());
                break;
            case "double":
                property.set(t, element.getAsDouble());
                break;
            case "float":
                property.set(t, element.getAsFloat());
                break;
            case "short":
                property.set(t, element.getAsShort());
                break;
            case "BigDecimal":
                property.set(t, element.getAsBigDecimal());
                break;
            case "BigInteger":
                property.set(t, element.getAsBigInteger());
                break;
        }
    }
}