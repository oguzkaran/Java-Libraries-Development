package org.csystem.util.grpc.error;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

/**
 * Utility class for handling gRPC error and converting them to runtime exceptions.
 * This class provides methods to simplify the conversion of common gRPC error statuses into runtime exceptions.
 * <p>Example usage:
 * <pre>{@code
 *  GrpcErrorUtil.invalidArgumentError(responseObserver, "User ID cannot be null");
 *  }</pre>
 * @author JavaApp2-Jan-2024 group
 * @version 1.0.0
 */
public final class GrpcErrorUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private GrpcErrorUtil()
    {
    }

    /**
     * Converts a gRPC {@link Status} to a runtime exception and passes it to the given {@link StreamObserver}.
     * @param streamObserver the stream observer that will receive the error
     * @param status the gRPC status code to use for the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */

    private static <T> void asRuntimeException(StreamObserver<T> streamObserver, Status status, String description)
    {
        streamObserver.onError(status.withDescription(description).asRuntimeException());
    }

    /**
     * Sends an {@link Status#INVALID_ARGUMENT} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void invalidArgumentError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.INVALID_ARGUMENT, description);
    }

    /**
     * Sends an {@link Status#OUT_OF_RANGE} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void outOfRangeError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.OUT_OF_RANGE, description);
    }

    /**
     * Sends an {@link Status#DEADLINE_EXCEEDED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void deadLineExceededError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.DEADLINE_EXCEEDED, description);
    }

    /**
     * Sends an {@link Status#NOT_FOUND} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void notFoundError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.NOT_FOUND, description);
    }

    /**
     * Sends an {@link Status#ALREADY_EXISTS} error to the provided stream observer.
     * @param streamObserver streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void alreadyExistsError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.ALREADY_EXISTS, description);
    }

    /**
     * Sends an {@link Status#PERMISSION_DENIED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void permissionDeniedError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.PERMISSION_DENIED, description);
    }

    /**
     * Sends an {@link Status#RESOURCE_EXHAUSTED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void resourceExhaustedError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.RESOURCE_EXHAUSTED, description);
    }

    /**
     * Sends an {@link Status#FAILED_PRECONDITION} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void failedPreconditionError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.FAILED_PRECONDITION, description);

    }

    /**
     * Sends an {@link Status#ABORTED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void abortedError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.ABORTED, description);
    }

    /**
     * Sends an {@link Status#UNIMPLEMENTED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void unImplementedError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.UNIMPLEMENTED, description);
    }

    /**
     * Sends an {@link Status#INTERNAL} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void internalError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.INTERNAL, description);
    }

    /**
     * Sends an {@link Status#UNAVAILABLE} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void unAvailableError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.UNAVAILABLE, description);
    }

    /**
     * Sends an {@link Status#DATA_LOSS} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void dataLossError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.DATA_LOSS, description);
    }

    /**
     * Sends an {@link Status#UNAUTHENTICATED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void unAuthenticatedError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.UNAUTHENTICATED, description);
    }

    /**
     * Sends an {@link Status#UNKNOWN} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void unKnownError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.UNKNOWN, description);
    }

    /**
     * Sends an {@link Status#CANCELLED} error to the provided stream observer.
     * @param streamObserver the stream observer that will receive the error
     * @param description a human-readable description of the error
     * @param <T> the type of the stream observer's response
     */
    public static <T> void CancelledError(StreamObserver<T> streamObserver, String description)
    {
        asRuntimeException(streamObserver, Status.CANCELLED, description);
    }
    //...
}
