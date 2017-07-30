/**
 * Created by David Turk on 7/28/17.
 */
public enum Status {
    INITIAL ("Initial"),
    ASSIGNED ("Assigned"),
    IN_PROGRESS ("In Progress"),
    DONE ("Done)");

    private String englishName;

    Status(String name){
        this.englishName = name;
    }

    public static Status[] getAllStatus(){
        return new Status[] {Status.INITIAL, Status.ASSIGNED, Status.IN_PROGRESS, Status.DONE};
    }

    public String getEnglishName(){
        return this.englishName;
    }
}
