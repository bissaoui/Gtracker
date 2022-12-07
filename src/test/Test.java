package test;


import entities.VehiculeTracker;
import entities.Position;
import java.util.Date;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import service.PositionService;
import service.TrackerService;
import service.VehiculeTrackerService;
import service.VehiculeServices;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

             VehiculeTrackerService vts = new VehiculeTrackerService();
            PositionService ps = new PositionService();
		VehiculeServices vs = new  VehiculeServices();
		TrackerService ts = new TrackerService();
                
                for( Position p :ps.findPositionsBetweenDates(ts.findById(1),new Date ("2020/3/30"),new Date ("2022/3/30"))){
                 		System.out.println(p.toString());

                }
		
	//if(vts.delete(vts.findById(2)))
//		System.out.println("okey");
	//}
                
                
	
                

        }
}
