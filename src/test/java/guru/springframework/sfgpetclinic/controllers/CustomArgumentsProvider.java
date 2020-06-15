package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomArgumentsProvider  implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of("CT", 10, 10), Arguments.of("MSU", 20, 2), Arguments.of("BFN", 30, 30));
    }
}
