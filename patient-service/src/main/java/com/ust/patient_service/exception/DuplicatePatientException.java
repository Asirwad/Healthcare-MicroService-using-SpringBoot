package com.ust.patient_service.exception;

public class DuplicatePatientException extends RuntimeException{
    public DuplicatePatientException(String s){
        super(s);
    }
}
