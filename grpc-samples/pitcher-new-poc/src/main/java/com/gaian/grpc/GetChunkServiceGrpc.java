package com.gaian.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: ChunkService.proto")
public final class GetChunkServiceGrpc {

  private GetChunkServiceGrpc() {}

  public static final String SERVICE_NAME = "com.gaian.grpc.GetChunkService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.gaian.grpc.GetChunkRequest,
      com.gaian.grpc.GetChunkResponse> getGetChunkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getChunk",
      requestType = com.gaian.grpc.GetChunkRequest.class,
      responseType = com.gaian.grpc.GetChunkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gaian.grpc.GetChunkRequest,
      com.gaian.grpc.GetChunkResponse> getGetChunkMethod() {
    io.grpc.MethodDescriptor<com.gaian.grpc.GetChunkRequest, com.gaian.grpc.GetChunkResponse> getGetChunkMethod;
    if ((getGetChunkMethod = GetChunkServiceGrpc.getGetChunkMethod) == null) {
      synchronized (GetChunkServiceGrpc.class) {
        if ((getGetChunkMethod = GetChunkServiceGrpc.getGetChunkMethod) == null) {
          GetChunkServiceGrpc.getGetChunkMethod = getGetChunkMethod = 
              io.grpc.MethodDescriptor.<com.gaian.grpc.GetChunkRequest, com.gaian.grpc.GetChunkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.gaian.grpc.GetChunkService", "getChunk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gaian.grpc.GetChunkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gaian.grpc.GetChunkResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GetChunkServiceMethodDescriptorSupplier("getChunk"))
                  .build();
          }
        }
     }
     return getGetChunkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GetChunkServiceStub newStub(io.grpc.Channel channel) {
    return new GetChunkServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GetChunkServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GetChunkServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GetChunkServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GetChunkServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GetChunkServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getChunk(com.gaian.grpc.GetChunkRequest request,
        io.grpc.stub.StreamObserver<com.gaian.grpc.GetChunkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetChunkMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetChunkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gaian.grpc.GetChunkRequest,
                com.gaian.grpc.GetChunkResponse>(
                  this, METHODID_GET_CHUNK)))
          .build();
    }
  }

  /**
   */
  public static final class GetChunkServiceStub extends io.grpc.stub.AbstractStub<GetChunkServiceStub> {
    private GetChunkServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetChunkServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GetChunkServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetChunkServiceStub(channel, callOptions);
    }

    /**
     */
    public void getChunk(com.gaian.grpc.GetChunkRequest request,
        io.grpc.stub.StreamObserver<com.gaian.grpc.GetChunkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetChunkMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GetChunkServiceBlockingStub extends io.grpc.stub.AbstractStub<GetChunkServiceBlockingStub> {
    private GetChunkServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetChunkServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GetChunkServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetChunkServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.gaian.grpc.GetChunkResponse getChunk(com.gaian.grpc.GetChunkRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetChunkMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GetChunkServiceFutureStub extends io.grpc.stub.AbstractStub<GetChunkServiceFutureStub> {
    private GetChunkServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetChunkServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GetChunkServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetChunkServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gaian.grpc.GetChunkResponse> getChunk(
        com.gaian.grpc.GetChunkRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetChunkMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CHUNK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GetChunkServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GetChunkServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CHUNK:
          serviceImpl.getChunk((com.gaian.grpc.GetChunkRequest) request,
              (io.grpc.stub.StreamObserver<com.gaian.grpc.GetChunkResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GetChunkServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GetChunkServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gaian.grpc.ChunkService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GetChunkService");
    }
  }

  private static final class GetChunkServiceFileDescriptorSupplier
      extends GetChunkServiceBaseDescriptorSupplier {
    GetChunkServiceFileDescriptorSupplier() {}
  }

  private static final class GetChunkServiceMethodDescriptorSupplier
      extends GetChunkServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GetChunkServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GetChunkServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GetChunkServiceFileDescriptorSupplier())
              .addMethod(getGetChunkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
