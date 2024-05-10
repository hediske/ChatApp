@echo off
for %%f in (proto\*.proto) do (
  node_modules\.bin\proto-loader-gen-types --longs String --enums String --defaults --oneofs --grpcLib @grpc/grpc-js --outDir src\protoCompiled\ "%%f"
)