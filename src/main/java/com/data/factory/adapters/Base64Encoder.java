package com.data.factory.adapters;

import java.util.Base64;

import com.data.factory.exceptions.EncoderException;
import com.data.factory.ports.Encoder;

public class Base64Encoder implements Encoder {
	
	private void validateRequest(String request) throws EncoderException {
		if (request == null || request.isEmpty()) {
			throw new EncoderException("request cannot be null or empty!");
		}
	}
	
	@Override
	public String decode(String request) throws EncoderException {
		validateRequest(request);
		try {
			return new String(Base64.getDecoder().decode(request));
		} catch(Exception ex) {
			throw new EncoderException("Error while decoding base64 message!");
		}
	}

	@Override
	public String encode(String request) throws EncoderException {
		validateRequest(request);
		return Base64.getEncoder().encodeToString(request.getBytes());
	}

}