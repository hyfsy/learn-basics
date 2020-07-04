package basics.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * @author baB_hyf
 * @date 2020/07/04
 */
// 表示支持的java版本
@SupportedSourceVersion(SourceVersion.RELEASE_8)
// 表示支持处理的注解
@SupportedAnnotationTypes("basics.processor.AllGetter")
public class AllGetterProcessor extends AbstractProcessor {

    /**
     * 获取编译时的一些环境信息
     *
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println(processingEnv);
    }

    /**
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        StringBuilder sb = new StringBuilder();
        sb.append("package basics.processor;\n\n");
        sb.append("public class GeneratedClass {\n\n");
        sb.append("\tpublic String getMessage() {\n");
        sb.append("\t\treturn \"");

        for (Element element : roundEnv.getElementsAnnotatedWith(AllGetter.class)) {
            String objectType = element.getSimpleName().toString();
            sb.append(objectType).append(" say hello!\\n");
        }

        sb.append("\";\n").append("\t}\n").append("}\n");

        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile("basics.processor.GeneratedClass");
            Writer writer = source.openWriter();
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // e.printStackTrace();
            // return false;
        }

        return true;
    }
}
