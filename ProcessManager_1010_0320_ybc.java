// 代码生成时间: 2025-10-10 03:20:23
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.QueryExp;
import java.lang.management.ManagementFactory;

public class ProcessManager extends ActionSupport {

    /*
     * 获取所有进程列表
     * @return 返回所有进程的列表
     */
    @Action("getAllProcesses")
    public String getAllProcesses() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            QueryExp query = Query.match(Query.attr("Name"), "java.lang:type=OperatingSystem");
            ObjectName objectName = new ObjectName("java.lang", "type", "OperatingSystem");
            List<ObjectName> list = server.queryNames(objectName, query);
            Map<String, String> processList = ManagementFactory.getPlatformMBeanServer().getAttribute(objectName, "ProcessCpuLoad").toString();
            // 将进程列表信息存入值栈
            addActionMessage("Process List: " + processList);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error retrieving process list: " + e.toString());
            return ERROR;
        }
    }

    /*
     * 终止指定进程
     * @param processId 要终止的进程ID
     * @return 返回操作结果
     */
    @Action("terminateProcess")
    public String terminateProcess(int processId) {
        try {
            ProcessBuilder builder = new ProcessBuilder("kill", String.valueOf(processId));
            builder.start();
            addActionMessage("Process with ID " + processId + " terminated successfully.");
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error terminating process: " + e.toString());
            return ERROR;
        }
    }

    /*
     * 获取当前系统时间
     * @return 返回当前系统时间的字符串表示
     */
    @Action("getCurrentTime")
    public String getCurrentTime() {
        Date currentDate = new Date();
        addActionMessage("Current Time: " + currentDate.toString());
        return SUCCESS;
    }
}
