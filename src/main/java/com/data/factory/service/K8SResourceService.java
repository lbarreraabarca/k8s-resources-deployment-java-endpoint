package com.data.factory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.data.factory.models.Contract;
import com.data.factory.ports.Encoder;
import com.data.factory.ports.KubernetesManager;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.data.factory.exceptions.ServiceException;

public class K8SResourceService {
    private static Logger mylog = LoggerFactory.getLogger(K8SResourceService.class);

    private Encoder encoder;
    private KubernetesManager kubernetesManager;

    private static final String DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSSSS";

    public K8SResourceService(Encoder encoder, KubernetesManager kubernetesManager) throws ServiceException{
        if (encoder == null) throw new ServiceException("encoder cannot be null.");
        if (kubernetesManager == null) throw new ServiceException("kubernetesManager cannot be null.");

        this.encoder = encoder;
        this.kubernetesManager = kubernetesManager;
    }
    
    public Boolean createPod(String payload) throws ServiceException{
        try{
            mylog.info("Starting process.");
            LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
            
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            Contract contract = objectMapper.readValue(payload, Contract.class);
            contract.validCreate();
            String template = encoder.decode(contract.getTemplate());
            template = template.replace("<job_name>", dtf.format(now));
            kubernetesManager.createJob(contract.getNamespace(),contract.getCluster(), contract.getToken(), template);
            return Boolean.TRUE;
        }catch(Exception e){
            throw new ServiceException(e.getMessage());
        }
    }
    
}
