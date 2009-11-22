package an.chopsticks.service;

/**
 * We support following data types in services. The typed attributes will be converted to Java typed values or provider
 * specific typed values while they are passed to providers.
 */
public enum AttributeType {
    String,
    Boolean,
    Integer,
    Double,
    Time,
    Date,
    DateTime
}