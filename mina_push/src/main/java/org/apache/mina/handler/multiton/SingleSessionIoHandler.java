/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.handler.multiton;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * A session handler without an {@link org.apache.mina.core.session.IoSession} parameter for simplicity.
 * <p>
 * A {@link SingleSessionIoHandler} is similar to an {@link org.apache.mina.core.service.IoHandler} with
 * the notable difference that a {@link SingleSessionIoHandler} is used only
 * by one session at a time. Thus, there is no {@link org.apache.mina.core.session.IoSession} parameter in
 * the methods of this interface.
 * </p>
 * <p>
 * Because events are passed to the session in order, it is possible to store
 * conversational state as instance variables in this object.
 * </p>
 *
 * WARNING: This class is badly named as the actual {@link org.apache.mina.core.service.IoHandler} implementor
 * is in fact the {@link SingleSessionIoHandlerDelegate}.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
@Deprecated
public interface SingleSessionIoHandler {

    /**
     * Invoked when the session is created. Initialize default socket parameters
     * and user-defined attributes here.
     *
     * @throws Exception
     * @see org.apache.mina.core.service.IoHandler#sessionCreated(org.apache.mina.core.session.IoSession)
     */
    void sessionCreated() throws Exception;

    /**
     * Invoked when the connection is opened. This method is not invoked if the
     * transport type is UDP.
     *
     * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
     */
    void sessionOpened() throws Exception;

    /**
     * Invoked when the connection is closed. This method is not invoked if the
     * transport type is UDP.
     *
     * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
     */
    void sessionClosed() throws Exception;

    /**
     * Invoked when the connection is idle. Refer to {@link org.apache.mina.core.session.IdleStatus}. This
     * method is not invoked if the transport type is UDP.
     *
     * @param status the type of idleness
     * @see org.apache.mina.core.service.IoHandler#sessionIdle(org.apache.mina.core.session.IoSession, org.apache.mina.core.session.IdleStatus)
     */
    void sessionIdle(IdleStatus status) throws Exception;

    /**
     * Invoked when any exception is thrown by user {@link org.apache.mina.core.service.IoHandler}
     * implementation or by MINA. If <code>cause</code> is instanceof
     * {@link java.io.IOException}, MINA will close the connection automatically.
     *
     * @param cause the caught exception
     * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession, Throwable)
     */
    void exceptionCaught(Throwable cause) throws Exception;

    void inputClosed(IoSession session);

    /**
     * Invoked when protocol message is received. Implement your protocol flow
     * here.
     *
     * @param message the received message
     * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, Object)
     */
    void messageReceived(Object message) throws Exception;

    /**
     * Invoked when protocol message that user requested by
     * {@link org.apache.mina.core.session.IoSession#write(Object)} is sent out actually.
     *
     * @param message the sent message
     * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, Object)
     */
    void messageSent(Object message) throws Exception;

}
