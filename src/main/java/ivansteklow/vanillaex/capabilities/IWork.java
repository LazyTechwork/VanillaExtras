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
