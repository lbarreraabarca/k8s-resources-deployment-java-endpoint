package com.data.factory.exceptions;

public class KubernetesServiceException extends Exception {
    
    private static final long serialVersionUID = 1L;

	public KubernetesServiceException(String message) {
		super(message);
	}

}
