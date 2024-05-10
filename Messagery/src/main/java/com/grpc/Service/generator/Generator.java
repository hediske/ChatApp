    package com.grpc.Service.generator;

    import java.util.Random;
    import java.util.UUID;

    import com.grpc.protoCompiled.Messaging.User;

    public class Generator {
         private Random random = new Random();
         private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
         private static String[] vowels = {"a", "e", "i", "o", "u"};
         private static String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", 
                                                 "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        private String generateString (int size ){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i =0 ; i<size ; i++){
                stringBuilder.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
            }
            return stringBuilder.toString();
        }

        private String generateName(){
            StringBuilder name = new StringBuilder();
            int length = random.nextInt(8) + 3;
            name.append(consonants[random.nextInt(consonants.length)].toUpperCase());
            for (int i = 1; i < length; i++) {
                if (i % 2 == 0) {
                    name.append(vowels[random.nextInt(vowels.length)]);
                } else {
                    name.append(consonants[random.nextInt(consonants.length)]);
                }
            }
            return name.toString();
        }

        private String generateID(){
            return UUID.randomUUID().toString();
        }
        public User newUser(){
            String name = generateName();
            String id = generateID();
            return User.newBuilder()
                .setId(id)
                .setName(name)
                .build();
        }
    }

