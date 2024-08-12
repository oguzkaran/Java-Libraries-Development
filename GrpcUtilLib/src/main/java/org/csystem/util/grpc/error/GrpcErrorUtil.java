package org.csystem.util.grpc.error;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

/**
 * Utility class for gRPC error operations
 * @author JavaApp2-Jan-2024 group
 */
public final class GrpcErrorUtil {
    private GrpcErrorUtil()
    {
    }

    public static <T> void asRuntimeException(StreamObserver<T> streamObserver, Status status, String description)
    {
        streamObserver.onError(status.withDescription(description).asRuntimeException());
    }

    public static <T> void invalidArgumentError(StreamObserver<T> streamObserver, String description)
    {
        streamObserver.onError(Status.INVALID_ARGUMENT.withDescription(description).asRuntimeException());
    }

    public static <T> void outOfRangeError(StreamObserver<T> streamObserver, String description)
    {
        streamObserver.onError(Status.OUT_OF_RANGE.withDescription(description).asRuntimeException());
    }

    //...
}
