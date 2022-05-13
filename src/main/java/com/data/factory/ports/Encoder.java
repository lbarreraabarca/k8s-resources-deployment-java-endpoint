package com.data.factory.ports;

import com.data.factory.exceptions.EncoderException;

public interface Encoder {
	public String decode(String request) throws EncoderException;
	public String encode(String request) throws EncoderException;
	
}