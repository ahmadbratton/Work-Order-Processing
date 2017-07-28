import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.Scanner;


/**
 * Created by David Turk on 7/28/17.
 */
public class Creator {
    public void createWorkOrders() {
        // read input:
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the WorkOrder description:”);
                creator.setDescription(scanner.nextline());

        System.out.println("Enter the name of who sent the WorkOrder:");
        creator.setSenderName(scanner.nextline());

        creator.setStatus = Status.INITIAL;

        creator.getId();
        // create work orders

        // write as json files

        ObjectMapper mapper = new ObjectMapper();
        fileName = creator.getId();
        mapper.writeValue(new File(fileName), creator);
        String json = mapper.writeValueAsString(creator);

    }

    public static void main(String args[]) {
        Creator creator = new Creator();
        while(true){
            creator.createWorkOrders();
        }
    }
}
