package com.example.hospitalorganizer.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Consultation not found")
public class ConsultationNotFoundException extends EntityNotFoundException {
}
