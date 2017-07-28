import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by David Turk on 7/28/17.
 */
public class Creator {

    public void createWorkOrders() throws IOException {
        // read input:
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the WorkOrder description:");
        String description = scanner.nextLine();

        System.out.println("Enter the name of who sent the WorkOrder:");
        String nameOfSender = scanner.nextLine();

        // create work orders
        WorkOrder order = new WorkOrder(description,nameOfSender,Status.INITIAL);

        // write as json files

        String fileName = order.getId() + ".json";

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(fileName), order);

        String json = mapper.writeValueAsString(order);

    }

    public static void main(String args[]) {
        Creator creator = new Creator();
        while(true){

            creator.createWorkOrders();
        }
    }
}
