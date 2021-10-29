package springtest.custom.label;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author Vin lan
 * @className MyNamespaceHandler
 * @description 创建一个Handler文件，扩展自NamespaceHandlerSupport，目的是将组件注册到Spring容器
 * @createTime 2021-08-18  14:05
 **/
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParse());
    }
}
