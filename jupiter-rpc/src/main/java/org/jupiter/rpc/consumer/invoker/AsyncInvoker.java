/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jupiter.rpc.consumer.invoker;

import org.jupiter.common.util.Reflects;
import org.jupiter.rpc.consumer.dispatcher.Dispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Asynchronous call, {@link AsyncInvoker#invoke(Object, Method, Object[])}
 * returns a default value of the corresponding method.
 *
 * jupiter
 * org.jupiter.rpc.consumer.invoker
 *
 * @author jiachun.fjc
 */
public class AsyncInvoker implements InvocationHandler {

    private final Dispatcher dispatcher;

    public AsyncInvoker(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        dispatcher.dispatch(method, args);
        return Reflects.getTypeDefaultValue(method.getReturnType());
    }
}
