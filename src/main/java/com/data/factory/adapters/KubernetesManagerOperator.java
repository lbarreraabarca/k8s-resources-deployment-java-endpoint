package com.data.factory.adapters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Yaml;

import com.data.factory.ports.KubernetesManager;
import com.data.factory.exceptions.KubernetesServiceException;

public class KubernetesManagerOperator implements KubernetesManager {

    private static Logger mylog = LoggerFactory.getLogger(KubernetesManagerOperator.class);
    
    @Override
    public Boolean createJob(String ns, String kubernetesEndpoint, String token, String job) throws KubernetesServiceException{
        try{
            mylog.info(job);
            ApiClient client = Config.fromToken(kubernetesEndpoint, token, false);
            Configuration.setDefaultApiClient(client);
            
            BatchV1Api apiInstance = new BatchV1Api(client);
            V1Job jobObj = Yaml.loadAs(job, V1Job.class);
            mylog.info("createNamespacedJob");
            apiInstance.createNamespacedJob(ns, jobObj, null, null, null);
            return Boolean.TRUE;
        } catch(ApiException e){
            mylog.error(e.getMessage());
            mylog.error("error ", e);
            throw new KubernetesServiceException("job create failure");
        }
    }
}
