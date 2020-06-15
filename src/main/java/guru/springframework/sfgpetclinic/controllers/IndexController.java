package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.exceptions.ValueNotFoundException;

public class IndexController {

    public String index(){

        return "index";
    }

    public String oopsHandler(){
        throw new ValueNotFoundException("Value has not been implemented or is not found");
    }
}
