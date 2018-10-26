package br.pb.jp.exponentServerSdkJava.communicators.request;

import org.apache.http.entity.ContentType;

public class RequestPropertiesBuilder extends RequestProperties {
	
	// Default constructor.
    public RequestPropertiesBuilder() {}
    
    /**
     * Method user to set into the super class the method type
     * The possible values are POST, GET, PUT and DELETE.
     *
     * @param   method
     *          {@code String} request method
     *
     * @return {@code this} (RequestPropertiesBuilder)
     */
    public RequestPropertiesBuilder method(String method) {
        super.method = method;

        return this;
    }
    
    /**
     * Method used to set the request endpoint into super class
     * Endpoint that will be requeted
     *
     * @param   endpoint
     *          {@code String} request endpoint.
     *
     * @return  {@code this} (RequestPropertiesBuilder)
     */
    public RequestPropertiesBuilder endpoint(String endpoint) {
        super.endpoint = endpoint;

        return this;
    }

    /**
     * Method used to set the request object into super class.
     * The object is the body of the request.
     *
     * @param   body
     *          {@code String} request object in Json format
     *
     * @see java.lang.String
     *
     * @return  {@code this} (RequestPropertiesBuilder)
     */
    public RequestPropertiesBuilder body(String body) {
        super.body = body;

        return this;
    }

    /**
     * Method used to set the model class type.
     * The class in this type will be filled if request
     * returns with a successful response.
     *
     * @param   type
     *          {@code Class} request class type
     *
     * @see java.lang.Class
     *
     * @return  {@code this} (RequestPropertiesBuilder)
     */
    public RequestPropertiesBuilder type(Class type) {
        super.type = type;

        return this;
    }

    /**
     * Method used to set the request content type
     * For now using only { @code APPLICATION_JSON} as unique request content type.
     *
     * @param   contentType
     *          {@code ContentType} request content type
     *
     * @see org.apache.http.entity.ContentType
     *
     * @return  {@code this} (RequestPropertiesBuilder)
     */
    public RequestPropertiesBuilder contentType(ContentType contentType) {
        super.contentType = ContentType.APPLICATION_JSON;

        return this;
    }

    /**
     * Method used to return the object built in fact 
     *
     * @return {@code this} (RequestProperties)
     */
    public RequestProperties build() { return this; }
}
