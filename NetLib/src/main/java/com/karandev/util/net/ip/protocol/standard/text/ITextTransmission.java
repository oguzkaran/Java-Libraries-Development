package com.karandev.util.net.ip.protocol.standard.text;

import com.karandev.util.net.IReceiver;
import com.karandev.util.net.ISender;

/**
 * Interface for text-based transmission operations, combining sending and receiving of text data.
 * <p>Extends both {@link ISender} and {@link IReceiver} for unified text communication in network protocols.</p>
 * <p>Copyleft (c) 1993 by C and System Programmers Association (CSD) All Rights Free</p>
 *
 * @author JavaApp2-Jan-2024 Group
 * @version 1.0.0
 */
public interface ITextTransmission extends ISender<String>, IReceiver<String> {

}
