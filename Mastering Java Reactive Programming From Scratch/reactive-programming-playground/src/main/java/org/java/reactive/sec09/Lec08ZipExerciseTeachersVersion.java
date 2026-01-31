package org.java.reactive.sec09;

import org.java.reactive.common.Util;
import org.java.reactive.sec09.client.ExternalServiceClient;

/*
    Ensure that the external service is up and running!
 */
public class Lec08ZipExerciseTeachersVersion
{

    public static void main(String[] args)
    {
        var client = new ExternalServiceClient();

        for(int i = 1; i <= 10; i++){
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(4);
    }
}
