/*----------------------------------------------------------------------
FILE        : IAction.java
AUTHOR      : Oğuz Karan
LAST UPDATE : 30.09.2020

IAction functional interface

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.exception;

/**
 * A functional interface that represents an action that can be executed.
 * This interface has a single abstract method {@code run()} which can throw an {@code Exception}.
 * @see Exception
 */

@FunctionalInterface
public interface IAction {
    void run() throws Exception;
}
