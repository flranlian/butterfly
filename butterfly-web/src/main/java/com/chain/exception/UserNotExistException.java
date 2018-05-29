package com.chain.exception;

//@EqualsAndHashCode(callSuper = true)
//@Data
public class UserNotExistException extends RuntimeException {

    private Integer id;

    public UserNotExistException(Integer id) {
        super("user not exist.");
        this.id = id;
    }
}