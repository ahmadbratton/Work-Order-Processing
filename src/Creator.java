import org.codehaus.jackson.JsonProcessingException;
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
        WorkOrder order = new WorkOrder(description, nameOfSender, Status.INITIAL);

        // write as json files
        try {
            String fileName = order.getId() + ".json";
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), order);
            String json = mapper.writeValueAsString(order);
        } catch (IOException e) {
            System.out.println("Error in processing file.");
            e.printStackTrace();
        }


    }

    public static void main(String args[]) {
        Creator creator = new Creator();
<<<<<<< HEAD
        while(true){
=======
        while (true) {

>>>>>>> 6c8917b4a7a1ea4a0e082e87c16d9a780c85c243
            creator.createWorkOrders();
        }
    }
}

