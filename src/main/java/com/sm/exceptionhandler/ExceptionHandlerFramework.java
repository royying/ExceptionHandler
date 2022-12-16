package com.sm.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sm.exceptionhandler.annotation.Handlers;
import com.sm.exceptionhandler.exception.handler.IExceptionHandler;

/**
 * This class is responsible to provide a single gateway to process exception
 *
 * @author Shubhashish.Mandal
 *
 */
@Component
public class ExceptionHandlerFramework<T> {

    private static final Log LOGGER = LogFactory
            .getLog(ExceptionHandlerFramework.class);

    @Autowired
    private ApplicationContext appContext;

    @SuppressWarnings("unchecked")
    private List<IExceptionHandler<T>> resolvedHandlers(
            final List<String> handlers) {
        final List<IExceptionHandler<T>> iExceptionHandlers = new ArrayList<>();
        CollectionUtils.forAllDo(handlers, new Closure() {
            @Override
            public void execute(final Object object) {
                final String handlerName = String.valueOf(object);
                try {
                    IExceptionHandler<T> iExceptionHandler = (IExceptionHandler<T>) appContext
                            .getBean(handlerName);
                    iExceptionHandlers.add(iExceptionHandler);
                } catch (Exception exp) {
                    LOGGER.error(exp);
                }
            }
        });

        /*
         * code for debug purpose
         * if (CollectionUtils.isEmpty(iExceptionHandlers)) {
         * IExceptionHandler<T> iExceptionHandler = (IExceptionHandler<T>)
         * appContext .getBean(DEFAULT_HANDLER);
         * System.out.println("--Default--" + iExceptionHandler);
         * iExceptionHandlers.add(iExceptionHandler); }
         */
        return iExceptionHandlers;
    }

    /**
     * Handle Exception by calling the list of mentioned exception handlers
     *
     * @param baseException exception that need to handled
     * @param exceptionHandlerList list of handlers to handle the exception
     */
    @SuppressWarnings("unchecked")
    private void handleException(final T baseException,
            final List<IExceptionHandler<T>> exceptionHandlerList) {
        CollectionUtils.forAllDo(exceptionHandlerList, new Closure() {

            @Override
            public void execute(final Object object) {
                try {
                    final IExceptionHandler<T> iExceptionHandler = (IExceptionHandler<T>) object;
                    iExceptionHandler.handleException(baseException);
                } catch (Exception exp) {
                    LOGGER.warn("Failed to call the handler due to " + exp);
                }
            }
        });
    }

    public void handleException(final T baseException,
            final String[] handlerNames) {
        List<String> handlers = fetchRegisteredHandler(baseException,
                handlerNames);
        if (!CollectionUtils.isEmpty(handlers)) {
            final List<IExceptionHandler<T>> exceptionHandlerList = resolvedHandlers(handlers);
            handleException(baseException, exceptionHandlerList);
        }

    }

    /**
     * Handle exception by extracting the registered handler for that exception
     *
     * @param baseException exception that need to handled
     */
    private List<String> fetchRegisteredHandler(final T baseException,
            final String[] handlerNames) {
        Handlers annotation = baseException.getClass().getAnnotation(
                Handlers.class);

        Set<String> handlerSet = new LinkedHashSet<String>(
                Arrays.asList(handlerNames));

        if (annotation != null) {
            handlerSet.addAll(Arrays.asList(annotation.value()));
        }
        return new ArrayList<>(handlerSet);
    }
}
