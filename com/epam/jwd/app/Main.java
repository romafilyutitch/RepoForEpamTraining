package com.epam.jwd.app;
import com.epam.jwd.model.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main{
	private static final Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args){
		LOGGER.trace("Entering application");
		Point point1 = new Point(1,2);
		Point point2 = new Point(3,4);
		Point point3 = new Point(5,6);
		Point[] points = new Point[]{point1,point2,point3};
		for(Point p : points){
			LOGGER.info(p);
		}
		LOGGER.trace("Exiting application");
	}
}

