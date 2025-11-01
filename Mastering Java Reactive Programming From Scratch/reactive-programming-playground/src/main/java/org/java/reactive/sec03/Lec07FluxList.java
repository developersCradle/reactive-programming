package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.java.reactive.sec03.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec07FluxList {

    private static final Logger log = LoggerFactory.getLogger(Lec07FluxList.class);

    public static void main(String[] args)
    {
        // This example there will be long waiting line and we don't know what Producer will be doing.
        var list = NameGenerator.getNameList(10);
        System.out.println(list);



        // This is the reactive way of logging
//        NameGenerator.getNameListAsReactiveSecond(10)
//                .subscribe(Util.subscriber());







    }
}