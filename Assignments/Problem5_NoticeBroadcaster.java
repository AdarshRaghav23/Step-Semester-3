// Question 5: The Campus Notice Broadcaster

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface NotificationChannel {
    void send(String studentName, String noticeTitle);
}

class EmailChannel implements NotificationChannel {
    public void send(String studentName, String noticeTitle) {
        System.out.println("[Email → " + studentName + "] " + noticeTitle);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String studentName, String noticeTitle) {
        System.out.println("[SMS → " + studentName + "] " + noticeTitle);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String studentName, String noticeTitle) {
        System.out.println("[App → " + studentName + "] " + noticeTitle);
    }
}

class NoticeStudent {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels = new ArrayList<NotificationChannel>();

    public NoticeStudent(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }

    public void receiveNotice(String title) {
        for (NotificationChannel channel : preferredChannels) {
            channel.send(name, title);
        }
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return targetDepartments;
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && targetDepartments != null && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private List<NoticeStudent> registeredStudents = new ArrayList<NoticeStudent>();

    public void registerStudent(NoticeStudent student) {
        registeredStudents.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        StringBuilder deptsStr = new StringBuilder();
        List<String> depts = notice.getTargetDepartments();
        for (int i = 0; i < depts.size(); i++) {
            deptsStr.append(depts.get(i));
            if (i < depts.size() - 1) deptsStr.append(", ");
        }

        System.out.println("Notice '" + notice.getTitle() + "' posted to " + deptsStr.toString() + ".");

        for (NoticeStudent student : registeredStudents) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                student.receiveNotice(notice.getTitle());
            }
        }
    }
}

public class Problem5_NoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        // Asha (CSE) prefers Email and App
        NoticeStudent asha = new NoticeStudent("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        // Ravi (ECE) prefers SMS
        NoticeStudent ravi = new NoticeStudent("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice notice1 = new Notice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        noticeBoard.postNotice(notice1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice notice2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        // Admin attempts to post notice 'Sports Day' with no target department
        Notice notice3 = new Notice("Sports Day", new ArrayList<String>());
        noticeBoard.postNotice(notice3);
    }
}