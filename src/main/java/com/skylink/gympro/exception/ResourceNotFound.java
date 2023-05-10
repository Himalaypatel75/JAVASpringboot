package com.skylink.gympro.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound() {
        super("something went wrong! - general exception");
    }
}
