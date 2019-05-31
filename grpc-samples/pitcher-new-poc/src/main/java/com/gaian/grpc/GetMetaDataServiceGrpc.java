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
    comments = "Source: ContentService.proto")
public final class GetMetaDataServiceGrpc {

  private GetMetaDataServiceGrpc() {}

  public static final String SERVICE_NAME = "com.gaian.grpc.GetMetaDataService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<GetMetaDataRequest,
      GetMetaDataResponse> getGetMetaDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMetaData",
      requestType = GetMetaDataRequest.class,
      responseType = GetMetaDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<GetMetaDataRequest,
      GetMetaDataResponse> getGetMetaDataMethod() {
    io.grpc.MethodDescriptor<GetMetaDataRequest, GetMetaDataResponse> getGetMetaDataMethod;
    if ((getGetMetaDataMethod = GetMetaDataServiceGrpc.getGetMetaDataMethod) == null) {
      synchronized (GetMetaDataServiceGrpc.class) {
        if ((getGetMetaDataMethod = GetMetaDataServiceGrpc.getGetMetaDataMethod) == null) {
          GetMetaDataServiceGrpc.getGetMetaDataMethod = getGetMetaDataMethod = 
              io.grpc.MethodDescriptor.<GetMetaDataRequest, GetMetaDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.gaian.grpc.GetMetaDataService", "getMetaData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GetMetaDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  GetMetaDataResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GetMetaDataServiceMethodDescriptorSupplier("getMetaData"))
                  .build();
          }
        }
     }
     return getGetMetaDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GetMetaDataServiceStub newStub(io.grpc.Channel channel) {
    return new GetMetaDataServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GetMetaDataServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GetMetaDataServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GetMetaDataServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GetMetaDataServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GetMetaDataServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getMetaData(GetMetaDataRequest request,
                            io.grpc.stub.StreamObserver<GetMetaDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMetaDataMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMetaDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                GetMetaDataRequest,
                GetMetaDataResponse>(
                  this, METHODID_GET_META_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class GetMetaDataServiceStub extends io.grpc.stub.AbstractStub<GetMetaDataServiceStub> {
    private GetMetaDataServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetMetaDataServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GetMetaDataServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetMetaDataServiceStub(channel, callOptions);
    }

    /**
     */
    public void getMetaData(GetMetaDataRequest request,
                            io.grpc.stub.StreamObserver<GetMetaDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMetaDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GetMetaDataServiceBlockingStub extends io.grpc.stub.AbstractStub<GetMetaDataServiceBlockingStub> {
    private GetMetaDataServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetMetaDataServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GetMetaDataServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetMetaDataServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public GetMetaDataResponse getMetaData(GetMetaDataRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMetaDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GetMetaDataServiceFutureStub extends io.grpc.stub.AbstractStub<GetMetaDataServiceFutureStub> {
    private GetMetaDataServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GetMetaDataServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GetMetaDataServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GetMetaDataServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<GetMetaDataResponse> getMetaData(
        GetMetaDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMetaDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_META_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GetMetaDataServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GetMetaDataServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_META_DATA:
          serviceImpl.getMetaData((GetMetaDataRequest) request,
              (io.grpc.stub.StreamObserver<GetMetaDataResponse>) responseObserver);
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

  private static abstract class GetMetaDataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GetMetaDataServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ContentService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GetMetaDataService");
    }
  }

  private static final class GetMetaDataServiceFileDescriptorSupplier
      extends GetMetaDataServiceBaseDescriptorSupplier {
    GetMetaDataServiceFileDescriptorSupplier() {}
  }

  private static final class GetMetaDataServiceMethodDescriptorSupplier
      extends GetMetaDataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GetMetaDataServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GetMetaDataServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GetMetaDataServiceFileDescriptorSupplier())
              .addMethod(getGetMetaDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
