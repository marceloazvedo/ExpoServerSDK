package br.pb.jp.exponentServerSdkJava.communicators.request;

import org.apache.http.entity.ContentType;

public class RequestProperties {
	
	protected String method;
    protected String endpoint;
    protected String body;
    protected Class type;
    protected ContentType contentType;
    protected String accept;
    
    public String getMethod() { return method; }

    public String getEndpoint() { return endpoint; }

    public Object getObject() { return body; }

    public <T> Class<T> getType() { return type; }

    public ContentType getContentType() { return contentType; }

    public String getAccept() { return accept; }
    
}
