package org.quange.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.quange.springframework.beans.factory.config.BeanDefinition;
import org.quange.springframework.context.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Lan
 * @createTime 2023-08-29  11:22
 **/
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
