package org.kamegatze.dbeditor.backend.info.metadata.utility;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class ReflectionUtility {

    public Optional<ParameterizedType> getInterfacesWithGenerics(final Class<?> fromInterfaces) {
        for (Type interfaceGeneric : fromInterfaces.getGenericInterfaces()) {
            if (interfaceGeneric instanceof ParameterizedType parameterizedType) {
                return Optional.of(parameterizedType);
            }
        }

        return Optional.ofNullable(
                getInterfaceByClazzRecursive(
                        fromInterfaces.getInterfaces()
                )
        );
    }

    private ParameterizedType getInterfaceByClazzRecursive(Class<?>[] fromInterfaces) {
        for (Class<?> fromInterface : fromInterfaces) {
            for (Type interfaceGeneric : fromInterface.getGenericInterfaces()) {
                if (interfaceGeneric instanceof ParameterizedType parameterizedType) {
                        return parameterizedType;
                }
            }
            return getInterfaceByClazzRecursive(fromInterface.getInterfaces());
        }
        return null;
    }

}
