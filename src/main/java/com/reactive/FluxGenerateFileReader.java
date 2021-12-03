package com.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author shashshe
 */
public class FluxGenerateFileReader {

    public Callable<BufferedReader> openFile(Path path) {
        System.out.println("Opening file");
        return () -> Files.newBufferedReader(path);
    }

    public BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> emitLines() {
        return (br, sink) -> {
            try {
                String line = br.readLine();
                if (line == null) {
                    sink.complete();
                }else{
                    sink.next(line);
                }

            } catch (IOException ioException) {
                sink.error(ioException);
            }
            return br;
        };
    }

    public Consumer<BufferedReader> closeReader() {
        return br -> {
            try {
                br.close();
                System.out.println("Closed file");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
    }

    public Flux<String> readFile(Path path) {
        return Flux.generate(openFile(path), emitLines(), closeReader());
    }

    public static void main(String[] args) {
        String pathSrt = "/Users/shashshe/workspace/java-reactive/src/main/resources/test-file.txt";
        FluxGenerateFileReader fluxGenerateFileReader = new FluxGenerateFileReader();
        fluxGenerateFileReader.readFile(Paths.get(pathSrt))
                .take(10)
        .subscribe(ReactiveUtil.onNext(),ReactiveUtil.onError(),ReactiveUtil.onComplete());
    }
}
