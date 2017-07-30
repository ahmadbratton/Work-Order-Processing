import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by David Turk on 7/28/17.
 */
public class Processor {
    private Map<Status,ArrayList> workOrders = new HashMap<>();

    public Processor() {
        for(Status status : Status.getAllStatus()){
            workOrders.put(status, new ArrayList<WorkOrder>());
        }
    }

    public void processWorkOrders() {
        moveIt();
        readIt();
    }

    private void moveIt() {
        printMap();

        ArrayList<WorkOrder> inProgressOrders = workOrders.get(Status.IN_PROGRESS);
        if (inProgressOrders.size() > 0){
            WorkOrder firstInprogress = inProgressOrders.get(0);
            inProgressOrders.remove(0);
            workOrders.get(Status.DONE).add(firstInprogress);
        }

        ArrayList<WorkOrder> assignedOrders = workOrders.get(Status.ASSIGNED);
        if (assignedOrders.size() > 0){
            WorkOrder firstAssigned = assignedOrders.get(0);
            assignedOrders.remove(0);
            workOrders.get(Status.IN_PROGRESS).add(firstAssigned);
        }
        ArrayList<WorkOrder> initialOrders = workOrders.get(Status.INITIAL);
        if (initialOrders.size() > 0){
            WorkOrder firstInital = initialOrders.get(0);
            initialOrders.remove(0);
            workOrders.get(Status.ASSIGNED).add(firstInital);
        }
        printMap();

    }

    private void printMap(){
        for(Status status : workOrders.keySet()){
            System.out.println(status.getEnglishName()+":");
            ArrayList<WorkOrder> orders = workOrders.get(status);
            for(WorkOrder order : orders){
                System.out.println("\t" + order.toString());
            }
        }
    }

    private void readIt() {
        // read the json files into WorkOrders and put in map
        File[] jsonFiles = findAllJsonFiles();
        String[] jsonStrings = extractJsonStrings(jsonFiles);
        WorkOrder[] newOrders = createWorkOrders(jsonStrings);
        for(WorkOrder order : newOrders){
            workOrders.get(order.getStatus()).add(order);
        }
    }

    private static File[] findAllJsonFiles(){
        ArrayList<File> jsonFiles = new ArrayList<>();
        File directory = new File(".");
        for (File f : directory.listFiles()) {
            if (f.getName().endsWith(".json")) {
                jsonFiles.add(f);
                f.delete();
            }
        }
        return jsonFiles.toArray(new File[0]);
    }

    private static String[] extractJsonStrings(File[] jsonFiles){
        ArrayList<String> jsonStrings = new ArrayList<>();
        Scanner fileScanner;
        for(File f : jsonFiles){
            try {
                fileScanner = new Scanner(f);
                ArrayList<String> fileContents = new ArrayList<>();
                while( fileScanner.hasNext() ){
                    fileContents.add( fileScanner.nextLine() );
                }
                jsonStrings.add(fileContents.toArray(new String[0])[0]);
            } catch (FileNotFoundException e) {
                System.out.println("ERROR! File " + f.getPath() + " not found");
                e.printStackTrace();
            }
        }
        return jsonStrings.toArray(new String[0]);
    }

    private static WorkOrder[] createWorkOrders(String[] jsonStrings){
        ArrayList<WorkOrder> reports = new ArrayList<>();
        for(String json : jsonStrings){
            try{
                ObjectMapper mapper = new ObjectMapper();
                WorkOrder vi = mapper.readValue(json, WorkOrder.class);
                reports.add(vi);
            } catch (IOException e) {
                System.out.println("\t\tCreating vehicle info: Failure!");
                e.printStackTrace();
            }
        }
        return reports.toArray(new WorkOrder[0]);
    }

    public static void main(String args[]) {
        Processor processor = new Processor();
        while (true) {
            processor.processWorkOrders();
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
