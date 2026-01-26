package org.java.reactive.sec09;

/*
    Ensure that the external service is up and running!
 */
public class Lec12FluxFlatMapExerciseAssignment {

    public static void main(String[] args)
    {
        public class Lec08ZipAssignment {

            public static void main(String[] args) {

                var client = new ExternalServiceClient();

                for (int i = 1; i < 10; i++) {
                    client.getProduct(i)
                            .subscribe(Util.subscriber());
                }

                Util.sleepSeconds(2);

            }
}
