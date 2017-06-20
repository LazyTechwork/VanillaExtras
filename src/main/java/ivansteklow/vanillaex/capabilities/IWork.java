/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.capabilities;

/**
 * Basic interface for mod worker
 * 
 * @author IvanSteklow
 *
 */
public interface IWork {

	int getWorkDone();

	int getMaxWork();

	void doWork();

	void workDone();

}
