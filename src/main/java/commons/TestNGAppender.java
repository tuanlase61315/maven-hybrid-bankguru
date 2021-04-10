package commons;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class TestNGAppender extends AppenderSkeleton {
	@Override
	protected void append(LoggingEvent event) {
		Reporter.log(getLayout().format(event));
	}
	
	@Override
	public void close() {
		Reporter.log("Logging appender is closed");
	}
	
	@Override
	public boolean requiresLayout() {
		return true;
	}
	
	
	public void info(String logmessage) {
		Reporter.log(logmessage);
	}
}
