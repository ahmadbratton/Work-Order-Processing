/**
 * Created by David Turk on 7/28/17.
 */
public class WorkOrder {
    private long id;
    private String description;
    private String senderName;
    private Status status;

    public WorkOrder(){

    }

    public WorkOrder(String description, String senderName, Status status) {
        this.description = description;
        this.senderName = senderName;
        this.status = status;
        id = System.currentTimeMillis();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "description='" + description + '\'' +
                ", senderName='" + senderName + '\'' +
                ", status=" + status +
                '}';
    }
}
