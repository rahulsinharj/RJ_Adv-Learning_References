package com.ashok.util;

/**
 * This class is used to declare constants of this application
 * 
 * @author Ashok
 *
 */
public class KafkaConstants {

	public static final String TOPIC = "customer";
	public static final String GROUP_ID = "group_customer";		// GROUP_ID = i.e, For every Consumer we can give 1 GROUP_ID as an Identifier, i.e, Multiple messages can be stored as a Single Group.
	public static final String HOST = "localhost:9092";

}
