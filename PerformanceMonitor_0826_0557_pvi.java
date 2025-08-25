// 代码生成时间: 2025-08-26 05:57:47
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * System Performance Monitor Action Class
 * This class provides methods to monitor system performance metrics.
 */
@ParentPackage("default")
@Namespace("/performance")
public class PerformanceMonitor extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private double cpuLoad;
    private long totalMemory;
    private long freeMemory;
    private long usedMemory;
    private long maxMemory;

    /**
     * Gets CPU load percentage
     * @return the CPU load percentage
     */
    public double getCpuLoad() {
        return cpuLoad;
    }

    /**
     * Sets CPU load percentage
     * @param cpuLoad the CPU load percentage
     */
    public void setCpuLoad(double cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    /**
     * Gets total memory in bytes
     * @return the total memory
     */
    public long getTotalMemory() {
        return totalMemory;
    }

    /**
     * Sets total memory in bytes
     * @param totalMemory the total memory
     */
    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    /**
     * Gets free memory in bytes
     * @return the free memory
     */
    public long getFreeMemory() {
        return freeMemory;
    }

    /**
     * Sets free memory in bytes
     * @param freeMemory the free memory
     */
    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    /**
     * Gets used memory in bytes
     * @return the used memory
     */
    public long getUsedMemory() {
        return usedMemory;
    }

    /**
     * Sets used memory in bytes
     * @param usedMemory the used memory
     */
    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    /**
     * Gets maximum memory in bytes
     * @return the maximum memory
     */
    public long getMaxMemory() {
        return maxMemory;
    }

    /**
     * Sets maximum memory in bytes
     * @param maxMemory the maximum memory
     */
    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    /**
     * Monitors system performance
     * @return a map of performance metrics
     */
    @Action(value = "monitor", results = { @Result(name = SUCCESS, type = JSON) })
    public String monitorPerformance() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
            RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();

            cpuLoad = osBean.getSystemCpuLoad();
            totalMemory = runtimeBean.getUptime();
            freeMemory = runtimeBean.getUptime();
            usedMemory = runtimeBean.getUptime();
            maxMemory = runtimeBean.getUptime();

            // Calculate used memory and max memory
            long heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
            usedMemory = heapMemoryUsage.getUsed();
            maxMemory = heapMemoryUsage.getMax();

            // Calculate free memory
            freeMemory = totalMemory - usedMemory;

            Map<String, Object> performanceMetrics = new HashMap<>();
            performanceMetrics.put("cpuLoad", cpuLoad);
            performanceMetrics.put("totalMemory", totalMemory);
            performanceMetrics.put("freeMemory", freeMemory);
            performanceMetrics.put("usedMemory", usedMemory);
            performanceMetrics.put("maxMemory", maxMemory);

            addActionMessage("System performance metrics: " + performanceMetrics);
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Error monitoring system performance: " + e.getMessage());
            return ERROR;
        }
    }
}