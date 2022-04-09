package com.example.password.Componment;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
//List各属性排序组件
public class SortListUtil<E> {

    public void Sort(List<E> list, final String method, final String order) {
        Collections.sort(list, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                int ret = 0;
                try {
                    Method method1 = o1.getClass().getMethod(method, null);
                    Method method2 = o2.getClass().getMethod(method, null);
                    //倒序
                    if (StringUtils.isNotEmpty(order) && "desc".equalsIgnoreCase(order)) {
                        ret = method2.invoke(o2, null).toString().compareTo(method1.invoke(o1, null)
                                .toString());
                    }
                    else {
                        ret = method1.invoke(o1, null).toString()
                                .compareTo(method2.invoke(o2, null).toString());
                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });
    }
}