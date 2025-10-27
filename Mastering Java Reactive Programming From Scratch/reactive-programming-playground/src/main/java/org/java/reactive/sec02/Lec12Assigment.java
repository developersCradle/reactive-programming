package org.java.reactive.sec02;

import org.java.reactive.common.Util;
import org.java.reactive.sec02.assigment.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec12Assigment {

    private static final Logger log = LoggerFactory.getLogger(Lec12Assigment.class);

    public static void main(String[] args) {

        var fileservice = new FileServiceImpl();

        fileservice.write("file.txt", "This is the test file!")
                .subscribe(Util.subscriber());

        fileservice.read("file.txt")
                .subscribe(Util.subscriber());


    }

}
