package io.data.encryption.aspect;

import io.data.encryption.annotations.Encrypt;
import io.data.encryption.utils.EncryptUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@CommonsLog
@Aspect
@Component
@EnableAspectJAutoProxy
public class AspectRepository {

    public AspectRepository() {
        log.info("AspectRepository Started");
    }


    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void inRepositoryLayer() {}

    @Around("inRepositoryLayer()")
    public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        Object[] methodArgs = pjp.getArgs();
        int numArgs = methodArgs.length;
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();
        for (int i = 0; i < numArgs; i++) {
            Annotation[] annotations = annotationMatrix[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Encrypt.class) {
                    methodArgs[i] = EncryptUtils.encrypt((String) pjp.getArgs()[i]);
                }
            }
        }
        return pjp.proceed(methodArgs);
    }

}
