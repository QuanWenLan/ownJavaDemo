package springtest.custom.label;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author Vin lan
 * @className UserBeanDefinitionParse
 * @description 用来解析 XSD 文件中的定义和组件定义
 * @createTime 2021-08-18  14:00
 **/
public class UserBeanDefinitionParse extends AbstractSingleBeanDefinitionParser {
    // element 对应的类
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    // 从 element 中解析并提取对应的元素

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
//        super.doParse(element, builder);
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
        // 将提取到的数据放入到 builder 中
        if (StringUtils.hasText(userName)) {
            builder.addPropertyValue("userName", userName);
        }

        if (StringUtils.hasText(email)) {
            builder.addPropertyValue("email", email);
        }
    }
}
