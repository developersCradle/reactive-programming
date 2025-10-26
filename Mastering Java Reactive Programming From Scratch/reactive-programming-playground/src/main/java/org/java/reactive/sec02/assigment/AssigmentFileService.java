package org.java.reactive.sec02.assigment;

import org.java.reactive.sec02.Lec06MonoFromCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AssigmentFileService implements FileService {

    private static final Logger log = LoggerFactory.getLogger(AssigmentFileService.class);
    private static final Path PATH = Path.of("src/main/resources/sec02");

    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return null;
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return null;
    }

    private void writeFile(String filename, String content){
        try {
            log.info("Writing to the file.");
            Files.writeString(PATH.resolve(filename), content);
        } catch (IOException e) {
            log.info("Something went wrong with the writing to the file!");
            throw new RuntimeException(e);
        }
    }



}
