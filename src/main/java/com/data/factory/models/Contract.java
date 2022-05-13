package com.data.factory.models;

import com.data.factory.exceptions.RequestException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @JsonProperty
    private String template;

    @JsonProperty
    private String namespace;

    @JsonProperty
    private String cluster;

    @JsonProperty
    private String token;

    public Boolean validCreate() throws RequestException{
        if(this.template == null || this.template.isEmpty()) throw new RequestException("template cannot be null or empty.");
        if(this.namespace == null || this.namespace.isEmpty()) throw new RequestException("namespace cannot be null or empty.");
        if(this.cluster == null || this.cluster.isEmpty()) throw new RequestException("namespace cannot be null or empty.");
        if(this.token == null || this.token.isEmpty()) throw new RequestException("namespace cannot be null or empty.");
        return Boolean.TRUE;
    }

    public String getTemplate() {
        return template;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getCluster() {
        return cluster;
    }

    public String getToken() {
        return token;
    }

}
