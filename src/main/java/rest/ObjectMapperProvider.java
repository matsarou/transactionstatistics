package rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Provides an instance of the JSON Object Mapper that is used to serialize objects to JSON strings.
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

	/**
	 * Creates an Object mapper.
	 * @return the mapper
	 */
	public static ObjectMapper newObjectMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return result;
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return newObjectMapper();
	}
}
