./node_modules/.bin/proto-loader-gen-types --grpcLib=@grpc/grpc-js --outDir=src/protoCompiled/ ./proto/*.proto

mkdir -p ./src/protoCompiled
cd ./proto
protoc -I=. ./*.proto \
  --js_out=import_style=commonjs:.././src/protoCompiled \
  --grpc-web_out=import_style=typescript,mode=grpcwebtext:.././src/protoCompiled
